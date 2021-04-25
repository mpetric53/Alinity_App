import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

public class Album {

    private int albumId;
    private String albumName;
    private String albumInfo;
    private Date releaseDate;
    private int artistId;
    private int genreId;
    private String imgPath;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumInfo() {
        return albumInfo;
    }

    public void setAlbumInfo(String albumInfo) {
        this.albumInfo = albumInfo;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

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


    public Album(int albumId, String albumName, String albumInfo, Date releaseDate, String imgPath, int artistId, int genreId) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.albumInfo = albumInfo;
        this.releaseDate = releaseDate;
        this.imgPath = imgPath;
        this.artistId = artistId;
        this.genreId = genreId;
    }

    public Album() {

    }

    /**
     * selectAlbum method of the Album class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select Albums where the name of the album input will be bound
     * from the ArrayList, and save the result.
     * Sets ID, name, info, date, artistId, genreId based on result data.
     * This method will select all album names which the user has input, regardless of artist or songs
     * within it. Used for displaying all albums with said name to the user.
     * Works with correct user privileges.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public ArrayList<ArrayList<String>> selectAlbum(User user, String albumName) throws AlinityException {
        try {
            if(user.getRole().equals("General") || user.getRole().equals("Admin")){
                ArrayList<String> info = new ArrayList<>();
                info.add(albumName);
                return AlinityMain.alinityDB.getData("SELECT * FROM Album WHERE albumName = ?" , info);
//                System.out.print("\nColumn headers: " + result.get(0));
//                ArrayList<String> albumData = result.get(1);
//                setAlbumId(Integer.parseInt(albumData.get(0)));
//                setAlbumName(albumData.get(1));
//                setAlbumInfo(albumData.get(2));
//                setReleaseDate(Date.valueOf(albumData.get(3)));
//                setArtistId(Integer.parseInt(albumData.get(4)));
//                setGenreId(Integer.parseInt(albumData.get(5)));
//                printAlbum();
            } else System.out.println("You do not have access to this function. Please contact an administrator."); return null;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Album WHERE albumName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Album WHERE albumName = ?");
        }
    }

    /**
     * selecting albums with ID
     *
     * @param user
     * @param albumId
     * @return
     * @throws AlinityException
     */
    public ArrayList<ArrayList<String>> selectAlbum(User user, int albumId) throws AlinityException {
        try {
            if(user.getRole().equals("General") || user.getRole().equals("Admin")){
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(albumId));
                return AlinityMain.alinityDB.getData("SELECT * FROM Album WHERE albumId = ?" , info);
//                System.out.print("\nColumn headers: " + result.get(0));
//                ArrayList<String> albumData = result.get(1);
//                setAlbumId(Integer.parseInt(albumData.get(0)));
//                setAlbumName(albumData.get(1));
//                setAlbumInfo(albumData.get(2));
//                setReleaseDate(Date.valueOf(albumData.get(3)));
//                setArtistId(Integer.parseInt(albumData.get(4)));
//                setGenreId(Integer.parseInt(albumData.get(5)));
//                printAlbum();
            } else System.out.println("You do not have access to this function. Please contact an administrator."); return null;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Album WHERE albumName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Album WHERE albumName = ?");
        }
    }

    /**
     * selecting all albums under the specified artist name
     *
     * @param user
     * @param artist
     * @return
     * @throws AlinityException
     */
    public ArrayList<ArrayList<String >> selectArtistAlbum(User user, Artist artist) throws AlinityException {
        try {
            if(user.getRole().equals("General") || user.getRole().equals("Admin")){
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(artist.getArtistId()));
                return AlinityMain.alinityDB.getData("SELECT Album.albumName FROM Album WHERE Album.artistId = ?" , info);
//                System.out.print("\nColumn headers: " + result.get(0));
//                ArrayList<String> albumData = result.get(1);
//                setAlbumName(albumData.get(0));
//                printAlbum();
//                return new Album(getAlbumName());
            } else System.out.println("You do not have access to this function. Please contact an administrator."); return null;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Album WHERE albumName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Album WHERE albumName = ?");
        }
    }

    public void handleSelectAlbum(ArrayList<ArrayList<String>> result, SearchGUI searchGUI, User user, Album album) {
        for(int i = 1; i < result.size(); i++){
            //System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> albumData = result.get(i);
            setAlbumId(Integer.parseInt(albumData.get(0)));
            setAlbumName(albumData.get(1));
            setAlbumInfo(albumData.get(2));
            setReleaseDate(Date.valueOf(albumData.get(3)));
            setImgPath(albumData.get(4));
            setArtistId(Integer.parseInt(albumData.get(5)));
            setGenreId(Integer.parseInt(albumData.get(6)));
            printAlbum();
            AlbumGUI gui = new AlbumGUI();
            if(AlinityController.counter > 0){
                searchGUI.getAlbumList().removeAll();
                searchGUI.repaint();
                AlinityController.counter = 0;
            }
            //panel.setSize(500, 500);
            gui.getjLabel2().setText(getAlbumName());
            gui.getjLabel1().setIcon(new javax.swing.ImageIcon(getClass().getResource(getImgPath())));
            searchGUI.getAlbumList().add(searchGUI.getjPanel2().add(gui));


            gui.getjLabel1().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int reply = JOptionPane.showConfirmDialog(null, "Would you like to save this Album?", "Saving album", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                       SavedAlbums savedAlbums = new SavedAlbums();
                        try {
                            savedAlbums.insertAll(user, album);
                        } catch (AlinityException alinityException) {
                            alinityException.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nothing to save!");
                    }
                }
            });

            gui.getjLabel2().addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent me) {
                    gui.getjLabel2().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                }
            });


            gui.getjLabel1().addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent me) {
                    gui.getjLabel1().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                }
            });


            gui.getjLabel2().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    int reply = JOptionPane.showConfirmDialog(null, "Available on:", "Available on", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        SavedAlbums savedAlbums = new SavedAlbums();
                        try {
                            savedAlbums.insertAll(user, album);
                        } catch (AlinityException alinityException) {
                            alinityException.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nothing to save!");
                    }
                }
            });
            //searchGUI.add(gui);
            //gui.getjLabel1().setIcon(new javax.swing.ImageIcon(getClass().getResource(getImgPath())));
        }


        searchGUI.invalidate();
        searchGUI.validate();
        searchGUI.repaint();

//        searchGUI.invalidate();
//        searchGUI.validate();
//        searchGUI.repaint();


    }

    public void handleSelectAlbumByArtist(ArrayList<ArrayList<String>> result) {
        for(int i = 1; i < result.size(); i++) {
            //System.out.print("\nColumn headers: " + result.get(0));
            ArrayList<String> albumData = result.get(i);
            setAlbumName(albumData.get(0));
            printAlbum();
        }
    }

    /**
     * updateAlbum method of the Album class.
     * A SQL statement as a String is created using the current name, info,
     * releaseDate, artistId, and genreId. They will be bound via the ArrayList<String>
     * The id of the album will be retrieved from the object which will use this method.
     * Executes using the setData method.
     * This method will specifically work only when updating ALL the information for said album
     * id.
     * Only admins can use this method. General users should not have the permissions to
     * update information within the database.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateAlbum(User user, String albumName, String albumInfo, Date releaseDate, Artist artist, Genre genre) throws AlinityException {
        try {
            if(user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(albumName);
                info.add(albumInfo);
                info.add(String.valueOf(releaseDate));
                info.add(String.valueOf(artist.getArtistId()));
                info.add(String.valueOf(genre.getGenreId()));
                info.add(String.valueOf(this.getAlbumId()));
                String putStmt = "UPDATE Album SET albumName = ? , albumInfo = ?, releaseDate = ?, artistId = ?, genreId = ? WHERE albumId = ?";
                return AlinityMain.alinityDB.setData(putStmt, info);
            } else System.out.println("You do not have the correct permissions to use this function. Please contact an administrator."); return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE album SET albumName = ? , albumInfo = ?, releaseDate = ?, artistId = ?, genreId = ? WHERE albumId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE album SET albumName = ? , albumInfo = ?, releaseDate = ?, artistId = ?, genreId = ? WHERE albumId = ?");
        }
    }

    /**
     * insertAlbum method of the Album class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said album.
     * Only Admins will have access to this method. General users should not be allowed to insert
     * new music into the database itself, only into their saved albums.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertAlbum(User user, String albumName, String albumInfo, Date releaseDate, Artist artist, Genre genre) throws AlinityException {
        try {
            if(user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(albumName);
                info.add(albumInfo);
                info.add(String.valueOf(releaseDate));
                info.add(String.valueOf(artist.getArtistId()));
                info.add(String.valueOf(genre.getGenreId()));
                String insertStmt = "INSERT INTO Album (albumName, albumInfo, releaseDate, artistId, genreId) VALUES (?, ?, ?, ?, ?)";
                return AlinityMain.alinityDB.setData(insertStmt, info);
            } else System.out.println("You do not have the correct permissions to use this function. Please contact an administrator."); return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Album (albumName, albumInfo, releaseDate, artistId, genreId) VALUES (?, ?, ?, ?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Album (albumName, albumInfo, releaseDate, artistId, genreId) VALUES (?, ?, ?, ?, ?)");
        }
    }

    /**
     * deleteAlbum method of the Album class.
     * Deletes the given album data where the albumId is bound
     * via tha value inside the ArrayList of Strings.
     * This value is retrieved based on which object the method is
     * called on.
     * Executes using the setData method.
     * This method will specifically work only when deleting ALL the information for said album
     * id
     * Only admins have the permissions to use this method. General users should not
     * be allowed to delete items directly from the database.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAlbum(User user) throws AlinityException {
        try {
            if(user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(this.getAlbumId()));
                String deleteStmt = "DELETE FROM Album WHERE albumId = ?";
                return AlinityMain.alinityDB.setData(deleteStmt, info);
            } else System.out.println("You do not have the correct permissions to use this function. Please contact an administrator."); return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Album WHERE albumId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Album WHERE albumId = ?");
        }
    }

    /**
     * printAlbum method of the Award class.
     * Print values for the current awardId,
     * awardName, information about the award,
     * and the id of the artist (will be changed
     * later to represent the artist itself).
     */
    public void printAlbum() {
        System.out.println("\nID: " + this.albumId);
        System.out.println("Name: " + this.albumName);
        System.out.println("Info: " + this.albumInfo);
        System.out.println("Release Date: " + this.releaseDate);
        System.out.println("Artist ID: " + this.artistId);
        System.out.println("Genre ID: " + this.genreId);
    }
}
