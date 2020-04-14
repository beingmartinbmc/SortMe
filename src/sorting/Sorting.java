package sorting;

import frames.Visualiser;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public final class Sorting {

    public static int[] values;
    public static int count = 0;
    public static int arrayAccesses = 0;
    private Sorting() {
    }

    public static int[] returnArray() {
        String length = Visualiser.length;
        return IntStream.range(0, length.length() == 0 ? 270 : Integer.parseInt(length))
                .map(index -> ThreadLocalRandom.current().nextInt(10, 301))
                .toArray();
    }

}
