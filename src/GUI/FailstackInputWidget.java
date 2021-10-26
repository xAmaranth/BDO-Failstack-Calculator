package GUI;

import Controller.FailstackButtonListener;

import javax.swing.*;
import java.awt.*;

/**
 *  A FailstackInputWidget consists of an image of a failstack, a label prompting the user to input the desired
 *  failstack, a text box for the user to type in the desired failstack, and a button to start up the calculator.
 */
public class FailstackInputWidget extends JPanel {

    private final GridBagConstraints imageConstraints;
    private final GridBagConstraints labelConstraints;
    private final GridBagConstraints textBoxConstraints;
    private final GridBagConstraints buttonConstraints;

    private final JTextField failstackTextField;

    public FailstackInputWidget(
            Icon icon,
            String textPrompt,
            String buttonLabel,
            JLabel outputLabel,
            CostBreakdownView costBreakdownView
    ) {
        super(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        imageConstraints = new GridBagConstraints();
        labelConstraints = new GridBagConstraints();
        textBoxConstraints = new GridBagConstraints();
        buttonConstraints = new GridBagConstraints();

        placeIcon(icon);
        placeLabel(textPrompt);
        failstackTextField = placeTextBox();
        placeButton(buttonLabel, outputLabel, costBreakdownView);
    }

    private void placeIcon(Icon icon) {
        imageConstraints.gridx = 0;
        imageConstraints.gridy = 0;

        imageConstraints.gridwidth = 1;
        imageConstraints.gridheight = 2;

        imageConstraints.ipadx = 8;

        JLabel failstackIcon = new JLabel(icon);
        add(failstackIcon, imageConstraints);
    }

    private void placeLabel(String textPrompt) {
        labelConstraints.gridx = 1;
        labelConstraints.gridy = 0;

        labelConstraints.gridwidth = 1;
        labelConstraints.gridheight = 1;

        labelConstraints.weightx = 1.0;

        JLabel prompt = new JLabel(textPrompt);
        prompt.setForeground(Color.ORANGE);
        add(prompt, labelConstraints);
    }

    private JTextField placeTextBox() {
        textBoxConstraints.gridx = 1;
        textBoxConstraints.gridy = 1;

        textBoxConstraints.gridwidth = 1;
        textBoxConstraints.gridheight = 1;

        textBoxConstraints.weightx = 1.0;

        textBoxConstraints.fill = GridBagConstraints.HORIZONTAL;

        JTextField inputField = new JTextField();
        add(inputField, textBoxConstraints);

        return inputField;
    }

    private void placeButton(String buttonLabel, JLabel outputLabel, CostBreakdownView costBreakdownView) {
        buttonConstraints.gridx = 2;
        buttonConstraints.gridy = 0;

        buttonConstraints.gridwidth = 1;
        buttonConstraints.gridheight = 2;

        buttonConstraints.fill = GridBagConstraints.BOTH;

        JButton confirmButton = new JButton(buttonLabel);
        add(confirmButton, buttonConstraints);

        confirmButton.addActionListener(new FailstackButtonListener(failstackTextField, outputLabel, costBreakdownView));
    }

}
