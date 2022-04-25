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

    //TODO: some of the information displayed isn't particularly useful (adjust the CostBreakdownWidget)
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int desiredFailstack = Integer.parseInt(failstackTextField.getText());
        FailstackCalculator failstackCalculator = new FailstackCalculator(desiredFailstack);

        failstackCalculator.calculateFailstackValue();

        long failstackValue = failstackCalculator.getFinalCost();
        EnhancementRoute finalRoute = failstackCalculator.getFinalRoute();

        costBreakdownView.clearCostBreakdownWidgets();

        for (int i = 0; i < finalRoute.getItemRoute().size(); i++){
            Item itemUsed = finalRoute.getItemRoute().get(i);
            int consecutiveUses = 1;
            for (int j = i + 1; j < finalRoute.getItemRoute().size(); j++){
                if (itemUsed.toString().equals(finalRoute.getItemRoute().get(j).toString())){
                    consecutiveUses++;
                    i++;
                } else {
                    i = j - 1;
                    break;
                }
            }

            costBreakdownView.addCostBreakdownWidget(finalRoute.getItemRoute().get(i),
                    consecutiveUses,
                    finalRoute.getFailstackRoute().get(i).getValue());
        }

        this.outputLabel.setText("Failstack Value: " + String.format("%,d", failstackValue));
    }

}
