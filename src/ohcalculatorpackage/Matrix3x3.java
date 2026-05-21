package ohcalculatorpackage;

/*
 * Matrix3x3
 *
 * This class represents a 3x3 matrix with ComplexNumber entries.
 *
 * WHY THIS CLASS EXISTS
 * ---------------------
 * The Oh calculator now has verified SU(3)-type blocks written in the group
 * algebra basis. The next natural stage is to compare those blocks against
 * ordinary 3x3 matrix algebra.
 *
 * So before we do basis conversion, we want a clean and trustworthy 3x3 matrix
 * class with:
 *
 * - construction
 * - addition
 * - subtraction
 * - scalar multiplication
 * - matrix multiplication
 * - identity and zero
 * - approximate equality testing
 *
 * STYLE
 * -----
 * This is written in the same explicit and heavily commented style as the rest
 * of the project.
 */
public class Matrix3x3 {

    /*
     * The nine entries of the matrix.
     *
     * We store these in row-major order:
     *
     *     m00 m01 m02
     *     m10 m11 m12
     *     m20 m21 m22
     */
    private final ComplexNumber m00;
    private final ComplexNumber m01;
    private final ComplexNumber m02;

    private final ComplexNumber m10;
    private final ComplexNumber m11;
    private final ComplexNumber m12;

    private final ComplexNumber m20;
    private final ComplexNumber m21;
    private final ComplexNumber m22;

    /*
     * Constructor
     *
     * All nine entries are supplied explicitly.
     */
    public Matrix3x3(
            ComplexNumber inputM00, ComplexNumber inputM01, ComplexNumber inputM02,
            ComplexNumber inputM10, ComplexNumber inputM11, ComplexNumber inputM12,
            ComplexNumber inputM20, ComplexNumber inputM21, ComplexNumber inputM22) {

        this.m00 = inputM00;
        this.m01 = inputM01;
        this.m02 = inputM02;

        this.m10 = inputM10;
        this.m11 = inputM11;
        this.m12 = inputM12;

        this.m20 = inputM20;
        this.m21 = inputM21;
        this.m22 = inputM22;
    }

    /*
     * zero
     *
     * Returns the 3x3 zero matrix.
     */
    public static Matrix3x3 zero() {
        ComplexNumber z = ComplexNumber.zero();
        return new Matrix3x3(
                z, z, z,
                z, z, z,
                z, z, z
        );
    }

    /*
     * identity
     *
     * Returns the 3x3 identity matrix.
     */
    public static Matrix3x3 identity() {
        ComplexNumber z = ComplexNumber.zero();
        ComplexNumber o = ComplexNumber.one();

        return new Matrix3x3(
                o, z, z,
                z, o, z,
                z, z, o
        );
    }

    /*
     * diagonal
     *
     * Returns a diagonal 3x3 matrix.
     */
    public static Matrix3x3 diagonal(
            ComplexNumber d00,
            ComplexNumber d11,
            ComplexNumber d22) {

        ComplexNumber z = ComplexNumber.zero();

        return new Matrix3x3(
                d00, z,   z,
                z,   d11, z,
                z,   z,   d22
        );
    }

    /*
     * basis
     *
     * Returns the matrix unit E_(row,column), using zero-based indices.
     *
     * Example:
     *     basis(0, 1)
     *
     * gives the matrix with a single 1 in the first row, second column.
     */
    public static Matrix3x3 basis(int row, int column) {

        ComplexNumber z = ComplexNumber.zero();
        ComplexNumber o = ComplexNumber.one();

        ComplexNumber a00 = z;
        ComplexNumber a01 = z;
        ComplexNumber a02 = z;
        ComplexNumber a10 = z;
        ComplexNumber a11 = z;
        ComplexNumber a12 = z;
        ComplexNumber a20 = z;
        ComplexNumber a21 = z;
        ComplexNumber a22 = z;

        if (row == 0 && column == 0) {
            a00 = o;
        } else if (row == 0 && column == 1) {
            a01 = o;
        } else if (row == 0 && column == 2) {
            a02 = o;
        } else if (row == 1 && column == 0) {
            a10 = o;
        } else if (row == 1 && column == 1) {
            a11 = o;
        } else if (row == 1 && column == 2) {
            a12 = o;
        } else if (row == 2 && column == 0) {
            a20 = o;
        } else if (row == 2 && column == 1) {
            a21 = o;
        } else if (row == 2 && column == 2) {
            a22 = o;
        } else {
            throw new IllegalArgumentException(
                    "Matrix3x3.basis received invalid row/column: " + row + ", " + column
            );
        }

        return new Matrix3x3(
                a00, a01, a02,
                a10, a11, a12,
                a20, a21, a22
        );
    }

    /*
     * Getters
     */
    public ComplexNumber getM00() { return m00; }
    public ComplexNumber getM01() { return m01; }
    public ComplexNumber getM02() { return m02; }

    public ComplexNumber getM10() { return m10; }
    public ComplexNumber getM11() { return m11; }
    public ComplexNumber getM12() { return m12; }

    public ComplexNumber getM20() { return m20; }
    public ComplexNumber getM21() { return m21; }
    public ComplexNumber getM22() { return m22; }

    /*
     * getEntry
     *
     * Returns one entry using zero-based row and column indices.
     */
    public ComplexNumber getEntry(int row, int column) {

        if (row == 0 && column == 0) { return m00; }
        if (row == 0 && column == 1) { return m01; }
        if (row == 0 && column == 2) { return m02; }

        if (row == 1 && column == 0) { return m10; }
        if (row == 1 && column == 1) { return m11; }
        if (row == 1 && column == 2) { return m12; }

        if (row == 2 && column == 0) { return m20; }
        if (row == 2 && column == 1) { return m21; }
        if (row == 2 && column == 2) { return m22; }

        throw new IllegalArgumentException(
                "Matrix3x3.getEntry received invalid row/column: " + row + ", " + column
        );
    }

    /*
     * add
     *
     * Returns this + other.
     */
    public Matrix3x3 add(Matrix3x3 other) {

        return new Matrix3x3(
                m00.add(other.m00), m01.add(other.m01), m02.add(other.m02),
                m10.add(other.m10), m11.add(other.m11), m12.add(other.m12),
                m20.add(other.m20), m21.add(other.m21), m22.add(other.m22)
        );
    }

    /*
     * subtract
     *
     * Returns this - other.
     */
    public Matrix3x3 subtract(Matrix3x3 other) {

        return new Matrix3x3(
                m00.subtract(other.m00), m01.subtract(other.m01), m02.subtract(other.m02),
                m10.subtract(other.m10), m11.subtract(other.m11), m12.subtract(other.m12),
                m20.subtract(other.m20), m21.subtract(other.m21), m22.subtract(other.m22)
        );
    }

    /*
     * scale
     *
     * Returns scalar * this.
     */
    public Matrix3x3 scale(ComplexNumber scalar) {

        return new Matrix3x3(
                scalar.multiply(m00), scalar.multiply(m01), scalar.multiply(m02),
                scalar.multiply(m10), scalar.multiply(m11), scalar.multiply(m12),
                scalar.multiply(m20), scalar.multiply(m21), scalar.multiply(m22)
        );
    }

    /*
     * multiply
     *
     * Returns this * other.
     */
    public Matrix3x3 multiply(Matrix3x3 other) {

        return new Matrix3x3(
                Entry(0, 0, other), Entry(0, 1, other), Entry(0, 2, other),
                Entry(1, 0, other), Entry(1, 1, other), Entry(1, 2, other),
                Entry(2, 0, other), Entry(2, 1, other), Entry(2, 2, other)
        );
    }

    /*
     * conjugateTranspose
     *
     * Returns the Hermitian conjugate of this matrix.
     */
    public Matrix3x3 conjugateTranspose() {

        return new Matrix3x3(
                m00.conjugate(), m10.conjugate(), m20.conjugate(),
                m01.conjugate(), m11.conjugate(), m21.conjugate(),
                m02.conjugate(), m12.conjugate(), m22.conjugate()
        );
    }

    /*
     * trace
     *
     * Returns the trace of the matrix.
     */
    public ComplexNumber trace() {
        return m00.add(m11).add(m22);
    }

    /*
     * Entry
     *
     * Computes one entry of the matrix product this * other.
     */
    private ComplexNumber Entry(int row, int column, Matrix3x3 other) {

        ComplexNumber sum = ComplexNumber.zero();

        for (int k = 0; k < 3; k++) {
            sum = sum.add(this.getEntry(row, k).multiply(other.getEntry(k, column)));
        }

        return sum;
    }














    
    @Override
    public String toString() {
        return "[[" + m00 + ", " + m01 + ", " + m02 + "], "
                + "[" + m10 + ", " + m11 + ", " + m12 + "], "
                + "[" + m20 + ", " + m21 + ", " + m22 + "]]";
    }
}