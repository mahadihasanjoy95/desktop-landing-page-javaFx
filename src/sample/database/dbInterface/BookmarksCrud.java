package sample.database.dbInterface;

import sample.data.model.Bookmarks;

import java.util.ArrayList;

public interface BookmarksCrud {
    public void addBookmarks(Bookmarks bookmarks);

    public void deleteBookmarks(String userId, Long applicationId);

    public ArrayList<Bookmarks> getAllBookmarks();

   public ArrayList<Bookmarks> getUserWiseBookmarks(String userId);
}
