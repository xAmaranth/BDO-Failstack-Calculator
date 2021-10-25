package Controller;

import Model.FailstackCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FailstackButtonListener implements ActionListener {

    private final JTextField failstackTextField;

    public FailstackButtonListener(JTextField failstackTextField) {
        this.failstackTextField = failstackTextField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int desiredFailstack = Integer.parseInt(failstackTextField.getText());
        FailstackCalculator failstackCalculator = new FailstackCalculator(desiredFailstack);
        long failstackValue = failstackCalculator.CalculateFailstackValue();
        //TODO: connect the results of the failstack calculator back to the FailstackView
    }
}
