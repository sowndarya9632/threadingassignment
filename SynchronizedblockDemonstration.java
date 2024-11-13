package multithreadingconcept;

 class Singleton {
    // Volatile variable to ensure visibility of changes across threads
    private static  Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {
    }

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        // First check (no locking)
        if (instance == null) {
            synchronized (Singleton.class) {
                // Second check (with locking)
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
