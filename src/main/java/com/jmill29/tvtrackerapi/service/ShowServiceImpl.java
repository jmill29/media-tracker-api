package com.jmill29.tvtrackerapi.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.jmill29.tvtrackerapi.dao.ShowDao;
import com.jmill29.tvtrackerapi.exception.NoShowsFoundException;
import com.jmill29.tvtrackerapi.exception.ShowAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.ShowNotFoundException;
import com.jmill29.tvtrackerapi.model.Show;

public class ShowServiceImpl implements ShowService {

    private final ShowDao showDao;

    public ShowServiceImpl(ShowDao showDao) {
        this.showDao = showDao;
    }

    @Override
    public Optional<Show> findById(int id) throws SQLException, ShowNotFoundException, IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        Optional<Show> show = showDao.findById(id);
        if (show.isEmpty()) {
            throw new ShowNotFoundException("Show with ID " + id + " not found.");
        }
        return show;
    }

    @Override
    public List<Show> findAll() throws SQLException, NoShowsFoundException {
        List<Show> shows = showDao.findAll();
        if (shows.isEmpty()) {
            throw new NoShowsFoundException("No shows found in the database.");
        }
        return shows;
    }

    @Override
    public List<Show> findByName(String name) throws SQLException, ShowNotFoundException, IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        List<Show> shows = showDao.findByName(name);
        if (shows.isEmpty()) {
            throw new ShowNotFoundException("No shows found with the name: " + name);
        }
        return shows;
    }

    @Override
    public List<Show> findByGenre(String genre) throws SQLException, NoShowsFoundException, IllegalArgumentException {
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Genre cannot be null or empty.");
        }

        List<Show> shows = showDao.findByGenre(genre);
        if (shows.isEmpty()) {
            throw new NoShowsFoundException("No shows found in the genre: " + genre);
        }
        return shows;
    }

    @Override
    public boolean save(Show show) throws SQLException, ShowAlreadyExistsException, ShowNotFoundException {
        if (show == null) {
            throw new IllegalArgumentException("Show cannot be null.");
        }

        // If the show has an ID of 0, it is considered a new show.
        return showDao.save(show);
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        // Check if the show exists before attempting to delete
        Optional<Show> show = showDao.findById(id);
        if (show.isEmpty()) {
            throw new ShowNotFoundException("Show with ID " + id + " not found.");
        }

        return showDao.deleteById(id);
    }

    //TODO: Implement the method to add a show and its genres.
    private boolean addShowandGenres(Show show) throws SQLException {
        // This method is not implemented yet.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
