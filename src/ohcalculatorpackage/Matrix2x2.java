package ohcalculatorpackage;

/*
 * Matrix2x2
 *
 * This class represents a 2x2 matrix whose entries are complex numbers.
 *
 * GENERAL FORM
 * ------------
 * A general 2x2 complex matrix has the form:
 *
 *     [ a  b ]
 *     [ c  d ]
 *
 * where a, b, c, and d are complex numbers.
 *
 * WHY THIS CLASS MATTERS
 * ----------------------
 * This class is the next natural step after ComplexNumber and PauliValue.
 *
 * It will allow us to:
 *
 * 1. Represent Pauli algebra elements as 2x2 matrices.
 * 2. Compare abstract algebra calculations with matrix calculations.
 * 3. Build toward the various 2x2 subalgebras that appear later in
 *    the Oh calculator project.
 *
 * PROGRAMMING STYLE
 * -----------------
 * This class is written in a deliberately explicit and heavily commented
 * style so that graduate students can read it easily and modify it for
 * their own purposes.
 *
 * We do not try to be maximally abstract or general. We simply write
 * the 2x2 matrix code clearly and correctly for this project.
 *
 * @author Carl Brannen and ChatGPT
 */
public class Matrix2x2 {

    /*
     * topLeft
     *
     * Entry in row 1, column 1.
     */
    private final ComplexNumber topLeft;

    /*
     * topRight
     *
     * Entry in row 1, column 2.
     */
    private final ComplexNumber topRight;

    /*
     * bottomLeft
     *
     * Entry in row 2, column 1.
     */
    private final ComplexNumber bottomLeft;

    /*
     * bottomRight
     *
     * Entry in row 2, column 2.
     */
    private final ComplexNumber bottomRight;

    /*
     * Constructor
     *
     * This creates a 2x2 complex matrix from its four entries.
     *
     * INPUT ORDER
     * -----------
 * The entries are given in the natural reading order:
     *
     *     topLeft, topRight, bottomLeft, bottomRight
     *
     * so that:
     *
     *     new Matrix2x2(a, b, c, d)
     *
     * means the matrix:
     *
     *     [ a  b ]
     *     [ c  d ]
     */
    public Matrix2x2(
            ComplexNumber topLeft,
            ComplexNumber topRight,
            ComplexNumber bottomLeft,
            ComplexNumber bottomRight) {

        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    /*
     * getTopLeft
     *
     * Returns the entry in row 1, column 1.
     */
    public ComplexNumber getTopLeft() {
        return topLeft;
    }

    /*
     * getTopRight
     *
     * Returns the entry in row 1, column 2.
     */
    public ComplexNumber getTopRight() {
        return topRight;
    }

    /*
     * getBottomLeft
     *
     * Returns the entry in row 2, column 1.
     */
    public ComplexNumber getBottomLeft() {
        return bottomLeft;
    }

    /*
     * getBottomRight
     *
     * Returns the entry in row 2, column 2.
     */
    public ComplexNumber getBottomRight() {
        return bottomRight;
    }

    /*
     * zero
     *
     * Returns the additive zero matrix:
     *
     *     [ 0  0 ]
     *     [ 0  0 ]
     */
    public static Matrix2x2 zero() {
        return new Matrix2x2(
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0)
        );
    }

    /*
     * identity
     *
     * Returns the multiplicative identity matrix:
     *
     *     [ 1  0 ]
     *     [ 0  1 ]
     *
     * This is the matrix version of the number 1.
     */
    public static Matrix2x2 identity() {
        return new Matrix2x2(
                new ComplexNumber(1.0, 0.0),
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(0.0, 0.0),
                new ComplexNumber(1.0, 0.0)
        );
    }

    /*
     * add
     *
     * Matrix addition is performed entry by entry.
     *
     * If:
     *
     *     A = [ a  b ]    B = [ e  f ]
     *         [ c  d ]        [ g  h ]
     *
     * then:
     *
     *     A + B = [ a+e  b+f ]
     *             [ c+g  d+h ]
     */
    public Matrix2x2 add(Matrix2x2 other) {
        return new Matrix2x2(
                this.topLeft.add(other.topLeft),
                this.topRight.add(other.topRight),
                this.bottomLeft.add(other.bottomLeft),
                this.bottomRight.add(other.bottomRight)
        );
    }

    /*
     * subtract
     *
     * Matrix subtraction is also performed entry by entry.
     */
    public Matrix2x2 subtract(Matrix2x2 other) {
        return new Matrix2x2(
                this.topLeft.subtract(other.topLeft),
                this.topRight.subtract(other.topRight),
                this.bottomLeft.subtract(other.bottomLeft),
                this.bottomRight.subtract(other.bottomRight)
        );
    }

    /*
     * multiplyByComplex
     *
     * This multiplies every entry of the matrix by the same complex scalar.
     *
     * If z is a complex number and A is a matrix, then zA is obtained by
     * multiplying each entry of A by z.
     */
    public Matrix2x2 multiplyByComplex(ComplexNumber scalar) {
        return new Matrix2x2(
                this.topLeft.multiply(scalar),
                this.topRight.multiply(scalar),
                this.bottomLeft.multiply(scalar),
                this.bottomRight.multiply(scalar)
        );
    }

    /*
     * multiply
     *
     * This performs ordinary matrix multiplication.
     *
     * If:
     *
     *     A = [ a  b ]    B = [ e  f ]
     *         [ c  d ]        [ g  h ]
     *
     * then:
     *
     *     A * B = [ ae + bg   af + bh ]
     *             [ ce + dg   cf + dh ]
     *
     * This is one of the most important methods in the class.
     */
    public Matrix2x2 multiply(Matrix2x2 other) {

        /*
         * Compute the new top-left entry.
         *
         * This is:
         *
         *     (row 1 of this) dot (column 1 of other)
         */
        ComplexNumber newTopLeft =
                this.topLeft.multiply(other.topLeft)
                .add(this.topRight.multiply(other.bottomLeft));

        /*
         * Compute the new top-right entry.
         *
         * This is:
         *
         *     (row 1 of this) dot (column 2 of other)
         */
        ComplexNumber newTopRight =
                this.topLeft.multiply(other.topRight)
                .add(this.topRight.multiply(other.bottomRight));

        /*
         * Compute the new bottom-left entry.
         *
         * This is:
         *
         *     (row 2 of this) dot (column 1 of other)
         */
        ComplexNumber newBottomLeft =
                this.bottomLeft.multiply(other.topLeft)
                .add(this.bottomRight.multiply(other.bottomLeft));

        /*
         * Compute the new bottom-right entry.
         *
         * This is:
         *
         *     (row 2 of this) dot (column 2 of other)
         */
        ComplexNumber newBottomRight =
                this.bottomLeft.multiply(other.topRight)
                .add(this.bottomRight.multiply(other.bottomRight));

        return new Matrix2x2(
                newTopLeft,
                newTopRight,
                newBottomLeft,
                newBottomRight
        );
    }

    /*
     * trace
     *
     * The trace of a matrix is the sum of the diagonal entries.
     *
     * For:
     *
     *     [ a  b ]
     *     [ c  d ]
     *
     * the trace is:
     *
     *     a + d
     */
    public ComplexNumber trace() {
        return topLeft.add(bottomRight);
    }

    /*
     * determinant
     *
     * The determinant of a 2x2 matrix is:
     *
     *     ad - bc
     *
     * This quantity is important because a 2x2 matrix is invertible
     * exactly when its determinant is nonzero.
     */
    public ComplexNumber determinant() {
        return topLeft.multiply(bottomRight)
                .subtract(topRight.multiply(bottomLeft));
    }

    /*
     * toString
     *
     * This prints the matrix in a simple readable two-line form.
     *
     * The main purpose here is clarity for debugging and teaching.
     */
    @Override
    public String toString() {
        return "[ " + topLeft + " , " + topRight + " ]\n"
                + "[ " + bottomLeft + " , " + bottomRight + " ]";
    }
}