package ohcalculatorpackage;

/*
 * OhCalculatorMain
 *
 * This is the main entry point for the Oh Calculator project.
 *
 * PROGRAMMING STYLE FOR THIS PROJECT
 * ----------------------------------
 * This project is intended to be:
 *
 * 1. A working calculator for mathematical physics.
 * 2. A teaching resource for graduate students.
 * 3. Easy to modify for related symmetry groups.
 *
 * Therefore, we deliberately use:
 *
 * - many comments,
 * - short methods,
 * - simple organization,
 * - automatic tests every time the program starts.
 *
 * We do NOT try to make the code maximally abstract or general unless
 * that is actually needed for the current project.
 *
 * The idea is to keep the code understandable and easy to debug.
 *
 * @author Carl Brannen and ChatGPT
 */
public class OhCalculatorMain {
    /*
     * GetCanonicalOhPermutationLabelsInBasisOrder
     *
     * Returns the 48 permutation labels in the intended canonical basis order.
     *
     * ORDERING PRINCIPLE
     * ------------------
     * 1. Proper classes first, then improper classes.
     * 2. Within each improper class, use the same order as the corresponding
     *    proper class, just with "i" appended.
     *
     * This gives a very natural and human-readable basis ordering.
     */
    private static String[] GetCanonicalOhPermutationLabelsInBasisOrder() {

        return new String[] {
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
    }
    /*
     * main
     *
     * This is the standard Java entry point.
     *
     * Every Java application begins with a method having exactly this form:
     *
     *     public static void main(String[] args)
     *
     * "public" means Java can call it from outside the class.
     * "static" means it belongs to the class itself and does not require
     * an object to be created first.
     * "void" means it does not return a value.
     * "String[] args" holds command-line arguments, which we are not using yet.
     */
    public static void main(String[] args) {

        /*
         * Print a short startup message.
         *
         * We keep startup output short because in this project we want
         * test failures to be visually obvious.
         */
        PrintProgramBanner();

        /*
         * Run all automatic tests.
         *
         * This is one of the main programming principles of the project:
         * every normal run also performs the regression tests.
         *
         * That way, if we later change the code and accidentally break
         * something old, we will notice immediately.
         */
        RunAllTests();

        /*
         * Print a short closing line.
         *
         * Again, this is intentionally brief.
         */
        System.out.println("*** Program completed");
    }

    /*
     * PrintProgramBanner
     *
     * This method prints a small banner so that the user can tell
     * the program has started correctly.
     *
     * We isolate it in its own method because:
     *
     * 1. It keeps main() short.
     * 2. It makes later changes easier.
     * 3. It helps keep each method focused on one purpose.
     */
    private static void PrintProgramBanner() {
        System.out.println("======================================");
        System.out.println("            Oh Calculator             ");
        System.out.println("======================================");
    }

    /*
     * RunAllTests
     *
     * This method calls every test routine for the project.
     *
     * As the code grows, we will add more test methods here, for example:
     *
     * - TestComplexNumbers()
     * - TestPauliValues()
     * - TestMatrix2x2()
     * - TestOhGroupMultiplication()
     *
     * We keep all test launching in one place so that the startup logic
     * remains easy to read.
     */
    private static void RunAllTests() {
        TestComplexNumbers();
        TestPauliValues();
        TestMatrix2x2();
        TestPauliToMatrix2x2();
        TestPauliMatrixMultiplicationAgreement();
        TestOhElementShell();
        TestOhBasisElementTable();
        TestOhPermutationLabels();
        TestOhClassInfoTable();
        TestOhPermutationElement();        
        TestAxisImageLabelMultiplication();
        TestOhMultiplicationTable();
        TestOhAllBasisProducts();        
        TestOhAssociativitySpots();
        TestOhPauliElementShell();
        TestOhPauliElementPrinter();
        TestOhFourierSingletIdempotents();
        TestOhFourierElements4to11Basics();
        TestOhFourierElements12to20Basics();
        TestOhFourierElements21to29Basics();
        TestOhFourierElements30to38Basics();
        TestOhFourierElements39to47Basics();
        TestOhClassSumsStayClassFunctions();
        TestOhPermutationLabelMultiplication();        
        //TestOhFourierBlockIdentities();
        TestOhInversionPermutationLabelConsistency();
        TestMatrix3x3();        
        //TestOhFourierTripletIdentityDiagnostics();
        //PrintOhBasisElementLabels();
        //PrintCanonicalOhPermutationLabels();
        TestOhClassSumsStayClassFunctions();
        TestOhCharacterTableIrreps();
        TestOhPauliAndGellMannBlockClosure();
        TestSUN();
        //DebugSU3Block1();
        System.out.println("*** Program startup completed");

        /*
     * PrintCanonicalOhPermutationLabels
     *
     * Prints the canonical 48-label list, 8 per line.
     */
    }
        private static void PrintCanonicalOhPermutationLabels() {

        String[] labels = GetCanonicalOhPermutationLabelsInBasisOrder();

        System.out.println("*** Canonical Oh permutation labels in basis order");

        for (int index = 0; index < labels.length; index++) {
            System.out.printf("%2d:%-12s", index, labels[index]);

            if ((index + 1) % 8 == 0) {
                System.out.println();
            } else {
                System.out.print("  ");
            }
        }

        if (labels.length % 8 != 0) {
            System.out.println();
        }

        System.out.println("*** End canonical Oh permutation labels");
    }
        /*
     * TestOhFourierElements12to20Basics
     *
     * This method tests the translated Fourier-basis elements 12 through 20.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * These elements are the first larger nontrivial block after the earlier
     * singlets and doublet-like block.
     *
     * Since we translated them from sparse numerical output, the safest next
     * step is to verify:
     *
     * - which basis slots are nonzero,
     * - whether they are real or purely imaginary when expected,
     * - and whether certain obvious parity-partner support relationships hold.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * Element 12:
     *     - purely real
     *     - nonzero on 32 slots
     *
     * Elements 13, 16, 18:
     *     - purely real
     *     - nonzero on 20 slots each
     *
     * Elements 14, 17, 19:
     *     - purely imaginary
     *     - nonzero on 20 slots each
     *
     * Element 15:
     *     - purely real
     *     - nonzero on 20 slots
     *
     * Element 20:
     *     - purely real
     *     - nonzero on 32 slots
     *
     * In addition, we check that:
     *
     * - elements 13 and 22 are not yet assumed related here
     * - elements 13/14, 16/17, 18/19 have matching support
     * - element 12 and element 21 are not tested here because 21 is not yet
     *   translated in this block's logical partner structure
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhFourierElements12to20Basics() {

        boolean allTestsOkay = true;

        OhElement e12 = OhFourierBasis.GetElement(12);
        OhElement e13 = OhFourierBasis.GetElement(13);
        OhElement e14 = OhFourierBasis.GetElement(14);
        OhElement e15 = OhFourierBasis.GetElement(15);
        OhElement e16 = OhFourierBasis.GetElement(16);
        OhElement e17 = OhFourierBasis.GetElement(17);
        OhElement e18 = OhFourierBasis.GetElement(18);
        OhElement e19 = OhFourierBasis.GetElement(19);
        OhElement e20 = OhFourierBasis.GetElement(20);

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Element 12 should be purely real with 32 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e20, 30)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements12to20Basics: element 20 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Element 13 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e13, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements12to20Basics: element 13 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Element 14 should be purely imaginary with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyImaginaryWithNonzeroCount(e14, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements12to20Basics: element 14 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * Element 15 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e15, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements12to20Basics: element 15 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Element 16 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e16, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements12to20Basics: element 16 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 6:
         * Element 17 should be purely imaginary with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyImaginaryWithNonzeroCount(e17, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements12to20Basics: element 17 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 7:
         * Element 18 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e18, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements12to20Basics: element 18 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 8:
         * Element 19 should be purely imaginary with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyImaginaryWithNonzeroCount(e19, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements12to20Basics: element 19 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 9:
         * Element 20 should be purely real with 32 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e20, 30)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements12to20Basics: element 20 basic structure failed"
            );
            allTestsOkay = false;
        }



        /*
         * ------------------------------------------------------------
         * Test 13:
         * Spot support anchors for element 12
         * ------------------------------------------------------------
         *
         * Element 12 should certainly be nonzero at:
         *
         *     0, 24
         *
         * and zero at:
         *
         *     4, 28
         *
         * according to the uploaded pattern. 
         */
        if (ComplexNumberIsCloseToZeroForTesting(e12.getCoefficient(0))
                || ComplexNumberIsCloseToZeroForTesting(e12.getCoefficient(24))
                || !ComplexNumberIsCloseToZeroForTesting(e12.getCoefficient(4))
                || !ComplexNumberIsCloseToZeroForTesting(e12.getCoefficient(28))) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements12to20Basics: element 12 spot support failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 14:
         * Spot support anchors for element 20
         * ------------------------------------------------------------
         *
         * Element 20 should be nonzero at:
         *
         *     1, 3, 12, 20, 25, 27, 36, 44
         *
         * and zero at:
         *
         *     0, 4, 24, 28
         *
         * based on the translated pattern. 
         */
        if (ComplexNumberIsCloseToZeroForTesting(e20.getCoefficient(1))
                || ComplexNumberIsCloseToZeroForTesting(e20.getCoefficient(3))
                || ComplexNumberIsCloseToZeroForTesting(e20.getCoefficient(12))
                || ComplexNumberIsCloseToZeroForTesting(e20.getCoefficient(20))
                || ComplexNumberIsCloseToZeroForTesting(e20.getCoefficient(25))
                || ComplexNumberIsCloseToZeroForTesting(e20.getCoefficient(27))
                || ComplexNumberIsCloseToZeroForTesting(e20.getCoefficient(36))
                || ComplexNumberIsCloseToZeroForTesting(e20.getCoefficient(44))
                || !ComplexNumberIsCloseToZeroForTesting(e20.getCoefficient(0))
                || !ComplexNumberIsCloseToZeroForTesting(e20.getCoefficient(4))
                || !ComplexNumberIsCloseToZeroForTesting(e20.getCoefficient(24))
                || !ComplexNumberIsCloseToZeroForTesting(e20.getCoefficient(28))) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements12to20Basics: element 20 spot support failed"
            );
            allTestsOkay = false;
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhFourierElements12to20Basics() Okay");
        }
    }

    /*
     * OhElementIsPurelyRealWithNonzeroCount
     *
     * Checks that:
     *
     * - exactly expectedNonzeroCount coefficients are nonzero
     * - every nonzero coefficient is purely real
     */
    private static boolean OhElementIsPurelyRealWithNonzeroCount(
            OhElement value,
            int expectedNonzeroCount) {

        int nonzeroCount = 0;

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {
            ComplexNumber c = value.getCoefficient(index);

            if (!ComplexNumberIsCloseToZeroForTesting(c)) {
                nonzeroCount++;

                if (Math.abs(c.getImaginaryPart()) > 1.0e-12) {
                    return false;
                }
            }
        }

        return nonzeroCount == expectedNonzeroCount;
    }

    /*
     * OhElementIsPurelyImaginaryWithNonzeroCount
     *
     * Checks that:
     *
     * - exactly expectedNonzeroCount coefficients are nonzero
     * - every nonzero coefficient is purely imaginary
     */
    private static boolean OhElementIsPurelyImaginaryWithNonzeroCount(
            OhElement value,
            int expectedNonzeroCount) {

        int nonzeroCount = 0;

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {
            ComplexNumber c = value.getCoefficient(index);

            if (!ComplexNumberIsCloseToZeroForTesting(c)) {
                nonzeroCount++;

                if (Math.abs(c.getRealPart()) > 1.0e-12) {
                    return false;
                }
            }
        }

        return nonzeroCount == expectedNonzeroCount;
    }
    
    /*
     * TestOhClassSumsStayClassFunctions
     *
     * This method tests whether products of class sums remain class functions.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * In the group algebra, sums over conjugacy classes lie in the center.
     * Therefore, the product of any two class sums must again be a class
     * function.
     *
     * That means the coefficients of the product must be constant on each
     * conjugacy class.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * 1. Build the 10 class sums.
     * 2. Multiply every ordered pair of class sums.
     * 3. Verify that every product is constant on each conjugacy class.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhClassSumsStayClassFunctions() {

        boolean allTestsOkay = true;

        /*
         * Build the 10 class sums.
         */
        OhElement[] classSums = new OhElement[10];

        for (int classIndex = 0; classIndex < 10; classIndex++) {
            classSums[classIndex] = BuildOhClassSum(classIndex);
        }

        /*
         * Multiply every ordered pair of class sums.
         */
        for (int leftClass = 0; leftClass < 10; leftClass++) {
            for (int rightClass = 0; rightClass < 10; rightClass++) {

                OhElement product =
                        classSums[leftClass].multiply(classSums[rightClass]);

                if (!OhElementIsConstantOnClasses(product)) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhClassSumsStayClassFunctions: class-sum product failed for classes "
                            + leftClass + " and " + rightClass
                    );

                    System.out.println("Left class sum:");
                    System.out.println(classSums[leftClass].toClassGroupedString());
                    System.out.println();

                    System.out.println("Right class sum:");
                    System.out.println(classSums[rightClass].toClassGroupedString());
                    System.out.println();

                    System.out.println("Product:");
                    System.out.println(product.toClassGroupedString());
                    System.out.println();

                    allTestsOkay = false;
                }
            }
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhClassSumsStayClassFunctions() Okay");
        }
    }

    /*
     * BuildOhClassSum
     *
     * Returns the OhElement that is 1 on every basis element in the specified
     * conjugacy class and 0 elsewhere.
     */
    private static OhElement BuildOhClassSum(int classIndex) {

        ComplexNumber[] coefficients =
                new ComplexNumber[OhElement.getNumberOfBasisElements()];

        for (int elementIndex = 0; elementIndex < OhElement.getNumberOfBasisElements(); elementIndex++) {

            OhBasisElement basisInfo = OhElement.getBasisElementInfo(elementIndex);

            if (basisInfo.getClassIndex() == classIndex) {
                coefficients[elementIndex] = new ComplexNumber(1.0, 0.0);
            } else {
                coefficients[elementIndex] = new ComplexNumber(0.0, 0.0);
            }
        }

        return new OhElement(coefficients);
    }


    /*
     * TestPauliToMatrix2x2
     *
     * This method tests the conversion from a PauliValue to a 2x2 complex
     * matrix.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * We now have:
     *
     * 1. An abstract Pauli algebra class.
     * 2. A concrete 2x2 matrix class.
     *
     * The method PauliValue.toMatrix2x2() is supposed to connect them.
     *
     * So this test checks that the standard basis elements:
     *
     *     1, sigma_x, sigma_y, sigma_z
     *
     * convert into the correct 2x2 matrices, and also checks one general
     * example.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestPauliToMatrix2x2() {

        /*
         * This variable remembers whether all tests have passed so far.
         */
        boolean allTestsOkay = true;

        /*
         * ------------------------------------------------------------
         * Test 1:
         * identity element
         * ------------------------------------------------------------
         *
         * The Pauli algebra identity should convert to:
         *
         *     [ 1  0 ]
         *     [ 0  1 ]
         */
        Matrix2x2 identityExpected = Matrix2x2.identity();
        Matrix2x2 identityComputed = PauliValue.identity().toMatrix2x2();

        if (!Matrix2x2AreClose(identityComputed, identityExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliToMatrix2x2: identity failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * sigma_x
         * ------------------------------------------------------------
         *
         * sigma_x should convert to:
         *
         *     [ 0  1 ]
         *     [ 1  0 ]
         */
        Matrix2x2 sigmaXExpected =
                new Matrix2x2(
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(1.0, 0.0),
                        new ComplexNumber(1.0, 0.0),
                        new ComplexNumber(0.0, 0.0)
                );

        Matrix2x2 sigmaXComputed = PauliValue.sigmaX().toMatrix2x2();

        if (!Matrix2x2AreClose(sigmaXComputed, sigmaXExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliToMatrix2x2: sigma_x failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * sigma_y
         * ------------------------------------------------------------
         *
         * sigma_y should convert to:
         *
         *     [ 0  -i ]
         *     [ i   0 ]
         */
        Matrix2x2 sigmaYExpected =
                new Matrix2x2(
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, -1.0),
                        new ComplexNumber(0.0, 1.0),
                        new ComplexNumber(0.0, 0.0)
                );

        Matrix2x2 sigmaYComputed = PauliValue.sigmaY().toMatrix2x2();

        if (!Matrix2x2AreClose(sigmaYComputed, sigmaYExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliToMatrix2x2: sigma_y failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * sigma_z
         * ------------------------------------------------------------
         *
         * sigma_z should convert to:
         *
         *     [ 1   0 ]
         *     [ 0  -1 ]
         */
        Matrix2x2 sigmaZExpected =
                new Matrix2x2(
                        new ComplexNumber(1.0, 0.0),
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(-1.0, 0.0)
                );

        Matrix2x2 sigmaZComputed = PauliValue.sigmaZ().toMatrix2x2();

        if (!Matrix2x2AreClose(sigmaZComputed, sigmaZExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliToMatrix2x2: sigma_z failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * one general Pauli value
         * ------------------------------------------------------------
         *
         * Let:
         *
         *     P = a*1 + b*sigma_x + c*sigma_y + d*sigma_z
         *
         * with:
         *
         *     a = 2 + 3i
         *     b = 5
         *     c = 7i
         *     d = -11
         *
         * Then the matrix should be:
         *
         *     [ a + d      b - i c ]
         *     [ b + i c    a - d   ]
         */
        ComplexNumber a = new ComplexNumber(2.0, 3.0);
        ComplexNumber b = new ComplexNumber(5.0, 0.0);
        ComplexNumber c = new ComplexNumber(0.0, 7.0);
        ComplexNumber d = new ComplexNumber(-11.0, 0.0);

        PauliValue generalPauliValue = new PauliValue(a, b, c, d);

        ComplexNumber i = new ComplexNumber(0.0, 1.0);
        ComplexNumber minusI = new ComplexNumber(0.0, -1.0);

        Matrix2x2 generalExpected =
                new Matrix2x2(
                        a.add(d),
                        b.add(c.multiply(minusI)),
                        b.add(c.multiply(i)),
                        a.subtract(d)
                );

        Matrix2x2 generalComputed = generalPauliValue.toMatrix2x2();

        if (!Matrix2x2AreClose(generalComputed, generalExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliToMatrix2x2: general conversion failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestPauliToMatrix2x2() Okay");
        }
    }
    
        /*
     * TestPauliMatrixMultiplicationAgreement
     *
     * This method checks that Pauli algebra multiplication agrees with
     * ordinary 2x2 complex matrix multiplication after conversion by
     * PauliValue.toMatrix2x2().
     *
     * MATHEMATICAL PURPOSE
     * --------------------
     * If the conversion from PauliValue to Matrix2x2 is correct, then
     * it should preserve multiplication.
     *
     * In symbols, if P and Q are PauliValue objects, then we should have:
     *
     *     (P * Q).toMatrix2x2()  =  P.toMatrix2x2() * Q.toMatrix2x2()
     *
     * This is stronger than merely checking the basis elements separately.
     * It verifies that the full algebra structure is being represented
     * correctly.
     *
     * PROGRAMMING PURPOSE
     * -------------------
     * This is an important bridge test. It checks that:
     *
     * 1. PauliValue.multiply(...) is correct.
     * 2. PauliValue.toMatrix2x2() is correct.
     * 3. Matrix2x2.multiply(...) is correct.
     *
     * If this test passes on a variety of examples, then the abstract Pauli
     * algebra and the concrete 2x2 matrix representation are in agreement.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestPauliMatrixMultiplicationAgreement() {

        /*
         * This variable records whether every sub-test has passed so far.
         */
        boolean allTestsOkay = true;

        /*
         * ------------------------------------------------------------
         * Test 1:
         * sigma_x * sigma_y
         * ------------------------------------------------------------
         *
         * This is a simple noncommuting basis example.
         *
         * We compare:
         *
         *     (sigma_x * sigma_y) converted to matrix
         *
         * with:
         *
         *     matrix(sigma_x) * matrix(sigma_y)
         */
        PauliValue sigmaX = PauliValue.sigmaX();
        PauliValue sigmaY = PauliValue.sigmaY();

        Matrix2x2 agreement1Left =
                sigmaX.multiply(sigmaY).toMatrix2x2();

        Matrix2x2 agreement1Right =
                sigmaX.toMatrix2x2().multiply(sigmaY.toMatrix2x2());

        if (!Matrix2x2AreClose(agreement1Left, agreement1Right)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliMatrixMultiplicationAgreement: sigma_x sigma_y failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * sigma_y * sigma_x
         * ------------------------------------------------------------
         *
         * This is the reversed order, so it should differ from Test 1.
         * This checks that the noncommutative structure is preserved by
         * the matrix representation.
         */
        Matrix2x2 agreement2Left =
                sigmaY.multiply(sigmaX).toMatrix2x2();

        Matrix2x2 agreement2Right =
                sigmaY.toMatrix2x2().multiply(sigmaX.toMatrix2x2());

        if (!Matrix2x2AreClose(agreement2Left, agreement2Right)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliMatrixMultiplicationAgreement: sigma_y sigma_x failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * one mixed general example
         * ------------------------------------------------------------
         *
         * Here we use two PauliValue objects with nontrivial complex
         * coefficients.
         *
         * The goal is to test a genuinely general product, not just
         * the basis elements.
         */
        PauliValue generalLeft =
                new PauliValue(
                        new ComplexNumber(2.0, 1.0),
                        new ComplexNumber(3.0, -2.0),
                        new ComplexNumber(0.0, 4.0),
                        new ComplexNumber(-1.0, 0.5)
                );

        PauliValue generalRight =
                new PauliValue(
                        new ComplexNumber(-5.0, 2.0),
                        new ComplexNumber(1.5, 0.0),
                        new ComplexNumber(2.0, -3.0),
                        new ComplexNumber(0.0, -2.5)
                );

        Matrix2x2 agreement3Left =
                generalLeft.multiply(generalRight).toMatrix2x2();

        Matrix2x2 agreement3Right =
                generalLeft.toMatrix2x2().multiply(generalRight.toMatrix2x2());

        if (!Matrix2x2AreClose(agreement3Left, agreement3Right)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliMatrixMultiplicationAgreement: general example 1 failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * another mixed general example
         * ------------------------------------------------------------
         *
         * It is good to test more than one general example so that we are
         * less likely to be fooled by some accidental cancellation.
         */
        PauliValue generalLeft2 =
                new PauliValue(
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(7.0, -1.0),
                        new ComplexNumber(-2.0, 3.0),
                        new ComplexNumber(4.0, 4.0)
                );

        PauliValue generalRight2 =
                new PauliValue(
                        new ComplexNumber(6.0, -2.0),
                        new ComplexNumber(0.0, 5.0),
                        new ComplexNumber(-3.5, 1.5),
                        new ComplexNumber(2.25, 0.0)
                );

        Matrix2x2 agreement4Left =
                generalLeft2.multiply(generalRight2).toMatrix2x2();

        Matrix2x2 agreement4Right =
                generalLeft2.toMatrix2x2().multiply(generalRight2.toMatrix2x2());

        if (!Matrix2x2AreClose(agreement4Left, agreement4Right)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliMatrixMultiplicationAgreement: general example 2 failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * identity agreement
         * ------------------------------------------------------------
         *
         * Multiplication by the identity should agree in both pictures.
         */
        PauliValue identity = PauliValue.identity();

        Matrix2x2 agreement5Left =
                generalLeft.multiply(identity).toMatrix2x2();

        Matrix2x2 agreement5Right =
                generalLeft.toMatrix2x2().multiply(identity.toMatrix2x2());

        if (!Matrix2x2AreClose(agreement5Left, agreement5Right)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliMatrixMultiplicationAgreement: identity agreement failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestPauliMatrixMultiplicationAgreement() Okay");
        }
    }

    /*
     * TestComplexNumbers
     *
     * This method checks that the ComplexNumber class performs the basic
     * operations correctly.
     *
     * DESIGN OF TEST OUTPUT
     * ---------------------
     * On success:
     *     print one short line
     *
     * On failure:
     *     print a loud, very visible, searchable error line
     *
     * This is done so that failures are easy to spot and easy to search for
     * in the source code.
     */
    private static void TestComplexNumbers() {

        /*
         * We use a boolean variable to remember whether any test has failed.
         *
         * The variable starts as true in the sense "all tests okay so far."
         * If any individual test fails, we change it to false.
         */
        boolean allTestsOkay = true;

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Check exp(i * 2*pi/3)
         * ------------------------------------------------------------
         *
         * Mathematically:
         *
         *     exp(i*theta) = cos(theta) + i sin(theta)
         *
         * Therefore:
         *
         *     exp(2 i pi / 3) = -1/2 + i sqrt(3)/2
         *
         * Since Java uses floating-point arithmetic, we compare using
         * a tolerance rather than exact equality.
         */
        double angle = 2.0 * Math.PI / 3.0;

        ComplexNumber computedRoot = ComplexNumber.expImaginary(angle);

        ComplexNumber expectedRoot =
                new ComplexNumber(-0.5, Math.sqrt(3.0) / 2.0);

        if (!ComplexNumbersAreClose(computedRoot, expectedRoot)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestComplexNumbers: "
                    + "exp(2 i pi / 3) failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Check that z^3 = 1 for z = exp(2 i pi / 3)
         * ------------------------------------------------------------
         *
         * This checks multiplication as well as the earlier exponential result.
         */
        ComplexNumber zCubed =
                computedRoot.multiply(computedRoot).multiply(computedRoot);

        ComplexNumber expectedOne = new ComplexNumber(1.0, 0.0);

        if (!ComplexNumbersAreClose(zCubed, expectedOne)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestComplexNumbers: "
                    + "cube root identity failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Check complex conjugation
         * ------------------------------------------------------------
         *
         * If z = a + ib, then conj(z) = a - ib.
         */
        ComplexNumber zConjugate = computedRoot.conjugate();

        ComplexNumber expectedConjugate =
                new ComplexNumber(-0.5, -Math.sqrt(3.0) / 2.0);

        if (!ComplexNumbersAreClose(zConjugate, expectedConjugate)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestComplexNumbers: "
                    + "complex conjugate failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * Check z * conj(z) = |z|^2
         * ------------------------------------------------------------
         *
         * For a unit complex number on the circle, this should equal 1.
         */
        ComplexNumber zTimesConjugate = computedRoot.multiply(zConjugate);

        if (!ComplexNumbersAreClose(zTimesConjugate, expectedOne)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestComplexNumbers: "
                    + "norm identity failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Check addition
         * ------------------------------------------------------------
         *
         * We use a very simple example so that it is easy for a human to inspect.
         */
        ComplexNumber addLeft = new ComplexNumber(2.0, 3.0);
        ComplexNumber addRight = new ComplexNumber(4.0, -5.0);
        ComplexNumber addExpected = new ComplexNumber(6.0, -2.0);
        ComplexNumber addComputed = addLeft.add(addRight);

        if (!ComplexNumbersAreClose(addComputed, addExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestComplexNumbers: "
                    + "addition failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 6:
         * Check subtraction
         * ------------------------------------------------------------
         */
        ComplexNumber subtractExpected = new ComplexNumber(-2.0, 8.0);
        ComplexNumber subtractComputed = addLeft.subtract(addRight);

        if (!ComplexNumbersAreClose(subtractComputed, subtractExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestComplexNumbers: "
                    + "subtraction failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 7:
         * Check multiplication using a hand-computable example
         * ------------------------------------------------------------
         *
         * (1 + 2i)(3 + 4i) = 3 + 4i + 6i + 8i^2 = -5 + 10i
         */
        ComplexNumber multiplyLeft = new ComplexNumber(1.0, 2.0);
        ComplexNumber multiplyRight = new ComplexNumber(3.0, 4.0);
        ComplexNumber multiplyExpected = new ComplexNumber(-5.0, 10.0);
        ComplexNumber multiplyComputed = multiplyLeft.multiply(multiplyRight);

        if (!ComplexNumbersAreClose(multiplyComputed, multiplyExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestComplexNumbers: "
                    + "multiplication failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 8:
         * Check division
         * ------------------------------------------------------------
         *
         * We verify division by checking:
         *
         *     (z / w) * w = z
         *
         * This is often a robust way to test division numerically.
         */
        ComplexNumber divideLeft = new ComplexNumber(5.0, -7.0);
        ComplexNumber divideRight = new ComplexNumber(2.0, 1.0);

        ComplexNumber quotient = divideLeft.divide(divideRight);
        ComplexNumber recoveredLeft = quotient.multiply(divideRight);

        if (!ComplexNumbersAreClose(recoveredLeft, divideLeft)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestComplexNumbers: "
                    + "division failed"
            );
            allTestsOkay = false;
        }
        // Tests for zero and one
        if (!ComplexNumbersAreClose(ComplexNumber.zero(), new ComplexNumber(0.0, 0.0))) {
            System.out.println("%%%%%%%%%%%%%% Error: TestComplexNumbers: zero() failed");
            allTestsOkay = false;
        }

        if (!ComplexNumbersAreClose(ComplexNumber.one(), new ComplexNumber(1.0, 0.0))) {
            System.out.println("%%%%%%%%%%%%%% Error: TestComplexNumbers: one() failed");
            allTestsOkay = false;
        }
        /*
         * ------------------------------------------------------------
         * Final result of the complex number tests
         * ------------------------------------------------------------
         *
         * Only one short success line is printed if everything passed.
         */
        if (allTestsOkay) {
            System.out.println("*** TestComplexNumbers() Okay");
        }
    }

    /*
     * ComplexNumbersAreClose
     *
     * This helper method compares two complex numbers using a numerical tolerance.
     *
     * WHY A TOLERANCE IS NEEDED
     * -------------------------
     * Floating-point numbers in Java are usually represented using "double".
     * These are approximate binary representations of real numbers.
     *
     * Therefore, values that are mathematically equal may differ by tiny amounts
     * after computation. So for scientific programming, exact equality is often
     * the wrong test.
     *
     * Instead, we ask whether both the real parts and imaginary parts are close.
     */
    private static boolean ComplexNumbersAreClose(
            ComplexNumber firstNumber,
            ComplexNumber secondNumber) {

        boolean realPartsAreClose =
                DoublesAreClose(
                        firstNumber.getRealPart(),
                        secondNumber.getRealPart()
                );

        boolean imaginaryPartsAreClose =
                DoublesAreClose(
                        firstNumber.getImaginaryPart(),
                        secondNumber.getImaginaryPart()
                );

        return realPartsAreClose && imaginaryPartsAreClose;
    }

    /*
     * DoublesAreClose
     *
     * This helper compares two double values using a small tolerance.
     *
     * The tolerance value may later be adjusted if needed.
     */
    private static boolean DoublesAreClose(double firstValue, double secondValue) {

        /*
         * We choose a small tolerance appropriate for this early stage.
         * This is not sacred; it can be adjusted later if the mathematics
         * of a particular calculation requires it.
         */
        double tolerance = 1.0e-10;

        return Math.abs(firstValue - secondValue) < tolerance;
    }
    /*
     * TestOhClassInfoTable
     *
     * This method tests the OhClassInfo metadata table stored in OhElement.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * We have two different metadata layers for Oh:
     *
     *     OhClassInfo     = information about conjugacy classes
     *     OhBasisElement  = information about individual group elements
     *
     * The previous basis-element test checked the 48-element table.
     * This test checks the 10-class table itself.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * 1. All 10 class entries exist.
     * 2. Each class stores the correct integer class index.
     * 3. Each class stores the agreed class code.
     * 4. Each class stores the agreed representative permutation label.
     * 5. Each class stores the agreed representative axis-image label.
     * 6. Each class stores the agreed class size.
     * 7. The class codes are unique.
     * 8. The representative axis-image labels are unique.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhClassInfoTable() {

        /*
         * This variable records whether every sub-test has passed.
         */
        boolean allTestsOkay = true;

        /*
         * These arrays hold the agreed data for the 10 classes.
         *
         * We write them explicitly so that the test itself documents the
         * intended structure of the class table.
         */
        String[] expectedClassCodes = {
            "K_xyz",
            "K_xYZ",
            "K_yzx",
            "K_yXz",
            "K_yxz",
            "K_XYZ",
            "K_Xyz",
            "K_YZX",
            "K_YxZ",
            "K_YXZ"
        };

        String[] expectedRepresentativePermutationLabels = {
            "()",
            "(12)(34)",
            "(123)",
            "(1234)",
            "(12)",
            "()i",
            "(12)(34)i",
            "(123)i",
            "(1234)i",
            "(12)i"
        };

        String[] expectedRepresentativeAxisImageLabels = {
            "xyz",
            "xYZ",
            "yzx",
            "yXz",
            "yxz",
            "XYZ",
            "Xyz",
            "YZX",
            "YxZ",
            "YXZ"
        };

        int[] expectedClassSizes = {
            1, 3, 8, 6, 6, 1, 3, 8, 6, 6
        };

        /*
         * We will store class codes and representative axis-image labels
         * so that we can later check uniqueness.
         */
        String[] actualClassCodes = new String[10];
        String[] actualRepresentativeAxisImageLabels = new String[10];

        /*
         * ------------------------------------------------------------
         * Check each of the 10 class entries
         * ------------------------------------------------------------
         */
        for (int classIndex = 0; classIndex < 10; classIndex++) {

            OhClassInfo classInfo = OhElement.getClassInfo(classIndex);

            /*
             * Every class entry must exist.
             */
            if (classInfo == null) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhClassInfoTable: null class entry at class index "
                        + classIndex
                );
                allTestsOkay = false;
                continue;
            }

            /*
             * Check that the stored class index agrees with the slot index.
             */
            if (classInfo.getClassIndex() != classIndex) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhClassInfoTable: stored class index mismatch at class index "
                        + classIndex
                );
                allTestsOkay = false;
            }

            /*
             * Check the class code.
             */
            if (!classInfo.getClassCode().equals(expectedClassCodes[classIndex])) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhClassInfoTable: class code failed at class index "
                        + classIndex
                );
                allTestsOkay = false;
            }

            /*
             * Check the representative permutation label.
             */
            if (!classInfo.getRepresentativePermutationLabel().equals(
                    expectedRepresentativePermutationLabels[classIndex])) {

                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhClassInfoTable: representative permutation label failed at class index "
                        + classIndex
                );
                allTestsOkay = false;
            }

            /*
             * Check the representative axis-image label.
             */
            if (!classInfo.getRepresentativeAxisImageLabel().equals(
                    expectedRepresentativeAxisImageLabels[classIndex])) {

                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhClassInfoTable: representative axis-image label failed at class index "
                        + classIndex
                );
                allTestsOkay = false;
            }

            /*
             * Check the class size.
             */
            if (classInfo.getClassSize() != expectedClassSizes[classIndex]) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhClassInfoTable: class size failed at class index "
                        + classIndex
                );
                allTestsOkay = false;
            }

            /*
             * Store values for later uniqueness checks.
             */
            actualClassCodes[classIndex] = classInfo.getClassCode();
            actualRepresentativeAxisImageLabels[classIndex] =
                    classInfo.getRepresentativeAxisImageLabel();
        }

        /*
         * ------------------------------------------------------------
         * Check uniqueness of class codes
         * ------------------------------------------------------------
         */
        for (int firstIndex = 0; firstIndex < 10; firstIndex++) {
            for (int secondIndex = firstIndex + 1; secondIndex < 10; secondIndex++) {

                if (actualClassCodes[firstIndex] != null
                        && actualClassCodes[firstIndex].equals(actualClassCodes[secondIndex])) {

                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhClassInfoTable: duplicate class code at class indices "
                            + firstIndex + " and " + secondIndex
                    );
                    allTestsOkay = false;
                }
            }
        }

        /*
         * ------------------------------------------------------------
         * Check uniqueness of representative axis-image labels
         * ------------------------------------------------------------
         */
        for (int firstIndex = 0; firstIndex < 10; firstIndex++) {
            for (int secondIndex = firstIndex + 1; secondIndex < 10; secondIndex++) {

                if (actualRepresentativeAxisImageLabels[firstIndex] != null
                        && actualRepresentativeAxisImageLabels[firstIndex].equals(
                                actualRepresentativeAxisImageLabels[secondIndex])) {

                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhClassInfoTable: duplicate representative axis-image label at class indices "
                            + firstIndex + " and " + secondIndex
                    );
                    allTestsOkay = false;
                }
            }
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestOhClassInfoTable() Okay");
        }
    }
    
    /*
     * TestOhMultiplicationTable
     *
     * This method tests the fully generated Oh multiplication table.
     *
     * WHY THIS TEST MATTERS
     * ---------------------
     * The multiplication table is now generated from the axis-image labels.
     * That is a very good design, but it is still important to test the
     * resulting table carefully.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * 1. The identity acts correctly on the left for all 48 basis elements.
     * 2. The identity acts correctly on the right for all 48 basis elements.
     * 3. Every basis element times its inverse gives the identity.
     * 4. Every inverse times its basis element gives the identity.
     * 5. A small collection of specific spot products gives the expected result.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhMultiplicationTable() {

        /*
         * This variable records whether every sub-test has passed.
         */
        boolean allTestsOkay = true;

        /*
         * The identity basis element is basis index 0 in the present design.
         */
        OhElement identity = OhElement.identity();

        /*
         * ------------------------------------------------------------
         * Test 1 and Test 2:
         * Identity on the left and on the right for all 48 basis elements
         * ------------------------------------------------------------
         */
        for (int elementIndex = 0;
                elementIndex < OhElement.getNumberOfBasisElements();
                elementIndex++) {

            OhElement currentBasisElement = OhElement.basisElement(elementIndex);

            OhElement leftIdentityProduct = identity.multiply(currentBasisElement);
            OhElement rightIdentityProduct = currentBasisElement.multiply(identity);

            if (!OhElementsAreClose(leftIdentityProduct, currentBasisElement)) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhMultiplicationTable: left identity failed at element index "
                        + elementIndex
                );
                allTestsOkay = false;
            }

            if (!OhElementsAreClose(rightIdentityProduct, currentBasisElement)) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhMultiplicationTable: right identity failed at element index "
                        + elementIndex
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 3 and Test 4:
         * Inverse products for all 48 basis elements
         * ------------------------------------------------------------
         *
         * For every basis element g, we should have:
         *
         *     g * g^{-1} = identity
         *     g^{-1} * g = identity
         */
        for (int elementIndex = 0;
                elementIndex < OhElement.getNumberOfBasisElements();
                elementIndex++) {

            OhBasisElement basisInfo = OhElement.getBasisElementInfo(elementIndex);

            if (basisInfo == null) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhMultiplicationTable: null basis metadata at element index "
                        + elementIndex
                );
                allTestsOkay = false;
                continue;
            }

            int inverseIndex = basisInfo.getInverseElementIndex();

            OhElement currentBasisElement = OhElement.basisElement(elementIndex);
            OhElement inverseBasisElement = OhElement.basisElement(inverseIndex);

            OhElement productWithInverse =
                    currentBasisElement.multiply(inverseBasisElement);

            OhElement inverseTimesProduct =
                    inverseBasisElement.multiply(currentBasisElement);

            if (!OhElementsAreClose(productWithInverse, identity)) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhMultiplicationTable: element times inverse failed at element index "
                        + elementIndex
                );
                allTestsOkay = false;
            }

            if (!OhElementsAreClose(inverseTimesProduct, identity)) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhMultiplicationTable: inverse times element failed at element index "
                        + elementIndex
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Spot products
         * ------------------------------------------------------------
         *
         * These are a few specific products that we already know from the
         * axis-image labels and inverse structure.
         *
         * We test them explicitly because they are easy to understand by eye.
         */

        /*
         * Spot product A:
         *
         *     xYZ * xYZ = xyz
         *
         * In basis indices:
         *
         *     1 * 1 = 0
         */
        OhElement spotAComputed =
                OhElement.basisElement(1).multiply(OhElement.basisElement(1));

        if (!OhElementsAreClose(spotAComputed, OhElement.basisElement(0))) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhMultiplicationTable: spot product A failed"
            );
            allTestsOkay = false;
        }

        /*
         * Spot product B:
         *
         *     yzx * zxy = xyz
         *
         * In the current basis indexing:
         *
         *     4 * 8 = 0
         */
        OhElement spotBComputed =
                OhElement.basisElement(4).multiply(OhElement.basisElement(8));

        if (!OhElementsAreClose(spotBComputed, OhElement.basisElement(0))) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhMultiplicationTable: spot product B failed"
            );
            allTestsOkay = false;
        }

        /*
         * Spot product C:
         *
         *     xzY * xZy = xyz
         *
         * In the current basis indexing:
         *
         *     12 * 13 = 0
         */
        OhElement spotCComputed =
                OhElement.basisElement(12).multiply(OhElement.basisElement(13));

        if (!OhElementsAreClose(spotCComputed, OhElement.basisElement(0))) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhMultiplicationTable: spot product C failed"
            );
            allTestsOkay = false;
        }

        /*
         * Spot product D:
         *
         *     Xzy * Xzy = xyz
         *
         * In the current basis indexing:
         *
         *     18 * 18 = 0
         */
        OhElement spotDComputed =
                OhElement.basisElement(18).multiply(OhElement.basisElement(18));

        if (!OhElementsAreClose(spotDComputed, OhElement.basisElement(0))) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhMultiplicationTable: spot product D failed"
            );
            allTestsOkay = false;
        }

        /*
         * Spot product E:
         *
         *     XYZ * XYZ = xyz
         *
         * In the current basis indexing:
         *
         *     24 * 24 = 0
         */
        OhElement spotEComputed =
                OhElement.basisElement(24).multiply(OhElement.basisElement(24));

        if (!OhElementsAreClose(spotEComputed, OhElement.basisElement(0))) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhMultiplicationTable: spot product E failed"
            );
            allTestsOkay = false;
        }

        /*
         * Spot product F:
         *
         *     xYZ * Xyz = XYZ
         *
         * In the current basis indexing:
         *
         *     1 * 25 = 24
         */
        OhElement spotFComputed =
                OhElement.basisElement(1).multiply(OhElement.basisElement(25));

        if (!OhElementsAreClose(spotFComputed, OhElement.basisElement(24))) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhMultiplicationTable: spot product F failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestOhMultiplicationTable() Okay");
        }
    }    

    /*
     * TestOhInversionPermutationLabelConsistency
     *
     * This method checks whether multiplying a proper basis element by the
     * inversion basis element produces the improper basis element with the
     * matching permutation label.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * The multiplication table is generated from the axis-image labels.
     * However, many human-facing parts of the project use the permutation
     * labels and the class assignments.
     *
     * If those two descriptions are mismatched for some basis elements,
     * then class-function tests and Fourier-basis tests can appear to fail
     * even though the axis-image multiplication itself is internally consistent.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * For every proper basis element g in indices 0 through 23:
     *
     *     g * i
     *
     * should be the improper basis element whose permutation label is simply
     * the proper label with "i" appended.
     *
     * EXAMPLES
     * --------
     *     ()        * i   should give ()i
     *     (1234)    * i   should give (1234)i
     *     (12)      * i   should give (12)i
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhInversionPermutationLabelConsistency() {

        boolean allTestsOkay = true;

        /*
         * In the current basis ordering, pure inversion is basis index 24.
         */
        OhElement inversionElement = OhElement.basisElement(24);

        /*
         * Check every proper basis element.
         */
        for (int properIndex = 0; properIndex < 24; properIndex++) {

            OhBasisElement properInfo = OhElement.getBasisElementInfo(properIndex);
            String properPermutationLabel = properInfo.getPermutationLabel();

            /*
             * Build the expected improper permutation label.
             *
             * We use the current project notation:
             *
             *     ()   -> ()i
             *     (12) -> (12)i
             */
            String expectedImproperLabel = properPermutationLabel + "i";

            /*
             * Multiply the proper basis element by inversion on the right.
             */
            OhElement product =
                    OhElement.basisElement(properIndex).multiply(inversionElement);

            int productIndex = FindSingleBasisIndexInOhElement(product);

            OhBasisElement productInfo = OhElement.getBasisElementInfo(productIndex);
            String actualImproperLabel = productInfo.getPermutationLabel();

            if (!actualImproperLabel.equals(expectedImproperLabel)) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhInversionPermutationLabelConsistency: proper index "
                        + properIndex
                        + " label "
                        + properPermutationLabel
                        + " times inversion gave index "
                        + productIndex
                        + " label "
                        + actualImproperLabel
                        + " instead of expected "
                        + expectedImproperLabel
                );
                allTestsOkay = false;
            }
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhInversionPermutationLabelConsistency() Okay");
        }
    }

    /*
     * FindSingleBasisIndexInOhElement
     *
     * This helper assumes the supplied OhElement is a pure basis element:
     *
     * - exactly one coefficient equal to 1
     * - all other coefficients equal to 0
     *
     * It returns the unique nonzero basis index.
     *
     * WHY THIS EXISTS
     * ---------------
     * For products of pure Oh basis elements, we often want the resulting
     * basis index directly in a test.
     */
    private static int FindSingleBasisIndexInOhElement(OhElement value) {

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
                            "FindSingleBasisIndexInOhElement found more than one nonzero basis slot."
                    );
                }
                foundIndex = index;
            } else if (!coefficientIsZero) {
                throw new IllegalStateException(
                        "FindSingleBasisIndexInOhElement encountered a coefficient that was neither zero nor one."
                    );
            }
        }

        if (foundIndex < 0) {
            throw new IllegalStateException(
                    "FindSingleBasisIndexInOhElement found no nonzero basis slot."
            );
        }

        return foundIndex;
    }


    /*
     * TestOhPauliElementShell
     *
     * This method tests the first working version of the OhPauliElement class.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * OhPauliElement is the Pauli-valued analogue of OhElement.
     *
     * That means it combines:
     *
     * - the 48-element Oh group basis
     * - with PauliValue coefficients
     *
     * So this test checks that the basic structure is behaving correctly
     * before we build anything more elaborate on top of it.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * 1. The zero element exists and has all-zero coefficients.
     * 2. The identity element exists.
     * 3. Left multiplication by identity works.
     * 4. Right multiplication by identity works.
     * 5. Addition with zero works.
     * 6. A pure basis-element product agrees with the Oh multiplication.
     * 7. A coefficient-carrying basis element multiplies correctly.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhPauliElementShell() {

        /*
         * This variable records whether every sub-test has passed.
         */
        boolean allTestsOkay = true;

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Zero element
         * ------------------------------------------------------------
         *
         * Every coefficient of the zero element should be the Pauli zero.
         */
        OhPauliElement zero = OhPauliElement.zero();

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {
            if (!PauliValuesAreClose(zero.getCoefficient(index), PauliValue.zero())) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhPauliElementShell: zero coefficient failed at index "
                        + index
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Identity element
         * ------------------------------------------------------------
         *
         * The identity should be basis element 0 with Pauli identity coefficient.
         */
        OhPauliElement identity = OhPauliElement.identity();

        if (!PauliValuesAreClose(identity.getCoefficient(0), PauliValue.identity())) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementShell: identity coefficient at index 0 failed"
            );
            allTestsOkay = false;
        }

        for (int index = 1; index < OhElement.getNumberOfBasisElements(); index++) {
            if (!PauliValuesAreClose(identity.getCoefficient(index), PauliValue.zero())) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhPauliElementShell: identity extra coefficient failed at index "
                        + index
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Left identity
         * ------------------------------------------------------------
         *
         * We test a nontrivial basis element rather than only the identity itself.
         */
        OhPauliElement basisFour = OhPauliElement.basisElement(4);
        OhPauliElement leftIdentityProduct = identity.multiply(basisFour);

        if (!OhPauliElementsAreClose(leftIdentityProduct, basisFour)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementShell: left identity failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * Right identity
         * ------------------------------------------------------------
         */
        OhPauliElement rightIdentityProduct = basisFour.multiply(identity);

        if (!OhPauliElementsAreClose(rightIdentityProduct, basisFour)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementShell: right identity failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Addition with zero
         * ------------------------------------------------------------
         */
        OhPauliElement zeroPlusBasisFour = zero.add(basisFour);
        OhPauliElement basisFourPlusZero = basisFour.add(zero);

        if (!OhPauliElementsAreClose(zeroPlusBasisFour, basisFour)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementShell: zero plus basis element failed"
            );
            allTestsOkay = false;
        }

        if (!OhPauliElementsAreClose(basisFourPlusZero, basisFour)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementShell: basis element plus zero failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 6:
         * Pure basis-element product
         * ------------------------------------------------------------
         *
         * We know from the Oh multiplication tests that:
         *
         *     basis 4 * basis 8 = basis 0
         *
         * So the Pauli-valued version with identity coefficients should
         * do exactly the same thing.
         */
        OhPauliElement basisEight = OhPauliElement.basisElement(8);
        OhPauliElement pureBasisProduct = basisFour.multiply(basisEight);
        OhPauliElement expectedPureBasisProduct = OhPauliElement.basisElement(0);

        if (!OhPauliElementsAreClose(pureBasisProduct, expectedPureBasisProduct)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementShell: pure basis product failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 7:
         * Basis element with nontrivial Pauli coefficient
         * ------------------------------------------------------------
         *
         * Let:
         *
         *     A = sigma_x * g_4
         *     B = sigma_y * g_8
         *
         * Since:
         *
         *     g_4 * g_8 = g_0
         *
         * and:
         *
         *     sigma_x * sigma_y = i sigma_z
         *
         * the product should be:
         *
         *     (i sigma_z) * g_0
         */
        PauliValue sigmaX = PauliValue.sigmaX();
        PauliValue sigmaY = PauliValue.sigmaY();

        PauliValue[] coefficientArrayA = new PauliValue[OhElement.getNumberOfBasisElements()];
        PauliValue[] coefficientArrayB = new PauliValue[OhElement.getNumberOfBasisElements()];
        PauliValue[] expectedCoefficientArray = new PauliValue[OhElement.getNumberOfBasisElements()];

        /*
         * Initialize all coefficients to zero first.
         */
        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {
            coefficientArrayA[index] = PauliValue.zero();
            coefficientArrayB[index] = PauliValue.zero();
            expectedCoefficientArray[index] = PauliValue.zero();
        }

        /*
         * Put sigma_x in slot 4 and sigma_y in slot 8.
         */
        coefficientArrayA[4] = sigmaX;
        coefficientArrayB[8] = sigmaY;

        /*
         * Expected coefficient at slot 0 is i sigma_z.
         */
        expectedCoefficientArray[0] =
                new PauliValue(
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 1.0)
                );

        OhPauliElement elementA = new OhPauliElement(coefficientArrayA);
        OhPauliElement elementB = new OhPauliElement(coefficientArrayB);
        OhPauliElement expectedMixedProduct = new OhPauliElement(expectedCoefficientArray);

        OhPauliElement computedMixedProduct = elementA.multiply(elementB);

        if (!OhPauliElementsAreClose(computedMixedProduct, expectedMixedProduct)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementShell: mixed Pauli basis product failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestOhPauliElementShell() Okay");
        }
    }

    /*
     * OhPauliElementsAreClose
     *
     * This helper compares two OhPauliElement objects coefficient by coefficient.
     *
     * WHY THIS METHOD EXISTS
     * ----------------------
     * An OhPauliElement has 48 coefficients, each of which is a PauliValue.
     * So two OhPauliElement objects are considered close exactly when all
     * 48 corresponding PauliValue coefficients are close.
     */
    private static boolean OhPauliElementsAreClose(
            OhPauliElement firstElement,
            OhPauliElement secondElement) {

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {

            if (!PauliValuesAreClose(
                    firstElement.getCoefficient(index),
                    secondElement.getCoefficient(index))) {
                return false;
            }
        }

        return true;
    }    

    /*
     * TestOhPermutationLabelMultiplication
     *
     * This method independently checks Oh basis multiplication by multiplying
     * the permutation labels themselves.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * This compares:
     *
     *     - the computed basis-element product from OhElement.multiply(...)
     *
     * against
     *
     *     - the expected product obtained by multiplying the sovereign
     *       permutation labels with OhPermutationElement
     *
     * Since the multiplication table is now generated from the canonical
     * permutation list, this test is a very strong consistency check that:
     *
     * - the basis-element metadata
     * - the multiplication table
     * - and the label lookup
     *
     * all agree with one another.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhPermutationLabelMultiplication() {

        boolean allTestsOkay = true;

        int numberOfBasisElements = OhElement.getNumberOfBasisElements();

        for (int leftIndex = 0; leftIndex < numberOfBasisElements; leftIndex++) {
            for (int rightIndex = 0; rightIndex < numberOfBasisElements; rightIndex++) {

                OhBasisElement leftInfo = OhElement.getBasisElementInfo(leftIndex);
                OhBasisElement rightInfo = OhElement.getBasisElementInfo(rightIndex);

                String leftLabel = leftInfo.getPermutationLabel();
                String rightLabel = rightInfo.getPermutationLabel();

                String expectedProductLabel =
                        OhPermutationElement.fromLabel(leftLabel)
                                .multiply(OhPermutationElement.fromLabel(rightLabel))
                                .toLabel();

                OhElement actualProduct =
                        OhElement.basisElement(leftIndex)
                                .multiply(OhElement.basisElement(rightIndex));

                int actualProductIndex = FindSingleBasisIndexInOhElement(actualProduct);

                String actualProductLabel =
                        OhElement.getBasisElementInfo(actualProductIndex).getPermutationLabel();

                if (!actualProductLabel.equals(expectedProductLabel)) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhPermutationLabelMultiplication: "
                            + leftLabel + " * " + rightLabel
                            + " gave " + actualProductLabel
                            + " instead of expected " + expectedProductLabel
                    );
                    allTestsOkay = false;
                }
            }
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhPermutationLabelMultiplication() Okay");
        }
    }

    /*
     * MultiplyPermutationLabels
     *
     * Multiplies two permutation labels such as:
     *
     *     ()
     *     (12)
     *     (1234)
     *     (12)(34)
     *     (123)i
     *     ()i
     *
     * and returns the canonical label of the product.
     *
     * CONVENTION
     * ----------
     * This follows the same convention as the Oh multiplication:
     *
     *     left followed by right
     *
     * so the resulting permutation is:
     *
     *     right ∘ left
     *
     * and inversion flags add modulo 2.
     */
    private static String MultiplyPermutationLabels(String leftLabel, String rightLabel) {

        PermutationWithInversion left = ParsePermutationLabel(leftLabel);
        PermutationWithInversion right = ParsePermutationLabel(rightLabel);

        int[] composedPermutation =
                ComposePermutations(left.permutation, right.permutation);

        boolean composedInversion = left.hasInversion ^ right.hasInversion;

        return FormatPermutationLabel(composedPermutation, composedInversion);
    }

    /*
     * ParsePermutationLabel
     *
     * Parses labels like:
     *
     *     ()
     *     (12)
     *     (123)
     *     (1234)
     *     (12)(34)
     *     ()i
     *     (123)i
     *
     * into:
     *
     *     - a permutation of {1,2,3,4}
     *     - a boolean inversion flag
     */
    private static PermutationWithInversion ParsePermutationLabel(String label) {

        boolean hasInversion = false;
        String permutationPart = label;

        if (label.endsWith("i")) {
            hasInversion = true;
            permutationPart = label.substring(0, label.length() - 1);
        }

        int[] permutation = {1, 2, 3, 4};

        int position = 0;
        while (position < permutationPart.length()) {

            if (permutationPart.charAt(position) == '(') {
                int endPosition = permutationPart.indexOf(')', position);
                String cycleText = permutationPart.substring(position + 1, endPosition);

                if (!cycleText.isEmpty()) {
                    ApplyCycleTextToPermutation(permutation, cycleText);
                }

                position = endPosition + 1;
            } else {
                position++;
            }
        }

        return new PermutationWithInversion(permutation, hasInversion);
    }

    /*
     * ApplyCycleTextToPermutation
     *
     * Applies one cycle such as:
     *
     *     "12"
     *     "123"
     *     "1234"
     *
     * to the permutation array in place.
     */
    private static void ApplyCycleTextToPermutation(int[] permutation, String cycleText) {

        int cycleLength = cycleText.length();

        if (cycleLength < 2) {
            return;
        }

        int[] cycleEntries = new int[cycleLength];

        for (int index = 0; index < cycleLength; index++) {
            cycleEntries[index] = Character.digit(cycleText.charAt(index), 10);
        }

        /*
         * Convert the cycle into a mapping on {1,2,3,4}.
         */
        int[] cyclePermutation = {1, 2, 3, 4};

        for (int index = 0; index < cycleLength; index++) {
            int from = cycleEntries[index];
            int to = cycleEntries[(index + 1) % cycleLength];
            cyclePermutation[from - 1] = to;
        }

        /*
         * Compose the current permutation with this cycle.
         */
        int[] updatedPermutation = ComposePermutations(permutation, cyclePermutation);

        for (int index = 0; index < 4; index++) {
            permutation[index] = updatedPermutation[index];
        }
    }

    /*
     * ComposePermutations
     *
     * Returns the composition:
     *
     *     right ∘ left
     *
     * represented on the set {1,2,3,4}.
     *
     * The array representation is:
     *
     *     permutation[k-1] = image of k
     */
    private static int[] ComposePermutations(int[] left, int[] right) {

        int[] result = new int[4];

        for (int index = 0; index < 4; index++) {
            int imageUnderLeft = left[index];
            int imageUnderRightAfterLeft = right[imageUnderLeft - 1];
            result[index] = imageUnderRightAfterLeft;
        }

        return result;
    }

    /*
     * FormatPermutationLabel
     *
     * Converts a permutation plus inversion flag back into the project's
     * canonical label format.
     *
     * EXAMPLES
     * --------
     *     identity permutation, no inversion  -> ()
     *     identity permutation, inversion     -> ()i
     *     transposition (12), inversion       -> (12)i
     */
    private static String FormatPermutationLabel(int[] permutation, boolean hasInversion) {

        boolean[] visited = new boolean[4];
        String cycleString = "";

        for (int start = 1; start <= 4; start++) {

            if (visited[start - 1]) {
                continue;
            }

            int next = permutation[start - 1];

            if (next == start) {
                visited[start - 1] = true;
                continue;
            }

            String oneCycle = "(" + start;
            visited[start - 1] = true;

            int current = next;
            while (current != start) {
                oneCycle += current;
                visited[current - 1] = true;
                current = permutation[current - 1];
            }

            oneCycle += ")";
            cycleString += oneCycle;
        }

        if (cycleString.equals("")) {
            cycleString = "()";
        }

        if (hasInversion) {
            cycleString += "i";
        }

        return cycleString;
    }

    /*
     * PermutationWithInversion
     *
     * Small helper record-like class for this test.
     */
    private static class PermutationWithInversion {
        private final int[] permutation;
        private final boolean hasInversion;

        private PermutationWithInversion(int[] permutation, boolean hasInversion) {
            this.permutation = permutation;
            this.hasInversion = hasInversion;
        }
    }

    /*
     * TestOhPauliElementPrinter
     *
     * This method tests the printer methods for OhPauliElement.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * Printers are easy to break accidentally when improving formatting.
     * Since readable output is important for this project, it is worth having
     * a small regression test for the printing behavior.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * 1. The zero OhPauliElement prints as "0" in compact form.
     * 2. The zero OhPauliElement prints as "0" in class-grouped form.
     * 3. A simple nonzero example contains an expected class code.
     * 4. A simple nonzero example contains an expected permutation label.
     * 5. A simple nonzero example contains a recognizable Pauli basis string.
     *
     * IMPORTANT NOTE
     * --------------
     * This test does not require an exact full-string match for nonzero output.
     * That would make the test too fragile against harmless formatting changes.
     *
     * Instead, it checks for key substrings that should definitely appear.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhPauliElementPrinter() {

        /*
         * This variable records whether every sub-test has passed.
         */
        boolean allTestsOkay = true;

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Zero element in compact form
         * ------------------------------------------------------------
         */
        OhPauliElement zero = OhPauliElement.zero();
        String zeroCompactString = zero.toString();

        if (!zeroCompactString.equals("0")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementPrinter: zero compact print failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Zero element in class-grouped form
         * ------------------------------------------------------------
         */
        String zeroGroupedString = zero.toClassGroupedString();

        if (!zeroGroupedString.equals("0")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementPrinter: zero grouped print failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Nonzero example in class-grouped form
         * ------------------------------------------------------------
         *
         * Build a small example with terms in three different classes.
         */
        PauliValue[] demoCoefficients =
                new PauliValue[OhElement.getNumberOfBasisElements()];

        /*
         * Initialize all coefficients to the Pauli zero.
         */
        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {
            demoCoefficients[index] = PauliValue.zero();
        }

        /*
         * Put a few nonzero coefficients in selected basis slots.
         */
        demoCoefficients[0] = PauliValue.identity();
        demoCoefficients[4] = PauliValue.sigmaX();
        demoCoefficients[24] = PauliValue.sigmaZ();

        OhPauliElement demoElement = new OhPauliElement(demoCoefficients);

        String groupedString = demoElement.toClassGroupedString();

        /*
         * Check that the grouped printer shows the expected class code
         * for the identity class.
         */
        if (!groupedString.contains("K_xyz")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementPrinter: missing class code K_xyz"
            );
            allTestsOkay = false;
        }

        /*
         * Check that it also shows the class code for the inversion class.
         */
        if (!groupedString.contains("K_XYZ")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementPrinter: missing class code K_XYZ"
            );
            allTestsOkay = false;
        }

        /*
         * Check that the identity permutation label appears.
         */
        if (!groupedString.contains("()")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementPrinter: missing permutation label ()"
            );
            allTestsOkay = false;
        }

        /*
         * Check that the inversion permutation label appears.
         */
        if (!groupedString.contains("()i")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementPrinter: missing permutation label ()i"
            );
            allTestsOkay = false;
        }

        /*
         * Check that the class-2 representative label appears, since slot 4
         * was included in the demo element.
         */
        if (!groupedString.contains("(123)")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementPrinter: missing permutation label (123)"
            );
            allTestsOkay = false;
        }

        /*
         * Check that the Pauli basis names appear somewhere in the printout.
         *
         * We do not require all of them here; seeing sigma_x and sigma_z is
         * enough for this small demo.
         */
        if (!groupedString.contains("sigma_x")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementPrinter: missing sigma_x text"
            );
            allTestsOkay = false;
        }

        if (!groupedString.contains("sigma_z")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementPrinter: missing sigma_z text"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * Nonzero example in compact form
         * ------------------------------------------------------------
         *
         * The compact string should also contain key basis labels.
         */
        String compactString = demoElement.toString();

        if (!compactString.contains("()")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementPrinter: compact string missing ()"
            );
            allTestsOkay = false;
        }

        if (!compactString.contains("(123)")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementPrinter: compact string missing (123)"
            );
            allTestsOkay = false;
        }

        if (!compactString.contains("()i")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPauliElementPrinter: compact string missing ()i"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestOhPauliElementPrinter() Okay");
        }
    }
    /*
     * TestOhFourierSingletIdempotents
     *
     * This method tests the first four Fourier-basis elements that have so far
     * been translated into OhFourierBasis.java.
     *
     * CURRENT SCOPE
     * -------------
     * At the moment, OhFourierBasis implements only transformed basis indices
     * 0 through 3, corresponding to the four singlet sectors at the beginning
     * of the uploaded Fourier transform data. :contentReference[oaicite:0]{index=0}
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * 1. Each of the first four Fourier elements is idempotent:
     *
     *        e_k * e_k = e_k
     *
     * 2. Distinct singlets annihilate:
     *
     *        e_i * e_j = 0   for i != j
     *
     * 3. Their sum is also idempotent:
     *
     *        (e_0 + e_1 + e_2 + e_3)^2 = e_0 + e_1 + e_2 + e_3
     *
     * WHY THIS TEST MATTERS
     * ---------------------
     * These are exactly the algebraic properties we expect from mutually
     * orthogonal idempotent projectors coming from the irreducible sectors.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhFourierSingletIdempotents() {

        /*
         * This variable records whether every sub-test has passed.
         */
        boolean allTestsOkay = true;

        /*
         * Load the first four translated Fourier basis elements.
         */
        OhElement e0 = OhFourierBasis.GetElement(0);
        OhElement e1 = OhFourierBasis.GetElement(1);
        OhElement e2 = OhFourierBasis.GetElement(2);
        OhElement e3 = OhFourierBasis.GetElement(3);

        OhElement[] singlets = {e0, e1, e2, e3};

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Each singlet should be idempotent
         * ------------------------------------------------------------
         */
        for (int index = 0; index < singlets.length; index++) {

            OhElement square = singlets[index].multiply(singlets[index]);

            if (!OhElementsAreClose(square, singlets[index])) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhFourierSingletIdempotents: idempotence failed for singlet index "
                        + index
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Distinct singlets should annihilate
         * ------------------------------------------------------------
         */
        OhElement zero = OhElement.zero();

        for (int firstIndex = 0; firstIndex < singlets.length; firstIndex++) {
            for (int secondIndex = 0; secondIndex < singlets.length; secondIndex++) {

                if (firstIndex == secondIndex) {
                    continue;
                }

                OhElement product =
                        singlets[firstIndex].multiply(singlets[secondIndex]);

                if (!OhElementsAreClose(product, zero)) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierSingletIdempotents: annihilation failed for singlet indices "
                            + firstIndex + " and " + secondIndex
                    );
                    allTestsOkay = false;
                }
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * The sum of the four singlets should be idempotent
         * ------------------------------------------------------------
         */
        OhElement singletSum =
                e0.add(e1).add(e2).add(e3);

        OhElement singletSumSquare =
                singletSum.multiply(singletSum);

        if (!OhElementsAreClose(singletSumSquare, singletSum)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierSingletIdempotents: singlet sum idempotence failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestOhFourierSingletIdempotents() Okay");
        }
    }    
    
        /*
     * TestOhFourierElements4to11Basics
     *
     * This method tests the first nontrivial block of translated Fourier-basis
     * elements, namely transformed basis indices 4 through 11.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * These elements are sparser and more structured than the first four
     * singlets, so before going further we want to verify that the Java
     * translation preserved:
     *
     * - support locations,
     * - real versus imaginary character,
     * - and the proper/improper sign relationships that appear in the
     *   uploaded transform data.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * Element 4:
     *     - nonzero exactly on indices 0..11 and 24..35
     *     - positive on 0..3 and 24..27
     *     - negative on 4..11 and 28..35
     *
     * Element 5:
     *     - nonzero exactly on 16 slots
     *     - all coefficients purely real
     *
     * Element 6:
     *     - nonzero exactly on 16 slots
     *     - all coefficients purely imaginary
     *
     * Element 7:
     *     - nonzero exactly on class-3/class-4 and class-8/class-9 slots
     *     - all coefficients purely real
     *
     * Elements 8, 9, 10, 11:
     *     - same support as 4, 5, 6, 7 respectively
     *     - proper/improper sign reversal pattern as encoded in the uploaded data
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhFourierElements4to11Basics() {

        boolean allTestsOkay = true;

        OhElement e4 = OhFourierBasis.GetElement(4);
        OhElement e5 = OhFourierBasis.GetElement(5);
        OhElement e6 = OhFourierBasis.GetElement(6);
        OhElement e7 = OhFourierBasis.GetElement(7);
        OhElement e8 = OhFourierBasis.GetElement(8);
        OhElement e9 = OhFourierBasis.GetElement(9);
        OhElement e10 = OhFourierBasis.GetElement(10);
        OhElement e11 = OhFourierBasis.GetElement(11);

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Element 4 support and signs
         * ------------------------------------------------------------
         */
        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {

            ComplexNumber c = e4.getCoefficient(index);
            boolean isZero = ComplexNumberIsCloseToZeroForTesting(c);

            boolean shouldBeNonzero =
                    (index >= 0 && index <= 11)
                    || (index >= 24 && index <= 35);

            if (shouldBeNonzero && isZero) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 4 missing nonzero coefficient at index "
                        + index
                );
                allTestsOkay = false;
            }

            if (!shouldBeNonzero && !isZero) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 4 unexpected nonzero coefficient at index "
                        + index
                );
                allTestsOkay = false;
            }

            /*
             * For the nonzero slots, check sign pattern.
             */
            if (shouldBeNonzero) {

                boolean shouldBePositive =
                        (index >= 0 && index <= 3)
                        || (index >= 24 && index <= 27);

                boolean shouldBeNegative =
                        (index >= 4 && index <= 11)
                        || (index >= 28 && index <= 35);

                if (shouldBePositive && !(c.getRealPart() > 0.0)) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 4 positive sign failed at index "
                            + index
                    );
                    allTestsOkay = false;
                }

                if (shouldBeNegative && !(c.getRealPart() < 0.0)) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 4 negative sign failed at index "
                            + index
                    );
                    allTestsOkay = false;
                }

                if (Math.abs(c.getImaginaryPart()) > 1.0e-12) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 4 should be purely real at index "
                            + index
                    );
                    allTestsOkay = false;
                }
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Element 5 should have exactly 16 nonzero purely real coefficients
         * ------------------------------------------------------------
         */
        int e5NonzeroCount = 0;

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {
            ComplexNumber c = e5.getCoefficient(index);

            if (!ComplexNumberIsCloseToZeroForTesting(c)) {
                e5NonzeroCount++;

                if (Math.abs(c.getImaginaryPart()) > 1.0e-12) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 5 should be purely real at index "
                            + index
                    );
                    allTestsOkay = false;
                }
            }
        }

        if (e5NonzeroCount != 16) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 5 nonzero count failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Element 6 should have exactly 16 nonzero purely imaginary coefficients
         * ------------------------------------------------------------
         */
        int e6NonzeroCount = 0;

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {
            ComplexNumber c = e6.getCoefficient(index);

            if (!ComplexNumberIsCloseToZeroForTesting(c)) {
                e6NonzeroCount++;

                if (Math.abs(c.getRealPart()) > 1.0e-12) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 6 should be purely imaginary at index "
                            + index
                    );
                    allTestsOkay = false;
                }
            }
        }

        if (e6NonzeroCount != 16) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 6 nonzero count failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * Element 7 should have exactly 24 nonzero purely real coefficients
         * ------------------------------------------------------------
         */
        int e7NonzeroCount = 0;

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {
            ComplexNumber c = e7.getCoefficient(index);

            if (!ComplexNumberIsCloseToZeroForTesting(c)) {
                e7NonzeroCount++;

                if (Math.abs(c.getImaginaryPart()) > 1.0e-12) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 7 should be purely real at index "
                            + index
                    );
                    allTestsOkay = false;
                }
            }
        }

        if (e7NonzeroCount != 24) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 7 nonzero count failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Elements 8, 9, 10, 11 should match support of 4, 5, 6, 7
         * respectively
         * ------------------------------------------------------------
         */
        if (!OhElementSupportMatches(e4, e8)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 8 support does not match element 4"
            );
            allTestsOkay = false;
        }

        if (!OhElementSupportMatches(e5, e9)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 9 support does not match element 5"
            );
            allTestsOkay = false;
        }

        if (!OhElementSupportMatches(e6, e10)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 10 support does not match element 6"
            );
            allTestsOkay = false;
        }

        if (!OhElementSupportMatches(e7, e11)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: element 11 support does not match element 7"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 6:
         * Proper/improper sign reversal relationships
         * ------------------------------------------------------------
         *
         * Element 8 versus 4:
         *     same signs on proper slots, opposite signs on improper slots
         *
         * Element 9 versus 5:
         *     same signs on proper slots, opposite signs on improper slots
         *
         * Element 10 versus 6:
         *     same signs on proper slots, opposite signs on improper slots
         *
         * Element 11 versus 7:
         *     same signs on proper slots, opposite signs on improper slots
         */
        if (!ProperImproperSignRelationHolds(e4, e8)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: proper/improper sign relation failed for elements 4 and 8"
            );
            allTestsOkay = false;
        }

        if (!ProperImproperSignRelationHolds(e5, e9)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: proper/improper sign relation failed for elements 5 and 9"
            );
            allTestsOkay = false;
        }

        if (!ProperImproperSignRelationHolds(e6, e10)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: proper/improper sign relation failed for elements 6 and 10"
            );
            allTestsOkay = false;
        }

        if (!ProperImproperSignRelationHolds(e7, e11)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements4to11Basics: proper/improper sign relation failed for elements 7 and 11"
            );
            allTestsOkay = false;
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhFourierElements4to11Basics() Okay");
        }
    }

    /*
     * ComplexNumberIsCloseToZeroForTesting
     *
     * Small helper used by Fourier-basis tests.
     */
    private static boolean ComplexNumberIsCloseToZeroForTesting(ComplexNumber value) {
        double tolerance = 1.0e-12;

        return Math.abs(value.getRealPart()) < tolerance
                && Math.abs(value.getImaginaryPart()) < tolerance;
    }

    /*
     * OhElementSupportMatches
     *
     * Two OhElement objects are said to have matching support if they are
     * nonzero in exactly the same basis slots.
     */
    private static boolean OhElementSupportMatches(
            OhElement firstElement,
            OhElement secondElement) {

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {

            boolean firstIsZero =
                    ComplexNumberIsCloseToZeroForTesting(firstElement.getCoefficient(index));

            boolean secondIsZero =
                    ComplexNumberIsCloseToZeroForTesting(secondElement.getCoefficient(index));

            if (firstIsZero != secondIsZero) {
                return false;
            }
        }

        return true;
    }

    /*
     * ProperImproperSignRelationHolds
     *
     * Checks the relation:
     *
     * - same coefficient on proper slots 0..23
     * - opposite coefficient on improper slots 24..47
     *
     * for two OhElement objects with matching support.
     */
    private static boolean ProperImproperSignRelationHolds(
            OhElement properReference,
            OhElement parityModified) {

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {

            ComplexNumber a = properReference.getCoefficient(index);
            ComplexNumber b = parityModified.getCoefficient(index);

            boolean aIsZero = ComplexNumberIsCloseToZeroForTesting(a);
            boolean bIsZero = ComplexNumberIsCloseToZeroForTesting(b);

            if (aIsZero && bIsZero) {
                continue;
            }

            if (aIsZero != bIsZero) {
                return false;
            }

            if (index < 24) {
                if (!ComplexNumbersAreClose(a, b)) {
                    return false;
                }
            } else {
                if (!ComplexNumbersAreClose(
                        a,
                        new ComplexNumber(-b.getRealPart(), -b.getImaginaryPart()))) {
                    return false;
                }
            }
        }

        return true;
    }
    /*
     * TestOhFourierElements21to29Basics
     *
     * This method tests the translated Fourier-basis elements 21 through 29.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * These elements are the next translated block after 12 through 20.
     * As before, the safest next step is to verify the structural properties
     * we intended to translate from the uploaded Fourier data.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * Element 21:
     *     - purely real
     *     - nonzero on 32 slots
     *
     * Elements 22, 24, 25, 27:
     *     - purely real
     *     - nonzero on 20 slots each
     *
     * Elements 23, 26, 28:
     *     - purely imaginary
     *     - nonzero on 20 slots each
     *
     * Element 29:
     *     - purely real
     *     - nonzero on 30 slots
     *
     * Additional structural checks:
     *
     * - 22 and 23 have matching support
     * - 25 and 26 have matching support
     * - 27 and 28 have matching support
     * - 21 is the proper/improper sign-reversed partner of 12
     * - 29 is the proper/improper sign-reversed partner of 20
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhFourierElements21to29Basics() {

        boolean allTestsOkay = true;

        OhElement e12 = OhFourierBasis.GetElement(12);
        OhElement e20 = OhFourierBasis.GetElement(20);

        OhElement e21 = OhFourierBasis.GetElement(21);
        OhElement e22 = OhFourierBasis.GetElement(22);
        OhElement e23 = OhFourierBasis.GetElement(23);
        OhElement e24 = OhFourierBasis.GetElement(24);
        OhElement e25 = OhFourierBasis.GetElement(25);
        OhElement e26 = OhFourierBasis.GetElement(26);
        OhElement e27 = OhFourierBasis.GetElement(27);
        OhElement e28 = OhFourierBasis.GetElement(28);
        OhElement e29 = OhFourierBasis.GetElement(29);

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Element 21 should be purely real with 32 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e21, 32)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: element 21 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Element 22 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e22, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: element 22 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Element 23 should be purely imaginary with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyImaginaryWithNonzeroCount(e23, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: element 23 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * Element 24 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e24, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: element 24 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Element 25 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e25, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: element 25 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 6:
         * Element 26 should be purely imaginary with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyImaginaryWithNonzeroCount(e26, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: element 26 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 7:
         * Element 27 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e27, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: element 27 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 8:
         * Element 28 should be purely imaginary with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyImaginaryWithNonzeroCount(e28, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: element 28 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 9:
         * Element 29 should be purely real with 30 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e29, 30)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: element 29 basic structure failed"
            );
            allTestsOkay = false;
        }


        /*
         * ------------------------------------------------------------
         * Test 13:
         * Element 21 should be the proper/improper sign-reversed partner of 12
         * ------------------------------------------------------------
         */
        if (!ProperImproperSignRelationHolds(e12, e21)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: proper/improper sign relation failed for elements 12 and 21"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 14:
         * Element 29 should be the proper/improper sign-reversed partner of 20
         * ------------------------------------------------------------
         */
        if (!ProperImproperSignRelationHolds(e20, e29)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: proper/improper sign relation failed for elements 20 and 29"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 15:
         * Spot support anchors for element 21
         * ------------------------------------------------------------
         *
         * Element 21 should be nonzero at:
         *
         *     0, 24, 12, 36
         *
         * and zero at:
         *
         *     4, 28
         */
        if (ComplexNumberIsCloseToZeroForTesting(e21.getCoefficient(0))
                || ComplexNumberIsCloseToZeroForTesting(e21.getCoefficient(24))
                || ComplexNumberIsCloseToZeroForTesting(e21.getCoefficient(12))
                || ComplexNumberIsCloseToZeroForTesting(e21.getCoefficient(36))
                || !ComplexNumberIsCloseToZeroForTesting(e21.getCoefficient(4))
                || !ComplexNumberIsCloseToZeroForTesting(e21.getCoefficient(28))) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: element 21 spot support failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 16:
         * Spot support anchors for element 29
         * ------------------------------------------------------------
         *
         * Element 29 should be nonzero at:
         *
         *     1, 3, 12, 20, 25, 27, 36, 44
         *
         * and zero at:
         *
         *     0, 4, 24, 28
         */
        if (ComplexNumberIsCloseToZeroForTesting(e29.getCoefficient(1))
                || ComplexNumberIsCloseToZeroForTesting(e29.getCoefficient(3))
                || ComplexNumberIsCloseToZeroForTesting(e29.getCoefficient(12))
                || ComplexNumberIsCloseToZeroForTesting(e29.getCoefficient(20))
                || ComplexNumberIsCloseToZeroForTesting(e29.getCoefficient(25))
                || ComplexNumberIsCloseToZeroForTesting(e29.getCoefficient(27))
                || ComplexNumberIsCloseToZeroForTesting(e29.getCoefficient(36))
                || ComplexNumberIsCloseToZeroForTesting(e29.getCoefficient(44))
                || !ComplexNumberIsCloseToZeroForTesting(e29.getCoefficient(0))
                || !ComplexNumberIsCloseToZeroForTesting(e29.getCoefficient(4))
                || !ComplexNumberIsCloseToZeroForTesting(e29.getCoefficient(24))
                || !ComplexNumberIsCloseToZeroForTesting(e29.getCoefficient(28))) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements21to29Basics: element 29 spot support failed"
            );
            allTestsOkay = false;
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhFourierElements21to29Basics() Okay");
        }
    }

        /*
     * TestOhFourierElements30to38Basics
     *
     * This method tests the translated Fourier-basis elements 30 through 38.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * This is the final translated nontrivial Fourier block.
     *
     * As with the previous blocks, the safest way to proceed is to verify:
     *
     * - real versus imaginary character,
     * - nonzero support counts,
     * - and a couple of obvious proper/improper sign-reversal relationships.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * Element 30:
     *     - purely real
     *     - nonzero on 32 slots
     *
     * Elements 31, 33, 34, 36:
     *     - purely real
     *     - nonzero on 20 slots each
     *
     * Elements 32, 35, 37:
     *     - purely imaginary
     *     - nonzero on 20 slots each
     *
     * Element 38:
     *     - purely real
     *     - nonzero on 30 slots
     *
     * Additional structural checks:
     *
     * - 30 is the proper/improper sign-reversed partner of 21
     * - 38 is the proper/improper sign-reversed partner of 29
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhFourierElements30to38Basics() {

        boolean allTestsOkay = true;

        OhElement e21 = OhFourierBasis.GetElement(21);
        OhElement e29 = OhFourierBasis.GetElement(29);

        OhElement e30 = OhFourierBasis.GetElement(30);
        OhElement e31 = OhFourierBasis.GetElement(31);
        OhElement e32 = OhFourierBasis.GetElement(32);
        OhElement e33 = OhFourierBasis.GetElement(33);
        OhElement e34 = OhFourierBasis.GetElement(34);
        OhElement e35 = OhFourierBasis.GetElement(35);
        OhElement e36 = OhFourierBasis.GetElement(36);
        OhElement e37 = OhFourierBasis.GetElement(37);
        OhElement e38 = OhFourierBasis.GetElement(38);

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Element 30 should be purely real with 32 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e30, 32)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements30to38Basics: element 30 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Element 31 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e31, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements30to38Basics: element 31 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Element 32 should be purely imaginary with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyImaginaryWithNonzeroCount(e32, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements30to38Basics: element 32 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * Element 33 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e33, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements30to38Basics: element 33 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Element 34 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e34, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements30to38Basics: element 34 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 6:
         * Element 35 should be purely imaginary with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyImaginaryWithNonzeroCount(e35, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements30to38Basics: element 35 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 7:
         * Element 36 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e36, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements30to38Basics: element 36 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 8:
         * Element 37 should be purely imaginary with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyImaginaryWithNonzeroCount(e37, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements30to38Basics: element 37 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 9:
         * Element 38 should be purely real with 30 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e38, 30)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements30to38Basics: element 38 basic structure failed"
            );
            allTestsOkay = false;
        }


        /*
         * ------------------------------------------------------------
         * Test 12:
         * Spot support anchors for element 30
         * ------------------------------------------------------------
         *
         * Element 30 should be nonzero at:
         *
         *     0, 24, 12, 36, 18, 42
         *
         * and zero at:
         *
         *     4, 28
         */
        if (ComplexNumberIsCloseToZeroForTesting(e30.getCoefficient(0))
                || ComplexNumberIsCloseToZeroForTesting(e30.getCoefficient(24))
                || ComplexNumberIsCloseToZeroForTesting(e30.getCoefficient(12))
                || ComplexNumberIsCloseToZeroForTesting(e30.getCoefficient(36))
                || ComplexNumberIsCloseToZeroForTesting(e30.getCoefficient(18))
                || ComplexNumberIsCloseToZeroForTesting(e30.getCoefficient(42))
                || !ComplexNumberIsCloseToZeroForTesting(e30.getCoefficient(4))
                || !ComplexNumberIsCloseToZeroForTesting(e30.getCoefficient(28))) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements30to38Basics: element 30 spot support failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 13:
         * Spot support anchors for element 38
         * ------------------------------------------------------------
         *
         * Element 38 should be nonzero at:
         *
         *     1, 3, 12, 20, 25, 27, 36, 44
         *
         * and zero at:
         *
         *     0, 4, 24, 28
         */
        if (ComplexNumberIsCloseToZeroForTesting(e38.getCoefficient(1))
                || ComplexNumberIsCloseToZeroForTesting(e38.getCoefficient(3))
                || ComplexNumberIsCloseToZeroForTesting(e38.getCoefficient(12))
                || ComplexNumberIsCloseToZeroForTesting(e38.getCoefficient(20))
                || ComplexNumberIsCloseToZeroForTesting(e38.getCoefficient(25))
                || ComplexNumberIsCloseToZeroForTesting(e38.getCoefficient(27))
                || ComplexNumberIsCloseToZeroForTesting(e38.getCoefficient(36))
                || ComplexNumberIsCloseToZeroForTesting(e38.getCoefficient(44))
                || !ComplexNumberIsCloseToZeroForTesting(e38.getCoefficient(0))
                || !ComplexNumberIsCloseToZeroForTesting(e38.getCoefficient(4))
                || !ComplexNumberIsCloseToZeroForTesting(e38.getCoefficient(24))
                || !ComplexNumberIsCloseToZeroForTesting(e38.getCoefficient(28))) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements30to38Basics: element 38 spot support failed"
            );
            allTestsOkay = false;
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhFourierElements30to38Basics() Okay");
        }
    }
    
    /*
     * TestOhFourierElements39to47Basics
     *
     * This method tests the translated Fourier-basis elements 39 through 47.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * This is the last translated 9-element block. Once this passes, the
     * translated nontrivial Fourier basis 0 through 47 is fully in place,
     * and then we can move on to the heavier algebraic tests.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * Element 39:
     *     - purely real
     *     - nonzero on 32 slots
     *
     * Elements 40, 42, 43, 45:
     *     - purely real
     *     - nonzero on 20 slots each
     *
     * Elements 41, 44, 46:
     *     - purely imaginary
     *     - nonzero on 20 slots each
     *
     * Element 47:
     *     - purely real
     *     - nonzero on 30 slots
     *
     * We also check a few visible support anchors from the uploaded table.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhFourierElements39to47Basics() {

        boolean allTestsOkay = true;

        OhElement e39 = OhFourierBasis.GetElement(39);
        OhElement e40 = OhFourierBasis.GetElement(40);
        OhElement e41 = OhFourierBasis.GetElement(41);
        OhElement e42 = OhFourierBasis.GetElement(42);
        OhElement e43 = OhFourierBasis.GetElement(43);
        OhElement e44 = OhFourierBasis.GetElement(44);
        OhElement e45 = OhFourierBasis.GetElement(45);
        OhElement e46 = OhFourierBasis.GetElement(46);
        OhElement e47 = OhFourierBasis.GetElement(47);

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Element 39 should be purely real with 32 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e39, 32)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 39 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Element 40 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e40, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 40 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Element 41 should be purely imaginary with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyImaginaryWithNonzeroCount(e41, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 41 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * Element 42 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e42, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 42 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Element 43 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e43, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 43 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 6:
         * Element 44 should be purely imaginary with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyImaginaryWithNonzeroCount(e44, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 44 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 7:
         * Element 45 should be purely real with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e45, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 45 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 8:
         * Element 46 should be purely imaginary with 20 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyImaginaryWithNonzeroCount(e46, 20)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 46 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 9:
         * Element 47 should be purely real with 30 nonzero coefficients
         * ------------------------------------------------------------
         */
        if (!OhElementIsPurelyRealWithNonzeroCount(e47, 30)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 47 basic structure failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 10:
         * Spot support anchors for element 39
         * ------------------------------------------------------------
         *
         * Visible from the uploaded table:
         * - nonzero at 0, 24, 12, 36, 18, 42
         * - zero at 4 and 28
         */
        if (ComplexNumberIsCloseToZeroForTesting(e39.getCoefficient(0))
                || ComplexNumberIsCloseToZeroForTesting(e39.getCoefficient(24))
                || ComplexNumberIsCloseToZeroForTesting(e39.getCoefficient(12))
                || ComplexNumberIsCloseToZeroForTesting(e39.getCoefficient(36))
                || ComplexNumberIsCloseToZeroForTesting(e39.getCoefficient(18))
                || ComplexNumberIsCloseToZeroForTesting(e39.getCoefficient(42))
                || !ComplexNumberIsCloseToZeroForTesting(e39.getCoefficient(4))
                || !ComplexNumberIsCloseToZeroForTesting(e39.getCoefficient(28))) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 39 spot support failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 11:
         * Spot support anchors for element 40
         * ------------------------------------------------------------
         *
         * Visible from the uploaded table:
         * - nonzero on class-2 slots and at 20, 21, 44, 45
         * - zero at 0 and 24
         */
        if (ComplexNumberIsCloseToZeroForTesting(e40.getCoefficient(4))
                || ComplexNumberIsCloseToZeroForTesting(e40.getCoefficient(20))
                || ComplexNumberIsCloseToZeroForTesting(e40.getCoefficient(21))
                || ComplexNumberIsCloseToZeroForTesting(e40.getCoefficient(44))
                || ComplexNumberIsCloseToZeroForTesting(e40.getCoefficient(45))
                || !ComplexNumberIsCloseToZeroForTesting(e40.getCoefficient(0))
                || !ComplexNumberIsCloseToZeroForTesting(e40.getCoefficient(24))) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 40 spot support failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 12:
         * Spot support anchors for element 41
         * ------------------------------------------------------------
         *
         * Visible from the uploaded table:
         * - nonzero on class-2 slots and at 16, 17, 40, 41
         * - zero at 0 and 24
         */
        if (ComplexNumberIsCloseToZeroForTesting(e41.getCoefficient(4))
                || ComplexNumberIsCloseToZeroForTesting(e41.getCoefficient(16))
                || ComplexNumberIsCloseToZeroForTesting(e41.getCoefficient(17))
                || ComplexNumberIsCloseToZeroForTesting(e41.getCoefficient(40))
                || ComplexNumberIsCloseToZeroForTesting(e41.getCoefficient(41))
                || !ComplexNumberIsCloseToZeroForTesting(e41.getCoefficient(0))
                || !ComplexNumberIsCloseToZeroForTesting(e41.getCoefficient(24))) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 41 spot support failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 13:
         * Spot support anchors for element 47
         * ------------------------------------------------------------
         *
         * Visible from the uploaded table:
         * - nonzero at 1, 3, 12, 20, 25, 27, 36, 44
         * - zero at 0, 4, 24, 28
         */
        if (ComplexNumberIsCloseToZeroForTesting(e47.getCoefficient(1))
                || ComplexNumberIsCloseToZeroForTesting(e47.getCoefficient(3))
                || ComplexNumberIsCloseToZeroForTesting(e47.getCoefficient(12))
                || ComplexNumberIsCloseToZeroForTesting(e47.getCoefficient(20))
                || ComplexNumberIsCloseToZeroForTesting(e47.getCoefficient(25))
                || ComplexNumberIsCloseToZeroForTesting(e47.getCoefficient(27))
                || ComplexNumberIsCloseToZeroForTesting(e47.getCoefficient(36))
                || ComplexNumberIsCloseToZeroForTesting(e47.getCoefficient(44))
                || !ComplexNumberIsCloseToZeroForTesting(e47.getCoefficient(0))
                || !ComplexNumberIsCloseToZeroForTesting(e47.getCoefficient(4))
                || !ComplexNumberIsCloseToZeroForTesting(e47.getCoefficient(24))
                || !ComplexNumberIsCloseToZeroForTesting(e47.getCoefficient(28))) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhFourierElements39to47Basics: element 47 spot support failed"
            );
            allTestsOkay = false;
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhFourierElements39to47Basics() Okay");
        }
    }
    
    /*
     * TestOhFourierBlockIdentities
     *
     * This method tests the most likely block-identity elements in the
     * translated Fourier basis.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * The previous test showed that the sums of whole translated ranges such as
     * 4..11 or 12..20 are not idempotent. That strongly suggests those ranges
     * are not projector sums, but rather matrix bases inside irreducible blocks.
     *
     * In that interpretation, the first element of each non-singlet block is a
     * natural candidate for the block identity.
     *
     * CANDIDATE BLOCK IDENTITIES
     * --------------------------
     * Based on the translation ordering:
     *
     *     0,1,2,3         = four singlets
     *     4 and 8         = the two doublet block identities
     *     12,21,30,39     = the four triplet block identities
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * 1. Each candidate block identity is idempotent.
     * 2. Distinct candidate block identities annihilate each other.
     * 3. Each block identity acts as identity on the translated elements in
     *    its own block.
     * 4. Each block identity annihilates translated elements outside its block.
     *
     * BLOCK ASSIGNMENTS USED HERE
     * ---------------------------
     * Singlets:
     *     0, 1, 2, 3
     *
     * Doublet blocks:
     *     identity 4  acts on 4..7
     *     identity 8  acts on 8..11
     *
     * Triplet blocks:
     *     identity 12 acts on 12..20
     *     identity 21 acts on 21..29
     *     identity 30 acts on 30..38
     *     identity 39 acts on 39..47
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhFourierBlockIdentities() {

        boolean allTestsOkay = true;

        /*
         * Load all 48 Fourier elements.
         */
        OhElement[] fourier = new OhElement[48];

        for (int index = 0; index < 48; index++) {
            fourier[index] = OhFourierBasis.GetElement(index);
        }

        OhElement zero = OhElement.zero();

        /*
         * The candidate block identities.
         */
        int[] identityIndices = {0, 1, 2, 3, 4, 8, 12, 21, 30, 39};

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Each candidate block identity should be idempotent
         * ------------------------------------------------------------
         */
        for (int arrayIndex = 0; arrayIndex < identityIndices.length; arrayIndex++) {

            int identityIndex = identityIndices[arrayIndex];
            OhElement square = fourier[identityIndex].multiply(fourier[identityIndex]);

            if (!OhElementsAreClose(square, fourier[identityIndex])) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhFourierBlockIdentities: idempotence failed for Fourier index "
                        + identityIndex
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Distinct candidate block identities should annihilate
         * ------------------------------------------------------------
         */
        for (int firstArrayIndex = 0; firstArrayIndex < identityIndices.length; firstArrayIndex++) {
            for (int secondArrayIndex = 0; secondArrayIndex < identityIndices.length; secondArrayIndex++) {

                if (firstArrayIndex == secondArrayIndex) {
                    continue;
                }

                int firstIdentityIndex = identityIndices[firstArrayIndex];
                int secondIdentityIndex = identityIndices[secondArrayIndex];

                OhElement firstTimesSecond =
                        fourier[firstIdentityIndex].multiply(fourier[secondIdentityIndex]);

                OhElement secondTimesFirst =
                        fourier[secondIdentityIndex].multiply(fourier[firstIdentityIndex]);

                if (!OhElementsAreClose(firstTimesSecond, zero)) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierBlockIdentities: left annihilation failed for Fourier identity indices "
                            + firstIdentityIndex + " and " + secondIdentityIndex
                    );
                    allTestsOkay = false;
                }

                if (!OhElementsAreClose(secondTimesFirst, zero)) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierBlockIdentities: right annihilation failed for Fourier identity indices "
                            + secondIdentityIndex + " and " + firstIdentityIndex
                    );
                    allTestsOkay = false;
                }
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 3 and Test 4:
         * Each block identity should act as identity on its own block,
         * and annihilate elements outside its block.
         * ------------------------------------------------------------
         */

        /*
         * Singlets: each singlet identity acts only on itself.
         */
        for (int singletIndex = 0; singletIndex <= 3; singletIndex++) {
            for (int targetIndex = 0; targetIndex < 48; targetIndex++) {

                OhElement leftProduct =
                        fourier[singletIndex].multiply(fourier[targetIndex]);

                OhElement rightProduct =
                        fourier[targetIndex].multiply(fourier[singletIndex]);

                if (targetIndex == singletIndex) {
                    if (!OhElementsAreClose(leftProduct, fourier[targetIndex])) {
                        System.out.println(
                                "%%%%%%%%%%%%%% Error: TestOhFourierBlockIdentities: singlet left identity action failed for indices "
                                + singletIndex + " and " + targetIndex
                        );
                        allTestsOkay = false;
                    }

                    if (!OhElementsAreClose(rightProduct, fourier[targetIndex])) {
                        System.out.println(
                                "%%%%%%%%%%%%%% Error: TestOhFourierBlockIdentities: singlet right identity action failed for indices "
                                + targetIndex + " and " + singletIndex
                        );
                        allTestsOkay = false;
                    }
                } else {
                    if (!OhElementsAreClose(leftProduct, zero)) {
                        System.out.println(
                                "%%%%%%%%%%%%%% Error: TestOhFourierBlockIdentities: singlet left annihilation-on-others failed for indices "
                                + singletIndex + " and " + targetIndex
                        );
                        allTestsOkay = false;
                    }

                    if (!OhElementsAreClose(rightProduct, zero)) {
                        System.out.println(
                                "%%%%%%%%%%%%%% Error: TestOhFourierBlockIdentities: singlet right annihilation-on-others failed for indices "
                                + targetIndex + " and " + singletIndex
                        );
                        allTestsOkay = false;
                    }
                }
            }
        }

        /*
         * Doublet block identity at 4 should act on 4..7 and annihilate others.
         */
        TestOneFourierBlockIdentityAction(4, 4, 7, fourier, zero);

        /*
         * Doublet block identity at 8 should act on 8..11 and annihilate others.
         */
        TestOneFourierBlockIdentityAction(8, 8, 11, fourier, zero);

        /*
         * Triplet block identities.
         */
        TestOneFourierBlockIdentityAction(12, 12, 20, fourier, zero);
        TestOneFourierBlockIdentityAction(21, 21, 29, fourier, zero);
        TestOneFourierBlockIdentityAction(30, 30, 38, fourier, zero);
        TestOneFourierBlockIdentityAction(39, 39, 47, fourier, zero);

        /*
         * NOTE:
         * The helper above prints its own searchable errors.
         * So if the helper found errors, we need a way to reflect that here.
         *
         * We do that by re-checking a small summary condition below:
         * every candidate identity should at least fix itself.
         */
        for (int arrayIndex = 0; arrayIndex < identityIndices.length; arrayIndex++) {
            int identityIndex = identityIndices[arrayIndex];

            OhElement leftSelf = fourier[identityIndex].multiply(fourier[identityIndex]);
            OhElement rightSelf = fourier[identityIndex].multiply(fourier[identityIndex]);

            if (!OhElementsAreClose(leftSelf, fourier[identityIndex])
                    || !OhElementsAreClose(rightSelf, fourier[identityIndex])) {
                allTestsOkay = false;
            }
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhFourierBlockIdentities() Okay");
        }
    }

    /*
     * TestOneFourierBlockIdentityAction
     *
     * Tests one candidate block identity against all 48 Fourier elements.
     *
     * EXPECTED BEHAVIOR
     * -----------------
     * For target indices inside the block:
     *
     *     e * x = x
     *     x * e = x
     *
     * For target indices outside the block:
     *
     *     e * x = 0
     *     x * e = 0
     *
     * IMPORTANT
     * ---------
     * This helper prints searchable errors directly. It does not return a
     * boolean so that the code remains straightforward and easy to inspect.
     */
    private static void TestOneFourierBlockIdentityAction(
            int identityIndex,
            int blockStart,
            int blockEnd,
            OhElement[] fourier,
            OhElement zero) {

        for (int targetIndex = 0; targetIndex < 48; targetIndex++) {

            OhElement leftProduct =
                    fourier[identityIndex].multiply(fourier[targetIndex]);

            OhElement rightProduct =
                    fourier[targetIndex].multiply(fourier[identityIndex]);

            boolean targetIsInsideBlock =
                    targetIndex >= blockStart && targetIndex <= blockEnd;

            if (targetIsInsideBlock) {
                if (!OhElementsAreClose(leftProduct, fourier[targetIndex])) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierBlockIdentities: left identity action failed for Fourier identity index "
                            + identityIndex + " on target index " + targetIndex
                    );
                }

                if (!OhElementsAreClose(rightProduct, fourier[targetIndex])) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierBlockIdentities: right identity action failed for Fourier identity index "
                            + identityIndex + " on target index " + targetIndex
                    );
                }
            } else {
                if (!OhElementsAreClose(leftProduct, zero)) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierBlockIdentities: left annihilation outside block failed for Fourier identity index "
                            + identityIndex + " on target index " + targetIndex
                    );
                }

                if (!OhElementsAreClose(rightProduct, zero)) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhFourierBlockIdentities: right annihilation outside block failed for Fourier identity index "
                            + identityIndex + " on target index " + targetIndex
                    );
                }
            }
        }
    }

    /*
     * SumFourierRange
     *
     * Returns the sum of Fourier-basis elements from startIndex through endIndex,
     * inclusive.
     *
     * WHY THIS EXISTS
     * ---------------
     * Many of the first algebraic tests are naturally phrased in terms of
     * sums of consecutive translated Fourier blocks.
     */
    private static OhElement SumFourierRange(int startIndex, int endIndex) {

        OhElement sum = OhElement.zero();

        for (int index = startIndex; index <= endIndex; index++) {
            sum = sum.add(OhFourierBasis.GetElement(index));
        }

        return sum;
    }    
    
    /*
     * TestOhFourierTripletIdentityDiagnostics
     *
     * This method performs a diagnostic study of the four candidate triplet
     * block-identity elements:
     *
     *     12, 21, 30, 39
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * The previous algebraic test showed that these four candidates are not
     * idempotent, even though the singlets and doublets behave correctly.
     *
     * The most likely simple explanation is a scaling problem:
     *
     *     e * e = c * e
     *
     * for some scalar c different from 1.
     *
     * This method checks exactly that possibility.
     *
     * WHAT THIS TEST DOES
     * -------------------
     * For each candidate e:
     *
     * 1. Compute e * e.
     * 2. Search for a complex scalar c such that e*e is close to c*e.
     * 3. Print the best-fit scalar.
     * 4. If the fit is poor, print a warning.
     *
     * OUTPUT STYLE
     * ------------
     * This is intentionally a diagnostic test, so it prints useful information
     * even on success.
     */
    private static void TestOhFourierTripletIdentityDiagnostics() {

        int[] candidateIndices = {12, 21, 30, 39};

        System.out.println("*** TestOhFourierTripletIdentityDiagnostics() starting");

        for (int arrayIndex = 0; arrayIndex < candidateIndices.length; arrayIndex++) {

            int fourierIndex = candidateIndices[arrayIndex];

            OhElement candidate = OhFourierBasis.GetElement(fourierIndex);
            OhElement square = candidate.multiply(candidate);

            ComplexNumber bestScale = EstimateBestComplexScale(candidate, square);
            OhElement scaledCandidate = MultiplyOhElementByComplex(candidate, bestScale);

            boolean scalingFitIsGood = OhElementsAreClose(square, scaledCandidate);

            System.out.println(
                    "Triplet candidate " + fourierIndex
                    + " best scaling coefficient c = "
                    + bestScale
            );

            if (scalingFitIsGood) {
                System.out.println(
                        "*** Triplet candidate " + fourierIndex
                        + " appears to satisfy e*e = c*e"
                );
            } else {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhFourierTripletIdentityDiagnostics: candidate "
                        + fourierIndex
                        + " is not well described by simple scaling"
                );
            }

            /*
             * Optional detailed printout for visual inspection.
             */
            System.out.println("Candidate element " + fourierIndex + ":");
            System.out.println(candidate.toClassGroupedString());
            System.out.println();

            System.out.println("Candidate squared " + fourierIndex + ":");
            System.out.println(square.toClassGroupedString());
            System.out.println();

            System.out.println("Best-scaled candidate " + fourierIndex + ":");
            System.out.println(scaledCandidate.toClassGroupedString());
            System.out.println();
        }

        System.out.println("*** TestOhFourierTripletIdentityDiagnostics() completed");
    }

    /*
     * EstimateBestComplexScale
     *
     * Given two OhElement objects source and target, this helper estimates the
     * complex scalar c that best fits:
     *
     *     target ~= c * source
     *
     * METHOD
     * ------
     * Since these are sparse algebra elements with complex coefficients, the
     * simplest robust approach is:
     *
     * - find the first source coefficient that is not zero
     * - compute target/source at that slot
     * - use that as the candidate scale
     *
     * This is sufficient for our present diagnostic purpose.
     */
    private static ComplexNumber EstimateBestComplexScale(
            OhElement source,
            OhElement target) {

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {

            ComplexNumber sourceCoefficient = source.getCoefficient(index);

            if (!ComplexNumberIsCloseToZeroForTesting(sourceCoefficient)) {

                ComplexNumber targetCoefficient = target.getCoefficient(index);
                return targetCoefficient.divide(sourceCoefficient);
            }
        }

        /*
         * If source were zero, something would be badly wrong.
         */
        throw new IllegalStateException(
                "EstimateBestComplexScale: source element appears to be zero."
        );
    }

    /*
     * MultiplyOhElementByComplex
     *
     * Multiplies every coefficient of an OhElement by one ComplexNumber.
     *
     * WHY THIS EXISTS
     * ---------------
     * We need this helper for the scaling diagnostic:
     *
     *     compare e*e with c*e
     */
    private static OhElement MultiplyOhElementByComplex(
            OhElement value,
            ComplexNumber scalar) {

        ComplexNumber[] newCoefficients =
                new ComplexNumber[OhElement.getNumberOfBasisElements()];

        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {
            newCoefficients[index] =
                    scalar.multiply(value.getCoefficient(index));
        }

        return new OhElement(newCoefficients);
    }    
    
    /*
     * TestOhAllBasisProducts
     *
     * This method exhaustively tests all 48 x 48 products of Oh basis elements.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * The Oh multiplication table is the heart of the project.
     *
     * Since there are only 48 basis elements, it is completely practical to
     * test every ordered pair:
     *
     *     g_i * g_j
     *
     * on every program run.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * For every ordered pair of basis indices (i, j):
     *
     * 1. The product basisElement(i) * basisElement(j) is again a pure basis element.
     * 2. The resulting basis index agrees with the table entry stored in OhElement.
     *
     * In addition, this test also checks:
     *
     * 3. Identity on the left for all basis elements.
     * 4. Identity on the right for all basis elements.
     * 5. Inverse products on both sides for all basis elements.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhAllBasisProducts() {

        boolean allTestsOkay = true;

        int numberOfBasisElements = OhElement.getNumberOfBasisElements();

        OhElement identity = OhElement.identity();

        /*
         * ------------------------------------------------------------
         * Test 1 and Test 2:
         * Exhaustive sweep of all ordered basis products
         * ------------------------------------------------------------
         */
        for (int leftIndex = 0; leftIndex < numberOfBasisElements; leftIndex++) {
            for (int rightIndex = 0; rightIndex < numberOfBasisElements; rightIndex++) {

                OhElement leftBasisElement = OhElement.basisElement(leftIndex);
                OhElement rightBasisElement = OhElement.basisElement(rightIndex);

                OhElement product = leftBasisElement.multiply(rightBasisElement);

                int productIndex;

                try {
                    productIndex = FindSingleBasisIndexInOhElement(product);
                } catch (Exception exception) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhAllBasisProducts: product was not a pure basis element for indices "
                            + leftIndex + " and " + rightIndex
                    );
                    allTestsOkay = false;
                    continue;
                }

                /*
                 * Since basis-element multiplication is supposed to be exactly
                 * table-driven, compare the actual resulting index with the
                 * table entry exposed through basis-element metadata:
                 *
                 * We do this by multiplying the pure basis elements and
                 * checking that the answer is stable and unambiguous.
                 *
                 * For now, because OhElement does not expose the raw table
                 * entry directly, this test treats the unique basis result as
                 * the main expected behavior.
                 *
                 * To make the test stronger, we also verify the result by
                 * comparing against a second multiplication path:
                 *
                 *     basisElement(productIndex)
                 */
                OhElement reconstructedProduct =
                        OhElement.basisElement(productIndex);

                if (!OhElementsAreClose(product, reconstructedProduct)) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhAllBasisProducts: reconstructed basis product mismatch for indices "
                            + leftIndex + " and " + rightIndex
                    );
                    allTestsOkay = false;
                }
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 3 and Test 4:
         * Identity on the left and right for all basis elements
         * ------------------------------------------------------------
         */
        for (int basisIndex = 0; basisIndex < numberOfBasisElements; basisIndex++) {

            OhElement currentBasisElement = OhElement.basisElement(basisIndex);

            OhElement leftIdentityProduct = identity.multiply(currentBasisElement);
            OhElement rightIdentityProduct = currentBasisElement.multiply(identity);

            if (!OhElementsAreClose(leftIdentityProduct, currentBasisElement)) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhAllBasisProducts: left identity failed at basis index "
                        + basisIndex
                );
                allTestsOkay = false;
            }

            if (!OhElementsAreClose(rightIdentityProduct, currentBasisElement)) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhAllBasisProducts: right identity failed at basis index "
                        + basisIndex
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Inverse products on both sides for all basis elements
         * ------------------------------------------------------------
         */
        for (int basisIndex = 0; basisIndex < numberOfBasisElements; basisIndex++) {

            OhBasisElement basisInfo = OhElement.getBasisElementInfo(basisIndex);
            int inverseIndex = basisInfo.getInverseElementIndex();

            OhElement basisElement = OhElement.basisElement(basisIndex);
            OhElement inverseElement = OhElement.basisElement(inverseIndex);

            OhElement leftInverseProduct = basisElement.multiply(inverseElement);
            OhElement rightInverseProduct = inverseElement.multiply(basisElement);

            if (!OhElementsAreClose(leftInverseProduct, identity)) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhAllBasisProducts: element times inverse failed at basis index "
                        + basisIndex
                );
                allTestsOkay = false;
            }

            if (!OhElementsAreClose(rightInverseProduct, identity)) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhAllBasisProducts: inverse times element failed at basis index "
                        + basisIndex
                );
                allTestsOkay = false;
            }
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhAllBasisProducts() Okay");
        }
    }

    /*
     * TestOhPermutationElement
     *
     * This method tests the new OhPermutationElement class.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * We intend to use OhPermutationElement as the new trustworthy foundation
     * for defining the Oh multiplication table from permutation notation.
     *
     * Before we let it do that, we should test it carefully on:
     *
     * - parsing
     * - canonical label output
     * - identity
     * - inversion
     * - and a collection of explicit products
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * 1. Round-trip parse/print for representative labels.
     * 2. Identity element behavior.
     * 3. Inversion element behavior.
     * 4. A set of explicit multiplication examples.
     * 5. Basic equality behavior.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhPermutationElement() {

        boolean allTestsOkay = true;

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Round-trip parse/print for representative labels
         * ------------------------------------------------------------
         *
         * We deliberately choose examples from:
         *
         * - identity
         * - transpositions
         * - 3-cycles
         * - 4-cycles
         * - disjoint transpositions
         * - and inverted versions
         */
        String[] representativeLabels = {
            "()",
            "(12)",
            "(123)",
            "(1234)",
            "(12)(34)",
            "()i",
            "(12)i",
            "(123)i",
            "(1234)i",
            "(12)(34)i"
        };

        for (int index = 0; index < representativeLabels.length; index++) {

            String originalLabel = representativeLabels[index];

            OhPermutationElement parsedElement =
                    OhPermutationElement.fromLabel(originalLabel);

            String reconstructedLabel = parsedElement.toLabel();

            if (!reconstructedLabel.equals(originalLabel)) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhPermutationElement: round-trip label failed for "
                        + originalLabel
                        + ", got "
                        + reconstructedLabel
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Identity element behavior
         * ------------------------------------------------------------
         */
        OhPermutationElement identity = OhPermutationElement.identity();
        OhPermutationElement sampleFourCycle =
                OhPermutationElement.fromLabel("(1234)");

        if (!identity.multiply(sampleFourCycle).equals(sampleFourCycle)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: left identity failed"
            );
            allTestsOkay = false;
        }

        if (!sampleFourCycle.multiply(identity).equals(sampleFourCycle)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: right identity failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Inversion element behavior
         * ------------------------------------------------------------
         *
         * Pure inversion should square to the identity.
         */
        OhPermutationElement inversion = OhPermutationElement.inversion();

        if (!inversion.multiply(inversion).equals(identity)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: inversion square failed"
            );
            allTestsOkay = false;
        }

        /*
         * Also check that multiplying by inversion appends i for a simple case.
         */
        OhPermutationElement transposition =
                OhPermutationElement.fromLabel("(12)");

        OhPermutationElement transpositionTimesInversion =
                transposition.multiply(inversion);

        if (!transpositionTimesInversion.toLabel().equals("(12)i")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: transposition times inversion failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * Explicit multiplication examples
         * ------------------------------------------------------------
         *
         * These are basic group-theory sanity checks.
         *
         * IMPORTANT
         * ---------
         * The multiplication convention used by OhPermutationElement is:
         *
         *     left followed by right
         *
         * meaning:
         *
         *     result = right ∘ left
         */

        /*
         * Example A:
         *
         *     (12) * (12) = ()
         */
        if (!OhPermutationElement.fromLabel("(12)")
                .multiply(OhPermutationElement.fromLabel("(12)"))
                .toLabel().equals("()")) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: explicit product A failed"
            );
            allTestsOkay = false;
        }

        /*
         * Example B:
         *
         *     (123) * (132) = ()
         */
        if (!OhPermutationElement.fromLabel("(123)")
                .multiply(OhPermutationElement.fromLabel("(132)"))
                .toLabel().equals("()")) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: explicit product B failed"
            );
            allTestsOkay = false;
        }

        /*
         * Example C:
         *
         *     (1234) * (1432) = ()
         */
        if (!OhPermutationElement.fromLabel("(1234)")
                .multiply(OhPermutationElement.fromLabel("(1432)"))
                .toLabel().equals("()")) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: explicit product C failed"
            );
            allTestsOkay = false;
        }

        /*
         * Example D:
         *
         *     (12)(34) * (12)(34) = ()
         */
        if (!OhPermutationElement.fromLabel("(12)(34)")
                .multiply(OhPermutationElement.fromLabel("(12)(34)"))
                .toLabel().equals("()")) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: explicit product D failed"
            );
            allTestsOkay = false;
        }

        /*
         * Example E:
         *
         *     (12)(34) * (34) = (12)
         *
         * This is the sort of example the user explicitly wanted checked.
         */
        if (!OhPermutationElement.fromLabel("(12)(34)")
                .multiply(OhPermutationElement.fromLabel("(34)"))
                .toLabel().equals("(12)")) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: explicit product E failed"
            );
            allTestsOkay = false;
        }

        /*
         * Example F:
         *
         *     (12) * ()i = (12)i
         */
        if (!OhPermutationElement.fromLabel("(12)")
                .multiply(OhPermutationElement.fromLabel("()i"))
                .toLabel().equals("(12)i")) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: explicit product F failed"
            );
            allTestsOkay = false;
        }

        /*
         * Example G:
         *
         *     ()i * ()i = ()
         */
        if (!OhPermutationElement.fromLabel("()i")
                .multiply(OhPermutationElement.fromLabel("()i"))
                .toLabel().equals("()")) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: explicit product G failed"
            );
            allTestsOkay = false;
        }

        /*
         * Example H:
         *
         *     (123)i * (132)i = ()
         *
         * because the permutation part multiplies to identity and the two
         * inversion flags cancel.
         */
        if (!OhPermutationElement.fromLabel("(123)i")
                .multiply(OhPermutationElement.fromLabel("(132)i"))
                .toLabel().equals("()")) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: explicit product H failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Equality behavior
         * ------------------------------------------------------------
         */
        OhPermutationElement parsedAgain =
                OhPermutationElement.fromLabel("(1234)");

        if (!sampleFourCycle.equals(parsedAgain)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: equality failed for identical elements"
            );
            allTestsOkay = false;
        }

        if (sampleFourCycle.equals(OhPermutationElement.fromLabel("(1432)"))) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationElement: equality failed for distinct elements"
            );
            allTestsOkay = false;
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhPermutationElement() Okay");
        }
    }

        /*
     * PrintOhBasisElementLabels
     *
     * Prints the 48 Oh basis elements in basis-index order, using their
     * permutation labels.
     *
     * WHY THIS METHOD EXISTS
     * ----------------------
     * Before rebuilding more infrastructure from the basis ordering, it is
     * useful to print the current basis labels in order and inspect them
     * by eye.
     *
     * FORMAT
     * ------
     * We print:
     *
     *     index:label
     *
     * with 8 entries per line.
     */
    private static void PrintOhBasisElementLabels() {

        System.out.println("*** Oh basis element labels in index order");

        int numberOfBasisElements = OhElement.getNumberOfBasisElements();

        for (int index = 0; index < numberOfBasisElements; index++) {

            String label = OhElement.getBasisElementInfo(index).getPermutationLabel();

            /*
             * Print one entry.
             */
            System.out.printf("%2d:%-10s", index, label);

            /*
             * Start a new line after every 8 entries.
             */
            if ((index + 1) % 8 == 0) {
                System.out.println();
            } else {
                System.out.print("  ");
            }
        }

        /*
         * In case the number of entries were not a multiple of 8,
         * finish with a newline.
         */
        if (numberOfBasisElements % 8 != 0) {
            System.out.println();
        }

        System.out.println("*** End Oh basis element labels");
    }

    /*
     * OhElementIsConstantOnClasses
     *
     * Returns true exactly when the coefficients of the supplied OhElement are
     * constant on each conjugacy class.
     */
    private static boolean OhElementIsConstantOnClasses(OhElement value) {

        for (int classIndex = 0; classIndex < 10; classIndex++) {

            ComplexNumber referenceCoefficient = null;

            for (int elementIndex = 0; elementIndex < OhElement.getNumberOfBasisElements(); elementIndex++) {

                OhBasisElement basisInfo = OhElement.getBasisElementInfo(elementIndex);

                if (basisInfo.getClassIndex() == classIndex) {

                    ComplexNumber currentCoefficient = value.getCoefficient(elementIndex);

                    if (referenceCoefficient == null) {
                        referenceCoefficient = currentCoefficient;
                    } else {
                        if (!ComplexNumbersAreClose(referenceCoefficient, currentCoefficient)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /*
     * TestOhCharacterTableIrreps
     *
     * This method builds the 10 central idempotents from the Oh character table
     * and tests their basic algebra.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * Now that the group structure is finally on a clean permutation-first
     * foundation, we can test the irreducible-projector elements obtained from
     * the character table.
     *
     * For an irreducible character chi, the central idempotent is:
     *
     *     e_chi = (chi(E) / |G|) * sum_g chi(g^{-1}) g
     *
     * Since the characters in this table are real and constant on classes,
     * and |G| = 48, we implement this as a class-sum combination.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * 1. Each character-table element is idempotent.
     * 2. Distinct character-table elements annihilate each other.
     * 3. The sum of all 10 character-table elements is the identity basis element.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhCharacterTableIrreps() {

        boolean allTestsOkay = true;

        OhElement[] irreps = BuildOhCharacterTableIrreps();
        OhElement zero = OhElement.zero();
        OhElement identity = OhElement.identity();

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Each irrep idempotent should square to itself
         * ------------------------------------------------------------
         */
        for (int irrepIndex = 0; irrepIndex < irreps.length; irrepIndex++) {

            OhElement square = irreps[irrepIndex].multiply(irreps[irrepIndex]);

            if (!OhElementsAreClose(square, irreps[irrepIndex])) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhCharacterTableIrreps: idempotence failed for irrep index "
                        + irrepIndex
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Distinct irrep idempotents should annihilate one another
         * ------------------------------------------------------------
         */
        for (int firstIndex = 0; firstIndex < irreps.length; firstIndex++) {
            for (int secondIndex = 0; secondIndex < irreps.length; secondIndex++) {

                if (firstIndex == secondIndex) {
                    continue;
                }

                OhElement leftTimesRight =
                        irreps[firstIndex].multiply(irreps[secondIndex]);

                OhElement rightTimesLeft =
                        irreps[secondIndex].multiply(irreps[firstIndex]);

                if (!OhElementsAreClose(leftTimesRight, zero)) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhCharacterTableIrreps: left annihilation failed for irrep indices "
                            + firstIndex + " and " + secondIndex
                    );
                    allTestsOkay = false;
                }

                if (!OhElementsAreClose(rightTimesLeft, zero)) {
                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhCharacterTableIrreps: right annihilation failed for irrep indices "
                            + secondIndex + " and " + firstIndex
                    );
                    allTestsOkay = false;
                }
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Sum of all 10 irreps should be the identity element
         * ------------------------------------------------------------
         */
        OhElement irrepSum = OhElement.zero();

        for (int irrepIndex = 0; irrepIndex < irreps.length; irrepIndex++) {
            irrepSum = irrepSum.add(irreps[irrepIndex]);
        }

        if (!OhElementsAreClose(irrepSum, identity)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhCharacterTableIrreps: sum of irreps failed to equal identity"
            );
            allTestsOkay = false;
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhCharacterTableIrreps() Okay");
        }
    }

    /*
     * BuildOhCharacterTableIrreps
     *
     * Builds the 10 central idempotents from the Oh character table.
     *
     * CLASS ORDER
     * -----------
     * The class order used here is the project's sovereign class order:
     *
     * 0: ()
     * 1: (12)(34)
     * 2: 3-cycles
     * 3: 4-cycles
     * 4: transpositions
     * 5: ()i
     * 6: (12)(34)i
     * 7: 3-cycles with i
     * 8: 4-cycles with i
     * 9: transpositions with i
     *
     * CHARACTER TABLE CONVENTION
     * --------------------------
     * Each row is one irreducible character in that class order.
     *
     * The dimensions are given by the value at the identity class, column 0.
     */
    private static OhElement[] BuildOhCharacterTableIrreps() {

        /*
         * Character table rows in sovereign class order.
         *
         * The row ordering used here is:
         *
         * 0  A1g
         * 1  A1u
         * 2  A2g
         * 3  A2u
         * 4  Eg
         * 5  Eu
         * 6  T1g
         * 7  T1u
         * 8  T2g
         * 9  T2u
         *
         * If your naming convention differs, the algebraic tests do not care.
         */
        double[][] characterTable = {
            { 1,  1,  1,  1,  1,  1,  1,  1,  1,  1},
            { 1,  1,  1,  1,  1, -1, -1, -1, -1, -1},
            { 1,  1,  1, -1, -1,  1,  1,  1, -1, -1},
            { 1,  1,  1, -1, -1, -1, -1, -1,  1,  1},
            { 2,  2, -1,  0,  0,  2,  2, -1,  0,  0},
            { 2,  2, -1,  0,  0, -2, -2,  1,  0,  0},
            { 3, -1,  0,  1, -1,  3, -1,  0,  1, -1},
            { 3, -1,  0,  1, -1, -3,  1,  0, -1,  1},
            { 3, -1,  0, -1,  1,  3, -1,  0, -1,  1},
            { 3, -1,  0, -1,  1, -3,  1,  0,  1, -1}
        };

        /*
         * Class sizes in sovereign class order.
         */
        int[] classSizes = {1, 3, 8, 6, 6, 1, 3, 8, 6, 6};

        OhElement[] irreps = new OhElement[10];

        for (int irrepIndex = 0; irrepIndex < 10; irrepIndex++) {

            double dimension = characterTable[irrepIndex][0];

            ComplexNumber[] coefficients =
                    new ComplexNumber[OhElement.getNumberOfBasisElements()];

            for (int basisIndex = 0; basisIndex < OhElement.getNumberOfBasisElements(); basisIndex++) {

                int classIndex = OhElement.getBasisElementInfo(basisIndex).getClassIndex();

                /*
                 * Since characters are real here, chi(g^{-1}) = chi(g).
                 *
                 * Each basis element in a given class gets the same coefficient:
                 *
                 *     dim * chi(class) / 48
                 */
                double coefficientValue =
                        dimension * characterTable[irrepIndex][classIndex] / 48.0;

                coefficients[basisIndex] = new ComplexNumber(coefficientValue, 0.0);
            }

            irreps[irrepIndex] = new OhElement(coefficients);
        }

        return irreps;
    }
    
    /*
     * TestOhPauliAndGellMannBlockClosure
     *
     * This method tests the first algebraic property we want from the
     * hand-entered Pauli and Gell-Mann blocks:
     *
     * - closure inside each block
     * - annihilation between different blocks
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * Before we worry about exact Pauli or Gell-Mann structure constants,
     * we should first verify the simpler block structure:
     *
     * - elements from one irreducible block should multiply back into that block
     * - elements from different irreducible blocks should annihilate
     *
     * That is the most natural first validation of the hand-input basis.
     */
    private static void TestOhPauliAndGellMannBlockClosure() {

        boolean allTestsOkay = true;

        OhElement[][] blocks = {
            OhFourierBasis.GetDoublet1PauliBlock(),
            OhFourierBasis.GetDoublet2PauliBlock(),
            OhFourierBasis.GetTriplet1GellMannBlock(),
            OhFourierBasis.GetTriplet2GellMannBlock(),
            OhFourierBasis.GetTriplet3GellMannBlock(),
            OhFourierBasis.GetTriplet4GellMannBlock()
        };

        String[] blockNames = {
            "Doublet1",
            "Doublet2",
            "Triplet1",
            "Triplet2",
            "Triplet3",
            "Triplet4"
        };

        OhElement zero = OhElement.zero();

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Closure inside each block
         * ------------------------------------------------------------
         */
        for (int blockIndex = 0; blockIndex < blocks.length; blockIndex++) {

            OhElement[] block = blocks[blockIndex];
            OhElement blockSpan = SumOhElements(block);

            for (int leftIndex = 0; leftIndex < block.length; leftIndex++) {
                for (int rightIndex = 0; rightIndex < block.length; rightIndex++) {

                    OhElement product = block[leftIndex].multiply(block[rightIndex]);

                    if (!OhElementLiesInSpan(product, block)) {
                        System.out.println(
                                "%%%%%%%%%%%%%% Error: TestOhPauliAndGellMannBlockClosure: closure failed in block "
                                + blockNames[blockIndex]
                                + " for local indices " + leftIndex + " and " + rightIndex
                        );
                        allTestsOkay = false;
                    }
                }
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Different blocks should annihilate
         * ------------------------------------------------------------
         */
        for (int firstBlock = 0; firstBlock < blocks.length; firstBlock++) {
            for (int secondBlock = 0; secondBlock < blocks.length; secondBlock++) {

                if (firstBlock == secondBlock) {
                    continue;
                }

                for (int firstLocal = 0; firstLocal < blocks[firstBlock].length; firstLocal++) {
                    for (int secondLocal = 0; secondLocal < blocks[secondBlock].length; secondLocal++) {

                        OhElement leftTimesRight =
                                blocks[firstBlock][firstLocal].multiply(blocks[secondBlock][secondLocal]);

                        OhElement rightTimesLeft =
                                blocks[secondBlock][secondLocal].multiply(blocks[firstBlock][firstLocal]);

                        if (!OhElementsAreClose(leftTimesRight, zero)) {
                            System.out.println(
                                    "%%%%%%%%%%%%%% Error: TestOhPauliAndGellMannBlockClosure: cross-block left annihilation failed for "
                                    + blockNames[firstBlock] + " and " + blockNames[secondBlock]
                            );
                            allTestsOkay = false;
                        }

                        if (!OhElementsAreClose(rightTimesLeft, zero)) {
                            System.out.println(
                                    "%%%%%%%%%%%%%% Error: TestOhPauliAndGellMannBlockClosure: cross-block right annihilation failed for "
                                    + blockNames[secondBlock] + " and " + blockNames[firstBlock]
                            );
                            allTestsOkay = false;
                        }
                    }
                }
            }
        }

        if (allTestsOkay) {
            System.out.println("*** TestOhPauliAndGellMannBlockClosure() Okay");
        }
    }

    /*
     * SumOhElements
     *
     * Returns the sum of an array of OhElement objects.
     */
    private static OhElement SumOhElements(OhElement[] elements) {

        OhElement sum = OhElement.zero();

        for (int index = 0; index < elements.length; index++) {
            sum = sum.add(elements[index]);
        }

        return sum;
    }

    /*
     * OhElementLiesInSpan
     *
     * For now, this helper checks whether an OhElement lies in the span of a
     * supplied block by using the block identity as a projector.
     *
     * IMPORTANT
     * ---------
     * This assumes the first element of the block is the identity-like element
     * for that block, which is exactly the structural assumption we are testing.
     */
    private static boolean OhElementLiesInSpan(OhElement value, OhElement[] block) {

        OhElement blockIdentity = block[0];

        OhElement leftProjection = blockIdentity.multiply(value);
        OhElement rightProjection = value.multiply(blockIdentity);

        return OhElementsAreClose(leftProjection, value)
                && OhElementsAreClose(rightProjection, value);
    }    
    
    /*
     * TestSUN
     *
     * This method tests SU(2) / SU(3)-type multiplication behavior for the
     * hand-entered Fourier-basis blocks.
     *
     * CURRENT SCOPE
     * -------------
     * For now, this method tests only the first SU(2)-type block:
     *
     *     I  = Fourier element 4
     *     sx = Fourier element 5
     *     sy = Fourier element 6
     *     sz = Fourier element 7
     *
     * LATER PLAN
     * ----------
     * If this works, the same method can be extended to:
     *
     * - the second SU(2) block
     * - the SU(3) blocks
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * 1. I is idempotent.
     * 2. I acts as identity on sx, sy, sz.
     * 3. sx^2 = sy^2 = sz^2 = I.
     * 4. sx sy =  i sz
     * 5. sy sz =  i sx
     * 6. sz sx =  i sy
     * 7. sy sx = -i sz
     * 8. sz sy = -i sx
     * 9. sx sz = -i sy
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    /*
     * TestSUN
     *
     * This method tests SU(2) / SU(3)-type multiplication behavior for the
     * hand-entered Fourier-basis blocks.
     *
     * CURRENT SCOPE
     * -------------
     * For now, this method tests:
     *
     * - the first SU(2)-type block
     * - the second SU(2)-type block
     *
     * LATER PLAN
     * ----------
     * If this works, the same method can be extended to the SU(3) blocks.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    /*
     * TestSUN
     *
     * This method tests SU(2) / SU(3)-type multiplication behavior for the
     * hand-entered Fourier-basis blocks.
     *
     * CURRENT SCOPE
     * -------------
     * - first SU(2) block
     * - second SU(2) block
     * - first SU(3) block
     */
    /*
     * TestSUN
     *
     * This method tests SU(2) / SU(3)-type multiplication behavior for the
     * hand-entered Fourier-basis blocks.
     *
     * CURRENT SCOPE
     * -------------
     * - first SU(2) block
     * - second SU(2) block
     * - all four SU(3) blocks
     */
    private static void TestSUN() {

        boolean allTestsOkay = true;

        /*
         * ------------------------------------------------------------
         * First SU(2) block
         * ------------------------------------------------------------
         */
        if (!TestOneSU2Block(
                OhFourierBasis.GetDoublet1Identity(),
                OhFourierBasis.GetDoublet1SigmaX(),
                OhFourierBasis.GetDoublet1SigmaY(),
                OhFourierBasis.GetDoublet1SigmaZ(),
                "first SU(2) block")) {
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Second SU(2) block
         * ------------------------------------------------------------
         */
        if (!TestOneSU2Block(
                OhFourierBasis.GetDoublet2Identity(),
                OhFourierBasis.GetDoublet2SigmaX(),
                OhFourierBasis.GetDoublet2SigmaY(),
                OhFourierBasis.GetDoublet2SigmaZ(),
                "second SU(2) block")) {
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * First SU(3) block
         * ------------------------------------------------------------
         */
        if (!TestOneSU3Block(
                OhFourierBasis.GetTriplet1Identity(),
                OhFourierBasis.GetTriplet1Lambda1(),
                OhFourierBasis.GetTriplet1Lambda2(),
                OhFourierBasis.GetTriplet1Lambda3(),
                OhFourierBasis.GetTriplet1Lambda4(),
                OhFourierBasis.GetTriplet1Lambda5(),
                OhFourierBasis.GetTriplet1Lambda6(),
                OhFourierBasis.GetTriplet1Lambda7(),
                OhFourierBasis.GetTriplet1Lambda8(),
                "first SU(3) block")) {
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Second SU(3) block
         * ------------------------------------------------------------
         */
        if (!TestOneSU3Block(
                OhFourierBasis.GetTriplet2Identity(),
                OhFourierBasis.GetTriplet2Lambda1(),
                OhFourierBasis.GetTriplet2Lambda2(),
                OhFourierBasis.GetTriplet2Lambda3(),
                OhFourierBasis.GetTriplet2Lambda4(),
                OhFourierBasis.GetTriplet2Lambda5(),
                OhFourierBasis.GetTriplet2Lambda6(),
                OhFourierBasis.GetTriplet2Lambda7(),
                OhFourierBasis.GetTriplet2Lambda8(),
                "second SU(3) block")) {
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Third SU(3) block
         * ------------------------------------------------------------
         */
        if (!TestOneSU3Block(
                OhFourierBasis.GetTriplet3Identity(),
                OhFourierBasis.GetTriplet3Lambda1(),
                OhFourierBasis.GetTriplet3Lambda2(),
                OhFourierBasis.GetTriplet3Lambda3(),
                OhFourierBasis.GetTriplet3Lambda4(),
                OhFourierBasis.GetTriplet3Lambda5(),
                OhFourierBasis.GetTriplet3Lambda6(),
                OhFourierBasis.GetTriplet3Lambda7(),
                OhFourierBasis.GetTriplet3Lambda8(),
                "third SU(3) block")) {
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Fourth SU(3) block
         * ------------------------------------------------------------
         */
        if (!TestOneSU3Block(
                OhFourierBasis.GetTriplet4Identity(),
                OhFourierBasis.GetTriplet4Lambda1(),
                OhFourierBasis.GetTriplet4Lambda2(),
                OhFourierBasis.GetTriplet4Lambda3(),
                OhFourierBasis.GetTriplet4Lambda4(),
                OhFourierBasis.GetTriplet4Lambda5(),
                OhFourierBasis.GetTriplet4Lambda6(),
                OhFourierBasis.GetTriplet4Lambda7(),
                OhFourierBasis.GetTriplet4Lambda8(),
                "fourth SU(3) block")) {
            allTestsOkay = false;
        }

        if (allTestsOkay) {
            System.out.println("*** TestSUN() Okay");
        }
    }

    /*
     * TestOneSU2Block
     *
     * Tests one SU(2)-type block with basis:
     *
     *     I, sigmaX, sigmaY, sigmaZ
     *
     * against the Pauli algebra relations.
     */
    private static boolean TestOneSU2Block(
            OhElement identity,
            OhElement sigmaX,
            OhElement sigmaY,
            OhElement sigmaZ,
            String blockName) {

        boolean blockOkay = true;

        if (!OhElementsAreClose(identity.multiply(identity), identity)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: identity idempotence failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(identity.multiply(sigmaX), sigmaX)
                || !OhElementsAreClose(sigmaX.multiply(identity), sigmaX)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: identity action on sigmaX failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(identity.multiply(sigmaY), sigmaY)
                || !OhElementsAreClose(sigmaY.multiply(identity), sigmaY)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: identity action on sigmaY failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(identity.multiply(sigmaZ), sigmaZ)
                || !OhElementsAreClose(sigmaZ.multiply(identity), sigmaZ)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: identity action on sigmaZ failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(sigmaX.multiply(sigmaX), identity)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: sigmaX squared failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(sigmaY.multiply(sigmaY), identity)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: sigmaY squared failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(sigmaZ.multiply(sigmaZ), identity)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: sigmaZ squared failed for " + blockName
            );
            blockOkay = false;
        }

        OhElement iSigmaZ      = MultiplyOhElementByComplex(sigmaZ, new ComplexNumber(0.0,  1.0));
        OhElement minusISigmaZ = MultiplyOhElementByComplex(sigmaZ, new ComplexNumber(0.0, -1.0));

        OhElement iSigmaX      = MultiplyOhElementByComplex(sigmaX, new ComplexNumber(0.0,  1.0));
        OhElement minusISigmaX = MultiplyOhElementByComplex(sigmaX, new ComplexNumber(0.0, -1.0));

        OhElement iSigmaY      = MultiplyOhElementByComplex(sigmaY, new ComplexNumber(0.0,  1.0));
        OhElement minusISigmaY = MultiplyOhElementByComplex(sigmaY, new ComplexNumber(0.0, -1.0));

        if (!OhElementsAreClose(sigmaX.multiply(sigmaY), iSigmaZ)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: sigmaX sigmaY = i sigmaZ failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(sigmaY.multiply(sigmaZ), iSigmaX)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: sigmaY sigmaZ = i sigmaX failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(sigmaZ.multiply(sigmaX), iSigmaY)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: sigmaZ sigmaX = i sigmaY failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(sigmaY.multiply(sigmaX), minusISigmaZ)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: sigmaY sigmaX = -i sigmaZ failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(sigmaZ.multiply(sigmaY), minusISigmaX)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: sigmaZ sigmaY = -i sigmaX failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(sigmaX.multiply(sigmaZ), minusISigmaY)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: sigmaX sigmaZ = -i sigmaY failed for " + blockName
            );
            blockOkay = false;
        }

        return blockOkay;
    }

    /*
     * TestOneSU3Block
     *
     * Tests one SU(3)-type block with basis:
     *
     *     I, lambda1, ..., lambda8
     *
     * CURRENT TEST SCOPE
     * ------------------
     * We test only a clean subset of the Gell-Mann algebra:
     *
     * 1. identity action
     * 2. lambda1^2 = lambda2^2 = lambda3^2 = (2/3)I + (1/sqrt(3))lambda8
     * 3. lambda8^2 = (2/3)I - (1/sqrt(3))lambda8
     * 4. lambda1 lambda2 =  i lambda3
     * 5. lambda2 lambda1 = -i lambda3
     * 6. lambda4 lambda5 =  i( 1/2 lambda3 + sqrt(3)/2 lambda8 )
     * 7. lambda5 lambda4 = -i( 1/2 lambda3 + sqrt(3)/2 lambda8 )
     * 8. lambda6 lambda7 =  i(-1/2 lambda3 + sqrt(3)/2 lambda8 )
     * 9. lambda7 lambda6 = -i(-1/2 lambda3 + sqrt(3)/2 lambda8 )
     */
    private static boolean TestOneSU3Block(
            OhElement identity,
            OhElement lambda1,
            OhElement lambda2,
            OhElement lambda3,
            OhElement lambda4,
            OhElement lambda5,
            OhElement lambda6,
            OhElement lambda7,
            OhElement lambda8,
            String blockName) {

        boolean blockOkay = true;

        double oneOverRootThree = 1.0 / Math.sqrt(3.0);
        double rootThreeOverTwo = Math.sqrt(3.0) / 2.0;

        /*
         * Identity action
         */
        OhElement[] generators = {
            lambda1, lambda2, lambda3, lambda4, lambda5, lambda6, lambda7, lambda8
        };

        for (int index = 0; index < generators.length; index++) {
            if (!OhElementsAreClose(identity.multiply(generators[index]), generators[index])
                    || !OhElementsAreClose(generators[index].multiply(identity), generators[index])) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestSUN: identity action failed in " + blockName
                );
                blockOkay = false;
                break;
            }
        }

        /*
         * lambda1^2 = lambda2^2 = lambda3^2 = (2/3)I + (1/sqrt(3))lambda8
         */
        OhElement expected123Square =
                AddOhElements(
                        MultiplyOhElementByComplex(identity, new ComplexNumber(2.0 / 3.0, 0.0)),
                        MultiplyOhElementByComplex(lambda8, new ComplexNumber(oneOverRootThree, 0.0)));

        if (!OhElementsAreClose(lambda1.multiply(lambda1), expected123Square)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: lambda1 squared failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(lambda2.multiply(lambda2), expected123Square)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: lambda2 squared failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(lambda3.multiply(lambda3), expected123Square)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: lambda3 squared failed for " + blockName
            );
            blockOkay = false;
        }

        /*
         * lambda8^2 = (2/3)I - (1/sqrt(3))lambda8
         */
        OhElement expected8Square =
                AddOhElements(
                        MultiplyOhElementByComplex(identity, new ComplexNumber(2.0 / 3.0, 0.0)),
                        MultiplyOhElementByComplex(lambda8, new ComplexNumber(-oneOverRootThree, 0.0)));

        if (!OhElementsAreClose(lambda8.multiply(lambda8), expected8Square)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: lambda8 squared failed for " + blockName
            );
            blockOkay = false;
        }

        /*
         * lambda1 lambda2 = i lambda3
         * lambda2 lambda1 = -i lambda3
         */
        OhElement iLambda3 =
                MultiplyOhElementByComplex(lambda3, new ComplexNumber(0.0, 1.0));
        OhElement minusILambda3 =
                MultiplyOhElementByComplex(lambda3, new ComplexNumber(0.0, -1.0));

        if (!OhElementsAreClose(lambda1.multiply(lambda2), iLambda3)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: lambda1 lambda2 = i lambda3 failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(lambda2.multiply(lambda1), minusILambda3)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: lambda2 lambda1 = -i lambda3 failed for " + blockName
            );
            blockOkay = false;
        }

        /*
         * lambda4 lambda5 = i( 1/2 lambda3 + sqrt(3)/2 lambda8 )
         */
        OhElement expected45 =
                MultiplyOhElementByComplex(
                        AddOhElements(
                                MultiplyOhElementByComplex(lambda3, new ComplexNumber(0.5, 0.0)),
                                MultiplyOhElementByComplex(lambda8, new ComplexNumber(rootThreeOverTwo, 0.0))),
                        new ComplexNumber(0.0, 1.0));

        OhElement expected54 =
                MultiplyOhElementByComplex(
                        AddOhElements(
                                MultiplyOhElementByComplex(lambda3, new ComplexNumber(0.5, 0.0)),
                                MultiplyOhElementByComplex(lambda8, new ComplexNumber(rootThreeOverTwo, 0.0))),
                        new ComplexNumber(0.0, -1.0));

        if (!OhElementsAreClose(lambda4.multiply(lambda5), expected45)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: lambda4 lambda5 failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(lambda5.multiply(lambda4), expected54)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: lambda5 lambda4 failed for " + blockName
            );
            blockOkay = false;
        }

        /*
         * lambda6 lambda7 = i( -1/2 lambda3 + sqrt(3)/2 lambda8 )
         */
        OhElement expected67 =
                MultiplyOhElementByComplex(
                        AddOhElements(
                                MultiplyOhElementByComplex(lambda3, new ComplexNumber(-0.5, 0.0)),
                                MultiplyOhElementByComplex(lambda8, new ComplexNumber(rootThreeOverTwo, 0.0))),
                        new ComplexNumber(0.0, 1.0));

        OhElement expected76 =
                MultiplyOhElementByComplex(
                        AddOhElements(
                                MultiplyOhElementByComplex(lambda3, new ComplexNumber(-0.5, 0.0)),
                                MultiplyOhElementByComplex(lambda8, new ComplexNumber(rootThreeOverTwo, 0.0))),
                        new ComplexNumber(0.0, -1.0));

        if (!OhElementsAreClose(lambda6.multiply(lambda7), expected67)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: lambda6 lambda7 failed for " + blockName
            );
            blockOkay = false;
        }

        if (!OhElementsAreClose(lambda7.multiply(lambda6), expected76)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestSUN: lambda7 lambda6 failed for " + blockName
            );
            blockOkay = false;
        }

        return blockOkay;
    }

    /*
     * AddOhElements
     *
     * Returns first + second.
     */
    private static OhElement AddOhElements(OhElement first, OhElement second) {
        return first.add(second);
    }

    /*
     * DebugSU3Block1
     *
     * Prints the first SU(3) block and the key products that failed in TestSUN().
     *
     * WHY THIS EXISTS
     * ---------------
     * The square relations passed, but the antisymmetric i[...] products failed.
     * That strongly suggests a sign or ordering convention issue rather than a
     * fundamental algebra failure.
     */
    private static void DebugSU3Block1() {

        OhElement identity = OhFourierBasis.GetTriplet1Identity();
        OhElement lambda1  = OhFourierBasis.GetTriplet1Lambda1();
        OhElement lambda2  = OhFourierBasis.GetTriplet1Lambda2();
        OhElement lambda3  = OhFourierBasis.GetTriplet1Lambda3();
        OhElement lambda4  = OhFourierBasis.GetTriplet1Lambda4();
        OhElement lambda5  = OhFourierBasis.GetTriplet1Lambda5();
        OhElement lambda6  = OhFourierBasis.GetTriplet1Lambda6();
        OhElement lambda7  = OhFourierBasis.GetTriplet1Lambda7();
        OhElement lambda8  = OhFourierBasis.GetTriplet1Lambda8();

        double rootThreeOverTwo = Math.sqrt(3.0) / 2.0;

        OhElement l1l2 = lambda1.multiply(lambda2);
        OhElement l2l1 = lambda2.multiply(lambda1);
        OhElement l4l5 = lambda4.multiply(lambda5);
        OhElement l5l4 = lambda5.multiply(lambda4);
        OhElement l6l7 = lambda6.multiply(lambda7);
        OhElement l7l6 = lambda7.multiply(lambda6);

        OhElement expected12 =
                MultiplyOhElementByComplex(lambda3, new ComplexNumber(0.0, 1.0));
        OhElement expected21 =
                MultiplyOhElementByComplex(lambda3, new ComplexNumber(0.0, -1.0));

        OhElement combo45 =
                AddOhElements(
                        MultiplyOhElementByComplex(lambda3, new ComplexNumber(0.5, 0.0)),
                        MultiplyOhElementByComplex(lambda8, new ComplexNumber(rootThreeOverTwo, 0.0)));

        OhElement expected45 =
                MultiplyOhElementByComplex(combo45, new ComplexNumber(0.0, 1.0));
        OhElement expected54 =
                MultiplyOhElementByComplex(combo45, new ComplexNumber(0.0, -1.0));

        OhElement combo67 =
                AddOhElements(
                        MultiplyOhElementByComplex(lambda3, new ComplexNumber(-0.5, 0.0)),
                        MultiplyOhElementByComplex(lambda8, new ComplexNumber(rootThreeOverTwo, 0.0)));

        OhElement expected67 =
                MultiplyOhElementByComplex(combo67, new ComplexNumber(0.0, 1.0));
        OhElement expected76 =
                MultiplyOhElementByComplex(combo67, new ComplexNumber(0.0, -1.0));

        System.out.println("*** DebugSU3Block1() starting");
        System.out.println();

        System.out.println("lambda1:");
        System.out.println(lambda1.toClassGroupedString());
        System.out.println();

        System.out.println("lambda2:");
        System.out.println(lambda2.toClassGroupedString());
        System.out.println();

        System.out.println("lambda3:");
        System.out.println(lambda3.toClassGroupedString());
        System.out.println();

        System.out.println("lambda4:");
        System.out.println(lambda4.toClassGroupedString());
        System.out.println();

        System.out.println("lambda5:");
        System.out.println(lambda5.toClassGroupedString());
        System.out.println();

        System.out.println("lambda6:");
        System.out.println(lambda6.toClassGroupedString());
        System.out.println();

        System.out.println("lambda7:");
        System.out.println(lambda7.toClassGroupedString());
        System.out.println();

        System.out.println("lambda8:");
        System.out.println(lambda8.toClassGroupedString());
        System.out.println();

        System.out.println("lambda1 * lambda2:");
        System.out.println(l1l2.toClassGroupedString());
        System.out.println();

        System.out.println("expected i lambda3:");
        System.out.println(expected12.toClassGroupedString());
        System.out.println();

        System.out.println("lambda2 * lambda1:");
        System.out.println(l2l1.toClassGroupedString());
        System.out.println();

        System.out.println("expected -i lambda3:");
        System.out.println(expected21.toClassGroupedString());
        System.out.println();

        System.out.println("lambda4 * lambda5:");
        System.out.println(l4l5.toClassGroupedString());
        System.out.println();

        System.out.println("expected i(1/2 lambda3 + sqrt(3)/2 lambda8):");
        System.out.println(expected45.toClassGroupedString());
        System.out.println();

        System.out.println("lambda5 * lambda4:");
        System.out.println(l5l4.toClassGroupedString());
        System.out.println();

        System.out.println("expected -i(1/2 lambda3 + sqrt(3)/2 lambda8):");
        System.out.println(expected54.toClassGroupedString());
        System.out.println();

        System.out.println("lambda6 * lambda7:");
        System.out.println(l6l7.toClassGroupedString());
        System.out.println();

        System.out.println("expected i(-1/2 lambda3 + sqrt(3)/2 lambda8):");
        System.out.println(expected67.toClassGroupedString());
        System.out.println();

        System.out.println("lambda7 * lambda6:");
        System.out.println(l7l6.toClassGroupedString());
        System.out.println();

        System.out.println("expected -i(-1/2 lambda3 + sqrt(3)/2 lambda8):");
        System.out.println(expected76.toClassGroupedString());
        System.out.println();

        System.out.println("*** DebugSU3Block1() completed");
    }

    /*
     * TestMatrix3x3
     *
     * This method tests the Matrix3x3 class.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * We are about to use ordinary 3x3 matrix arithmetic as the comparison
     * target for the SU(3)-type Fourier blocks, so this arithmetic needs to
     * be trusted first.
     */
    private static void TestMatrix3x3() {

        boolean allTestsOkay = true;

        Matrix3x3 zero = Matrix3x3.zero();
        Matrix3x3 identity = Matrix3x3.identity();

        Matrix3x3 e12 = Matrix3x3.basis(0, 1);
        Matrix3x3 e21 = Matrix3x3.basis(1, 0);
        Matrix3x3 e23 = Matrix3x3.basis(1, 2);
        Matrix3x3 e32 = Matrix3x3.basis(2, 1);
        Matrix3x3 e13 = Matrix3x3.basis(0, 2);
        Matrix3x3 e31 = Matrix3x3.basis(2, 0);
        Matrix3x3 e11 = Matrix3x3.basis(0, 0);
        Matrix3x3 e22 = Matrix3x3.basis(1, 1);
        Matrix3x3 e33 = Matrix3x3.basis(2, 2);

        /*
         * Identity tests
         */
        if (!Matrix3x3AreClose(identity.multiply(e12), e12)
                || !Matrix3x3AreClose(e12.multiply(identity), e12)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: identity action failed");
            allTestsOkay = false;
        }

        /*
         * Matrix-unit multiplication tests
         *
         * E12 E21 = E11
         * E21 E12 = E22
         * E12 E23 = E13
         * E23 E32 = E22
         */
        if (!Matrix3x3AreClose(e12.multiply(e21), e11)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: E12 E21 failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(e21.multiply(e12), e22)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: E21 E12 failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(e12.multiply(e23), Matrix3x3.basis(0, 2))) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: E12 E23 failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(e23.multiply(e32), e22)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: E23 E32 failed");
            allTestsOkay = false;
        }


        /*
         * add
         */
        if (!Matrix3x3AreClose(e12.add(e23), new Matrix3x3(
                ComplexNumber.zero(), ComplexNumber.one(),  ComplexNumber.zero(),
                ComplexNumber.zero(), ComplexNumber.zero(), ComplexNumber.one(),
                ComplexNumber.zero(), ComplexNumber.zero(), ComplexNumber.zero()))) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: add() failed");
            allTestsOkay = false;
        }

        /*
         * subtract
         */
        if (!Matrix3x3AreClose(e12.add(e23).subtract(e23), e12)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: subtract() failed");
            allTestsOkay = false;
        }
        /*
         * conjugateTranspose
         */
        Matrix3x3 testMatrix = new Matrix3x3(
                new ComplexNumber(1.0,  2.0), new ComplexNumber(3.0,  4.0), new ComplexNumber(5.0,  6.0),
                new ComplexNumber(7.0,  8.0), new ComplexNumber(9.0, 10.0), new ComplexNumber(11.0, 12.0),
                new ComplexNumber(13.0,14.0), new ComplexNumber(15.0,16.0), new ComplexNumber(17.0,18.0)
        );

        Matrix3x3 expectedAdjoint = new Matrix3x3(
                new ComplexNumber(1.0,  -2.0), new ComplexNumber(7.0,  -8.0), new ComplexNumber(13.0, -14.0),
                new ComplexNumber(3.0,  -4.0), new ComplexNumber(9.0, -10.0), new ComplexNumber(15.0, -16.0),
                new ComplexNumber(5.0,  -6.0), new ComplexNumber(11.0,-12.0), new ComplexNumber(17.0, -18.0)
        );

        if (!Matrix3x3AreClose(testMatrix.conjugateTranspose(), expectedAdjoint)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: conjugateTranspose() failed");
            allTestsOkay = false;
        }

        /*
         * trace
         */
        ComplexNumber expectedTrace =
                new ComplexNumber(1.0, 2.0)
                        .add(new ComplexNumber(9.0, 10.0))
                        .add(new ComplexNumber(17.0, 18.0));

        if (!ComplexNumbersAreClose(testMatrix.trace(), expectedTrace)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: trace() failed");
            allTestsOkay = false;
        }

        /*
         * Hermitian spot test for matrix units
         */
        if (!Matrix3x3AreClose(e12.conjugateTranspose(), e21)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: E12 adjoint failed");
            allTestsOkay = false;
        }
        

        if (!ComplexNumbersAreClose(Matrix3x3.identity().trace(), new ComplexNumber(3.0, 0.0))) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: identity trace failed");
            allTestsOkay = false;
        }
        /*
         * scale
         */
        if (!Matrix3x3AreClose(
                e12.scale(new ComplexNumber(2.0, 0.0)),
                new Matrix3x3(
                        ComplexNumber.zero(), new ComplexNumber(2.0, 0.0), ComplexNumber.zero(),
                        ComplexNumber.zero(), ComplexNumber.zero(), ComplexNumber.zero(),
                        ComplexNumber.zero(), ComplexNumber.zero(), ComplexNumber.zero()))) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: scale() failed");
            allTestsOkay = false;
        }

        /*
         * multiply: matrix-unit products
         */
        if (!Matrix3x3AreClose(e12.multiply(e21), e11)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: E12 E21 failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(e21.multiply(e12), e22)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: E21 E12 failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(e12.multiply(e23), e13)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: E12 E23 failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(e23.multiply(e32), e22)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: E23 E32 failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(e31.multiply(e12), e32)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: E31 E12 failed");
            allTestsOkay = false;
        }

        /*
         * zero and identity behavior
         */
        if (!Matrix3x3AreClose(e12.multiply(zero), zero)
                || !Matrix3x3AreClose(zero.multiply(e12), zero)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: zero multiplication failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(identity.multiply(e23), e23)
                || !Matrix3x3AreClose(e23.multiply(identity), e23)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: identity multiplication failed");
            allTestsOkay = false;
        }
        /*
         * Associativity spot test
         */
        Matrix3x3 leftAssoc = e12.multiply(e21).multiply(e23);
        Matrix3x3 rightAssoc = e12.multiply(e21.multiply(e23));

        if (!Matrix3x3AreClose(leftAssoc, rightAssoc)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: associativity spot failed");
            allTestsOkay = false;
        }

        /*
         * Hermitian conjugate spot test
         */
        if (!Matrix3x3AreClose(e12.conjugateTranspose(), e21)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: conjugate transpose failed");
            allTestsOkay = false;
        }

        /*
         * Standard Gell-Mann matrix spot tests
         */
        Matrix3x3 lambda1 = GetStandardLambda1();
        Matrix3x3 lambda2 = GetStandardLambda2();
        Matrix3x3 lambda3 = GetStandardLambda3();
        Matrix3x3 lambda8 = GetStandardLambda8();

        Matrix3x3 expectedSquare123 =
                identity.scale(new ComplexNumber(2.0 / 3.0, 0.0))
                        .add(lambda8.scale(new ComplexNumber(1.0 / Math.sqrt(3.0), 0.0)));

        if (!Matrix3x3AreClose(lambda1.multiply(lambda1), expectedSquare123)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: lambda1 squared failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(lambda2.multiply(lambda2), expectedSquare123)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: lambda2 squared failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(lambda3.multiply(lambda3), expectedSquare123)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: lambda3 squared failed");
            allTestsOkay = false;
        }

        Matrix3x3 iLambda3 = lambda3.scale(new ComplexNumber(0.0, 1.0));
        if (!Matrix3x3AreClose(lambda1.multiply(lambda2), iLambda3)) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: lambda1 lambda2 = i lambda3 failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(Matrix3x3.zero(), Matrix3x3.zero())) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: zero() failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(
                Matrix3x3.identity().multiply(Matrix3x3.basis(0, 1)),
                Matrix3x3.basis(0, 1))) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: identity() failed");
            allTestsOkay = false;
        }

        if (!Matrix3x3AreClose(
                Matrix3x3.basis(0, 1).multiply(Matrix3x3.basis(1, 2)),
                Matrix3x3.basis(0, 2))) {
            System.out.println("%%%%%%%%%%%%%% Error: TestMatrix3x3: basis() multiplication failed");
            allTestsOkay = false;
        }
        
        if (allTestsOkay) {
            System.out.println("*** TestMatrix3x3() Okay");
        }
    }

    /*
     * Matrix3x3AreClose
     *
     * Approximate equality test for 3x3 matrices.
     */
    private static boolean Matrix3x3AreClose(Matrix3x3 first, Matrix3x3 second) {

        double tolerance = 1.0e-12;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                ComplexNumber a = first.getEntry(row, column);
                ComplexNumber b = second.getEntry(row, column);

                if (Math.abs(a.getRealPart() - b.getRealPart()) > tolerance
                        || Math.abs(a.getImaginaryPart() - b.getImaginaryPart()) > tolerance) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * Standard 3x3 Gell-Mann matrices
     *
     * These are the ordinary matrix representatives, not the group-algebra
     * elements. They give us a trustworthy comparison target for later
     * conversion work.
     */
    private static Matrix3x3 GetStandardLambda1() {
        ComplexNumber z = ComplexNumber.zero();
        ComplexNumber o = ComplexNumber.one();

        return new Matrix3x3(
                z, o, z,
                o, z, z,
                z, z, z
        );
    }

    private static Matrix3x3 GetStandardLambda2() {
        ComplexNumber z = ComplexNumber.zero();
        ComplexNumber i = new ComplexNumber(0.0, 1.0);
        ComplexNumber minusI = new ComplexNumber(0.0, -1.0);

        return new Matrix3x3(
                z, minusI, z,
                i, z, z,
                z, z, z
        );
    }

    private static Matrix3x3 GetStandardLambda3() {
        return Matrix3x3.diagonal(
                ComplexNumber.one(),
                new ComplexNumber(-1.0, 0.0),
                ComplexNumber.zero()
        );
    }

    private static Matrix3x3 GetStandardLambda4() {
        ComplexNumber z = ComplexNumber.zero();
        ComplexNumber o = ComplexNumber.one();

        return new Matrix3x3(
                z, z, o,
                z, z, z,
                o, z, z
        );
    }

    private static Matrix3x3 GetStandardLambda5() {
        ComplexNumber z = ComplexNumber.zero();
        ComplexNumber i = new ComplexNumber(0.0, 1.0);
        ComplexNumber minusI = new ComplexNumber(0.0, -1.0);

        return new Matrix3x3(
                z, z, minusI,
                z, z, z,
                i, z, z
        );
    }

    private static Matrix3x3 GetStandardLambda6() {
        ComplexNumber z = ComplexNumber.zero();
        ComplexNumber o = ComplexNumber.one();

        return new Matrix3x3(
                z, z, z,
                z, z, o,
                z, o, z
        );
    }

    private static Matrix3x3 GetStandardLambda7() {
        ComplexNumber z = ComplexNumber.zero();
        ComplexNumber i = new ComplexNumber(0.0, 1.0);
        ComplexNumber minusI = new ComplexNumber(0.0, -1.0);

        return new Matrix3x3(
                z, z, z,
                z, z, minusI,
                z, i, z
        );
    }

    private static Matrix3x3 GetStandardLambda8() {
        double oneOverRootThree = 1.0 / Math.sqrt(3.0);

        return Matrix3x3.diagonal(
                new ComplexNumber(oneOverRootThree, 0.0),
                new ComplexNumber(oneOverRootThree, 0.0),
                new ComplexNumber(-2.0 * oneOverRootThree, 0.0)
        );
    }

    
    /*
     * TestPauliValues
     *
     * This method tests the PauliValue class.
     *
     * EXPECTED FORM OF A PAULI VALUE
     * ------------------------------
     * A PauliValue is intended to represent:
     *
     *     a * 1 + b * sigma_x + c * sigma_y + d * sigma_z
     *
     * where a, b, c, and d are complex numbers.
     *
     * WHY THESE TESTS MATTER
     * ----------------------
     * The Pauli algebra will be our first noncommutative algebra in the project.
     * That means multiplication order matters, unlike ordinary complex numbers.
     *
     * So the tests must check not only simple arithmetic, but also the defining
     * Pauli multiplication rules.
     *
     * QUIET SUCCESS / LOUD FAILURE
     * ----------------------------
     * On success:
     *     one short line is printed
     *
     * On failure:
     *     very visible error lines are printed
     *
     * The text of each failure message is chosen to be easy to search for.
     */
    private static void TestPauliValues() {

        /*
         * This variable keeps track of whether every individual test has passed.
         * It starts out optimistic and is turned false if any test fails.
         */
        boolean allTestsOkay = true;

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Check addition
         * ------------------------------------------------------------
         *
         * We use a small hand-checkable example.
         *
         * Let:
         *
         *     left  = 1*1 + 2*sigma_x + 3*sigma_y + 4*sigma_z
         *     right = 5*1 + 6*sigma_x + 7*sigma_y + 8*sigma_z
         *
         * Then:
         *
         *     left + right
         *         = 6*1 + 8*sigma_x + 10*sigma_y + 12*sigma_z
         */
        PauliValue addLeft =
                new PauliValue(
                        new ComplexNumber(1.0, 0.0),
                        new ComplexNumber(2.0, 0.0),
                        new ComplexNumber(3.0, 0.0),
                        new ComplexNumber(4.0, 0.0)
                );

        PauliValue addRight =
                new PauliValue(
                        new ComplexNumber(5.0, 0.0),
                        new ComplexNumber(6.0, 0.0),
                        new ComplexNumber(7.0, 0.0),
                        new ComplexNumber(8.0, 0.0)
                );

        PauliValue addExpected =
                new PauliValue(
                        new ComplexNumber(6.0, 0.0),
                        new ComplexNumber(8.0, 0.0),
                        new ComplexNumber(10.0, 0.0),
                        new ComplexNumber(12.0, 0.0)
                );

        PauliValue addComputed = addLeft.add(addRight);

        if (!PauliValuesAreClose(addComputed, addExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: addition failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Check subtraction
         * ------------------------------------------------------------
         */
        PauliValue subtractExpected =
                new PauliValue(
                        new ComplexNumber(-4.0, 0.0),
                        new ComplexNumber(-4.0, 0.0),
                        new ComplexNumber(-4.0, 0.0),
                        new ComplexNumber(-4.0, 0.0)
                );

        PauliValue subtractComputed = addLeft.subtract(addRight);

        if (!PauliValuesAreClose(subtractComputed, subtractExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: subtraction failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Check sigma_x * sigma_x = 1
         * ------------------------------------------------------------
         *
         * This is one of the defining Pauli relations.
         */
        PauliValue sigmaX = PauliValue.sigmaX();
        PauliValue one = PauliValue.identity();

        PauliValue sigmaXSquare = sigmaX.multiply(sigmaX);

        if (!PauliValuesAreClose(sigmaXSquare, one)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: sigma_x squared failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * Check sigma_y * sigma_y = 1
         * ------------------------------------------------------------
         */
        PauliValue sigmaY = PauliValue.sigmaY();
        PauliValue sigmaYSquare = sigmaY.multiply(sigmaY);

        if (!PauliValuesAreClose(sigmaYSquare, one)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: sigma_y squared failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Check sigma_z * sigma_z = 1
         * ------------------------------------------------------------
         */
        PauliValue sigmaZ = PauliValue.sigmaZ();
        PauliValue sigmaZSquare = sigmaZ.multiply(sigmaZ);

        if (!PauliValuesAreClose(sigmaZSquare, one)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: sigma_z squared failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 6:
         * Check sigma_x * sigma_y = i sigma_z
         * ------------------------------------------------------------
         *
         * This is a central Pauli multiplication rule.
         */
        PauliValue iSigmaZ =
                new PauliValue(
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 1.0)
                );

        PauliValue sigmaXSigmaY = sigmaX.multiply(sigmaY);

        if (!PauliValuesAreClose(sigmaXSigmaY, iSigmaZ)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: sigma_x sigma_y failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 7:
         * Check sigma_y * sigma_x = - i sigma_z
         * ------------------------------------------------------------
         *
         * This checks noncommutativity directly.
         */
        PauliValue minusISigmaZ =
                new PauliValue(
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, -1.0)
                );

        PauliValue sigmaYSigmaX = sigmaY.multiply(sigmaX);

        if (!PauliValuesAreClose(sigmaYSigmaX, minusISigmaZ)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: sigma_y sigma_x failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 8:
         * Check sigma_y * sigma_z = i sigma_x
         * ------------------------------------------------------------
         */
        PauliValue iSigmaX =
                new PauliValue(
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 1.0),
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 0.0)
                );

        PauliValue sigmaYSigmaZ = sigmaY.multiply(sigmaZ);

        if (!PauliValuesAreClose(sigmaYSigmaZ, iSigmaX)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: sigma_y sigma_z failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 9:
         * Check sigma_z * sigma_x = i sigma_y
         * ------------------------------------------------------------
         */
        PauliValue iSigmaY =
                new PauliValue(
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 1.0),
                        new ComplexNumber(0.0, 0.0)
                );

        PauliValue sigmaZSigmaX = sigmaZ.multiply(sigmaX);

        if (!PauliValuesAreClose(sigmaZSigmaX, iSigmaY)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: sigma_z sigma_x failed"
            );
            allTestsOkay = false;
        }
        /*
         * ------------------------------------------------------------
         * Test 10:
         * Check sigma_z * sigma_y = - i sigma_x
         * ------------------------------------------------------------
         *
         * This is the reverse-order partner of:
         *
         *     sigma_y * sigma_z = i sigma_x
         *
         * so it should pick up a minus sign:
         *
         *     sigma_z * sigma_y = -i sigma_x
         *
         * This is another direct check that multiplication order matters.
         */
        PauliValue minusISigmaX =
                new PauliValue(
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, -1.0),
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 0.0)
                );

        PauliValue sigmaZSigmaY = sigmaZ.multiply(sigmaY);

        if (!PauliValuesAreClose(sigmaZSigmaY, minusISigmaX)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: sigma_z sigma_y failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 11:
         * Check sigma_x * sigma_z = - i sigma_y
         * ------------------------------------------------------------
         *
         * This is the reverse-order partner of:
         *
         *     sigma_z * sigma_x = i sigma_y
         *
         * so here again the reversed order introduces a minus sign:
         *
         *     sigma_x * sigma_z = -i sigma_y
         */
        PauliValue minusISigmaY =
                new PauliValue(
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, 0.0),
                        new ComplexNumber(0.0, -1.0),
                        new ComplexNumber(0.0, 0.0)
                );

        PauliValue sigmaXSigmaZ = sigmaX.multiply(sigmaZ);

        if (!PauliValuesAreClose(sigmaXSigmaZ, minusISigmaY)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: sigma_x sigma_z failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 12:
         * Check sigma_z * sigma_y + sigma_y * sigma_z = 0
         * ------------------------------------------------------------
         *
         * We already tested one anticommutator:
         *
         *     sigma_x sigma_y + sigma_y sigma_x = 0
         *
         * Here we test a second one:
         *
         *     sigma_z sigma_y + sigma_y sigma_z = 0
         *
         * This gives us another structural check involving both addition
         * and multiplication.
         */
        PauliValue zero = PauliValue.zero();
        PauliValue anticommutatorZY =
                sigmaZ.multiply(sigmaY).add(sigmaY.multiply(sigmaZ));

        if (!PauliValuesAreClose(anticommutatorZY, zero)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: anticommutator zy failed"
            );
            allTestsOkay = false;
        }
        /*
         * ------------------------------------------------------------
         * Test 11:
         * Check the anticommutator relation
         * ------------------------------------------------------------
         *
         * For distinct Pauli matrices:
         *
         *     sigma_x sigma_y + sigma_y sigma_x = 0
         *
         * This is another important structural check.
         */
        PauliValue anticommutatorXY =
                sigmaX.multiply(sigmaY).add(sigmaY.multiply(sigmaX));

        if (!PauliValuesAreClose(anticommutatorXY, zero)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestPauliValues: anticommutator xy failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Final result
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestPauliValues() Okay");
        }
    }
    
    /*
     * TestOhAssociativitySpots
     *
     * This method performs a collection of spot checks of associativity
     * for the Oh multiplication.
     *
     * WHY THIS TEST EXISTS
     * --------------------
     * We already know that the octahedral group itself must be associative.
     * However, in software, mistakes can still occur if:
     *
     * - multiplication order conventions are inconsistent,
     * - metadata is mismatched,
     * - basis indexing is wrong,
     * - or the multiplication table is filled incorrectly.
     *
     * Therefore, even though the mathematics guarantees associativity,
     * it is still very useful to test it in code.
     *
     * WHAT THIS TEST DOES
     * -------------------
     * It selects a modest collection of basis-element triples
     *
     *     (a, b, c)
     *
     * and checks that:
     *
     *     (a * b) * c = a * (b * c)
     *
     * We keep the list fairly small because this is only a spot test.
     * If we later want a more exhaustive associativity test, we can add one.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhAssociativitySpots() {

        /*
         * This variable records whether every sub-test has passed.
         */
        boolean allTestsOkay = true;

        /*
         * Each row is one triple of basis indices:
         *
         *     { leftIndex, middleIndex, rightIndex }
         *
         * We choose examples from several different classes so that we are
         * not only testing one tiny corner of the group.
         *
         * The choices include:
         *
         * - identity,
         * - class 1 half-turns,
         * - class 2 three-cycles,
         * - class 3 quarter-turns,
         * - class 4 proper transposition-type half-turns,
         * - inversion,
         * - and several inverted classes.
         */
        int[][] testTriples = {
            {0, 0, 0},
            {0, 4, 12},
            {1, 1, 1},
            {1, 4, 8},
            {1, 12, 24},
            {4, 5, 8},
            {4, 12, 18},
            {5, 10, 24},
            {6, 11, 1},
            {12, 13, 14},
            {12, 18, 24},
            {18, 18, 18},
            {24, 24, 24},
            {24, 1, 25},
            {25, 26, 27},
            {28, 32, 24},
            {29, 34, 1},
            {30, 35, 18},
            {36, 37, 38},
            {38, 39, 40},
            {40, 47, 24},
            {42, 44, 45},
            {44, 45, 46},
            {47, 40, 41}
        };

        /*
         * ------------------------------------------------------------
         * Main associativity loop
         * ------------------------------------------------------------
         *
         * For each triple (a,b,c), compare:
         *
         *     (a*b)*c
         *
         * with:
         *
         *     a*(b*c)
         */
        for (int testIndex = 0; testIndex < testTriples.length; testIndex++) {

            int leftIndex = testTriples[testIndex][0];
            int middleIndex = testTriples[testIndex][1];
            int rightIndex = testTriples[testIndex][2];

            OhElement leftElement = OhElement.basisElement(leftIndex);
            OhElement middleElement = OhElement.basisElement(middleIndex);
            OhElement rightElement = OhElement.basisElement(rightIndex);

            OhElement leftAssociated =
                    leftElement.multiply(middleElement).multiply(rightElement);

            OhElement rightAssociated =
                    leftElement.multiply(middleElement.multiply(rightElement));

            if (!OhElementsAreClose(leftAssociated, rightAssociated)) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhAssociativitySpots: associativity failed for triple "
                        + "(" + leftIndex + ", " + middleIndex + ", " + rightIndex + ")"
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestOhAssociativitySpots() Okay");
        }
    }
    
    /*
     * TestAxisImageLabelMultiplication
     *
     * This method tests the helper that composes two 3-letter axis-image labels.
     *
     * WHY THIS TEST MATTERS
     * ---------------------
     * The axis-image labels are intended to become the computational foundation
     * for generating the full Oh multiplication table.
     *
     * Therefore, before we trust them to generate 48x48 products, we should
     * test the composition rule carefully on a small collection of products
     * whose answers we already know.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * 1. Identity works on the left.
     * 2. Identity works on the right.
     * 3. A class-1 half-turn squares to the identity.
     * 4. A class-2 element times its inverse gives the identity.
     * 5. A class-3 quarter-turn times its inverse gives the identity.
     * 6. A transposition-type proper half-turn squares to the identity.
     * 7. Inversion squares to the identity.
     * 8. A proper element times its inverted partner gives the inversion.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestAxisImageLabelMultiplication() {

        /*
         * This variable records whether every sub-test has passed.
         */
        boolean allTestsOkay = true;

        /*
         * ------------------------------------------------------------
         * Test 1:
         * identity on the left
         * ------------------------------------------------------------
         *
         * The identity label xyz should satisfy:
         *
         *     xyz * g = g
         */
        String test1Computed =
                OhElement.MultiplyAxisImageLabelsForTesting("xyz", "yzx");

        if (!test1Computed.equals("yzx")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestAxisImageLabelMultiplication: left identity failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * identity on the right
         * ------------------------------------------------------------
         *
         * The identity label xyz should also satisfy:
         *
         *     g * xyz = g
         */
        String test2Computed =
                OhElement.MultiplyAxisImageLabelsForTesting("yzx", "xyz");

        if (!test2Computed.equals("yzx")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestAxisImageLabelMultiplication: right identity failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * class-1 half-turn squares to identity
         * ------------------------------------------------------------
         *
         * The label xYZ is a 180-degree proper rotation, so squaring it
         * should give the identity.
         */
        String test3Computed =
                OhElement.MultiplyAxisImageLabelsForTesting("xYZ", "xYZ");

        if (!test3Computed.equals("xyz")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestAxisImageLabelMultiplication: class 1 square failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * class-2 element times its inverse
         * ------------------------------------------------------------
         *
         * We entered yzx and zxy as an inverse pair in the basis table.
         * Therefore their product should be the identity.
         */
        String test4Computed =
                OhElement.MultiplyAxisImageLabelsForTesting("yzx", "zxy");

        if (!test4Computed.equals("xyz")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestAxisImageLabelMultiplication: class 2 inverse product failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * class-3 element times its inverse
         * ------------------------------------------------------------
         *
         * We entered xzY and xZy as an inverse pair in the basis table.
         */
        String test5Computed =
                OhElement.MultiplyAxisImageLabelsForTesting("xzY", "xZy");

        if (!test5Computed.equals("xyz")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestAxisImageLabelMultiplication: class 3 inverse product failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 6:
         * class-4 half-turn squares to identity
         * ------------------------------------------------------------
         *
         * The label Xzy is a 180-degree proper rotation, so its square
         * should be the identity.
         */
        String test6Computed =
                OhElement.MultiplyAxisImageLabelsForTesting("Xzy", "Xzy");

        if (!test6Computed.equals("xyz")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestAxisImageLabelMultiplication: class 4 square failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 7:
         * inversion squares to identity
         * ------------------------------------------------------------
         *
         * The pure inversion XYZ should satisfy:
         *
         *     XYZ * XYZ = xyz
         */
        String test7Computed =
                OhElement.MultiplyAxisImageLabelsForTesting("XYZ", "XYZ");

        if (!test7Computed.equals("xyz")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestAxisImageLabelMultiplication: inversion square failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 8:
         * proper element times its inverted partner
         * ------------------------------------------------------------
         *
         * Since Xyz is the inverted partner of xYZ, their product should
         * be the pure inversion XYZ.
         */
        String test8Computed =
                OhElement.MultiplyAxisImageLabelsForTesting("xYZ", "Xyz");

        if (!test8Computed.equals("XYZ")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestAxisImageLabelMultiplication: inverted partner product failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestAxisImageLabelMultiplication() Okay");
        }
    }    
    
    /*
     * TestOhPermutationLabels
     *
     * This method tests the permutation labels stored in the Oh basis-element
     * metadata table.
     *
     * NOW THAT THE TABLE IS FILLED
     * ----------------------------
     * Earlier in the project, temporary labels beginning with TEMP_ were
     * allowed while we were gradually entering the permutation labels.
     *
     * That stage is now over.
     *
     * Therefore this strengthened version checks the following:
     *
     * 1. Every basis entry exists.
     * 2. Every basis entry has a non-null, non-empty permutation label.
     * 3. No permutation label begins with TEMP_.
     * 4. Basis element 0 has label ().
     * 5. Basis element 24 has label ()i.
     * 6. All 48 permutation labels are unique.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhPermutationLabels() {

        /*
         * This variable records whether every sub-test has passed.
         */
        boolean allTestsOkay = true;

        /*
         * We store all labels here so that we can later check uniqueness.
         */
        String[] permutationLabels =
                new String[OhElement.getNumberOfBasisElements()];

        /*
         * ------------------------------------------------------------
         * Check presence and basic validity of every permutation label
         * ------------------------------------------------------------
         */
        for (int elementIndex = 0;
                elementIndex < OhElement.getNumberOfBasisElements();
                elementIndex++) {

            OhBasisElement basisInfo = OhElement.getBasisElementInfo(elementIndex);

            /*
             * Every basis entry must exist.
             */
            if (basisInfo == null) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhPermutationLabels: null basis entry at index "
                        + elementIndex
                );
                allTestsOkay = false;
                continue;
            }

            String permutationLabel = basisInfo.getPermutationLabel();
            permutationLabels[elementIndex] = permutationLabel;

            /*
             * The label must exist and must not be empty.
             */
            if (permutationLabel == null || permutationLabel.length() == 0) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhPermutationLabels: missing permutation label at index "
                        + elementIndex
                );
                allTestsOkay = false;
                continue;
            }

            /*
             * Temporary labels are no longer allowed.
             */
            if (permutationLabel.startsWith("TEMP_")) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhPermutationLabels: temporary permutation label remains at index "
                        + elementIndex
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Anchor checks for the two most important special elements
         * ------------------------------------------------------------
         *
         * Basis element 0 should be the identity.
         * Basis element 24 should be the inversion.
         */
        OhBasisElement basisZero = OhElement.getBasisElementInfo(0);
        if (basisZero == null || !basisZero.getPermutationLabel().equals("()")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationLabels: basis element 0 label failed"
            );
            allTestsOkay = false;
        }

        OhBasisElement basisTwentyFour = OhElement.getBasisElementInfo(24);
        if (basisTwentyFour == null || !basisTwentyFour.getPermutationLabel().equals("()i")) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhPermutationLabels: basis element 24 label failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Check uniqueness of all 48 permutation labels
         * ------------------------------------------------------------
         *
         * Since there are only 48 labels, a simple pairwise comparison is
         * perfectly fine and keeps the code easy to read.
         */
        for (int firstIndex = 0;
                firstIndex < OhElement.getNumberOfBasisElements();
                firstIndex++) {

            for (int secondIndex = firstIndex + 1;
                    secondIndex < OhElement.getNumberOfBasisElements();
                    secondIndex++) {

                if (permutationLabels[firstIndex] != null
                        && permutationLabels[firstIndex].equals(permutationLabels[secondIndex])) {

                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhPermutationLabels: duplicate permutation label at indices "
                            + firstIndex + " and " + secondIndex
                    );
                    allTestsOkay = false;
                }
            }
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestOhPermutationLabels() Okay");
        }
    }
    
    
    
    /*
     * TestOhBasisElementTable
     *
     * This method tests the basis-element metadata table for the Oh group.
     *
     * WHAT THIS TEST IS MEANT TO CHECK
     * --------------------------------
     * At the present stage of the project, we have entered the full list
     * of 48 basis elements into the metadata table, but we have NOT yet
     * finished the full multiplication table and we have NOT yet finalized
     * every permutation label.
     *
     * So this test focuses on the metadata properties that we do intend
     * to trust now:
     *
     * 1. All 48 basis-element entries exist.
     * 2. The class sizes are correct.
     * 3. The 48 axis-image labels are all unique.
     * 4. Every axis-image label has valid 3-letter form.
     * 5. The inversion flag agrees with the letter case of the label.
     * 6. The inverse index of each element is in range.
     * 7. Inverse-of-inverse returns the original index.
     * 8. A few especially important entries have the expected labels.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhBasisElementTable() {

        /*
         * This variable records whether every sub-test has passed.
         */
        boolean allTestsOkay = true;

        /*
         * We use this array to count how many basis elements belong to each
         * conjugacy class.
         *
         * Since there are 10 classes, the array has length 10.
         */
        int[] classCounts = new int[10];

        /*
         * We use this array of strings to collect the 48 axis-image labels.
         * Later, we will compare them pairwise to check uniqueness.
         */
        String[] allAxisImageLabels =
                new String[OhElement.getNumberOfBasisElements()];

        /*
         * ------------------------------------------------------------
         * Main loop over all 48 basis elements
         * ------------------------------------------------------------
         *
         * In this loop we check:
         *
         * - entry exists,
         * - class index is in range,
         * - axis-image label is valid,
         * - inversion flag matches the label,
         * - inverse index is in range.
         */
        for (int elementIndex = 0;
                elementIndex < OhElement.getNumberOfBasisElements();
                elementIndex++) {

            OhBasisElement basisInfo = OhElement.getBasisElementInfo(elementIndex);

            /*
             * Check that the table entry exists.
             */
            if (basisInfo == null) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhBasisElementTable: null basis entry at index "
                        + elementIndex
                );
                allTestsOkay = false;

                /*
                 * If the entry is null, we cannot safely check the rest of its fields.
                 * So skip to the next element.
                 */
                continue;
            }

            /*
             * Check that the stored element index agrees with the slot index.
             *
             * This is a very basic consistency check.
             */
            if (basisInfo.getElementIndex() != elementIndex) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhBasisElementTable: stored element index mismatch at index "
                        + elementIndex
                );
                allTestsOkay = false;
            }

            /*
             * Check that the class index is in the valid range 0 through 9.
             */
            int classIndex = basisInfo.getClassIndex();

            if (classIndex < 0 || classIndex >= 10) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhBasisElementTable: class index out of range at element index "
                        + elementIndex
                );
                allTestsOkay = false;
            } else {
                classCounts[classIndex]++;
            }

            /*
             * Record the axis-image label so we can later check uniqueness.
             */
            String axisImageLabel = basisInfo.getAxisImageLabel();
            allAxisImageLabels[elementIndex] = axisImageLabel;

            /*
             * Check that the axis-image label has valid basic form.
             *
             * We require:
             *
             * - exactly 3 characters
             * - each character one of x,y,z,X,Y,Z
             */
            if (!AxisImageLabelHasValidForm(axisImageLabel)) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhBasisElementTable: invalid axis-image label at element index "
                        + elementIndex
                );
                allTestsOkay = false;
            }

            /*
             * Check that the inversion flag agrees with the axis-image label.
             *
             * Our current design principle is:
             *
             * - non-inverted elements have an even number of capital letters
             *   among 0 or 2 capitals
             * - inverted elements have an odd number of capital letters
             *   among 1 or 3 capitals
             *
             * This follows from whether the transformation preserves or reverses
             * handedness.
             */
            if (!AxisImageLabelMatchesInversionFlag(
                    axisImageLabel,
                    basisInfo.getIsInverted())) {

                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhBasisElementTable: inversion flag mismatch at element index "
                        + elementIndex
                );
                allTestsOkay = false;
            }

            /*
             * Check that the inverse index is in range.
             */
            int inverseIndex = basisInfo.getInverseElementIndex();

            if (inverseIndex < 0
                    || inverseIndex >= OhElement.getNumberOfBasisElements()) {

                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhBasisElementTable: inverse index out of range at element index "
                        + elementIndex
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Check class counts
         * ------------------------------------------------------------
         *
         * The agreed class sizes are:
         *
         *     1, 3, 8, 6, 6, 1, 3, 8, 6, 6
         */
        int[] expectedClassCounts = {1, 3, 8, 6, 6, 1, 3, 8, 6, 6};

        for (int classIndex = 0; classIndex < 10; classIndex++) {
            if (classCounts[classIndex] != expectedClassCounts[classIndex]) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhBasisElementTable: class count failed for class index "
                        + classIndex
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Check uniqueness of the 48 axis-image labels
         * ------------------------------------------------------------
         *
         * Since there are only 48 labels, a simple pairwise comparison is
         * perfectly fine here and keeps the code easy to read.
         */
        for (int firstIndex = 0;
                firstIndex < OhElement.getNumberOfBasisElements();
                firstIndex++) {

            for (int secondIndex = firstIndex + 1;
                    secondIndex < OhElement.getNumberOfBasisElements();
                    secondIndex++) {

                if (allAxisImageLabels[firstIndex] != null
                        && allAxisImageLabels[firstIndex].equals(
                                allAxisImageLabels[secondIndex])) {

                    System.out.println(
                            "%%%%%%%%%%%%%% Error: TestOhBasisElementTable: duplicate axis-image label at indices "
                            + firstIndex + " and " + secondIndex
                    );
                    allTestsOkay = false;
                }
            }
        }

        /*
         * ------------------------------------------------------------
         * Check inverse-of-inverse property
         * ------------------------------------------------------------
         *
         * If h is the inverse of g, then the inverse of h must be g.
         *
         * In basis-index form:
         *
         *     inverse(inverse(index)) = index
         */
        for (int elementIndex = 0;
                elementIndex < OhElement.getNumberOfBasisElements();
                elementIndex++) {

            OhBasisElement basisInfo = OhElement.getBasisElementInfo(elementIndex);

            if (basisInfo == null) {
                continue;
            }

            int inverseIndex = basisInfo.getInverseElementIndex();
            OhBasisElement inverseInfo = OhElement.getBasisElementInfo(inverseIndex);

            if (inverseInfo == null) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhBasisElementTable: inverse entry missing for element index "
                        + elementIndex
                );
                allTestsOkay = false;
            } else if (inverseInfo.getInverseElementIndex() != elementIndex) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhBasisElementTable: inverse-of-inverse failed at element index "
                        + elementIndex
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Check a few especially important specific entries
         * ------------------------------------------------------------
         *
         * These are quick anchor checks for the entries we especially care about.
         */
        OhBasisElement basisZero = OhElement.getBasisElementInfo(0);
        if (basisZero == null
                || !basisZero.getAxisImageLabel().equals("xyz")
                || !basisZero.getPermutationLabel().equals("()")) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhBasisElementTable: basis element 0 failed"
            );
            allTestsOkay = false;
        }

        OhBasisElement basisTwentyFour = OhElement.getBasisElementInfo(24);
        if (basisTwentyFour == null
                || !basisTwentyFour.getAxisImageLabel().equals("XYZ")
                || !basisTwentyFour.getPermutationLabel().equals("()i")) {

            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhBasisElementTable: basis element 24 failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestOhBasisElementTable() Okay");
        }
    }

    /*
     * AxisImageLabelHasValidForm
     *
     * This helper checks whether an axis-image label has the correct basic form.
     *
     * A valid label must:
     *
     * - have exactly three characters,
     * - each character must be one of x,y,z,X,Y,Z.
     *
     * This helper does NOT check uniqueness and does NOT check whether the
     * label corresponds to a valid cube symmetry. It only checks the basic
     * formatting rules.
     */
    private static boolean AxisImageLabelHasValidForm(String axisImageLabel) {

        /*
         * Null is invalid.
         */
        if (axisImageLabel == null) {
            return false;
        }

        /*
         * The label must have exactly three characters.
         */
        if (axisImageLabel.length() != 3) {
            return false;
        }

        /*
         * Check each character individually.
         */
        for (int position = 0; position < 3; position++) {

            char currentCharacter = axisImageLabel.charAt(position);

            boolean characterIsAllowed =
                    currentCharacter == 'x'
                    || currentCharacter == 'y'
                    || currentCharacter == 'z'
                    || currentCharacter == 'X'
                    || currentCharacter == 'Y'
                    || currentCharacter == 'Z';

            if (!characterIsAllowed) {
                return false;
            }
        }

        return true;
    }

     /*
     * AxisImageLabelMatchesInversionFlag
     *
     * This helper checks whether the stored inversion flag agrees with the
     * actual handedness of the signed axis-image label.
     *
     * WHY THE PREVIOUS SIMPLE RULE WAS WRONG
     * --------------------------------------
     * It is NOT enough to count capital letters.
     *
     * Example:
     *
     *     xzY
     *
     * has one capital letter, but the axis order xzy is an odd permutation,
     * so the odd permutation and the odd sign flip cancel, giving an overall
     * determinant of +1. Therefore xzY is proper, not inverted.
     *
     * CORRECT RULE
     * ------------
     * The determinant sign is:
     *
     *     permutation parity  times  sign-flip parity
     *
     * where:
     *
     * - permutation parity is +1 for an even permutation of xyz
     *   and -1 for an odd permutation
     *
     * - sign-flip parity is +1 for an even number of capital letters
     *   and -1 for an odd number of capital letters
     *
     * Then:
     *
     * - determinant +1 means proper, so isInverted must be false
     * - determinant -1 means improper, so isInverted must be true
     */
    private static boolean AxisImageLabelMatchesInversionFlag(
            String axisImageLabel,
            boolean isInverted) {

        /*
         * If the label is not even well formed, it cannot match correctly.
         */
        if (!AxisImageLabelHasValidForm(axisImageLabel)) {
            return false;
        }

        /*
         * ------------------------------------------------------------
         * Step 1:
         * Count capital letters
         * ------------------------------------------------------------
         *
         * Each capital letter contributes one minus sign.
         */
        int capitalLetterCount = 0;

        for (int position = 0; position < 3; position++) {
            char currentCharacter = axisImageLabel.charAt(position);

            if (currentCharacter == 'X'
                    || currentCharacter == 'Y'
                    || currentCharacter == 'Z') {
                capitalLetterCount++;
            }
        }

        /*
         * Even number of capitals gives sign +1.
         * Odd number of capitals gives sign -1.
         */
        int signFlipParity;
        if ((capitalLetterCount % 2) == 0) {
            signFlipParity = 1;
        } else {
            signFlipParity = -1;
        }

        /*
         * ------------------------------------------------------------
         * Step 2:
         * Extract the underlying unsigned axis order
         * ------------------------------------------------------------
         *
         * We convert X->x, Y->y, Z->z so that, for example:
         *
         *     xzY  becomes  xzy
         *     YZX  becomes  yzx
         */
        char firstAxis = Character.toLowerCase(axisImageLabel.charAt(0));
        char secondAxis = Character.toLowerCase(axisImageLabel.charAt(1));
        char thirdAxis = Character.toLowerCase(axisImageLabel.charAt(2));

        /*
         * ------------------------------------------------------------
         * Step 3:
         * Determine permutation parity of the axis order
         * ------------------------------------------------------------
         *
         * The even permutations of xyz are:
         *
         *     xyz
         *     yzx
         *     zxy
         *
         * The odd permutations are:
         *
         *     xzy
         *     yxz
         *     zyx
         */
        String unsignedAxisOrder =
                "" + firstAxis + secondAxis + thirdAxis;

        int permutationParity;

        if (unsignedAxisOrder.equals("xyz")
                || unsignedAxisOrder.equals("yzx")
                || unsignedAxisOrder.equals("zxy")) {
            permutationParity = 1;
        } else if (unsignedAxisOrder.equals("xzy")
                || unsignedAxisOrder.equals("yxz")
                || unsignedAxisOrder.equals("zyx")) {
            permutationParity = -1;
        } else {
            /*
             * This should never happen if the label format is valid, but we
             * keep the check for safety.
             */
            return false;
        }

        /*
         * ------------------------------------------------------------
         * Step 4:
         * Combine the two parities
         * ------------------------------------------------------------
         *
         * determinantSign = permutationParity * signFlipParity
         *
         * determinant +1 means proper
         * determinant -1 means improper / inverted
         */
        int determinantSign = permutationParity * signFlipParity;

        boolean labelIndicatesInverted = (determinantSign == -1);

        return labelIndicatesInverted == isInverted;
    }    
    /*
     * TestOhElementShell
     *
     * This method tests the FIRST SHELL VERSION of the OhElement class.
     *
     * VERY IMPORTANT
     * --------------
     * At the present stage of the project, the Oh multiplication table is
     * only partially initialized:
     *
     * - identity row is filled
     * - identity column is filled
     *
     * Therefore, the only products we should trust at this moment are
     * products involving the identity.
     *
     * So this test is intentionally modest. It checks exactly the things
     * that the current shell is supposed to support.
     *
     * WHAT THIS TEST CHECKS
     * ---------------------
     * 1. The metadata for the identity element exists and looks correct.
     * 2. The identity algebra element exists and behaves as identity.
     * 3. Left multiplication by identity works.
     * 4. Right multiplication by identity works.
     * 5. The zero element behaves correctly under addition.
     *
     * OUTPUT STYLE
     * ------------
     * Quiet success, loud failure.
     */
    private static void TestOhElementShell() {

        /*
         * This variable records whether every sub-test has passed.
         */
        boolean allTestsOkay = true;

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Check class metadata for class 0
         * ------------------------------------------------------------
         *
         * In the present design, class 0 should be the identity class.
         */
        OhClassInfo identityClassInfo = OhElement.getClassInfo(0);

        if (identityClassInfo == null) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhElementShell: identity class info missing"
            );
            allTestsOkay = false;
        } else {

            /*
             * Check the agreed short class code.
             */
            if (!identityClassInfo.getClassCode().equals("K_xyz")) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhElementShell: identity class code failed"
                );
                allTestsOkay = false;
            }

            /*
             * Check the agreed class size.
             */
            if (identityClassInfo.getClassSize() != 1) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhElementShell: identity class size failed"
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Check basis metadata for element 0
         * ------------------------------------------------------------
         *
         * In the present shell, basis element 0 should be the group identity.
         */
        OhBasisElement identityBasisInfo = OhElement.getBasisElementInfo(0);

        if (identityBasisInfo == null) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhElementShell: identity basis info missing"
            );
            allTestsOkay = false;
        } else {

            if (!identityBasisInfo.getPermutationLabel().equals("()")) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhElementShell: identity permutation label failed"
                );
                allTestsOkay = false;
            }

            if (!identityBasisInfo.getAxisImageLabel().equals("xyz")) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhElementShell: identity axis-image label failed"
                );
                allTestsOkay = false;
            }

            if (identityBasisInfo.getIsInverted()) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhElementShell: identity inversion flag failed"
                );
                allTestsOkay = false;
            }

            if (identityBasisInfo.getInverseElementIndex() != 0) {
                System.out.println(
                        "%%%%%%%%%%%%%% Error: TestOhElementShell: identity inverse index failed"
                );
                allTestsOkay = false;
            }
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Check identity * identity = identity
         * ------------------------------------------------------------
         *
         * This is the most basic multiplication test for the shell.
         */
        OhElement identity = OhElement.identity();
        OhElement identityTimesIdentity = identity.multiply(identity);

        if (!OhElementsAreClose(identityTimesIdentity, identity)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhElementShell: identity squared failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * Check left multiplication by identity
         * ------------------------------------------------------------
         *
         * Because the identity row of the multiplication table has already
         * been initialized, this should work for any basis element index.
         *
         * We choose one nonzero index to make sure we are not merely testing
         * the identity against itself.
         */
        OhElement basisSeven = OhElement.basisElement(7);
        OhElement leftIdentityProduct = identity.multiply(basisSeven);

        if (!OhElementsAreClose(leftIdentityProduct, basisSeven)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhElementShell: left identity failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Check right multiplication by identity
         * ------------------------------------------------------------
         *
         * Because the identity column of the multiplication table has already
         * been initialized, this should also work.
         */
        OhElement rightIdentityProduct = basisSeven.multiply(identity);

        if (!OhElementsAreClose(rightIdentityProduct, basisSeven)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhElementShell: right identity failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 6:
         * Check zero element under addition
         * ------------------------------------------------------------
         *
         * Even in the shell version, addition is fully implemented.
         *
         * Therefore:
         *
         *     0 + g = g
         *     g + 0 = g
         */
        OhElement zero = OhElement.zero();

        OhElement zeroPlusBasisSeven = zero.add(basisSeven);
        OhElement basisSevenPlusZero = basisSeven.add(zero);

        if (!OhElementsAreClose(zeroPlusBasisSeven, basisSeven)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhElementShell: zero plus basis element failed"
            );
            allTestsOkay = false;
        }

        if (!OhElementsAreClose(basisSevenPlusZero, basisSeven)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhElementShell: basis element plus zero failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 7:
         * Check scalar multiplication by 1
         * ------------------------------------------------------------
         *
         * Since multiplyByComplex() is already implemented, multiplying by
         * the complex number 1 should leave an OhElement unchanged.
         */
        ComplexNumber one = new ComplexNumber(1.0, 0.0);
        OhElement scaledBasisSeven = basisSeven.multiplyByComplex(one);

        if (!OhElementsAreClose(scaledBasisSeven, basisSeven)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestOhElementShell: scalar multiplication by one failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestOhElementShell() Okay");
        }
    }

    /*
     * OhElementsAreClose
     *
     * This helper compares two OhElement objects coefficient by coefficient.
     *
     * WHY THIS METHOD EXISTS
     * ----------------------
     * An OhElement is represented internally by 48 complex coefficients.
     * Therefore two OhElement objects are considered close exactly when
     * each corresponding pair of coefficients is close.
     *
     * We use the existing ComplexNumbersAreClose() helper for the individual
     * coefficient comparisons.
     */
    private static boolean OhElementsAreClose(
            OhElement firstElement,
            OhElement secondElement) {

        /*
         * Loop through all 48 basis-element coefficients.
         */
        for (int index = 0; index < OhElement.getNumberOfBasisElements(); index++) {

            if (!ComplexNumbersAreClose(
                    firstElement.getCoefficient(index),
                    secondElement.getCoefficient(index))) {
                return false;
            }
        }

        /*
         * If no coefficient comparison failed, the two OhElements are close.
         */
        return true;
    }
    
     /*
     * Matrix2x2AreClose
     *
     * This helper compares two 2x2 matrices entry by entry.
     *
     * We use the existing ComplexNumbersAreClose() helper for the
     * individual matrix entries.
     */
    private static boolean Matrix2x2AreClose(
            Matrix2x2 firstMatrix,
            Matrix2x2 secondMatrix) {

        boolean topLeftsAreClose =
                ComplexNumbersAreClose(
                        firstMatrix.getTopLeft(),
                        secondMatrix.getTopLeft()
                );

        boolean topRightsAreClose =
                ComplexNumbersAreClose(
                        firstMatrix.getTopRight(),
                        secondMatrix.getTopRight()
                );

        boolean bottomLeftsAreClose =
                ComplexNumbersAreClose(
                        firstMatrix.getBottomLeft(),
                        secondMatrix.getBottomLeft()
                );

        boolean bottomRightsAreClose =
                ComplexNumbersAreClose(
                        firstMatrix.getBottomRight(),
                        secondMatrix.getBottomRight()
                );

        return topLeftsAreClose
                && topRightsAreClose
                && bottomLeftsAreClose
                && bottomRightsAreClose;
    }

    /*
     * PauliValuesAreClose
     *
     * This helper compares two PauliValue objects coefficient by coefficient.
     *
     * It assumes the PauliValue class provides getters for:
     *
     * - the identity coefficient
     * - the sigma_x coefficient
     * - the sigma_y coefficient
     * - the sigma_z coefficient
     *
     * The comparison uses the existing ComplexNumbersAreClose helper.
     */
    private static boolean PauliValuesAreClose(
            PauliValue firstValue,
            PauliValue secondValue) {

        boolean identityPartsAreClose =
                ComplexNumbersAreClose(
                        firstValue.getIdentityPart(),
                        secondValue.getIdentityPart()
                );

        boolean sigmaXPartsAreClose =
                ComplexNumbersAreClose(
                        firstValue.getSigmaXPart(),
                        secondValue.getSigmaXPart()
                );

        boolean sigmaYPartsAreClose =
                ComplexNumbersAreClose(
                        firstValue.getSigmaYPart(),
                        secondValue.getSigmaYPart()
                );

        boolean sigmaZPartsAreClose =
                ComplexNumbersAreClose(
                        firstValue.getSigmaZPart(),
                        secondValue.getSigmaZPart()
                );

        return identityPartsAreClose
                && sigmaXPartsAreClose
                && sigmaYPartsAreClose
                && sigmaZPartsAreClose;
    }
    
    
    
        /*
     * TestMatrix2x2
     *
     * This method tests the Matrix2x2 class.
     *
     * WHY THESE TESTS MATTER
     * ----------------------
     * The 2x2 matrix class will become a central bridge between:
     *
     * - abstract Pauli algebra calculations
     * - explicit matrix calculations
     *
     * So we want to test the class carefully before we start using it
     * to represent Pauli algebra elements.
     *
     * As usual for this project:
     *
     * - success output should be short
     * - failure output should be loud and searchable
     */
    private static void TestMatrix2x2() {

        /*
         * This variable remembers whether every test has passed.
         */
        boolean allTestsOkay = true;

        /*
         * ------------------------------------------------------------
         * Test 1:
         * Addition
         * ------------------------------------------------------------
         *
         * Let:
         *
         *     A = [ 1   2 ]     B = [ 5   6 ]
         *         [ 3   4 ]         [ 7   8 ]
         *
         * Then:
         *
         *     A + B = [ 6   8 ]
         *             [ 10  12 ]
         */
        Matrix2x2 addLeft =
                new Matrix2x2(
                        new ComplexNumber(1.0, 0.0),
                        new ComplexNumber(2.0, 0.0),
                        new ComplexNumber(3.0, 0.0),
                        new ComplexNumber(4.0, 0.0)
                );

        Matrix2x2 addRight =
                new Matrix2x2(
                        new ComplexNumber(5.0, 0.0),
                        new ComplexNumber(6.0, 0.0),
                        new ComplexNumber(7.0, 0.0),
                        new ComplexNumber(8.0, 0.0)
                );

        Matrix2x2 addExpected =
                new Matrix2x2(
                        new ComplexNumber(6.0, 0.0),
                        new ComplexNumber(8.0, 0.0),
                        new ComplexNumber(10.0, 0.0),
                        new ComplexNumber(12.0, 0.0)
                );

        Matrix2x2 addComputed = addLeft.add(addRight);

        if (!Matrix2x2AreClose(addComputed, addExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestMatrix2x2: addition failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 2:
         * Subtraction
         * ------------------------------------------------------------
         */
        Matrix2x2 subtractExpected =
                new Matrix2x2(
                        new ComplexNumber(-4.0, 0.0),
                        new ComplexNumber(-4.0, 0.0),
                        new ComplexNumber(-4.0, 0.0),
                        new ComplexNumber(-4.0, 0.0)
                );

        Matrix2x2 subtractComputed = addLeft.subtract(addRight);

        if (!Matrix2x2AreClose(subtractComputed, subtractExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestMatrix2x2: subtraction failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 3:
         * Multiplication with a hand-checkable example
         * ------------------------------------------------------------
         *
         * Let:
         *
         *     A = [ 1  2 ]     B = [ 5  6 ]
         *         [ 3  4 ]         [ 7  8 ]
         *
         * Then:
         *
         *     A * B = [ 1*5 + 2*7   1*6 + 2*8 ]
         *             [ 3*5 + 4*7   3*6 + 4*8 ]
         *
         *           = [ 19  22 ]
         *             [ 43  50 ]
         */
        Matrix2x2 multiplyExpected =
                new Matrix2x2(
                        new ComplexNumber(19.0, 0.0),
                        new ComplexNumber(22.0, 0.0),
                        new ComplexNumber(43.0, 0.0),
                        new ComplexNumber(50.0, 0.0)
                );

        Matrix2x2 multiplyComputed = addLeft.multiply(addRight);

        if (!Matrix2x2AreClose(multiplyComputed, multiplyExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestMatrix2x2: multiplication failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 4:
         * Identity matrix on the left
         * ------------------------------------------------------------
         *
         * The identity matrix should satisfy:
         *
         *     I * A = A
         */
        Matrix2x2 identity = Matrix2x2.identity();
        Matrix2x2 leftIdentityComputed = identity.multiply(addLeft);

        if (!Matrix2x2AreClose(leftIdentityComputed, addLeft)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestMatrix2x2: left identity failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 5:
         * Identity matrix on the right
         * ------------------------------------------------------------
         *
         * The identity matrix should also satisfy:
         *
         *     A * I = A
         */
        Matrix2x2 rightIdentityComputed = addLeft.multiply(identity);

        if (!Matrix2x2AreClose(rightIdentityComputed, addLeft)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestMatrix2x2: right identity failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 6:
         * Zero matrix multiplication
         * ------------------------------------------------------------
         *
         * The zero matrix should satisfy:
         *
         *     A * 0 = 0
         *     0 * A = 0
         */
        Matrix2x2 zero = Matrix2x2.zero();

        Matrix2x2 rightZeroComputed = addLeft.multiply(zero);
        Matrix2x2 leftZeroComputed = zero.multiply(addLeft);

        if (!Matrix2x2AreClose(rightZeroComputed, zero)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestMatrix2x2: right zero failed"
            );
            allTestsOkay = false;
        }

        if (!Matrix2x2AreClose(leftZeroComputed, zero)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestMatrix2x2: left zero failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 7:
         * Trace
         * ------------------------------------------------------------
         *
         * For:
         *
         *     [ 1  2 ]
         *     [ 3  4 ]
         *
         * the trace is:
         *
         *     1 + 4 = 5
         */
        ComplexNumber traceExpected = new ComplexNumber(5.0, 0.0);
        ComplexNumber traceComputed = addLeft.trace();

        if (!ComplexNumbersAreClose(traceComputed, traceExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestMatrix2x2: trace failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Test 8:
         * Determinant
         * ------------------------------------------------------------
         *
         * For:
         *
         *     [ 1  2 ]
         *     [ 3  4 ]
         *
         * the determinant is:
         *
         *     1*4 - 2*3 = -2
         */
        ComplexNumber determinantExpected = new ComplexNumber(-2.0, 0.0);
        ComplexNumber determinantComputed = addLeft.determinant();

        if (!ComplexNumbersAreClose(determinantComputed, determinantExpected)) {
            System.out.println(
                    "%%%%%%%%%%%%%% Error: TestMatrix2x2: determinant failed"
            );
            allTestsOkay = false;
        }

        /*
         * ------------------------------------------------------------
         * Final success line
         * ------------------------------------------------------------
         */
        if (allTestsOkay) {
            System.out.println("*** TestMatrix2x2() Okay");
        }
    }


}