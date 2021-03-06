import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Lucija Filipovic
 * @author Mislav Rukonic
 * @author Sven Slivar
 * @author Matej Petric
 */
public class SavedSongs {

    private int userId;
    private int songId;
    private String songName;
    private JFrame frame = new JFrame();


    public SavedSongs(int userId, int songId) {
        this.userId = userId;
        this.songId = songId;
    }

    public SavedSongs() {
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }


    /**
     * selectAll method of the SavedSongs class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select saved songs where the ID value input will be bound
     * from the ArrayList, and save the result for that specific user.
     * Sets ID, name based on result data.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public ArrayList<ArrayList<String>> selectSavedSongs(User user) throws AlinityException {
        try {
            ArrayList<String> info = new ArrayList<>();
            info.add(String.valueOf(user.getUserId()));
            return AlinityMain.alinityDB.getData("SELECT Song.songName FROM User\n" +
                    "NATURAL JOIN Saved_Songs NATURAL JOIN Song\n" +
                    "WHERE User.userId = ?", info);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Song.songName FROM User\\n\" +\n" +
                    "                    \"NATURAL JOIN Saved_Songs NATURAL JOIN Song\\n\" +\n" +
                    "                    \"WHERE User.userId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Song.songName FROM User\\n\" +\n" +
                    "                    \"NATURAL JOIN Saved_Songs NATURAL JOIN Song\\n\" +\n" +
                    "                    \"WHERE User.userId = ?");
        }
    }

    /**
     * selectSavedSongsHandler of the SavedSongs class
     * Handles information retrieved from database
     * to show the user their saved songs.
     *
     * @param result
     * @param savedSongs
     */
    public void selectSavedSongsHandler(ArrayList<ArrayList<String>> result, SavedSongs savedSongs) {
        JPanel panelRoot = new JPanel();
        for (int i = 1; i < result.size(); i++) {
            ArrayList<String> savedSongData = result.get(i);
            setSongName(savedSongData.get(0));
            printSaved_Songs();
            JPanel panel = new JPanel();
            panel.add(new JLabel(savedSongs.getSongName()));
            panelRoot.add(panel);
        }
        frame.add(panelRoot);
        frame.setMinimumSize(new java.awt.Dimension(300, 300));
        frame.setVisible(true);
    }

    /**
     * insertAll method of the SavedSongs class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said award.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertAll(User user, Song song) throws AlinityException {
        try {
            ArrayList<String> info = new ArrayList<>();
            info.add(String.valueOf(user.getUserId()));
            info.add(String.valueOf(song.getSongId()));
            String insertStmt = "INSERT INTO Saved_Songs (userId, songId) VALUES (?, ?)";
            return AlinityMain.alinityDB.setData(insertStmt, info);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.", "INSERT INTO Saved_Songs (userId, songID) VALUES (?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.", "INSERT INTO Saved_Songs (userId, songID) VALUES (?, ?)");
        }
    }

    /**
     * deleteAll method of the SavedSongs class.
     * Deletes the given saved album data where the songId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAll(Song song) throws AlinityException {
        try {
            ArrayList<String> info = new ArrayList<>();
            info.add(String.valueOf(song.getSongId()));
            String deleteStmt = "DELETE FROM Saved_Songs WHERE songId = ?";
            return AlinityMain.alinityDB.setData(deleteStmt, info);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Saved_Songs WHERE songId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Saved_Songs WHERE songId = ?");
        }
    }

    /**
     * printSaved_Songs method of the SavedAlbums class.
     * Print values for the saved songs for that
     * specific user.
     */
    public void printSaved_Songs() {
        System.out.println("\nAlbum name: " + this.songName);
    }
}
