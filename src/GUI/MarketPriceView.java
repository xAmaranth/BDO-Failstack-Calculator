package GUI;

import javax.swing.*;
import java.awt.*;

public class MarketPriceView extends JPanel {

    public MarketPriceView() {
        super(new GridLayout(6, 2, 10, 8));
        setBackground(Color.DARK_GRAY);
        setSize(608, 375);

        addMarketPriceWidgets();
    }

    private void addMarketPriceWidgets() {
        add(new MarketPriceWidget(new ImageIcon("blackStoneArmor.png"), "Black Stone (Armor)", 200000L));
        add(new MarketPriceWidget(new ImageIcon("blackStoneWeapon.png"), "Black Stone (Weapon)", 230000L));
        add(new MarketPriceWidget(new ImageIcon("hardCrystal.png"), "Hard Black Crystal Shard", 1600000L));
        add(new MarketPriceWidget(new ImageIcon("sharpCrystal.png"), "Sharp Black Crystal Shard", 2000000L));
        add(new MarketPriceWidget(new ImageIcon("concentratedArmor.png"), "Concentrated Black Stone (Armor)", 2000000L));
        add(new MarketPriceWidget(new ImageIcon("concentratedWeapon.png"), "Concentrated Black Stone (Weapon)", 3000000L));
        add(new MarketPriceWidget(new ImageIcon("memoryFragment.png"), "Memory Fragment", 2350000L));
        add(new MarketPriceWidget(new ImageIcon("accessory.png"), "Accessory to Enhance, If Applicable", 40000000L));
        add(new MarketPriceWidget(new ImageIcon("reblaith.png"), "Model.Reblaith Gloves", 12900L));
        add(new MarketPriceWidget(new ImageIcon("cronStone.png"), "Cron Stones", 1000000L));
    }

    @Override
    public String toString() {
        return "marketplace";
    }
}
