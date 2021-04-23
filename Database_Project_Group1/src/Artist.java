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

    public Artist() {

    }

    /**
     * selectArtist method of the Artist class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select Albums where the name of the artist input will be bound
     * from the ArrayList, and save the result.
     * Sets ID, name, info, recordLabelId based on result data.
     * This method will specifically work only when selecting ALL the information for said Artist
     * name.
     * Both admins and general users will have access to selecting artists from search.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean selectArtist(User user, String artistName) throws AlinityException {
        try {
            if(user.getRole().equals("General") || user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(artistName);
                ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM Artist WHERE artistName = ?" , info);
                System.out.print("\nColumn headers: " + result.get(0));
                ArrayList<String> artistData = result.get(1);
                setArtistId(Integer.parseInt(artistData.get(0)));
                setArtistName(artistData.get(1));
                setArtistInfo(artistData.get(2));
                setRecordLabelId(Integer.parseInt(artistData.get(3)));
                if(getArtistName() == null || getArtistName().equals("")) {
                    System.out.println("Not what you what you were looking for, continuing...");
                    return false;
                }
                printArtist();
            } else System.out.println("You do not have the correct permissions to use this function. Please contact an administrator."); return false;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Artist WHERE artistName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Artist WHERE artistName = ?");
        }
    }

    public boolean selectArtist(User user, int artistId) throws AlinityException {
        try {
            if(user.getRole().equals("General") || user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(artistId));
                ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM Artist WHERE artistId = ?" , info);
                System.out.print("\nColumn headers: " + result.get(0));
                ArrayList<String> artistData = result.get(1);
                setArtistId(Integer.parseInt(artistData.get(0)));
                setArtistName(artistData.get(1));
                setArtistInfo(artistData.get(2));
                setRecordLabelId(Integer.parseInt(artistData.get(3)));
                if(getArtistName() == null || getArtistName().equals("")) {
                    System.out.println("Not what you what you were looking for, continuing...");
                    return false;
                }
                printArtist();
            } else System.out.println("You do not have the correct permissions to use this function. Please contact an administrator."); return false;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Artist WHERE artistName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Artist WHERE artistName = ?");
        }
    }

    /**
     * updateArtist method of the Artist class.
     * A SQL statement as a String is created using the current name, info,
     * and recordLabelId. They are bound to the statement using an ArrayList of Strings.
     * The artistId where these changes will take place is taken from the object
     * on which this method will be called on.
     * Executes using the setDada method.
     * This method will specifically work only when updating ALL the information for said artist
     * id.
     * Only works for admins, since users with the General role should not be allowed to update information.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateArtist(User user, String artistName, String artistInfo, RecordLabel recordLabel) throws AlinityException {
        try {
            if(user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(artistName);
                info.add(artistInfo);
                info.add(String.valueOf(recordLabel.getRecordLabelId()));
                info.add(String.valueOf(this.getArtistId()));
                String putStmt = "UPDATE Artist SET artistName = ? , artistInfo = ?, recordLabelId = ? WHERE artistId = ?";
                return AlinityMain.alinityDB.setData(putStmt, info);
            } else System.out.println("You do not have the correct permissions to use this function. Please contact an administrator."); return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Artist SET artistName = ? , artistInfo = ?, recordLabelId = ? WHERE artistId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Artist SET artistName = ? , artistInfo = ?, recordLabelId = ? WHERE artistId = ?");
        }
    }

    /**
     * insertArtist method of the Artist class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said artist.
     * Only admins have the permissions to do this. General users should not be allowed to insert
     * new information to the database.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertArtist(User user, String artistName, String artistInfo, RecordLabel recordLabel) throws AlinityException {
        try {
            if(user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(artistName);
                info.add(artistInfo);
                info.add(String.valueOf(recordLabel.getRecordLabelId()));
                String insertStmt = "INSERT INTO Artist (artistName, artistInfo, recordLabelId) VALUES (?, ?, ?)";
                return AlinityMain.alinityDB.setData(insertStmt, info);
            } else System.out.println("You do not have the correct permissions to use this function. Please contact an administrator."); return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Artist (artistName, artistInfo, recordLabelId) VALUES (?, ?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Artist (artistName, artistInfo, recordLabelId) VALUES (?, ?, ?)");
        }
    }

    /**
     * deleteArtist method of the Artist class.
     * Deletes the given artist data where the artistId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when deleting ALL the information for said artist
     * id
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteArtist(User user) throws AlinityException {
        try {
            if(user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(this.getArtistId()));
                String deleteStmt = "DELETE FROM Artist WHERE artistId = ?";
                return AlinityMain.alinityDB.setData(deleteStmt, info);
            } else System.out.println("You do not have the correct permissions to use this function. Please contact an administrator."); return false;
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
        System.out.println("Record Label: " + this.recordLabelId);
    }

}
