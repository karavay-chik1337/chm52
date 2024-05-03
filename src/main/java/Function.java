public class Function {
    double u10 = 1d;
    double u20 = 2d;

    public double f1(double x, double u1, double u2) {
        return -1d / u2;
    }

    public double dF1(double x) {
        return Math.sqrt(1d - 2d * x);
    }

    public double f2(double x, double u1, double u2) {
        return 1d / u1;
    }

    public double dF2(double x) {
        return Math.sqrt(2d * x + 4d);
    }
}
