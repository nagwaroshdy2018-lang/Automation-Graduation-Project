package utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates unique emails/names so each test run uses fresh data.
 */
public class TestDataGenerator {

    private TestDataGenerator() {}

    /** Unique email like: nagwa_test_1719500000123@test.com */
    public static String uniqueEmail() {
        long ts = System.currentTimeMillis();
        int rnd = ThreadLocalRandom.current().nextInt(100, 999);
        return "nagwa_test_" + ts + rnd + "@test.com";
    }

    public static String uniqueName() {
        return "Nagwa" + ThreadLocalRandom.current().nextInt(100, 9999);
    }
}