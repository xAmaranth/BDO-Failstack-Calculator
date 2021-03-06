package GUI;

import Controller.MarketPriceListener;
import Model.Items.Item;

import javax.swing.*;
import java.awt.*;

/**
 * MarketPriceWidgets consist of an image to identify an item, a text label with the item's name, and a modifiable
 * text field displaying the item's value, in silver.
 *
 * It's important for the MarketPriceWidget (in the MarketPriceView) to be the sole place to adjust the market prices
 * of items in order to avoid issues with the CostTracker.
 */
public class MarketPriceWidget extends JPanel {

    private final GridBagConstraints imageConstraints;
    private final GridBagConstraints labelConstraints;
    private final GridBagConstraints textBoxConstraints;

    private final Item itemOnDisplay;

    public MarketPriceWidget(Item itemToDisplay) {
        super(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        imageConstraints = new GridBagConstraints();
        labelConstraints = new GridBagConstraints();
        textBoxConstraints = new GridBagConstraints();

        itemOnDisplay = itemToDisplay;

        placeIcon(itemToDisplay.getImage());
        placeName(itemToDisplay.getName());
        placeValue(itemToDisplay.getValue());
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

        itemValue.getDocument().addDocumentListener(new MarketPriceListener(itemOnDisplay));
    }

}
