/**
 * @author Lucija Filipovic
 * @author Mislav Rukonic
 * @author Sven Slivar
 * @author Matej Petric
 */
public class AlinityMain {

    public static Alinity alinityDB = null;


    public static void main(String[] args) {
        try {
            /**
             * The object should have the following set in its "creation" (all are Strings):
             * dbms (name of database management system, such as MySQL), serverName, portNumber, dbName (name of
             * the database you wish to connect to), username, and password.
             * NOTE TO PROFESSOR: HERE, SET YOU USERNAME AND PASSWORD OF YOUR LOCAL DATABASE. PLEASE RUN THE alinity.sql
             * IN MySQL WORKBENCH FIRST!!!
             *
             */
            alinityDB = new Alinity("mysql", "localHost", "3306", "alinity", "root", "");
            alinityDB.connect();

            AlinityController ac = new AlinityController();

            //NOTE TO PROFESSOR: CLOSING IS DONE WITHIN A WINDOW.ONCLOSE METHOD, DON'T WORRY IT ISN't MISSING.

        } catch (AlinityException ae) {
            System.out.println("Test error!");
        }
    }
}
