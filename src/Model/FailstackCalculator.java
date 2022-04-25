package Model;

import Model.Items.PriReblaith;
import Model.Items.Reblaith14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FailstackCalculator {

    private final int targetFailstack;
    private final HashMap<Integer, Long> lowestCost;

    private EnhancementRoute finalRoute;
    public EnhancementRoute getFinalRoute() {
        return finalRoute;
    }

    private long finalCost;
    public long getFinalCost() {
        return finalCost;
    }

    public FailstackCalculator(int targetFailstack) {
        this.targetFailstack = targetFailstack;
        lowestCost = new HashMap<>();
    }

    public void calculateFailstackValue(){
        ArrayList<EnhancementRoute> frontier = new ArrayList<>();
        frontier.add(new EnhancementRoute());

        while (!frontier.isEmpty()){
           EnhancementRoute currentEnhancementRoute = frontier.remove(0);

           Failstack currentFailstack = currentEnhancementRoute.getFailstack();

            if (currentFailstack.getFailstack() >= targetFailstack){
                finalRoute = currentEnhancementRoute;
                finalCost = currentFailstack.getValue();
                break;
            }

           if (!lowestCost.containsKey(currentFailstack.getFailstack())){
               lowestCost.put(currentFailstack.getFailstack(), currentFailstack.getValue());
           } else {
               if (currentFailstack.getValue() >= lowestCost.get(currentFailstack.getFailstack())){
                   continue;
               } else {
                   lowestCost.replace(currentFailstack.getFailstack(), currentFailstack.getValue());
               }
           }

           ArrayList<EnhancementRoute> newFailstackRoutes = iterate(currentEnhancementRoute);
           frontier.addAll(newFailstackRoutes);
           Collections.sort(frontier);
        }

    }

    private ArrayList<EnhancementRoute> iterate(EnhancementRoute currentEnhancementRoute) {
        ArrayList<EnhancementRoute> newEnhancementRoutes = new ArrayList<>();

        newEnhancementRoutes.add(clickReblaith14(currentEnhancementRoute));
        newEnhancementRoutes.add(clickPriReblaith(currentEnhancementRoute));

        return newEnhancementRoutes;
    }

    private EnhancementRoute clickReblaith14(EnhancementRoute currentEnhancementRoute) {
        EnhancementRoute reblaith14Route = new EnhancementRoute(currentEnhancementRoute);

        double totalCost = Reblaith14.CostToClick(currentEnhancementRoute.getFailstack());

        reblaith14Route.AddToRoute(
                new Failstack(currentEnhancementRoute.getFailstack()).Increment(1, Math.round(totalCost)),
                new Reblaith14()
        );

        return reblaith14Route;
    }

    private EnhancementRoute clickPriReblaith(EnhancementRoute currentEnhancementRoute) {
        EnhancementRoute priReblaithRoute = new EnhancementRoute(currentEnhancementRoute);

        double chanceOfSuccess = SuccessRateCalculator.getPriToDuoRate(currentEnhancementRoute.getFailstack().getFailstack());
        double expectedNumberOfClicks = 1 / (1 - chanceOfSuccess);
        double totalCost = expectedNumberOfClicks * PriReblaith.CostToClick(currentEnhancementRoute.getFailstack());

        priReblaithRoute.AddToRoute(
                new Failstack(currentEnhancementRoute.getFailstack()).Increment(2, Math.round(totalCost)),
                new PriReblaith()
        );

        return priReblaithRoute;
    }

}
