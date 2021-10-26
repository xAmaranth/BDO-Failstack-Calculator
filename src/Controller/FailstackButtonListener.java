package Controller;

import GUI.CostBreakdownView;
import Model.FailstackCalculator;
import Model.Items.Reblaith;

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
        long[] calculatorOutput = failstackCalculator.calculateFailstackValue();

        long failstackValue = calculatorOutput[0];
        int reblaithClicks = Math.toIntExact(calculatorOutput[1]);

        costBreakdownView.clearCostBreakdownWidgets();
        costBreakdownView.addCostBreakdownWidget(new Reblaith(), reblaithClicks, failstackValue);

        this.outputLabel.setText("Failstack Value: " + String.format("%,d", failstackValue));
    }

}
