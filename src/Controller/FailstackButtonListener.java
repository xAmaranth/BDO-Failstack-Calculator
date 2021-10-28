package Controller;

import GUI.CostBreakdownView;
import Model.FailstackCalculator;
import Model.Items.Reblaith;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<List<Long>> finalRoute = failstackCalculator.getFinalRoute();

        costBreakdownView.clearCostBreakdownWidgets();

        for (List<Long> step : finalRoute){
            costBreakdownView.addCostBreakdownWidget(
                    new Reblaith(Math.toIntExact(step.get(0)), 12700),
                    Math.toIntExact(step.get(1)),
                    step.get(2));
        }

        this.outputLabel.setText("Failstack Value: " + String.format("%,d", failstackValue));
    }

}
