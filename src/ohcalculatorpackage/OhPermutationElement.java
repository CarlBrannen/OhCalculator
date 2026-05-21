package ohcalculatorpackage;

/*
 * OhPermutationElement
 *
 * This class represents one element of the full octahedral group O_h
 * in the permutation-plus-inversion description.
 *
 * MATHEMATICAL DESCRIPTION
 * ------------------------
 * Every element is described by:
 *
 * 1. a permutation of the four body diagonals of the cube,
 *    represented here as a permutation of {1,2,3,4}
 *
 * 2. an optional inversion flag
 *
 * So a general element looks like:
 *
 *     permutationPart
 *
 * or
 *
 *     permutationPart followed by i
 *
 * such as:
 *
 *     ()
 *     (12)
 *     (123)
 *     (1234)
 *     (12)(34)
 *     ()i
 *     (123)i
 *
 * WHY THIS CLASS EXISTS
 * ---------------------
 * We want the group multiplication to be defined from the permutation
 * description that we trust, rather than from any secondary representation.
 *
 * This class is therefore intended to become the foundation for:
 *
 * - multiplication-table generation
 * - basis-element definitions
 * - permutation-label verification
 *
 * PROGRAMMING STYLE
 * -----------------
 * This class is written in explicit, heavily commented style so that a
 * beginning graduate student can read it line by line and understand it.
 *
 * IMPORTANT REPRESENTATION CONVENTION
 * -----------------------------------
 * The permutation is stored as an array of length 4 where:
 *
 *     permutation[0] = image of 1
 *     permutation[1] = image of 2
 *     permutation[2] = image of 3
 *     permutation[3] = image of 4
 *
 * Example:
 *
 *     permutation = {2,1,3,4}
 *
 * means the transposition (12).
 *
 * @author Carl Brannen and ChatGPT
 */
public class OhPermutationElement {

    /*
     * permutation
     *
     * This is the permutation part of the group element.
     *
     * The meaning is:
     *
     *     permutation[k - 1] = image of k
     *
     * for k = 1,2,3,4.
     */
    private final int[] permutation;

    /*
     * hasInversion
     *
     * This is true exactly when the element has the extra inversion factor i.
     *
     * Examples:
     *
     *     ()      -> false
     *     (123)   -> false
     *     ()i     -> true
     *     (123)i  -> true
     */
    private final boolean hasInversion;

    /*
     * Constructor
     *
     * Constructs one OhPermutationElement from:
     *
     * - a permutation array of length 4
     * - an inversion flag
     *
     * SAFETY CHECKS
     * -------------
     * We verify that the permutation is valid, meaning that it is a true
     * permutation of {1,2,3,4}.
     *
     * We also copy the array so that the internal state cannot be modified
     * from outside.
     */
    public OhPermutationElement(int[] inputPermutation, boolean inputHasInversion) {

        if (inputPermutation == null) {
            throw new IllegalArgumentException(
                    "OhPermutationElement constructor received null permutation."
            );
        }

        if (inputPermutation.length != 4) {
            throw new IllegalArgumentException(
                    "OhPermutationElement constructor requires permutation length 4."
            );
        }

        if (!IsValidPermutation(inputPermutation)) {
            throw new IllegalArgumentException(
                    "OhPermutationElement constructor received invalid permutation."
            );
        }

        this.permutation = new int[4];

        for (int index = 0; index < 4; index++) {
            this.permutation[index] = inputPermutation[index];
        }

        this.hasInversion = inputHasInversion;
    }

    /*
     * identity
     *
     * Returns the identity element:
     *
     *     ()
     */
    public static OhPermutationElement identity() {
        return new OhPermutationElement(new int[]{1, 2, 3, 4}, false);
    }

    /*
     * inversion
     *
     * Returns the pure inversion element:
     *
     *     ()i
     */
    public static OhPermutationElement inversion() {
        return new OhPermutationElement(new int[]{1, 2, 3, 4}, true);
    }

    /*
     * fromLabel
     *
     * Parses one project-style permutation label and returns the corresponding
     * OhPermutationElement.
     *
     * SUPPORTED INPUTS
     * ----------------
     * Examples:
     *
     *     ()
     *     (12)
     *     (123)
     *     (1234)
     *     (12)(34)
     *     ()i
     *     (12)i
     *     (1234)i
     *
     * IMPORTANT PARSING RULE
     * ----------------------
     * Multiple cycles such as (12)(34) are applied from left to right exactly
     * as written in the label string.
     */
    public static OhPermutationElement fromLabel(String label) {

        if (label == null) {
            throw new IllegalArgumentException(
                    "OhPermutationElement.fromLabel received null label."
            );
        }

        boolean inversionFlag = false;
        String permutationPart = label;

        /*
         * If the label ends in i, remove that final character and record the
         * inversion flag separately.
         */
        if (label.endsWith("i")) {
            inversionFlag = true;
            permutationPart = label.substring(0, label.length() - 1);
        }

        /*
         * Start from the identity permutation and apply each cycle in turn.
         */
        int[] workingPermutation = {1, 2, 3, 4};

        int position = 0;
        while (position < permutationPart.length()) {

            if (permutationPart.charAt(position) == '(') {

                int closingPosition = permutationPart.indexOf(')', position);

                if (closingPosition < 0) {
                    throw new IllegalArgumentException(
                            "OhPermutationElement.fromLabel found unmatched '(' in label: " + label
                    );
                }

                String cycleText =
                        permutationPart.substring(position + 1, closingPosition);

                /*
                 * The empty cycle () means identity and changes nothing.
                 */
                if (!cycleText.equals("")) {
                    int[] cyclePermutation = BuildCyclePermutation(cycleText);
                    workingPermutation =
                            ComposePermutations(workingPermutation, cyclePermutation);
                }

                position = closingPosition + 1;
            } else {
                /*
                 * Ignore any character not part of a cycle. This keeps the parser
                 * simple and tolerant of the final i having already been removed.
                 */
                position++;
            }
        }

        return new OhPermutationElement(workingPermutation, inversionFlag);
    }

    /*
     * multiply
     *
     * Multiplies this element by another OhPermutationElement.
     *
     * CONVENTION
     * ----------
     * We use the same convention we have used elsewhere in the project:
     *
     *     left followed by right
     *
     * so if this = left and other = right, the resulting permutation is:
     *
     *     right ∘ left
     *
     * The inversion flags combine by XOR because:
     *
     *     i * i = identity
     */
    public OhPermutationElement multiply(OhPermutationElement other) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "OhPermutationElement.multiply received null other element."
            );
        }

        int[] productPermutation =
                ComposePermutations(this.permutation, other.permutation);

        boolean productInversion =
                this.hasInversion ^ other.hasInversion;

        return new OhPermutationElement(productPermutation, productInversion);
    }

    /*
     * getPermutation
     *
     * Returns a defensive copy of the permutation array.
     *
     * We return a copy rather than the internal array so that callers cannot
     * accidentally modify the object's internal state.
     */
    public int[] getPermutation() {

        int[] copy = new int[4];

        for (int index = 0; index < 4; index++) {
            copy[index] = this.permutation[index];
        }

        return copy;
    }

    /*
     * hasInversion
     *
     * Returns true exactly when this element carries the inversion flag i.
     */
    public boolean hasInversion() {
        return hasInversion;
    }

    /*
     * toLabel
     *
     * Converts this element back into the project's canonical label form.
     *
     * EXAMPLES
     * --------
     * identity, no inversion:
     *
     *     ()
     *
     * identity, with inversion:
     *
     *     ()i
     *
     * transposition with inversion:
     *
     *     (12)i
     */
    public String toLabel() {

        boolean[] visited = new boolean[4];
        String cycleString = "";

        /*
         * We build disjoint cycles in increasing starting-point order.
         */
        for (int start = 1; start <= 4; start++) {

            if (visited[start - 1]) {
                continue;
            }

            int next = permutation[start - 1];

            /*
             * Fixed points are omitted from cycle notation.
             */
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

        /*
         * If there were no nontrivial cycles, this is the identity permutation.
         */
        if (cycleString.equals("")) {
            cycleString = "()";
        }

        if (hasInversion) {
            cycleString += "i";
        }

        return cycleString;
    }

    /*
     * toString
     *
     * The natural string form of this object is its project-style label.
     */
    @Override
    public String toString() {
        return toLabel();
    }

    /*
     * equals
     *
     * Two OhPermutationElement objects are equal exactly when:
     *
     * - their inversion flags are equal
     * - and their permutations are identical
     */
    @Override
    public boolean equals(Object otherObject) {

        if (this == otherObject) {
            return true;
        }

        if (!(otherObject instanceof OhPermutationElement)) {
            return false;
        }

        OhPermutationElement other = (OhPermutationElement) otherObject;

        if (this.hasInversion != other.hasInversion) {
            return false;
        }

        for (int index = 0; index < 4; index++) {
            if (this.permutation[index] != other.permutation[index]) {
                return false;
            }
        }

        return true;
    }

    /*
     * hashCode
     *
     * Since we overrode equals, we also provide a matching hashCode.
     */
    @Override
    public int hashCode() {

        int hash = hasInversion ? 1 : 0;

        for (int index = 0; index < 4; index++) {
            hash = 31 * hash + permutation[index];
        }

        return hash;
    }

    /*
     * IsValidPermutation
     *
     * Returns true exactly when the array is a valid permutation of {1,2,3,4}.
     */
    private static boolean IsValidPermutation(int[] candidate) {

        boolean[] seen = new boolean[4];

        for (int index = 0; index < 4; index++) {

            int value = candidate[index];

            if (value < 1 || value > 4) {
                return false;
            }

            if (seen[value - 1]) {
                return false;
            }

            seen[value - 1] = true;
        }

        return true;
    }

    /*
     * BuildCyclePermutation
     *
     * Builds the permutation corresponding to one cycle text such as:
     *
     *     "12"
     *     "123"
     *     "1234"
     *
     * The returned permutation is a full 4-entry permutation array.
     */
    private static int[] BuildCyclePermutation(String cycleText) {

        int cycleLength = cycleText.length();

        if (cycleLength < 2) {
            throw new IllegalArgumentException(
                    "Cycle text must have length at least 2: " + cycleText
            );
        }

        int[] cycleEntries = new int[cycleLength];

        for (int index = 0; index < cycleLength; index++) {
            int digitValue = Character.digit(cycleText.charAt(index), 10);

            if (digitValue < 1 || digitValue > 4) {
                throw new IllegalArgumentException(
                        "Invalid digit in cycle text: " + cycleText
                );
            }

            cycleEntries[index] = digitValue;
        }

        int[] cyclePermutation = {1, 2, 3, 4};

        for (int index = 0; index < cycleLength; index++) {
            int from = cycleEntries[index];
            int to = cycleEntries[(index + 1) % cycleLength];
            cyclePermutation[from - 1] = to;
        }

        return cyclePermutation;
    }

    /*
     * ComposePermutations
     *
     * Returns:
     *
     *     right ∘ left
     *
     * on the set {1,2,3,4}.
     *
     * If:
     *
     *     left sends k to left[k-1]
     *     right then sends that image onward,
     *
     * then the result sends k to:
     *
     *     right[left[k-1] - 1]
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
}