package ohcalculatorpackage;

/*
 * PauliValue
 *
 * This class represents an element of the Pauli algebra written in the basis:
 *
 *     1, sigma_x, sigma_y, sigma_z
 *
 * So a general PauliValue has the form:
 *
 *     a * 1 + b * sigma_x + c * sigma_y + d * sigma_z
 *
 * where:
 *
 *     a, b, c, d
 *
 * are complex numbers.
 *
 * WHY THIS CLASS MATTERS
 * ----------------------
 * This is the first noncommutative algebra in the Oh Calculator project.
 * Ordinary complex numbers commute under multiplication:
 *
 *     z1 * z2 = z2 * z1
 *
 * but the Pauli matrices do not. For example:
 *
 *     sigma_x * sigma_y =  i sigma_z
 *     sigma_y * sigma_x = -i sigma_z
 *
 * Therefore multiplication order matters.
 *
 * LONG-TERM ROLE IN THE PROJECT
 * -----------------------------
 * Later, PauliValue objects will be used as coefficients inside larger
 * algebras, especially the Oh group algebra over the Pauli algebra.
 *
 * PROGRAMMING STYLE
 * -----------------
 * This code is written to be easy to read and easy to modify, especially
 * by graduate students who may know the mathematics better than the Java.
 *
 * Therefore:
 *
 * - we use many comments,
 * - we keep methods fairly short,
 * - we avoid unnecessary generalization,
 * - we prefer clear formulas over clever tricks.
 *
 * @author Carl Brannen and ChatGPT
 */
public class PauliValue {

    /*
     * identityPart
     *
     * This is the coefficient of the algebra identity element 1.
     *
     * In:
     *
     *     a * 1 + b * sigma_x + c * sigma_y + d * sigma_z
     *
     * this field stores a.
     */
    private final ComplexNumber identityPart;

    /*
     * sigmaXPart
     *
     * This is the coefficient of sigma_x.
     */
    private final ComplexNumber sigmaXPart;

    /*
     * sigmaYPart
     *
     * This is the coefficient of sigma_y.
     */
    private final ComplexNumber sigmaYPart;

    /*
     * sigmaZPart
     *
     * This is the coefficient of sigma_z.
     */
    private final ComplexNumber sigmaZPart;

    /*
     * Constructor
     *
     * This constructs a general PauliValue from its four coefficients.
     *
     * INPUT ORDER
     * -----------
     * The coefficients are given in the order:
     *
     *     identity, sigma_x, sigma_y, sigma_z
     *
     * so that:
     *
     *     new PauliValue(a, b, c, d)
     *
     * means:
     *
     *     a * 1 + b * sigma_x + c * sigma_y + d * sigma_z
     */
    public PauliValue(
            ComplexNumber identityPart,
            ComplexNumber sigmaXPart,
            ComplexNumber sigmaYPart,
            ComplexNumber sigmaZPart) {

        this.identityPart = identityPart;
        this.sigmaXPart = sigmaXPart;
        this.sigmaYPart = sigmaYPart;
        this.sigmaZPart = sigmaZPart;
    }

    /*
     * getIdentityPart
     *
     * Returns the coefficient of the algebra identity element 1.
     */
    public ComplexNumber getIdentityPart() {
        return identityPart;
    }

    /*
     * getSigmaXPart
     *
     * Returns the coefficient of sigma_x.
     */
    public ComplexNumber getSigmaXPart() {
        return sigmaXPart;
    }

    /*
     * getSigmaYPart
     *
     * Returns the coefficient of sigma_y.
     */
    public ComplexNumber getSigmaYPart() {
        return sigmaYPart;
    }

    /*
     * getSigmaZPart
     *
     * Returns the coefficient of sigma_z.
     */
    public ComplexNumber getSigmaZPart() {
        return sigmaZPart;
    }

    /*
     * zero
     *
     * Returns the additive zero of the Pauli algebra:
     *
     *     0 * 1 + 0 * sigma_x + 0 * sigma_y + 0 * sigma_z
     *
     * This is useful in tests and in algebraic calculations.
     */
    public static PauliValue zero() {
        return new PauliValue(
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0)
        );
    }

    /*
     * identity
     *
     * Returns the multiplicative identity of the Pauli algebra:
     *
     *     1 * 1 + 0 * sigma_x + 0 * sigma_y + 0 * sigma_z
     */
    public static PauliValue identity() {
        return new PauliValue(
                new ComplexNumber(1.0, 0.0),
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0)
        );
    }

    /*
     * sigmaX
     *
     * Returns sigma_x itself:
     *
     *     0 * 1 + 1 * sigma_x + 0 * sigma_y + 0 * sigma_z
     */
    public static PauliValue sigmaX() {
        return new PauliValue(
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(1.0, 0.0),
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0)
        );
    }

    /*
     * sigmaY
     *
     * Returns sigma_y itself.
     */
    public static PauliValue sigmaY() {
        return new PauliValue(
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(1.0, 0.0),
                new ComplexNumber(0.0, 0.0)
        );
    }

    /*
     * sigmaZ
     *
     * Returns sigma_z itself.
     */
    public static PauliValue sigmaZ() {
        return new PauliValue(
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(1.0, 0.0)
        );
    }

    /*
     * add
     *
     * Addition in the Pauli algebra is coefficient-by-coefficient.
     *
     * If:
     *
     *     P = a * 1 + b * sigma_x + c * sigma_y + d * sigma_z
     *     Q = e * 1 + f * sigma_x + g * sigma_y + h * sigma_z
     *
     * then:
     *
     *     P + Q
     *       = (a+e) * 1
     *       + (b+f) * sigma_x
     *       + (c+g) * sigma_y
     *       + (d+h) * sigma_z
     *
     * This method does not alter either input object.
     * Instead, it returns a new PauliValue.
     */
    public PauliValue add(PauliValue other) {
        return new PauliValue(
                this.identityPart.add(other.identityPart),
                this.sigmaXPart.add(other.sigmaXPart),
                this.sigmaYPart.add(other.sigmaYPart),
                this.sigmaZPart.add(other.sigmaZPart)
        );
    }

    /*
     * subtract
     *
     * Subtraction is also coefficient-by-coefficient.
     */
    public PauliValue subtract(PauliValue other) {
        return new PauliValue(
                this.identityPart.subtract(other.identityPart),
                this.sigmaXPart.subtract(other.sigmaXPart),
                this.sigmaYPart.subtract(other.sigmaYPart),
                this.sigmaZPart.subtract(other.sigmaZPart)
        );
    }

    /*
     * multiplyByComplex
     *
     * This multiplies the entire PauliValue by a complex scalar.
     *
     * If:
     *
     *     P = a * 1 + b * sigma_x + c * sigma_y + d * sigma_z
     *
     * and z is a complex number, then:
     *
     *     z P
     *       = (z a) * 1
     *       + (z b) * sigma_x
     *       + (z c) * sigma_y
     *       + (z d) * sigma_z
     *
     * This is useful internally when building products.
     */
    public PauliValue multiplyByComplex(ComplexNumber scalar) {
        return new PauliValue(
                this.identityPart.multiply(scalar),
                this.sigmaXPart.multiply(scalar),
                this.sigmaYPart.multiply(scalar),
                this.sigmaZPart.multiply(scalar)
        );
    }

    /*
     * multiply
     *
     * This is the most important method in the class.
     *
     * It multiplies two general PauliValue objects.
     *
     * PAULI MULTIPLICATION RULES
     * --------------------------
     * The defining multiplication rules are:
     *
     *     sigma_x * sigma_x = 1
     *     sigma_y * sigma_y = 1
     *     sigma_z * sigma_z = 1
     *
     *     sigma_x * sigma_y =  i sigma_z
     *     sigma_y * sigma_z =  i sigma_x
     *     sigma_z * sigma_x =  i sigma_y
     *
     * and reversing the order changes the sign:
     *
     *     sigma_y * sigma_x = -i sigma_z
     *     sigma_z * sigma_y = -i sigma_x
     *     sigma_x * sigma_z = -i sigma_y
     *
     * STRATEGY USED HERE
     * ------------------
     * We expand the product term by term.
     *
     * If:
     *
     *     P = a*1 + b*sigma_x + c*sigma_y + d*sigma_z
     *     Q = e*1 + f*sigma_x + g*sigma_y + h*sigma_z
     *
     * then P*Q is obtained by distributing every term in P over every term in Q.
     *
     * We do this in a very explicit way so that the code mirrors the algebra.
     * This is longer than some more compact approaches, but much easier to read.
     */
    public PauliValue multiply(PauliValue other) {

        /*
         * Start with the zero value.
         *
         * We will accumulate product contributions into this variable.
         */
        PauliValue result = PauliValue.zero();

        /*
         * ------------------------------------------------------------
         * Contributions from this.identityPart times the other value
         * ------------------------------------------------------------
         *
         * Since the identity element 1 acts trivially under multiplication,
         * these contributions simply copy the structure of the other value.
         *
         *     (a*1)(e*1 + f*sigma_x + g*sigma_y + h*sigma_z)
         *       = ae*1 + af*sigma_x + ag*sigma_y + ah*sigma_z
         */
        result = result.add(new PauliValue(
                this.identityPart.multiply(other.identityPart),
                this.identityPart.multiply(other.sigmaXPart),
                this.identityPart.multiply(other.sigmaYPart),
                this.identityPart.multiply(other.sigmaZPart)
        ));

        /*
         * ------------------------------------------------------------
         * Contributions from this.sigmaXPart times the other value
         * ------------------------------------------------------------
         *
         * We use:
         *
         *     sigma_x * 1       = sigma_x
         *     sigma_x * sigma_x = 1
         *     sigma_x * sigma_y =  i sigma_z
         *     sigma_x * sigma_z = -i sigma_y
         */
        result = result.add(new PauliValue(
                this.sigmaXPart.multiply(other.sigmaXPart),
                this.sigmaXPart.multiply(other.identityPart),
                this.sigmaXPart.multiply(other.sigmaZPart)
                        .multiply(new ComplexNumber(0.0, -1.0)),
                this.sigmaXPart.multiply(other.sigmaYPart)
                        .multiply(new ComplexNumber(0.0, 1.0))
        ));

        /*
         * ------------------------------------------------------------
         * Contributions from this.sigmaYPart times the other value
         * ------------------------------------------------------------
         *
         * We use:
         *
         *     sigma_y * 1       = sigma_y
         *     sigma_y * sigma_x = -i sigma_z
         *     sigma_y * sigma_y = 1
         *     sigma_y * sigma_z = i sigma_x
         */
        result = result.add(new PauliValue(
                this.sigmaYPart.multiply(other.sigmaYPart),
                this.sigmaYPart.multiply(other.sigmaZPart)
                        .multiply(new ComplexNumber(0.0, 1.0)),
                this.sigmaYPart.multiply(other.identityPart),
                this.sigmaYPart.multiply(other.sigmaXPart)
                        .multiply(new ComplexNumber(0.0, -1.0))
        ));

        /*
         * ------------------------------------------------------------
         * Contributions from this.sigmaZPart times the other value
         * ------------------------------------------------------------
         *
         * We use:
         *
         *     sigma_z * 1       = sigma_z
         *     sigma_z * sigma_x = i sigma_y
         *     sigma_z * sigma_y = -i sigma_x
         *     sigma_z * sigma_z = 1
         */
        result = result.add(new PauliValue(
                this.sigmaZPart.multiply(other.sigmaZPart),
                this.sigmaZPart.multiply(other.sigmaYPart)
                        .multiply(new ComplexNumber(0.0, -1.0)),
                this.sigmaZPart.multiply(other.sigmaXPart)
                        .multiply(new ComplexNumber(0.0, 1.0)),
                this.sigmaZPart.multiply(other.identityPart)
        ));

        /*
         * Return the accumulated result.
         */
        return result;
    }
    /*
     * toMatrix2x2
     *
     * This converts the current PauliValue into its standard 2x2 complex
     * matrix representation.
     *
     * STANDARD PAULI MATRIX REPRESENTATION
     * ------------------------------------
     * We use:
     *
     *     1 =
     *         [ 1  0 ]
     *         [ 0  1 ]
     *
     *     sigma_x =
     *         [ 0  1 ]
     *         [ 1  0 ]
     *
     *     sigma_y =
     *         [ 0  -i ]
     *         [ i   0 ]
     *
     *     sigma_z =
     *         [ 1   0 ]
     *         [ 0  -1 ]
     *
     * Therefore, if:
     *
     *     P = a * 1 + b * sigma_x + c * sigma_y + d * sigma_z
     *
     * then the corresponding matrix is:
     *
     *     [ a + d      b - i c ]
     *     [ b + i c    a - d   ]
     *
     * WHY THIS METHOD MATTERS
     * -----------------------
     * This gives us a bridge between:
     *
     * - the abstract Pauli algebra
     * - explicit 2x2 matrix calculations
     *
     * That bridge will later be extremely useful when we begin extracting
     * and viewing 2x2 subalgebras inside the larger Oh algebra.
     */
    public Matrix2x2 toMatrix2x2() {

        /*
         * Define the complex numbers i and -i explicitly.
         *
         * We do this to make the formulas below easy to read.
         */
        ComplexNumber i = new ComplexNumber(0.0, 1.0);
        ComplexNumber minusI = new ComplexNumber(0.0, -1.0);

        /*
         * Compute the four matrix entries using the standard formula:
         *
         *     [ a + d      b - i c ]
         *     [ b + i c    a - d   ]
         */
        ComplexNumber topLeft =
                this.identityPart.add(this.sigmaZPart);

        ComplexNumber topRight =
                this.sigmaXPart.add(this.sigmaYPart.multiply(minusI));

        ComplexNumber bottomLeft =
                this.sigmaXPart.add(this.sigmaYPart.multiply(i));

        ComplexNumber bottomRight =
                this.identityPart.subtract(this.sigmaZPart);

        /*
         * Return the resulting 2x2 matrix.
         */
        return new Matrix2x2(
                topLeft,
                topRight,
                bottomLeft,
                bottomRight
        );
    }
    /*
     * toString
     *
     * This converts the PauliValue into a readable string.
     *
     * STYLE OF OUTPUT
     * ---------------
     * We print all four coefficients explicitly, even when some are zero.
     *
     * This is a deliberate teaching choice. It makes the basis visible
     * at all times, which is useful for learning and debugging.
     *
     * Example:
     *
     *     [1.000000000000] * 1
     *   + [2.000000000000] * sigma_x
     *   + [0.000000000000] * sigma_y
     *   + [3.000000000000] * sigma_z
     *
     * Later, if we decide we want a more compact printing style, we can
     * add another method. For now, explicitness is better.
     */
    @Override
    public String toString() {
        return "[" + identityPart + "] * 1"
                + " + [" + sigmaXPart + "] * sigma_x"
                + " + [" + sigmaYPart + "] * sigma_y"
                + " + [" + sigmaZPart + "] * sigma_z";
    }
}