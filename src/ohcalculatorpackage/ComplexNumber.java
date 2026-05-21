package ohcalculatorpackage;

/*
 * ComplexNumber
 *
 * This class represents an ordinary complex number:
 *
 *     z = a + i b
 *
 * where:
 *
 *     a = real part
 *     b = imaginary part
 *
 * This class is the scalar foundation for the whole project.
 *
 * Later, we expect to use ComplexNumber objects as:
 *
 * - coefficients in the Pauli algebra,
 * - entries in 2x2 matrices,
 * - coefficients in the Oh group algebra,
 * - coefficients in the Oh group algebra over the Pauli algebra.
 *
 * Therefore, even though this class is small, it is very important.
 *
 * @author Carl Brannen and ChatGPT
 */
public class ComplexNumber {

    /*
     * realPart
     *
     * This stores the ordinary real part of the complex number.
     */
    private final double realPart;

    /*
     * imaginaryPart
     *
     * This stores the coefficient of i in the complex number.
     */
    private final double imaginaryPart;

    /*
     * ComplexNumber constructor
     *
     * A constructor creates a new object.
     *
     * When we write:
     *
     *     new ComplexNumber(2.0, 3.0)
     *
     * Java calls this constructor and creates the complex number:
     *
     *     2 + 3i
     */
    public ComplexNumber(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    /*
     * getRealPart
     *
     * This returns the real part of the complex number.
     *
     * We provide a method instead of exposing the field directly.
     * That is standard Java practice and keeps control of the class design.
     */
    public double getRealPart() {
        return realPart;
    }

    /*
     * getImaginaryPart
     *
     * This returns the imaginary part of the complex number.
     */
    public double getImaginaryPart() {
        return imaginaryPart;
    }

    /*
     * add
     *
     * If:
     *
     *     z1 = a + ib
     *     z2 = c + id
     *
     * then:
     *
     *     z1 + z2 = (a + c) + i(b + d)
     *
     * This method does not modify the existing object.
     * Instead, it creates and returns a new ComplexNumber.
     *
     * That style is often easier to reason about mathematically.
     */
    public ComplexNumber add(ComplexNumber other) {

        double newRealPart = this.realPart + other.realPart;
        double newImaginaryPart = this.imaginaryPart + other.imaginaryPart;

        return new ComplexNumber(newRealPart, newImaginaryPart);
    }

    /*
     * subtract
     *
     * If:
     *
     *     z1 = a + ib
     *     z2 = c + id
     *
     * then:
     *
     *     z1 - z2 = (a - c) + i(b - d)
     */
    public ComplexNumber subtract(ComplexNumber other) {

        double newRealPart = this.realPart - other.realPart;
        double newImaginaryPart = this.imaginaryPart - other.imaginaryPart;

        return new ComplexNumber(newRealPart, newImaginaryPart);
    }

    /*
     * multiply
     *
     * Complex multiplication is:
     *
     *     (a + ib)(c + id) = (ac - bd) + i(ad + bc)
     *
     * This formula comes from the rule:
     *
     *     i^2 = -1
     */
    public ComplexNumber multiply(ComplexNumber other) {

        double newRealPart =
                this.realPart * other.realPart
                - this.imaginaryPart * other.imaginaryPart;

        double newImaginaryPart =
                this.realPart * other.imaginaryPart
                + this.imaginaryPart * other.realPart;

        return new ComplexNumber(newRealPart, newImaginaryPart);
    }

    /*
     * multiply
     *
     * This overloaded version multiplies by an ordinary real number.
     *
     * In Java, "overloaded" means we use the same method name with a
     * different input type.
     *
     * That allows both of these:
     *
     *     z.multiply(w)
     *     z.multiply(3.0)
     */
    public ComplexNumber multiply(double scalar) {
        return new ComplexNumber(
                this.realPart * scalar,
                this.imaginaryPart * scalar
        );
    }

    /*
     * divide
     *
     * To divide by another complex number, we use:
     *
     *     z / w = z * conjugate(w) / |w|^2
     *
     * because multiplying numerator and denominator by conjugate(w)
     * makes the denominator real.
     *
     * We also check for division by zero.
     */
    public ComplexNumber divide(ComplexNumber other) {

        double denominator =
                other.realPart * other.realPart
                + other.imaginaryPart * other.imaginaryPart;

        if (denominator == 0.0) {
            throw new ArithmeticException(
                    "Division by zero complex number."
            );
        }

        ComplexNumber numerator = this.multiply(other.conjugate());

        return new ComplexNumber(
                numerator.realPart / denominator,
                numerator.imaginaryPart / denominator
        );
    }

    /*
     * conjugate
     *
     * If:
     *
     *     z = a + ib
     *
     * then:
     *
     *     conjugate(z) = a - ib
     *
     * Conjugation is used constantly in complex algebra, norms,
     * inner products, and matrix calculations.
     */
    public ComplexNumber conjugate() {
        return new ComplexNumber(this.realPart, -this.imaginaryPart);
    }

    /*
     * abs
     *
     * This returns the magnitude of the complex number:
     *
     *     |z| = sqrt(a^2 + b^2)
     *
     * We use Math.hypot(a, b) because it is a standard Java method
     * designed for this purpose and is generally numerically safer
     * than manually computing sqrt(a*a + b*b).
     */
    public double abs() {
        return Math.hypot(realPart, imaginaryPart);
    }
    
    /*
     * zero, one
     *
     * Returns the complex number 0 + 0i.
     *
     * WHY THIS EXISTS
     * ---------------
     * Many classes need a clean and readable way to request the additive
     * identity in the complex numbers.
     */
    public static ComplexNumber zero() {
        return new ComplexNumber(0.0, 0.0);
    }
    public static ComplexNumber one() {
        return new ComplexNumber(1.0, 0.0);
    }
    
    
    /*
     * arg
     *
     * This returns the phase angle (also called the argument) of the
     * complex number in radians.
     *
     * We use Math.atan2(y, x), which correctly handles the quadrant.
     */
    public double arg() {
        return Math.atan2(imaginaryPart, realPart);
    }

    /*
     * exp
     *
     * If:
     *
     *     z = a + ib
     *
     * then:
     *
     *     exp(z) = exp(a) [cos(b) + i sin(b)]
     *
     * This is one of the fundamental formulas of complex analysis.
     */
    public ComplexNumber exp() {

        double scaleFactor = Math.exp(realPart);

        return new ComplexNumber(
                scaleFactor * Math.cos(imaginaryPart),
                scaleFactor * Math.sin(imaginaryPart)
        );
    }

    /*
     * expImaginary
     *
     * This is a convenience method for the special and very common case:
     *
     *     exp(i theta)
     *
     * Using Euler's formula:
     *
     *     exp(i theta) = cos(theta) + i sin(theta)
     *
     * Since this is used often in physics and group theory, it is worth
     * giving it a dedicated method.
     */
    public static ComplexNumber expImaginary(double theta) {
        return new ComplexNumber(Math.cos(theta), Math.sin(theta));
    }

    /*
     * toString
     *
     * This method tells Java how to convert the object into a printable string.
     *
     * That is why statements like:
     *
     *     System.out.println(z);
     *
     * automatically use this format.
     *
     * We also clean away tiny floating-point noise so that values that
     * should be zero do not print as ugly scientific garbage such as
     * 0.000000000000001.
     */
    @Override
    public String toString() {

        double displayRealPart = cleanSmall(realPart);
        double displayImaginaryPart = cleanSmall(imaginaryPart);

        if (displayImaginaryPart == 0.0) {
            return String.format("%.12f", displayRealPart);
        }

        if (displayRealPart == 0.0) {
            return String.format("%.12fi", displayImaginaryPart);
        }

        if (displayImaginaryPart > 0.0) {
            return String.format(
                    "%.12f + %.12fi",
                    displayRealPart,
                    displayImaginaryPart
            );
        } else {
            return String.format(
                    "%.12f - %.12fi",
                    displayRealPart,
                    Math.abs(displayImaginaryPart)
            );
        }
    }

    /*
     * cleanSmall
     *
     * Floating-point calculations often produce tiny numerical leftovers
     * when the mathematical answer should be exactly zero.
     *
     * This helper replaces very small values by exactly zero for display.
     *
     * We make it private because it is only an internal detail of how
     * the class prints itself.
     */
    private static double cleanSmall(double value) {

        double tolerance = 1.0e-12;

        if (Math.abs(value) < tolerance) {
            return 0.0;
        }

        return value;
    }
}