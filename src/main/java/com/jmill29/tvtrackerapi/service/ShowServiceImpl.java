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
 * Service implementation for show-related operations.
 * Handles business logic for retrieving, saving, and deleting shows.
 */
@Service
public class ShowServiceImpl implements ShowService {

    private final ShowDao showDao;


    /**
     * Constructs a ShowServiceImpl with the given ShowDao.
     * @param showDao the ShowDao to use for data access
     */
    public ShowServiceImpl(ShowDao showDao) {
        this.showDao = showDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Show> findById(int id) throws IllegalArgumentException, DatabaseException, ShowNotFoundException {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Show> findAll() throws DatabaseException, NoShowsFoundException {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Show> findByName(String name) throws IllegalArgumentException, DatabaseException, ShowNotFoundException {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Show> findByGenre(String genre) throws IllegalArgumentException, DatabaseException, NoShowsFoundException {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(Show show) throws IllegalArgumentException, DatabaseException, ShowAlreadyExistsException, ShowNotFoundException {
        if (show == null) {
            throw new IllegalArgumentException("Show cannot be null.");
        }

        try {
            // If the show has an ID of 0, it is considered a new show.
            return showDao.save(show);
        } catch (SQLException ex) {
            // Check if the exception is due to a duplicate entry
            if (ex.getMessage().contains("duplicate key")) {
                throw new ShowAlreadyExistsException("Show with ID " + show.getId() + " already exists.");
            } else {
                throw new DatabaseException("Error accessing the database while saving the show: " + ex);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(int id) throws DatabaseException, IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        try {
            // Check if the show exists before attempting to delete
            Optional<Show> show = showDao.findById(id);
            if (show.isEmpty()) {
                throw new ShowNotFoundException("Show with ID " + id + " not found.");
            }

            return showDao.deleteById(id);
        } catch (SQLException ex) {
            throw new DatabaseException("Error accessing the database while deleting the show with ID " + id + ": " + ex);
        }
    }

    //TODO: Implement the method to add a show and its genres.
    private boolean addShowandGenres(Show show) throws SQLException {
        // This method is not implemented yet.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
