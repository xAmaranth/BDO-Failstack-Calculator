import javax.swing.*;
import java.awt.*;

public class marketPriceView extends JPanel {

    public marketPriceView() {
        setBackground(Color.DARK_GRAY);

        setSize(608, 375);
        add(new JLabel(new ImageIcon("blackStoneArmor.png")));

    }

    @Override
    public String toString() {
        return "marketplace";
    }
}
