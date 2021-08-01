package GUI;

import Model.Reblaith;

import javax.swing.*;
import java.awt.*;

public class CostBreakdownView extends JPanel {

    // TODO: Implement this class when implementing the algorithm that calculates cost. Currently a placeholder.


    public CostBreakdownView() {
        super();

        FlowLayout costBreakdownLayout = new FlowLayout();
        costBreakdownLayout.setAlignment(FlowLayout.LEFT);
        setLayout(costBreakdownLayout);

        setBackground(Color.DARK_GRAY);

        add(new CostBreakdownWidget(new Reblaith(), 4, 3000000L));
    }

}
