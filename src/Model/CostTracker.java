package Model;

import Model.Items.Item;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class CostTracker {

    private static final HashMap<String, Item> itemMap = new HashMap<>();

    public static void addItem(Item itemToAdd) {
        String itemName = itemToAdd.getName();

        if (itemMap.containsKey(itemName)){
            itemMap.replace(itemName, itemToAdd);
        } else {
            itemMap.put(itemToAdd.getName(), itemToAdd);
        }
    }

    public static Long getCost(String itemName) {
        if (itemMap.containsKey(itemName)){
            return itemMap.get(itemName).getValue();
        } else {
            throw new NoSuchElementException("Attempting to get cost before adding item");
        }
    }

    public static Boolean hasEntry(String itemName) {
        return itemMap.containsKey(itemName);
    }

}
