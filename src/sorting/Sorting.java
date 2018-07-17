package sorting;

import java.util.concurrent.ThreadLocalRandom;

public class Sorting {
    public static int[] values = new int[270];
    public static int count = 0;

    public static int[] returnArray() {
        int array[] = new int[270];
        for (int index = 0; index < array.length; index++) {
            array[index] = ThreadLocalRandom.current().nextInt(5, 400);
        }
        return array;
    }
}
