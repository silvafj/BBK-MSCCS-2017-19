package singletonpattern;

final public class SingletonProtected {

    private static final SingletonProtected INSTANCE = new SingletonProtected();

    private static SingletonProtected lazyInstance;


    private SingletonProtected() {
        //
    }

    /**
     * Returns a previously instantiated object. No runtime penalty, but occupies memory even if never used.
     *
     * @return SingletonProtected
     */
    public static SingletonProtected getInstance() {
        return INSTANCE;
    }

    /**
     * This lazy approach has the advantage that only when the instance is required it will be built.
     * It is memory efficient with the runtime penalty of taking more time to instantiate the first time.
     *
     * @return SingletonProtected
     */
    public static SingletonProtected getInstanceLazy() {
        if (lazyInstance == null) {
            lazyInstance = new SingletonProtected();
        }

        return lazyInstance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
