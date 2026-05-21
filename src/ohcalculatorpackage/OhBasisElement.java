package ohcalculatorpackage;

/*
 * OhBasisElement
 *
 * This class stores metadata for one specific basis element of the Oh group.
 *
 * VERY IMPORTANT DISTINCTION
 * --------------------------
 * This class does NOT represent:
 *
 * - a general Oh group algebra value,
 * - a sum of group elements,
 * - or a coefficient array.
 *
 * Instead, it stores information about one single group element in the
 * fixed basis ordering that the calculator will use internally.
 *
 * So the roles of the classes are:
 *
 *     OhClassInfo    = metadata for one conjugacy class
 *     OhBasisElement = metadata for one group element
 *     OhElement      = a general group algebra value with coefficients
 *
 * WHY THIS CLASS EXISTS
 * ---------------------
 * The full Oh group has 48 elements.
 *
 * For each one, we want to remember things such as:
 *
 * - its basis index from 0 to 47,
 * - which conjugacy class it belongs to,
 * - its permutation label,
 * - its 3-letter axis-image label,
 * - whether it includes inversion,
 * - which element is its group inverse.
 *
 * Keeping that information in a dedicated class makes the algebra code
 * much easier to understand.
 *
 * DESIGN PRINCIPLE
 * ----------------
 * The multiplication table should run on internal indices.
 * Human-readable labels are attached as metadata.
 *
 * That is exactly what this class supports.
 *
 * @author Carl Brannen and ChatGPT
 */
public class OhBasisElement {

    /*
     * elementIndex
     *
     * This is the internal index of the basis element.
     *
     * We intend to number the 48 group elements from:
     *
     *     0 through 47
     *
     * according to the fixed class ordering chosen for the project.
     *
     * This is the most important internal identifier because the
     * multiplication table will use these indices directly.
     */
    private final int elementIndex;

    /*
     * classIndex
     *
     * This tells which conjugacy class contains the current element.
     *
     * The class indices are intended to run from:
     *
     *     0 through 9
     *
     * matching the 10 classes of Oh in the chosen ordering.
     */
    private final int classIndex;

    /*
     * permutationLabel
     *
     * This is the human-readable group-element label in permutation notation.
     *
     * Example values might include:
     *
     *     ()
     *     (12)(34)
     *     (123)
     *     (1324)
     *     ()i
     *     (12)(34)i
     *
     * This label is especially useful when discussing the mathematics.
     */
    private final String permutationLabel;

    /*
     * axisImageLabel
     *
     * This is the short 3-letter axis-image label.
     *
     * Example values include:
     *
     *     xyz
     *     xYZ
     *     Zyx
     *     XYZ
     *
     * In this notation:
     *
     * - lower-case letters mean the positive axis image,
     * - upper-case letters mean the negative axis image.
     *
     * For example:
     *
     *     xyz  = identity
     *     XYZ  = inversion
     *
     * This is a very compact and useful geometric description.
     */
    private final String axisImageLabel;

    /*
     * isInverted
     *
     * This is true exactly when the group element includes the inversion.
     *
     * For example:
     *
     *     ()      -> false
     *     (123)   -> false
     *     ()i     -> true
     *     (123)i  -> true
     *
     * This field is not strictly necessary because it can often be inferred
     * from the label, but storing it directly makes later code simpler and
     * clearer.
     */
    private final boolean isInverted;

    /*
     * inverseElementIndex
     *
     * This stores the basis index of the inverse group element.
     *
     * WHY THIS IS USEFUL
     * ------------------
     * The inverse is important for:
     *
     * - testing the multiplication table,
     * - constructing certain algebraic operations,
     * - debugging,
     * - later Fourier-transform-related constructions.
     *
     * Instead of recomputing or searching for inverses repeatedly, we store
     * the inverse index directly as metadata.
     */
    private final int inverseElementIndex;

    /*
     * Constructor
     *
     * This creates one OhBasisElement object from the supplied metadata.
     *
     * INPUT ORDER
     * -----------
     * The input order is:
     *
     *     elementIndex,
     *     classIndex,
     *     permutationLabel,
     *     axisImageLabel,
     *     isInverted,
     *     inverseElementIndex
     *
     * This order starts with the structural integer data, then the labels,
     * and finally the boolean and inverse information.
     */
    public OhBasisElement(
            int elementIndex,
            int classIndex,
            String permutationLabel,
            String axisImageLabel,
            boolean isInverted,
            int inverseElementIndex) {

        /*
         * Store the supplied metadata directly into the object's fields.
         *
         * The fields are final, because once a basis element is defined,
         * its metadata should not change.
         */
        this.elementIndex = elementIndex;
        this.classIndex = classIndex;
        this.permutationLabel = permutationLabel;
        this.axisImageLabel = axisImageLabel;
        this.isInverted = isInverted;
        this.inverseElementIndex = inverseElementIndex;
    }

    /*
     * getElementIndex
     *
     * Returns the internal basis index of the group element.
     */
    public int getElementIndex() {
        return elementIndex;
    }

    /*
     * getClassIndex
     *
     * Returns the conjugacy-class index of the group element.
     */
    public int getClassIndex() {
        return classIndex;
    }

    /*
     * getPermutationLabel
     *
     * Returns the permutation-notation label.
     */
    public String getPermutationLabel() {
        return permutationLabel;
    }

    /*
     * getAxisImageLabel
     *
     * Returns the 3-letter axis-image label.
     */
    public String getAxisImageLabel() {
        return axisImageLabel;
    }

    /*
     * getIsInverted
     *
     * Returns true exactly when this element includes inversion.
     *
     * NOTE ON METHOD NAME
     * -------------------
     * A more industry-standard Java name might be:
     *
     *     isInverted()
     *
     * But for this project, we are favoring clarity and consistency with
     * the other getter names, so getIsInverted() is perfectly acceptable.
     */
    public boolean getIsInverted() {
        return isInverted;
    }

    /*
     * getInverseElementIndex
     *
     * Returns the basis index of the inverse group element.
     */
    public int getInverseElementIndex() {
        return inverseElementIndex;
    }

    /*
     * toString
     *
     * Converts the metadata object into a readable one-line string.
     *
     * WHY THIS IS HELPFUL
     * -------------------
     * This is useful for:
     *
     * - debugging,
     * - checking that the basis ordering was entered correctly,
     * - printing tables,
     * - verifying inverse assignments.
     *
     * We print all important fields so that one line tells the whole story
     * of the basis element.
     */
    @Override
    public String toString() {
        return "elementIndex=" + elementIndex
                + ", classIndex=" + classIndex
                + ", permutationLabel=" + permutationLabel
                + ", axisImageLabel=" + axisImageLabel
                + ", isInverted=" + isInverted
                + ", inverseElementIndex=" + inverseElementIndex;
    }
}