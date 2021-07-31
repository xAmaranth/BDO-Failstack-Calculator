import javax.swing.*;
import java.awt.*;

/**
 * Displays an image representing a category of enhancements (armor, weapon, or accessory), alongside that category's
 * name and desired enhancement level.
 */
public class EnhancementCategoryWidget extends JPanel{

    private final GridBagConstraints imageConstraints;
    private final GridBagConstraints categoryConstraints;
    private final GridBagConstraints levelConstraints;

    public EnhancementCategoryWidget(Icon icon, String category, String enhancementLevel) {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);

        imageConstraints = new GridBagConstraints();
        categoryConstraints = new GridBagConstraints();
        levelConstraints = new GridBagConstraints();

        placeImage(icon);
        placeCategory(category);
        placeLevel(enhancementLevel);
    }

    private void placeImage(Icon icon) {
        imageConstraints.gridx = 0;
        imageConstraints.gridy = 0;

        imageConstraints.gridwidth = 1;
        imageConstraints.gridheight = 2;

        imageConstraints.ipadx = 8;

        JLabel categoryImage = new JLabel(icon);
        add(categoryImage, imageConstraints);
    }

    private void placeCategory(String category) {
        categoryConstraints.gridx = 1;
        categoryConstraints.gridy = 0;

        categoryConstraints.gridwidth = 1;
        categoryConstraints.gridheight = 1;

        categoryConstraints.weightx = 1.0;

        JLabel categoryName = new JLabel(category);
        categoryName.setForeground(Color.ORANGE);
        add(categoryName, categoryConstraints);
    }

    private void placeLevel(String enhancementLevel) {
       levelConstraints.gridx = 1;
       levelConstraints.gridy = 1;

       levelConstraints.gridwidth = 1;
       levelConstraints.gridheight = 1;

       levelConstraints.weightx = 1.0;

       JLabel level = new JLabel(enhancementLevel);
       level.setForeground(Color.ORANGE);
       add(level, levelConstraints);
    }

}
