import java.util.ArrayList;
/**
 * @author Lucija Filipovic
 * @author Mislav Rukonic
 * @author Sven Slivar
 * @author Matej Petric
 */
public class Genre {

    public int genreId;
    public String genreName;

    public Genre() {
    }

    public Genre(int genreId, String name) {
        this.genreId = genreId;
        genreName = name;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    /**
     * selectGenre method of the Genre class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select Genres where the genreName value input will be bound
     * from the ArrayList, and save the result.
     * Sets genreID, name based on result data.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public ArrayList<ArrayList<String>> selectGenre(User user, String genreName) throws AlinityException {
        try {
            if (user.getRole().equals("General") || user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(genreName);
                return AlinityMain.alinityDB.getData("SELECT * FROM Genre WHERE Genre.genreName = ?", info);
//                System.out.print("\nColumn headers: " + result.get(0));
//                ArrayList<String> genreData = result.get(1);
//                setGenreId(Integer.parseInt(genreData.get(0)));
//                setGenreName(genreData.get(1));
//                printGenre();
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
            return null;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectGenre(ArrayList<String>) method.", "SELECT * FROM Album WHERE genreName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectGenre(ArrayList<String>) method.", "SELECT * FROM Album WHERE genreName = ?");
        }
    }

    public ArrayList<ArrayList<String>> selectGenre(User user, int genreId) throws AlinityException {
        try {
            if (user.getRole().equals("General") || user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(genreId));
                return AlinityMain.alinityDB.getData("SELECT * FROM Genre WHERE Genre.genreId = ?", info);
//                System.out.print("\nColumn headers: " + result.get(0));
//                ArrayList<String> genreData = result.get(1);
//                setGenreId(Integer.parseInt(genreData.get(0)));
//                setGenreName(genreData.get(1));
//                printGenre();
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
            return null;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectGenre(ArrayList<String>) method.", "SELECT * FROM Album WHERE genreName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectGenre(ArrayList<String>) method.", "SELECT * FROM Album WHERE genreName = ?");
        }
    }

    public void selectGenreHandler(ArrayList<ArrayList<String>> result) {
        for (int i = 1; i < result.size(); i++) {
            ArrayList<String> genreData = result.get(i);
            setGenreId(Integer.parseInt(genreData.get(0)));
            setGenreName(genreData.get(1));
            printGenre();
        }
    }

    /**
     * updateGenre method of the Genre class.
     * A SQL statement as a String is created using the current name
     * where the genreId is based on the values which will be bound
     * from the input ArrayList of Strings.
     * Executes using the setData method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateGenre(User user, String genreName) throws AlinityException {
        try {
            if (user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(genreName);
                info.add(String.valueOf(this.getGenreId()));
                String putStmt = "UPDATE Genre SET genreName = ? WHERE genreId = ?";
                return AlinityMain.alinityDB.setData(putStmt, info);
            } else
                System.out.println("You do not have the correct permissions to use this function. Please contact an administrator.");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateGenre(ArrayList<String>) method.", "UPDATE Genre SET genreName = ? WHERE genreId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateGenre(ArrayList<String>) method.", "UPDATE Genre SET genreName = ? WHERE genreId = ?");
        }
    }

    /**
     * insertGenre method of the Genre class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertGenre(User user, String genreName) throws AlinityException {
        try {
            if (user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(genreName);
                String insertStmt = "INSERT INTO Album (genreName) VALUES (?)";
                return AlinityMain.alinityDB.setData(insertStmt, info);
            } else
                System.out.println("You do not have the correct permissions to use this function. Please contact an administrator.");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertGenre(ArrayList<String>) method.", "INSERT INTO Album (genreName) VALUES (?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertGenre(ArrayList<String>) method.", "INSERT INTO Album (genreName) VALUES (?)");
        }
    }

    /**
     * deleteAlbum method of the Genre class.
     * Deletes the given album data where the genreId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAlbum(User user) throws AlinityException {
        try {
            if (user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(this.getGenreId()));
                String deleteStmt = "DELETE FROM Genre WHERE genreId = ?";
                return AlinityMain.alinityDB.setData(deleteStmt, info);
            } else
                System.out.println("You do not have the correct permissions to use this function. Please contact an administrator.");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAlbum() method.", "DELETE FROM Genre WHERE genreId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAlbum() method.", "DELETE FROM Genre WHERE genreId = ?");
        }
    }

    /**
     * printAlbum method of the Genre class.
     * Print values for the current genreId,
     * genreName(will be changed
     * later to represent the artist itself).
     */
    public void printGenre() {
        System.out.println("\nID: " + this.genreId);
        System.out.println("Name: " + this.genreName);
    }
}
