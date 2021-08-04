package Model;

import javax.swing.*;

public abstract class Item {

    protected Icon image;
    public Icon getImage() {
        return image;
    }

    protected String name;
    public String getName() {
        return name;
    }

    protected Long value;
    public Long getValue(){
        return value;
    }
    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getName();
    }
}
