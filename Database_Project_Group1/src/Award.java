import java.util.ArrayList;

public class Award {

    private int awardId;
    private String awardName;
    private String awardInfo;
    private int artistId;

    public String getAwardInfo() {
        return awardInfo;
    }

    public void setAwardInfo(String awardInfo) {
        this.awardInfo = awardInfo;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public int getAwardId() {
        return awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public Award(String awardName, String awardInfo, int artistId) {
        this.awardName = awardName;
        this.awardInfo = awardInfo;
        this.artistId = artistId;
    }

    /**
     * selectAll method of the Award class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select Awards where the ID value input will be bound
     * from the ArrayList, and save the result.
     * Sets ID, name based on result data.
     * This method will specifically work only when selecting ALL the information for said award
     * id.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public void selectAll(ArrayList<String> stringList) throws AlinityException {
        try {
            ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM Award WHERE awardId = ?" , stringList);
            System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> awardData = result.get(1);
            setAwardId(Integer.parseInt(awardData.get(0)));
            setAwardName(awardData.get(1));
            setAwardInfo(awardData.get(2));
            setArtistId(Integer.parseInt(awardData.get(3)));
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Award WHERE awardId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Award WHERE awardId = ?");
        }
    }

    /**
     * updateAll method of the Award class.
     * A SQL statement as a String is created using the current name, info,
     * and artistId where the awardId is based on the values which will be bound
     * from the input ArrayList of Strings.
     * Executes using the setDada method.
     * This method will specifically work only when updating ALL the information for said award
     * id.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String putStmt = "UPDATE Award SET awardName = ? , awardInfo = ?, artistId = ? WHERE awardId = ?";
            return AlinityMain.alinityDB.setData(putStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Award SET awardName = ? , awardInfo = ?, artistId = ? WHERE awardId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Award SET awardName = ? , awardInfo = ?, artistId = ? WHERE awardId = ?");
        }
    }

    /**
     * insertAll method of the Award class.
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
            String insertStmt = "INSERT INTO Award (awardName, awardInfo, artistId) VALUES (?, ?, ?)";
            return AlinityMain.alinityDB.setData(insertStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Award (awardName, awardInfo, artistId) VALUES (?, ?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Award (awardName, awardInfo, artistId) VALUES (?, ?, ?)");
        }
    }

    /**
     * deleteAll method of the Award class.
     * Deletes the given award data where the awardId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when deleting ALL the information for said award
     * id
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String deleteStmt = "DELETE FROM Award WHERE awardId = ?";
            return AlinityMain.alinityDB.setData(deleteStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Award WHERE awardId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Award WHERE awardId = ?");
        }
    }

    /**
     * printAward method of the Award class.
     * Print values for the current awardId,
     * awardName, information about the award,
     * and the id of the artist (will be changed
     * later to represent the artist itself).
     */
    public void printAward() {
        System.out.println("\nID: " + this.awardId);
        System.out.println("Name: " + this.awardName);
        System.out.println("Info: " + this.awardInfo);
        System.out.println("Artist: " + this.artistId);
    }
}
