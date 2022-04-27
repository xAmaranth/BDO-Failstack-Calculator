package Model;

import java.util.ArrayList;

@FunctionalInterface
public interface Iterator {
    ArrayList<ReblaithRoute> iterate(ReblaithRoute reblaithRoute);
}
