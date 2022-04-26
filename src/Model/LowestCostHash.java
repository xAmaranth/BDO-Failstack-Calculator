package Model;

import java.util.HashMap;

public class LowestCostHash extends HashMap<Integer, Long> {

    private final HashMap<Double, Long> expectedValues;

    public LowestCostHash() {
        super();

        expectedValues = new HashMap<>();
    }

    public boolean isCheapest(Failstack failstack) {
        if (!containsKey(failstack.getFailstack())){
            put(failstack.getFailstack(), failstack.getValue());
            return true;
        } else {
            if (failstack.getValue() >= get(failstack.getFailstack())){
                return false;
            } else {
                replace(failstack.getFailstack(), failstack.getValue());
                return true;
            }
        }
    }

    public boolean isCheapest(Failstack failstack, double expectedValue) {
        for (double value : expectedValues.keySet()) {
            if (value >= expectedValue && expectedValues.get(value) <= failstack.getValue()) {
                return isCheapest(failstack);
            }
        }

        if (!expectedValues.containsKey(expectedValue)) {
            expectedValues.put(expectedValue, failstack.getValue());
        } else {
            expectedValues.replace(expectedValue, failstack.getValue());
        }

        isCheapest(failstack);

        return true;
    }

}
