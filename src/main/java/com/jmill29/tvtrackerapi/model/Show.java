package com.jmill29.tvtrackerapi.model;

import java.time.LocalDateTime;

/**
 * Model representing a TV show in the system.
 */
public class Show {

    /** The unique ID of the show */
    private int id;
    /** The name of the show */
    private String name;
    /** The description of the show */
    private String description;
    /** The image URL for the show */
    private String imageUrl;
    /** The number of episodes in the show */
    private int numEpisodes;
    /** The release year of the show */
    private short releaseYear;
    /** The date and time the show was created in the system */
    private LocalDateTime createdAt;

    /**
     * Default constructor.
     */
    public Show() {}

    /**
     * Constructs a Show with all fields.
     * @param id the unique ID of the show
     * @param name the name of the show
     * @param description the description of the show
     * @param imageUrl the image URL for the show
     * @param numEpisodes the number of episodes
     * @param releaseYear the release year
     * @param createdAt the date and time the show was created
     */
    public Show(int id, String name, String description, String imageUrl, int numEpisodes, short releaseYear, LocalDateTime createdAt) {
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
     * Returns a string representation of the Show object.
     * Useful for logging and debugging purposes.
     * @return a string representation of the show
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
