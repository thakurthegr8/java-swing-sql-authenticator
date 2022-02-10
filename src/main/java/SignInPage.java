import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.ResultSet;

class SignInPage extends JFrame {
    static boolean signedIn = false;
    CustomFieldPanel username;
    CustomFieldPanel pwd;
    JButton submit;
    ColumnLayout layout;

    SignInPage() {
        setSize(400, 400);
        setVisible(true);
        setResizable(false);
        setLayout(new GridLayout(1, 1));
        layout = new ColumnLayout();
        username = new CustomFieldPanel("Username : ", "Username");
        pwd = new CustomFieldPanel("Password : ", "pwd");
        submit = new ThemeButton("Sign in",new Color(0x0057ff));
        layout.setBorder(new EmptyBorder(0, 0, 130, 0));
        layout.addComponent(new Heading("Sign in",30));
        layout.addComponent(username);
        layout.addComponent(pwd);
        layout.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(submit));
        layout.setBackground(Color.white);
        add(layout, BorderLayout.CENTER);
        submit.addActionListener(e -> {
            String password = new String(pwd.pField.getPassword()).trim();
            String usernameValue = username.field.getText();
            String checkIfExistsQuery = String.format("select exists(select username from users where username = '%s') as dataExists;",
                    usernameValue);
            String authUserQuery = String.format("select * from users where username = '%s' and pwd = '%s';",
                    usernameValue, password);
            if (usernameValue.length() == 0) {
                this.popup("Username must not be empty");
            } else if (password.length() == 0) {
                this.popup("password must not be empty");
            } else {
                boolean checkIfAccountExists = false;
                Database dbCheck = new Database();
                try {
                    dbCheck.connect();
                    ResultSet rsCheck = dbCheck.runQuery(checkIfExistsQuery);
                    while (rsCheck.next()) {
                        if (rsCheck.getInt("dataExists") == 1) {
                            try {
                                rsCheck = dbCheck.runQuery(authUserQuery);
                                while (rsCheck.next()) {
                                    String queryUsername = rsCheck.getNString("username");
                                    String queryPassword = rsCheck.getNString("pwd");
                                    if (queryUsername.equals(usernameValue) && queryPassword.equals(password)) {
                                        signedIn = true;
                                    }
                                }
                                if (signedIn) {
                                    this.popup("Signed in successfully");
                                } else {
                                    this.popup("Invalid username/password");
                                }
                            } catch (Exception isValidException) {
                                isValidException.printStackTrace();
                                System.out.println(isValidException.getMessage());
                            }
                        } else {
                            this.popup("Account does not exists");
                        }
                    }

                } catch (Exception checkException) {
                    checkException.printStackTrace();
                    System.out.println(checkException.getMessage());
                } finally {
                    try {
                        dbCheck.connection.close();
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