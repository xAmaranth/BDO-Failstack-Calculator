package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NavBarView extends JPanel {

    private final ArrayList<NavBarButton> navBarButtons;

    public NavBarView() {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);
        setNavBarLayout();

        navBarButtons = new ArrayList<>();
    }

    private void setNavBarLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addNavBarButton(NavBarButton navBarButton) {
        super.add(navBarButton);
        navBarButtons.add(navBarButton);

        navBarButton.addActionListener(actionEvent -> {
            for (NavBarButton button : navBarButtons) {
                if (!(button.isEnabled())) {
                    button.setEnabled(true);
                } else if (button == navBarButton) {
                    button.setEnabled(false);
                }
            }
        });
    }

}
