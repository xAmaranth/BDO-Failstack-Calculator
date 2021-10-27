package GUI;

import Model.Items.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CostBreakdownView extends JPanel {

    private final ArrayList<CostBreakdownWidget> currentWidgets;

    public CostBreakdownView() {
        super();

        FlowLayout costBreakdownLayout = new FlowLayout();
        costBreakdownLayout.setAlignment(FlowLayout.LEFT);
        setLayout(costBreakdownLayout);

        setBackground(Color.DARK_GRAY);

        currentWidgets = new ArrayList<>();
    }

    public void addCostBreakdownWidget(Item itemUsed, int attemptsMade, long cost){
        CostBreakdownWidget newWidget = new CostBreakdownWidget(itemUsed, attemptsMade, cost);
        currentWidgets.add(newWidget);

        add(newWidget);
    }

    public void clearCostBreakdownWidgets(){
        for (CostBreakdownWidget costBreakdownWidget : currentWidgets) {
            remove(costBreakdownWidget);
        }

        currentWidgets.clear();

        revalidate();
        repaint();
    }

}
