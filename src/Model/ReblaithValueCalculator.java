package Model;

import Model.Items.PriReblaith;
import Model.Items.Reblaith14;

import java.util.ArrayList;
import java.util.Collections;

public class ReblaithValueCalculator {

    public long calculateValue(Iterator iterator) {
        LowestCostHash lowestCost = new LowestCostHash();
        ArrayList<ReblaithRoute> frontier = new ArrayList<>();
        frontier.add(new ReblaithRoute());

        while(!frontier.isEmpty()){
            ReblaithRoute currentRoute = frontier.remove(0);

            double currentReblaithCount = currentRoute.getExpectedNumberOfReblaiths();
            if (currentReblaithCount >= 1.0){
                return currentRoute.getFailstack().getValue();
            }

            if (!lowestCost.isCheapest(currentRoute.getFailstack(), currentRoute.getExpectedNumberOfReblaiths())) {
                continue;
            }

            ArrayList<ReblaithRoute> newReblaithRoutes = iterator.iterate(currentRoute);
            frontier.addAll(newReblaithRoutes);
            Collections.sort(frontier);
        }

        return 0L;

    }

    public ReblaithRoute clickReblaith14(ReblaithRoute currentReblaithRoute) {
        ReblaithRoute reblaith14Route = new ReblaithRoute(currentReblaithRoute);

        double totalCost = Reblaith14.CostToClick(currentReblaithRoute.getFailstack());

        reblaith14Route.addToRoute(
                new Failstack(currentReblaithRoute.getFailstack()).increment(1, Math.round(totalCost)),
                new Reblaith14(),
                0
        );

        return reblaith14Route;
    }

    public ReblaithRoute clickPriReblaith(ReblaithRoute currentReblaithRoute) {
        ReblaithRoute priReblaithRoute = new ReblaithRoute(currentReblaithRoute);

        double totalCost = PriReblaith.costToClick(currentReblaithRoute.getFailstack());

        priReblaithRoute.addToRoute(
                new Failstack(currentReblaithRoute.getFailstack()).increment(2, Math.round(totalCost)),
                new PriReblaith(),
                0
        );

        return priReblaithRoute;
    }

}
