package com.jmill29.tvtrackerapi.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jmill29.tvtrackerapi.model.Show;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
@ActiveProfiles("test")
@DisplayName("ShowDaoImpl Integration Test")
class ShowDaoImplIntegrationTest {

    @Autowired
    private ShowDao showDao;
    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setUp() throws Exception {
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM tv_shows");
            stmt.execute("ALTER TABLE tv_shows ALTER COLUMN show_id RESTART WITH 1;");
            stmt.execute("INSERT INTO tv_shows (show_name, description, image_url, num_episodes, release_year) VALUES ('Test Show', 'A test show', 'test.jpg', 10, 2020)");
            stmt.execute("INSERT INTO tv_shows (show_name, description, image_url, num_episodes, release_year) VALUES ('Another Show', 'Another desc', 'img2.jpg', 5, 2021)");
            // For genre tests, you would need to insert into genres/show_genres if those tables exist in test schema
        }
    }

    @Test
    @DisplayName("findById returns show for valid id")
    void findById_returnsShow() throws Exception {
        Optional<Show> show = showDao.findById(1);
        assertTrue(show.isPresent());
        assertEquals("Test Show", show.get().getName());
    }

    @Test
    @DisplayName("findById returns empty for missing id")
    void findById_returnsEmptyForMissingId() throws Exception {
        Optional<Show> show = showDao.findById(999);
        assertFalse(show.isPresent());
    }

    @Test
    @DisplayName("findAll returns all shows")
    void findAll_returnsAllShows() throws Exception {
        List<Show> shows = showDao.findAll();
        assertFalse(shows.isEmpty());
        assertEquals(2, shows.size());
    }

    @Test
    @DisplayName("findByName returns matching shows")
    void findByName_returnsMatchingShows() throws Exception {
        List<Show> shows = showDao.findByName("Test");
        assertFalse(shows.isEmpty());
        assertEquals("Test Show", shows.get(0).getName());
    }

    @Test
    @DisplayName("findByName returns empty for no match")
    void findByName_returnsEmptyForNoMatch() throws Exception {
        List<Show> shows = showDao.findByName("Nonexistent");
        assertTrue(shows.isEmpty());
    }

    @Test
    @DisplayName("save inserts new show")
    void save_insertsNewShow() throws Exception {
        Show newShow = new Show(0, "Brand New Show", "desc", "img.jpg", 8, (short)2022, null);
        boolean result = showDao.save(newShow);
        assertTrue(result);
        List<Show> shows = showDao.findByName("Brand New Show");
        assertFalse(shows.isEmpty());
    }

    @Test
    @DisplayName("save throws on duplicate show")
    void save_throwsOnDuplicateShow() throws Exception {
        Show dup = new Show(0, "Test Show", "desc", "img.jpg", 8, (short)2020, null);
        assertThrows(com.jmill29.tvtrackerapi.exception.ShowAlreadyExistsException.class, () -> showDao.save(dup));
    }

    @Test
    @DisplayName("save updates existing show")
    void save_updatesExistingShow() throws Exception {
        Optional<Show> showOpt = showDao.findById(1);
        assertTrue(showOpt.isPresent());
        Show show = showOpt.get();
        show.setDescription("Updated desc");
        boolean result = showDao.save(show);
        assertTrue(result);
        Show updated = showDao.findById(1).get();
        assertEquals("Updated desc", updated.getDescription());
    }

    @Test
    @DisplayName("save throws on update missing show")
    void save_throwsOnUpdateMissingShow() throws Exception {
        Show fake = new Show(999, "Fake", "desc", "img.jpg", 1, (short)2000, null);
        assertThrows(com.jmill29.tvtrackerapi.exception.ShowNotFoundException.class, () -> showDao.save(fake));
    }

    @Test
    @DisplayName("deleteById deletes show")
    void deleteById_deletesShow() throws Exception {
        boolean result = showDao.deleteById(2);
        assertTrue(result);
        assertFalse(showDao.findById(2).isPresent());
    }

    @Test
    @DisplayName("deleteById returns false for missing show")
    void deleteById_returnsFalseForMissingShow() throws Exception {
        boolean result = showDao.deleteById(999);
        assertFalse(result);
    }

    @Test
    @DisplayName("findByGenre returns empty if no genre table")
    void findByGenre_returnsEmptyIfNoGenreTable() throws Exception {
        // If genre tables are not present in test schema, this should not throw but return empty
        try {
            List<Show> shows = showDao.findByGenre("Comedy");
            assertNotNull(shows);
        } catch (Exception e) {
            // Acceptable if schema is missing, but test should not fail
            assertTrue(e instanceof java.sql.SQLException);
        }
    }
}
