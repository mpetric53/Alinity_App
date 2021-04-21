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
     * selectRecordLabel method of the RecordLabel class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * Sets RecordLabelID, RecordLabelName, recordDateCreated, labelInfo
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public void selectRecordLabel(User user, String recordLabelName) throws AlinityException {
        try {
            if(user.getRole().equals("General") || user.getRole().equals("Admin")){
                ArrayList<String> info = new ArrayList<>();
                info.add(recordLabelName);
                ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM Record_Label WHERE recordLabelName = ?" , info);
                System.out.print("\nColumn headers: " + result.get(0));
                ArrayList<String> albumData = result.get(1);
                setRecordLabelId(Integer.parseInt(albumData.get(0)));
                setRecordLabelName(albumData.get(1));
                setRecordDateCreated(Date.valueOf(albumData.get(2)));
                setLabelInfo(albumData.get(3));
                printRecordLabel();
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectRecordLabel(ArrayList<String>) method.", "SELECT * FROM Record_Label WHERE recordLabelName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectRecordLabel(ArrayList<String>) method.", "SELECT * FROM Record_Label WHERE recordLabelName = ?");
        }
    }


    /**
     * updateRecordLabel method of the RecordLabel class.
     * A SQL statement as a String is created using the current recordLabelName, dateCreated,
     * labelInfo and recordLabelId where the recordLabelId is based on the values which will be bound
     * from the input ArrayList of Strings.
     * Executes using the setDada method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateRecordLabel(User user, String recordLabelName, Date dateCreated, String labelInfo) throws AlinityException {
        try {
            if(user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(recordLabelName);
                info.add(String.valueOf(dateCreated));
                info.add(labelInfo);
                info.add(String.valueOf(this.getRecordLabelId()));
                String putStmt = "UPDATE Album SET recordLabelName = ? , dateCreated = ?, labelInfo = ? WHERE recordLabelId = ?";
                return AlinityMain.alinityDB.setData(putStmt, info);
            } else System.out.println("You do not have the correct permissions to use this function. Please contact an administrator."); return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateRecordLabel(ArrayList<String>) method.", "UPDATE Album SET recordLabelName = ? , dateCreated = ?, labelInfo = ? WHERE recordLabelId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateRecordLabel(ArrayList<String>) method.", "UPDATE Album SET recordLabelName = ? , dateCreated = ?, labelInfo = ? WHERE recordLabelId = ?");
        }
    }

    /**
     * insertRecordLabel method of the Album class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertRecordLabel(User user, String recordLabelName, Date recordDateCreated, String labelInfo) throws AlinityException {
        try {
            if(user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(recordLabelName);
                info.add(String.valueOf(recordDateCreated));
                info.add(String.valueOf(labelInfo));
                String insertStmt = "INSERT INTO Record_Label (recordLabelName, dateCreated, labelInfo) VALUES (?, ?, ?)";
                return AlinityMain.alinityDB.setData(insertStmt, info);
            } else System.out.println("You do not have the correct permissions to use this function. Please contact an administrator."); return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertRecordLabel(ArrayList<String>) method.","INSERT INTO Record_Label (recordLabelName, dateCreated, labelInfo) VALUES (?, ?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertRecordLabel(ArrayList<String>) method.","INSERT INTO Record_Label (recordLabelName, dateCreated, labelInfo) VALUES (?, ?, ?)");
        }
    }

    /**
     * deleteAlbum method of the Album class.
     * Deletes the given Record Label data where the recordLabelId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAlbum(User user) throws AlinityException {
        try {
            if(user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(this.getRecordLabelId()));
                String deleteStmt = "DELETE FROM Record_Label WHERE albumId = ?";
                return AlinityMain.alinityDB.setData(deleteStmt, info);
            } else System.out.println("You do not have the correct permissions to use this function. Please contact an administrator."); return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAlbum() method.", "DELETE FROM Record_Label WHERE albumId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAlbum() method.", "DELETE FROM Record_Label WHERE albumId = ?");
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
