package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FailstackCalculator {

    private final int targetFailstack;
    private final HashMap<Integer, Long> lowestCost;

    private ArrayList<Integer> finalRoute;
    private long finalCost;

    public FailstackCalculator(int targetFailstack) {
        this.targetFailstack = targetFailstack;
        lowestCost = new HashMap<>();
    }

    public ArrayList<Integer> getFinalRoute() {
        return finalRoute;
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
