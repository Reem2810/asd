package nl.han.asd;
import java.util.NoSuchElementException;

public class DequePerformanceTest {
    private static final int ELEMENT_COUNT = 100_000; // Number of elements for operations
    private static final int MIXED_OPERATIONS = 50_000; // Number of mixed operations

    public static void main(String[] args) {
        // Initialize deques
        IDeque<String> dequeCircularArray = new DequeOfCircularArray<>();
        IDeque<String> dequeDoubleLinkedList = new DequeOfDoubleLinkedArray<>();

        // Test InsertRight Performance
        System.out.println("=== InsertRight Performance ===");
        testInsertRight(dequeCircularArray, "DequeOfCircularArray");
        testInsertRight(dequeDoubleLinkedList, "DequeOfDoubleLinkedList");

        // Test InsertLeft Performance
        System.out.println("\n=== InsertLeft Performance ===");
        testInsertLeft(dequeCircularArray, "DequeOfCircularArray");
        testInsertLeft(dequeDoubleLinkedList, "DequeOfDoubleLinkedList");

        // Test DeleteRight Performance
        System.out.println("\n=== DeleteRight Performance ===");
        testDeleteRight(dequeCircularArray, "DequeOfCircularArray");
        testDeleteRight(dequeDoubleLinkedList, "DequeOfDoubleLinkedList");

        // Test DeleteLeft Performance
        System.out.println("\n=== DeleteLeft Performance ===");
        testDeleteLeft(dequeCircularArray, "DequeOfCircularArray");
        testDeleteLeft(dequeDoubleLinkedList, "DequeOfDoubleLinkedList");

        // Re-populate deques for Peek and Mixed Operations
        populateDeques(dequeCircularArray, dequeDoubleLinkedList);

        // Test Peek Performance
        System.out.println("\n=== Peek Performance ===");
        testPeek(dequeCircularArray, "DequeOfCircularArray");
        testPeek(dequeDoubleLinkedList, "DequeOfDoubleLinkedList");

        // Test Mixed Operations Performance
        System.out.println("\n=== Mixed Operations Performance ===");
        testMixedOperations(dequeCircularArray, "DequeOfCircularArray", MIXED_OPERATIONS);
        testMixedOperations(dequeDoubleLinkedList, "DequeOfDoubleLinkedList", MIXED_OPERATIONS);
    }

    private static void testInsertRight(IDeque<String> deque, String dequeName) {
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            deque.InsertRight("Element " + i);
        }
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println(dequeName + " InsertRight time: " + durationMs + " ms | Size: " + deque.size());
    }

    private static void testInsertLeft(IDeque<String> deque, String dequeName) {
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            deque.InsertLeft("Element " + i);
        }
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println(dequeName + " InsertLeft time: " + durationMs + " ms | Size: " + deque.size());
    }

    private static void testDeleteRight(IDeque<String> deque, String dequeName) {
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            try {
                deque.DeleteRight();
            } catch (NoSuchElementException e) {
                System.out.println(dequeName + " DeleteRight encountered an empty deque.");
                break;
            }
        }
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println(dequeName + " DeleteRight time: " + durationMs + " ms | Size: " + deque.size());
    }

    private static void testDeleteLeft(IDeque<String> deque, String dequeName) {
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            try {
                deque.DeleteLeft();
            } catch (NoSuchElementException e) {
                System.out.println(dequeName + " DeleteLeft encountered an empty deque.");
                break;
            }
        }
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println(dequeName + " DeleteLeft time: " + durationMs + " ms | Size: " + deque.size());
    }

    private static void testPeek(IDeque<String> deque, String dequeName) {
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            try {
                // Assuming peek is accessing both ends
                // Since IDeque does not define peek, we'll define it here
                // Alternatively, you can implement peekLeft and peekRight in IDeque
                deque.DeleteLeft(); // Example operation to simulate access
                deque.InsertLeft("Element Peek " + i);
            } catch (NoSuchElementException e) {
                System.out.println(dequeName + " Peek encountered an empty deque.");
                break;
            }
        }
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println(dequeName + " Peek time: " + durationMs + " ms");
    }

    private static void testMixedOperations(IDeque<String> deque, String dequeName, int operations) {
        long startTime = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            deque.InsertRight("Mixed Push " + i);
            deque.DeleteLeft();
        }
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println(dequeName + " Mixed Operations time: " + durationMs + " ms | Size: " + deque.size());
    }

    private static void populateDeques(IDeque<String>... deques) {
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            String element = "Element " + i;
            for (IDeque<String> deque : deques) {
                deque.InsertRight(element);
            }
        }
    }
}
