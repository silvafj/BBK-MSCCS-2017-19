package sml;

import java.util.ArrayList;

/**
 * This class ....
 * <p>
 * An instance contains a list of Strings, called "labels", in the order in which they were added to the list.
 *
 * @author Fernando Silva (fdealm02)
 */
public class Labels {
    private ArrayList<String> labels;

    {
        labels = new ArrayList<>();
    }

    /**
     * Add label lab to this list and return its number in the list
     * (the first one added is number 0)
     * Precondition: the list has at most 49 entries
     *
     * @param lab
     * @return
     */
    public int addLabel(String lab) {
        labels.add(lab);
        return labels.size() - 1;
    }

    /**
     * = the number of label lab in the list
     * (= -1 if lab is not in the list)
     *
     * @param lab
     * @return
     */
    public int indexOf(String lab) {

        // invariant: lab is not in labels[0..i-1]
        for (int i = 0; i != labels.size(); i++) {
            if (lab.equals(labels.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * representation of this instance, "(label 0, label 1, ..., label (n-1))"
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder r = new StringBuilder("(");
        // invariant: r contains the representation for labels[0..i-1]
        // (with the opening "(" but no closing ")")
        for (int i = 0; i != labels.size(); i++) {
            if (i == 0) {
                r.append(labels.get(i));
            } else {
                r.append(", ").append(labels.get(i));
            }
        }
        r.append(")");
        return r.toString();
    }

    /**
     * Set the number of elements in the list to 0
     */
    public void reset() {
        labels.clear();
    }
}
