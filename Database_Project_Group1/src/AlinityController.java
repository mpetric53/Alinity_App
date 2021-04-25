import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;

public class AlinityController {
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

    public AlinityController(LogInGUI login) throws AlinityException {
        this.login = login;
        start();
    }

    public void logging() throws AlinityException {
        username = login.getjTextField1().getText();
        password = login.getPass().getText();
        user = new User();
        user.login(username, password);
    }

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
//        if(searchAlbum. || searchArtist.get(0) = 0 || searchSong.isEmpty()+) {
//            JOptionPane.showMessageDialog(null, "No results found!",
//                    null, JOptionPane.ERROR_MESSAGE);
//        }
    }

    public void showSavedSongs() throws AlinityException {
        SavedSongs savedSongs = new SavedSongs();
        ArrayList<ArrayList<String>> searchSavedSongs = savedSongs.selectSavedSongs(user);
        savedSongs.selectSavedSongsHandler(searchSavedSongs, savedSongs);
    }

    public void showSavedAlbums() throws AlinityException {
        SavedAlbums savedAlbums = new SavedAlbums();
        ArrayList<ArrayList<String>> searchSavedAlbums = savedAlbums.selectSavedAlbums(user);
        savedAlbums.selectSavedAlbumsHandler(searchSavedAlbums, savedAlbums);
    }

    public void showSavedArtists() throws AlinityException {
        SavedArtists savedArtists = new SavedArtists();
        ArrayList<ArrayList<String>> searchSavedArtists = savedArtists.selectSavedArtists(user);
        savedArtists.selectSavedArtistsHandler(searchSavedArtists, savedArtists);
    }


    public void start(){

        //Action Listener for the button LogInGUI class
        this.login.getjButton1().addActionListener((ActionEvent e) -> {
            try {
                //Action Listener for the button SignUpGUI class

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
                //System.out.println("Uso sam");
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
