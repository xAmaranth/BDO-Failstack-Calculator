import javax.swing.*;
import java.awt.*;

public class marketPriceView extends JPanel {

    public marketPriceView() {
        super(new GridLayout(6, 2, 10, 8));
        setBackground(Color.DARK_GRAY);
        setSize(608, 375);

        addMarketPriceWidgets();
    }

    private void addMarketPriceWidgets() {
        add(new MarketPriceWidget(new ImageIcon("blackStoneArmor.png"), "Black Stone (Armor)", 200000L));
        add(new MarketPriceWidget(new ImageIcon("blackStoneWeapon.png"), "Black Stone (Weapon)", 230000L));
        add(new MarketPriceWidget(new ImageIcon("concentratedArmor.png"), "Concentrated Black Stone (Armor)", 2000000L));
        add(new MarketPriceWidget(new ImageIcon("concentratedWeapon.png"), "Concentrated Black Stone (Weapon)", 3000000L));
    }

    @Override
    public String toString() {
        return "marketplace";
    }
}
