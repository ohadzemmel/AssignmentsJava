public class MathUtil {

    public static final double precision = 1e-5;
    public static final double precisionHalf = 1;
    public static boolean doubleEqual(double a, double b) {
        return a == b || Math.abs(a - b) < precision;
    }
    public static boolean doubleEqualHalf(double a, double b) {
        return a == b || Math.abs(a - b) <= precisionHalf;
    }
}


