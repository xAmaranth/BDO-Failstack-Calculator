import javax.swing.*;
import java.awt.*;

public class enhancementView extends JPanel {

    public enhancementView() {
        super();
        setBackground(Color.DARK_GRAY);

        setSize(608, 375);
        add(new EnhancementSelectionWidget(new ImageIcon("penWeapon.png"), "Boss Weapon", "PEN (V)"));
    }

    @Override
    public String toString() {
        return "enhancement";
    }

}
