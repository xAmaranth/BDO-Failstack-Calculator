package Model;

import Model.Items.PriReblaith;
import Model.Items.Reblaith14;

import java.util.ArrayList;

public class ReblaithRoute implements Comparable<ReblaithRoute>{

    private double expectedNumberOfReblaiths;
    public double getExpectedNumberOfReblaiths() {
        return expectedNumberOfReblaiths;
    }
    public void setExpectedNumberOfReblaiths(double expectedNumberOfReblaiths) {
        this.expectedNumberOfReblaiths = expectedNumberOfReblaiths;
    }

    private long cost;
    public long getCost() {
        return cost;
    }
    public void setCost(long cost) {
        this.cost = cost;
    }

    private int currentFailstack;
    public int getCurrentFailstack() {
        return currentFailstack;
    }
    public void setCurrentFailstack(int currentFailstack) {
        this.currentFailstack = currentFailstack;
    }

    public ReblaithRoute() {
        expectedNumberOfReblaiths = 0;
        cost = 0;
        currentFailstack = 0;
    }
    public ReblaithRoute(ReblaithRoute currentReblaithRoute){
        expectedNumberOfReblaiths = currentReblaithRoute.getExpectedNumberOfReblaiths();
        cost = currentReblaithRoute.getCost();
        currentFailstack = currentReblaithRoute.getCurrentFailstack();
    }

    public ArrayList<ReblaithRoute> iterate(){
        ArrayList<ReblaithRoute> next = new ArrayList<>();

        next.add(clickReblaith14());
        next.add(clickPriReblaith());

        return next;
    }

    private ReblaithRoute clickReblaith14() {
        ReblaithRoute reblaith14Route = new ReblaithRoute(this);

        double totalCost = Reblaith14.CostToClick(new Failstack (currentFailstack, cost));

        reblaith14Route.setCost(cost + Math.round(totalCost));
        reblaith14Route.setCurrentFailstack(currentFailstack + 1);
        reblaith14Route.setExpectedNumberOfReblaiths(expectedNumberOfReblaiths);

        return reblaith14Route;
    }

    private ReblaithRoute clickPriReblaith() {
        ReblaithRoute priReblaithRoute = new ReblaithRoute(this);

        double chanceOfSuccess = SuccessRateCalculator.getPriToDuoRate(currentFailstack);

        double totalCost = PriReblaith.CostToClick(new Failstack(currentFailstack, cost));

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
