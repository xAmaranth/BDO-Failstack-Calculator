package Controller;

import Model.FailstackCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FailstackButtonListener implements ActionListener {

    private final JTextField failstackTextField;
    private final JLabel outputLabel;

    public FailstackButtonListener(JTextField failstackTextField, JLabel outputLabel) {
        this.failstackTextField = failstackTextField;
        this.outputLabel = outputLabel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int desiredFailstack = Integer.parseInt(failstackTextField.getText());
        FailstackCalculator failstackCalculator = new FailstackCalculator(desiredFailstack);
        long failstackValue = failstackCalculator.calculateFailstackValue();

        this.outputLabel.setText("Failstack Value: " + String.format("%,d", failstackValue));
        //TODO: improve the output to also display how to failstack the way the calculator does.
    }
}
