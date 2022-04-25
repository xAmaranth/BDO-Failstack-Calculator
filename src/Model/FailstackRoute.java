package Model;

import Model.Items.PriReblaith;
import Model.Items.Reblaith14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// This class is obsolete, and remains only for reference.

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

    public FailstackRoute() {
        value = 0;
        currentFailstack = 0;
        route = new ArrayList<>();
    }
    public FailstackRoute(FailstackRoute currentFailstackRoute) {
        value = currentFailstackRoute.getValue();
        currentFailstack = currentFailstackRoute.getCurrentFailstack();
        route = new ArrayList<>();
        route.addAll(currentFailstackRoute.getRoute());
    }

    public ArrayList<FailstackRoute> iterate(){
        ArrayList<FailstackRoute> next = new ArrayList<>();

        next.add(clickReblaith14());
        next.add(clickPriReblaith());

        return next;
    }

    private FailstackRoute clickReblaith14() {
        FailstackRoute reblaithRoute = new FailstackRoute(this);

        double totalCost = Reblaith14.CostToClick(new Failstack(currentFailstack, value));

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

        double totalCost = expectedNumberOfClicks * PriReblaith.CostToClick(new Failstack(currentFailstack, value));

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
