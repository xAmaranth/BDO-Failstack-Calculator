package Model;

import java.util.*;

public class FailstackCalculator {

    private final int targetFailstack;
    private final HashMap<Integer, Long> lowestCost;

    private ArrayList<List<Long>> finalRoute;
    private long finalCost;

    public FailstackCalculator(int targetFailstack) {
        this.targetFailstack = targetFailstack;
        lowestCost = new HashMap<>();
    }

    public ArrayList<List<Long>> getFinalRoute() {
        ArrayList<List<Long>> parsedRoute = new ArrayList<>();

        long currentMethod = finalRoute.get(0).get(0);
        long numberOfSteps = 0;
        long costOfSteps = 0;

        for (List<Long> step : finalRoute){
            if (step.get(0) == currentMethod){
                numberOfSteps++;
                costOfSteps += step.get(1);
            } else {
                parsedRoute.add(Arrays.asList(currentMethod - 1, numberOfSteps, costOfSteps));

                currentMethod = step.get(0);
                numberOfSteps = 1;
                costOfSteps = step.get(1);
            }
        }

        parsedRoute.add(Arrays.asList(currentMethod - 1, numberOfSteps, costOfSteps));

        return parsedRoute;
    }

    public long getFinalCost() {
        return finalCost;
    }

    public void calculateFailstackValue(){
        FailstackRoute failstackZero = new FailstackRoute();

        ArrayList<FailstackRoute> frontier = new ArrayList<>();
        frontier.add(failstackZero);

        while (!frontier.isEmpty()){
           FailstackRoute currentFailstackRoute = frontier.remove(0);

           int currentFailstack = currentFailstackRoute.getCurrentFailstack();
           long currentValue = currentFailstackRoute.getValue();

            if (currentFailstack >= targetFailstack){
                finalRoute = currentFailstackRoute.getRoute();
                finalCost = currentValue;
                break;
            }

           if (!lowestCost.containsKey(currentFailstack)){
               lowestCost.put(currentFailstack, currentValue);
           } else {
               if (currentValue >= lowestCost.get(currentFailstack)){
                   continue;
               } else {
                   lowestCost.replace(currentFailstack, currentValue);
               }
           }

           ArrayList<FailstackRoute> newFailstackRoutes = currentFailstackRoute.iterate();
           frontier.addAll(newFailstackRoutes);
           Collections.sort(frontier);
        }

    }

}
