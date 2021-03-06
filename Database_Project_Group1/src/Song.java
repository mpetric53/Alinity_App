import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

/**
 * @author Lucija Filipovic
 * @author Mislav Rukonic
 * @author Sven Slivar
 * @author Matej Petric
 */

public class Song {
    private int songId;
    private String songName;
    private int songDuration;
    private int artistId;
    private int albumId;
    private int genreId;
    private SongGUI gui = new SongGUI();

    public Song(int songId, String songName, int songDuration, int artistId, int albumId, int genreId) {
        this.songId = songId;
        this.songName = songName;
        this.songDuration = songDuration;
        this.artistId = artistId;
        this.albumId = albumId;
        this.genreId = genreId;
    }

    public Song() {

    }

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

    public void setSongDuration(int songDuration) {
        this.songDuration = songDuration;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    /**
     * selectSong method of the Song class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select Songs where the name value input will be bound
     * from the ArrayList, and save the result.
     * Sets ID, name, duration, albumId, artistId, genreId based on result data.
     * This method will specifically work only when selecting ALL the information for said song
     * id.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public ArrayList<ArrayList<String>> selectSong(User user, String songName) throws AlinityException {
        try {
            if (user.getRole().equals("General") || user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(songName);
                return AlinityMain.alinityDB.getData("SELECT * FROM Song WHERE songName = ?", info);
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
            return null;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Song WHERE songName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Song WHERE songName = ?");
        }
    }

    /**
     * selectSong method of the Song class.
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
    public ArrayList<ArrayList<String>> selectSong(User user, int songId) throws AlinityException {
        try {
            if (user.getRole().equals("General") || user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(songId));
                return AlinityMain.alinityDB.getData("SELECT * FROM Song WHERE songId = ?", info);
            } else System.out.println("You do not have access to this function. Please contact an administrator.");
            return null;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Song WHERE songId = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Song WHERE songId = ?");
        }
    }

    /**
     * selectSongHandler of the Song class.
     * Will handle and display the songs searched
     * by the user.
     *
     * @param result
     * @param searchGUI
     * @param user
     * @param song
     */
    public void selectSongHandler(ArrayList<ArrayList<String>> result, SearchGUI searchGUI, User user, Song song) {
        for (int i = 1; i < result.size(); i++) {
            ArrayList<String> songData = result.get(i);
            setSongId(Integer.parseInt(songData.get(0)));
            setSongName(songData.get(1));
            setSongDuration(Integer.parseInt(songData.get(2)));
            setAlbumId(Integer.parseInt(songData.get(3)));
            setArtistId(Integer.parseInt(songData.get(4)));
            setGenreId(Integer.parseInt(songData.get(5)));
            printSong();
            SongGUI gui = new SongGUI();
            if (AlinityController.counter > 0) {
                System.out.println("test981hrw");
                searchGUI.getAlbumList().removeAll();
                searchGUI.repaint();
                AlinityController.counter = 0;
            }
            gui.getjLabel2().setText(getSongName());
            searchGUI.getAlbumList().add(searchGUI.getjPanel2().add(gui));
            gui.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int reply = JOptionPane.showConfirmDialog(null, "Would you like to save this Song?", "Saving song", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        SavedSongs savedSongs = new SavedSongs();
                        try {
                            savedSongs.insertAll(user, song);
                        } catch (AlinityException alinityException) {
                            JOptionPane.showMessageDialog(null, "You've already saved this! Try a different song", "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nothing to save!");
                    }
                }
            });

        }
        searchGUI.invalidate();
        searchGUI.validate();
        searchGUI.repaint();
    }

    /**
     * updateSong method of the Song class.
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
    public boolean updateSong(User user, String songName, String songDuration, Album album, Artist artist, Genre genre) throws AlinityException {
        try {
            if (user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(songName);
                info.add(String.valueOf(songDuration));
                info.add(String.valueOf(album.getAlbumId()));
                info.add(String.valueOf(artist.getArtistId()));
                info.add(String.valueOf(genre.getGenreId()));
                String putStmt = "UPDATE Song SET songName = ? , songDuration = ?, albumId = ?, artistId = ?, genreId = ? WHERE songId = ?";
                return AlinityMain.alinityDB.setData(putStmt, info);
            } else
                System.out.println("You do not have the correct permissions to use this function. Please contact an administrator.");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Song SET songName = ? , songDuration = ?, albumId = ?, artistId = ?, genreId = ? WHERE songId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Song SET songName = ? , songDuration = ?, albumId = ?, artistId = ?, genreId = ? WHERE songId = ?");
        }
    }

    /**
     * insertSong method of the Song class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said Song.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertSong(User user, String songName, String songDuration, Album album, Artist artist, Genre genre) throws AlinityException {
        try {
            if (user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(songName);
                info.add(String.valueOf(songDuration));
                info.add(String.valueOf(album.getAlbumId()));
                info.add(String.valueOf(artist.getArtistId()));
                info.add(String.valueOf(genre.getGenreId()));
                String insertStmt = "INSERT INTO Song (songName, songDuration, albumId, artistId, genreId) VALUES (?, ?, ?, ?, ?)";
                return AlinityMain.alinityDB.setData(insertStmt, info);
            } else
                System.out.println("You do not have the correct permissions to use this function. Please contact an administrator.");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertSong(ArrayList<String>) method.", "INSERT INTO Song (songName, songDuration, albumId, artistId, genreId) VALUES (?, ?, ?, ?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertSong(ArrayList<String>) method.", "INSERT INTO Song (songName, songDuration, albumId, artistId, genreId) VALUES (?, ?, ?, ?, ?)");
        }
    }

    /**
     * deleteSong method of the Song class.
     * Deletes the given song data where the songId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when deleting ALL the information for said song
     * id
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteSong(User user) throws AlinityException {
        try {
            if (user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(this.getSongId()));
                String deleteStmt = "DELETE FROM Song WHERE songId = ?";
                return AlinityMain.alinityDB.setData(deleteStmt, info);
            } else
                System.out.println("You do not have the correct permissions to use this function. Please contact an administrator.");
            return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteSong() method.", "DELETE FROM Song WHERE songId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteSong() method.", "DELETE FROM Song WHERE songId = ?");
        }
    }

    /**
     * printSong method of the Song class.
     * Print values for the current songName,
     * songDuration, and the id of the artist and genre (will be changed
     * later to represent the song itself).
     */
    public void printSong() {
        System.out.println("\nID: " + this.songId);
        System.out.println("Song Name: " + this.songName);
        System.out.println("Song Duration: " + this.songDuration);
        System.out.println("Album ID: " + this.albumId);
        System.out.println("Artist ID: " + this.artistId);
        System.out.println("Genre ID: " + this.genreId);
    }
}
