import java.awt.event.ActionEvent;

public class AlinityController {
   private LogInGUI login = new LogInGUI();
   private ViewGUI view = new ViewGUI();
   private SignUpGUI signup = new SignUpGUI();

    public AlinityController(LogInGUI login, ViewGUI view, SignUpGUI signup){
        this.login = login;
        this.view = view;
        this.signup = signup;
        start();
    }


    public void start(){

        //Action Listener for the button LogInGUI class
        this.login.getjButton1().addActionListener((ActionEvent e) -> {
            System.out.println("--- LOGIN BUTTON ---");
        });


        //Action Listener for the button SignUpGUI class
        this.signup.getjButton1().addActionListener((ActionEvent e) -> {
            System.out.println("--- SIGNUP BUTTON ---");
        });


        //Action Listener for the button ViewGUI class
        this.view.getjButton1().addActionListener((ActionEvent e) -> {
            System.out.println("--- VIEW BUTTON 1 ---");
        });
        this.view.getjButton2().addActionListener((ActionEvent e) -> {
            System.out.println("--- VIEW BUTTON 2 ---");
        });
        this.view.getjButton3().addActionListener((ActionEvent e) -> {
            System.out.println("--- VIEW BUTTON 3 ---");
        });
        this.view.getjButton4().addActionListener((ActionEvent e) -> {
            System.out.println("--- VIEW BUTTON 4 ---");
        });
        this.view.getjButton5().addActionListener((ActionEvent e) -> {
            System.out.println("--- VIEW BUTTON 5 ---");
        });
        this.view.getjButton6().addActionListener((ActionEvent e) -> {
            System.out.println("--- VIEW BUTTON 6 ---");
        });
        this.view.getjButton7().addActionListener((ActionEvent e) -> {
            System.out.println("--- VIEW BUTTON 7 ---");
        });
        this.view.getjButton8().addActionListener((ActionEvent e) -> {
            System.out.println("--- VIEW BUTTON 8 ---");
        });
    }
}
