package GUI;

import javax.swing.*;
import java.awt.*;

public class FailstackView extends JPanel {

    public FailstackView() {
        super(new BorderLayout());
        setBackground(Color.DARK_GRAY);
        setSize(608, 375);

        JLabel outputLabel = addOutputLabel();

        add(new FailstackInputWidget(
                new ImageIcon("adviceOfValks.png"),
                "Desired Failstack: ",
                "Calculate Value",
                outputLabel
        ), BorderLayout.PAGE_START);

        add(new CostBreakdownView(), BorderLayout.CENTER);

    }

    private JLabel addOutputLabel() {
        JLabel outputLabel = new JLabel("Failstack Value:                                  ");
        outputLabel.setForeground(Color.ORANGE);
        outputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(outputLabel, BorderLayout.PAGE_END);

        return outputLabel;
    }

    @Override
    public String toString() {
        return "failstack";
    }
}
