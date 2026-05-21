package ohcalculatorpackage;

/*
 * OhPauliElement
 *
 * This class represents one element of the Oh group algebra over the
 * Pauli algebra.
 *
 * GENERAL FORM
 * ------------
 * A general OhPauliElement has the form:
 *
 *     sum over k from 0 to 47 of pauliCoefficient[k] * g_k
 *
 * where:
 *
 *     - pauliCoefficient[k] is a PauliValue
 *     - g_k is the kth basis element of the Oh group in our fixed ordering
 *
 * VERY IMPORTANT DISTINCTION
 * --------------------------
 * This class is the Pauli-valued analogue of OhElement.
 *
 * So:
 *
 *     OhElement      = Oh group algebra with ComplexNumber coefficients
 *     OhPauliElement = Oh group algebra with PauliValue coefficients
 *
 * WHY THIS CLASS MATTERS
 * ----------------------
 * This is much closer to the full intended Oh Calculator.
 *
 * It allows us to combine:
 *
 * - the finite group structure of Oh
 * - with the noncommutative Pauli algebra
 *
 * and therefore begins to realize the original design goal of the project.
 *
 * DESIGN PRINCIPLE
 * ----------------
 * We deliberately reuse the already-tested Oh multiplication table.
 *
 * The basis-element multiplication is still controlled by the group:
 *
 *     g_i * g_j = g_k
 *
 * while the coefficient multiplication is controlled by the Pauli algebra:
 *
 *     P_i * Q_j
 *
 * Therefore:
 *
 *     (P_i g_i)(Q_j g_j) = (P_i Q_j)(g_i g_j)
 *
 * PROGRAMMING STYLE
 * -----------------
 * This class is written in the same heavily commented, explicit, and
 * project-focused style as the rest of the codebase.
 *
 * @author Carl Brannen and ChatGPT
 */
public class OhPauliElement {

    /*
     * NUMBER_OF_BASIS_ELEMENTS
     *
     * The Oh group has 48 basis elements.
     */
    private static final int NUMBER_OF_BASIS_ELEMENTS = 48;

    /*
     * coefficients
     *
     * These are the 48 PauliValue coefficients of the current OhPauliElement.
     *
     * If:
     *
     *     A = sum_k coefficient[k] * g_k
     *
     * then coefficients[k] stores the PauliValue multiplying the kth
     * Oh basis element.
     */
    private final PauliValue[] coefficients;

    /*
     * Constructor
     *
     * This constructs one OhPauliElement from an array of 48 PauliValue
     * coefficients.
     *
     * IMPORTANT BEHAVIOR
     * ------------------
     * We copy the input array into a new internal array, just as we did for
     * OhElement. This prevents accidental modification from outside.
     */
    public OhPauliElement(PauliValue[] inputCoefficients) {

        /*
         * Check that exactly 48 coefficients were supplied.
         */
        if (inputCoefficients.length != NUMBER_OF_BASIS_ELEMENTS) {
            throw new IllegalArgumentException(
                    "OhPauliElement constructor requires exactly 48 coefficients."
            );
        }

        /*
         * Allocate the internal coefficient array.
         */
        this.coefficients = new PauliValue[NUMBER_OF_BASIS_ELEMENTS];

        /*
         * Copy the coefficients one by one.
         */
        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            this.coefficients[index] = inputCoefficients[index];
        }
    }

    /*
     * zero
     *
     * Returns the additive zero of the Oh group algebra over the Pauli algebra.
     *
     * All 48 coefficients are the Pauli zero.
     */
    public static OhPauliElement zero() {

        PauliValue[] zeroCoefficients = new PauliValue[NUMBER_OF_BASIS_ELEMENTS];

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            zeroCoefficients[index] = PauliValue.zero();
        }

        return new OhPauliElement(zeroCoefficients);
    }

    /*
     * basisElement
     *
     * Returns the pure basis element with Pauli identity coefficient in one slot
     * and Pauli zero in all other slots.
     *
     * This is the natural Pauli-valued analogue of OhElement.basisElement(...).
     */
    public static OhPauliElement basisElement(int basisIndex) {

        if (basisIndex < 0 || basisIndex >= NUMBER_OF_BASIS_ELEMENTS) {
            throw new IllegalArgumentException(
                    "OhPauliElement basisElement index out of range."
            );
        }

        PauliValue[] newCoefficients = new PauliValue[NUMBER_OF_BASIS_ELEMENTS];

        /*
         * Start with all coefficients equal to the Pauli zero.
         */
        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            newCoefficients[index] = PauliValue.zero();
        }

        /*
         * Put the Pauli identity in the chosen slot.
         */
        newCoefficients[basisIndex] = PauliValue.identity();

        return new OhPauliElement(newCoefficients);
    }

    /*
     * identity
     *
     * Returns the multiplicative identity of the Oh-Pauli algebra.
     *
     * This is the Oh identity basis element with Pauli identity coefficient.
     */
    public static OhPauliElement identity() {
        return basisElement(0);
    }

    /*
     * getCoefficient
     *
     * Returns the PauliValue coefficient in the requested basis slot.
     */
    public PauliValue getCoefficient(int basisIndex) {

        if (basisIndex < 0 || basisIndex >= NUMBER_OF_BASIS_ELEMENTS) {
            throw new IllegalArgumentException(
                    "OhPauliElement getCoefficient index out of range."
            );
        }

        return coefficients[basisIndex];
    }

    /*
     * add
     *
     * Adds two OhPauliElement objects coefficient by coefficient.
     */
    public OhPauliElement add(OhPauliElement other) {

        PauliValue[] resultCoefficients = new PauliValue[NUMBER_OF_BASIS_ELEMENTS];

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            resultCoefficients[index] =
                    this.coefficients[index].add(other.coefficients[index]);
        }

        return new OhPauliElement(resultCoefficients);
    }

    /*
     * subtract
     *
     * Subtracts two OhPauliElement objects coefficient by coefficient.
     */
    public OhPauliElement subtract(OhPauliElement other) {

        PauliValue[] resultCoefficients = new PauliValue[NUMBER_OF_BASIS_ELEMENTS];

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            resultCoefficients[index] =
                    this.coefficients[index].subtract(other.coefficients[index]);
        }

        return new OhPauliElement(resultCoefficients);
    }

    /*
     * multiplyByPauliValue
     *
     * Multiplies the entire OhPauliElement by one PauliValue on the left.
     *
     * For now, this is enough for the kinds of tests we want to write.
     */
    public OhPauliElement multiplyByPauliValue(PauliValue scalar) {

        PauliValue[] resultCoefficients = new PauliValue[NUMBER_OF_BASIS_ELEMENTS];

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            resultCoefficients[index] =
                    scalar.multiply(this.coefficients[index]);
        }

        return new OhPauliElement(resultCoefficients);
    }

    /*
     * multiply
     *
     * Multiplies two OhPauliElement objects using:
     *
     * - PauliValue multiplication on coefficients
     * - Oh basis multiplication via the already-tested Oh multiplication table
     *
     * ALGEBRAIC IDEA
     * --------------
     * If:
     *
     *     A = sum_i P_i g_i
     *     B = sum_j Q_j g_j
     *
     * then:
     *
     *     A * B = sum_{i,j} (P_i * Q_j) (g_i * g_j)
     *
     * and the basis product g_i * g_j is determined by the Oh multiplication
     * table already embedded in OhElement.
     */
    public OhPauliElement multiply(OhPauliElement other) {

        PauliValue[] resultCoefficients = new PauliValue[NUMBER_OF_BASIS_ELEMENTS];

        /*
         * Initialize the result coefficients to the Pauli zero.
         */
        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            resultCoefficients[index] = PauliValue.zero();
        }

        /*
         * Multiply every left coefficient against every right coefficient.
         */
        for (int leftIndex = 0; leftIndex < NUMBER_OF_BASIS_ELEMENTS; leftIndex++) {

            PauliValue leftCoefficient = this.coefficients[leftIndex];

            /*
             * Skip left coefficients that are zero.
             */
            if (PauliValueIsZero(leftCoefficient)) {
                continue;
            }

            for (int rightIndex = 0; rightIndex < NUMBER_OF_BASIS_ELEMENTS; rightIndex++) {

                PauliValue rightCoefficient = other.coefficients[rightIndex];

                /*
                 * Skip right coefficients that are zero.
                 */
                if (PauliValueIsZero(rightCoefficient)) {
                    continue;
                }

                /*
                 * Determine which Oh basis element receives the contribution.
                 *
                 * We do this by multiplying the corresponding pure Oh basis
                 * elements and then finding the unique nonzero slot.
                 *
                 * This is not the most optimized possible approach, but it is
                 * clear, safe, and uses the already-tested Oh multiplication.
                 */
                OhElement leftBasisElement = OhElement.basisElement(leftIndex);
                OhElement rightBasisElement = OhElement.basisElement(rightIndex);
                OhElement basisProduct = leftBasisElement.multiply(rightBasisElement);

                int productIndex = FindSingleNonzeroBasisIndex(basisProduct);

                /*
                 * Multiply the Pauli coefficients and accumulate the result.
                 */
                PauliValue contribution = leftCoefficient.multiply(rightCoefficient);

                resultCoefficients[productIndex] =
                        resultCoefficients[productIndex].add(contribution);
            }
        }

        return new OhPauliElement(resultCoefficients);
    }

    /*
     * toString
     *
     * Compact printer for an OhPauliElement.
     *
     * We print only the nonzero coefficients, one term per line.
     */
    @Override
    public String toString() {

        String result = "";
        boolean firstTermFound = false;

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {

            if (!PauliValueIsZero(coefficients[index])) {

                if (firstTermFound) {
                    result += "\n";
                }

                String basisLabel = OhElement.getBasisElementInfo(index).getPermutationLabel();

                result += "[" + coefficients[index] + "] * " + basisLabel;
                firstTermFound = true;
            }
        }

        if (!firstTermFound) {
            return "0";
        }

        return result;
    }

    /*
     * toClassGroupedString
     *
     * Class-grouped printer for an OhPauliElement.
     *
     * This is the Pauli-valued analogue of OhElement.toClassGroupedString().
     */
    public String toClassGroupedString() {

        String result = "";
        boolean anyNonzeroTermFound = false;

        for (int classIndex = 0; classIndex < 10; classIndex++) {

            boolean classHasNonzeroTerm = false;
            String classBlock = "";

            for (int elementIndex = 0; elementIndex < NUMBER_OF_BASIS_ELEMENTS; elementIndex++) {

                OhBasisElement basisInfo = OhElement.getBasisElementInfo(elementIndex);

                if (basisInfo == null) {
                    continue;
                }

                if (basisInfo.getClassIndex() == classIndex
                        && !PauliValueIsZero(coefficients[elementIndex])) {

                    if (!classHasNonzeroTerm) {
                        OhClassInfo classInfo = OhElement.getClassInfo(classIndex);

                        classBlock += classInfo.getClassCode()
                                + " representative "
                                + classInfo.getRepresentativePermutationLabel()
                                + "\n";

                        classHasNonzeroTerm = true;
                    }

                    classBlock += "    "
                            + coefficients[elementIndex]
                            + " * "
                            + basisInfo.getPermutationLabel()
                            + "\n";
                }
            }

            if (classHasNonzeroTerm) {

                if (anyNonzeroTermFound) {
                    result += "\n";
                }

                result += classBlock;
                anyNonzeroTermFound = true;
            }
        }

        if (!anyNonzeroTermFound) {
            return "0";
        }

        if (result.endsWith("\n")) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    /*
     * PauliValueIsZero
     *
     * This helper checks whether a PauliValue is effectively zero.
     *
     * A PauliValue is zero exactly when all four of its ComplexNumber
     * coefficients are zero.
     */
    private static boolean PauliValueIsZero(PauliValue value) {

        double tolerance = 1.0e-12;

        return Math.abs(value.getIdentityPart().getRealPart()) < tolerance
                && Math.abs(value.getIdentityPart().getImaginaryPart()) < tolerance
                && Math.abs(value.getSigmaXPart().getRealPart()) < tolerance
                && Math.abs(value.getSigmaXPart().getImaginaryPart()) < tolerance
                && Math.abs(value.getSigmaYPart().getRealPart()) < tolerance
                && Math.abs(value.getSigmaYPart().getImaginaryPart()) < tolerance
                && Math.abs(value.getSigmaZPart().getRealPart()) < tolerance
                && Math.abs(value.getSigmaZPart().getImaginaryPart()) < tolerance;
    }

    /*
     * FindSingleNonzeroBasisIndex
     *
     * This helper assumes that the supplied OhElement is a pure basis element,
     * meaning that exactly one coefficient is 1 and all others are zero.
     *
     * It returns the index of that unique nonzero slot.
     *
     * WHY THIS EXISTS
     * ---------------
     * When multiplying pure basis elements inside the Oh group, the result
     * should be another pure basis element. This helper extracts its index.
     */
    private static int FindSingleNonzeroBasisIndex(OhElement value) {

        int foundIndex = -1;

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {

            ComplexNumber coefficient = value.getCoefficient(index);

            boolean coefficientIsZero =
                    Math.abs(coefficient.getRealPart()) < 1.0e-12
                    && Math.abs(coefficient.getImaginaryPart()) < 1.0e-12;

            boolean coefficientIsOne =
                    Math.abs(coefficient.getRealPart() - 1.0) < 1.0e-12
                    && Math.abs(coefficient.getImaginaryPart()) < 1.0e-12;

            if (coefficientIsOne) {
                if (foundIndex >= 0) {
                    throw new IllegalStateException(
                            "FindSingleNonzeroBasisIndex found more than one nonzero basis slot."
                    );
                }
                foundIndex = index;
            } else if (!coefficientIsZero) {
                throw new IllegalStateException(
                        "FindSingleNonzeroBasisIndex encountered a coefficient that was neither zero nor one."
                );
            }
        }

        if (foundIndex < 0) {
            throw new IllegalStateException(
                    "FindSingleNonzeroBasisIndex found no nonzero basis slot."
            );
        }

        return foundIndex;
    }
}