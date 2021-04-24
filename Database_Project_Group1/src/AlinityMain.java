import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
//            Scanner scan = new Scanner(System.in);
//            System.out.print("Enter name of DBMS you wish to use (i.e. 'mysql'): ");
//            String dbms = scan.nextLine();
//            System.out.print("Enter server name (i.e. 'localHost'): ");
//            String serverName = scan.nextLine();
//            System.out.print("Enter port number: ");
//            String portNumber = scan.nextLine();
//            System.out.print("Enter the name of the DB you wish to connect to: ");
//            String dbName = scan.nextLine();
//            System.out.print("Enter your username: ");
//            String username = scan.nextLine();
//            System.out.print("Enter your password: ");
//            String password = scan.nextLine();

            /**
             * The object should have the following set in its "creation" (all are Strings):
             * dbms (name of database management system, such as MySQL), serverName, portNumber, dbName (name of
             * the database you wish to connect to), username, and password.
             *
             */
            alinityDB = new Alinity("mysql", "localHost", "3306", "alinity", "root", "root");
            alinityDB.connect();

            /**
             * attempts to login with the information the
             * user has presented. If the user has failed,
             * ask again up to a total of three times.
             * to be placed within login gui code
             */
            //User user = new User();
//            System.out.println("Enter your username and password: ");
//            String userId = scan.nextLine();
//            String passwordId = scan.nextLine();
//
//            while (!user.login(userId, passwordId)) {
//                userId = scan.nextLine();
//                passwordId = scan.nextLine();
//            }
            /**
             * to be placed within signup GUI code
             */
         //   LogInGUI login = new LogInGUI();
            AlinityController ac = new AlinityController();

//            System.out.println("First time here? Sign up to use Alinity to its fullest!");
//            System.out.println("Enter a username, email, birthday (in YYYY-MM-DD format), and password: ");
//            String signUpUsername = scan.nextLine();
//            String signUpEmail = scan.nextLine();
//            String bday = scan.nextLine();
//            java.sql.Date dateBday = java.sql.Date.valueOf(bday);
//            String signUpPassword = scan.nextLine();
//            User user2 = new User();
//            user2.signUp(signUpUsername, signUpPassword, dateBday, signUpEmail);

//            Album album = new Album();
//            Artist artist = new Artist();
//            artist.selectArtist(user, "Metallica");
//            ArrayList<ArrayList<String>> test = album.selectArtistAlbum(user, artist);
//            album.handleSelectAlbumByArtist(test);

        } catch (AlinityException ae) {
            System.out.println("Test error!");
        } finally {
            if (alinityDB != null) {

                //alinityDB.close();
            }
        }
    }
}
