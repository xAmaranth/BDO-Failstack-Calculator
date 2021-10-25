package GUI;

import Model.Items.*;

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
        add(new MarketPriceWidget(new BlackStoneArmor()));
        add(new MarketPriceWidget(new BlackStoneWeapon()));
        add(new MarketPriceWidget(new HardCrystalShard()));
        add(new MarketPriceWidget(new SharpCrystalShard()));
        add(new MarketPriceWidget(new ConcentratedArmor()));
        add(new MarketPriceWidget(new ConcentratedWeapon()));
        add(new MarketPriceWidget(new MemoryFragment()));
        add(new MarketPriceWidget(new Accessory()));
        add(new MarketPriceWidget(new Reblaith()));
        add(new MarketPriceWidget(new CronStone()));
    }

    @Override
    public String toString() {
        return "marketplace";
    }
}
