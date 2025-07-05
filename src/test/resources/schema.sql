-- Copied from capstone_schema_seed.sql for H2 test initialization


-- Drop tables if they already exist (for reset convenience)
DROP TABLE IF EXISTS user_watch_history;
DROP TABLE IF EXISTS show_genres;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS tv_shows;
DROP TABLE IF EXISTS users;


-- Users Table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(320) NOT NULL UNIQUE,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE authorities (
    username VARCHAR(100),
    authority VARCHAR(50),
    FOREIGN KEY (username) REFERENCES users(username)
);

-- TV Shows Table
CREATE TABLE tv_shows (
    show_id INT AUTO_INCREMENT PRIMARY KEY,
    show_name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    image_url VARCHAR(2083) NOT NULL,
    num_episodes INT NOT NULL,
    release_year SMALLINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Genres Table
CREATE TABLE genres (
    genre_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- User Watch History Table
CREATE TABLE user_watch_history (
    user_id INT NOT NULL,
    show_id INT NOT NULL,
    status VARCHAR(32) NOT NULL,
    episodes_watched INT DEFAULT 0,
    rating INT DEFAULT NULL CHECK (rating IS NULL OR rating BETWEEN 1 AND 5),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    favorite BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (user_id, show_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (show_id) REFERENCES tv_shows(show_id) ON DELETE CASCADE
);

-- Show Genres Table
CREATE TABLE show_genres (
    show_id INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (show_id, genre_id),
    FOREIGN KEY (show_id) REFERENCES tv_shows(show_id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres(genre_id) ON DELETE CASCADE
);
