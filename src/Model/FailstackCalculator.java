package Model;

public class FailstackCalculator {

    private final int targetFailstack;

    public FailstackCalculator(int targetFailstack) {
        this.targetFailstack = targetFailstack;
    }

    public long calculateFailstackValue(){
        long blackStoneCost = CostTracker.getCost("Black Stone (Armor)");
        long reblaithCost = CostTracker.getCost("Reblaith Gloves");

        int currentFailstack = 0;
        double currentValue = 0;
        while (currentFailstack < targetFailstack){
            double chanceOfSuccess = SuccessRateCalculator.getReblaithRate(currentFailstack);
            double chanceOfFail = 1 - chanceOfSuccess;
            double expectedNumberOfClicks = 1 / chanceOfFail;

            double costOfFail = blackStoneCost + reblaithCost / 2.0;
            double costOfSuccess = blackStoneCost + 100000 + currentValue;

            double totalCost = expectedNumberOfClicks * (costOfFail * chanceOfFail + costOfSuccess * chanceOfSuccess);

            currentValue += totalCost;
            currentFailstack++;
        }

        return Math.round(currentValue);
    }

}
