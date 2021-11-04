package Model;

import java.util.ArrayList;
import java.util.Collections;

public class ReblaithCalculator {

    private long calculateReblaithCost(){
        ArrayList<ReblaithRoute> frontier = new ArrayList<>();
        frontier.add(new ReblaithRoute());

        while(!frontier.isEmpty()){
            ReblaithRoute currentReblaithRoute = frontier.remove(0);

            double currentReblaithCount = currentReblaithRoute.getExpectedNumberOfReblaiths();
            long currentCost = currentReblaithRoute.getCost();

            if (currentReblaithCount >= 1.0){
                return currentCost;
            }

            ArrayList<ReblaithRoute> newReblaithRoutes = currentReblaithRoute.iterate();
            frontier.addAll(newReblaithRoutes);
            Collections.sort(frontier);
        }

        return 0;
    }

}
