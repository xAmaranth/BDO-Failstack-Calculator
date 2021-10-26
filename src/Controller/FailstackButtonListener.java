package Controller;

import GUI.CostBreakdownView;
import Model.FailstackCalculator;
import Model.Items.Reblaith;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class FailstackButtonListener implements ActionListener {

    private final JTextField failstackTextField;
    private final JLabel outputLabel;
    private final CostBreakdownView costBreakdownView;

    public FailstackButtonListener(
            JTextField failstackTextField,
            JLabel outputLabel,
            CostBreakdownView costBreakdownView
    ) {
        this.failstackTextField = failstackTextField;
        this.outputLabel = outputLabel;
        this.costBreakdownView = costBreakdownView;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int desiredFailstack = Integer.parseInt(failstackTextField.getText());
        FailstackCalculator failstackCalculator = new FailstackCalculator(desiredFailstack);

        failstackCalculator.calculateFailstackValue();

        long failstackValue = failstackCalculator.getFinalCost();
        ArrayList<Integer> finalRoute = failstackCalculator.getFinalRoute();

        costBreakdownView.clearCostBreakdownWidgets();
        costBreakdownView.addCostBreakdownWidget(new Reblaith(), Collections.frequency(finalRoute, 1), failstackValue);

        this.outputLabel.setText("Failstack Value: " + String.format("%,d", failstackValue));
    }

}
