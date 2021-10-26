package Model;

/**
 * The SuccessRateCalculator returns the probability that an enhancement attempt at a given failstack with succeed.
 * When failstacking, this is an undesirable outcome since we are intentionally trying to fail.
 * When enhancing, this is a desirable outcome.
 */
public class SuccessRateCalculator {

    public static double getReblaithRate(int currentFailstack){
        return 0.02 + (currentFailstack * 0.002);
    }
}
