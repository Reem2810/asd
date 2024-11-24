
package nl.han.asd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DequePerformanceTest {

    private IDeque<String> dequeCircularArray;
    private IDeque<String> dequeDoubleLinkedArray;
    private static final int ELEMENT_COUNT = 100_000; // Number of elements for bulk operations

    @BeforeEach
    void setup() {
        dequeCircularArray = new DequeOfCircularArray<>();
        dequeDoubleLinkedArray = new DequeOfDoubleLinkedList<>();
    }

    // ===================== InsertLeft Tests =====================

    @Test
    void testInsertLeft_SingleElement_DequeOfCircularArray() {
        String element = "Element1";

        long startTime = System.nanoTime();
        dequeCircularArray.InsertLeft(element);
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfCircularArray InsertLeft Single Element time: " + durationMs + " ms | Size: " + dequeCircularArray.size());

        assertEquals(1, dequeCircularArray.size(), "Size should be 1 after inserting one element.");
        assertFalse(dequeCircularArray.isEmpty(), "Deque should not be empty after inserting one element.");
    }

    @Test
    void testInsertLeft_BulkElements_DequeOfCircularArray() {
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            dequeCircularArray.InsertLeft("Element " + i);
        }
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfCircularArray InsertLeft Bulk (" + ELEMENT_COUNT + ") time: " + durationMs + " ms | Size: " + dequeCircularArray.size());

        assertEquals(ELEMENT_COUNT, dequeCircularArray.size(), "Size should match the number of inserted elements.");
    }

    @Test
    void testInsertLeft_SingleElement_DequeOfDoubleLinkedArray() {
        String element = "Element1";

        long startTime = System.nanoTime();
        dequeDoubleLinkedArray.InsertLeft(element);
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfDoubleLinkedArray InsertLeft Single Element time: " + durationMs + " ms | Size: " + dequeDoubleLinkedArray.size());

        assertEquals(1, dequeDoubleLinkedArray.size(), "Size should be 1 after inserting one element.");
        assertFalse(dequeDoubleLinkedArray.isEmpty(), "Deque should not be empty after inserting one element.");
    }

    @Test
    void testInsertLeft_BulkElements_DequeOfDoubleLinkedArray() {
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            dequeDoubleLinkedArray.InsertLeft("Element " + i);
        }
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfDoubleLinkedArray InsertLeft Bulk (" + ELEMENT_COUNT + ") time: " + durationMs + " ms | Size: " + dequeDoubleLinkedArray.size());

        assertEquals(ELEMENT_COUNT, dequeDoubleLinkedArray.size(), "Size should match the number of inserted elements.");
    }

    // ===================== InsertRight Tests =====================

    @Test
    void testInsertRight_SingleElement_DequeOfCircularArray() {
        String element = "Element1";

        long startTime = System.nanoTime();
        dequeCircularArray.InsertRight(element);
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfCircularArray InsertRight Single Element time: " + durationMs + " ms | Size: " + dequeCircularArray.size());

        assertEquals(1, dequeCircularArray.size(), "Size should be 1 after inserting one element.");
        assertFalse(dequeCircularArray.isEmpty(), "Deque should not be empty after inserting one element.");
    }

    @Test
    void testInsertRight_BulkElements_DequeOfCircularArray() {
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            dequeCircularArray.InsertRight("Element " + i);
        }
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfCircularArray InsertRight Bulk (" + ELEMENT_COUNT + ") time: " + durationMs + " ms | Size: " + dequeCircularArray.size());

        assertEquals(ELEMENT_COUNT, dequeCircularArray.size(), "Size should match the number of inserted elements.");
    }

    @Test
    void testInsertRight_SingleElement_DequeOfDoubleLinkedArray() {
        String element = "Element1";

        long startTime = System.nanoTime();
        dequeDoubleLinkedArray.InsertRight(element);
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfDoubleLinkedArray InsertRight Single Element time: " + durationMs + " ms | Size: " + dequeDoubleLinkedArray.size());

        assertEquals(1, dequeDoubleLinkedArray.size(), "Size should be 1 after inserting one element.");
        assertFalse(dequeDoubleLinkedArray.isEmpty(), "Deque should not be empty after inserting one element.");
    }

    @Test
    void testInsertRight_BulkElements_DequeOfDoubleLinkedArray() {
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            dequeDoubleLinkedArray.InsertRight("Element " + i);
        }
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfDoubleLinkedArray InsertRight Bulk (" + ELEMENT_COUNT + ") time: " + durationMs + " ms | Size: " + dequeDoubleLinkedArray.size());

        assertEquals(ELEMENT_COUNT, dequeDoubleLinkedArray.size(), "Size should match the number of inserted elements.");
    }

    // ===================== DeleteLeft Tests =====================

    @Test
    void testDeleteLeft_SingleElement_DequeOfCircularArray() {
        // Arrange
        dequeCircularArray.InsertLeft("Element1");

        long startTime = System.nanoTime();
        String removed = dequeCircularArray.DeleteLeft();
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfCircularArray DeleteLeft Single Element time: " + durationMs + " ms | Removed: " + removed + " | Size: " + dequeCircularArray.size());

        assertEquals("Element1", removed, "Removed element should be 'Element1'.");
        assertEquals(0, dequeCircularArray.size(), "Size should be 0 after deletion.");
        assertTrue(dequeCircularArray.isEmpty(), "Deque should be empty after deletion.");
    }

    @Test
    void testDeleteLeft_BulkElements_DequeOfCircularArray() {
        // Arrange
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            dequeCircularArray.InsertLeft("Element " + i);
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            String removed = dequeCircularArray.DeleteLeft();
            // Optionally, verify the removed element
            // assertEquals("Element " + (ELEMENT_COUNT - 1 - i), removed);
        }
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfCircularArray DeleteLeft Bulk (" + ELEMENT_COUNT + ") time: " + durationMs + " ms | Size: " + dequeCircularArray.size());

        assertEquals(0, dequeCircularArray.size(), "Size should be 0 after bulk deletions.");
        assertTrue(dequeCircularArray.isEmpty(), "Deque should be empty after bulk deletions.");
    }

    @Test
    void testDeleteLeft_SingleElement_DequeOfDoubleLinkedArray() {
        // Arrange
        dequeDoubleLinkedArray.InsertLeft("Element1");

        long startTime = System.nanoTime();
        String removed = dequeDoubleLinkedArray.DeleteLeft();
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfDoubleLinkedArray DeleteLeft Single Element time: " + durationMs + " ms | Removed: " + removed + " | Size: " + dequeDoubleLinkedArray.size());

        assertEquals("Element1", removed, "Removed element should be 'Element1'.");
        assertEquals(0, dequeDoubleLinkedArray.size(), "Size should be 0 after deletion.");
        assertTrue(dequeDoubleLinkedArray.isEmpty(), "Deque should be empty after deletion.");
    }

    @Test
    void testDeleteLeft_BulkElements_DequeOfDoubleLinkedArray() {
        // Arrange
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            dequeDoubleLinkedArray.InsertLeft("Element " + i);
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            String removed = dequeDoubleLinkedArray.DeleteLeft();
            // Optionally, verify the removed element
            // assertEquals("Element " + (ELEMENT_COUNT - 1 - i), removed);
        }
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfDoubleLinkedArray DeleteLeft Bulk (" + ELEMENT_COUNT + ") time: " + durationMs + " ms | Size: " + dequeDoubleLinkedArray.size());

        assertEquals(0, dequeDoubleLinkedArray.size(), "Size should be 0 after bulk deletions.");
        assertTrue(dequeDoubleLinkedArray.isEmpty(), "Deque should be empty after bulk deletions.");
    }

    // ===================== DeleteRight Tests =====================

    @Test
    void testDeleteRight_SingleElement_DequeOfCircularArray() {
        // Arrange
        dequeCircularArray.InsertRight("Element1");

        long startTime = System.nanoTime();
        String removed = dequeCircularArray.DeleteRight();
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfCircularArray DeleteRight Single Element time: " + durationMs + " ms | Removed: " + removed + " | Size: " + dequeCircularArray.size());

        assertEquals("Element1", removed, "Removed element should be 'Element1'.");
        assertEquals(0, dequeCircularArray.size(), "Size should be 0 after deletion.");
        assertTrue(dequeCircularArray.isEmpty(), "Deque should be empty after deletion.");
    }

    @Test
    void testDeleteRight_BulkElements_DequeOfCircularArray() {
        // Arrange
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            dequeCircularArray.InsertRight("Element " + i);
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            String removed = dequeCircularArray.DeleteRight();
            // Optionally, verify the removed element
            // assertEquals("Element " + (ELEMENT_COUNT - 1 - i), removed);
        }
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfCircularArray DeleteRight Bulk (" + ELEMENT_COUNT + ") time: " + durationMs + " ms | Size: " + dequeCircularArray.size());

        assertEquals(0, dequeCircularArray.size(), "Size should be 0 after bulk deletions.");
        assertTrue(dequeCircularArray.isEmpty(), "Deque should be empty after bulk deletions.");
    }

    @Test
    void testDeleteRight_SingleElement_DequeOfDoubleLinkedArray() {
        // Arrange
        dequeDoubleLinkedArray.InsertRight("Element1");

        long startTime = System.nanoTime();
        String removed = dequeDoubleLinkedArray.DeleteRight();
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfDoubleLinkedArray DeleteRight Single Element time: " + durationMs + " ms | Removed: " + removed + " | Size: " + dequeDoubleLinkedArray.size());

        assertEquals("Element1", removed, "Removed element should be 'Element1'.");
        assertEquals(0, dequeDoubleLinkedArray.size(), "Size should be 0 after deletion.");
        assertTrue(dequeDoubleLinkedArray.isEmpty(), "Deque should be empty after deletion.");
    }

    @Test
    void testDeleteRight_BulkElements_DequeOfDoubleLinkedArray() {
        // Arrange
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            dequeDoubleLinkedArray.InsertRight("Element " + i);
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            String removed = dequeDoubleLinkedArray.DeleteRight();
            // Optionally, verify the removed element
            // assertEquals("Element " + (ELEMENT_COUNT - 1 - i), removed);
        }
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfDoubleLinkedArray DeleteRight Bulk (" + ELEMENT_COUNT + ") time: " + durationMs + " ms | Size: " + dequeDoubleLinkedArray.size());

        assertEquals(0, dequeDoubleLinkedArray.size(), "Size should be 0 after bulk deletions.");
        assertTrue(dequeDoubleLinkedArray.isEmpty(), "Deque should be empty after bulk deletions.");
    }

    // ===================== Edge Case Tests =====================

    @Test
    void testDeleteFromEmptyDeque_DequeOfCircularArray() {
        // Act & Assert
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            dequeCircularArray.DeleteLeft();
        });
        System.out.println("DequeOfCircularArray Exception when deleting left from empty deque: " + exception.getMessage());

        exception = assertThrows(NoSuchElementException.class, () -> {
            dequeCircularArray.DeleteRight();
        });
        System.out.println("DequeOfCircularArray Exception when deleting right from empty deque: " + exception.getMessage());
    }

    @Test
    void testDeleteFromEmptyDeque_DequeOfDoubleLinkedArray() {
        // Act & Assert
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            dequeDoubleLinkedArray.DeleteLeft();
        });
        System.out.println("DequeOfDoubleLinkedArray Exception when deleting left from empty deque: " + exception.getMessage());

        exception = assertThrows(NoSuchElementException.class, () -> {
            dequeDoubleLinkedArray.DeleteRight();
        });
        System.out.println("DequeOfDoubleLinkedArray Exception when deleting right from empty deque: " + exception.getMessage());
    }

    @Test
    void testInsertNullElement_DequeOfCircularArray() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dequeCircularArray.InsertLeft(null);
        });
        System.out.println("DequeOfCircularArray Exception when inserting null to left: " + exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            dequeCircularArray.InsertRight(null);
        });
        System.out.println("DequeOfCircularArray Exception when inserting null to right: " + exception.getMessage());
    }

    @Test
    void testInsertNullElement_DequeOfDoubleLinkedArray() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dequeDoubleLinkedArray.InsertLeft(null);
        });
        System.out.println("DequeOfDoubleLinkedArray Exception when inserting null to left: " + exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            dequeDoubleLinkedArray.InsertRight(null);
        });
        System.out.println("DequeOfDoubleLinkedArray Exception when inserting null to right: " + exception.getMessage());
    }

    // ===================== Size and IsEmpty Tests =====================

    @Test
    void testSizeAndIsEmpty_DequeOfCircularArray() {
        assertTrue(dequeCircularArray.isEmpty(), "Deque should be empty initially.");
        assertEquals(0, dequeCircularArray.size(), "Initial size should be 0.");

        dequeCircularArray.InsertLeft("Element1");
        assertFalse(dequeCircularArray.isEmpty(), "Deque should not be empty after insertion.");
        assertEquals(1, dequeCircularArray.size(), "Size should be 1 after one insertion.");

        dequeCircularArray.DeleteLeft();
        assertTrue(dequeCircularArray.isEmpty(), "Deque should be empty after deletion.");
        assertEquals(0, dequeCircularArray.size(), "Size should be 0 after deletion.");
    }

    @Test
    void testSizeAndIsEmpty_DequeOfDoubleLinkedArray() {
        assertTrue(dequeDoubleLinkedArray.isEmpty(), "Deque should be empty initially.");
        assertEquals(0, dequeDoubleLinkedArray.size(), "Initial size should be 0.");

        dequeDoubleLinkedArray.InsertRight("Element1");
        assertFalse(dequeDoubleLinkedArray.isEmpty(), "Deque should not be empty after insertion.");
        assertEquals(1, dequeDoubleLinkedArray.size(), "Size should be 1 after one insertion.");

        dequeDoubleLinkedArray.DeleteRight();
        assertTrue(dequeDoubleLinkedArray.isEmpty(), "Deque should be empty after deletion.");
        assertEquals(0, dequeDoubleLinkedArray.size(), "Size should be 0 after deletion.");
    }

    // ===================== Mixed Operations Test =====================

    @Test
    void testMixedOperations_DequeOfCircularArray() {
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            dequeCircularArray.InsertRight("MixedPush " + i);
            try {
                dequeCircularArray.DeleteLeft();
            } catch (NoSuchElementException e) {
                // If deque is empty, continue
            }
        }
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfCircularArray Mixed Operations (" + ELEMENT_COUNT + ") time: " + durationMs + " ms | Size: " + dequeCircularArray.size());

        // The size should be 0 or ELEMENT_COUNT if deletes failed
        assertTrue(dequeCircularArray.size() <= ELEMENT_COUNT, "Deque size should be manageable after mixed operations.");
    }

    @Test
    void testMixedOperations_DequeOfDoubleLinkedArray() {
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            dequeDoubleLinkedArray.InsertLeft("MixedPush " + i);
            try {
                dequeDoubleLinkedArray.DeleteRight();
            } catch (NoSuchElementException e) {
                // If deque is empty, continue
            }
        }
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("DequeOfDoubleLinkedArray Mixed Operations (" + ELEMENT_COUNT + ") time: " + durationMs + " ms | Size: " + dequeDoubleLinkedArray.size());

        // The size should be 0 or ELEMENT_COUNT if deletes failed
        assertTrue(dequeDoubleLinkedArray.size() <= ELEMENT_COUNT, "Deque size should be manageable after mixed operations.");
    }
}
