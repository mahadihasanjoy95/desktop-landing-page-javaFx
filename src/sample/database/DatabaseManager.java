package sample.database;

import sample.data.model.Bookmarks;
import sample.database.dbInterface.BookmarksCrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseManager implements BookmarksCrud {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/super_app_test";
    private static final String DB_USERNAME = "test";
    private static final String DB_PASSWORD = "test";
    private static final String CREATE_TABLE = "CREATE TABLE BOOKMARKS "
            + "(bookmarkId bigint auto_increment NOT NULL PRIMARY KEY, "
            + " userId VARCHAR(255), "
            + " applicationId INTEGER)";
    private Connection connection;
    private Statement stmt;

    public DatabaseManager() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = connection.createStatement();
        } catch (Exception ex) {
            System.err.println("Database Error >>> " + ex);
        }
    }

    public void createTable() {
        try {
            stmt.execute(CREATE_TABLE);
        } catch (Exception ex) {
            System.err.println("Database Error >>> " + ex);
        }
    }

    @Override
    public void addBookmarks(Bookmarks bookmarks) {
        /**
         * TODO: Before adding new bookmarks have to check that already added in database or not
         */
        try {
            String sql = "INSERT INTO BOOKMARKS (userId, applicationId) VALUES ('" + bookmarks.getUserId() + "',"
                    + " '" + bookmarks.getId() + "')";
            stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteBookmarks(String userId, Long applicationId) {
        try {
            String sql = "DELETE FROM BOOKMARKS  where userId =  '" + userId + "' AND applicationId = '" + applicationId + "'";
            stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Bookmarks> getAllBookmarks() {
        ArrayList<Bookmarks> bookmarksArrayList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM BOOKMARKS";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Bookmarks bookmarks = new Bookmarks();
                bookmarks.setUserId(rs.getString("userId"));
                bookmarks.setId(rs.getLong("applicationId"));

                bookmarksArrayList.add(bookmarks);
            }
            return bookmarksArrayList;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookmarksArrayList;
    }

    @Override
    public ArrayList<Bookmarks> getUserWiseBookmarks(String userId) {
        ArrayList<Bookmarks> bookmarksArrayList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM BOOKMARKS where userId =  '" + userId + "'";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Bookmarks bookmarks = new Bookmarks();
                bookmarks.setUserId(rs.getString("userId"));
                bookmarks.setId(rs.getLong("applicationId"));

                bookmarksArrayList.add(bookmarks);
            }
            return bookmarksArrayList;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookmarksArrayList;
    }
}
