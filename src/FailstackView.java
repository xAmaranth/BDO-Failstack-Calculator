import javax.swing.*;
import java.awt.*;

public class FailstackView extends JPanel {

    public FailstackView() {
        super(new BorderLayout());
        setBackground(Color.DARK_GRAY);
        setSize(608, 375);

        add(new FailstackInputWidget(
                new ImageIcon("adviceOfValks.png"),
                "Desired Failstack: ",
                "Calculate Value!"
        ), BorderLayout.PAGE_START);

        add(new CostBreakdownView(), BorderLayout.CENTER);

        addOutputLabel();
    }

    private void addOutputLabel() {
        JLabel outputLabel = new JLabel("Failstack Value:                                  ");
        outputLabel.setForeground(Color.ORANGE);
        outputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(outputLabel, BorderLayout.PAGE_END);
    }

    @Override
    public String toString() {
        return "failstack";
    }
}
