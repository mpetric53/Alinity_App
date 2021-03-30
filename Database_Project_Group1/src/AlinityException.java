import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.SQLException;

public class AlinityException extends Exception{
    private String errorMessage;
    private String sqlStmt;
    private static final String logFile = "log.txt";
    private PrintWriter pw;


    public AlinityException (Exception e){
        super(e);
        createLog(e, null,null);
    }

    public AlinityException (Exception e, String errorMessage, String sqlStmt){
        super(e);
        this.errorMessage = errorMessage;
        this.sqlStmt = sqlStmt;
        createLog(e, errorMessage, sqlStmt);
    }

    public void createLog(Exception e, String errorMessage, String SQLStatement) {
        try {
            Date time = new Date();
            String timeStamp = time.toString();
            File file = new File("AlinityLog.txt");
            pw = new PrintWriter(new FileWriter(file, true));
            if (e instanceof SQLException) {
                pw.write(timeStamp + "-> SQLState: " + ((SQLException) e).getSQLState() + '\n');
                pw.write(timeStamp + "-> Error code: " + ((SQLException) e).getErrorCode() + '\n');
                pw.write(timeStamp + "-> Original SQLStatement: " + sqlStmt + '\n');
            } else if (e instanceof NullPointerException) {
                pw.write(timeStamp + "-> NullPointerException: " + e + '\n');
            } else if (e instanceof IndexOutOfBoundsException) {
                pw.write(timeStamp + "-> IndexOutOfBoundsException: " + e + '\n');
            }
            pw.write(timeStamp + "-> Message: " + e.getMessage() + '\n');
            Throwable thrw = e.getCause();
            while (thrw != null) {
                pw.write(timeStamp + "-> Cause: " + thrw + '\n');
                thrw = thrw.getCause();
            }
            pw.write(timeStamp + " " + errorMessage + '\n');
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            pw.flush();
            pw.close();
        }
    }
}
