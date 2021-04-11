import java.sql.*;
import java.util.ArrayList;

public class User {

    private int userId;
    private String username;
    private String password;
    private Date birthday;
    private String email;
    private String role;
    private int counter = 0;
    private final int MAX_ATTEMPTS = 3;

    public User(int userId, String username, String password, Date birthday, String email, String role){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    /**
     * authenticate method of the User class.
     * Will attempt to authenticate the users login information
     * by checking if the input information is in the DB or not.
     * If successful, that user and its "object" will be set.
     * This is important, since we use the user's ID to retrieve
     * their specific saved songs, albums, and artists.
     *
     * @param username
     * @param password
     * @return
     * @throws AlinityException
     */
    public boolean authenticate(String username, String password) throws AlinityException{
        try{
            ArrayList<String> credentials = new ArrayList<>();
            credentials.add(username);
            credentials.add(password);
            ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM User WHERE User.username = ? AND User.password = ?" , credentials);
            if(result.size() <= 1) {
                System.out.println("Invalid credentials.");
                return false;
            }
            ArrayList<String> userData = result.get(1);
            setUserId(Integer.parseInt(userData.get(0)));
            setUsername(userData.get(1));
            setPassword(userData.get(2));
            setBirthday(Date.valueOf(userData.get(3)));
            setEmail(userData.get(4));
            setRole(userData.get(5));
            return true;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error authenticating.", null);
        }
    }

    /**
     * login method of the User class.
     * Will allow a user to login and use our system. This is done
     * by calling the authenticate method to check their information.
     * If the information is incorrect, the user will have 2 more tries
     * to input valid information. Else, it will authenticate properly,
     * and if the user is an admin, it will display their information.
     *
     * @param username
     * @param password
     * @return
     * @throws AlinityException
     */
    public boolean login(String username, String password) throws AlinityException {
        if(!authenticate(username, password)) {
            counter++;
            if(counter >= MAX_ATTEMPTS) {
                System.out.println("Too many incorrect attempts, terminating system...");
                System.exit(0);
            }
            System.out.println("Incorrect credentials, try again. You have: " + (MAX_ATTEMPTS - counter) + " more tries");
            return false;
        } else {
            authenticate(username, password);
            if(this.getRole().equals("Admin")) {
                printInfo();
            }
            return true;
        }
    }

    public void printInfo() {
        System.out.println("\nUser ID: " + this.userId);
        System.out.println("Username: " + this.username);
        System.out.println("User password: " + this.password);
        System.out.println("User birthday: " + this.birthday);
        System.out.println("User email: " + this.email);
        System.out.println("User role: " + this.role);
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
            if(this.getRole().equals("Admin")) {
                ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM User WHERE userId = ?", stringList);
                System.out.print("\nColumn headers: " + result.get(0));
                ArrayList<String> userData = result.get(1);
                setUserId(Integer.parseInt(userData.get(0)));
                setUsername(userData.get(1));
                setPassword(userData.get(2));
                setBirthday(Date.valueOf(userData.get(3)));
                setEmail(userData.get(4));
            } else System.out.println("You do not have the right permissions to perform this action!");
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
