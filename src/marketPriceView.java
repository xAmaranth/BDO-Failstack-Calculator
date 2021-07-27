import javax.swing.*;
import java.awt.*;

public class marketPriceView extends JPanel {

    public marketPriceView() {
        setBackground(Color.DARK_GRAY);

        setSize(608, 375);
        add(new MarketPriceWidget(new ImageIcon("blackStoneArmor.png"), "Black Stone (Armor)", 200000L));

    }

    @Override
    public String toString() {
        return "marketplace";
    }
}
