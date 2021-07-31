import javax.swing.*;
import java.awt.*;

public class CategorySelectionRenderer extends JPanel implements ListCellRenderer<EnhancementCategoryWidget> {

    @Override
    public Component getListCellRendererComponent(
            JList<? extends EnhancementCategoryWidget> jList,
            EnhancementCategoryWidget enhancementCategoryWidget,
            int indexOfSelection,
            boolean isSelected,
            boolean cellHasFocus
    ) {
        return enhancementCategoryWidget;
    }

}
