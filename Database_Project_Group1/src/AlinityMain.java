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
             *
             * NOTE TO PROFESSOR" PLEASE CHANGE THESE PARAMETERS TO FIT YOUR PERSONAL DATABASE NEEDS. PASTE THE
             * FILE CALLED "project_sq;.txt", FOUND IH THE ALINITY_SQL FOLDER,  INTO YOUR DESIRED SQL WORKBENCH
             * BEFORE DOING THIS.
             */
            alinityDB = new Alinity("mysql", "localHost", "3306", "alinity", "root", "Marlenagang4519995711121999robot123-");
            alinityDB.connect();

            AlinityController ac = new AlinityController();

            //NOTE TO PROFESSOR: THE CLOSING OF THE CONNECTION IS DONE WITHIN A WINDOW.ONCLOSE METHOD IN THE ALINITYCONTROLLER CLASS.

        } catch (AlinityException ae) {
            System.out.println("Test error!");
        }
    }
}
