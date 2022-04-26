package Controller;

import GUI.CostBreakdownView;
import Model.EnhancementRoute;
import Model.FailstackCalculator;
import Model.Items.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        EnhancementRoute finalRoute = failstackCalculator.getFinalRoute();

        costBreakdownView.clearCostBreakdownWidgets();

        for (int i = 0; i < finalRoute.getItemRoute().size(); i++) {
            Item itemUsed = finalRoute.getItemRoute().get(i);
            if (i > 0 && itemUsed.toString().equals(finalRoute.getItemRoute().get(i - 1).toString())) {
                continue;
            }

            long stepCost = 0;
            for (int j = i; j < finalRoute.getItemRoute().size(); j++) {
                if (!itemUsed.toString().equals(finalRoute.getItemRoute().get(j).toString())) {
                    stepCost = finalRoute.getFailstackRoute().get(j).getValue() - finalRoute.getFailstackRoute().get(i).getValue();
                    break;
                } else if (j + 1 == finalRoute.getItemRoute().size()) {
                    stepCost = failstackValue - finalRoute.getFailstackRoute().get(i).getValue();
                }
            }

            costBreakdownView.addCostBreakdownWidget(itemUsed,
                    finalRoute.getFailstackRoute().get(i).getFailstack(),
                    stepCost);
        }

        this.outputLabel.setText("Failstack Value: " + String.format("%,d", failstackValue));
    }

}
