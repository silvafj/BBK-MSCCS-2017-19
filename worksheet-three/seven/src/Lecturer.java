package seven;

/**
 * A lecturer has both teaching and research responsibilities
 */
public class Lecturer extends Teacher {

    /**
     * Because Teacher class overrides the default constructor, it is required by inherited classes to also provide
     * a constructor implementation.
     *
     * @param name
     */
    public Lecturer(String name) {
        super(name);
    }

    /**
     *
     * @param topic
     */
    public void doResearch(String topic) {
        System.out.println("Doing research on: " + topic);
    }

}