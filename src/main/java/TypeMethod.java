public class TypeMethod {
    Function function;

    public TypeMethod(Function function) {
        this.function = function;
    }

    public double[][] euler(double a, double b, int n) {
        double[][] u = new double[2][n + 1];
        Section section = new Section(a, b, n);
        double[] u1 = new double[n + 1];
        double[] u2 = new double[n + 1];
        double[] x = section.uniformPart();
        u1[0] = function.u10;
        u2[0] = function.u20;
        for (int i = 1; i <= section.n; i++) {
            u1[i] = u1[i - 1] + section.h * function.f1(x[i - 1], u1[i - 1], u2[i - 1]);
            u2[i] = u2[i - 1] + section.h * function.f2(x[i - 1], u1[i - 1], u2[i - 1]);
        }
        u[0] = u1;
        u[1] = u2;
        return u;
    }

    public double[][] runge(double a, double b, int n) {
        double[][] u = new double[2][n + 1];
        Section section = new Section(a, b, n);
        double[] u1 = new double[n + 1];
        double[] u2 = new double[n + 1];
        double[] x = section.uniformPart();
        u1[0] = function.u10;
        u2[0] = function.u20;
        for (int i = 1; i <= section.n; i++) {
            double k1_u1 = section.h * function.f1(x[i - 1], u1[i - 1], u2[i - 1]);
            double k1_u2 = section.h * function.f2(x[i - 1], u1[i - 1], u2[i - 1]);
            double k2_u1 = section.h * function.f1(x[i - 1] + 2 * section.h / 3, u1[i - 1] + 2 * k1_u1 / 3, u2[i - 1] + 2 * k1_u1 / 3);
            double k2_u2 = section.h * function.f2(x[i - 1] + 2 * section.h / 3, u1[i - 1] + 2 * k1_u2 / 3, u2[i - 1] + 2 * k1_u2 / 3);
            u1[i] = u1[i - 1] + 0.25 * (k1_u1 + 3 * k2_u1);
            u2[i] = u2[i - 1] + 0.25 * (k1_u2 + 3 * k2_u2);
        }
        u[0] = u1;
        u[1] = u2;
        return u;
    }

    public double accuracyEuler(double a, double b, int i) {
        return Math.max(Math.abs(euler(a, b, i)[0][i - 1] - euler(a, b, 2 * i)[0][2 * i - 1]),
                (Math.abs(euler(a, b, i)[1][i - 1] - euler(a, b, 2 * i)[1][2 * i - 1])));
    }

    public double accuracyRunge(double a, double b, int i) {
        return Math.max((Math.abs(runge(a, b, i)[0][i - 1] - runge(a, b, 2 * i)[0][2 * i - 1]) / 3d),
                (Math.abs(runge(a, b, i)[1][i - 1] - runge(a, b, 2 * i)[1][2 * i - 1])) / 3d);
    }

    public int tes_epx_euler(double a, double b, double eps) {
        int i = 2;
        while (accuracyEuler(a, b, i) > eps)
            i += 2;
        return i;
    }

    public int tes_epx_runge(double a, double b, double eps) {
        int i = 2;
        while (accuracyRunge(a, b, i) > eps)
            i += 2;
        return i;
    }
}
