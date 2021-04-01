import java.sql.Date;
import java.util.ArrayList;

public class Song {

    public int songId;
    public String songName;
    public int songDuration;
    public int artistId;
    public int albumId;
    public int genreId;

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(int songDuration) {
        this.songDuration = songDuration;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public Song(int songId, String songName, int songDuration, int artistId, int albumId, int genreId) {
        this.songId = songId;
        this.songName = songName;
        this.songDuration = songDuration;
        this.artistId = artistId;
        this.albumId = albumId;
        this.genreId = genreId;
    }

    /**
     * selectAll method of the Song class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select Songs where the ID value input will be bound
     * from the ArrayList, and save the result.
     * Sets ID, name, duration, albumId, artistId, genreId based on result data.
     * This method will specifically work only when selecting ALL the information for said song
     * id.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public void selectAll(ArrayList<String> stringList) throws AlinityException {
        try {
            ArrayList<ArrayList<String>> result = AlinityMain.alinityDB.getData("SELECT * FROM Song WHERE songId = ?" , stringList);
            System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> songData = result.get(1);
            setSongId(Integer.parseInt(songData.get(0)));
            setSongName(songData.get(1));
            setSongDuration(Integer.parseInt(songData.get(2)));
            setAlbumId(Integer.parseInt(songData.get(3)));
            setArtistId(Integer.parseInt(songData.get(4)));
            setGenreId(Integer.parseInt(songData.get(5)));
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Song WHERE songId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Song WHERE songId = ?");
        }
    }

    /**
     * updateAll method of the Song class.
     * A SQL statement as a String is created using the current name, duration,
     * albumId, artistId, and genreId where is based on the values which will be bound
     * from the input ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when updating ALL the information for said Song
     * id.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String putStmt = "UPDATE Song SET songName = ? , songDuration = ?, albumId = ?, artistId = ?, genreId = ? WHERE songId = ?";
            return AlinityMain.alinityDB.setData(putStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Song SET songName = ? , songDuration = ?, albumId = ?, artistId = ?, genreId = ? WHERE songId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Song SET songName = ? , songDuration = ?, albumId = ?, artistId = ?, genreId = ? WHERE songId = ?");
        }
    }

    /**
     * insertAll method of the Song class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said Song.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String insertStmt = "INSERT INTO Song (songName, songDuration, albumId, artistId, genreId) VALUES (?, ?, ?, ?, ?)";
            return AlinityMain.alinityDB.setData(insertStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Song (songName, songDuration, albumId, artistId, genreId) VALUES (?, ?, ?, ?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Song (songName, songDuration, albumId, artistId, genreId) VALUES (?, ?, ?, ?, ?)");
        }
    }

    /**
     * deleteAll method of the Song class.
     * Deletes the given song data where the songId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when deleting ALL the information for said song
     * id
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAll(ArrayList<String> stringList) throws AlinityException {
        try {
            String deleteStmt = "DELETE FROM Song WHERE songId = ?";
            return AlinityMain.alinityDB.setData(deleteStmt, stringList);
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Song WHERE songId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Song WHERE songId = ?");
        }
    }

    /**
     * printAlbum method of the Song class.
     * Print values for the current songName,
     * songDuration, and the id of the artist and genre (will be changed
     * later to represent the song itself).
     */
    public void printAlbum() {
        System.out.println("\nID: " + this.songId);
        System.out.println("Song Name: " + this.songName);
        System.out.println("Song Duration: " + this.songDuration);
        System.out.println("Album ID: " + this.albumId);
        System.out.println("Artist ID: " + this.artistId);
        System.out.println("Genre ID: " + this.genreId);
    }
}
