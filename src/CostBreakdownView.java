import javax.swing.*;
import java.awt.*;

public class CostBreakdownView extends JPanel {

    public CostBreakdownView() {
        super();

        FlowLayout costBreakdownLayout = new FlowLayout();
        costBreakdownLayout.setAlignment(FlowLayout.LEFT);
        setLayout(costBreakdownLayout);

        setBackground(Color.DARK_GRAY);

        add(new CostBreakdownWidget(new Reblaith(), 4, 3000000L));
    }

}
