package Model.Items;

import Model.CostTracker;
import Model.LowestCostHash;

import javax.swing.*;

public class TriReblaith extends Item {

    private final LowestCostHash lowestCost;

    public TriReblaith() {
        lowestCost = new LowestCostHash();

        name = "TRI Reblaith Gloves";
        image = new ImageIcon("triReblaith.png");

        if (CostTracker.hasEntry(name)) {
            value = CostTracker.getCost(name);
        } else {
            //value = calculateValue();
            CostTracker.addItem(this);
        }
    }

}
