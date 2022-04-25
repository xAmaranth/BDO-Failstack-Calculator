package Model;

import Model.Items.Item;

public class ReblaithRoute extends EnhancementRoute {

    private double expectedNumberOfReblaiths;
    public double getExpectedNumberOfReblaiths() {
        return expectedNumberOfReblaiths;
    }

    public ReblaithRoute() {
        super();

        expectedNumberOfReblaiths = 0.0;
    }

    public ReblaithRoute(ReblaithRoute routeToCopy) {
        super(routeToCopy);

        expectedNumberOfReblaiths = routeToCopy.expectedNumberOfReblaiths;
    }

    public void addToRoute(Failstack newFailstack, Item itemUsed, double additionalReblaiths) {
        super.addToRoute(newFailstack, itemUsed);

        expectedNumberOfReblaiths = expectedNumberOfReblaiths + additionalReblaiths;
    }

}
