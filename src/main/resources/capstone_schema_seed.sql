USE tv_show_tracker;


-- Drop tables if they already exist (for reset convenience)
DROP TABLE IF EXISTS user_watch_history;
DROP TABLE IF EXISTS tv_shows;
DROP TABLE IF EXISTS users;

-- Users Table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);

-- TV Shows Table
CREATE TABLE tv_shows (
    show_id INT AUTO_INCREMENT PRIMARY KEY,
    show_name VARCHAR(255) NOT NULL,
    description TEXT
);

-- User Watch History Table
CREATE TABLE user_watch_history (
    history_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    show_id INT NOT NULL,
    status ENUM('Not Watched', 'Want to Watch', 'Currently Watching', 'Already Watched') NOT NULL,
    episodes_watched INT DEFAULT 0,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (show_id) REFERENCES tv_shows(show_id)
);

-- Seed 10 TV Shows
INSERT INTO tv_shows (show_name, description) VALUES
('Breaking Bad', 'A high school chemistry teacher turned methamphetamine producer.'),
('Stranger Things', 'Mystery and supernatural events unfold in a small town.'),
('Game of Thrones', 'Noble families vie for control of the Iron Throne.'),
('The Office', 'A mockumentary on a group of typical office workers.'),
('The Mandalorian', 'A bounty hunter travels through the Star Wars galaxy.'),
('Friends', 'Follows the personal and professional lives of six friends in NYC.'),
('The Witcher', 'A monster hunter struggles to find his place in a world of beasts and men.'),
('Severance', 'Employees at a biotech company undergo memory-severing surgery.'),
('Attack on Titan', 'Humans fight for survival against man-eating giants.'),
('Avatar: The Last Airbender', 'A young boy must master all four elements and save the world.');
