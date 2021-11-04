package Model;

import java.util.ArrayList;

public class ReblaithRoute implements Comparable<ReblaithRoute>{

    private double expectedNumberOfReblaiths;
    private long cost;
    private int currentFailstack;
    private long blackStoneCost;
    private long concentratedBlackStoneCost;
    private long reblaithCost;

    public ReblaithRoute() {
        expectedNumberOfReblaiths = 0;
        cost = 0;
        currentFailstack = 0;

        getMarketCosts();
    }
    public ReblaithRoute(ReblaithRoute currentReblaithRoute){
        expectedNumberOfReblaiths = currentReblaithRoute.getExpectedNumberOfReblaiths();
        cost = currentReblaithRoute.getCost();
        currentFailstack = currentReblaithRoute.getCurrentFailstack();

        getMarketCosts();
    }

    public double getExpectedNumberOfReblaiths() {
        return expectedNumberOfReblaiths;
    }

    public void setExpectedNumberOfReblaiths(double expectedNumberOfReblaiths) {
        this.expectedNumberOfReblaiths = expectedNumberOfReblaiths;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public int getCurrentFailstack() {
        return currentFailstack;
    }

    public void setCurrentFailstack(int currentFailstack) {
        this.currentFailstack = currentFailstack;
    }

    private void getMarketCosts() {
        blackStoneCost = CostTracker.getCost("Black Stone (Armor)");
        concentratedBlackStoneCost = CostTracker.getCost("Concentrated Black Stone (Armor)");
        reblaithCost = CostTracker.getCost("Reblaith Gloves");
    }

    public ArrayList<ReblaithRoute> iterate(){
        ArrayList<ReblaithRoute> next = new ArrayList<>();

        next.add(clickReblaith14());
        next.add(clickPriReblaith());

        return next;
    }

    private ReblaithRoute clickReblaith14() {
        ReblaithRoute reblaith14Route = new ReblaithRoute(this);

        double chanceOfSuccess = SuccessRateCalculator.getReblaithRate(currentFailstack);
        double chanceOfFail = 1 - chanceOfSuccess;
        double expectedNumberOfClicks = 1 / chanceOfFail;

        double costOfFail = blackStoneCost + reblaithCost / 2.0;
        double costOfSuccess = blackStoneCost + 100000 + cost;

        double totalCost = expectedNumberOfClicks * (costOfFail * chanceOfFail + costOfSuccess * chanceOfSuccess);

        reblaith14Route.setCost(cost + Math.round(totalCost));
        reblaith14Route.setCurrentFailstack(currentFailstack + 1);
        reblaith14Route.setExpectedNumberOfReblaiths(expectedNumberOfReblaiths + (expectedNumberOfReblaiths * chanceOfSuccess));

        return reblaith14Route;
    }

    private ReblaithRoute clickPriReblaith() {
        ReblaithRoute priReblaithRoute = new ReblaithRoute(this);

        double chanceOfSuccess = SuccessRateCalculator.getPriToDuoRate(currentFailstack);
        double chanceOfFail = 1 - chanceOfSuccess;

        double costOfFail = concentratedBlackStoneCost + reblaithCost;
        double costOfSuccess = concentratedBlackStoneCost;

        double totalCost = costOfFail * chanceOfFail + costOfSuccess * chanceOfSuccess;

        priReblaithRoute.setCost(cost + Math.round(totalCost));
        priReblaithRoute.setCurrentFailstack(currentFailstack + 2);
        priReblaithRoute.setExpectedNumberOfReblaiths(expectedNumberOfReblaiths + chanceOfSuccess);

        return priReblaithRoute;
    }

    @Override
    public int compareTo(ReblaithRoute reblaithRoute) {
        return Long.compare(cost, reblaithRoute.getCost());
    }
}
