import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class welcome extends JFrame {
    ThemeButton signup;
    ThemeButton signin;
    JPanel panel;

    welcome() {
        signup = new ThemeButton("Sign Up",Color.black);
        signin = new ThemeButton("Sign In",new Color(0x0057ff));
        panel = new JPanel();
        setTitle("Welcome");
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new CardLayout());
        setSize(400, 400);
        setResizable(false);
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(new EmptyBorder(100, 50, 50, 50));
        panel.add(new Heading("Welcome",50));
        panel.add(signup);
        panel.add(signin);
        add(panel);
        signin.addActionListener(e -> {
            dispose();
            new SignInPage();
        });
        signup.addActionListener(e -> {
            dispose();
            new SignUpPage();
        });
    }
}


public class App {

    public static void main(String[] args) {
        new welcome();
    }
}
