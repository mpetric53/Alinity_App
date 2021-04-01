import java.util.ArrayList;

public class SavedArtists {
    public int userId;
    public int artistId;
    private String artistName;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public SavedArtists(int userId, int artistId) {
        this.userId = userId;
        this.artistId = artistId;
    }

    public SavedArtists() {

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
    public void selectAll(ArrayList<String> stringList) throws AlinityException {
        try {
            ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT Artist.artistName \n" +
                    "FROM ((Artist INNER JOIN Saved_Artist\n" +
                    "ON Artist.artistId = Saved_Artist.artistId)\n" +
                    "INNER JOIN User ON Saved_Artist.userId = User.userId)\n" +
                    "WHERE User.userId = ?" , stringList);
            System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> savedArtistData = result.get(1);
            setArtistName(savedArtistData.get(0));
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Artist.artistName \n" +
                    "FROM ((Artist INNER JOIN Saved_Artist\n" +
                    "ON Artist.artistId = Saved_Artist.artistId)\n" +
                    "INNER JOIN User ON Saved_Artist.userId = User.userId)\n" +
                    "WHERE User.userId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Artist.artistName \n" +
                    "FROM ((Artist INNER JOIN Saved_Artist\n" +
                    "ON Artist.artistId = Saved_Artist.artistId)\n" +
                    "INNER JOIN User ON Saved_Artist.userId = User.userId)\n" +
                    "WHERE User.userId = ?");
        }
    }

    /**
     * updateAll method of the SavedArtists class.
     * A SQL statement as a String is created using the current artistId
     * and userId where the userId is based on the values which will be bound
     * from the input ArrayList of Strings.
     * Executes using the setDada method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String putStmt = "UPDATE Saved_Artist SET userId = ?, artistId = ? WHERE awardId = ?";
            return AlinityMain.alinityDB.setData(putStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Saved_Artist SET userId = ?, artistId = ? WHERE awardId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Saved_Artist SET userId = ?, artistId = ? WHERE awardId = ?");
        }
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
    public boolean insertAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String insertStmt = "INSERT INTO Saved_Artist (userId, artistId) VALUES (?, ?)";
            return AlinityMain.alinityDB.setData(insertStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Saved_Artist (userId, artistId) VALUES (?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Saved_Artist (userId, artistId) VALUES (?, ?)");
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
    public boolean deleteAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String deleteStmt = "DELETE FROM Saved_Artist WHERE albumId = ?";
            return AlinityMain.alinityDB.setData(deleteStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Saved_Artist WHERE albumId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Saved_Artist WHERE albumId = ?");
        }
    }

    /**
     * printSaved_Albums method of the SavedAlbums class.
     * Print values for the saved albums for that
     * specific user.
     */
    public void printSaved_Albums() {
        System.out.println("\nAlbum name: " + this.artistName);
    }
}
