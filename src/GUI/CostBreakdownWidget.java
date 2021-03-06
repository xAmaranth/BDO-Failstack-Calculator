package GUI;

import Model.Items.Item;

import javax.swing.*;
import java.awt.*;

/**
 * The CostBreakdownWidget explains to the user how the system calculated the cost of a failstack, and allows the
 * player to follow the steps taken by the system to build the failstack themselves in game. Displayed is the item
 * used to failstack, how many times to failstack on that item, and the expected cost of doing so.
 */
public class CostBreakdownWidget extends JPanel {

    private final GridBagConstraints imageConstraints;
    private final GridBagConstraints nameConstraints;
    private final GridBagConstraints failstacksConstraint;
    private final GridBagConstraints costConstraints;

    public CostBreakdownWidget(Item equipmentUsed, Integer startingFailstack, Long cost) {
        super(new GridBagLayout());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(275, 60));

        imageConstraints = new GridBagConstraints();
        nameConstraints = new GridBagConstraints();
        failstacksConstraint = new GridBagConstraints();
        costConstraints = new GridBagConstraints();

        placeImage(equipmentUsed.getImage());
        placeName(equipmentUsed.getName());
        placeStartingFailstack(startingFailstack);
        placeCost(cost);
    }

    private void placeImage(Icon image) {
        imageConstraints.gridx = 0;
        imageConstraints.gridy = 0;

        imageConstraints.gridwidth = 1;
        imageConstraints.gridheight = 3;

        imageConstraints.ipadx = 8;

        JLabel itemImage = new JLabel(image);
        add(itemImage, imageConstraints);
    }

    private void placeName(String name) {
        nameConstraints.gridx = 1;
        nameConstraints.gridy = 0;

        nameConstraints.gridwidth = 1;
        nameConstraints.gridheight = 1;

        nameConstraints.weightx = 1.0;

        nameConstraints.anchor = GridBagConstraints.WEST;

        JLabel itemName = new JLabel(name);
        itemName.setForeground(Color.RED);
        add(itemName, nameConstraints);
    }

    private void placeStartingFailstack(Integer startingFailstack) {
        failstacksConstraint.gridx = 1;
        failstacksConstraint.gridy = 1;

        failstacksConstraint.gridwidth = 1;
        failstacksConstraint.gridheight = 1;

        failstacksConstraint.ipady = 6;

        failstacksConstraint.anchor = GridBagConstraints.WEST;

        JLabel failstack = new JLabel();
        failstack.setText("Starting from a " + startingFailstack + " failstack");
        failstack.setForeground(Color.ORANGE);

        add(failstack, failstacksConstraint);
    }

    private void placeCost(Long cost) {
        costConstraints.gridx = 1;
        costConstraints.gridy = 2;

        costConstraints.gridwidth = 1;
        costConstraints.gridheight = 1;

        costConstraints.ipady = 6;

        costConstraints.anchor = GridBagConstraints.WEST;

        JLabel totalCost = new JLabel("Cost: " + String.format("%,d", cost));
        totalCost.setForeground(Color.ORANGE);

        add(totalCost, costConstraints);
    }

}
