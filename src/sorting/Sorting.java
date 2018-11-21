package sorting;

import java.util.concurrent.ThreadLocalRandom;

public class Sorting {
    public static int[] values;
    public static int count = 0;
    public static int arrayAccesses = 0;

    public static int[] returnArray() {
        int array[] = new int[300];
        for (int index = 0; index < array.length; index++) {
            array[index] = ThreadLocalRandom.current().nextInt(10, 301);
        }
        return array;
    }

}
