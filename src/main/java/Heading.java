import javax.swing.*;
import java.awt.*;

public class Heading extends JLabel {
    Heading(String value,int size){
        setFont(new Font("product sans",Font.BOLD,size));
        setText(value);
    }
}
