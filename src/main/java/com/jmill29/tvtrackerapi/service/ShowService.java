package com.jmill29.tvtrackerapi.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.jmill29.tvtrackerapi.exception.DatabaseException;
import com.jmill29.tvtrackerapi.exception.NoShowsFoundException;
import com.jmill29.tvtrackerapi.exception.ShowAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.ShowNotFoundException;
import com.jmill29.tvtrackerapi.model.Show;

public interface ShowService {

    /**
     * Finds a show by its unique ID.
     *
     * <p>
     * This method queries the database for a show with the specified unique identifier.
     * </p>
     *
     * @param id the ID of the show
     * @return an {@code Optional} containing the {@code Show} if found, or empty if not found
     * @throws IllegalArgumentException if the ID is invalid (e.g., less than or equal to 0)
     * @throws DatabaseException if a database access error occurs
     * @throws ShowNotFoundException if no show is found with the given ID
     */
    Optional<Show> findById(int id) throws IllegalArgumentException, DatabaseException, ShowNotFoundException;

    /**
     * Retrieves all shows from the database.
     *
     * <p>
     * Returns a complete list of all shows stored in the system.
     * </p>
     *
     * @return a {@code List} of all {@code Show} records
     * @throws DatabaseException if a database access error occurs
     * @throws NoShowsFoundException if no shows are found in the database
     */
    List<Show> findAll() throws DatabaseException, NoShowsFoundException;

    /**
     * Finds shows by their name (case-insensitive).
     *
     * <p>
     * This method performs a search that may return multiple results matching the given name,
     * using case-insensitive comparison.
     * </p>
     *
     * @param name the name of the show to search for
     * @return a {@code List} of {@code Show} objects with matching names
     * @throws IllegalArgumentException if the name is null or empty
     * @throws DatabaseException if a database access error occurs
     * @throws ShowNotFoundException if no shows are found with the given name
     */
    List<Show> findByName(String name) throws IllegalArgumentException, DatabaseException, ShowNotFoundException;

    /**
     * Finds shows by their genre.
     *
     * <p>
     * Returns all shows that belong to the specified genre.
     * </p>
     *
     * @param genre the genre to search for
     * @return a {@code List} of {@code Show} objects in the specified genre
     * @throws IllegalArgumentException if the genre is null or empty
     * @throws DatabaseException if a database access error occurs
     * @throws NoShowsFoundException if no shows are found for the given genre
     */
    List<Show> findByGenre(String genre) throws IllegalArgumentException, DatabaseException, NoShowsFoundException;

    /**
     * Saves a show to the database.
     *
     * <p>
     * If the {@code showId} of the given {@link Show} object is 0, a new show is inserted.
     * Otherwise, the existing show is updated based on its ID.
     * </p>
     *
     * @param show the {@link Show} object to insert or update
     * @return {@code true} if the operation was successful, {@code false} otherwise
     * @throws IllegalArgumentException if the show object is null or contains invalid fields
     * @throws SQLException if a database access error occurs
     */
    boolean save(Show show) throws IllegalArgumentException, DatabaseException, ShowAlreadyExistsException, ShowNotFoundException;

    /**
     * Deletes a show by its unique ID.
     *
     * <p>
     * Removes the show record with the given ID from the database.
     * </p>
     *
     * @param id the ID of the show to delete
     * @return {@code true} if the show was deleted successfully, {@code false} otherwise
     * @throws IllegalArgumentException if the ID is invalid (e.g., less than or equal to 0)
     * @throws DatabaseException if a database access error occurs
     */
    boolean deleteById(int id) throws IllegalArgumentException, DatabaseException;

}
