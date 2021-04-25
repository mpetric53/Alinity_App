import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Lucija Filipovic
 * @author Mislav Rukonic
 * @author Sven Slivar
 * @author Matej Petric
 */

public class User {

    private final int MAX_ATTEMPTS = 3;
    private int userId;
    private String username;
    private String password;
    private Date birthday;
    private String email;
    private String role;
    private int counter = 0;


    public User(int userId, String username, String password, Date birthday, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
        this.role = role;
    }

    public User() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public boolean authenticate(String username, String password) throws AlinityException {
        try {
            ArrayList<String> credentials = new ArrayList<>();
            credentials.add(username);
            ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM User WHERE User.username = ?", credentials);
            if (result.size() <= 1) {
                System.out.println("Invalid credentials.");
                return false;
            }
            ArrayList<String> userData = result.get(1);
            if (validatePassword(password, userData.get(2))) {
                setUserId(Integer.parseInt(userData.get(0)));
                setUsername(userData.get(1));
                setPassword(userData.get(2));
                setBirthday(Date.valueOf(userData.get(3)));
                setEmail(userData.get(4));
                setRole(userData.get(5));
                return true;
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException | NoSuchAlgorithmException | InvalidKeySpecException ioobe) {
            throw new AlinityException(ioobe, "-> Error authenticating.", null);
        }
    }

    /**
     * signUp method of the User class.
     * Will be used to perform an initial signup for a new user.
     * This is needed because this is the first time a user's hashed password
     * will be generated with a random salt. Since the salt changes every time,
     * this is needed to store the generated password into the database, so when
     * the user logs in, it can match the stored hashed password with the hash of the
     * input password (otherwise it would be a different password hash every time due to
     * random salt generation).
     *
     * @param username
     * @param password
     * @param birthday
     * @param email
     * @throws AlinityException
     */
    public void signUp(String username, String password, Date birthday, String email) throws AlinityException {
        try {
            ArrayList<String> signUpInfo = new ArrayList<>();
            String hashedPassword = generateStrongPasswordHash(password);
            signUpInfo.add(username);
            signUpInfo.add(hashedPassword);
            signUpInfo.add(String.valueOf(birthday));
            signUpInfo.add(email);
            String insertStmt = "INSERT INTO User (username, password, birthday, email) VALUES (?, ?, ?, ?)";
            AlinityMain.alinityDB.setData(insertStmt, signUpInfo);
            login(username, password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AlinityException(e, "Error inserting new user into DB (sign up) at signUp method.", "INSERT INTO User (username, password, birthday, email) VALUES (?, ?, ?, ?)");
        }
    }

    /**
     * getSalt method of the User class.
     * Generates a random salt to be used when
     * password hashing.
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    private byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    /**
     * toHex method of the User class.
     * Turns the byte array into hex values.
     *
     * @param array
     * @return
     */
    private String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    /**
     * generateStrongPasswordHash method of the User class.
     * Will generate a hashed password based on what the user inputs as their password.
     * This is used when the user is signing up for the first time, to store this to the database.
     *
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private String generateStrongPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    /**
     * validatePassword method of the User class.
     * Compares the input password (from the user) and the password from the database.
     * If the check passes, the hashes match, and the user will be logged in.
     * Else, the user will be denied access to login.
     *
     * @param password
     * @param storedPassword
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private boolean validatePassword(String password, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    /**
     * fromHex method of the User class.
     * Turns the hex value into an array of bytes,
     *
     * @param hex
     * @return
     */
    private byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
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
        if (!authenticate(username, password)) {
            counter++;
            if (counter >= MAX_ATTEMPTS) {
                System.out.println("Too many incorrect attempts, terminating system...");
                System.exit(0);
            }
            JFrame jf = new JFrame();
            String name = JOptionPane.showInputDialog(jf, "Incorrect credentials, try again. You have: " + (MAX_ATTEMPTS - counter) + " more tries", null);
            return false;
        } else {
            authenticate(username, password);
            this.setPassword(password);
            if (this.getRole().equals("Admin")) {
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
     * selectUser method of the User class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select Users where the ID value input will be bound
     * from the ArrayList, and save the result.
     * Sets ID, username, password, birthday, email. Prints said information
     * for testing purposes.
     * This method will specifically work only when selecting ALL the information for said User
     * Only admins wil have access to this method, since users should not be allowed
     * to see information about any user.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public void selectUser(int userId) throws AlinityException {
        try {
            if (this.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(userId));
                ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM User WHERE userId = ?", info);
                System.out.print("\nColumn headers: " + result.get(0));
                ArrayList<String> userData = result.get(1);
                setUserId(Integer.parseInt(userData.get(0)));
                setUsername(userData.get(1));
                setPassword(userData.get(2));
                setBirthday(Date.valueOf(userData.get(3)));
                setEmail(userData.get(4));
                printInfo();
            } else System.out.println("You do not have the right permissions to perform this action!");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM User WHERE userId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM User WHERE userId = ?");
        }
    }


    /**
     * deleteUser method of the User class.
     * Deletes the given User data where the userId is bound
     * via tha value inside the ArrayList of Strings.
     * This value is retrieved from the object
     * on which this method is called.
     * Executes using the setData method.
     * This method will specifically work only when deleting ALL the information for said userId
     * Only admins can delete users from the database.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteUser() throws AlinityException {
        try {
            if (this.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(this.getUserId()));
                String deleteStmt = "DELETE FROM User WHERE userId = ?";
                return AlinityMain.alinityDB.setData(deleteStmt, info);
            } else System.out.println("You do not have the right permissions to perform this action!");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM User WHERE userId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM User WHERE userId = ?");
        }
    }
}
