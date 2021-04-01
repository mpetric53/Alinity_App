import java.util.ArrayList;

public class SavedAlbums {

    public int userId;
    public int albumId;
    private String albumName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public SavedAlbums(int userId, int albumId) {
        this.userId = userId;
        this.albumId = albumId;
    }

    public SavedAlbums() {}

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
    public void selectAll(ArrayList<String> stringList) throws AlinityException {
        try {
            ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT Album.albumName \n" +
                    "FROM ((Album INNER JOIN Saved_Album\n" +
                    "ON Album.albumId = Saved_Album.albumId)\n" +
                    "INNER JOIN User ON Saved_Album.userId = User.userId)\n" +
                    "WHERE User.userId = ?" , stringList);
            System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> savedAlbumData = result.get(1);
            setAlbumName(savedAlbumData.get(0));
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Album.albumName \n" +
                    "FROM ((Album INNER JOIN Saved_Album\n" +
                    "ON Album.albumId = Saved_Album.albumId)\n" +
                    "INNER JOIN User ON Saved_Album.userId = User.userId)\n" +
                    "WHERE User.userId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Album.albumName \n" +
                    "FROM ((Album INNER JOIN Saved_Album\n" +
                    "ON Album.albumId = Saved_Album.albumId)\n" +
                    "INNER JOIN User ON Saved_Album.userId = User.userId)\n" +
                    "WHERE User.userId = ?");
        }
    }

    /**
     * updateAll method of the SavedAlbums class.
     * A SQL statement as a String is created using the current albumId
     * and userId where the userId is based on the values which will be bound
     * from the input ArrayList of Strings.
     * Executes using the setDada method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String putStmt = "UPDATE Saved_Album SET userId = ?, albumId = ? WHERE awardId = ?";
            return AlinityMain.alinityDB.setData(putStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Saved_Album SET userId = ?, albumId = ? WHERE awardId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Saved_Album SET userId = ?, albumId = ? WHERE awardId = ?");
        }
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
    public boolean insertAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String insertStmt = "INSERT INTO Saved_Album (userId, albumId) VALUES (?, ?)";
            return AlinityMain.alinityDB.setData(insertStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Saved_Album (userId, albumId) VALUES (?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Saved_Album (userId, albumId) VALUES (?, ?)");
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
    public boolean deleteAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String deleteStmt = "DELETE FROM Saved_Album WHERE albumId = ?";
            return AlinityMain.alinityDB.setData(deleteStmt, stringList);
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