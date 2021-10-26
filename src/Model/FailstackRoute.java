package Model;

import java.util.ArrayList;

public class FailstackRoute implements Comparable<FailstackRoute>{

    private final ArrayList<Integer> route;
    private long value;
    private int currentFailstack;
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
        route = currentFailstackRoute.getRoute();

        getCosts();
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public int getCurrentFailstack() {
        return currentFailstack;
    }

    public void setCurrentFailstack(int currentFailstack) {
        this.currentFailstack = currentFailstack;
    }

    public ArrayList<Integer> getRoute() {
        return route;
    }

    public void addToRoute(Integer newClick){
        route.add(newClick);
    }

    private void getCosts(){
        blackStoneCost = CostTracker.getCost("Black Stone (Armor)");
        concentratedBlackStoneCost = CostTracker.getCost("Concentrated Black Stone (Armor)");
        reblaithCost = CostTracker.getCost("Reblaith Gloves");
    }

    public ArrayList<FailstackRoute> iterate(){
        ArrayList<FailstackRoute> next = new ArrayList<>();

        next.add(clickReblaith14());

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
        reblaithRoute.addToRoute(1);

        return reblaithRoute;
    }

    @Override
    public int compareTo(FailstackRoute otherFailstackRoute) {
       return Long.compare(value, otherFailstackRoute.getValue());
    }
}
