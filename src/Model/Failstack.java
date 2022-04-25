package Model;

public class Failstack {

    private int failstack;
    public Failstack(Failstack failstackToCopy){
        failstack = failstackToCopy.getFailstack();
        value = failstackToCopy.getValue();
    }

    public int getFailstack() {
        return failstack;
    }

    private long value;

    public void setFailstack(int failstack) {
        this.failstack = failstack;
    }

    public long getValue() {
        return value;
    }

    public Failstack(){
        failstack = 0;
        value = 0L;
    }

    public Failstack(int failstack, long value){
        this.failstack = failstack;
        this.value = value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Failstack Increment(int failstackIncrement, long additionalValue){
        failstack = failstack + failstackIncrement;
        value = value + additionalValue;

        return this;
    }

}
