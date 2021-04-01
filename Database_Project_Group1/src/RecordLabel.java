import java.sql.Date;
import java.util.*;
import java.sql.*;
public class RecordLabel {

    private int recordLabelId;
    private String recordLabelName;
    private Date recordDateCreated;
    private String labelInfo;

    public RecordLabel() {
    }

    public RecordLabel(int recordLabelId, String recordLabelName, Date recordDateCreated, String labelInfo) {
        this.recordLabelId = recordLabelId;
        this.recordLabelName = recordLabelName;
        this.recordDateCreated = recordDateCreated;
        this.labelInfo = labelInfo;
    }

    public int getRecordLabelId() {
        return recordLabelId;
    }

    public void setRecordLabelId(int recordLabelId) {
        this.recordLabelId = recordLabelId;
    }

    public String getRecordLabelName() {
        return recordLabelName;
    }

    public void setRecordLabelName(String recordLabelName) {
        this.recordLabelName = recordLabelName;
    }

    public Date getRecordDateCreated() {
        return recordDateCreated;
    }

    public void setRecordDateCreated(Date recordDateCreated) {
        this.recordDateCreated = recordDateCreated;
    }

    public String getLabelInfo() {
        return labelInfo;
    }

    public void setLabelInfo(String labelInfo) {
        this.labelInfo = labelInfo;
    }

    /**
     * selectAll method of the RecordLabel class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select RecordLabels where the ID value input will be bound
     * from the ArrayList, and save the result.
     * Sets RecordLabelID, RecordLabelName, recordDateCreated, labelInfo
     * This method will specifically work only when selecting ALL the information for said Record Label
     * id.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public void selectAll(ArrayList<String> stringList) throws AlinityException {
        try {
            ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM Record_Label WHERE recordLabelId = ?" , stringList);
            System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> RecordLabelData = result.get(1);
            setRecordLabelId(Integer.parseInt(RecordLabelData.get(0)));
            setRecordLabelName(RecordLabelData.get(1));
            setRecordDateCreated(Date.valueOf(RecordLabelData.get(2)));
            setLabelInfo(RecordLabelData.get(3));

        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Album WHERE awardId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Album WHERE awardId = ?");
        }
    }

    /**
     * updateAll method of the RecordLabel class.
     * A SQL statement as a String is created using the current recordLabelName, dateCreated,
     * labelInfo and recordLabelId where the recordLabelId is based on the values which will be bound
     * from the input ArrayList of Strings.
     * Executes using the setDada method.
     * This method will specifically work only when updating ALL the information for said RecordLabel
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String putStmt = "UPDATE Record_Label SET recordLabelName = ? , dateCreated = ?, labelInfo = ? WHERE recordLabelId = ?";
            return AlinityMain.alinityDB.setData(putStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE album SET albumName = ? , albumInfo = ?, releaseDate = ?, artistId = ?, genreId = ? WHERE artistId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE album SET albumName = ? , albumInfo = ?, releaseDate = ?, artistId = ?, genreId = ? WHERE artistId = ?");
        }
    }



    /**
     * insertAll method of the Album class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said Record Label.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String insertStmt = "INSERT INTO Record_Label (recordLabelName, dateCreated, labelInfo) VALUES (?, ?, ?)";
            return AlinityMain.alinityDB.setData(insertStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Album (albumName, albumInfo, releaseDate, artistId, genreId) VALUES (?, ?, ?, ?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Album (albumName, albumInfo, releaseDate, artistId, genreId) VALUES (?, ?, ?, ?, ?)");
        }
    }

    /**
     * deleteAll method of the Album class.
     * Deletes the given Record Label data where the recordLabelId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when deleting ALL the information for said Record Label
     * id
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String deleteStmt = "DELETE FROM Record_Label WHERE recordLabelId = ?";
            return AlinityMain.alinityDB.setData(deleteStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Album WHERE albumId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Album WHERE albumId = ?");
        }
    }

    /**
     * printRecordLabel method of the Award class.
     * Print values for the current recordLabelId,
     * recordLabelName, dateCreated and labelInfo
     * and the id of the recordLabel
     */
    public void printRecordLabel() {
        System.out.println("\nID: " + this.recordLabelId);
        System.out.println("Record name: " + this.recordLabelName);
        System.out.println("Date created: " + this.recordDateCreated);
        System.out.println("Label info: " + this.labelInfo);
    }

}
