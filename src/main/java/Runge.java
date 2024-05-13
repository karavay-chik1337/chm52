public class Runge {
    Function function;

    public Runge(Function function) {
        this.function = function;
    }

    public double runge_calc(double x, double u1, double u2, double h) {

            double k1_u1 = h * function.f1(x, u1, u2);
            double k2_u1 = h * function.f1(x + 2 * h / 3, u1 + 2 * k1_u1 / 3, u2);
            double k1_u2 = h * function.f2(x, u1, u2);
            double k2_u2 = h * function.f2(x + 2 * h / 3, u1,u2 + 2 * k1_u2 / 3);
            return y + 0.25 * (k1 + 3 * k2);

    }

    public double[] runge(double a, double b, int n) {
        Section section = new Section(a, b, n);
        double[] x = section.separation(section.uniformPart());
        double[] y = new double[n];
        y[0] = function.y0;
        for (int i = 0; i < n - 1; i++) {
            y[i + 1] = runge_calc(x[i], y[i], section.h);
        }
        return y;
    }

    public int tes_epxT(double a, double b, double eps) {
        double yToch = function.fTochnoe(b);
        int i = 2;
        while (Math.abs(runge(a, b, i)[i - 1] - yToch) > eps)
            i += 1;
        return i;
    }

    public int tes_epxCH(double a, double b, double eps) {
        int i = 2;
        while ((Math.abs(runge(a, b, i)[i - 1] - runge(a, b, 2 * i)[2 * i - 1])) / 8d > eps)
            i += 1;
        return i;
    }
}
