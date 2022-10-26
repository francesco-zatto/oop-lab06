package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
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
        String message = "Inserting 100_000 ints in a ";
        printTimes(firstList, message, "insert");
        printTimes(secondList, message, "insert");
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both firstList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */
        message = "Reading 1000 times in a ";
        printTimes(firstList, message, "read");
        printTimes(secondList, message, "read");
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
        final Map<String, Long> continentsMap = new HashMap<>();
        final List<String> continentKeys = List.of("Africa", "Americas", "Antarctica", "Asia", "Europe", "Oceania");
        final List<Long> continentPopulations = List.of(1_110_635_000L, 972_005_000L, 0L,
             4_298_723_000L, 742_452_000L, 38_304_000L);
        for (int i = 0; i < continentKeys.size(); i++) {
            continentsMap.put(continentKeys.get(i), continentPopulations.get(i));
        }
        /*
         * 8) Compute the population of the world
         */
        long worldPopulation = 0;
        for (final var key : continentsMap.keySet()) {
            worldPopulation = worldPopulation + continentsMap.get(key);
        }
        System.out.println("The population of the world: " + worldPopulation);
    }

    private static void printTimes(final List<Integer> list, final String message, final String op) {
        long time = System.nanoTime();
        final int numOperations = op == "insert" ? 100_000 : 1000;
        for (int i = 0; i < numOperations; i++) {
            listOperation(list, op);
        }
        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(message + list.getClass() + " took " + time + "ns (" + millis + "ms)");
    }

    private static void listOperation(final List<Integer> list, final String op) {
        if (op == "insert") {
            list.add(0, 1);
        }
        if (op == "read") {
            list.get(list.size() / 2);
        }
    }
}
