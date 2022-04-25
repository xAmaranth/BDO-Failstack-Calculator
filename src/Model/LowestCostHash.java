package Model;

import java.util.HashMap;

public class LowestCostHash extends HashMap<Integer, Long> {

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

}
