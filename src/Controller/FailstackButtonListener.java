package Controller;

import GUI.CostBreakdownView;
import Model.FailstackCalculator;
import Model.Items.Reblaith;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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

        ArrayList<List<Long>> parsedRoute = parseFailstackRoute(finalRoute);

        for (List<Long> step : parsedRoute){
            costBreakdownView.addCostBreakdownWidget(
                    new Reblaith(Math.toIntExact(step.get(0) - 1), 12700),
                    Math.toIntExact(step.get(1)),
                    step.get(2));
        }

        this.outputLabel.setText("Failstack Value: " + String.format("%,d", failstackValue));
    }

    private ArrayList<List<Long>> parseFailstackRoute(ArrayList<List<Long>> finalRoute) {
        ArrayList<List<Long>> parsedRoute = new ArrayList<>();

        long currentMethod = finalRoute.get(0).get(0);
        long numberOfSteps = 0;
        long costOfSteps = 0;

        for (List<Long> step : finalRoute){
            if (step.get(0) == currentMethod){
                numberOfSteps++;
                costOfSteps += step.get(1);
            } else {
                parsedRoute.add(Arrays.asList(currentMethod, numberOfSteps, costOfSteps));

                currentMethod = step.get(0);
                numberOfSteps = 1;
                costOfSteps = step.get(1);
            }
        }

        parsedRoute.add(Arrays.asList(currentMethod, numberOfSteps, costOfSteps));

        return parsedRoute;
    }

}
