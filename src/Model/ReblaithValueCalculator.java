package Model;

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

}