public class AlinityMain {

    public static Alinity alinityDB = null;

    public static void main(String[] args) {
        try {
            alinityDB = new Alinity("mysql", "localHost", "3306", "alinity", "root", "Marlenagang4519995711121999robot123-");
            alinityDB.connect();
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
