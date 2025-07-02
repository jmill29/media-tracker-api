package com.jmill29.tvtrackerapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jmill29.tvtrackerapi.exception.ShowAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.ShowNotFoundException;
import com.jmill29.tvtrackerapi.model.Show;

@Repository
public class ShowDaoImpl implements ShowDao {

    private final DataSource dataSource;

    @Autowired
    public ShowDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Show> findById(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT * FROM shows WHERE show_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setInt(1, id);

            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                // If a show is found, map and return it
                return Optional.of(mapShow(rs));
            }
            // Return empty if no show found with the given ID
            return Optional.empty();
        }
    }

    @Override
    public List<Show> findAll() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT * FROM shows";
            PreparedStatement pStmt = conn.prepareStatement(query);
            ResultSet rs = pStmt.executeQuery();

            List<Show> shows = new ArrayList<>();
            while (rs.next()) {
                // Map each row to a Show object
                shows.add(mapShow(rs));
            }
            return shows;
        }
    }

    @Override
    public List<Show> findByName(String name) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT * FROM shows WHERE LOWER(show_name) LIKE LOWER(?)";
            PreparedStatement pStmt = conn.prepareStatement(query);
            // Use LIKE for partial, case-insensitive match
            pStmt.setString(1, "%" + name + "%");

            ResultSet rs = pStmt.executeQuery();
            List<Show> shows = new ArrayList<>();
            while (rs.next()) {
                shows.add(mapShow(rs));
            }
            return shows;
        }
    }

    @Override
    public List<Show> findByGenre(String genre) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            // Join with show_genres to filter by genre
            String query = "SELECT s.* FROM shows s JOIN show_genres sg ON s.show_id = sg.show_id WHERE sg.genre = ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1, genre);

            ResultSet rs = pStmt.executeQuery();
            List<Show> shows = new ArrayList<>();
            while (rs.next()) {
                shows.add(mapShow(rs));
            }
            return shows;
        }
    }

    @Override
    public boolean save(Show show) throws SQLException, ShowAlreadyExistsException, ShowNotFoundException {
        if (show.getId() == 0) {
            // Insert: check if a show with the same name and release year already exists
            try (Connection conn = dataSource.getConnection()) {
                if (alreadyExists(show, conn)) {
                    // Prevent duplicate shows
                    throw new ShowAlreadyExistsException("Show with name " + show.getName() + " and release year " + show.getReleaseYear() + " already exists.");
                }
                return create(show, conn);
            }
        } else {
            // Update: ensure the show exists before updating
            if (findById(show.getId()).isEmpty()) {
                throw new ShowNotFoundException("Show with ID " + show.getId() + " not found.");
            }

            try (Connection conn = dataSource.getConnection()) {
                return update(show, conn);
            }
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "DELETE FROM shows WHERE show_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setInt(1, id);

            // Returns true if a row was deleted, false if not found
            return pStmt.executeUpdate() > 0;
        }
    }

    private boolean create(Show show, Connection conn) throws SQLException {
        // Insert a new show into the database
        String query = "INSERT INTO shows (show_name, description, image_url, num_episodes, release_year) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pStmt = conn.prepareStatement(query);
        pStmt.setString(1, show.getName());
        pStmt.setString(2, show.getDescription());
        pStmt.setString(3, show.getImageUrl());
        pStmt.setInt(4, show.getNumEpisodes());
        pStmt.setShort(5, show.getReleaseYear());

        return pStmt.executeUpdate() > 0;
    }

    private boolean update(Show show, Connection conn) throws SQLException {
        // Update an existing show in the database
        String query = "UPDATE shows SET show_name = ?, description = ?, image_url = ?, num_episodes = ?, release_year = ? WHERE show_id = ?";
        PreparedStatement pStmt = conn.prepareStatement(query);
        pStmt.setString(1, show.getName());
        pStmt.setString(2, show.getDescription());
        pStmt.setString(3, show.getImageUrl());
        pStmt.setInt(4, show.getNumEpisodes());
        pStmt.setShort(5, show.getReleaseYear());
        pStmt.setInt(6, show.getId());

        return pStmt.executeUpdate() > 0;
    }

    private Show mapShow(ResultSet rs) throws SQLException {
        // Map a ResultSet row to a Show object
        return new Show(
            rs.getInt("show_id"),
            rs.getString("show_name"),
            rs.getString("description"),
            rs.getString("image_url"),
            rs.getInt("num_episodes"),
            rs.getShort("release_year"),
            rs.getTimestamp("created_at").toLocalDateTime()
        );
    }

    private boolean alreadyExists(Show show, Connection conn) throws SQLException {
        // Check for an existing show with the same name (case-insensitive) and release year
        String query = "SELECT 1 FROM shows WHERE LOWER(show_name) = LOWER(?) AND release_year = ?";
        PreparedStatement pStmt = conn.prepareStatement(query);
        pStmt.setString(1, show.getName());
        pStmt.setShort(2, show.getReleaseYear());

        ResultSet rs = pStmt.executeQuery();
        return rs.next();
    }

}
