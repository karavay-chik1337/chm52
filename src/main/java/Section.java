public class Section {
    double a;
    double b;
    int n;
    double h;

    public Section(double a, double b, int n) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.h = (b - a) / (double) (n);
    }

    public double[] uniformPart() {
        double[] x = new double[n + 1];
        for (int i = 0; i <= n; i++)//равномерное разбиение
            x[i] = a + (h * i);
        return x;
    }
}
