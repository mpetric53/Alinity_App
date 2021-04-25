import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;

/**
 * @author Lucija Filipovic
 * @author Mislav Rukonic
 * @author Sven Slivar
 * @author Matej Petric
 */

/**
 * AlinityCOntroller class. Here, most perfromances
 * with the GUI are handled and called to display
 * information to the user. Handles login/signup
 * business, adding to saved songs, artists, and
 * albums. Calls the start method with a default
 * parameter.
 */
public class AlinityController {
    /**
     * private variables of the Alinity controller class.
     */
   private LogInGUI login = new LogInGUI();
   private SignUpGUI signup = new SignUpGUI();
   private SearchGUI search = new SearchGUI();
   private User user;
   private String username;
   private String password;

   private String signName;
   private String signPassword;
   private Date signBirthday;
   private String signEmail;
   private String query;

   public static int counter = 0;


    public AlinityController() throws AlinityException {
        start();
    }

    /**
     * logging method of the AlinityController class
     * Handles login of the user.
     *
     * @throws AlinityException
     */
    public void logging() throws AlinityException {
        username = login.getjTextField1().getText();
        password = login.getPass().getText();
        user = new User();
        user.login(username, password);
    }

    /**
     * signingUp method of the AlinityController class
     * Handles signup of the user.
     *
     * @throws AlinityException
     */
    public void signingUp() throws AlinityException{
        System.out.println("a");
        signName = signup.getjTextField1().getText();
        signEmail = signup.getjTextField2().getText();
        signBirthday = Date.valueOf(signup.getjTextField3().getText());
        signPassword = signup.getjTextField4().getText();
        user = new User();
        user.signUp(signName,signPassword,signBirthday,signEmail);
        System.out.println(signName + signEmail+ signBirthday+signPassword);

    }

    /**
     * search method of the AlinityController class.
     * Handles searching through a database, all actions
     * can be performed in one search bar.
     *
     * @throws AlinityException
     */
    public void search() throws AlinityException {
        Album album = new Album();
        Song song = new Song();
        Artist artist = new Artist();
        query = search.getjTextField1().getText();
        ArrayList<ArrayList<String>> searchAlbum = album.selectAlbum(user, query);
        album.handleSelectAlbum(searchAlbum, search, user, album);
        ArrayList<ArrayList<String>> searchSong = song.selectSong(user, query);
        song.selectSongHandler(searchSong, search, user, song);
        ArrayList<ArrayList<String>> searchArtist = artist.selectArtist(user, query);
        artist.selectArtistHandler(searchArtist, search, user, artist);
    }

    /**
     * showSavedSongs method of the AlinityController class.
     * Handles displaying saved songs (stored within song class)
     *
     * @throws AlinityException
     */
    public void showSavedSongs() throws AlinityException {
        SavedSongs savedSongs = new SavedSongs();
        ArrayList<ArrayList<String>> searchSavedSongs = savedSongs.selectSavedSongs(user);
        savedSongs.selectSavedSongsHandler(searchSavedSongs, savedSongs);
    }

    /**
     * showSavedAlbums method of the AlinityController class.
     * Handles displaying saved al;bums (stored within album class)
     *
     * @throws AlinityException
     */
    public void showSavedAlbums() throws AlinityException {
        SavedAlbums savedAlbums = new SavedAlbums();
        ArrayList<ArrayList<String>> searchSavedAlbums = savedAlbums.selectSavedAlbums(user);
        savedAlbums.selectSavedAlbumsHandler(searchSavedAlbums, savedAlbums);
    }

    /**
     * showSavedArtists method of the AlinityController class.
     * Handles displaying saved artists (stored within artist class)
     *
     * @throws AlinityException
     */
    public void showSavedArtists() throws AlinityException {
        SavedArtists savedArtists = new SavedArtists();
        ArrayList<ArrayList<String>> searchSavedArtists = savedArtists.selectSavedArtists(user);
        savedArtists.selectSavedArtistsHandler(searchSavedArtists, savedArtists);
    }

    /**
     * start method of the AlinityController class.
     * Called with a default constructor in AlinityMain.
     * Allows action to be performed.
     */
    public void start(){

        this.login.getjButton1().addActionListener((ActionEvent e) -> {
            try {
                logging();
                if(user.authenticate(username, password)){
                    login.setVisible(false);
                    search.setVisible(true);
                }else System.out.println("User not authenticated");
            } catch (AlinityException alinityException) {
                alinityException.printStackTrace();
            }
        });

        this.signup.getjButton1().addActionListener((ActionEvent ae) -> {
            try {
                signingUp();
                if(user.login(signName, signPassword)){
                    signup.setVisible(false);
                    search.setVisible(true);
                }else System.out.println("User not authenicated");
            } catch (AlinityException alinityException) {
                alinityException.printStackTrace();
            }
        });

        this.login.getJLabel2().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                login.setVisible(false);
                signup.setVisible(true);
            }
        });

        this.login.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    AlinityMain.alinityDB.close();
                } catch (AlinityException alinityException) {
                    alinityException.printStackTrace();
                }
            }
        });

        this.signup.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    AlinityMain.alinityDB.close();
                } catch (AlinityException alinityException) {
                    alinityException.printStackTrace();
                }
            }
        });

        this.search.getjTextField1().addActionListener((ActionEvent e) -> {
            try {
                search();
                counter++;
            } catch (AlinityException alinityException) {
                alinityException.printStackTrace();
            }
        });

        this.search.getjButton2().addActionListener((ActionEvent e) -> {
            try {
                showSavedSongs();
            } catch (AlinityException alinityException) {
                alinityException.printStackTrace();
            }
        });

        this.search.getjButton1().addActionListener((ActionEvent e) -> {
            try {
                showSavedAlbums();
            } catch (AlinityException alinityException) {
                alinityException.printStackTrace();
            }
        });

        this.search.getjButton3().addActionListener((ActionEvent e) -> {
            try {
                showSavedArtists();
            } catch (AlinityException alinityException) {
                alinityException.printStackTrace();
            }
        });
    }
}
