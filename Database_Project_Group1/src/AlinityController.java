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
        //this.view.getjButton1().addActionListener();
    }
}
