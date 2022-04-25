package Model;

public class Failstack {

    private int failstack;
    private long value;
    public Failstack(){
        failstack = 0;
        value = 0L;
    }

    public Failstack(int failstack, long value){
        this.failstack = failstack;
        this.value = value;
    }

    public int getFailstack() {
        return failstack;
    }

    public void setFailstack(int failstack) {
        this.failstack = failstack;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
