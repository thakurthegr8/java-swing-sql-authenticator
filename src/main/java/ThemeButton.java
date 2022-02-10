import javax.swing.*;
import java.awt.*;


public class ThemeButton extends JButton {
    ThemeButton(String text, Color color){
        setFont(new Font("IBM Plex Sans",Font.BOLD,14));
        setText(text);
        setBackground(color);
        setForeground(Color.white);
        setFocusable(false);
    }
}
