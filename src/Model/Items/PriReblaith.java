package Model.Items;

import Model.CostTracker;
import Model.Failstack;
import Model.SuccessRateCalculator;

import javax.swing.*;

public class PriReblaith extends Item{

    public PriReblaith() {
        image = new ImageIcon("priReblaith.png");
        name = "PRI Reblaith Gloves";
        value = 0L;
    }

    /**
     * Calculates the cost of clicking a PRI Reblaith given a Failstack, but does not calculate the appropriate cost
     * of the provided failstack, relying on a previous operation to do that first.
     * <p>
     * CostToClick is agnostic to whether the desired outcome is failing to build a Failstack or succeeding to build a
     * DUO Reblaith.
     *
     * @param currentFailstack  The Failstack used to click the PRI Reblaith Gloves.
     * @return                  The cost to click the PRI Reblaith Gloves given a Failstack
     */
    public static Long costToClick(Failstack currentFailstack){
        long concentratedBlackStoneCost = CostTracker.getCost("Concentrated Black Stone (Armor)");
        long reblaithCost = CostTracker.getCost("Reblaith Gloves");

        double chanceOfSuccess = SuccessRateCalculator.getPriToDuoRate(currentFailstack.getFailstack());
        double costOfSuccess = chanceOfSuccess * (concentratedBlackStoneCost + currentFailstack.getValue());
        double costOfFail = (1 - chanceOfSuccess) * (concentratedBlackStoneCost + reblaithCost);

        double totalCost = costOfSuccess + costOfFail;
        return Math.round(totalCost);
    }

}
