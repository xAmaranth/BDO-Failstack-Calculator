package Model;

import Model.Items.DuoReblaith;
import Model.Items.PriReblaith;
import Model.Items.Reblaith14;
import Model.Items.TriReblaith;

import java.util.ArrayList;
import java.util.Collections;

public class FailstackCalculator {

    private final int targetFailstack;
    private final LowestCostHash lowestCost;

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
        lowestCost = new LowestCostHash();
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

           if (!lowestCost.isCheapest(currentFailstack)) {
               continue;
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
        newEnhancementRoutes.add(clickDuoReblaith(currentEnhancementRoute));
        newEnhancementRoutes.add(clickTriReblaith(currentEnhancementRoute));

        return newEnhancementRoutes;
    }

    private EnhancementRoute clickReblaith14(EnhancementRoute currentEnhancementRoute) {
        EnhancementRoute reblaith14Route = new EnhancementRoute(currentEnhancementRoute);

        double totalCost = Reblaith14.CostToClick(currentEnhancementRoute.getFailstack());

        reblaith14Route.addToRoute(
                new Failstack(currentEnhancementRoute.getFailstack()).increment(1, Math.round(totalCost)),
                new Reblaith14()
        );

        return reblaith14Route;
    }

    private EnhancementRoute clickPriReblaith(EnhancementRoute currentEnhancementRoute) {
        EnhancementRoute priReblaithRoute = new EnhancementRoute(currentEnhancementRoute);

        double chanceOfSuccess = SuccessRateCalculator.getPriToDuoRate(currentEnhancementRoute.getFailstack().getFailstack());
        double expectedNumberOfClicks = 1 / (1 - chanceOfSuccess);
        double totalCost = expectedNumberOfClicks * PriReblaith.costToClick(currentEnhancementRoute.getFailstack());

        priReblaithRoute.addToRoute(
                new Failstack(currentEnhancementRoute.getFailstack()).increment(3, Math.round(totalCost)),
                new PriReblaith()
        );

        return priReblaithRoute;
    }

    private EnhancementRoute clickDuoReblaith(EnhancementRoute currentEnhancementRoute) {
        EnhancementRoute duoReblaithRoute = new EnhancementRoute(currentEnhancementRoute);

        double chanceOfSuccess = SuccessRateCalculator.getDuoToTriRate(currentEnhancementRoute.getFailstack().getFailstack());
        double expectedNumberOfClicks = 1 / (1 - chanceOfSuccess);
        double totalCost = expectedNumberOfClicks * DuoReblaith.costToClick(currentEnhancementRoute.getFailstack());

        duoReblaithRoute.addToRoute(
                new Failstack(currentEnhancementRoute.getFailstack()).increment(4, Math.round(totalCost)),
                new DuoReblaith()
        );

        return duoReblaithRoute;
    }

    private EnhancementRoute clickTriReblaith(EnhancementRoute currentEnhancementRoute) {
        EnhancementRoute triReblaithRoute = new EnhancementRoute(currentEnhancementRoute);

        double chanceOfSuccess = SuccessRateCalculator.getTriToTetRate(currentEnhancementRoute.getFailstack().getFailstack());
        double expectedNumberOfClicks = 1 / (1 - chanceOfSuccess);
        double totalCost = expectedNumberOfClicks * TriReblaith.costToClick(currentEnhancementRoute.getFailstack());

        triReblaithRoute.addToRoute(
                new Failstack(currentEnhancementRoute.getFailstack()).increment(5, Math.round(totalCost)),
                new TriReblaith()
        );

        return triReblaithRoute;
    }

}
