import java.util.ArrayList;

/**
 * @author Lucija Filipovic
 * @author Mislav Rukonic
 * @author Sven Slivar
 * @author Matej Petric
 */

public class AvailableOn {

    public int artistId;
    public int platformId;
    private String platformName;
    private String artistName;

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getPlatformId() {
        return platformId;
    }

    public AvailableOn() {

    }

    /**
     * selectAvailableOn method of the AvailableOn class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select platform names where the artist ID value input will be bound
     * from the ArrayList, and save the result.
     * Sets names via platform id.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public ArrayList<ArrayList<String>> selectAvailableOn(User user, int artistId) throws AlinityException {
        try {
            if (user.getRole().equals("General") || user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(artistId));
                return AlinityMain.alinityDB.getData("SELECT Platform.platformName\n" +
                        "FROM Platform NATURAL JOIN Available_On NATURAL JOIN\n" +
                        "Artist WHERE Artist.artistId = ?", info);
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
            return null;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Platform.platformName\\n\" +\n" +
                    "                        \"FROM Platform NATURAL JOIN Available_On NATURAL JOIN\\n\" +\n" +
                    "                        \"Artist WHERE Artist.artistId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Platform.platformName\\n\" +\n" +
                    "                        \"FROM Platform NATURAL JOIN Available_On NATURAL JOIN\\n\" +\n" +
                    "                        \"Artist WHERE Artist.artistId = ?");
        }
    }


    /**
     * updateAvailableOn method of the AvailableOn class.
     * A SQL statement as a String is created using the current artistId
     * and platformId where the artistId is based on the values which will be bound
     * from the input ArrayList of Strings.
     * Executes using the setDada method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateAvailableOn(User user, Artist artist, Platform platform) throws AlinityException {
        try {
            if (user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(platform.getPlatformId()));
                info.add(String.valueOf(artist.getArtistId()));
                String putStmt = "UPDATE Available_On SET platformId = ? WHERE artistId = ?";
                return AlinityMain.alinityDB.setData(putStmt, info);
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Available_On SET platformId = ? WHERE artistId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Available_On SET platformId = ? WHERE artistId = ?");
        }
    }

    /**
     * insertAvailableOn method of the AvailableOn class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said AvailableOn.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertAvailableOn(User user, Artist artist, Platform platform) throws AlinityException {
        try {
            if (user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(artist.getArtistId()));
                info.add(String.valueOf(platform.getPlatformId()));
                String insertStmt = "INSERT INTO Available_On (artistId, platformId) VALUES (?, ?)";
                return AlinityMain.alinityDB.setData(insertStmt, info);
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.", "INSERT INTO Available_On (artistId, platformId) VALUES (?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.", "INSERT INTO Available_On (artistId, platformId) VALUES (?, ?)");
        }
    }

    /**
     * deleteAvailableOn method of the AvailableOn class.
     * Deletes the given artist availability data where the artistId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when deleting ALL the information for said artist
     * id
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAvailableOn(User user, Artist artist) throws AlinityException {
        try {
            if (user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(artist.getArtistId()));
                String deleteStmt = "DELETE FROM Available_On WHERE artistId = ?";
                return AlinityMain.alinityDB.setData(deleteStmt, info);
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Available_On WHERE artistId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Available_On WHERE artistId = ?");
        }
    }

    /**
     * printAvailable_On method of the AvailableOn class.
     * Print values for the current platform and
     * artist names
     */
    public void printPlatform() {
        System.out.println("\nArtist Name: " + this.artistName);
        System.out.println("Platform Name: " + this.platformName);
    }

}
