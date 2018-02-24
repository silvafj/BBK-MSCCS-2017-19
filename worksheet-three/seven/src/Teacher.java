package seven;

/**
 *
 */
public class Teacher {
    private String name;

    /**
     *
     * @param name
     */
    public Teacher(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param lessonName
     */
    public void teach(String lessonName) {
        System.out.println("Teaching lesson: " + lessonName);
    }
}