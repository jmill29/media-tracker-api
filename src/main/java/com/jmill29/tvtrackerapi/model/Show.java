package com.jmill29.tvtrackerapi.model;

import java.time.LocalDateTime;

/**
 * Represents a TV show within the application.
 * <p>
 * This model is used throughout the system to encapsulate show-related data,
 * including metadata such as title, description, episode count, and creation timestamp.
 * </p>
 */
public class Show {

    /** The unique identifier for the show */
    private int id;

    /** The title of the show */
    private String name;

    /** A brief description or synopsis of the show */
    private String description;

    /** A URL pointing to the show's image or poster */
    private String imageUrl;

    /** The total number of episodes in the show */
    private int numEpisodes;

    /** The year the show was released */
    private short releaseYear;

    /** The timestamp when the show record was created in the system */
    private LocalDateTime createdAt;

    /**
     * Default no-args constructor.
     */
    public Show() {}

    /**
     * Constructs a new {@code Show} with all fields initialized.
     *
     * @param id           the unique ID of the show
     * @param name         the name of the show
     * @param description  the description of the show
     * @param imageUrl     the image URL for the show
     * @param numEpisodes  the number of episodes in the show
     * @param releaseYear  the year the show was released
     * @param createdAt    the timestamp when the show was added to the system
     */
    public Show(int id, String name, String description, String imageUrl,
                int numEpisodes, short releaseYear, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.numEpisodes = numEpisodes;
        this.releaseYear = releaseYear;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getNumEpisodes() {
        return numEpisodes;
    }

    public void setNumEpisodes(int numEpisodes) {
        this.numEpisodes = numEpisodes;
    }

    public short getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns a string representation of this show object.
     * Useful for debugging and logging purposes.
     *
     * @return a string summarizing the show's details
     */
    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", numEpisodes=" + numEpisodes +
                ", releaseYear=" + releaseYear +
                ", createdAt=" + createdAt +
                '}';
    }
}
