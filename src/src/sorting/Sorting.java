package sorting;

import frames.Visualiser;

import java.util.concurrent.ThreadLocalRandom;

public class Sorting {
    public static int[] values;
    public static int count = 0;
    public static int arrayAccesses = 0;

    public static int[] returnArray() {
        String length = Visualiser.length;
        int[] array = new int[length.length() == 0 ? 270 : Integer.parseInt(length)];
        for (int index = 0; index < array.length; index++) {
            array[index] = ThreadLocalRandom.current().nextInt(10, 301);
        }
        return array;
    }

}
