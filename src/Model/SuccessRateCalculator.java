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

    public static double getPriToDuoRate(int currentFailstack) {
        if (currentFailstack > 82) {
            return 0.7075 + ((currentFailstack - 82) * 0.00155);
        } else {
            return 0.0769 + (currentFailstack * 0.0077);
        }
    }

    public static double getDuoToTriRate(int currentFailstack) {
        if (currentFailstack > 102) {
            return 0.7000 + ((currentFailstack - 102) * 0.00125);
        } else {
            return 0.0625 + (currentFailstack * 0.00625);
        }
    }
}
