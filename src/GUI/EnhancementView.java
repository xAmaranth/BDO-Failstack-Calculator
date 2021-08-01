package GUI;

import javax.swing.*;
import java.awt.*;

public class EnhancementView extends JPanel {

    public EnhancementView() {
        super(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        setSize(608, 375);

        placeEnhancementSelectionWidget();

        add(new CostBreakdownView(), BorderLayout.CENTER);
    }

    private void placeEnhancementSelectionWidget() {
        EnhancementCategoryWidget[] enhancementCategoryWidgets = new EnhancementCategoryWidget[9];

        enhancementCategoryWidgets[0] = new EnhancementCategoryWidget(
                new ImageIcon("triWeapon.png"),
                "Boss Weapon",
                "TRI (III)"
        );
        enhancementCategoryWidgets[1] = new EnhancementCategoryWidget(
                new ImageIcon("tetWeapon.png"),
                "Boss Weapon",
                "TET (IV)"
        );
        enhancementCategoryWidgets[2] = new EnhancementCategoryWidget(
                new ImageIcon("penWeapon.png"),
                "Boss Weapon",
                "PEN (V)"
        );
        enhancementCategoryWidgets[3] = new EnhancementCategoryWidget(
                new ImageIcon("triArmor.png"),
                "Boss Armor",
                "TRI (III)"
        );
        enhancementCategoryWidgets[4] = new EnhancementCategoryWidget(
                new ImageIcon("tetArmor.png"),
                "Boss Armor",
                "TET (IV)"
        );
        enhancementCategoryWidgets[5] = new EnhancementCategoryWidget(
                new ImageIcon("penArmor.png"),
                "Boss Armor",
                "PEN (V)"
        );
        enhancementCategoryWidgets[6] = new EnhancementCategoryWidget(
                new ImageIcon("triAccessory.png"),
                "Accessory",
                "TRI (III)"
        );
        enhancementCategoryWidgets[7] = new EnhancementCategoryWidget(
                new ImageIcon("tetAccessory.png"),
                "Accessory",
                "TET (IV)"
        );
        enhancementCategoryWidgets[8] = new EnhancementCategoryWidget(
                new ImageIcon("penAccessory.png"),
                "Accessory",
                "PEN (V)"
        );

        EnhancementSelectionWidget enhancementSelectionWidget = new EnhancementSelectionWidget(
                "Desired Model.Item: ",
                enhancementCategoryWidgets,
                "Calculate Cost"
        );
        add(enhancementSelectionWidget, BorderLayout.PAGE_START);
    }

    @Override
    public String toString() {
        return "enhancement";
    }

}
