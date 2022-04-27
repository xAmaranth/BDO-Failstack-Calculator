package Model.Items;

import Model.*;

import javax.swing.*;
import java.util.ArrayList;

public class DuoReblaith extends Item {

    private ReblaithRoute finalRoute;

    public DuoReblaith() {
        name = "DUO Reblaith Gloves";
        image = new ImageIcon("duoReblaith.png");

        //TODO: What if the cost of Black Stones are changed after the cost is first calculated?
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

        if (!CostTracker.hasEntry("DUO Reblaith Gloves")) {
            new DuoReblaith();
        }
        long duoReblaithCost = CostTracker.getCost("DUO Reblaith Gloves");

        double chanceOfSuccess = SuccessRateCalculator.getDuoToTriRate(failstack.getFailstack());
        double costOfSuccess = chanceOfSuccess * (concentratedBlackStoneCost + failstack.getValue());
        double costOfFail = (1 - chanceOfSuccess) * (concentratedBlackStoneCost + duoReblaithCost + repairCost);

        double totalCost = costOfSuccess + costOfFail;
        return Math.round(totalCost);
    }

    public ReblaithRoute getFinalRoute() {
        return finalRoute;
    }

    private long calculateValue() {
        ReblaithValueCalculator calculator = new ReblaithValueCalculator();
        return calculator.calculateValue(this::iterate);
    }

    private ArrayList<ReblaithRoute> iterate(ReblaithRoute currentReblaithRoute) {
        ArrayList<ReblaithRoute> newReblaithRoutes = new ArrayList<>();

        newReblaithRoutes.add(clickReblaith14(currentReblaithRoute));
        newReblaithRoutes.add(clickPriReblaith(currentReblaithRoute));

        return newReblaithRoutes;
    }

    //TODO: How to avoid retyping this same method for TRI, TET, and PEN Reblaith?
    private ReblaithRoute clickReblaith14(ReblaithRoute currentReblaithRoute) {
        ReblaithRoute reblaith14Route = new ReblaithRoute(currentReblaithRoute);

        double totalCost = Reblaith14.CostToClick(currentReblaithRoute.getFailstack());

        reblaith14Route.addToRoute(
                new Failstack(currentReblaithRoute.getFailstack()).increment(1, Math.round(totalCost)),
                new Reblaith14(),
                0
        );

        return reblaith14Route;
    }

    private ReblaithRoute clickPriReblaith(ReblaithRoute currentReblaithRoute) {
        ReblaithRoute priReblaithRoute = new ReblaithRoute(currentReblaithRoute);

        double chanceOfSuccess = SuccessRateCalculator.getPriToDuoRate(currentReblaithRoute.getFailstack().getFailstack());
        double totalCost = PriReblaith.costToClick(currentReblaithRoute.getFailstack());

        priReblaithRoute.addToRoute(
                new Failstack(currentReblaithRoute.getFailstack()).increment(2, Math.round(totalCost)),
                new PriReblaith(),
                chanceOfSuccess
        );

        return priReblaithRoute;
    }

}
