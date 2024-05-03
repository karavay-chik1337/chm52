public class Main {
    public static void main(String[] args) {
        double a = 0.6;
        double b = 1;
        int n = 100;
        double eps = 0.01;
        Function function = new Function();
        Euler euler = new Euler(function);
        Runge runge = new Runge(function);
        System.out.println("Тестовая");
        System.out.println("EPS " + " Euler " + " Runge");
        for (int i = 0; i < 3; i++) {
            System.out.println(eps + "  " + euler.tes_epxT(0.6, 1, eps) + "  " + runge.tes_epxT(0.6, 1, eps));
            eps *= 0.1;
        }
//        System.out.println(function.fTochnoe(1d) + " " + euler.euler(0.6, 1, n)[n-1]);
//        eps = 0.01;
//        System.out.println("Численное");
//        System.out.println("\nEPS " + " Euler " + " Runge");
//        for (int i = 0; i < 3; i++) {
//            System.out.println(eps + "  " + euler.tes_epxCH(0.6, 1, eps) + "  " + runge.tes_epxCH(0.6, 1, eps));
//            eps *= 0.1;
//        }
    }
}
