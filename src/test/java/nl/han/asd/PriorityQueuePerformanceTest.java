// PriorityQueuePerformanceTest.java
package nl.han.asd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueuePerformanceTest {

    private PriorityQueue<Integer> priorityQueue;
    private static final int ELEMENT_COUNT = 100_000; // Number of elements for bulk operations
    private static final int MIXED_OPERATIONS = 50_000; // Number of mixed operations

    @BeforeEach
    void setup() {
        priorityQueue = new PriorityQueue<>();
    }

    @Test
    void testAddOneElement() {
        // Arrange
        Integer valueToAdd = 42;

        // Act
        long startTime = System.nanoTime();
        priorityQueue.add(valueToAdd);
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to add one element: " + (endTime - startTime) + " nanoseconds");
        assertEquals(1, priorityQueue.size(), "PriorityQueue should contain one element after adding.");
        assertFalse(priorityQueue.isEmpty(), "PriorityQueue should not be empty after adding an element.");
    }

    @Test
    void testAddMultipleElements() {
        // Arrange
        Integer[] valuesToAdd = {10, 20, 30, 40, 50};

        // Act
        long startTime = System.nanoTime();
        for (Integer value : valuesToAdd) {
            priorityQueue.add(value);
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to add " + valuesToAdd.length + " elements: " + (endTime - startTime) + " nanoseconds");
        assertEquals(valuesToAdd.length, priorityQueue.size(), "PriorityQueue should contain all added elements.");
    }

    @Test
    void testAddBulkElements() {
        // Arrange
        Random rand = new Random();

        // Act
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            priorityQueue.add(rand.nextInt());
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to add " + ELEMENT_COUNT + " elements: " + (endTime - startTime) + " nanoseconds");
        assertEquals(ELEMENT_COUNT, priorityQueue.size(), "PriorityQueue should contain all added elements.");
    }

    @Test
    void testPeekEmptyQueue() {
        // Act
        long startTime = System.nanoTime();
        Integer peeked = priorityQueue.peek();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to peek on empty queue: " + (endTime - startTime) + " nanoseconds");
        assertNull(peeked, "Peek should return null on an empty PriorityQueue.");
    }

    @Test
    void testPeekWithElements() {
        // Arrange
        priorityQueue.add(5);
        priorityQueue.add(3);
        priorityQueue.add(8);

        // Act
        long startTime = System.nanoTime();
        Integer peeked = priorityQueue.peek();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to peek with elements: " + (endTime - startTime) + " nanoseconds");
        assertEquals(3, peeked, "Peek should return the smallest element (3).");
    }

    @Test
    void testPollEmptyQueue() {
        // Act
        long startTime = System.nanoTime();
        Integer polled = priorityQueue.poll();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to poll on empty queue: " + (endTime - startTime) + " nanoseconds");
        assertNull(polled, "Poll should return null on an empty PriorityQueue.");
    }

    @Test
    void testPollWithElements() {
        // Arrange
        priorityQueue.add(15);
        priorityQueue.add(10);
        priorityQueue.add(20);

        // Act
        long startTime = System.nanoTime();
        Integer polled = priorityQueue.poll();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to poll with elements: " + (endTime - startTime) + " nanoseconds");
        assertEquals(10, polled, "Poll should return the smallest element (10).");
        assertEquals(2, priorityQueue.size(), "PriorityQueue should have two elements after polling.");
    }

    @Test
    void testPollAllElements() {
        // Arrange
        Random rand = new Random();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            priorityQueue.add(rand.nextInt());
        }

        // Act
        long startTime = System.nanoTime();
        int polledCount = 0;
        while (!priorityQueue.isEmpty()) {
            priorityQueue.poll();
            polledCount++;
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to poll all " + ELEMENT_COUNT + " elements: " + (endTime - startTime) + " nanoseconds");
        assertEquals(0, priorityQueue.size(), "PriorityQueue should be empty after polling all elements.");
        assertEquals(ELEMENT_COUNT, polledCount, "All elements should have been polled.");
    }

    @Test
    void testMixedAddAndPollOperations() {
        // Arrange
        Random rand = new Random();

        // Act
        long startTime = System.nanoTime();
        for (int i = 0; i < MIXED_OPERATIONS; i++) {
            priorityQueue.add(rand.nextInt());
            priorityQueue.poll();
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time for " + MIXED_OPERATIONS + " mixed add and poll operations: " + (endTime - startTime) + " nanoseconds");
        // Since we add and poll the same number of elements, the size should remain 0 or fluctuate slightly
        assertTrue(priorityQueue.size() <= MIXED_OPERATIONS, "PriorityQueue size should be manageable after mixed operations.");
    }

    @Test
    void testSingleElementOperations() {
        // Arrange
        priorityQueue.add(100);

        // Act
        long startTime = System.nanoTime();
        Integer peeked = priorityQueue.peek();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to peek single element: " + (endTime - startTime) + " nanoseconds");
        assertEquals(100, peeked, "Peek should return the single element added.");

        startTime = System.nanoTime();
        Integer polled = priorityQueue.poll();
        endTime = System.nanoTime();
        System.out.println("Time to poll single element: " + (endTime - startTime) + " nanoseconds");
        assertEquals(100, polled, "Poll should return the single element added.");
        assertTrue(priorityQueue.isEmpty(), "PriorityQueue should be empty after polling the single element.");
    }

    @Test
    void testRemoveAllElements() {
        // Arrange
        Random rand = new Random();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            priorityQueue.add(rand.nextInt());
        }

        // Act
        long startTime = System.nanoTime();
        while (!priorityQueue.isEmpty()) {
            priorityQueue.poll();
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to remove all " + ELEMENT_COUNT + " elements: " + (endTime - startTime) + " nanoseconds");
        assertTrue(priorityQueue.isEmpty(), "PriorityQueue should be empty after removing all elements.");
    }

    @Test
    void testHeapPropertyAfterOperations() {
        // Arrange
        Random rand = new Random();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            priorityQueue.add(rand.nextInt());
        }

        // Act & Assert
        // Indirectly verifying heap property by ensuring that each polled element is greater than or equal to the previous one
        long startTime = System.nanoTime();
        Integer previous = null;
        boolean heapPropertyMaintained = true;
        while (!priorityQueue.isEmpty()) {
            Integer current = priorityQueue.poll();
            if (previous != null && current < previous) {
                heapPropertyMaintained = false;
                break;
            }
            previous = current;
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Heap property maintained after polling all elements: " + heapPropertyMaintained);
        assertTrue(heapPropertyMaintained, "PriorityQueue should maintain the heap property.");
    }

    @Test
    void testOrderOfPolledElements() {
        // Arrange
        priorityQueue.add(50);
        priorityQueue.add(30);
        priorityQueue.add(20);
        priorityQueue.add(15);
        priorityQueue.add(10);
        priorityQueue.add(8);
        priorityQueue.add(16);

        // Act & Assert
        int[] expectedOrder = {8, 10, 15, 16, 20, 30, 50};
        for (int expected : expectedOrder) {
            Integer polled = priorityQueue.poll();
            System.out.println("Expected: " + expected + ", Polled: " + polled);
            assertEquals(expected, polled, "Polled element should match the expected order.");
        }
        assertTrue(priorityQueue.isEmpty(), "PriorityQueue should be empty after polling all elements.");
    }

    @Test
    void testPriorityQueueWithDuplicateElements() {
        // Arrange
        priorityQueue.add(10);
        priorityQueue.add(20);
        priorityQueue.add(10);
        priorityQueue.add(30);
        priorityQueue.add(20);

        // Act & Assert
        long startTime = System.nanoTime();
        while (!priorityQueue.isEmpty()) {
            priorityQueue.poll();
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to poll all elements with duplicates: " + (endTime - startTime) + " nanoseconds");
        assertTrue(priorityQueue.isEmpty(), "PriorityQueue should be empty after polling all elements.");
    }

    @Test
    void testPerformanceWithLargeNumbers() {
        // Arrange
        Random rand = new Random();
        int largeNumber = Integer.MAX_VALUE;

        // Act
        long startTime = System.nanoTime();
        priorityQueue.add(largeNumber);
        priorityQueue.add(rand.nextInt(largeNumber));
        priorityQueue.add(rand.nextInt(largeNumber));
        priorityQueue.add(rand.nextInt(largeNumber));
        priorityQueue.add(0);
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to add elements with large numbers: " + (endTime - startTime) + " nanoseconds");
        assertEquals(5, priorityQueue.size(), "PriorityQueue should contain five elements.");
    }

    @Test
    void testNullElementAddition() {
        // Act & Assert
        Exception exception = assertThrows(NullPointerException.class, () -> {
            priorityQueue.add(null);
        });
        System.out.println("Exception when adding null element: " + exception.getMessage());
    }

    @Test
    void testPeekAfterPollingAllElements() {
        // Arrange
        priorityQueue.add(5);
        priorityQueue.add(3);
        priorityQueue.add(8);

        // Act
        priorityQueue.poll();
        priorityQueue.poll();
        priorityQueue.poll();
        Integer peeked = priorityQueue.peek();

        // Result
        System.out.println("Peek after polling all elements: " + peeked);
        assertNull(peeked, "Peek should return null after polling all elements.");
    }

    @Test
    void testPriorityQueueToString() {
        // Arrange
        priorityQueue.add(10);
        priorityQueue.add(4);
        priorityQueue.add(15);
        priorityQueue.add(20);
        priorityQueue.add(0);

        // Act
        String queueString = priorityQueue.toString();

        // Result
        System.out.println("PriorityQueue toString(): " + queueString);
        assertNotNull(queueString, "toString() should not return null.");
        assertFalse(queueString.isEmpty(), "toString() should not return an empty string.");
    }
}
