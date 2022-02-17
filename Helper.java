public class Helper {
    public static final double DEFAULT_PRECISION = 1.E-5;
    public static double precision = Helper.DEFAULT_PRECISION;

    public static double getPrecision() {
        return precision;
    }

    public static void setPrecision(double precision) {
        if (precision <= 0)
            Helper.precision = Helper.DEFAULT_PRECISION;
        else
            Helper.precision = precision;
    }

    public static double compare(double a, double b) {
        if (Math.abs(a - b) < Helper.getPrecision())
            return 0;
        else
            return a - b;
    }

    public static boolean areEqual(double a, double b) {
        return compare(a, b) == 0;
    }
}
