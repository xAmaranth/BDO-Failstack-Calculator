package Model.Items;

import Model.CostTracker;
import Model.Failstack;
import Model.SuccessRateCalculator;

import javax.swing.*;

public class Reblaith14 extends Item {

    public Reblaith14() {
        name = "+14 Reblaith Gloves";
        // TODO: get a proper image for the +14 Reblaith
        image = new ImageIcon("reblaith.png");
        value = 0L;
    }

    /**
     * Calculates the cost of using a +14 Reblaith Glove to increase a failstack by 1.
     * <p>
     * Assumes that the attempt failing is always the desirable outcome.
     *
     * @param currentFailstack  The Failstack used to click the +14 Reblaith Gloves
     * @return                  The cost of increasing a failstack by 1 using +14 Reblaith Gloves.
     */
    public static Long CostToClick(Failstack currentFailstack) {
        long blackStoneCost = CostTracker.getCost("Black Stone (Armor)");
        long reblaithCost = CostTracker.getCost("Reblaith Gloves");

        double chanceOfSuccess = SuccessRateCalculator.getReblaithRate(currentFailstack.getFailstack());
        double chanceOfFail = 1 - chanceOfSuccess;
        double expectedNumberOfClicks = 1 / chanceOfFail;

        double costOfFail = blackStoneCost + reblaithCost / 2.0;
        double costOfSuccess = blackStoneCost + 100000 + currentFailstack.getValue();

        double totalCost = expectedNumberOfClicks * (costOfFail * chanceOfFail + costOfSuccess * chanceOfSuccess);
        return Math.round(totalCost);
    }
}
