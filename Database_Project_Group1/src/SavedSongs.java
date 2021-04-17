import java.util.ArrayList;

public class SavedSongs {

    private int userId;
    private int songId;
    private String songName;


    public SavedSongs(int userId, int songId) {
        this.userId = userId;
        this.songId = songId;
    }

    public SavedSongs() {}

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
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
    public void selectAll(User user) throws AlinityException {
        try {
            ArrayList<String> info = new ArrayList<>();
            info.add(String.valueOf(user.getUserId()));
            ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT Song.songName FROM User\n" +
                    "NATURAL JOIN Saved_Songs NATURAL JOIN Song\n" +
                    "WHERE User.userId = ?" , info);
            System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> savedSongData = result.get(1);
            setSongName(savedSongData.get(0));
            printSaved_Songs();
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

//    /**
//     * updateAll method of the SavedSongs class.
//     * A SQL statement as a String is created using the current songId
//     * and userId where the userId is based on the values which will be bound
//     * from the input ArrayList of Strings.
//     * Executes using the setDada method.
//     * If the attempt of the executing the statement fails, log the error to the file.
//     *
//     * @throws AlinityException
//     */
//    public boolean updateAll(ArrayList<String> stringList) throws AlinityException {
//        try {
//            String putStmt = "UPDATE Saved_Songs SET userId = ?, songId = ? WHERE awardId = ?";
//            return AlinityMain.alinityDB.setData(putStmt, stringList);
//        } catch (NullPointerException npe) {
//            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Saved_Songs SET userId = ?, songId = ? WHERE awardId = ?");
//        } catch (IndexOutOfBoundsException ioobe) {
//            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Saved_Songs SET userId = ?, songId = ? WHERE awardId = ?");
//        }
//    }

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
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Saved_Songs (userId, songID) VALUES (?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Saved_Songs (userId, songID) VALUES (?, ?)");
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
