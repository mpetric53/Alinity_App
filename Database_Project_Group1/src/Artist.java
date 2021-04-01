import java.sql.Date;
import java.util.ArrayList;

public class Artist {

    private int artistId;
    private String artistName;
    private String artistInfo;
    private int recordLabelId;

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistInfo() {
        return artistInfo;
    }

    public void setArtistInfo(String artistInfo) {
        this.artistInfo = artistInfo;
    }

    public int getRecordLabelId() {
        return recordLabelId;
    }

    public void setRecordLabelId(int recordLabelId) {
        this.recordLabelId = recordLabelId;
    }

    public Artist(String artistName, String artistInfo, int recordLabelId) {
        this.artistName = artistName;
        this.artistInfo = artistInfo;
        this.recordLabelId = recordLabelId;
    }

    /**
     * selectAll method of the Artist class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select Albums where the ID value input will be bound
     * from the ArrayList, and save the result.
     * Sets ID, name, info, recordLabelId based on result data.
     * This method will specifically work only when selecting ALL the information for said Artist
     * id.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public void selectAll(ArrayList<String> stringList) throws AlinityException {
        try {
            ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM Artist WHERE artistId = ?" , stringList);
            System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> artistData = result.get(1);
            setArtistId(Integer.parseInt(artistData.get(0)));
            setArtistName(artistData.get(1));
            setArtistInfo(artistData.get(2));
            setRecordLabelId(Integer.parseInt(artistData.get(3)));
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Artist WHERE artistId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Artist WHERE artistId = ?");
        }
    }

    /**
     * updateAll method of the Artist class.
     * A SQL statement as a String is created using the current name, info,
     * and recordLabelId where the artistId is based on the values which will be bound
     * from the input ArrayList of Strings.
     * Executes using the setDada method.
     * This method will specifically work only when updating ALL the information for said artist
     * id.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String putStmt = "UPDATE Artist SET artistName = ? , artistInfo = ?, recordLabelId = ? WHERE artistId = ?";
            return AlinityMain.alinityDB.setData(putStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Artist SET artistName = ? , artistInfo = ?, recordLabelId = ? WHERE artistId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Artist SET artistName = ? , artistInfo = ?, recordLabelId = ? WHERE artistId = ?");
        }
    }

    /**
     * insertAll method of the Artist class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said artist.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String insertStmt = "INSERT INTO Artist (artistName, artistInfo, recordLabelId) VALUES (?, ?, ?)";
            return AlinityMain.alinityDB.setData(insertStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Artist (artistName, artistInfo, recordLabelId) VALUES (?, ?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Artist (artistName, artistInfo, recordLabelId) VALUES (?, ?, ?)");
        }
    }

    /**
     * deleteAll method of the Artist class.
     * Deletes the given artist data where the artistId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when deleting ALL the information for said artist
     * id
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String deleteStmt = "DELETE FROM Artist WHERE artistId = ?";
            return AlinityMain.alinityDB.setData(deleteStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Artist WHERE artistId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Artist WHERE artistId = ?");
        }
    }

    /**
     * printArtist method of the Artist class.
     * Print values for the current artistId,
     * artistName, artist information, and recordLabelId
     * (will be changed
     * later to represent the artist itself).
     */
    public void printArtist() {
        System.out.println("\nID: " + this.artistId);
        System.out.println("Name: " + this.artistName);
        System.out.println("Info: " + this.artistInfo);
        System.out.println("Release Date: " + this.recordLabelId);
    }

}
