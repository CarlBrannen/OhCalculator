package ohcalculatorpackage;

/*
 * OhClassInfo
 *
 * This class stores information about one conjugacy class of the Oh group.
 *
 * IMPORTANT IDEA
 * --------------
 * This class does NOT represent an element of the group algebra.
 * It does NOT represent a group element either.
 *
 * Instead, it stores metadata describing one conjugacy class.
 *
 * In other words:
 *
 *     OhClassInfo = information about a class
 *
 * and later:
 *
 *     OhBasisElement = information about one group element
 *     OhElement      = a group algebra value with coefficients
 *
 * WHY SEPARATE THIS OUT?
 * ----------------------
 * We separate class information into its own class because this makes the
 * code easier to understand and easier to maintain.
 *
 * A conjugacy class has properties such as:
 *
 * - a class index,
 * - a short class code such as K_xyz,
 * - a representative permutation label,
 * - a representative axis-image label,
 * - a class size.
 *
 * Those pieces of information belong together naturally.
 *
 * PROGRAMMING STYLE
 * -----------------
 * This project is written in a heavily commented style because one of the
 * goals is that graduate students should be able to read the code, modify it,
 * and adapt it to other finite groups without getting lost.
 *
 * Therefore, this class is intentionally simple, explicit, and fully explained.
 *
 * @author Carl Brannen and ChatGPT
 */
public class OhClassInfo {

    /*
     * classIndex
     *
     * This is the internal integer index of the conjugacy class.
     *
     * We intend to number the 10 classes as:
     *
     *     0 through 9
     *
     * This integer index is useful for:
     *
     * - arrays,
     * - lookup tables,
     * - test code,
     * - debugging output.
     */
    private final int classIndex;

    /*
     * classCode
     *
     * This is the short fixed-width class name.
     *
     * Example values include:
     *
     *     K_xyz
     *     K_xYZ
     *     K_YXZ
     *
     * We like these names because they are:
     *
     * - compact,
     * - easy to scan in code,
     * - visually distinct from group elements,
     * - all of similar length.
     */
    private final String classCode;

    /*
     * representativePermutationLabel
     *
     * This is the chosen representative of the conjugacy class written in
     * permutation notation.
     *
     * Example values might include:
     *
     *     ()
     *     (12)(34)
     *     (123)
     *     ()i
     *
     * The representative is useful because it gives a mathematically familiar
     * name for the class.
     */
    private final String representativePermutationLabel;

    /*
     * representativeAxisImageLabel
     *
     * This is the chosen representative written in the 3-letter axis-image
     * notation.
     *
     * Example values include:
     *
     *     xyz
     *     xYZ
     *     XYZ
     *
     * This notation is compact and geometrically meaningful.
     */
    private final String representativeAxisImageLabel;

    /*
     * classSize
     *
     * This is the number of group elements in the conjugacy class.
     *
     * For Oh, the class sizes are intended to be:
     *
     *     1, 3, 8, 6, 6, 1, 3, 8, 6, 6
     *
     * depending on which class is being described.
     */
    private final int classSize;

    /*
     * Constructor
     *
     * This creates one OhClassInfo object from the supplied data.
     *
     * INPUT ORDER
     * -----------
     * The input order is:
     *
     *     classIndex,
     *     classCode,
     *     representativePermutationLabel,
     *     representativeAxisImageLabel,
     *     classSize
     *
     * This order is chosen so that the structural data comes first,
     * followed by the human-readable labels.
     */
    public OhClassInfo(
            int classIndex,
            String classCode,
            String representativePermutationLabel,
            String representativeAxisImageLabel,
            int classSize) {

        /*
         * Store the input data directly into the fields of the object.
         *
         * The fields are "final", meaning that once the object is created,
         * these values cannot be changed.
         *
         * That is appropriate here because class metadata should be fixed.
         */
        this.classIndex = classIndex;
        this.classCode = classCode;
        this.representativePermutationLabel = representativePermutationLabel;
        this.representativeAxisImageLabel = representativeAxisImageLabel;
        this.classSize = classSize;
    }

    /*
     * getClassIndex
     *
     * Returns the integer index of the class.
     */
    public int getClassIndex() {
        return classIndex;
    }

    /*
     * getClassCode
     *
     * Returns the short class code, for example K_xyz.
     */
    public String getClassCode() {
        return classCode;
    }

    /*
     * getRepresentativePermutationLabel
     *
     * Returns the class representative written in permutation notation.
     */
    public String getRepresentativePermutationLabel() {
        return representativePermutationLabel;
    }

    /*
     * getRepresentativeAxisImageLabel
     *
     * Returns the class representative written in axis-image notation.
     */
    public String getRepresentativeAxisImageLabel() {
        return representativeAxisImageLabel;
    }

    /*
     * getClassSize
     *
     * Returns the number of elements in the conjugacy class.
     */
    public int getClassSize() {
        return classSize;
    }

    /*
     * toString
     *
     * This converts the object into a readable string.
     *
     * WHY THIS IS USEFUL
     * ------------------
     * A readable toString() method is extremely helpful for:
     *
     * - debugging,
     * - printing tables,
     * - writing quick test output,
     * - understanding what metadata is actually stored.
     *
     * We deliberately print all the important fields in a compact but readable
     * form.
     */
    @Override
    public String toString() {
        return "classIndex=" + classIndex
                + ", classCode=" + classCode
                + ", representativePermutationLabel=" + representativePermutationLabel
                + ", representativeAxisImageLabel=" + representativeAxisImageLabel
                + ", classSize=" + classSize;
    }
}