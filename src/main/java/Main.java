public class Main {
    public static void main(String[] args) {
        SimpleGUI simpleGUI = new SimpleGUI();
        simpleGUI.setVisible(true);
        double a = 0;
        double b = 2;
        int n = 100;
        double eps = 0.01;
        Function function = new Function();
        TypeMethod typeMethod = new TypeMethod(function);
        System.out.println("Численное");
        System.out.println("EPS " + " Euler " + " Runge");
        for (int i = 0; i < 3; i++) {
            System.out.println(eps + "  " + typeMethod.tes_epx_euler(a, b, eps)
                    + "  " + typeMethod.tes_epx_runge(a, b, eps));
            eps *= 0.1;
        }
    }
}
