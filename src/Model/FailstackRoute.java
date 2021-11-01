package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FailstackRoute implements Comparable<FailstackRoute>{

    private final ArrayList<List<Long>> route;
    public ArrayList<List<Long>> getRoute() {
        return route;
    }
    public void addToRoute(Long newClick, Long currentValue){
        route.add(Arrays.asList(newClick, currentValue));
    }

    private long value;
    public long getValue() {
        return value;
    }
    public void setValue(long value) {
        this.value = value;
    }

    private int currentFailstack;
    public int getCurrentFailstack() {
        return currentFailstack;
    }
    public void setCurrentFailstack(int currentFailstack) {
        this.currentFailstack = currentFailstack;
    }

    private long blackStoneCost;
    private long concentratedBlackStoneCost;
    private long reblaithCost;

    public FailstackRoute() {
        value = 0;
        currentFailstack = 0;
        route = new ArrayList<>();

        getCosts();
    }
    public FailstackRoute(FailstackRoute currentFailstackRoute) {
        value = currentFailstackRoute.getValue();
        currentFailstack = currentFailstackRoute.getCurrentFailstack();
        route = new ArrayList<>();
        route.addAll(currentFailstackRoute.getRoute());

        getCosts();
    }

    private void getCosts(){
        blackStoneCost = CostTracker.getCost("Black Stone (Armor)");
        concentratedBlackStoneCost = CostTracker.getCost("Concentrated Black Stone (Armor)");
        reblaithCost = CostTracker.getCost("Reblaith Gloves");
    }

    public ArrayList<FailstackRoute> iterate(){
        ArrayList<FailstackRoute> next = new ArrayList<>();

        next.add(clickReblaith14());
        next.add(clickPriReblaith());

        return next;
    }

    private FailstackRoute clickReblaith14() {
        FailstackRoute reblaithRoute = new FailstackRoute(this);

        double chanceOfSuccess = SuccessRateCalculator.getReblaithRate(currentFailstack);
        double chanceOfFail = 1 - chanceOfSuccess;
        double expectedNumberOfClicks = 1 / chanceOfFail;

        double costOfFail = blackStoneCost + reblaithCost / 2.0;
        double costOfSuccess = blackStoneCost + 100000 + value;

        double totalCost = expectedNumberOfClicks * (costOfFail * chanceOfFail + costOfSuccess * chanceOfSuccess);

        reblaithRoute.setValue(value + Math.round(totalCost));
        reblaithRoute.setCurrentFailstack(currentFailstack + 1);
        reblaithRoute.addToRoute(1L, Math.round(totalCost));

        return reblaithRoute;
    }

    private FailstackRoute clickPriReblaith() {
        FailstackRoute priReblaithRoute = new FailstackRoute(this);

        double chanceOfSuccess = SuccessRateCalculator.getPriToDuoRate(currentFailstack);
        double chanceOfFail = 1 - chanceOfSuccess;
        double expectedNumberOfClicks = 1 / chanceOfFail;

        double costOfFail = concentratedBlackStoneCost + reblaithCost;
        double costOfSuccess = concentratedBlackStoneCost + value;

        double totalCost = expectedNumberOfClicks * (costOfFail * chanceOfFail + costOfSuccess * chanceOfSuccess);

        priReblaithRoute.setValue(value + Math.round(totalCost));
        priReblaithRoute.setCurrentFailstack(currentFailstack + 2);
        priReblaithRoute.addToRoute(2L, Math.round(totalCost));

        return priReblaithRoute;
    }

    @Override
    public int compareTo(FailstackRoute otherFailstackRoute) {
       return Long.compare(value, otherFailstackRoute.getValue());
    }
}
