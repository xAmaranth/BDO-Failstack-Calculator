package Model;

import Model.Items.Item;

import java.util.ArrayList;

public class EnhancementRoute implements Comparable<EnhancementRoute> {

    private final ArrayList<Item> itemRoute;
    private final ArrayList<Failstack> failstackRoute;
    private Failstack failstack;

    public EnhancementRoute() {
        failstack = new Failstack();
        itemRoute = new ArrayList<>();
        failstackRoute = new ArrayList<>();
    }

    public EnhancementRoute(EnhancementRoute currentEnhancementRoute){
        failstack = new Failstack(currentEnhancementRoute.getFailstack());
        itemRoute = new ArrayList<>(currentEnhancementRoute.getItemRoute());
        failstackRoute = new ArrayList<>(currentEnhancementRoute.getFailstackRoute());
    }

    public Failstack getFailstack() {
        return failstack;
    }

    public ArrayList<Item> getItemRoute() {
        return itemRoute;
    }

    public ArrayList<Failstack> getFailstackRoute() {
        return failstackRoute;
    }

    public void addToRoute(Failstack newFailstack, Item itemUsed) {
        failstackRoute.add(this.failstack);
        itemRoute.add(itemUsed);

        this.failstack = newFailstack;
    }

    @Override
    public int compareTo(EnhancementRoute otherEnhancementRoute) {
        return Long.compare(failstack.getValue(), otherEnhancementRoute.failstack.getValue());
    }

}
