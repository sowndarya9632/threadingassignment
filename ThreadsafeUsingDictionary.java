package synchronizeconcept;

import java.util.HashMap;
import java.util.Map;

public class ThreadsafeUsingDictionary {
    private final Map<String, String> dictionary;

    public ThreadsafeUsingDictionary() {
        this.dictionary = new HashMap<>();
    }

    public synchronized void put(String key, String value) {
        dictionary.put(key, value);
    }

    public synchronized String get(String key) {
        return dictionary.get(key);
    }

    public synchronized void remove(String key) {
        dictionary.remove(key);
    }

    public synchronized boolean containsKey(String key) {
        return dictionary.containsKey(key);
    }

    public static void main(String[] args) {
        ThreadsafeUsingDictionary tsDict = new ThreadsafeUsingDictionary();

        // Sample usage
        tsDict.put("key1", "value1");
        System.out.println("Get key1: " + tsDict.get("key1"));

        tsDict.remove("key1");
        System.out.println("Contains key1: " + tsDict.containsKey("key1"));
    }
}

