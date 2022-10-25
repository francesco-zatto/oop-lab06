package it.unibo.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> firstList = new ArrayList<>();
        for (int i = 1000; i < 2000; i++) {
            firstList.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> secondList = new LinkedList<>(firstList);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final int temp = firstList.get(firstList.size() - 1);
        firstList.set(firstList.size() - 1, firstList.get(0));
        firstList.set(0, temp);
        /*
         * 4) Using a single for-each, print the contents of the firstList.
         */
        for (final var element : firstList) {
            System.out.println(element);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both firstList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        printInsertTime(firstList);
        printInsertTime(secondList);
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both firstList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         *
        arrayTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            firstList.get(firstList.size() / 2);
        }
        arrayTime = System.nanoTime() - arrayTime;

        linkedTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            secondList.get(secondList.size() / 2);
        }
        linkedTime = System.nanoTime() - linkedTime;

        arrayMillis = TimeUnit.NANOSECONDS.toMillis(arrayTime);
        System.out.println(// NOPMD
            "Reading 1000 times ints in an firstList took "
                + arrayTime
                + "ns ("
                + arrayMillis
                + "ms)"
        );

        linkedMillis = TimeUnit.NANOSECONDS.toMillis(linkedTime);
        System.out.println(// NOPMD
            "Reading 1000 ints in a LinkedList took "
                + linkedTime
                + "ns ("
                + linkedMillis
                + "ms)"
        );
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        /*
         * 8) Compute the population of the world
         */
    }

    private static void printInsertTime(List<Integer> list) {
        long time = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            list.add(0, i);
        }
        time = System.nanoTime() - time;
        var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(// NOPMD
            "Inserting 100_000 ints in a "
                + list.getClass()
                +" took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );
    }
}
