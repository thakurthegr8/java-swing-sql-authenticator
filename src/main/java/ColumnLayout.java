import javax.swing.*;
import java.awt.*;

public class ColumnLayout extends JPanel {
    ColumnLayout(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(Color.white);
    }
    void addComponent(JComponent component){
        add(component);
    }
}
