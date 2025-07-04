package com.jmill29.tvtrackerapi.service;

import java.util.List;
import java.util.Optional;

import com.jmill29.tvtrackerapi.exception.DatabaseException;
import com.jmill29.tvtrackerapi.exception.NoShowsFoundException;
import com.jmill29.tvtrackerapi.exception.ShowAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.ShowNotFoundException;
import com.jmill29.tvtrackerapi.model.Show;

/**
 * Service interface for managing TV shows.
 * <p>
 * Defines operations for retrieving, creating, updating, and deleting shows,
 * as well as searching by name or genre.
 */
public interface ShowService {

    /**
     * Retrieves a show by its unique ID.
     *
     * @param id the ID of the show
     * @return an {@code Optional} containing the {@code Show} if found, or empty if not
     * @throws IllegalArgumentException if the ID is invalid (e.g., ≤ 0)
     * @throws DatabaseException if a database access error occurs
     * @throws ShowNotFoundException if no show is found with the given ID
     */
    Optional<Show> findById(int id)
            throws IllegalArgumentException, DatabaseException, ShowNotFoundException;

    /**
     * Retrieves all shows in the system.
     *
     * @return a {@code List} of all {@code Show} records
     * @throws DatabaseException if a database access error occurs
     * @throws NoShowsFoundException if no shows are found
     */
    List<Show> findAll()
            throws DatabaseException, NoShowsFoundException;

    /**
     * Searches for shows by name (case-insensitive).
     *
     * @param name the show name to search for
     * @return a {@code List} of matching {@code Show} objects
     * @throws IllegalArgumentException if the name is null or empty
     * @throws DatabaseException if a database access error occurs
     * @throws ShowNotFoundException if no shows match the given name
     */
    List<Show> findByName(String name)
            throws IllegalArgumentException, DatabaseException, ShowNotFoundException;

    /**
     * Searches for shows by genre.
     *
     * @param genre the genre to search for
     * @return a {@code List} of shows in the specified genre
     * @throws IllegalArgumentException if the genre is null or empty
     * @throws DatabaseException if a database access error occurs
     * @throws NoShowsFoundException if no shows are found for the given genre
     */
    List<Show> findByGenre(String genre)
            throws IllegalArgumentException, DatabaseException, NoShowsFoundException;

    /**
     * Saves a new or existing show to the database.
     * <p>
     * If the {@code showId} is 0, a new record is inserted.
     * Otherwise, the existing show is updated.
     *
     * @param show the {@link Show} object to create or update
     * @return {@code true} if the operation succeeded, {@code false} otherwise
     * @throws IllegalArgumentException if the show is null or contains invalid fields
     * @throws DatabaseException if a database access error occurs
     * @throws ShowAlreadyExistsException if the show already exists and cannot be duplicated
     * @throws ShowNotFoundException if updating a show that does not exist
     */
    boolean save(Show show)
            throws IllegalArgumentException, DatabaseException, ShowAlreadyExistsException, ShowNotFoundException;

    /**
     * Deletes a show by its ID.
     *
     * @param id the ID of the show to delete
     * @return {@code true} if the deletion was successful, {@code false} otherwise
     * @throws IllegalArgumentException if the ID is invalid (e.g., ≤ 0)
     * @throws DatabaseException if a database access error occurs
     */
    boolean deleteById(int id)
            throws IllegalArgumentException, DatabaseException;
}
