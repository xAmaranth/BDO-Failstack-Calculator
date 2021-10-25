package Controller;

import Model.CostTracker;
import Model.Items.Item;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class MarketPriceListener implements DocumentListener {

    private final Item itemOnDisplay;

    public MarketPriceListener(Item itemOnDisplay) {
        this.itemOnDisplay = itemOnDisplay;
        CostTracker.addItem(itemOnDisplay);
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        updateItemPrice(documentEvent);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        updateItemPrice(documentEvent);
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        updateItemPrice(documentEvent);
    }

    private void updateItemPrice(DocumentEvent documentEvent) {
        int textLength = documentEvent.getDocument().getLength();
        String text = null;
        try {
            text = documentEvent.getDocument().getText(0, textLength);
        } catch (BadLocationException e) {
            //TODO: show error to user.
            e.printStackTrace();
        }

        // We don't have to call CostTracker.addItem(itemOnDisplay) because the CostTracker isn't updated
        // anywhere else in the application.
        if (text != null && !text.equals("")){
            itemOnDisplay.setValue(Long.parseLong(text));
        }
    }

}
