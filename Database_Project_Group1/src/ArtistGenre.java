import java.util.ArrayList;

public class ArtistGenre {

    private int artistId;
    private int genreId;
    private String artistName;
    private String genreName;

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public ArtistGenre() {

    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }


    /**
     * selectArtistGenre method of the ArtistGenre class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select ArtistGenres where the name value input will be bound
     * from the ArrayList, and save the result.
     * Sets names of artist and genre based on result data name of genre..
     * This method will work only when selecting A SPECIFIC the information for said genre name,
     * since this table allows for joining. It will show all artists that belong to the specified genre name.
     * This action can be performed by both admins and general users.
     * If the attempt of the executing the statement fails, log the error to the file.
     * NOTE: This version of selecting is still in development, expect this to be changed.
     *
     * @throws AlinityException
     */
    public ArrayList<ArrayList<String>> selectArtistGenre(User user, Genre genre) throws AlinityException {
        try {
            if (user.getRole().equals("General") || user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(genre.getGenreId()));
                return AlinityMain.alinityDB.getData("SELECT Artist.artistName, Genre.genreName FROM Genre\n" +
                        "NATURAL JOIN Artist_Genre NATURAL JOIN Artist\n" +
                        "WHERE Genre.genreId = ?", info);
//                System.out.print("\nColumn headers: " + result.get(0));
//                ArrayList<String> artistData = result.get(1);
//                setArtistName(artistData.get(0));
//                setGenreName(artistData.get(1));
//                printArtist_Genre();
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
            return null;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Artist.artistName, Genre.genreName FROM Genre\\n\" +\n" +
                    "                        \"NATURAL JOIN Artist_Genre NATURAL JOIN Artist\\n\" +\n" +
                    "                        \"WHERE Genre.genreName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT Artist.artistName, Genre.genreName FROM Genre\\n\" +\n" +
                    "                        \"NATURAL JOIN Artist_Genre NATURAL JOIN Artist\\n\" +\n" +
                    "                        \"WHERE Genre.genreName = ?");
        }
    }

    public void selectArtistGenreHandler(ArrayList<ArrayList<String>> result) {
        for (int i = 1; i < result.size(); i++) {
            //System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> artistData = result.get(i);
            setArtistName(artistData.get(0));
            setGenreName(artistData.get(1));
            printArtist_Genre();
        }
    }

    /**
     * updateArtistGenre method of the ArtistGenre class.
     * A SQL statement as a String is created using the current ids of the
     * artist and genre where the artistId is based on the values which will be bound
     * from the input ArrayList of Strings.
     * Executes using the setDada method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateArtistGenre(User user, Artist artist, Genre genre) throws AlinityException {
        try {
            if (user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(genre.getGenreId()));
                info.add(String.valueOf(artist.getArtistId()));
                String putStmt = "UPDATE Artist_Genre SET genreId = ? WHERE artistId = ?";
                return AlinityMain.alinityDB.setData(putStmt, info);
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Artist_Genre SET artistId = ? , genreId = ? WHERE artistId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Artist_Genre SET artistId = ? , genreId = ? WHERE artistId = ?");
        }
    }

    /**
     * insertArtistGenre method of the ArtistGenre class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said artist.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertArtistGenre(User user, Artist artist, Genre genre) throws AlinityException {
        try {
            if (user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(artist.getArtistId()));
                info.add(String.valueOf(genre.getGenreId()));
                String insertStmt = "INSERT INTO Artist_Genre (artistId, genreId) VALUES (?, ?)";
                return AlinityMain.alinityDB.setData(insertStmt, info);
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.", "INSERT INTO Artist_Genre (artistId, genreId) VALUES (?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.", "INSERT INTO Artist_Genre (artistId, genreId) VALUES (?, ?)");
        }
    }

    /**
     * deleteArtistGenre method of the ArtistGenre class.
     * Deletes the given Artist_Genre data where the artistId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteArtistGenre(User user, Artist artist) throws AlinityException {
        try {
            if (user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(artist.getArtistId()));
                String deleteStmt = "DELETE FROM Artist_Genre WHERE artistId = ?";
                return AlinityMain.alinityDB.setData(deleteStmt, info);
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Artist_Genre WHERE artistId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Artist_Genre WHERE artistId = ?");
        }
    }

    /**
     * printArtist_Genre method of the Artist class.
     * Print values for the current
     * artistName and genreName based on bound ID Value.
     * (will be changed
     * later to represent the artist itself).
     */
    public void printArtist_Genre() {
        System.out.println("\nArtist Name: " + this.artistName);
        System.out.println("Genre Name: " + this.genreName);
    }
}
