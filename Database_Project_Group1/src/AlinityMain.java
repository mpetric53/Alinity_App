import java.util.Scanner;

public class AlinityMain {

    public static Alinity alinityDB = null;

    public static void main(String[] args) {
        try {
            /**
             * Scanner object to take in user input about the database.
             * Based on this, the database connection either will or
             * will not be established.
             */
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter name of DBMS you wish to use (i.e. 'mysql'): ");
            String dbms = scan.nextLine();
            System.out.print("Enter server name (i.e. 'localHost'): ");
            String serverName = scan.nextLine();
            System.out.print("Enter port number: ");
            String portNumber = scan.nextLine();
            System.out.print("Enter the name of the DB you wish to connect to: ");
            String dbName = scan.nextLine();
            System.out.print("Enter your username: ");
            String username = scan.nextLine();
            System.out.print("Enter your password: ");
            String password = scan.nextLine();

            /**
             * The object should have the following set in its "creation" (all are Strings):
             * dbms (name of database management system, such as MySQL), serverName, portNumber, dbName (name of
             * the database you wish to connect to), username, and password.
             *
             */
            alinityDB = new Alinity(dbms, serverName, portNumber, dbName, username, password);
            alinityDB.connect();

            /**
             * attempts to login with the information the
             * user has presented. If the user has failed,
             * ask again up to a total of three times.
             */
            User user = new User();
            System.out.println("Enter your username and password: ");
            String userId = scan.nextLine();
            String passwordId = scan.nextLine();

            while (!user.login(userId, passwordId)) {
                userId = scan.nextLine();
                passwordId = scan.nextLine();
            }
        } catch (AlinityException ae) {
            System.out.println("Test error!");
        } finally {
            if (alinityDB != null) {
                try{
                    alinityDB.close();
                } catch (AlinityException dle){
                    System.out.println("Unable to complete operation. Please contact the administrator.");
                }
            }
        }
    }
}
