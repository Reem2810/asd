// StackPerformanceTest.java
package nl.han.asd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StackPerformanceTest {
    private static final int ELEMENT_COUNT = 100_000;

    private IStack<String> stackArrayList;
    private IStack<String> stackDynamicArray;
    private IStack<String> stackLinkedList;

    @BeforeEach
    void setup() {
        stackArrayList = new StackOfArrayList<>();
        stackDynamicArray = new StackOfDynamicArray<>();
        stackLinkedList = new StackOfLinkedList<>();
    }

    @Test
    void testPushPerformance() {
        System.out.println("=== Push Performance ===");

        // StackOfArrayList
        long start = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            stackArrayList.push("Element " + i);
        }
        long end = System.nanoTime();
        long timeArrayList = end - start;
        System.out.println("StackOfArrayList push time: " + timeArrayList / 1_000_000 + " ms");

        // StackOfDynamicArray
        start = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            stackDynamicArray.push("Element " + i);
        }
        end = System.nanoTime();
        long timeDynamicArray = end - start;
        System.out.println("StackOfDynamicArray push time: " + timeDynamicArray / 1_000_000 + " ms");

        // StackOfLinkedList
        start = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            stackLinkedList.push("Element " + i);
        }
        end = System.nanoTime();
        long timeLinkedList = end - start;
        System.out.println("StackOfLinkedList push time: " + timeLinkedList / 1_000_000 + " ms");

        // Assertions to ensure all stacks have the correct size
        assertEquals(ELEMENT_COUNT, stackArrayList.size());
        assertEquals(ELEMENT_COUNT, stackDynamicArray.size());
        assertEquals(ELEMENT_COUNT, stackLinkedList.size());
    }

    @Test
    void testPopPerformance() {
        System.out.println("=== Pop Performance ===");

        // Pre-populate stacks
        populateStacks();

        // StackOfArrayList
        long start = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            stackArrayList.pop();
        }
        long end = System.nanoTime();
        long timeArrayList = end - start;
        System.out.println("StackOfArrayList pop time: " + timeArrayList / 1_000_000 + " ms");

        // StackOfDynamicArray
        start = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            stackDynamicArray.pop();
        }
        end = System.nanoTime();
        long timeDynamicArray = end - start;
        System.out.println("StackOfDynamicArray pop time: " + timeDynamicArray / 1_000_000 + " ms");

        // StackOfLinkedList
        start = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            stackLinkedList.pop();
        }
        end = System.nanoTime();
        long timeLinkedList = end - start;
        System.out.println("StackOfLinkedList pop time: " + timeLinkedList / 1_000_000 + " ms");

        // Assertions to ensure all stacks are empty
        assertTrue(stackArrayList.isEmpty());
        assertTrue(stackDynamicArray.isEmpty());
        assertTrue(stackLinkedList.isEmpty());
    }

    @Test
    void testPeekPerformance() {
        System.out.println("=== Peek Performance ===");

        // Pre-populate stacks
        populateStacks();

        // StackOfArrayList
        long start = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            stackArrayList.peek();
        }
        long end = System.nanoTime();
        long timeArrayList = end - start;
        System.out.println("StackOfArrayList peek time: " + timeArrayList / 1_000_000 + " ms");

        // StackOfDynamicArray
        start = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            stackDynamicArray.peek();
        }
        end = System.nanoTime();
        long timeDynamicArray = end - start;
        System.out.println("StackOfDynamicArray peek time: " + timeDynamicArray / 1_000_000 + " ms");

        // StackOfLinkedList
        start = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            stackLinkedList.peek();
        }
        end = System.nanoTime();
        long timeLinkedList = end - start;
        System.out.println("StackOfLinkedList peek time: " + timeLinkedList / 1_000_000 + " ms");
    }

    private void populateStacks() {
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            String element = "Element " + i;
            stackArrayList.push(element);
            stackDynamicArray.push(element);
            stackLinkedList.push(element);
        }
    }

    @Test
    void testMixedOperationsPerformance() {
        System.out.println("=== Mixed Operations Performance/push+pop ===");

        // Define a smaller number for mixed operations to avoid long test times
        int operations = 50_000;

        // StackOfArrayList
        long start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            stackArrayList.push("Push " + i);
            stackArrayList.pop();
        }
        long end = System.nanoTime();
        long timeArrayList = end - start;
        System.out.println("StackOfArrayList mixed operations time: " + timeArrayList / 1_000_000 + " ms");

        // StackOfDynamicArray
        start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            stackDynamicArray.push("Push " + i);
            stackDynamicArray.pop();
        }
        end = System.nanoTime();
        long timeDynamicArray = end - start;
        System.out.println("StackOfDynamicArray mixed operations time: " + timeDynamicArray / 1_000_000 + " ms");

        // StackOfLinkedList
        start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            stackLinkedList.push("Push " + i);
            stackLinkedList.pop();
        }
        end = System.nanoTime();
        long timeLinkedList = end - start;
        System.out.println("StackOfLinkedList mixed operations time: " + timeLinkedList / 1_000_000 + " ms");

        // Assertions to ensure all stacks are empty
        assertTrue(stackArrayList.isEmpty());
        assertTrue(stackDynamicArray.isEmpty());
        assertTrue(stackLinkedList.isEmpty());
    }
}
