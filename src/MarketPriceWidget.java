import javax.swing.*;
import java.awt.*;

/**
 * MarketPriceWidgets consist of an image to identify an item, a text label with the item's name, and a modifiable
 * text field displaying the item's value, in silver.
 */
public class MarketPriceWidget extends JPanel {

    private final GridBagConstraints imageConstraints;
    private final GridBagConstraints labelConstraints;
    private final GridBagConstraints textBoxConstraints;

    public MarketPriceWidget(Icon icon, String name, Long value) {
        super(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        imageConstraints = new GridBagConstraints();
        labelConstraints = new GridBagConstraints();
        textBoxConstraints = new GridBagConstraints();

        placeIcon(icon);
        placeName(name);
        placeValue(value);
    }

    private void placeIcon(Icon icon) {
        imageConstraints.gridx = 0;
        imageConstraints.gridy = 0;

        imageConstraints.gridwidth = 1;
        imageConstraints.gridheight = 2;

        imageConstraints.ipadx = 8;

        JLabel itemImage = new JLabel(icon);
        add(itemImage, imageConstraints);
    }

    private void placeName(String name) {
        labelConstraints.gridx = 1;
        labelConstraints.gridy = 0;

        labelConstraints.gridwidth = 2;
        labelConstraints.gridheight = 1;

        labelConstraints.weightx = 1.0;

        JLabel itemName = new JLabel(name);
        itemName.setForeground(Color.ORANGE);
        add(itemName, labelConstraints);
    }

    private void placeValue(Long value) {
        textBoxConstraints.gridx = 1;
        textBoxConstraints.gridy = 1;

        textBoxConstraints.gridwidth = 2;
        textBoxConstraints.gridheight = 1;

        textBoxConstraints.weightx = 1.0;

        textBoxConstraints.fill = GridBagConstraints.HORIZONTAL;

        JTextField itemValue = new JTextField((value.toString()));
        add(itemValue, textBoxConstraints);
    }

}
