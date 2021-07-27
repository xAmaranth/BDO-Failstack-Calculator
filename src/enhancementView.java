import javax.swing.*;
import java.awt.*;

public class enhancementView extends JPanel {

    public enhancementView() {
        setBackground(Color.DARK_GRAY);

        setSize(608, 375);
        add(new JLabel(new ImageIcon("concentratedWeapon.png")));

    }

    @Override
    public String toString() {
        return "enhancement";
    }

}
