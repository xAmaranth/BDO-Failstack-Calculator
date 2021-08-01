package GUI;

import javax.swing.*;
import java.awt.*;

public class NavBarButton extends JButton {

    private final JPanel viewToDisplay;
    private final MainFrame mainFrame;

    /**
     * The button will be enabled when its related screen is not shown, so an icon's dark version should be used as
     * the button's enabled icon, while its bright version should be used as the button's disabled icon.
     */
    public NavBarButton(Icon enabledIcon, Icon disabledIcon, JPanel viewToDisplay, MainFrame mainFrame){
        super();
        setBackground(Color.DARK_GRAY);
        setSize(59, 59);

        this.setIcon(enabledIcon);
        this.setDisabledIcon(disabledIcon);

        this.viewToDisplay = viewToDisplay;
        this.mainFrame = mainFrame;

        setButtonBehavior();
    }

    private void setButtonBehavior() {
        addActionListener(actionEvent -> mainFrame.setMainView(viewToDisplay));
    }

}
