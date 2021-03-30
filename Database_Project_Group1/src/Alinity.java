import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Alinity {
    private String dbms;
    private String serverName;
    private String portNumber;
    private String dbName;
    private String username;
    private String password;
    private Connection connection = null;

    /**
     * constructor of Alinity class
     *
     * @param dbms
     * @param serverName
     * @param portNumber
     * @param dbName
     * @param username
     * @param password
     */
    public Alinity(String dbms, String serverName, String portNumber, String dbName, String username, String password) {
        this.dbms = dbms;
        this.serverName = serverName;
        this.portNumber = portNumber;
        this.dbName = dbName;
        this.username = username;
        this.password = password;
    }

    /**
     * connect() method of the Alinity class
     * Attempts to connect to the given database.
     * If successful, using the isValid() method, it will
     * print out a confirmation message.
     * Else, return false
     *
     * @return boolean. True or false, depending on
     * whether the connection has been established.
     */
    public boolean connect() throws AlinityException{
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", this.username);
            connectionProps.put("password", this.password);
            connection = DriverManager.getConnection("jdbc:" + dbms + "://" + serverName + ":" + portNumber + "/" + dbName, connectionProps);
            if (connection.isValid(3)) {
                System.out.println("Connected to database: " + this.dbName);
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqle) {
            throw new AlinityException(sqle, "Error connecting to database: ", null);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe,"Error in processing information at the connect method: ", null);
        }
    }

    /**
     * close method of the Alinity class.
     * Closes the connection to the database.
     * If it is closes, checked with the isClosed() method,
     * it will report a confirmation message to the user
     * and set the boolean isClosed to true.
     * Else, return false.
     *
     * @return True or false, depending on
     * whether the connection has been closed.
     */
    public boolean close() throws AlinityException {
        try {
            connection.close();
            if (connection.isClosed()) {
                System.out.println("The connection has closed successfully.");
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqle) {
            throw new AlinityException(sqle,"Error closing connection: ",null);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe,"Error in processing information at the close method: ",null);
        }
    }

    /**
     * getData method of the Alinity method.
     * Takes in an SQL statement as a String, and an ArrayList of Strings.
     * Prepares a statement using the prepare method.
     * The header ArrayList will store the column names of the query
     * as a "header" row first.
     * While there is something to read from the result set,
     * create an ArrayList of strings.
     * Loops through the amouynt of column numbers, and adds
     * each result at i to the row list. The row is then added to the result list.
     * Returns the 2D ArrayList.
     *
     * @param sqlStatement
     * @param stringValues
     * @return ArrayList<ArrayList<String>>
     */
    public ArrayList<ArrayList<String>> getData(String sqlStatement, ArrayList<String> stringValues) throws AlinityException {
        ArrayList<ArrayList<String>> result = null;
        try {
            result = new ArrayList<>();
            PreparedStatement prep = prepare(sqlStatement, stringValues);
            ResultSet resultSet = prep.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if(columnCount == 0) return null;
            ArrayList<String> header = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                header.add(rsmd.getColumnName(i));
            }
            result.add(header);
            //System.out.println(result); Testing to see if the "headers" display properly
            while (resultSet.next()) {
                ArrayList<String> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }
                result.add(row);
            }
        } catch (SQLException sqle) {
            throw new AlinityException(sqle,"-> Error in processing data retrieval (SQLException) to the database at getData(String, ArrayList<String>) method.",sqlStatement);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe,"-> Error in processing data retrieval (NullPointerException) to the database at getData(String, ArrayList<String>) method.",sqlStatement);
        }
        return result;
    }

    /**
     * setData method of the MySQLDatabase class.
     * An SQL statement is taken in as a paramater, and an ArrayList of Strings.
     * Prepares a statement using the prepare method.
     * If successful, returns true. If there is an error,
     * false is returned and errors are logged to the file.
     *
     * @param sqlStatement
     * @param stringValues
     * @return boolean
     */
    public boolean setData(String sqlStatement, ArrayList<String> stringValues) throws AlinityException{
        try {
            PreparedStatement prep = prepare(sqlStatement, stringValues);
            int update = prep.executeUpdate();
            if(update > 0) {
                return true;
            } return false;
        } catch (SQLException sqle) {
            throw new AlinityException(sqle, "-> Error in processing data manipulation (SQLException) to the database at setData(String, ArrayList<String>) method.", sqlStatement);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in processing data manipulation (NullPointerException) to the database at setData(String, ArrayList<String>) method.", sqlStatement);
        }
    }

    /**
     * prepare() method of the Alinity class.
     * Takes in a String as an SQL statement and an ArrayList of Strings.
     * Prepares a statement using the PreparedStatement class.
     * For each item then input into the ArrayList, bind it within the
     * prepared statement. Returns a prepared statement.
     *
     * @param sqlStatement
     * @param stringList
     * @return PreparedStatement
     */
    public PreparedStatement prepare(String sqlStatement, ArrayList<String> stringList) throws AlinityException{
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
            for(int i = 0; i < stringList.size(); i++) {
                prepStmt.setString(i + 1, stringList.get(i));
            }
            return prepStmt;
        } catch (SQLException sqle) {
            throw new AlinityException(sqle,"Error preparing statement: ", sqlStatement);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe,"Error processing information at prepare method: ", sqlStatement);
        }
    }

}
