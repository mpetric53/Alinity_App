import java.sql.*;
import java.util.ArrayList;

public class User {

    private int userId;
    private String username;
    private String password;
    private Date birthday;
    private String email;

    public User(int userId, String username, String password, Date birthday, String email){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
    }

    public User() {
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * selectAll method of the User class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select Users where the ID value input will be bound
     * from the ArrayList, and save the result.
     * Sets ID, username, password, birthday, email
     * This method will specifically work only when selecting ALL the information for said User
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public void selectAll(ArrayList<String> stringList) throws AlinityException {
        try {
            ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM User WHERE userId = ?" , stringList);
            System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> userData = result.get(1);
            setUserId(Integer.parseInt(userData.get(0)));
            setUsername(userData.get(1));
            setPassword(userData.get(2));
            setBirthday(Date.valueOf(userData.get(3)));
            setEmail(userData.get(4));

        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM User WHERE userId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM User WHERE userId = ?");
        }
    }

    /**
     * updateAll method of the User class.
     * A SQL statement as a String is created using the current username, password, birthday, email
     * from the input ArrayList of Strings.
     * Executes using the setDada method.
     * This method will specifically work only when updating ALL the information for said userId
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String putStmt = "UPDATE User SET username = ? , password = ?, birthday = ?, email = ? WHERE userId = ?";
            return AlinityMain.alinityDB.setData(putStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE User SET username = ? , password = ?, birthday = ?, email = ? WHERE userId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE User SET username = ? , password = ?, birthday = ?, email = ? WHERE userId = ?");

        }
    }

    /**
     * insertAll method of the User class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said User.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String insertStmt = "INSERT INTO User (username, password, birthday, email) VALUES (?, ?, ?, ?)";
            return AlinityMain.alinityDB.setData(insertStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO User (username, password, birthday, email) VALUES (?, ?, ?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO User (username, password, birthday, email) VALUES (?, ?, ?, ?)");
        }
    }



    /**
     * deleteAll method of the User class.
     * Deletes the given User data where the userId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when deleting ALL the information for said userId
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String deleteStmt = "DELETE FROM User WHERE userId = ?";
            return AlinityMain.alinityDB.setData(deleteStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM User WHERE userId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM User WHERE userId = ?");
        }
    }


}
