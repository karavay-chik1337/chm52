public class Euler {
    Function function;

    public Euler(Function function) {
        this.function = function;
    }

    public double euler_step1(double x, double u1, double u2, double h) {
        return u1 - h * 2 * u1 / x;
    }

    public double euler_step2(double x, double u1, double u2, double h) {
        return u2 + h * (u2 + (x + 2) * u1 / x);
    }

    public double[][] euler(double a, double b, int n) {
        Section section = new Section(a, b, n);
        double[] x = section.uniformPart();
        double[][] u = new double[2][n];
        for(int i = 0; i < n; i++) {
            u[1][i + 1] = euler_step1(x[i], u[1][i], u[2][i], section.h);
            u[2][i + 1] = euler_step2(x[i], u[1][i], u[2][i], section.h);
        }
        return u;
    }
//    public int tes_epxT(double a, double b, double eps) {
//        double yToch = function.fTochnoe(b);
//        int i = 2;
//        while (Math.abs(euler(a, b, i)[i - 1] - yToch) > eps)
//            i += 1;
//        return i;
//    }
//
//    public int tes_epxCH(double a, double b, double eps) {
//        int i = 2;
//        while ((Math.abs(euler(a, b, i)[i - 1] - euler(a, b, 2 * i)[2 * i - 1])) / 8d > eps)
//            i += 1;
//        return i;
//    }
}
