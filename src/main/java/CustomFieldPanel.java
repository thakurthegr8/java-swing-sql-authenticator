import javax.swing.*;
import java.awt.*;

public class CustomFieldPanel extends JPanel {
    JLabel label;
    JTextField field;
    JPasswordField pField;

    CustomFieldPanel(String value, String type) {
        label = new JLabel(value);
        label.setFont(new Font("IBM Plex Sans", Font.BOLD, 14));
        add(label);
        if (type == "pwd") {
            pField = new JPasswordField();
            pField.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            pField.setFont(new Font("IBM Plex Sans", Font.BOLD, 14));
            add(pField);
        } else {
            field = new JTextField();
            field.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            field.setFont(new Font("IBM Plex Sans", Font.BOLD, 14));

            add(field);
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }

}
