package Model.Items;

import Model.*;

import javax.swing.*;
import java.util.ArrayList;

public class TriReblaith extends Item {

    private final ReblaithValueCalculator reblaithValueCalculator;

    public TriReblaith() {
        name = "TRI Reblaith Gloves";
        image = new ImageIcon("triReblaith.png");

        reblaithValueCalculator = new ReblaithValueCalculator();

        if (CostTracker.hasEntry(name)) {
            value = CostTracker.getCost(name);
        } else {
            value = calculateValue();
            CostTracker.addItem(this);
        }
    }

    public static long costToClick(Failstack failstack) {
        long concentratedBlackStoneCost = CostTracker.getCost("Concentrated Black Stone (Armor)");
        long repairCost = CostTracker.getCost("Reblaith Gloves");

        if (!CostTracker.hasEntry("TRI Reblaith Gloves")) {
            new TriReblaith();
        }
        long triReblaithCost = CostTracker.getCost("TRI Reblaith Gloves");

        double chanceOfSuccess = SuccessRateCalculator.getTriToTetRate(failstack.getFailstack());
        double costOfSuccess = chanceOfSuccess * (concentratedBlackStoneCost + failstack.getValue());
        double costOfFail = (1 - chanceOfSuccess) * (concentratedBlackStoneCost + triReblaithCost + repairCost);

        double totalCost = costOfSuccess + costOfFail;
        return Math.round(totalCost);
    }

    private long calculateValue() {
        return reblaithValueCalculator.calculateValue(this::iterate);
    }

    private ArrayList<ReblaithRoute> iterate(ReblaithRoute currentReblaithRoute) {
        ArrayList<ReblaithRoute> newReblaithRoutes = new ArrayList<>();

        newReblaithRoutes.add(reblaithValueCalculator.clickReblaith14(currentReblaithRoute));
        newReblaithRoutes.add(reblaithValueCalculator.clickPriReblaith(currentReblaithRoute));
        newReblaithRoutes.add(clickDuoReblaith(currentReblaithRoute));

        return newReblaithRoutes;
    }

    private ReblaithRoute clickDuoReblaith(ReblaithRoute currentReblaithRoute) {
        ReblaithRoute duoReblaithRoute = new ReblaithRoute(currentReblaithRoute);

        double chanceOfSuccess = SuccessRateCalculator.getDuoToTriRate(currentReblaithRoute.getFailstack().getFailstack());
        double totalCost = DuoReblaith.costToClick(currentReblaithRoute.getFailstack());

        duoReblaithRoute.addToRoute(
                new Failstack(currentReblaithRoute.getFailstack()).increment(3, Math.round(totalCost)),
                new DuoReblaith(),
                chanceOfSuccess
        );

        return duoReblaithRoute;
    }

}
