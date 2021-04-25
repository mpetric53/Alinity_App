import javax.swing.*;
import java.awt.image.Kernel;
import java.util.ArrayList;

/**
 * @author Lucija Filipovic
 * @author Mislav Rukonic
 * @author Sven Slivar
 * @author Matej Petric
 */
public class SavedArtists {
    private int userId;
    private int artistId;
    private String artistName;
    private JFrame frame = new JFrame();

    public SavedArtists(int userId, int artistId) {
        this.userId = userId;
        this.artistId = artistId;
    }

    public SavedArtists() {

    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }


    /**
     * selectAll method of the SavedArtists class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select saved Artists where the ID value input will be bound
     * from the ArrayList, and save the result for that specific user.
     * Sets ID, name based on result data.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public ArrayList<ArrayList<String>> selectSavedArtists(User user) throws AlinityException {
        try {
            ArrayList<String> info = new ArrayList<>();
            info.add(String.valueOf(user.getUserId()));
            return AlinityMain.alinityDB.getData("SELECT Artist.artistName FROM User\n" +
                    "NATURAL JOIN Saved_Artist NATURAL JOIN Artist\n" +
                    "WHERE User.userId = ?", info);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Artist.artistName FROM User\\n\" +\n" +
                    "                    \"NATURAL JOIN Saved_Artist NATURAL JOIN Artist\\n\" +\n" +
                    "                    \"WHERE User.userId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Artist.artistName FROM User\\n\" +\n" +
                    "                    \"NATURAL JOIN Saved_Artist NATURAL JOIN Artist\\n\" +\n" +
                    "                    \"WHERE User.userId = ?");
        }
    }

    /**
     * selectSavedSongsHandler of the SavedArtists class
     * Handles information retrieved from database
     * to show the user their saved artists.
     * @param result
     * @param savedArtists
     */
    public void selectSavedArtistsHandler(ArrayList<ArrayList<String>> result, SavedArtists savedArtists) {
        JPanel rootPanel = new JPanel();
        for (int i = 1; i < result.size(); i++) {
            ArrayList<String> savedArtistData = result.get(i);
            setArtistName(savedArtistData.get(0));
            printSaved_Artists();
            JPanel panel = new JPanel();
            panel.add(new JLabel(savedArtists.getArtistName()));
            rootPanel.add(panel);
        }
        frame.add(rootPanel);
        frame.setMinimumSize(new java.awt.Dimension(300, 300));
        frame.setVisible(true);
    }


    /**
     * insertAll method of the SavedArtists class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said award.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertAll(User user, Artist artist) throws AlinityException {
        try {
            ArrayList<String> info = new ArrayList<>();
            info.add(String.valueOf(user.getUserId()));
            info.add(String.valueOf(artist.getArtistId()));
            String insertStmt = "INSERT INTO Saved_Artist (userId, artistId) VALUES (?, ?)";
            return AlinityMain.alinityDB.setData(insertStmt, info);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.", "INSERT INTO Saved_Artist (userId, artistId) VALUES (?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.", "INSERT INTO Saved_Artist (userId, artistId) VALUES (?, ?)");
        }
    }

    /**
     * deleteAll method of the SavedArtists class.
     * Deletes the given saved album data where the artistId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAll(Artist artist) throws AlinityException {
        try {
            ArrayList<String> info = new ArrayList<>();
            info.add(String.valueOf(artist.getArtistId()));
            String deleteStmt = "DELETE FROM Saved_Artist WHERE albumId = ?";
            return AlinityMain.alinityDB.setData(deleteStmt, info);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Saved_Artist WHERE albumId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Saved_Artist WHERE albumId = ?");
        }
    }

    /**
     * printSaved_Artists method of the SavedAlbums class.
     * Print values for the saved artists for that
     * specific user.
     */
    public void printSaved_Artists() {
        System.out.println("\nAlbum name: " + this.artistName);
    }
}
