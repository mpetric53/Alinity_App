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
    public void selectAll(User user) throws AlinityException {
        try {
            ArrayList<String> info = new ArrayList<>();
            info.add(String.valueOf(user.getUserId()));
            ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT Album.albumName FROM User\n" +
                    "NATURAL JOIN Saved_Album Natural JOIN Album\n" +
                    "WHERE User.userId = ?" , info);
            System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> savedAlbumData = result.get(1);
            setAlbumName(savedAlbumData.get(0));
            printSaved_Albums();
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

//    /**
//     * updateAll method of the SavedAlbums class.
//     * A SQL statement as a String is created using the current albumId
//     * and userId where the userId is based on the values which will be bound
//     * from the input ArrayList of Strings.
//     * Executes using the setDada method.
//     * If the attempt of the executing the statement fails, log the error to the file.
//     *
//     * @throws AlinityException
//     */
//    public boolean updateAll(User user, Album album, int albumId) throws AlinityException {
//        try {
//            album.setAlbumId(albumId);
//            ArrayList<String> info = new ArrayList<>();
//            info.add(String.valueOf(album.getAlbumId()));
//            info.add(String.valueOf(user.getUserId()));
//            String putStmt = "UPDATE Saved_Album SET albumId = ? WHERE userId = ?";
//            return AlinityMain.alinityDB.setData(putStmt, info);
//        } catch (NullPointerException npe) {
//            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Saved_Album SET albumId = ? WHERE userId = ?");
//        } catch (IndexOutOfBoundsException ioobe) {
//            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Saved_Album SET albumId = ? WHERE userId = ?");
//        }
//    }

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