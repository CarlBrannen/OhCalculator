package ohcalculatorpackage;

/*
 * OhFourierBasis
 *
 * This class stores the generalized Fourier-transform basis elements for Oh
 * as OhElement objects.
 *
 * VERY IMPORTANT DESIGN DECISION
 * ------------------------------
 * We are not recomputing these basis elements from scratch.
 *
 * Instead, we are translating them from the previously obtained Python output.
 * That Python output already contains the sparse and elegant basis you want,
 * and preserving that structure is the whole point.
 *
 * FIRST STAGE OF THE TRANSLATION
 * ------------------------------
 * In this first version, we translate only the first four transformed basis
 * elements, which are the singlet sector.
 *
 * WHY START WITH THE FIRST FOUR?
 * ------------------------------
 * Because they are simple, highly symmetric, and easy to verify by eye:
 *
 *     transformed basis 0
 *     transformed basis 1
 *     transformed basis 2
 *     transformed basis 3
 *
 * These correspond to the four one-dimensional irreducible sectors seen at
 * the beginning of the uploaded Fourier transform data. :contentReference[oaicite:1]{index=1}
 *
 * LATER PLAN
 * ----------
 * After this class compiles and the first few tests pass, we will continue
 * translating the remaining transformed basis elements in order.
 *
 * NUMERICAL STYLE
 * ---------------
 * The uploaded file uses 5-digit decimal approximations such as:
 *
 *     0.02083
 *     0.04167
 *     0.06250
 *     0.07217
 *
 * Wherever possible, we translate these into exact rational or square-root
 * forms in Java.
 *
 * For example:
 *
 *     0.02083  ->  1/48
 *
 * This makes the code more readable and more accurate than decimal literals.
 *
 * @author Carl Brannen and ChatGPT
 */
public class OhFourierBasis {

    /*
     * NUMBER_OF_BASIS_ELEMENTS
     *
     * The Oh group has 48 basis elements.
     */
    private static final int NUMBER_OF_BASIS_ELEMENTS = 48;

    /*
     * Exact real constants that already appear in the uploaded Fourier data.
     *
     * We begin with the ones needed for the first four transformed basis
     * elements.
     */
    private static final double ONE_OVER_48 = 1.0 / 48.0;

    /*
     * Real
     *
     * Convenience constructor for a purely real complex number.
     *
     * This keeps the later basis-element definitions easier to read.
     */
    private static ComplexNumber Real(double value) {
        return new ComplexNumber(value, 0.0);
    }
    /*
     * Additional exact constants needed for Fourier-basis elements 4 through 11.
     */
    private static final double ONE_OVER_24 = 1.0 / 24.0;
    private static final double ONE_OVER_12 = 1.0 / 12.0;
    private static final double ONE_OVER_16 = 1.0 / 16.0;
    private static final double ONE_OVER_8  = 1.0 / 8.0;
    private static final double THREE_OVER_16 = 3.0 / 16.0;

    private static final double ROOT_THREE_OVER_48 = Math.sqrt(3.0) / 48.0;
    private static final double ROOT_THREE_OVER_24 = Math.sqrt(3.0) / 24.0;
    private static final double ROOT_THREE_OVER_12 = Math.sqrt(3.0) / 12.0;

    /*
     * Imaginary
     *
     * Convenience constructor for a purely imaginary complex number.
     */
    private static ComplexNumber Imaginary(double value) {
        return new ComplexNumber(0.0, value);
    }

    /*
     * SetImaginaryCoefficient
     *
     * Sets one slot of a coefficient array to a purely imaginary value.
     */
    private static void SetImaginaryCoefficient(
            ComplexNumber[] coefficients,
            int basisIndex,
            double value) {

        coefficients[basisIndex] = Imaginary(value);
    }
    /*
     * ZeroCoefficients
     *
     * Returns a fresh 48-entry coefficient array initialized to zero.
     *
     * WHY THIS EXISTS
     * ---------------
     * Many Fourier-basis elements are sparse. So the natural pattern is:
     *
     * 1. start with all coefficients zero
     * 2. set only the nonzero slots
     * 3. build the OhElement from that array
     */
    private static ComplexNumber[] ZeroCoefficients() {

        ComplexNumber[] coefficients = new ComplexNumber[NUMBER_OF_BASIS_ELEMENTS];

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            coefficients[index] = Real(0.0);
        }

        return coefficients;
    }
    /*
     * Negate
     *
     * Returns the additive inverse of an OhElement.
     *
     * WHY THIS EXISTS
     * ---------------
     * Some Fourier-basis generators differ by a global sign convention.
     * In particular, the SU(3) imaginary off-diagonal generators
     * lambda2, lambda5, lambda7 in each triplet block need a sign flip
     * to match the Java multiplication convention.
     */
    private static OhElement Negate(OhElement value) {

        ComplexNumber[] coefficients =
                new ComplexNumber[OhElement.getNumberOfBasisElements()];

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {
            ComplexNumber c = value.getCoefficient(index);
            coefficients[index] = new ComplexNumber(-c.getRealPart(), -c.getImaginaryPart());
        }

        return new OhElement(coefficients);
    }
    /*
     * SetRealCoefficient
     *
     * Sets one slot of a coefficient array to a real number.
     *
     * This helper exists only to keep the basis-element definitions short.
     */
    private static void SetRealCoefficient(
            ComplexNumber[] coefficients,
            int basisIndex,
            double value) {

        coefficients[basisIndex] = Real(value);
    }

    /*
     * GetElement
     *
     * Returns one transformed basis element by index.
     *
     * CURRENT STATUS
     * --------------
     * In this first version, only indices 0 through 3 are implemented.
     *
     * Later we will continue adding the remaining transformed basis elements.
     */
    public static OhElement GetElement(int transformedBasisIndex) {

        if (transformedBasisIndex == 0) {
            return GetElement0();
        } else if (transformedBasisIndex == 1) {
            return GetElement1();
        } else if (transformedBasisIndex == 2) {
            return GetElement2();
        } else if (transformedBasisIndex == 3) {
            return GetElement3();
        } else if (transformedBasisIndex == 4) {
            return GetElement4();
        } else if (transformedBasisIndex == 5) {
            return GetElement5();
        } else if (transformedBasisIndex == 6) {
            return GetElement6();
        } else if (transformedBasisIndex == 7) {
            return GetElement7();
        } else if (transformedBasisIndex == 8) {
            return GetElement8();
        } else if (transformedBasisIndex == 9) {
            return GetElement9();
        } else if (transformedBasisIndex == 10) {
            return GetElement10();
        } else if (transformedBasisIndex == 11) {
            return GetElement11();
        } else if (transformedBasisIndex == 12) {
            return GetElement12();
        } else if (transformedBasisIndex == 13) {
            return GetElement13();
        } else if (transformedBasisIndex == 14) {
            return GetElement14();
        } else if (transformedBasisIndex == 15) {
            return GetElement15();
        } else if (transformedBasisIndex == 16) {
            return GetElement16();
        } else if (transformedBasisIndex == 17) {
            return GetElement17();
        } else if (transformedBasisIndex == 18) {
            return GetElement18();
        } else if (transformedBasisIndex == 19) {
            return GetElement19();
        } else if (transformedBasisIndex == 20) {
            return GetElement20();
        } else if (transformedBasisIndex == 21) {
            return GetElement21();
        } else if (transformedBasisIndex == 22) {
            return GetElement22();
        } else if (transformedBasisIndex == 23) {
            return GetElement23();
        } else if (transformedBasisIndex == 24) {
            return GetElement24();
        } else if (transformedBasisIndex == 25) {
            return GetElement25();
        } else if (transformedBasisIndex == 26) {
            return GetElement26();
        } else if (transformedBasisIndex == 27) {
            return GetElement27();
        } else if (transformedBasisIndex == 28) {
            return GetElement28();
        } else if (transformedBasisIndex == 29) {
            return GetElement29();
        } else if (transformedBasisIndex == 30) {
            return GetElement30();
        } else if (transformedBasisIndex == 31) {
            return GetElement31();
        } else if (transformedBasisIndex == 32) {
            return GetElement32();
        } else if (transformedBasisIndex == 33) {
            return GetElement33();
        } else if (transformedBasisIndex == 34) {
            return GetElement34();
        } else if (transformedBasisIndex == 35) {
            return GetElement35();
        } else if (transformedBasisIndex == 36) {
            return GetElement36();
        } else if (transformedBasisIndex == 37) {
            return GetElement37();
        } else if (transformedBasisIndex == 38) {
            return GetElement38();
        } else if (transformedBasisIndex == 39) {
            return GetElement39();
        } else if (transformedBasisIndex == 40) {
            return GetElement40();
        } else if (transformedBasisIndex == 41) {
            return GetElement41();
        } else if (transformedBasisIndex == 42) {
            return GetElement42();
        } else if (transformedBasisIndex == 43) {
            return GetElement43();
        } else if (transformedBasisIndex == 44) {
            return GetElement44();
        } else if (transformedBasisIndex == 45) {
            return GetElement45();
        } else if (transformedBasisIndex == 46) {
            return GetElement46();
        } else if (transformedBasisIndex == 47) {
            return GetElement47();
        } else {
            throw new IllegalArgumentException(
                    "OhFourierBasis.GetElement currently implements only indices 0 through 47."
            );
        }    }

    /*
     * GetElement0
     *
     * Translated from Fourier-basis element 0 in the uploaded data.
     *
     * PATTERN
     * -------
     * Every one of the 48 group basis elements has coefficient +1/48.
     *
     * This is the completely uniform singlet. :contentReference[oaicite:2]{index=2}
     */
    public static OhElement GetElement0() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            SetRealCoefficient(coefficients, index, ONE_OVER_48);
        }

        return new OhElement(coefficients);
    }

    /*
     * GetElement1
     *
     * Translated from Fourier-basis element 1 in the uploaded data.
     *
     * PATTERN
     * -------
     * Basis indices 0 through 23 have coefficient +1/48.
     * Basis indices 24 through 47 have coefficient -1/48.
     *
     * This is the parity-like singlet separating proper and improper sectors. :contentReference[oaicite:3]{index=3}
     */
    public static OhElement GetElement1() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        /*
         * Proper sector: indices 0 through 23.
         */
        for (int index = 0; index < 24; index++) {
            SetRealCoefficient(coefficients, index, ONE_OVER_48);
        }

        /*
         * Improper sector: indices 24 through 47.
         */
        for (int index = 24; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            SetRealCoefficient(coefficients, index, -ONE_OVER_48);
        }

        return new OhElement(coefficients);
    }

    /*
     * GetElement2
     *
     * Translated from Fourier-basis element 2 in the uploaded data.
     *
     * PATTERN
     * -------
     * Positive on classes:
     *
     *     class 0  size 1   indices 0
     *     class 1  size 3   indices 1..3
     *     class 2  size 8   indices 4..11
     *     class 5  size 1   index 24
     *     class 6  size 3   indices 25..27
     *     class 7  size 8   indices 28..35
     *
     * Negative on classes:
     *
     *     class 3  size 6   indices 12..17
     *     class 4  size 6   indices 18..23
     *     class 8  size 6   indices 36..41
     *     class 9  size 6   indices 42..47
     *
     * All nonzero coefficients have magnitude 1/48. :contentReference[oaicite:4]{index=4}
     */
    public static OhElement GetElement2() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        /*
         * Positive blocks.
         */
        for (int index = 0; index <= 11; index++) {
            SetRealCoefficient(coefficients, index, ONE_OVER_48);
        }

        for (int index = 24; index <= 35; index++) {
            SetRealCoefficient(coefficients, index, ONE_OVER_48);
        }

        /*
         * Negative blocks.
         */
        for (int index = 12; index <= 23; index++) {
            SetRealCoefficient(coefficients, index, -ONE_OVER_48);
        }

        for (int index = 36; index <= 47; index++) {
            SetRealCoefficient(coefficients, index, -ONE_OVER_48);
        }

        return new OhElement(coefficients);
    }

    /*
     * GetElement3
     *
     * Translated from Fourier-basis element 3 in the uploaded data.
     *
     * PATTERN
     * -------
     * On the proper sector, the signs are the same as in GetElement2():
     *
     *     indices 0..11   positive
     *     indices 12..23  negative
     *
     * On the improper sector, the signs are reversed relative to GetElement2():
     *
     *     indices 24..35  negative
     *     indices 36..47  positive
     *
     * Again, every nonzero coefficient has magnitude 1/48. :contentReference[oaicite:5]{index=5}
     */
    public static OhElement GetElement3() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        /*
         * Proper sector.
         */
        for (int index = 0; index <= 11; index++) {
            SetRealCoefficient(coefficients, index, ONE_OVER_48);
        }

        for (int index = 12; index <= 23; index++) {
            SetRealCoefficient(coefficients, index, -ONE_OVER_48);
        }

        /*
         * Improper sector.
         */
        for (int index = 24; index <= 35; index++) {
            SetRealCoefficient(coefficients, index, -ONE_OVER_48);
        }

        for (int index = 36; index <= 47; index++) {
            SetRealCoefficient(coefficients, index, ONE_OVER_48);
        }

        return new OhElement(coefficients);
    }
    
        /*
     * GetElement4
     *
     * Fourier-basis element 4.
     *
     * Pattern from uploaded data:
     *   indices 0..3, 24..27 have coefficient +1/12
     *   indices 4..11, 28..35 have coefficient -1/24
     *   all others are zero. :contentReference[oaicite:1]{index=1}
     */
    public static OhElement GetElement4() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        for (int index = 0; index <= 3; index++) {
            SetRealCoefficient(coefficients, index, ONE_OVER_12);
        }
        for (int index = 4; index <= 11; index++) {
            SetRealCoefficient(coefficients, index, -ONE_OVER_24);
        }
        for (int index = 24; index <= 27; index++) {
            SetRealCoefficient(coefficients, index, ONE_OVER_12);
        }
        for (int index = 28; index <= 35; index++) {
            SetRealCoefficient(coefficients, index, -ONE_OVER_24);
        }

        return new OhElement(coefficients);
    }

    /*
     * GetElement5
     *
     * Fourier-basis element 5.
     *
     * Sparse real element with coefficients ±sqrt(3)/24 on selected proper
     * quarter-turn and transposition slots, and the same signs on their
     * inverted partners. :contentReference[oaicite:2]{index=2}
     */
    public static OhElement GetElement5() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 16,  ROOT_THREE_OVER_24);  // (1243)
        SetRealCoefficient(coefficients, 14, -ROOT_THREE_OVER_24);  // (1324)
        SetRealCoefficient(coefficients, 17,  ROOT_THREE_OVER_24);  // (1342)
        SetRealCoefficient(coefficients, 15, -ROOT_THREE_OVER_24);  // (1423)
        SetRealCoefficient(coefficients, 18, -ROOT_THREE_OVER_24);  // (12)
        SetRealCoefficient(coefficients, 20,  ROOT_THREE_OVER_24);  // (14)
        SetRealCoefficient(coefficients, 21,  ROOT_THREE_OVER_24);  // (23)
        SetRealCoefficient(coefficients, 23, -ROOT_THREE_OVER_24);  // (34)

        SetRealCoefficient(coefficients, 40,  ROOT_THREE_OVER_24);  // (1243)i
        SetRealCoefficient(coefficients, 38, -ROOT_THREE_OVER_24);  // (1324)i
        SetRealCoefficient(coefficients, 41,  ROOT_THREE_OVER_24);  // (1342)i
        SetRealCoefficient(coefficients, 39, -ROOT_THREE_OVER_24);  // (1423)i
        SetRealCoefficient(coefficients, 42, -ROOT_THREE_OVER_24);  // (12)i
        SetRealCoefficient(coefficients, 44,  ROOT_THREE_OVER_24);  // (14)i
        SetRealCoefficient(coefficients, 45,  ROOT_THREE_OVER_24);  // (23)i
        SetRealCoefficient(coefficients, 47, -ROOT_THREE_OVER_24);  // (34)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement6
     *
     * Fourier-basis element 6.
     *
     * Sparse purely imaginary element on the 3-cycle sectors, with the same
     * imaginary sign pattern on the inverted partners. :contentReference[oaicite:3]{index=3}
     */
    public static OhElement GetElement6() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,   ROOT_THREE_OVER_24);  // (123)
        SetImaginaryCoefficient(coefficients, 5,  -ROOT_THREE_OVER_24);  // (124)
        SetImaginaryCoefficient(coefficients, 8,  -ROOT_THREE_OVER_24);  // (132)
        SetImaginaryCoefficient(coefficients, 6,   ROOT_THREE_OVER_24);  // (134)
        SetImaginaryCoefficient(coefficients, 11,  ROOT_THREE_OVER_24);  // (142)
        SetImaginaryCoefficient(coefficients, 10, -ROOT_THREE_OVER_24);  // (143)
        SetImaginaryCoefficient(coefficients, 7,  -ROOT_THREE_OVER_24);  // (234)
        SetImaginaryCoefficient(coefficients, 9,   ROOT_THREE_OVER_24);  // (243)

        SetImaginaryCoefficient(coefficients, 28,  ROOT_THREE_OVER_24);  // (123)i
        SetImaginaryCoefficient(coefficients, 29, -ROOT_THREE_OVER_24);  // (124)i
        SetImaginaryCoefficient(coefficients, 32, -ROOT_THREE_OVER_24);  // (132)i
        SetImaginaryCoefficient(coefficients, 30,  ROOT_THREE_OVER_24);  // (134)i
        SetImaginaryCoefficient(coefficients, 35,  ROOT_THREE_OVER_24);  // (142)i
        SetImaginaryCoefficient(coefficients, 34, -ROOT_THREE_OVER_24);  // (143)i
        SetImaginaryCoefficient(coefficients, 31, -ROOT_THREE_OVER_24);  // (234)i
        SetImaginaryCoefficient(coefficients, 33,  ROOT_THREE_OVER_24);  // (243)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement7
     *
     * Fourier-basis element 7.
     *
     * Supported on classes 3, 4, 8, 9 with coefficients ±1/12 and ±1/24,
     * same signs on proper and improper sectors. :contentReference[oaicite:4]{index=4}
     */
    public static OhElement GetElement7() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 12, -ONE_OVER_12);  // (1234)
        SetRealCoefficient(coefficients, 16,  ONE_OVER_24);  // (1243)
        SetRealCoefficient(coefficients, 14,  ONE_OVER_24);  // (1324)
        SetRealCoefficient(coefficients, 17,  ONE_OVER_24);  // (1342)
        SetRealCoefficient(coefficients, 15,  ONE_OVER_24);  // (1423)
        SetRealCoefficient(coefficients, 13, -ONE_OVER_12);  // (1432)
        SetRealCoefficient(coefficients, 18,  ONE_OVER_24);  // (12)
        SetRealCoefficient(coefficients, 19, -ONE_OVER_12);  // (13)
        SetRealCoefficient(coefficients, 20,  ONE_OVER_24);  // (14)
        SetRealCoefficient(coefficients, 21,  ONE_OVER_24);  // (23)
        SetRealCoefficient(coefficients, 22, -ONE_OVER_12);  // (24)
        SetRealCoefficient(coefficients, 23,  ONE_OVER_24);  // (34)

        SetRealCoefficient(coefficients, 36, -ONE_OVER_12);  // (1234)i
        SetRealCoefficient(coefficients, 40,  ONE_OVER_24);  // (1243)i
        SetRealCoefficient(coefficients, 38,  ONE_OVER_24);  // (1324)i
        SetRealCoefficient(coefficients, 41,  ONE_OVER_24);  // (1342)i
        SetRealCoefficient(coefficients, 39,  ONE_OVER_24);  // (1423)i
        SetRealCoefficient(coefficients, 37, -ONE_OVER_12);  // (1432)i
        SetRealCoefficient(coefficients, 42,  ONE_OVER_24);  // (12)i
        SetRealCoefficient(coefficients, 43, -ONE_OVER_12);  // (13)i
        SetRealCoefficient(coefficients, 44,  ONE_OVER_24);  // (14)i
        SetRealCoefficient(coefficients, 45,  ONE_OVER_24);  // (23)i
        SetRealCoefficient(coefficients, 46, -ONE_OVER_12);  // (24)i
        SetRealCoefficient(coefficients, 47,  ONE_OVER_24);  // (34)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement8
     *
     * Fourier-basis element 8.
     *
     * Same support as element 4, but with the improper-sector signs reversed. :contentReference[oaicite:5]{index=5}
     */
    public static OhElement GetElement8() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        for (int index = 0; index <= 3; index++) {
            SetRealCoefficient(coefficients, index, ONE_OVER_12);
        }
        for (int index = 4; index <= 11; index++) {
            SetRealCoefficient(coefficients, index, -ONE_OVER_24);
        }
        for (int index = 24; index <= 27; index++) {
            SetRealCoefficient(coefficients, index, -ONE_OVER_12);
        }
        for (int index = 28; index <= 35; index++) {
            SetRealCoefficient(coefficients, index, ONE_OVER_24);
        }

        return new OhElement(coefficients);
    }

    /*
     * GetElement9
     *
     * Fourier-basis element 9.
     *
     * Same support as element 5, but with all improper-sector signs reversed. :contentReference[oaicite:6]{index=6}
     */
    public static OhElement GetElement9() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 16,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 14, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 17,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 15, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 18, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 20,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 21,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 23, -ROOT_THREE_OVER_24);

        SetRealCoefficient(coefficients, 40, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 38,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 41, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 39,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 42,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 44, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 45, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 47,  ROOT_THREE_OVER_24);

        return new OhElement(coefficients);
    }

    /*
     * GetElement10
     *
     * Fourier-basis element 10.
     *
     * Same support as element 6, but with all improper-sector imaginary signs
     * reversed. :contentReference[oaicite:7]{index=7}
     */
    public static OhElement GetElement10() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,   ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 5,  -ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 8,  -ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 6,   ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 11,  ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 10, -ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 7,  -ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 9,   ROOT_THREE_OVER_24);

        SetImaginaryCoefficient(coefficients, 28, -ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 29,  ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 32,  ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 30, -ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 35, -ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 34,  ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 31,  ROOT_THREE_OVER_24);
        SetImaginaryCoefficient(coefficients, 33, -ROOT_THREE_OVER_24);

        return new OhElement(coefficients);
    }

    /*
     * GetElement11
     *
     * Fourier-basis element 11.
     *
     * Same support as element 7, but with all improper-sector signs reversed. :contentReference[oaicite:8]{index=8}
     */
    public static OhElement GetElement11() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 12, -ONE_OVER_12);
        SetRealCoefficient(coefficients, 16,  ONE_OVER_24);
        SetRealCoefficient(coefficients, 14,  ONE_OVER_24);
        SetRealCoefficient(coefficients, 17,  ONE_OVER_24);
        SetRealCoefficient(coefficients, 15,  ONE_OVER_24);
        SetRealCoefficient(coefficients, 13, -ONE_OVER_12);
        SetRealCoefficient(coefficients, 18,  ONE_OVER_24);
        SetRealCoefficient(coefficients, 19, -ONE_OVER_12);
        SetRealCoefficient(coefficients, 20,  ONE_OVER_24);
        SetRealCoefficient(coefficients, 21,  ONE_OVER_24);
        SetRealCoefficient(coefficients, 22, -ONE_OVER_12);
        SetRealCoefficient(coefficients, 23,  ONE_OVER_24);

        SetRealCoefficient(coefficients, 36,  ONE_OVER_12);
        SetRealCoefficient(coefficients, 40, -ONE_OVER_24);
        SetRealCoefficient(coefficients, 38, -ONE_OVER_24);
        SetRealCoefficient(coefficients, 41, -ONE_OVER_24);
        SetRealCoefficient(coefficients, 39, -ONE_OVER_24);
        SetRealCoefficient(coefficients, 37,  ONE_OVER_12);
        SetRealCoefficient(coefficients, 42, -ONE_OVER_24);
        SetRealCoefficient(coefficients, 43,  ONE_OVER_12);
        SetRealCoefficient(coefficients, 44, -ONE_OVER_24);
        SetRealCoefficient(coefficients, 45, -ONE_OVER_24);
        SetRealCoefficient(coefficients, 46,  ONE_OVER_12);
        SetRealCoefficient(coefficients, 47, -ONE_OVER_24);

        return new OhElement(coefficients);
    }



        /*
     * GetElement12
     *
     * Fourier-basis element 12.
     *
     * Real element with support on:
     * - identity / inversion
     * - class 1 / class 6
     * - class 3 / class 8
     * - class 4 / class 9
     *
     * Coefficients are 3/16, +/-1/16 according to the uploaded table. 
     */
    public static OhElement GetElement12() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 0,  THREE_OVER_16);
        SetRealCoefficient(coefficients, 1, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 2, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 3, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 12, ONE_OVER_16);
        SetRealCoefficient(coefficients, 13, ONE_OVER_16);
        SetRealCoefficient(coefficients, 14, ONE_OVER_16);
        SetRealCoefficient(coefficients, 15, ONE_OVER_16);
        SetRealCoefficient(coefficients, 16, ONE_OVER_16);
        SetRealCoefficient(coefficients, 17, ONE_OVER_16);

        SetRealCoefficient(coefficients, 18, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 19, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 20, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 21, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 22, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 23, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 24, THREE_OVER_16);
        SetRealCoefficient(coefficients, 25, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 26, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 27, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 36, ONE_OVER_16);
        SetRealCoefficient(coefficients, 37, ONE_OVER_16);
        SetRealCoefficient(coefficients, 38, ONE_OVER_16);
        SetRealCoefficient(coefficients, 39, ONE_OVER_16);
        SetRealCoefficient(coefficients, 40, ONE_OVER_16);
        SetRealCoefficient(coefficients, 41, ONE_OVER_16);

        SetRealCoefficient(coefficients, 42, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 43, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 44, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 45, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 46, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 47, -ONE_OVER_16);

        return new OhElement(coefficients);
    }

    /*
     * GetElement13
     *
     * Fourier-basis element 13.
     *
     * Sparse real element supported on class 2, two class-4 slots,
     * and the matching improper partners. 
     */
    public static OhElement GetElement13() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 4,  ONE_OVER_16);   // (123)
        SetRealCoefficient(coefficients, 5, -ONE_OVER_16);   // (124)
        SetRealCoefficient(coefficients, 8,  ONE_OVER_16);   // (132)
        SetRealCoefficient(coefficients, 6, -ONE_OVER_16);   // (134)
        SetRealCoefficient(coefficients, 11, -ONE_OVER_16);  // (142)
        SetRealCoefficient(coefficients, 10, -ONE_OVER_16);  // (143)
        SetRealCoefficient(coefficients, 7,  ONE_OVER_16);   // (234)
        SetRealCoefficient(coefficients, 9,  ONE_OVER_16);   // (243)

        SetRealCoefficient(coefficients, 20,  ONE_OVER_8);   // (14)
        SetRealCoefficient(coefficients, 21, -ONE_OVER_8);   // (23)

        SetRealCoefficient(coefficients, 28,  ONE_OVER_16);   // (123)i
        SetRealCoefficient(coefficients, 29, -ONE_OVER_16);   // (124)i
        SetRealCoefficient(coefficients, 32,  ONE_OVER_16);   // (132)i
        SetRealCoefficient(coefficients, 30, -ONE_OVER_16);   // (134)i
        SetRealCoefficient(coefficients, 35, -ONE_OVER_16);   // (142)i
        SetRealCoefficient(coefficients, 34, -ONE_OVER_16);   // (143)i
        SetRealCoefficient(coefficients, 31,  ONE_OVER_16);   // (234)i
        SetRealCoefficient(coefficients, 33,  ONE_OVER_16);   // (243)i

        SetRealCoefficient(coefficients, 44,  ONE_OVER_8);    // (14)i
        SetRealCoefficient(coefficients, 45, -ONE_OVER_8);    // (23)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement14
     *
     * Fourier-basis element 14.
     *
     * Sparse purely imaginary element on class 2, two class-3 slots,
     * and the matching improper partners. 
     */
    public static OhElement GetElement14() {
        return Negate(GetElement14Original());
    }

    private static OhElement GetElement14Original() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 5,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 8,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 6,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 11,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 10, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 7,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 9,  -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 16,  ONE_OVER_8);
        SetImaginaryCoefficient(coefficients, 17, -ONE_OVER_8);

        SetImaginaryCoefficient(coefficients, 28, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 29, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 32,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 30,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 35,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 34, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 31,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 33, -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 40,  ONE_OVER_8);
        SetImaginaryCoefficient(coefficients, 41, -ONE_OVER_8);

        return new OhElement(coefficients);
    }

    /*
     * GetElement15
     *
     * Fourier-basis element 15.
     *
     * Sparse real element on class 1, selected class-3 and class-4 slots,
     * and the matching improper partners. 
     */
    public static OhElement GetElement15() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 1,  ONE_OVER_8);    // (12)(34)
        SetRealCoefficient(coefficients, 2, -ONE_OVER_8);    // (13)(24)

        SetRealCoefficient(coefficients, 12, -ONE_OVER_16);  // (1234)
        SetRealCoefficient(coefficients, 14,  ONE_OVER_16);  // (1324)
        SetRealCoefficient(coefficients, 15,  ONE_OVER_16);  // (1423)
        SetRealCoefficient(coefficients, 13, -ONE_OVER_16);  // (1432)

        SetRealCoefficient(coefficients, 18, -ONE_OVER_16);  // (12)
        SetRealCoefficient(coefficients, 19,  ONE_OVER_16);  // (13)
        SetRealCoefficient(coefficients, 22,  ONE_OVER_16);  // (24)
        SetRealCoefficient(coefficients, 23, -ONE_OVER_16);  // (34)

        SetRealCoefficient(coefficients, 25,  ONE_OVER_8);    // (12)(34)i
        SetRealCoefficient(coefficients, 26, -ONE_OVER_8);    // (13)(24)i

        SetRealCoefficient(coefficients, 36, -ONE_OVER_16);  // (1234)i
        SetRealCoefficient(coefficients, 38,  ONE_OVER_16);  // (1324)i
        SetRealCoefficient(coefficients, 39,  ONE_OVER_16);  // (1423)i
        SetRealCoefficient(coefficients, 37, -ONE_OVER_16);  // (1432)i

        SetRealCoefficient(coefficients, 42, -ONE_OVER_16);  // (12)i
        SetRealCoefficient(coefficients, 43,  ONE_OVER_16);  // (13)i
        SetRealCoefficient(coefficients, 46,  ONE_OVER_16);  // (24)i
        SetRealCoefficient(coefficients, 47, -ONE_OVER_16);  // (34)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement16
     *
     * Fourier-basis element 16.
     *
     * Sparse real element on class 2, two class-4 slots,
     * and the matching improper partners. 
     */
    public static OhElement GetElement16() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 4,  ONE_OVER_16);   // (123)
        SetRealCoefficient(coefficients, 5, -ONE_OVER_16);   // (124)
        SetRealCoefficient(coefficients, 8,  ONE_OVER_16);   // (132)
        SetRealCoefficient(coefficients, 6,  ONE_OVER_16);   // (134)
        SetRealCoefficient(coefficients, 11, -ONE_OVER_16);  // (142)
        SetRealCoefficient(coefficients, 10,  ONE_OVER_16);  // (143)
        SetRealCoefficient(coefficients, 7, -ONE_OVER_16);   // (234)
        SetRealCoefficient(coefficients, 9, -ONE_OVER_16);   // (243)

        SetRealCoefficient(coefficients, 19, -ONE_OVER_8);   // (13)
        SetRealCoefficient(coefficients, 22,  ONE_OVER_8);   // (24)

        SetRealCoefficient(coefficients, 28,  ONE_OVER_16);   // (123)i
        SetRealCoefficient(coefficients, 29, -ONE_OVER_16);   // (124)i
        SetRealCoefficient(coefficients, 32,  ONE_OVER_16);   // (132)i
        SetRealCoefficient(coefficients, 30,  ONE_OVER_16);   // (134)i
        SetRealCoefficient(coefficients, 35, -ONE_OVER_16);   // (142)i
        SetRealCoefficient(coefficients, 34,  ONE_OVER_16);   // (143)i
        SetRealCoefficient(coefficients, 31, -ONE_OVER_16);   // (234)i
        SetRealCoefficient(coefficients, 33, -ONE_OVER_16);   // (243)i

        SetRealCoefficient(coefficients, 43, -ONE_OVER_8);    // (13)i
        SetRealCoefficient(coefficients, 46,  ONE_OVER_8);    // (24)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement17
     *
     * Fourier-basis element 17.
     *
     * Sparse purely imaginary element on class 2, two class-3 slots,
     * and the matching improper partners. 
     */
    public static OhElement GetElement17() {
        return Negate(GetElement17Original());
    }

    private static OhElement GetElement17Original() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 5,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 8,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 6,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 11, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 10, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 7,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 9,  -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 12, -ONE_OVER_8);
        SetImaginaryCoefficient(coefficients, 13,  ONE_OVER_8);

        SetImaginaryCoefficient(coefficients, 28,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 29,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 32, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 30,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 35, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 34, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 31,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 33, -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 36, -ONE_OVER_8);
        SetImaginaryCoefficient(coefficients, 37,  ONE_OVER_8);

        return new OhElement(coefficients);
    }

    /*
     * GetElement18
     *
     * Fourier-basis element 18.
     *
     * Sparse real element on class 2, two class-4 slots,
     * and the matching improper partners. 
     */
    public static OhElement GetElement18() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 4,  ONE_OVER_16);   // (123)
        SetRealCoefficient(coefficients, 5,  ONE_OVER_16);   // (124)
        SetRealCoefficient(coefficients, 8,  ONE_OVER_16);   // (132)
        SetRealCoefficient(coefficients, 6, -ONE_OVER_16);   // (134)
        SetRealCoefficient(coefficients, 11, ONE_OVER_16);   // (142)
        SetRealCoefficient(coefficients, 10, -ONE_OVER_16);  // (143)
        SetRealCoefficient(coefficients, 7, -ONE_OVER_16);   // (234)
        SetRealCoefficient(coefficients, 9, -ONE_OVER_16);   // (243)

        SetRealCoefficient(coefficients, 18, -ONE_OVER_8);   // (12)
        SetRealCoefficient(coefficients, 23,  ONE_OVER_8);   // (34)

        SetRealCoefficient(coefficients, 28,  ONE_OVER_16);   // (123)i
        SetRealCoefficient(coefficients, 29,  ONE_OVER_16);   // (124)i
        SetRealCoefficient(coefficients, 32,  ONE_OVER_16);   // (132)i
        SetRealCoefficient(coefficients, 30, -ONE_OVER_16);   // (134)i
        SetRealCoefficient(coefficients, 35,  ONE_OVER_16);   // (142)i
        SetRealCoefficient(coefficients, 34, -ONE_OVER_16);   // (143)i
        SetRealCoefficient(coefficients, 31, -ONE_OVER_16);   // (234)i
        SetRealCoefficient(coefficients, 33, -ONE_OVER_16);   // (243)i

        SetRealCoefficient(coefficients, 42, -ONE_OVER_8);    // (12)i
        SetRealCoefficient(coefficients, 47,  ONE_OVER_8);    // (34)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement19
     *
     * Fourier-basis element 19.
     *
     * Sparse purely imaginary element on class 2, two class-3 slots,
     * and the matching improper partners. 
     */
    public static OhElement GetElement19() {
        return Negate(GetElement19Original());
    }

    private static OhElement GetElement19Original() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 5,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 8,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 6,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 11, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 10, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 7,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 9,   ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 14, -ONE_OVER_8);
        SetImaginaryCoefficient(coefficients, 15,  ONE_OVER_8);

        SetImaginaryCoefficient(coefficients, 28, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 29,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 32,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 30,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 35, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 34, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 31, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 33,  ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 38, -ONE_OVER_8);
        SetImaginaryCoefficient(coefficients, 39,  ONE_OVER_8);

        return new OhElement(coefficients);
    }

    /*
     * GetElement20
     *
     * Fourier-basis element 20.
     *
     * Sparse real element with coefficients involving sqrt(3)/24, sqrt(3)/48,
     * and -sqrt(3)/12, with the same signs on the improper partners. 
     */
    public static OhElement GetElement20() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 1,  ROOT_THREE_OVER_24);   // (12)(34)
        SetRealCoefficient(coefficients, 2,  ROOT_THREE_OVER_24);   // (13)(24)
        SetRealCoefficient(coefficients, 3, -ROOT_THREE_OVER_12);   // (14)(23)

        SetRealCoefficient(coefficients, 12,  ROOT_THREE_OVER_48);  // (1234)
        SetRealCoefficient(coefficients, 16, -ROOT_THREE_OVER_24);  // (1243)
        SetRealCoefficient(coefficients, 14,  ROOT_THREE_OVER_48);  // (1324)
        SetRealCoefficient(coefficients, 17, -ROOT_THREE_OVER_24);  // (1342)
        SetRealCoefficient(coefficients, 15,  ROOT_THREE_OVER_48);  // (1423)
        SetRealCoefficient(coefficients, 13,  ROOT_THREE_OVER_48);  // (1432)

        SetRealCoefficient(coefficients, 18, -ROOT_THREE_OVER_48);  // (12)
        SetRealCoefficient(coefficients, 19, -ROOT_THREE_OVER_48);  // (13)
        SetRealCoefficient(coefficients, 20,  ROOT_THREE_OVER_24);  // (14)
        SetRealCoefficient(coefficients, 21,  ROOT_THREE_OVER_24);  // (23)
        SetRealCoefficient(coefficients, 22, -ROOT_THREE_OVER_48);  // (24)
        SetRealCoefficient(coefficients, 23, -ROOT_THREE_OVER_48);  // (34)

        SetRealCoefficient(coefficients, 25,  ROOT_THREE_OVER_24);   // (12)(34)i
        SetRealCoefficient(coefficients, 26,  ROOT_THREE_OVER_24);   // (13)(24)i
        SetRealCoefficient(coefficients, 27, -ROOT_THREE_OVER_12);   // (14)(23)i

        SetRealCoefficient(coefficients, 36,  ROOT_THREE_OVER_48);  // (1234)i
        SetRealCoefficient(coefficients, 40, -ROOT_THREE_OVER_24);  // (1243)i
        SetRealCoefficient(coefficients, 38,  ROOT_THREE_OVER_48);  // (1324)i
        SetRealCoefficient(coefficients, 41, -ROOT_THREE_OVER_24);  // (1342)i
        SetRealCoefficient(coefficients, 39,  ROOT_THREE_OVER_48);  // (1423)i
        SetRealCoefficient(coefficients, 37,  ROOT_THREE_OVER_48);  // (1432)i

        SetRealCoefficient(coefficients, 42, -ROOT_THREE_OVER_48);  // (12)i
        SetRealCoefficient(coefficients, 43, -ROOT_THREE_OVER_48);  // (13)i
        SetRealCoefficient(coefficients, 44,  ROOT_THREE_OVER_24);  // (14)i
        SetRealCoefficient(coefficients, 45,  ROOT_THREE_OVER_24);  // (23)i
        SetRealCoefficient(coefficients, 46, -ROOT_THREE_OVER_48);  // (24)i
        SetRealCoefficient(coefficients, 47, -ROOT_THREE_OVER_48);  // (34)i

        return new OhElement(coefficients);
    }
    
        /*
     * GetElement21
     *
     * Same support/sign pattern as element 12 on the proper sector,
     * with all improper-sector signs reversed. :contentReference[oaicite:1]{index=1}
     */
    public static OhElement GetElement21() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 0,  THREE_OVER_16);
        SetRealCoefficient(coefficients, 1, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 2, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 3, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 12, ONE_OVER_16);
        SetRealCoefficient(coefficients, 13, ONE_OVER_16);
        SetRealCoefficient(coefficients, 14, ONE_OVER_16);
        SetRealCoefficient(coefficients, 15, ONE_OVER_16);
        SetRealCoefficient(coefficients, 16, ONE_OVER_16);
        SetRealCoefficient(coefficients, 17, ONE_OVER_16);

        SetRealCoefficient(coefficients, 18, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 19, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 20, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 21, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 22, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 23, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 24, -THREE_OVER_16);
        SetRealCoefficient(coefficients, 25,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 26,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 27,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 36, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 37, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 38, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 39, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 40, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 41, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 42,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 43,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 44,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 45,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 46,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 47,  ONE_OVER_16);

        return new OhElement(coefficients);
    }

    /*
     * GetElement22
     *
     * Same support as element 13, with all improper-sector signs reversed. :contentReference[oaicite:2]{index=2}
     */
    public static OhElement GetElement22() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 4,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 5, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 8,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 6, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 11, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 10, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 7,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 9,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 20,  ONE_OVER_8);
        SetRealCoefficient(coefficients, 21, -ONE_OVER_8);

        SetRealCoefficient(coefficients, 28, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 29,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 32, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 30,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 35,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 34,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 31, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 33, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 44, -ONE_OVER_8);
        SetRealCoefficient(coefficients, 45,  ONE_OVER_8);

        return new OhElement(coefficients);
    }

    /*
     * GetElement23
     *
     * Same support as element 14, with the improper-sector signs adjusted
     * exactly as in the uploaded table. :contentReference[oaicite:3]{index=3}
     */
    public static OhElement GetElement23() {
        return Negate(GetElement23Original());
    }
    
    public static OhElement GetElement23Original() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 5,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 8,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 6,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 11,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 10, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 7,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 9,  -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 16,  ONE_OVER_8);
        SetImaginaryCoefficient(coefficients, 17, -ONE_OVER_8);

        SetImaginaryCoefficient(coefficients, 28,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 29,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 32, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 30, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 35, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 34,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 31, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 33,  ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 40, -ONE_OVER_8);
        SetImaginaryCoefficient(coefficients, 41,  ONE_OVER_8);

        return new OhElement(coefficients);
    }

    /*
     * GetElement24
     *
     * Same support as element 15, with all improper-sector signs reversed. :contentReference[oaicite:4]{index=4}
     */
    public static OhElement GetElement24() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 1,  ONE_OVER_8);
        SetRealCoefficient(coefficients, 2, -ONE_OVER_8);

        SetRealCoefficient(coefficients, 12, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 14,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 15,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 13, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 18, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 19,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 22,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 23, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 25, -ONE_OVER_8);
        SetRealCoefficient(coefficients, 26,  ONE_OVER_8);

        SetRealCoefficient(coefficients, 36,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 38, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 39, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 37,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 42,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 43, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 46, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 47,  ONE_OVER_16);

        return new OhElement(coefficients);
    }

    /*
     * GetElement25
     *
     * Same support as element 16, with all improper-sector signs reversed. :contentReference[oaicite:5]{index=5}
     */
    public static OhElement GetElement25() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 4,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 5, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 8,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 6,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 11, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 10,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 7, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 9, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 19, -ONE_OVER_8);
        SetRealCoefficient(coefficients, 22,  ONE_OVER_8);

        SetRealCoefficient(coefficients, 28, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 29,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 32, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 30, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 35,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 34, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 31,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 33,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 43,  ONE_OVER_8);
        SetRealCoefficient(coefficients, 46, -ONE_OVER_8);

        return new OhElement(coefficients);
    }

    /*
     * GetElement26
     *
     * Same support as element 17, with improper-sector signs adjusted as in the file. 
     */
    public static OhElement GetElement26() {
        return Negate(GetElement26Original());
    }

    private static OhElement GetElement26Original() {
        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 5,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 8,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 6,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 11, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 10, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 7,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 9,  -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 12, -ONE_OVER_8);
        SetImaginaryCoefficient(coefficients, 13,  ONE_OVER_8);

        SetImaginaryCoefficient(coefficients, 28, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 29, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 32,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 30, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 35,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 34,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 31, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 33,  ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 36,  ONE_OVER_8);
        SetImaginaryCoefficient(coefficients, 37, -ONE_OVER_8);

        return new OhElement(coefficients);
    }

    /*
     * GetElement27
     *
     * Same support as element 18, with all improper-sector signs reversed. :contentReference[oaicite:7]{index=7}
     */
    public static OhElement GetElement27() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 4,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 5,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 8,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 6, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 11, ONE_OVER_16);
        SetRealCoefficient(coefficients, 10, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 7, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 9, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 18, -ONE_OVER_8);
        SetRealCoefficient(coefficients, 23,  ONE_OVER_8);

        SetRealCoefficient(coefficients, 28, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 29, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 32, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 30,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 35, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 34,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 31,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 33,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 42,  ONE_OVER_8);
        SetRealCoefficient(coefficients, 47, -ONE_OVER_8);

        return new OhElement(coefficients);
    }

    /*
     * GetElement28
     *
     * Same support as element 19, with improper-sector signs adjusted as in the file. :contentReference[oaicite:8]{index=8}
     */
    public static OhElement GetElement28() {
        return Negate(GetElement28Original());
    }

    private static OhElement GetElement28Original() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 5,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 8,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 6,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 11, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 10, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 7,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 9,   ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 14, -ONE_OVER_8);
        SetImaginaryCoefficient(coefficients, 15,  ONE_OVER_8);

        SetImaginaryCoefficient(coefficients, 28,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 29, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 32, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 30, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 35,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 34,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 31,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 33, -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 38,  ONE_OVER_8);
        SetImaginaryCoefficient(coefficients, 39, -ONE_OVER_8);

        return new OhElement(coefficients);
    }

    /*
     * GetElement29
     *
     * Same support as element 20, with all improper-sector signs reversed. 
     */
    public static OhElement GetElement29() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 1,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 2,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 3, -ROOT_THREE_OVER_12);

        SetRealCoefficient(coefficients, 12,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 16, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 14,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 17, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 15,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 13,  ROOT_THREE_OVER_48);

        SetRealCoefficient(coefficients, 18, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 19, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 20,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 21,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 22, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 23, -ROOT_THREE_OVER_48);

        SetRealCoefficient(coefficients, 25, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 26, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 27,  ROOT_THREE_OVER_12);

        SetRealCoefficient(coefficients, 36, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 40,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 38, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 41,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 39, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 37, -ROOT_THREE_OVER_48);

        SetRealCoefficient(coefficients, 42,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 43,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 44, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 45, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 46,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 47,  ROOT_THREE_OVER_48);

        return new OhElement(coefficients);
    }
    
    /*
     * GetElement30
     *
     * Real element with support on:
     * - identity / inversion
     * - class 1 / class 6
     * - class 3 / class 8
     * - class 4 / class 9
     *
     * Coefficients are 3/16, -1/16 on classes 1 and 3, and +1/16 on class 4,
     * with the same signs on the improper sector. :contentReference[oaicite:1]{index=1}
     */
    public static OhElement GetElement30() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 0,  THREE_OVER_16);
        SetRealCoefficient(coefficients, 1, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 2, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 3, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 12, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 13, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 14, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 15, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 16, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 17, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 18,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 19,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 20,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 21,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 22,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 23,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 24, THREE_OVER_16);
        SetRealCoefficient(coefficients, 25, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 26, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 27, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 36, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 37, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 38, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 39, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 40, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 41, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 42,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 43,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 44,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 45,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 46,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 47,  ONE_OVER_16);

        return new OhElement(coefficients);
    }

    /*
     * GetElement31
     *
     * Sparse real element on class 2, two class-4 slots,
     * and the matching improper partners. :contentReference[oaicite:2]{index=2}
     */
    public static OhElement GetElement31() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 4,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 5, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 8,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 6, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 11, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 10, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 7,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 9,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 20, -ONE_OVER_8);   // (14)
        SetRealCoefficient(coefficients, 21,  ONE_OVER_8);   // (23)

        SetRealCoefficient(coefficients, 28,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 29, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 32,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 30, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 35, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 34, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 31,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 33,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 44, -ONE_OVER_8);   // (14)i
        SetRealCoefficient(coefficients, 45,  ONE_OVER_8);   // (23)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement32
     *
     * Sparse purely imaginary element on class 2, two class-3 slots,
     * and the matching improper partners. :contentReference[oaicite:3]{index=3}
     */
    public static OhElement GetElement32() {
        return Negate(GetElement32Original());
    }

    private static OhElement GetElement32Original() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 5,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 8,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 6,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 11,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 10, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 7,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 9,  -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 16, -ONE_OVER_8);   // (1243)
        SetImaginaryCoefficient(coefficients, 17,  ONE_OVER_8);   // (1342)

        SetImaginaryCoefficient(coefficients, 28, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 29, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 32,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 30,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 35,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 34, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 31,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 33, -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 40, -ONE_OVER_8);   // (1243)i
        SetImaginaryCoefficient(coefficients, 41,  ONE_OVER_8);   // (1342)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement33
     *
     * Sparse real element on class 1, selected class-3 and class-4 slots,
     * and the matching improper partners. :contentReference[oaicite:4]{index=4}
     */
    public static OhElement GetElement33() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 1,  ONE_OVER_8);
        SetRealCoefficient(coefficients, 2, -ONE_OVER_8);

        SetRealCoefficient(coefficients, 12,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 14, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 15, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 13,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 18,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 19, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 22, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 23,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 25,  ONE_OVER_8);
        SetRealCoefficient(coefficients, 26, -ONE_OVER_8);

        SetRealCoefficient(coefficients, 36,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 38, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 39, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 37,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 42,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 43, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 46, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 47,  ONE_OVER_16);

        return new OhElement(coefficients);
    }

    /*
     * GetElement34
     *
     * Sparse real element on class 2, two class-4 slots,
     * and the matching improper partners. :contentReference[oaicite:5]{index=5}
     */
    public static OhElement GetElement34() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 4,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 5, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 8,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 6,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 11, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 10,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 7, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 9, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 19,  ONE_OVER_8);   // (13)
        SetRealCoefficient(coefficients, 22, -ONE_OVER_8);   // (24)

        SetRealCoefficient(coefficients, 28,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 29, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 32,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 30,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 35, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 34,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 31, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 33, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 43,  ONE_OVER_8);   // (13)i
        SetRealCoefficient(coefficients, 46, -ONE_OVER_8);   // (24)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement35
     *
     * Sparse purely imaginary element on class 2, two class-3 slots,
     * and the matching improper partners. :contentReference[oaicite:6]{index=6}
     */
    public static OhElement GetElement35() {
        return Negate(GetElement35Original());
    }

    private static OhElement GetElement35Original() {
        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 5,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 8,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 6,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 11, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 10, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 7,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 9,  -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 12,  ONE_OVER_8);   // (1234)
        SetImaginaryCoefficient(coefficients, 13, -ONE_OVER_8);   // (1432)

        SetImaginaryCoefficient(coefficients, 28,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 29,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 32, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 30,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 35, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 34, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 31,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 33, -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 36,  ONE_OVER_8);   // (1234)i
        SetImaginaryCoefficient(coefficients, 37, -ONE_OVER_8);   // (1432)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement36
     *
     * Sparse real element on class 2, two class-4 slots,
     * and the matching improper partners. :contentReference[oaicite:7]{index=7}
     */
    public static OhElement GetElement36() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 4,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 5,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 8,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 6, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 11, ONE_OVER_16);
        SetRealCoefficient(coefficients, 10, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 7, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 9, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 18,  ONE_OVER_8);   // (12)
        SetRealCoefficient(coefficients, 23, -ONE_OVER_8);   // (34)

        SetRealCoefficient(coefficients, 28,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 29,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 32,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 30, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 35,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 34, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 31, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 33, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 42,  ONE_OVER_8);   // (12)i
        SetRealCoefficient(coefficients, 47, -ONE_OVER_8);   // (34)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement37
     *
     * Sparse purely imaginary element on class 2, two class-3 slots,
     * and the matching improper partners. 
     */
    public static OhElement GetElement37() {
        return Negate(GetElement37Original());
    }

    private static OhElement GetElement37Original() {
        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 5,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 8,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 6,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 11, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 10, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 7,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 9,   ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 14,  ONE_OVER_8);   // (1324)
        SetImaginaryCoefficient(coefficients, 15, -ONE_OVER_8);   // (1423)

        SetImaginaryCoefficient(coefficients, 28, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 29,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 32,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 30,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 35, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 34, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 31, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 33,  ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 38,  ONE_OVER_8);   // (1324)i
        SetImaginaryCoefficient(coefficients, 39, -ONE_OVER_8);   // (1423)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement38
     *
     * Sparse real element with coefficients involving sqrt(3)/24, sqrt(3)/48,
     * and -sqrt(3)/12, with the same signs on the improper partners. 
     */
    public static OhElement GetElement38() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 1,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 2,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 3, -ROOT_THREE_OVER_12);

        SetRealCoefficient(coefficients, 12, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 16,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 14, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 17,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 15, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 13, -ROOT_THREE_OVER_48);

        SetRealCoefficient(coefficients, 18,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 19,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 20, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 21, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 22,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 23,  ROOT_THREE_OVER_48);

        SetRealCoefficient(coefficients, 25,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 26,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 27, -ROOT_THREE_OVER_12);

        SetRealCoefficient(coefficients, 36, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 40,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 38, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 41,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 39, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 37, -ROOT_THREE_OVER_48);

        SetRealCoefficient(coefficients, 42,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 43,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 44, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 45, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 46,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 47,  ROOT_THREE_OVER_48);

        return new OhElement(coefficients);
    }

        /*
     * GetElement39
     *
     * Real element with support on:
     * - identity / inversion
     * - class 1 / class 6
     * - class 3 / class 8
     * - class 4 / class 9
     *
     * Proper sector:
     *     +3/16 on identity
     *     -1/16 on class 1
     *     -1/16 on class 3
     *     +1/16 on class 4
     *
     * Improper sector:
     *     -3/16 on inversion
     *     +1/16 on class 6
     *     +1/16 on class 8
     *     -1/16 on class 9
     *
     * Visible in the uploaded table. :contentReference[oaicite:1]{index=1}
     */
    public static OhElement GetElement39() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 0,  THREE_OVER_16);
        SetRealCoefficient(coefficients, 1, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 2, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 3, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 12, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 13, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 14, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 15, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 16, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 17, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 18,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 19,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 20,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 21,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 22,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 23,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 24, -THREE_OVER_16);
        SetRealCoefficient(coefficients, 25,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 26,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 27,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 36,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 37,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 38,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 39,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 40,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 41,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 42, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 43, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 44, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 45, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 46, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 47, -ONE_OVER_16);

        return new OhElement(coefficients);
    }

    /*
     * GetElement40
     *
     * Sparse real element on class 2, two class-4 slots,
     * and the matching improper partners.
     *
     * From the uploaded table:
     * - same proper support/sign pattern as element 31
     * - improper-sector signs reversed relative to 31
     *
     * Visible in the uploaded table. :contentReference[oaicite:2]{index=2}
     */
    public static OhElement GetElement40() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 4,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 5, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 8,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 6, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 11, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 10, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 7,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 9,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 20, -ONE_OVER_8);   // (14)
        SetRealCoefficient(coefficients, 21,  ONE_OVER_8);   // (23)

        SetRealCoefficient(coefficients, 28, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 29,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 32, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 30,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 35,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 34,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 31, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 33, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 44,  ONE_OVER_8);   // (14)i
        SetRealCoefficient(coefficients, 45, -ONE_OVER_8);   // (23)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement41
     *
     * Sparse purely imaginary element on class 2, two class-3 slots,
     * and the matching improper partners.
     *
     * Visible in the uploaded table. :contentReference[oaicite:3]{index=3}
     */
    public static OhElement GetElement41() {
        return Negate(GetElement41Original());
    }

    private static OhElement GetElement41Original() {
        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 5,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 8,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 6,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 11,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 10, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 7,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 9,  -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 16, -ONE_OVER_8);   // (1243)
        SetImaginaryCoefficient(coefficients, 17,  ONE_OVER_8);   // (1342)

        SetImaginaryCoefficient(coefficients, 28,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 29,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 32, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 30, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 35, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 34,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 31, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 33,  ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 40,  ONE_OVER_8);   // (1243)i
        SetImaginaryCoefficient(coefficients, 41, -ONE_OVER_8);   // (1342)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement42
     *
     * Sparse real element on class 1, selected class-3 and class-4 slots,
     * and the matching improper partners.
     *
     * Visible in the uploaded table. :contentReference[oaicite:4]{index=4}
     */
    public static OhElement GetElement42() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 1,  ONE_OVER_8);
        SetRealCoefficient(coefficients, 2, -ONE_OVER_8);

        SetRealCoefficient(coefficients, 12,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 14, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 15, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 13,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 18,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 19, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 22, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 23,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 25, -ONE_OVER_8);
        SetRealCoefficient(coefficients, 26,  ONE_OVER_8);

        SetRealCoefficient(coefficients, 36, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 38,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 39,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 37, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 42, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 43,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 46,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 47, -ONE_OVER_16);

        return new OhElement(coefficients);
    }

    /*
     * GetElement43
     *
     * Sparse real element on class 2, two class-4 slots,
     * and the matching improper partners.
     *
     * Visible in the uploaded table. :contentReference[oaicite:5]{index=5}
     */
    public static OhElement GetElement43() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 4,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 5, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 8,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 6,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 11, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 10,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 7, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 9, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 19,  ONE_OVER_8);   // (13)
        SetRealCoefficient(coefficients, 22, -ONE_OVER_8);   // (24)

        SetRealCoefficient(coefficients, 28, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 29,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 32, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 30, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 35,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 34, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 31,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 33,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 43, -ONE_OVER_8);   // (13)i
        SetRealCoefficient(coefficients, 46,  ONE_OVER_8);   // (24)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement44
     *
     * Sparse purely imaginary element on class 2, two class-3 slots,
     * and the matching improper partners.
     *
     * Visible in the uploaded table. :contentReference[oaicite:6]{index=6}
     */
    public static OhElement GetElement44() {
        return Negate(GetElement44Original());
    }

    private static OhElement GetElement44Original() {
        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 5,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 8,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 6,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 11, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 10, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 7,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 9,  -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 12,  ONE_OVER_8);   // (1234)
        SetImaginaryCoefficient(coefficients, 13, -ONE_OVER_8);   // (1432)

        SetImaginaryCoefficient(coefficients, 28, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 29, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 32,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 30, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 35,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 34,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 31, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 33,  ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 36, -ONE_OVER_8);   // (1234)i
        SetImaginaryCoefficient(coefficients, 37,  ONE_OVER_8);   // (1432)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement45
     *
     * Sparse real element on class 2, two class-4 slots,
     * and the matching improper partners.
     *
     * Visible in the uploaded table. :contentReference[oaicite:7]{index=7}
     */
    public static OhElement GetElement45() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 4,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 5,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 8,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 6, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 11,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 10, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 7, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 9, -ONE_OVER_16);

        SetRealCoefficient(coefficients, 18,  ONE_OVER_8);   // (12)
        SetRealCoefficient(coefficients, 23, -ONE_OVER_8);   // (34)

        SetRealCoefficient(coefficients, 28, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 29, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 32, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 30,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 35, -ONE_OVER_16);
        SetRealCoefficient(coefficients, 34,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 31,  ONE_OVER_16);
        SetRealCoefficient(coefficients, 33,  ONE_OVER_16);

        SetRealCoefficient(coefficients, 42, -ONE_OVER_8);   // (12)i
        SetRealCoefficient(coefficients, 47,  ONE_OVER_8);   // (34)i

        return new OhElement(coefficients);
    }

    /*
     * GetElement46
     *
     * Sparse purely imaginary element on class 2, two class-3 slots,
     * and the matching improper partners.
     *
     * Visible in the uploaded table. :contentReference[oaicite:8]{index=8}
     */
    public static OhElement GetElement46() {
        return Negate(GetElement46Original());
    }

    private static OhElement GetElement46Original() {
        ComplexNumber[] coefficients = ZeroCoefficients();

        SetImaginaryCoefficient(coefficients, 4,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 5,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 8,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 6,   ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 11, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 10, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 7,  -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 9,   ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 14,  ONE_OVER_8);   // (1324)
        SetImaginaryCoefficient(coefficients, 15, -ONE_OVER_8);   // (1423)

        SetImaginaryCoefficient(coefficients, 28,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 29, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 32, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 30, -ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 35,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 34,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 31,  ONE_OVER_16);
        SetImaginaryCoefficient(coefficients, 33, -ONE_OVER_16);

        SetImaginaryCoefficient(coefficients, 38, -ONE_OVER_8);   // (1324)i
        SetImaginaryCoefficient(coefficients, 39,  ONE_OVER_8);   // (1423)i

        return new OhElement(coefficients);
    }

    /*
     * Doublet block 1
     *
     * Indices 4..7
     */
    public static OhElement GetDoublet1Identity() { return GetElement(4); }
    public static OhElement GetDoublet1SigmaX()   { return GetElement(5); }
    public static OhElement GetDoublet1SigmaY()   { return GetElement(6); }
    public static OhElement GetDoublet1SigmaZ()   { return GetElement(7); }

    /*
     * Doublet block 2
     *
     * Indices 8..11
     */
    public static OhElement GetDoublet2Identity() { return GetElement(8); }
    public static OhElement GetDoublet2SigmaX()   { return GetElement(9); }
    public static OhElement GetDoublet2SigmaY()   { return GetElement(10); }
    public static OhElement GetDoublet2SigmaZ()   { return GetElement(11); }

    /*
     * Triplet block 1
     *
     * Indices 12..20
     */
    public static OhElement GetTriplet1Identity() { return GetElement(12); }
    public static OhElement GetTriplet1Lambda1()  { return GetElement(13); }
    public static OhElement GetTriplet1Lambda2()  { return GetElement(14); }
    public static OhElement GetTriplet1Lambda3()  { return GetElement(15); }
    public static OhElement GetTriplet1Lambda4()  { return GetElement(16); }
    public static OhElement GetTriplet1Lambda5()  { return GetElement(17); }
    public static OhElement GetTriplet1Lambda6()  { return GetElement(18); }
    public static OhElement GetTriplet1Lambda7()  { return GetElement(19); }
    public static OhElement GetTriplet1Lambda8()  { return GetElement(20); }

    /*
     * Triplet block 2
     *
     * Indices 21..29
     */
    public static OhElement GetTriplet2Identity() { return GetElement(21); }
    public static OhElement GetTriplet2Lambda1()  { return GetElement(22); }
    public static OhElement GetTriplet2Lambda2()  { return GetElement(23); }
    public static OhElement GetTriplet2Lambda3()  { return GetElement(24); }
    public static OhElement GetTriplet2Lambda4()  { return GetElement(25); }
    public static OhElement GetTriplet2Lambda5()  { return GetElement(26); }
    public static OhElement GetTriplet2Lambda6()  { return GetElement(27); }
    public static OhElement GetTriplet2Lambda7()  { return GetElement(28); }
    public static OhElement GetTriplet2Lambda8()  { return GetElement(29); }

    /*
     * Triplet block 3
     *
     * Indices 30..38
     */
    public static OhElement GetTriplet3Identity() { return GetElement(30); }
    public static OhElement GetTriplet3Lambda1()  { return GetElement(31); }
    public static OhElement GetTriplet3Lambda2()  { return GetElement(32); }
    public static OhElement GetTriplet3Lambda3()  { return GetElement(33); }
    public static OhElement GetTriplet3Lambda4()  { return GetElement(34); }
    public static OhElement GetTriplet3Lambda5()  { return GetElement(35); }
    public static OhElement GetTriplet3Lambda6()  { return GetElement(36); }
    public static OhElement GetTriplet3Lambda7()  { return GetElement(37); }
    public static OhElement GetTriplet3Lambda8()  { return GetElement(38); }

    /*
     * Triplet block 4
     *
     * Indices 39..47
     */
    public static OhElement GetTriplet4Identity() { return GetElement(39); }
    public static OhElement GetTriplet4Lambda1()  { return GetElement(40); }
    public static OhElement GetTriplet4Lambda2()  { return GetElement(41); }
    public static OhElement GetTriplet4Lambda3()  { return GetElement(42); }
    public static OhElement GetTriplet4Lambda4()  { return GetElement(43); }
    public static OhElement GetTriplet4Lambda5()  { return GetElement(44); }
    public static OhElement GetTriplet4Lambda6()  { return GetElement(45); }
    public static OhElement GetTriplet4Lambda7()  { return GetElement(46); }
    public static OhElement GetTriplet4Lambda8()  { return GetElement(47); }
    
    /*
     * GetElement47
     *
     * Sparse real element with coefficients involving sqrt(3)/24, sqrt(3)/48,
     * and -sqrt(3)/12, with improper-sector signs reversed relative to the
     * corresponding proper-sector pattern.
     *
     * Visible in the uploaded table. :contentReference[oaicite:9]{index=9}
     */
    public static OhElement GetElement47() {

        ComplexNumber[] coefficients = ZeroCoefficients();

        SetRealCoefficient(coefficients, 1,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 2,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 3, -ROOT_THREE_OVER_12);

        SetRealCoefficient(coefficients, 12, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 16,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 14, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 17,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 15, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 13, -ROOT_THREE_OVER_48);

        SetRealCoefficient(coefficients, 18,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 19,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 20, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 21, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 22,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 23,  ROOT_THREE_OVER_48);

        SetRealCoefficient(coefficients, 25, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 26, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 27,  ROOT_THREE_OVER_12);

        SetRealCoefficient(coefficients, 36,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 40, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 38,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 41, -ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 39,  ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 37,  ROOT_THREE_OVER_48);

        SetRealCoefficient(coefficients, 42, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 43, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 44,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 45,  ROOT_THREE_OVER_24);
        SetRealCoefficient(coefficients, 46, -ROOT_THREE_OVER_48);
        SetRealCoefficient(coefficients, 47, -ROOT_THREE_OVER_48);

        return new OhElement(coefficients);
    }

    public static OhElement[] GetDoublet1PauliBlock() {
        return new OhElement[] {
            GetDoublet1Identity(),
            GetDoublet1SigmaX(),
            GetDoublet1SigmaY(),
            GetDoublet1SigmaZ()
        };
    }

    public static OhElement[] GetDoublet2PauliBlock() {
        return new OhElement[] {
            GetDoublet2Identity(),
            GetDoublet2SigmaX(),
            GetDoublet2SigmaY(),
            GetDoublet2SigmaZ()
        };
    }

    public static OhElement[] GetTriplet1GellMannBlock() {
        return new OhElement[] {
            GetTriplet1Identity(),
            GetTriplet1Lambda1(),
            GetTriplet1Lambda2(),
            GetTriplet1Lambda3(),
            GetTriplet1Lambda4(),
            GetTriplet1Lambda5(),
            GetTriplet1Lambda6(),
            GetTriplet1Lambda7(),
            GetTriplet1Lambda8()
        };
    }

    public static OhElement[] GetTriplet2GellMannBlock() {
        return new OhElement[] {
            GetTriplet2Identity(),
            GetTriplet2Lambda1(),
            GetTriplet2Lambda2(),
            GetTriplet2Lambda3(),
            GetTriplet2Lambda4(),
            GetTriplet2Lambda5(),
            GetTriplet2Lambda6(),
            GetTriplet2Lambda7(),
            GetTriplet2Lambda8()
        };
    }

    public static OhElement[] GetTriplet3GellMannBlock() {
        return new OhElement[] {
            GetTriplet3Identity(),
            GetTriplet3Lambda1(),
            GetTriplet3Lambda2(),
            GetTriplet3Lambda3(),
            GetTriplet3Lambda4(),
            GetTriplet3Lambda5(),
            GetTriplet3Lambda6(),
            GetTriplet3Lambda7(),
            GetTriplet3Lambda8()
        };
    }

    public static OhElement[] GetTriplet4GellMannBlock() {
        return new OhElement[] {
            GetTriplet4Identity(),
            GetTriplet4Lambda1(),
            GetTriplet4Lambda2(),
            GetTriplet4Lambda3(),
            GetTriplet4Lambda4(),
            GetTriplet4Lambda5(),
            GetTriplet4Lambda6(),
            GetTriplet4Lambda7(),
            GetTriplet4Lambda8()
        };
    }    
    
}
