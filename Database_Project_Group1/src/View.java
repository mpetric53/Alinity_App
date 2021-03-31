import javax.swing.*;

public class View {

    public View(){

        JFrame frame = new JFrame("Alinity");
        JPanel panel = new JPanel();
        JTextArea area = new JTextArea(100, 100);
        JButton btn = new JButton("Do not press me!");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);

        panel.add(area);
        panel.add(btn);

        frame.add(panel);

        frame.setVisible(true);

    }
}
