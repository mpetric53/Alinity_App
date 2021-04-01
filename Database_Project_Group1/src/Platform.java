import java.util.*;

public class Platform {

    private int platformId;
    private String platformName;
    private String platformInfo;

    public Platform(int platformId, String platformName, String platformInfo) {
        this.platformId = platformId;
        this.platformName = platformName;
        this.platformInfo = platformInfo;
    }

    public Platform() {
    }


    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformInfo() {
        return platformInfo;
    }

    public void setPlatformInfo(String platformInfo) {
        this.platformInfo = platformInfo;
    }

    /**
     * selectAll method of the Platform class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select Platforms where the ID value input will be bound
     * from the ArrayList, and save the result.
     * Sets ID, name, info
     * This method will specifically work only when selecting ALL the information for said Platform
     * id.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public void selectAll(ArrayList<String> stringList) throws AlinityException {
        try {
            ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM Platform WHERE platformId = ?" , stringList);
            System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> platformData = result.get(1);
            setPlatformId(Integer.parseInt(platformData.get(0)));
            setPlatformName(platformData.get(1));
            setPlatformInfo(platformData.get(2));
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Platform WHERE platformId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Platform WHERE platformId = ?");
        }
    }


    /**
     * updateAll method of the Platform class.
     * A SQL statement as a String is created using the current name, info where the platformId is based on the values which will be bound
     * from the input ArrayList of Strings.
     * Executes using the setDada method.
     * This method will specifically work only when updating ALL the information for said platformId
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String putStmt = "UPDATE Platform SET platformName = ? , platformInfo = ? WHERE platformId = ?";
            return AlinityMain.alinityDB.setData(putStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Platform SET platformName = ? , platformInfo = ? WHERE platformId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Platform SET platformName = ? , platformInfo = ? WHERE platformId = ?");
        }
    }


    /**
     * insertAll method of the Platform class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said platform.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String insertStmt = "INSERT INTO Platform (platformName, platformInfo) VALUES (?, ?)";
            return AlinityMain.alinityDB.setData(insertStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Platform (platformName, platformInfo) VALUES (?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Platform (platformName, platformInfo) VALUES (?, ?)");
        }
    }

    /**
     * deleteAll method of the Platform class.
     * Deletes the given Platform data where the platformId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when deleting ALL the information for said platformId
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String deleteStmt = "DELETE FROM Platform WHERE platformId = ?";
            return AlinityMain.alinityDB.setData(deleteStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Platform WHERE platformId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Platform WHERE platformId = ?");
        }
    }

    /**
     * printPlatform method of the Platform class.
     * Print values for the current platformId,
     * platformName, platformInfo
     */

    public void printPlatform() {
        System.out.println("\nID: " + this.platformId);
        System.out.println("Platform name: " + this.platformName);
        System.out.println("Platform Info: " + this.platformInfo);
    }


}
