package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private JFrame staticFrame;

    private JPanel mainView;
    public void setMainView(JPanel mainView) {
        if (!(mainView.getParent() == this.mainView)){
            this.mainView.add(mainView.toString(), mainView);
        }
        CardLayout cardLayout = (CardLayout)(this.mainView.getLayout());
        cardLayout.show(this.mainView, mainView.toString());
        this.mainView.revalidate();
    }

    public MainFrame() {
        createFrame();
        populateFrame();
        showFrame();
    }

    private void createFrame() {
        staticFrame = new JFrame("BDO Failstack Calculator");
        staticFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        staticFrame.setSize(667, 375);
    }

    private void populateFrame() {
        mainView = new JPanel();
        mainView.setBackground(Color.DARK_GRAY);
        mainView.setSize(608, 375);
        mainView.setLayout(new CardLayout());

        NavBarView navBarView = new NavBarView();
        navBarView.setPreferredSize(new Dimension(59, 375));

        navBarView.addNavBarButton(new NavBarButton(
                new ImageIcon("marketplaceDark.png"),
                new ImageIcon("marketplaceBright.png"),
                new MarketPriceView(),
                this
            ));
        navBarView.addNavBarButton(new NavBarButton(
                new ImageIcon("adviceOfValksDark.png"),
                new ImageIcon("sealedAdviceOfValks.png"),
                new FailstackView(),
                this
            ));
        navBarView.addNavBarButton(new NavBarButton(
                new ImageIcon("enhancementDark.png"),
                new ImageIcon("enhancementBright.png"),
                new EnhancementView(),
                this
            ));

        staticFrame.add(navBarView, BorderLayout.LINE_START);
        staticFrame.add(mainView, BorderLayout.CENTER);
    }

    private void showFrame() {
        staticFrame.setVisible(true);
    }


    public static void main(String[] args) {
        new MainFrame();
    }

}
