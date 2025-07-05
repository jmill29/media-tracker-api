package com.jmill29.tvtrackerapi.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Exception Constructors Test")
class ExceptionConstructorsTest {
    @Test
    @DisplayName("All custom exception constructors create non-null objects")
    void testAllExceptionConstructors() {
        // UserAlreadyExistsException
        assertNotNull(new UserAlreadyExistsException());
        assertNotNull(new UserAlreadyExistsException("msg"));
        assertNotNull(new UserAlreadyExistsException("msg", new RuntimeException()));
        assertNotNull(new UserAlreadyExistsException(new RuntimeException()));

        // UserNotFoundException
        assertNotNull(new UserNotFoundException());
        assertNotNull(new UserNotFoundException("msg"));
        assertNotNull(new UserNotFoundException("msg", new RuntimeException()));
        assertNotNull(new UserNotFoundException(new RuntimeException()));

        // ShowAlreadyExistsException
        assertNotNull(new ShowAlreadyExistsException());
        assertNotNull(new ShowAlreadyExistsException("msg"));
        assertNotNull(new ShowAlreadyExistsException("msg", new RuntimeException()));
        assertNotNull(new ShowAlreadyExistsException(new RuntimeException()));

        // ShowNotFoundException
        assertNotNull(new ShowNotFoundException());
        assertNotNull(new ShowNotFoundException("msg"));
        assertNotNull(new ShowNotFoundException("msg", new RuntimeException()));
        assertNotNull(new ShowNotFoundException(new RuntimeException()));

        // NoShowsFoundException
        assertNotNull(new NoShowsFoundException());
        assertNotNull(new NoShowsFoundException("msg"));
        assertNotNull(new NoShowsFoundException("msg", new RuntimeException()));
        assertNotNull(new NoShowsFoundException(new RuntimeException()));

        // WatchHistoryAlreadyExistsException
        assertNotNull(new WatchHistoryAlreadyExistsException());
        assertNotNull(new WatchHistoryAlreadyExistsException("msg"));
        assertNotNull(new WatchHistoryAlreadyExistsException("msg", new RuntimeException()));
        assertNotNull(new WatchHistoryAlreadyExistsException(new RuntimeException()));

        // WatchHistoryNotFoundException
        assertNotNull(new WatchHistoryNotFoundException());
        assertNotNull(new WatchHistoryNotFoundException("msg"));
        assertNotNull(new WatchHistoryNotFoundException("msg", new RuntimeException()));
        assertNotNull(new WatchHistoryNotFoundException(new RuntimeException()));

        // DatabaseException
        assertNotNull(new DatabaseException());
        assertNotNull(new DatabaseException("msg"));
        assertNotNull(new DatabaseException("msg", new RuntimeException()));
        assertNotNull(new DatabaseException(new RuntimeException()));
    }

    // User Already Exists exception
    @Test
    @DisplayName("UserAlreadyExistsException: default constructor")
    void userAlreadyExistsException_defaultConstructor() {
        UserAlreadyExistsException ex = new UserAlreadyExistsException();
        assertEquals("User already exists", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("UserAlreadyExistsException: message constructor")
    void userAlreadyExistsException_messageConstructor() {
        UserAlreadyExistsException ex = new UserAlreadyExistsException("msg");
        assertEquals("msg", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("UserAlreadyExistsException: cause constructor")
    void userAlreadyExistsException_causeConstructor() {
        Throwable cause = new RuntimeException("root");
        UserAlreadyExistsException ex = new UserAlreadyExistsException(cause);
        assertEquals(cause, ex.getCause());
    }

    @Test
    @DisplayName("UserAlreadyExistsException: message and cause constructor")
    void userAlreadyExistsException_messageAndCauseConstructor() {
        Throwable cause = new RuntimeException("root");
        UserAlreadyExistsException ex = new UserAlreadyExistsException("msg", cause);
        assertEquals("msg", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    // User Not Found exception
    @Test
    @DisplayName("UserNotFoundException: default constructor")
    void userNotFoundException_defaultConstructor() {
        UserNotFoundException ex = new UserNotFoundException();
        assertEquals("User not found", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("UserNotFoundException: message constructor")
    void userNotFoundException_messageConstructor() {
        UserNotFoundException ex = new UserNotFoundException("msg");
        assertEquals("msg", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("UserNotFoundException: cause constructor")
    void userNotFoundException_causeConstructor() {
        Throwable cause = new RuntimeException("root");
        UserNotFoundException ex = new UserNotFoundException(cause);
        assertEquals(cause, ex.getCause());
    }

    @Test
    @DisplayName("UserNotFoundException: message and cause constructor")
    void userNotFoundException_messageAndCauseConstructor() {
        Throwable cause = new RuntimeException("root");
        UserNotFoundException ex = new UserNotFoundException("msg", cause);
        assertEquals("msg", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    // ShowAlreadyExistsException
    @Test
    @DisplayName("ShowAlreadyExistsException: default constructor")
    void showAlreadyExistsException_defaultConstructor() {
        ShowAlreadyExistsException ex = new ShowAlreadyExistsException();
        assertEquals("Show already exists", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("ShowAlreadyExistsException: message constructor")
    void showAlreadyExistsException_messageConstructor() {
        ShowAlreadyExistsException ex = new ShowAlreadyExistsException("msg");
        assertEquals("msg", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("ShowAlreadyExistsException: cause constructor")
    void showAlreadyExistsException_causeConstructor() {
        Throwable cause = new RuntimeException("root");
        ShowAlreadyExistsException ex = new ShowAlreadyExistsException(cause);
        assertEquals(cause, ex.getCause());
    }

    @Test
    @DisplayName("ShowAlreadyExistsException: message and cause constructor")
    void showAlreadyExistsException_messageAndCauseConstructor() {
        Throwable cause = new RuntimeException("root");
        ShowAlreadyExistsException ex = new ShowAlreadyExistsException("msg", cause);
        assertEquals("msg", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    // ShowNotFoundException
    @Test
    @DisplayName("ShowNotFoundException: default constructor")
    void showNotFoundException_defaultConstructor() {
        ShowNotFoundException ex = new ShowNotFoundException();
        assertEquals("Show not found", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("ShowNotFoundException: message constructor")
    void showNotFoundException_messageConstructor() {
        ShowNotFoundException ex = new ShowNotFoundException("msg");
        assertEquals("msg", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("ShowNotFoundException: cause constructor")
    void showNotFoundException_causeConstructor() {
        Throwable cause = new RuntimeException("root");
        ShowNotFoundException ex = new ShowNotFoundException(cause);
        assertEquals(cause, ex.getCause());
    }

    @Test
    @DisplayName("ShowNotFoundException: message and cause constructor")
    void showNotFoundException_messageAndCauseConstructor() {
        Throwable cause = new RuntimeException("root");
        ShowNotFoundException ex = new ShowNotFoundException("msg", cause);
        assertEquals("msg", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    // NoShowsFoundException
    @Test
    @DisplayName("NoShowsFoundException: default constructor")
    void noShowsFoundException_defaultConstructor() {
        NoShowsFoundException ex = new NoShowsFoundException();
        assertEquals("No shows found", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("NoShowsFoundException: message constructor")
    void noShowsFoundException_messageConstructor() {
        NoShowsFoundException ex = new NoShowsFoundException("msg");
        assertEquals("msg", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("NoShowsFoundException: cause constructor")
    void noShowsFoundException_causeConstructor() {
        Throwable cause = new RuntimeException("root");
        NoShowsFoundException ex = new NoShowsFoundException(cause);
        assertEquals(cause, ex.getCause());
    }

    @Test
    @DisplayName("NoShowsFoundException: message and cause constructor")
    void noShowsFoundException_messageAndCauseConstructor() {
        Throwable cause = new RuntimeException("root");
        NoShowsFoundException ex = new NoShowsFoundException("msg", cause);
        assertEquals("msg", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    // WatchHistoryAlreadyExistsException
    @Test
    @DisplayName("WatchHistoryAlreadyExistsException: default constructor")
    void watchHistoryAlreadyExistsException_defaultConstructor() {
        WatchHistoryAlreadyExistsException ex = new WatchHistoryAlreadyExistsException();
        assertEquals("Watch history entry already exists for this user and show", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("WatchHistoryAlreadyExistsException: message constructor")
    void watchHistoryAlreadyExistsException_messageConstructor() {
        WatchHistoryAlreadyExistsException ex = new WatchHistoryAlreadyExistsException("msg");
        assertEquals("msg", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("WatchHistoryAlreadyExistsException: cause constructor")
    void watchHistoryAlreadyExistsException_causeConstructor() {
        Throwable cause = new RuntimeException("root");
        WatchHistoryAlreadyExistsException ex = new WatchHistoryAlreadyExistsException(cause);
        assertEquals(cause, ex.getCause());
    }

    @Test
    @DisplayName("WatchHistoryAlreadyExistsException: message and cause constructor")
    void watchHistoryAlreadyExistsException_messageAndCauseConstructor() {
        Throwable cause = new RuntimeException("root");
        WatchHistoryAlreadyExistsException ex = new WatchHistoryAlreadyExistsException("msg", cause);
        assertEquals("msg", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    // WatchHistoryNotFoundException
    @Test
    @DisplayName("WatchHistoryNotFoundException: default constructor")
    void watchHistoryNotFoundException_defaultConstructor() {
        WatchHistoryNotFoundException ex = new WatchHistoryNotFoundException();
        assertEquals("No watch history found for the specified user", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("WatchHistoryNotFoundException: message constructor")
    void watchHistoryNotFoundException_messageConstructor() {
        WatchHistoryNotFoundException ex = new WatchHistoryNotFoundException("msg");
        assertEquals("msg", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("WatchHistoryNotFoundException: cause constructor")
    void watchHistoryNotFoundException_causeConstructor() {
        Throwable cause = new RuntimeException("root");
        WatchHistoryNotFoundException ex = new WatchHistoryNotFoundException(cause);
        assertEquals(cause, ex.getCause());
    }

    @Test
    @DisplayName("WatchHistoryNotFoundException: message and cause constructor")
    void watchHistoryNotFoundException_messageAndCauseConstructor() {
        Throwable cause = new RuntimeException("root");
        WatchHistoryNotFoundException ex = new WatchHistoryNotFoundException("msg", cause);
        assertEquals("msg", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    // DatabaseException
    @Test
    @DisplayName("DatabaseException: default constructor")
    void databaseException_defaultConstructor() {
        DatabaseException ex = new DatabaseException();
        assertEquals("Database operation failed", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("DatabaseException: message constructor")
    void databaseException_messageConstructor() {
        DatabaseException ex = new DatabaseException("msg");
        assertEquals("msg", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("DatabaseException: cause constructor")
    void databaseException_causeConstructor() {
        Throwable cause = new RuntimeException("root");
        DatabaseException ex = new DatabaseException(cause);
        assertEquals(cause, ex.getCause());
    }

    @Test
    @DisplayName("DatabaseException: message and cause constructor")
    void databaseException_messageAndCauseConstructor() {
        Throwable cause = new RuntimeException("root");
        DatabaseException ex = new DatabaseException("msg", cause);
        assertEquals("msg", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    @Test
    @DisplayName("UserNotFoundException: inherited methods and serialization")
    void userNotFoundException_inheritedMethodsAndSerialization() throws Exception {
        testExceptionBehavior(new UserNotFoundException("msg", new RuntimeException("root")));
    }

    @Test
    @DisplayName("ShowAlreadyExistsException: inherited methods and serialization")
    void showAlreadyExistsException_inheritedMethodsAndSerialization() throws Exception {
        testExceptionBehavior(new ShowAlreadyExistsException("msg", new RuntimeException("root")));
    }

    @Test
    @DisplayName("ShowNotFoundException: inherited methods and serialization")
    void showNotFoundException_inheritedMethodsAndSerialization() throws Exception {
        testExceptionBehavior(new ShowNotFoundException("msg", new RuntimeException("root")));
    }

    @Test
    @DisplayName("NoShowsFoundException: inherited methods and serialization")
    void noShowsFoundException_inheritedMethodsAndSerialization() throws Exception {
        testExceptionBehavior(new NoShowsFoundException("msg", new RuntimeException("root")));
    }

    @Test
    @DisplayName("WatchHistoryAlreadyExistsException: inherited methods and serialization")
    void watchHistoryAlreadyExistsException_inheritedMethodsAndSerialization() throws Exception {
        testExceptionBehavior(new WatchHistoryAlreadyExistsException("msg", new RuntimeException("root")));
    }

    @Test
    @DisplayName("WatchHistoryNotFoundException: inherited methods and serialization")
    void watchHistoryNotFoundException_inheritedMethodsAndSerialization() throws Exception {
        testExceptionBehavior(new WatchHistoryNotFoundException("msg", new RuntimeException("root")));
    }

    @Test
    @DisplayName("DatabaseException: inherited methods and serialization")
    void databaseException_inheritedMethodsAndSerialization() throws Exception {
        testExceptionBehavior(new DatabaseException("msg", new RuntimeException("root")));
    }

    private void testExceptionBehavior(Exception ex) throws Exception {
        // toString
        assertNotNull(ex.toString());

        // getLocalizedMessage
        assertNotNull(ex.getLocalizedMessage());

        // equals & hashCode (reflexive)
        assertEquals(ex, ex);
        assertEquals(ex.hashCode(), ex.hashCode());

        // Serialization and deserialization
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        oos.writeObject(ex);
        oos.close();

        java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(baos.toByteArray());
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
        Object deserialized = ois.readObject();

        assertNotNull(deserialized);
        assertEquals(ex.getClass(), deserialized.getClass());
    }
}
