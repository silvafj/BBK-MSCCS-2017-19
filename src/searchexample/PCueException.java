package searchexample;

public class PCueException extends RuntimeException {

    /**
     * Constructor: an instance with message m
     */
    public PCueException(String m) {
        super(m);
    }

    /**
     * Constructor: an instance with no message
     */
    public PCueException() {
        super();
    }
}
