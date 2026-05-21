package ohcalculatorpackage;

/*
 * OhElement
 *
 * This class represents one element of the Oh group algebra with
 * complex-number coefficients.
 *
 * GENERAL FORM
 * ------------
 * A general Oh group algebra element has the form:
 *
 *     sum over k from 0 to 47 of coefficient[k] * g_k
 *
 * where:
 *
 *     - coefficient[k] is a ComplexNumber,
 *     - g_k is the kth basis element of the Oh group in our fixed ordering.
 *
 * VERY IMPORTANT DISTINCTION
 * --------------------------
 * This class represents a GROUP ALGEBRA VALUE.
 *
 * It does NOT merely describe one group element. For that, we have:
 *
 *     OhBasisElement
 *
 * It does NOT merely describe one conjugacy class. For that, we have:
 *
 *     OhClassInfo
 *
 * So the intended roles are:
 *
 *     OhClassInfo    = metadata for one conjugacy class
 *     OhBasisElement = metadata for one group element
 *     OhElement      = a general linear combination of group elements
 *
 * DESIGN PRINCIPLE
 * ----------------
 * Internally, multiplication is performed using:
 *
 * 1. coefficient arrays,
 * 2. a fixed basis ordering,
 * 3. a precomputed multiplication lookup table.
 *
 * This is exactly the structure we want for the project because:
 *
 * - it is efficient,
 * - it is easy to explain,
 * - it is easy to debug,
 * - it separates human-readable labels from algebraic computation.
 *
 * FIRST VERSION POLICY
 * --------------------
 * This file is intentionally written first as a working shell.
 *
 * That means:
 *
 * - the class structure is real,
 * - the main algebra methods are real,
 * - the metadata tables exist,
 * - the multiplication table exists,
 *
 * but only the identity entry is initialized completely at first.
 *
 * We will fill in the remaining 47 basis elements and the full multiplication
 * table carefully in later steps.
 *
 * @author Carl Brannen and ChatGPT
 */
public class OhElement {
    
    /*
     * Canonical permutation labels in sovereign basis order.
     *
     * This list is now the official definition of basis indices 0..47.
     */
    private static final String[] CANONICAL_PERMUTATION_LABELS = {
        /* class 0 */
        "()",

        /* class 1 */
        "(12)(34)", "(13)(24)", "(14)(23)",

        /* class 2 */
        "(123)", "(124)", "(134)", "(234)",
        "(132)", "(243)", "(143)", "(142)",

        /* class 3 */
        "(1234)", "(1432)", "(1324)", "(1423)", "(1243)", "(1342)",

        /* class 4 */
        "(12)", "(13)", "(14)", "(23)", "(24)", "(34)",

        /* class 5 */
        "()i",

        /* class 6 */
        "(12)(34)i", "(13)(24)i", "(14)(23)i",

        /* class 7 */
        "(123)i", "(124)i", "(134)i", "(234)i",
        "(132)i", "(243)i", "(143)i", "(142)i",

        /* class 8 */
        "(1234)i", "(1432)i", "(1324)i", "(1423)i", "(1243)i", "(1342)i",

        /* class 9 */
        "(12)i", "(13)i", "(14)i", "(23)i", "(24)i", "(34)i"
    };

    /*
     * Canonical axis-image labels in the same sovereign basis order.
     *
     * These are now secondary descriptive labels, not the source of truth
     * for multiplication.
     */
    private static final String[] CANONICAL_AXIS_IMAGE_LABELS = {
        /* class 0 */
        "xyz",

        /* class 1 */
        "xYZ", "XyZ", "XYz",

        /* class 2 */
        "yzx", "yZX", "YzX", "YZx",
        "zxy", "zXY", "ZxY", "ZXy",

        /* class 3 */
        "xzY", "xZy", "yXz", "Yxz", "zyX", "Zyx",

        /* class 4 */
        "Xzy", "XZY", "yxZ", "YXZ", "zYx", "ZYX",

        /* class 5 */
        "XYZ",

        /* class 6 */
        "Xyz", "xYz", "xyZ",

        /* class 7 */
        "YZX", "Yzx", "yZx", "yzX",
        "ZXY", "Zxy", "zXy", "zxY",

        /* class 8 */
        "XZy", "XzY", "YxZ", "yXZ", "ZYx", "zYX",

        /* class 9 */
        "xZY", "xzy", "YXz", "yxz", "ZyX", "zyx"
    };
    /*
     * NUMBER_OF_BASIS_ELEMENTS
     *
     * The Oh group has 48 elements.
     *
     * Therefore the Oh group algebra basis has 48 basis vectors.
     */
    private static final int NUMBER_OF_BASIS_ELEMENTS = 48;

    /*
     * NUMBER_OF_CLASSES
     *
     * In the ordering we are using, the Oh group has 10 conjugacy classes.
     */
    private static final int NUMBER_OF_CLASSES = 10;

    /*
     * IDENTITY_ELEMENT_INDEX
     *
     * We intend the group identity () to be basis element 0.
     *
     * This is consistent with the class ordering where the first class
     * has size 1 and contains the identity.
     */
    private static final int IDENTITY_ELEMENT_INDEX = 0;

    /*
     * classInfoTable
     *
     * This table stores metadata for the 10 conjugacy classes.
     *
     * The entry:
     *
     *     classInfoTable[classIndex]
     *
     * stores the OhClassInfo object for that class.
     */
    private static final OhClassInfo[] classInfoTable =
            new OhClassInfo[NUMBER_OF_CLASSES];

    /*
     * basisElementTable
     *
     * This table stores metadata for the 48 group basis elements.
     *
     * The entry:
     *
     *     basisElementTable[elementIndex]
     *
     * stores the OhBasisElement object for that element.
     */
    private static final OhBasisElement[] basisElementTable =
            new OhBasisElement[NUMBER_OF_BASIS_ELEMENTS];

    /*
     * multiplicationTable
     *
     * This table stores the group multiplication law in index form.
     *
     * If:
     *
     *     multiplicationTable[leftIndex][rightIndex] = productIndex
     *
     * then:
     *
     *     g_leftIndex * g_rightIndex = g_productIndex
     *
     * where g_k denotes the kth basis element of the group.
     *
     * IMPORTANT NOTE
     * --------------
     * In this first shell version, we initialize every entry to -1 and then
     * fill in only the identity row and column.
     *
     * Later we will replace the placeholder values with the full table.
     */
    private static final int[][] multiplicationTable =
            new int[NUMBER_OF_BASIS_ELEMENTS][NUMBER_OF_BASIS_ELEMENTS];

    /*
     * coefficients
     *
     * These are the 48 complex coefficients of the current OhElement.
     *
     * If the current object is:
     *
     *     A = sum_k coefficient[k] * g_k
     *
     * then coefficients[k] stores the coefficient multiplying g_k.
     */
    private final ComplexNumber[] coefficients;

    /*
     * Static initializer block
     *
     * This block runs once, automatically, when the OhElement class is first
     * loaded by Java.
     *
     * WHY WE USE THIS
     * ---------------
     * We use a static initializer because the class metadata tables and
     * multiplication table are properties of the class as a whole, not of
     * any one particular OhElement object.
     *
     * The methods called here are kept short so that the initialization
     * logic is easy to read.
     */
    static {
        InitializeClassInfoTable();
        InitializeBasisElementTable();
        InitializeMultiplicationTable();
    }

    /*
     * GetClassIndexFromBasisIndex
     *
     * Returns the conjugacy-class index corresponding to the sovereign basis order.
     */
    private static int GetClassIndexFromBasisIndex(int basisIndex) {

        if (basisIndex == 0) {
            return 0;
        } else if (basisIndex <= 3) {
            return 1;
        } else if (basisIndex <= 11) {
            return 2;
        } else if (basisIndex <= 17) {
            return 3;
        } else if (basisIndex <= 23) {
            return 4;
        } else if (basisIndex == 24) {
            return 5;
        } else if (basisIndex <= 27) {
            return 6;
        } else if (basisIndex <= 35) {
            return 7;
        } else if (basisIndex <= 41) {
            return 8;
        } else {
            return 9;
        }
    }

    /*
     * BuildCanonicalLabelToIndexTable
     *
     * Builds a lookup table from canonical permutation label to sovereign basis index.
     */
    private static java.util.HashMap<String, Integer> BuildCanonicalLabelToIndexTable() {

        java.util.HashMap<String, Integer> labelToIndex =
                new java.util.HashMap<String, Integer>();

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            labelToIndex.put(CANONICAL_PERMUTATION_LABELS[index], index);
        }

        return labelToIndex;
    }

    /*
     * FindInverseBasisIndexFromCanonicalLabels
     *
     * Finds the inverse basis index by brute force using the canonical labels
     * and the OhPermutationElement multiplication.
     */
    private static int FindInverseBasisIndexFromCanonicalLabels(int basisIndex) {

        OhPermutationElement element =
                OhPermutationElement.fromLabel(CANONICAL_PERMUTATION_LABELS[basisIndex]);

        OhPermutationElement identity = OhPermutationElement.identity();

        for (int candidateIndex = 0; candidateIndex < NUMBER_OF_BASIS_ELEMENTS; candidateIndex++) {

            OhPermutationElement candidate =
                    OhPermutationElement.fromLabel(CANONICAL_PERMUTATION_LABELS[candidateIndex]);

            if (element.multiply(candidate).equals(identity)
                    && candidate.multiply(element).equals(identity)) {
                return candidateIndex;
            }
        }

        throw new IllegalStateException(
                "Could not find inverse basis index for sovereign basis index " + basisIndex
        );
    }    
    
    
    /*
     * Constructor
     *
     * This constructs one OhElement from an array of 48 coefficients.
     *
     * IMPORTANT BEHAVIOR
     * ------------------
     * We COPY the input array into a new internal array.
     *
     * This is good defensive programming because it prevents outside code
     * from changing the object's internal coefficients after construction.
     */
    public OhElement(ComplexNumber[] inputCoefficients) {

        /*
         * Check that the caller supplied exactly 48 coefficients.
         */
        if (inputCoefficients.length != NUMBER_OF_BASIS_ELEMENTS) {
            throw new IllegalArgumentException(
                    "OhElement constructor requires exactly 48 coefficients."
            );
        }

        /*
         * Allocate the internal coefficient array.
         */
        this.coefficients = new ComplexNumber[NUMBER_OF_BASIS_ELEMENTS];

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
     * Returns the additive zero of the Oh group algebra.
     *
     * That is, all 48 coefficients are zero.
     */
    public static OhElement zero() {

        ComplexNumber[] zeroCoefficients =
                new ComplexNumber[NUMBER_OF_BASIS_ELEMENTS];

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            zeroCoefficients[index] = new ComplexNumber(0.0, 0.0);
        }

        return new OhElement(zeroCoefficients);
    }

    /*
     * basisElement
     *
     * Returns the pure group basis element with coefficient 1 in one slot
     * and 0 in all other slots.
     *
     * For example:
     *
     *     basisElement(0)
     *
     * returns the algebra element corresponding to the group identity.
     */
    public static OhElement basisElement(int basisIndex) {

        if (basisIndex < 0 || basisIndex >= NUMBER_OF_BASIS_ELEMENTS) {
            throw new IllegalArgumentException(
                    "basisElement index out of range."
            );
        }

        ComplexNumber[] newCoefficients =
                new ComplexNumber[NUMBER_OF_BASIS_ELEMENTS];

        /*
         * Start with all coefficients zero.
         */
        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            newCoefficients[index] = new ComplexNumber(0.0, 0.0);
        }

        /*
         * Set the chosen slot to 1.
         */
        newCoefficients[basisIndex] = new ComplexNumber(1.0, 0.0);

        return new OhElement(newCoefficients);
    }

    /*
     * identity
     *
     * Returns the multiplicative identity of the Oh group algebra.
     *
     * Since the group identity is intended to sit at basis index 0,
     * this is simply basisElement(0).
     */
    public static OhElement identity() {
        return basisElement(IDENTITY_ELEMENT_INDEX);
    }

    /*
     * getCoefficient
     *
     * Returns the coefficient in the requested basis slot.
     */
    public ComplexNumber getCoefficient(int basisIndex) {

        if (basisIndex < 0 || basisIndex >= NUMBER_OF_BASIS_ELEMENTS) {
            throw new IllegalArgumentException(
                    "getCoefficient index out of range."
            );
        }

        return coefficients[basisIndex];
    }

    /*
     * getNumberOfBasisElements
     *
     * Returns the number 48.
     *
     * This is mostly useful in test code or in display code.
     */
    public static int getNumberOfBasisElements() {
        return NUMBER_OF_BASIS_ELEMENTS;
    }

    /*
     * getClassInfo
     *
     * Returns metadata for the requested conjugacy class.
     */
    public static OhClassInfo getClassInfo(int classIndex) {

        if (classIndex < 0 || classIndex >= NUMBER_OF_CLASSES) {
            throw new IllegalArgumentException(
                    "getClassInfo class index out of range."
            );
        }

        return classInfoTable[classIndex];
    }

    /*
     * getBasisElementInfo
     *
     * Returns metadata for the requested group basis element.
     */
    public static OhBasisElement getBasisElementInfo(int elementIndex) {

        if (elementIndex < 0 || elementIndex >= NUMBER_OF_BASIS_ELEMENTS) {
            throw new IllegalArgumentException(
                    "getBasisElementInfo element index out of range."
            );
        }

        return basisElementTable[elementIndex];
    }

    /*
     * add
     *
     * Adds two Oh group algebra elements coefficient by coefficient.
     */
    public OhElement add(OhElement other) {

        ComplexNumber[] resultCoefficients =
                new ComplexNumber[NUMBER_OF_BASIS_ELEMENTS];

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            resultCoefficients[index] =
                    this.coefficients[index].add(other.coefficients[index]);
        }

        return new OhElement(resultCoefficients);
    }

    /*
     * subtract
     *
     * Subtracts two Oh group algebra elements coefficient by coefficient.
     */
    public OhElement subtract(OhElement other) {

        ComplexNumber[] resultCoefficients =
                new ComplexNumber[NUMBER_OF_BASIS_ELEMENTS];

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            resultCoefficients[index] =
                    this.coefficients[index].subtract(other.coefficients[index]);
        }

        return new OhElement(resultCoefficients);
    }

    /*
     * multiplyByComplex
     *
     * Multiplies the entire Oh group algebra element by one complex scalar.
     *
     * Each coefficient is multiplied by that scalar.
     */
    public OhElement multiplyByComplex(ComplexNumber scalar) {

        ComplexNumber[] resultCoefficients =
                new ComplexNumber[NUMBER_OF_BASIS_ELEMENTS];

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            resultCoefficients[index] =
                    this.coefficients[index].multiply(scalar);
        }

        return new OhElement(resultCoefficients);
    }

    /*
     * multiply
     *
     * Multiplies two Oh group algebra elements using the group multiplication
     * lookup table.
     *
     * ALGEBRAIC IDEA
     * --------------
     * If:
     *
     *     A = sum_i a_i g_i
     *     B = sum_j b_j g_j
     *
     * then:
     *
     *     A * B = sum_{i,j} a_i b_j (g_i g_j)
     *
     * and the lookup table tells us which basis element g_i g_j equals.
     *
     * IMPORTANT CURRENT STATUS
     * ------------------------
     * In this first shell version, only the identity row and identity column
     * of the multiplication table are initialized.
     *
     * Therefore multiplication involving the identity works, but general
     * products should not yet be trusted until the full table is filled in.
     *
     * IMPORTANT PROGRAMMING DETAIL
     * ----------------------------
     * We must skip pairs of basis slots whose coefficients are zero.
     *
     * Otherwise, even a harmless product like:
     *
     *     basisElement(7) * identity()
     *
     * would eventually examine unrelated table entries such as
     * multiplicationTable[1][1], even though the corresponding coefficient
     * contribution is zero.
     *
     * So we only consult the multiplication table when both coefficients
     * in the pair are actually nonzero.
     */
    public OhElement multiply(OhElement other) {

        ComplexNumber[] resultCoefficients =
                new ComplexNumber[NUMBER_OF_BASIS_ELEMENTS];

        /*
         * Initialize the result coefficients to zero.
         */
        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {
            resultCoefficients[index] = new ComplexNumber(0.0, 0.0);
        }

        /*
         * Multiply every left coefficient against every right coefficient,
         * but skip pairs whose contribution is automatically zero.
         */
        for (int leftIndex = 0; leftIndex < NUMBER_OF_BASIS_ELEMENTS; leftIndex++) {

            ComplexNumber leftCoefficient = this.coefficients[leftIndex];

            /*
             * If the left coefficient is zero, then every contribution from
             * this leftIndex is zero, so we can skip the whole inner loop.
             */
            if (ComplexNumberIsZero(leftCoefficient)) {
                continue;
            }

            for (int rightIndex = 0; rightIndex < NUMBER_OF_BASIS_ELEMENTS; rightIndex++) {

                ComplexNumber rightCoefficient = other.coefficients[rightIndex];

                /*
                 * If the right coefficient is zero, then this particular pair
                 * contributes nothing, so skip it.
                 */
                if (ComplexNumberIsZero(rightCoefficient)) {
                    continue;
                }

                int productIndex = multiplicationTable[leftIndex][rightIndex];

                /*
                 * In the unfinished shell version, unused table entries are -1.
                 * If one is encountered for a genuinely nonzero contribution,
                 * that means the multiplication table has not yet been filled in
                 * for that pair.
                 */
                if (productIndex < 0) {
                    throw new IllegalStateException(
                            "Oh multiplication table entry not initialized for leftIndex="
                            + leftIndex + " rightIndex=" + rightIndex
                    );
                }

                ComplexNumber contribution =
                        leftCoefficient.multiply(rightCoefficient);

                resultCoefficients[productIndex] =
                        resultCoefficients[productIndex].add(contribution);
            }
        }

        return new OhElement(resultCoefficients);
    }

    /*
     * toString
     *
     * This is the compact default printer for an OhElement.
     *
     * PRINTING POLICY
     * ---------------
     * We print only the nonzero coefficients.
     * Each nonzero term is printed on one line in the form:
     *
     *     [coefficient] * permutationLabel
     *
     * If all coefficients are zero, we print 0.
     *
     * WHY KEEP THIS SIMPLE?
     * ---------------------
     * This compact form is useful for:
     *
     * - quick debugging,
     * - checking a small number of terms,
     * - and preserving a simple default display.
     *
     * For a more structured display grouped by conjugacy classes, use:
     *
     *     toClassGroupedString()
     */
    @Override
    public String toString() {

        String result = "";
        boolean firstTermFound = false;

        for (int index = 0; index < NUMBER_OF_BASIS_ELEMENTS; index++) {

            if (!ComplexNumberIsZero(coefficients[index])) {

                if (firstTermFound) {
                    result += "\n";
                }

                String basisLabel;
                if (basisElementTable[index] != null) {
                    basisLabel = basisElementTable[index].getPermutationLabel();
                } else {
                    basisLabel = "g_" + index;
                }

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
     * This printer displays the OhElement grouped by conjugacy classes.
     *
     * WHY THIS METHOD EXISTS
     * ----------------------
     * For Oh calculations, a flat list of 48 possible coefficients is often
     * harder to read than a class-grouped display.
     *
     * This method therefore prints the nonzero terms class by class, using:
     *
     * - the class code,
     * - the class representative,
     * - and then the nonzero terms belonging to that class.
     *
     * EXAMPLE STYLE
     * -------------
     * A typical output might look like:
     *
     *     K_xyz representative ()
     *         [1.000000000000] * ()
     *
     *     K_yzx representative (123)
     *         [2.000000000000] * (123)
     *         [-1.000000000000] * (132)
     *
     * If the whole element is zero, this method prints 0.
     */
    public String toClassGroupedString() {

        String result = "";
        boolean anyNonzeroTermFound = false;

        /*
         * Loop through the 10 conjugacy classes in order.
         */
        for (int classIndex = 0; classIndex < NUMBER_OF_CLASSES; classIndex++) {

            boolean classHasNonzeroTerm = false;
            String classBlock = "";

            /*
             * Search all 48 basis slots and collect the nonzero terms that
             * belong to the current class.
             */
            for (int elementIndex = 0; elementIndex < NUMBER_OF_BASIS_ELEMENTS; elementIndex++) {

                OhBasisElement basisInfo = basisElementTable[elementIndex];

                /*
                 * A null entry would indicate an improperly initialized basis table.
                 * We skip it here because the tests should already catch that problem.
                 */
                if (basisInfo == null) {
                    continue;
                }

                if (basisInfo.getClassIndex() == classIndex
                        && !ComplexNumberIsZero(coefficients[elementIndex])) {

                    /*
                     * If this is the first nonzero term found in the class,
                     * print the class header first.
                     */
                    if (!classHasNonzeroTerm) {

                        OhClassInfo classInfo = classInfoTable[classIndex];

                        if (classInfo != null) {
                            classBlock += classInfo.getClassCode()
                                    + " representative "
                                    + classInfo.getRepresentativePermutationLabel()
                                    + "\n";
                        } else {
                            classBlock += "classIndex " + classIndex + "\n";
                        }

                        classHasNonzeroTerm = true;
                    }

                    classBlock += "    "
                            + "[" + coefficients[elementIndex] + "] * "
                            + basisInfo.getPermutationLabel()
                            + "\n";
                }
            }

            /*
             * If the current class contributed any nonzero terms, append its block
             * to the full output.
             */
            if (classHasNonzeroTerm) {

                if (anyNonzeroTermFound) {
                    result += "\n";
                }

                result += classBlock;
                anyNonzeroTermFound = true;
            }
        }

        /*
         * If no nonzero term was found anywhere, print 0.
         */
        if (!anyNonzeroTermFound) {
            return "0";
        }

        /*
         * Remove one trailing newline for a cleaner final string.
         */
        if (result.endsWith("\n")) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    
    /*
     * InitializeClassInfoTable
     *
     * Fills the table of the 10 conjugacy classes.
     *
     * CURRENT STATUS
     * --------------
     * In this first shell version, we enter the agreed class codes and sizes,
     * together with placeholder representative permutation labels for the
     * classes that have not yet been finalized in detail.
     *
     * We can refine the representatives later without changing the overall
     * class structure.
     */
    private static void InitializeClassInfoTable() {

        classInfoTable[0] = new OhClassInfo(
                0, "K_xyz", "()", "xyz", 1
        );

        classInfoTable[1] = new OhClassInfo(
                1, "K_xYZ", "(12)(34)", "xYZ", 3
        );

        classInfoTable[2] = new OhClassInfo(
                2, "K_yzx", "(123)", "yzx", 8
        );

        classInfoTable[3] = new OhClassInfo(
                3, "K_yXz", "(1234)", "yXz", 6
        );

        classInfoTable[4] = new OhClassInfo(
                4, "K_yxz", "(12)", "yxz", 6
        );

        classInfoTable[5] = new OhClassInfo(
                5, "K_XYZ", "()i", "XYZ", 1
        );

        classInfoTable[6] = new OhClassInfo(
                6, "K_Xyz", "(12)(34)i", "Xyz", 3
        );

        classInfoTable[7] = new OhClassInfo(
                7, "K_YZX", "(123)i", "YZX", 8
        );

        classInfoTable[8] = new OhClassInfo(
                8, "K_YxZ", "(1234)i", "YxZ", 6
        );

        classInfoTable[9] = new OhClassInfo(
                9, "K_YXZ", "(12)i", "YXZ", 6
        );
    }
    /*
     * ComplexNumberIsZero
     *
     * This helper decides whether a complex number is effectively zero.
     *
     * WHY THIS EXISTS
     * ---------------
     * In scientific code, floating-point values should usually be checked
     * using a tolerance rather than exact equality.
     *
     * Here we use a small tolerance because this helper is only being used
     * to decide whether a coefficient contributes anything meaningful to
     * the multiplication loop.
     */
    private static boolean ComplexNumberIsZero(ComplexNumber value) {

        double tolerance = 1.0e-12;

        return Math.abs(value.getRealPart()) < tolerance
                && Math.abs(value.getImaginaryPart()) < tolerance;
    }

        /*
     * MultiplyAxisImageLabels
     *
     * This helper composes two 3-letter axis-image labels and returns the
     * resulting 3-letter axis-image label.
     *
     * CONVENTION USED HERE
     * --------------------
     * We use the ordinary function-composition convention:
     *
     *     product = left followed by right
     *
     * That is, if:
     *
     *     leftLabel  sends the axes somewhere,
     *     rightLabel then acts on those results,
     *
     * then:
     *
     *     MultiplyAxisImageLabels(leftLabel, rightLabel)
     *
     * returns the axis-image label for the composed transformation.
     *
     * WHY THIS METHOD MATTERS
     * -----------------------
     * This is the core computational step needed to generate the Oh group
     * multiplication table from the axis-image labels.
     *
     * EXAMPLE
     * -------
     * The identity label xyz should satisfy:
     *
     *     MultiplyAxisImageLabels("xyz", g) = g
     *     MultiplyAxisImageLabels(g, "xyz") = g
     *
     * for every valid label g.
     */
    private static String MultiplyAxisImageLabels(
            String leftLabel,
            String rightLabel) {

        /*
         * Check that both labels have the correct basic form.
         */
        if (!AxisImageLabelHasValidFormForOh(leftLabel)) {
            throw new IllegalArgumentException(
                    "Invalid left axis-image label: " + leftLabel
            );
        }

        if (!AxisImageLabelHasValidFormForOh(rightLabel)) {
            throw new IllegalArgumentException(
                    "Invalid right axis-image label: " + rightLabel
            );
        }

        /*
         * The three characters of leftLabel describe where +x, +y, +z go
         * under the left transformation.
         *
         * We then apply the right transformation to each of those three
         * signed axis images.
         */
        char productImageOfX =
                ApplyAxisImageCharacter(rightLabel, leftLabel.charAt(0));

        char productImageOfY =
                ApplyAxisImageCharacter(rightLabel, leftLabel.charAt(1));

        char productImageOfZ =
                ApplyAxisImageCharacter(rightLabel, leftLabel.charAt(2));

        /*
         * Assemble the three resulting signed axis images into the final
         * 3-letter axis-image label.
         */
        return ""
                + productImageOfX
                + productImageOfY
                + productImageOfZ;
    }

        /*
     * MultiplyAxisImageLabelsForTesting
     *
     * This public wrapper exists so that the test code in OhCalculatorMain
     * can verify the axis-image-label multiplication logic.
     *
     * WHY THIS WRAPPER EXISTS
     * -----------------------
     * The actual multiplication helper MultiplyAxisImageLabels(...) is kept
     * private because it is an internal implementation detail of OhElement.
     *
     * However, our testing philosophy is that important internal logic should
     * still be directly testable.
     *
     * So this wrapper exposes the behavior to the test code without forcing us
     * to make the internal helper itself public.
     */
    public static String MultiplyAxisImageLabelsForTesting(
            String leftLabel,
            String rightLabel) {

        return MultiplyAxisImageLabels(leftLabel, rightLabel);
    }
    
    /*
     * ApplyAxisImageCharacter
     *
     * This helper applies one 3-letter axis-image label to one signed axis.
     *
     * INPUT MEANING
     * -------------
     * Example axisImageLabel:
     *
     *     xZY
     *
     * means:
     *
     *     +x -> +x
     *     +y -> -z
     *     +z -> -y
     *
     * Example signedAxis input:
     *
     *     'y' means +y
     *     'Y' means -y
     *
     * The output is the image of that signed axis under the transformation.
     *
     * WHY THIS METHOD EXISTS
     * ----------------------
     * Composition of axis-image labels is easiest to write if we can first
     * answer the simpler question:
     *
     *     "Where does this one signed axis go?"
     */
    private static char ApplyAxisImageCharacter(
            String axisImageLabel,
            char signedAxis) {

        /*
         * Check that the axis-image label is valid.
         */
        if (!AxisImageLabelHasValidFormForOh(axisImageLabel)) {
            throw new IllegalArgumentException(
                    "Invalid axis-image label: " + axisImageLabel
            );
        }

        /*
         * Check that the input signed axis is one of x,y,z,X,Y,Z.
         */
        if (!AxisCharacterIsValid(signedAxis)) {
            throw new IllegalArgumentException(
                    "Invalid signed axis character: " + signedAxis
            );
        }

        /*
         * Separate the sign of the input axis from its underlying unsigned axis.
         *
         * Lower-case means positive.
         * Upper-case means negative.
         */
        boolean inputAxisIsNegative = Character.isUpperCase(signedAxis);
        char unsignedInputAxis = Character.toLowerCase(signedAxis);

        /*
         * Determine where the corresponding positive basis axis goes under
         * the transformation.
         *
         * Positions:
         *
         *     index 0 = image of +x
         *     index 1 = image of +y
         *     index 2 = image of +z
         */
        char imageOfPositiveAxis;

        if (unsignedInputAxis == 'x') {
            imageOfPositiveAxis = axisImageLabel.charAt(0);
        } else if (unsignedInputAxis == 'y') {
            imageOfPositiveAxis = axisImageLabel.charAt(1);
        } else if (unsignedInputAxis == 'z') {
            imageOfPositiveAxis = axisImageLabel.charAt(2);
        } else {
            /*
             * This should never happen because of the earlier validity check,
             * but we keep the fallback for safety.
             */
            throw new IllegalArgumentException(
                    "Unexpected unsigned axis: " + unsignedInputAxis
            );
        }

        /*
         * Determine whether the image of the positive axis is negative.
         */
        boolean imageOfPositiveAxisIsNegative =
                Character.isUpperCase(imageOfPositiveAxis);

        /*
         * If the input axis itself was negative, that introduces one more
         * sign flip. So the final sign is the XOR of:
         *
         * - whether the input axis was negative
         * - whether the image of the positive axis is negative
         */
        boolean finalImageIsNegative =
                inputAxisIsNegative ^ imageOfPositiveAxisIsNegative;

        /*
         * The final unsigned axis is obtained by removing case.
         */
        char finalUnsignedAxis = Character.toLowerCase(imageOfPositiveAxis);

        /*
         * Restore the correct sign using letter case.
         */
        if (finalImageIsNegative) {
            return Character.toUpperCase(finalUnsignedAxis);
        } else {
            return finalUnsignedAxis;
        }
    }

       /*
     * FindBasisIndexFromAxisImageLabel
     *
     * This helper searches the Oh basis-element metadata table and returns
     * the basis index whose axis-image label matches the supplied label.
     *
     * WHY THIS METHOD EXISTS
     * ----------------------
     * Once we multiply two axis-image labels, we obtain another axis-image label.
     * To store the product in the multiplication table, we must convert that
     * label back into the corresponding basis index.
     *
     * Since there are only 48 basis elements, a simple linear search is
     * perfectly acceptable here and keeps the code easy to read.
     *
     * RETURN VALUE
     * ------------
     * If the label is found, the corresponding basis index is returned.
     *
     * If the label is not found, the method throws an exception, because at
     * this stage of the project every valid Oh axis-image label should already
     * appear exactly once in the basis-element table.
     */
    private static int FindBasisIndexFromAxisImageLabel(String axisImageLabel) {

        /*
         * Check that the input label has the correct basic character form.
         */
        if (!AxisImageLabelHasValidFormForOh(axisImageLabel)) {
            throw new IllegalArgumentException(
                    "Invalid axis-image label in FindBasisIndexFromAxisImageLabel: "
                    + axisImageLabel
            );
        }

        /*
         * Search all 48 basis-element metadata entries.
         */
        for (int elementIndex = 0; elementIndex < NUMBER_OF_BASIS_ELEMENTS; elementIndex++) {

            OhBasisElement basisInfo = basisElementTable[elementIndex];

            /*
             * A null entry would indicate that the basis table has not been
             * initialized correctly.
             */
            if (basisInfo == null) {
                throw new IllegalStateException(
                        "Null basis-element entry encountered at index "
                        + elementIndex
                        + " while searching for axis-image label "
                        + axisImageLabel
                );
            }

            if (basisInfo.getAxisImageLabel().equals(axisImageLabel)) {
                return elementIndex;
            }
        }

        /*
         * If we reach this point, the supplied label was not found in the
         * basis-element table, which should never happen once the table is complete.
         */
        throw new IllegalStateException(
                "Axis-image label not found in basis table: " + axisImageLabel
        );
    } 
    
    /*
     * AxisCharacterIsValid
     *
     * This helper checks whether a single character is a valid signed-axis
     * symbol for this project.
     *
     * Allowed values are:
     *
     *     x, y, z, X, Y, Z
     */
    private static boolean AxisCharacterIsValid(char axisCharacter) {
        return axisCharacter == 'x'
                || axisCharacter == 'y'
                || axisCharacter == 'z'
                || axisCharacter == 'X'
                || axisCharacter == 'Y'
                || axisCharacter == 'Z';
    }

    /*
     * AxisImageLabelHasValidFormForOh
     *
     * This helper checks whether a 3-letter axis-image label has the correct
     * basic character form for Oh calculations.
     *
     * NOTE
     * ----
     * This checks only the basic character-level form:
     *
     * - not null
     * - exactly three characters
     * - each character one of x,y,z,X,Y,Z
     *
     * It does NOT check uniqueness of the three axes. That stronger check
     * can be added later if desired.
     */
    private static boolean AxisImageLabelHasValidFormForOh(String axisImageLabel) {

        if (axisImageLabel == null) {
            return false;
        }

        if (axisImageLabel.length() != 3) {
            return false;
        }

        for (int position = 0; position < 3; position++) {
            if (!AxisCharacterIsValid(axisImageLabel.charAt(position))) {
                return false;
            }
        }

        return true;
    }

    
    /*
     * InitializeBasisElementTable
     *
     * Builds the basis-element metadata table from the sovereign canonical
     * permutation-label list.
     *
     * This means the basis index ordering is now defined by the canonical
     * permutation labels, not by hand-entered patchwork metadata.
     */
    private static void InitializeBasisElementTable() {

        for (int basisIndex = 0; basisIndex < NUMBER_OF_BASIS_ELEMENTS; basisIndex++) {

            int classIndex = GetClassIndexFromBasisIndex(basisIndex);
            String permutationLabel = CANONICAL_PERMUTATION_LABELS[basisIndex];
            String axisImageLabel = CANONICAL_AXIS_IMAGE_LABELS[basisIndex];
            boolean hasInversion = permutationLabel.endsWith("i");
            int inverseIndex = FindInverseBasisIndexFromCanonicalLabels(basisIndex);

            AddBasisElement(
                    basisIndex,
                    classIndex,
                    permutationLabel,
                    axisImageLabel,
                    hasInversion,
                    inverseIndex
            );
        }
    }

    /*
     * AddBasisElement
     *
     * This helper inserts one OhBasisElement into the basis-element table.
     *
     * WHY THIS HELPER EXISTS
     * ----------------------
     * Without a helper like this, the initialization method would contain
     * many repeated lines of boilerplate constructor syntax and would be
     * harder to read.
     *
     * The helper keeps the actual table-entry list short and visually clear.
     */
    private static void AddBasisElement(
            int elementIndex,
            int classIndex,
            String permutationLabel,
            String axisImageLabel,
            boolean isInverted,
            int inverseElementIndex) {

        basisElementTable[elementIndex] =
                new OhBasisElement(
                        elementIndex,
                        classIndex,
                        permutationLabel,
                        axisImageLabel,
                        isInverted,
                        inverseElementIndex
                );
    }

    /*
     * InitializeMultiplicationTable
     *
     * Builds the multiplication table from sovereign permutation labels using
     * OhPermutationElement multiplication.
     *
     * This is now the official group-law definition.
     */
    private static void InitializeMultiplicationTable() {

        java.util.HashMap<String, Integer> labelToIndex =
                BuildCanonicalLabelToIndexTable();

        for (int leftIndex = 0; leftIndex < NUMBER_OF_BASIS_ELEMENTS; leftIndex++) {
            for (int rightIndex = 0; rightIndex < NUMBER_OF_BASIS_ELEMENTS; rightIndex++) {

                String leftLabel = CANONICAL_PERMUTATION_LABELS[leftIndex];
                String rightLabel = CANONICAL_PERMUTATION_LABELS[rightIndex];

                OhPermutationElement leftElement =
                        OhPermutationElement.fromLabel(leftLabel);
                OhPermutationElement rightElement =
                        OhPermutationElement.fromLabel(rightLabel);

                OhPermutationElement product =
                        leftElement.multiply(rightElement);

                String productLabel = product.toLabel();

                Integer productIndex = labelToIndex.get(productLabel);

                if (productIndex == null) {
                    throw new IllegalStateException(
                            "InitializeMultiplicationTable could not find product label "
                            + productLabel
                            + " from "
                            + leftLabel
                            + " * "
                            + rightLabel
                    );
                }

                multiplicationTable[leftIndex][rightIndex] = productIndex;
            }
        }
    }
}