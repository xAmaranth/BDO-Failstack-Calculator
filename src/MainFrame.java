import javax.swing.*;

public class MainFrame {

    private JFrame staticFrame;

    public MainFrame() {
        createFrame();
        populateFrame();
        showFrame();
    }

    /**
     * Creates a new frame, and sets its title and size.
     */
    private void createFrame() {
        staticFrame = new JFrame("BDO Failstack Calculator");
        staticFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        staticFrame.setSize(607, 375);
    }

    private void populateFrame() {
        //TODO: implement me later.
    }

    private void showFrame() {
        staticFrame.setVisible(true);
    }


    public static void main(String[] args) {
        new MainFrame();
    }
}
