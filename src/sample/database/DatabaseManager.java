package sample.database;

import sample.data.model.Bookmarks;
import sample.database.dbInterface.BookmarksCrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseManager implements BookmarksCrud {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/superapp_test1";
    private static final String DB_USERNAME = "test";
    private static final String DB_PASSWORD = "test";
    private Connection connection;
    private Statement stmt;

    private static final String CREATE_TABLE = "CREATE TABLE BOOKMARKS "
            + "(bookmarkId bigint auto_increment NOT NULL PRIMARY KEY, "
            + " userId VARCHAR(255), "
            + " applicationId INTEGER)";

    public DatabaseManager() {
        openConnection();
    }

    public void openConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = connection.createStatement();
            stmt.execute(CREATE_TABLE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addBookmarks(Bookmarks bookmarks) {
        /**
         * TODO: Before adding new bookmarks have to check that already added in database or not
         */
//        int size = 0;
//        try {
//            String sql = "SELECT * FROM BOOKMARKS WHERE userId = '" + bookmarks.getUserId() + "' AND applicationId = " + bookmarks.getApplicationId() + "";
//            ResultSet rs = stmt.executeQuery(sql);
//            size = rs.getRow();
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (size == 0)
//        {
            try {
                String sql = "INSERT INTO BOOKMARKS (userId, applicationId) VALUES ('" + bookmarks.getUserId() + "',"
                        + " '" + bookmarks.getId() + "')";
                stmt.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
//        }
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
        ArrayList<Bookmarks> bookmarksArrayList  = new ArrayList<>();

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
        ArrayList<Bookmarks> bookmarksArrayList  = new ArrayList<>();

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
