package GUI;

import javax.swing.*;
import java.awt.*;

public class EnhancementSelectionWidget extends JPanel {

    private final GridBagConstraints promptConstraint;
    private final GridBagConstraints selectionConstraint;
    private final GridBagConstraints buttonConstraint;

    public EnhancementSelectionWidget(
            String textPrompt,
            EnhancementCategoryWidget[] enhancementCategoryWidgets,
            String buttonPrompt
    ) {
        super(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        promptConstraint = new GridBagConstraints();
        selectionConstraint = new GridBagConstraints();
        buttonConstraint = new GridBagConstraints();

        placeText(textPrompt);
        placeCategories(enhancementCategoryWidgets);
        placeButton(buttonPrompt);
    }

    private void placeText(String textPrompt) {
        promptConstraint.gridx = 0;
        promptConstraint.gridy = 0;

        promptConstraint.gridwidth = 1;
        promptConstraint.gridheight = 1;

        promptConstraint.ipady = 6;

        JLabel prompt = new JLabel(textPrompt);
        prompt.setForeground(Color.ORANGE);
        add(prompt, promptConstraint);
    }

    private void placeCategories(EnhancementCategoryWidget[] enhancementCategoryWidgets) {
        selectionConstraint.gridx = 0;
        selectionConstraint.gridy = 1;

        selectionConstraint.gridwidth = 1;
        selectionConstraint.gridheight = 1;

        selectionConstraint.ipadx = 8;

        JComboBox<EnhancementCategoryWidget> selectionBox = new JComboBox<>(enhancementCategoryWidgets);
        selectionBox.setRenderer(new CategorySelectionRenderer());
        selectionBox.setBackground(Color.DARK_GRAY);
        selectionBox.setFocusable(false);
        add(selectionBox, selectionConstraint);
    }

    private void placeButton(String buttonPrompt) {
        buttonConstraint.gridx = 1;
        buttonConstraint.gridy = 0;

        buttonConstraint.gridwidth = 1;
        buttonConstraint.gridheight = 2;

        buttonConstraint.fill = GridBagConstraints.BOTH;

        JButton confirmButton = new JButton(buttonPrompt);
        add(confirmButton, buttonConstraint);
    }
}
