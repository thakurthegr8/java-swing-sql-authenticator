import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.ResultSet;

class SignUpPage extends JFrame {
    static boolean signedIn = false;
    CustomFieldPanel username;
    CustomFieldPanel name;
    CustomFieldPanel pwd;
    ThemeButton submit;
    ColumnLayout layout;
    Database db;

    SignUpPage() {
        setSize(400, 400);
        setVisible(true);
        setResizable(false);
        setLayout(new GridLayout(1, 1));
        layout = new ColumnLayout();
        name = new CustomFieldPanel("Name : ", "name");
        username = new CustomFieldPanel("Username : ", "Username");
        pwd = new CustomFieldPanel("Password : ", "pwd");
        submit = new ThemeButton("Sign up",new Color(0x0057ff));
        layout.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        layout.setBackground(Color.white);
        layout.setBorder(new EmptyBorder(0, 0, 50, 0));
        layout.addComponent(new Heading("Sign Up",30));
        layout.addComponent(name);
        layout.addComponent(username);
        layout.addComponent(pwd);
        layout.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(submit));
        add(layout, BorderLayout.CENTER);
        submit.addActionListener(e -> {
            String password = new String(pwd.pField.getPassword()).trim();
            String usernameValue = username.field.getText();
            String nameValue = name.field.getText();
            String query = String.format("insert into users (name, username,pwd) values ('%s','%s','%s');",
                    nameValue, usernameValue, password);
            if (nameValue.length() == 0) {
                this.popup("Name must not be empty");
            } else if (usernameValue.length() == 0) {
                this.popup("Username must not be empty");
            } else if (password.length() == 0) {
                this.popup("Password must not be empty");
            } else {
                try {
                    db = new Database();
                    db.connect();
                    ResultSet rs = db.runQuery(query);
                    this.popup("Account Successfully created");
                    dispose();
                } catch (Exception sqlException) {
                    if (sqlException.getMessage().contains("Duplicate")) {
                        this.popup("User already exists");
                    } else {
                        sqlException.printStackTrace();
                    }
                } finally {
                    try {
                        db.connection.close();
                        System.out.println("Successfully closed database connection");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    void popup(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}