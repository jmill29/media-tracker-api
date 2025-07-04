package com.jmill29.tvtrackerapi.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jmill29.tvtrackerapi.dao.ShowDao;
import com.jmill29.tvtrackerapi.exception.DatabaseException;
import com.jmill29.tvtrackerapi.exception.NoShowsFoundException;
import com.jmill29.tvtrackerapi.exception.ShowAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.ShowNotFoundException;
import com.jmill29.tvtrackerapi.model.Show;

/**
 * Implementation of the {@link ShowService} interface.
 * <p>
 * Provides business logic for managing TV shows, including retrieval,
 * search, insertion, updating, and deletion.
 */
@Service
public class ShowServiceImpl implements ShowService {

    private final ShowDao showDao;

    /**
     * Initializes a new {@code ShowServiceImpl} with the given {@link ShowDao}.
     *
     * @param showDao the data access object used to interact with the show database
     */
    public ShowServiceImpl(ShowDao showDao) {
        this.showDao = showDao;
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Show> findById(int id)
            throws IllegalArgumentException, DatabaseException, ShowNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        try {
            Optional<Show> show = showDao.findById(id);
            if (show.isEmpty()) {
                throw new ShowNotFoundException("Show with ID " + id + " not found.");
            }
            return show;
        } catch (SQLException ex) {
            throw new DatabaseException("Error accessing the database while retrieving show with ID " + id + ": " + ex);
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<Show> findAll()
            throws DatabaseException, NoShowsFoundException {
        try {
            List<Show> shows = showDao.findAll();
            if (shows.isEmpty()) {
                throw new NoShowsFoundException("No shows found in the database.");
            }
            return shows;
        } catch (SQLException e) {
            throw new DatabaseException("Error accessing the database while retrieving shows." + e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<Show> findByName(String name)
            throws IllegalArgumentException, DatabaseException, ShowNotFoundException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        try {
            List<Show> shows = showDao.findByName(name);
            if (shows.isEmpty()) {
                throw new ShowNotFoundException("No shows found with the name: " + name);
            }
            return shows;
        } catch (SQLException e) {
            throw new DatabaseException("Error accessing the database while searching for shows by name: " + name + ", " + e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<Show> findByGenre(String genre)
            throws IllegalArgumentException, DatabaseException, NoShowsFoundException {
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Genre cannot be null or empty.");
        }

        try {
            List<Show> shows = showDao.findByGenre(genre);
            if (shows.isEmpty()) {
                throw new NoShowsFoundException("No shows found in the genre: " + genre);
            }
            return shows;
        } catch (SQLException e) {
            throw new DatabaseException("Error accessing the database while searching for shows by genre: " + genre + ", " + e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean save(Show show)
            throws IllegalArgumentException, DatabaseException, ShowAlreadyExistsException, ShowNotFoundException {
        if (show == null) {
            throw new IllegalArgumentException("Show cannot be null.");
        }

        try {
            return showDao.save(show);
        } catch (SQLException ex) {
            if (ex.getMessage().contains("duplicate key")) {
                throw new ShowAlreadyExistsException("Show with ID " + show.getId() + " already exists.");
            } else {
                throw new DatabaseException("Error accessing the database while saving the show: " + ex);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean deleteById(int id)
            throws DatabaseException, IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        try {
            Optional<Show> show = showDao.findById(id);
            if (show.isEmpty()) {
                throw new ShowNotFoundException("Show with ID " + id + " not found.");
            }

            return showDao.deleteById(id);
        } catch (SQLException ex) {
            throw new DatabaseException("Error accessing the database while deleting the show with ID " + id + ": " + ex);
        }
    }

    /**
     * Placeholder for a future feature that will allow adding a show along with its genres.
     *
     * @param show the show to be inserted along with its genres
     * @return currently unimplemented
     * @throws SQLException if a database error occurs
     * @throws UnsupportedOperationException always, since the method is not yet implemented
     */
    private boolean addShowandGenres(Show show) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
