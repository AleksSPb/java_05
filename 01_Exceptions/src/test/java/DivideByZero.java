/**
 *
 */
public class DivideByZero {

    public static void main(String[] args) {
        double d = 1.0 / 0.0;
        calcSomething();
        div(1.0, 0.0);
        System.out.println("d = " + d);
    }

    private static void calcSomething() {
        int i = 10 / 0;
    }

    private static void div(double v, double v1) {
        if (Math.abs(v1) < 1e-15)
            throw new ArithmeticException("/ by zero");
    }
}
