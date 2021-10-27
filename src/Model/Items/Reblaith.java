package Model.Items;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Reblaith extends Item{

    public Reblaith() {
        image = new ImageIcon("reblaith.png");
        name = "Reblaith Gloves";
        value = 12900L;
    }

    public Reblaith(int enhancementLevel, long value){
        List<ImageIcon> possibleIcons = Arrays.asList(
                new ImageIcon("reblaith.png"),
                new ImageIcon("priReblaith.png"));
        List<String> possibleNames = Arrays.asList("Reblaith Gloves", "PRI Reblaith Gloves");

        image = possibleIcons.get(enhancementLevel);
        name = possibleNames.get(enhancementLevel);

        this.value = value;
    }
}
