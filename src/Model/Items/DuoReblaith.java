package Model.Items;

import Model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class DuoReblaith extends Item {

    private final LowestCostHash lowestCost;
    private ReblaithRoute finalRoute;

    public DuoReblaith() {
        lowestCost = new LowestCostHash();

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

    public static Long costToClick(Failstack failstack) {
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

    private Long calculateValue() {
        ArrayList<ReblaithRoute> frontier = new ArrayList<>();
        frontier.add(new ReblaithRoute());

        while(!frontier.isEmpty()){
            ReblaithRoute currentRoute = frontier.remove(0);

            double currentReblaithCount = currentRoute.getExpectedNumberOfReblaiths();
            if (currentReblaithCount >= 1.0){
                finalRoute = currentRoute;
                return currentRoute.getFailstack().getValue();
            }

            //TODO: How can I prune the search tree to optimize the search?

            ArrayList<ReblaithRoute> newReblaithRoutes = iterate(currentRoute);
            frontier.addAll(newReblaithRoutes);
            Collections.sort(frontier);
        }

        return 0L;

    }

    private ArrayList<ReblaithRoute> iterate(ReblaithRoute currentReblaithRoute) {
        ArrayList<ReblaithRoute> newReblaithRoutes = new ArrayList<>();

        newReblaithRoutes.add(clickReblaith14(currentReblaithRoute));
        newReblaithRoutes.add(clickPriReblaith(currentReblaithRoute));

        return newReblaithRoutes;
    }

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
