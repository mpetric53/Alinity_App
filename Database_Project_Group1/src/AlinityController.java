import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

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




    public AlinityController() {
        start();
    }

    public AlinityController(LogInGUI login){
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

    public void start(){

        //Action Listener for the button LogInGUI class
        this.login.getjButton1().addActionListener((ActionEvent e) -> {
            try {
                //Action Listener for the button SignUpGUI class
                this.signup.getjButton1().addActionListener((ActionEvent ae) -> {

                    try {
                        System.out.println("b");
                        signingUp();
                        if(user.login(signName, signPassword) == true){
                            signup.setVisible(false);
                            search.setVisible(true);

                        }else System.out.println("User not authenicated");


                    } catch (AlinityException alinityException) {
                        alinityException.printStackTrace();
                    }

                });

                logging();
                if(user.authenticate(username, password) == true){
                    login.setVisible(false);
                    search.setVisible(true);
                }else System.out.println("User not authenicated");


            } catch (AlinityException alinityException) {
                alinityException.printStackTrace();
            }
        });






        login.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                try {
                    AlinityMain.alinityDB.close();
                } catch (AlinityException alinityException) {
                    alinityException.printStackTrace();
                }
            }
        });

        signup.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                try {
                    AlinityMain.alinityDB.close();
                } catch (AlinityException alinityException) {
                    alinityException.printStackTrace();
                }
            }
        });





    }
}
