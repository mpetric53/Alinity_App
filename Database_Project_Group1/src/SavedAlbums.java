import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Lucija Filipovic
 * @author Mislav Rukonic
 * @author Sven Slivar
 * @author Matej Petric
 */
public class SavedAlbums {

    public int userId;
    public int albumId;
    private String albumName;
    private JFrame frame = new JFrame();

    public SavedAlbums(int userId, int albumId) {
        this.userId = userId;
        this.albumId = albumId;
    }

    public SavedAlbums() {
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    /**
     * selectAll method of the SavedAlbums class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select saved Albums where the ID value input will be bound
     * from the ArrayList, and save the result for that specific user.
     * Sets ID, name based on result data.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public ArrayList<ArrayList<String>> selectSavedAlbums(User user) throws AlinityException {
        try {
            ArrayList<String> info = new ArrayList<>();
            info.add(String.valueOf(user.getUserId()));
            return AlinityMain.alinityDB.getData("SELECT Album.albumName FROM User\n" +
                    "NATURAL JOIN Saved_Album Natural JOIN Album\n" +
                    "WHERE User.userId = ?", info);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Album.albumName FROM User\n" +
                    "NATURAL JOIN Saved_Album Natural JOIN Album\n" +
                    "WHERE User.userId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Album.albumName FROM User\n" +
                    "NATURAL JOIN Saved_Album Natural JOIN Album\n" +
                    "WHERE User.userId = ?");
        }
    }

    /**
     *  selectSavedSongsHandler of the SavedAlbums class
     * Handles information retrieved from database
     * show the user their saved albums.
     * @param result
     * @param savedAlbums
     */
    public void selectSavedAlbumsHandler(ArrayList<ArrayList<String>> result, SavedAlbums savedAlbums) {
        JPanel rootPanel = new JPanel();
        for (int i = 1; i < result.size(); i++) {
            ArrayList<String> savedAlbumData = result.get(i);
            setAlbumName(savedAlbumData.get(0));
            printSaved_Albums();
            JPanel panel = new JPanel();
            panel.add(new JLabel(savedAlbums.getAlbumName()));
            rootPanel.add(panel);
        }
        frame.add(rootPanel);
        frame.setMinimumSize(new java.awt.Dimension(300, 300));
        frame.setVisible(true);
    }


    /**
     * insertAll method of the SavedAlbums class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said award.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertAll(User user, Album album) throws AlinityException {
        try {
            ArrayList<String> info = new ArrayList<>();
            info.add(String.valueOf(user.getUserId()));
            info.add(String.valueOf(album.getAlbumId()));
            String insertStmt = "INSERT INTO Saved_Album (userId, albumId) VALUES (?, ?)";
            return AlinityMain.alinityDB.setData(insertStmt, info);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.", "INSERT INTO Saved_Album (userId, albumId) VALUES (?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.", "INSERT INTO Saved_Album (userId, albumId) VALUES (?, ?)");
        }
    }

    /**
     * deleteAll method of the SavedAlbums class.
     * Deletes the given saved album data where the albumId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAll(Album album) throws AlinityException {
        try {
            ArrayList<String> info = new ArrayList<>();
            info.add(String.valueOf(album.getAlbumId()));
            String deleteStmt = "DELETE FROM Saved_Album WHERE albumId = ?";
            return AlinityMain.alinityDB.setData(deleteStmt, info);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Saved_Album WHERE albumId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Saved_Album WHERE albumId = ?");
        }
    }

    /**
     * printSaved_Albums method of the SavedAlbums class.
     * Print values for the saved albums for that
     * specific user.
     */
    public void printSaved_Albums() {
        System.out.println("\nAlbum name: " + this.albumName);
    }

}