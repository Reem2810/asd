
package nl.han.asd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListPerformanceTest {

    private DoubleLinkedList<String> doubleLinkedList;
    private static final int ELEMENT_COUNT = 100_000; // Number of elements for bulk operations
    private static final int MIXED_OPERATIONS = 50_000; // Number of mixed operations

    @BeforeEach
    void setup() {
        doubleLinkedList = new DoubleLinkedList<>();
    }

    @Test
    void testAddOneElement() {
        // Arrange
        String valueToAdd = "Element1";

        // Act
        long startTime = System.nanoTime();
        doubleLinkedList.add(valueToAdd);
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to add one element: " + (endTime - startTime) + " nanoseconds");
        assertEquals(1, doubleLinkedList.size(), "DoubleLinkedList should contain one element after adding.");
        assertFalse(doubleLinkedList.isEmpty(), "DoubleLinkedList should not be empty after adding an element.");
    }

    @Test
    void testAddMultipleElementsIndividually() {
        // Arrange
        String[] valuesToAdd = {"Element1", "Element2", "Element3", "Element4", "Element5"};

        // Act
        long startTime = System.nanoTime();
        for (String value : valuesToAdd) {
            doubleLinkedList.add(value);
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to add " + valuesToAdd.length + " elements individually: " + (endTime - startTime) + " nanoseconds");
        assertEquals(valuesToAdd.length, doubleLinkedList.size(), "DoubleLinkedList should contain all added elements.");
    }

    @Test
    void testAddAllElements() {
        // Arrange
        String[] valuesToAdd = new String[ELEMENT_COUNT];
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            valuesToAdd[i] = "Element" + i;
        }

        // Act
        long startTime = System.nanoTime();
        doubleLinkedList.addAll(valuesToAdd);
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to add " + ELEMENT_COUNT + " elements using addAll: " + (endTime - startTime) + " nanoseconds");
        assertEquals(ELEMENT_COUNT, doubleLinkedList.size(), "DoubleLinkedList should contain all added elements.");
    }

    @Test
    void testRemoveFirstElement() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");

        // Act
        long startTime = System.nanoTime();
        String removed = doubleLinkedList.removeFirst();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to remove first element: " + (endTime - startTime) + " nanoseconds");
        assertEquals("Element1", removed, "Removed element should be 'Element1'.");
        assertEquals(2, doubleLinkedList.size(), "DoubleLinkedList should have two elements after removal.");
    }

    @Test
    void testRemoveLastElement() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");

        // Act
        long startTime = System.nanoTime();
        String removed = doubleLinkedList.removeLast();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to remove last element: " + (endTime - startTime) + " nanoseconds");
        assertEquals("Element3", removed, "Removed element should be 'Element3'.");
        assertEquals(2, doubleLinkedList.size(), "DoubleLinkedList should have two elements after removal.");
    }

    @Test
    void testRemoveElementByIndex() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");
        doubleLinkedList.add("Element4");

        // Act
        long startTime = System.nanoTime();
        String removed = doubleLinkedList.remove(2); // Remove "Element3"
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to remove element at index 2: " + (endTime - startTime) + " nanoseconds");
        assertEquals("Element3", removed, "Removed element should be 'Element3'.");
        assertEquals(3, doubleLinkedList.size(), "DoubleLinkedList should have three elements after removal.");
    }

    @Test
    void testRemoveElementByValue() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");
        doubleLinkedList.add("Element2"); // Duplicate

        // Act
        long startTime = System.nanoTime();
        boolean removed = doubleLinkedList.remove("Element2"); // Should remove the first "Element2"
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to remove element 'Element2': " + (endTime - startTime) + " nanoseconds");
        assertTrue(removed, "Element 'Element2' should be removed.");
        assertEquals(3, doubleLinkedList.size(), "DoubleLinkedList should have three elements after removal.");
        assertEquals("Element2", doubleLinkedList.get(1), "Second element should now be 'Element3'.");
    }

    @Test
    void testGetElement() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");
        doubleLinkedList.add("Element4");

        // Act
        long startTime = System.nanoTime();
        String element = doubleLinkedList.get(2); // "Element3"
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to get element at index 2: " + (endTime - startTime) + " nanoseconds");
        assertEquals("Element3", element, "Element at index 2 should be 'Element3'.");
    }

    @Test
    void testSetElement() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");

        // Act
        long startTime = System.nanoTime();
        doubleLinkedList.set(1, "Element2-Updated");
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to set element at index 1: " + (endTime - startTime) + " nanoseconds");
        assertEquals("Element2-Updated", doubleLinkedList.get(1), "Element at index 1 should be updated.");
    }

    @Test
    void testContainsExistingElement() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");

        // Act
        long startTime = System.nanoTime();
        boolean contains = doubleLinkedList.contains("Element2");
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to check contains (existing element): " + (endTime - startTime) + " nanoseconds");
        assertTrue(contains, "DoubleLinkedList should contain 'Element2'.");
    }

    @Test
    void testContainsNonExistingElement() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");

        // Act
        long startTime = System.nanoTime();
        boolean contains = doubleLinkedList.contains("Element4");
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to check contains (non-existing element): " + (endTime - startTime) + " nanoseconds");
        assertFalse(contains, "DoubleLinkedList should not contain 'Element4'.");
    }

    @Test
    void testIndexOfExistingElement() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");
        doubleLinkedList.add("Element2"); // Duplicate

        // Act
        long startTime = System.nanoTime();
        int index = doubleLinkedList.indexOf("Element2"); // Should return 1
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to find index of 'Element2': " + (endTime - startTime) + " nanoseconds");
        assertEquals(1, index, "Index of 'Element2' should be 1.");
    }

    @Test
    void testIndexOfNonExistingElement() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");

        // Act
        long startTime = System.nanoTime();
        int index = doubleLinkedList.indexOf("Element4"); // Should return -1
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to find index of 'Element4': " + (endTime - startTime) + " nanoseconds");
        assertEquals(-1, index, "Index of 'Element4' should be -1.");
    }

    @Test
    void testSize() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");

        // Act
        long startTime = System.nanoTime();
        int currentSize = doubleLinkedList.size();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to get size: " + (endTime - startTime) + " nanoseconds");
        assertEquals(3, currentSize, "Size should be 3.");
    }

    @Test
    void testIsEmpty() {
        // Arrange
        // doubleLinkedList is already empty from setup

        // Act
        long startTime = System.nanoTime();
        boolean empty = doubleLinkedList.isEmpty();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to check if empty: " + (endTime - startTime) + " nanoseconds");
        assertTrue(empty, "DoubleLinkedList should be empty.");
    }

    @Test
    void testClear() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");

        // Act
        long startTime = System.nanoTime();
        doubleLinkedList.clear();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to clear the list: " + (endTime - startTime) + " nanoseconds");
        assertEquals(0, doubleLinkedList.size(), "DoubleLinkedList should be empty after clearing.");
        assertTrue(doubleLinkedList.isEmpty(), "DoubleLinkedList should be empty after clearing.");
    }

    @Test
    void testIteratorPerformance() {
        // Arrange
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            doubleLinkedList.add("Element" + i);
        }

        // Act
        long startTime = System.nanoTime();
        Iterator<String> iterator = doubleLinkedList.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            // Optionally, perform operations with value
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to iterate over " + ELEMENT_COUNT + " elements: " + (endTime - startTime) + " nanoseconds");
    }

    @Test
    void testRemoveAllElementsOneByOne() {
        // Arrange
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            doubleLinkedList.add("Element" + i);
        }

        // Act
        long startTime = System.nanoTime();
        while (!doubleLinkedList.isEmpty()) {
            doubleLinkedList.remove(doubleLinkedList.size() - 1);
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to remove all " + ELEMENT_COUNT + " elements one by one: " + (endTime - startTime) + " nanoseconds");
        assertTrue(doubleLinkedList.isEmpty(), "DoubleLinkedList should be empty after removing all elements.");
    }

    @Test
    void testAddNullElements() {
        // Arrange
        String[] valuesToAdd = {"Element1", null, "Element3", null};

        // Act
        long startTime = System.nanoTime();
        for (String value : valuesToAdd) {
            try {
                doubleLinkedList.add(value);
            } catch (IllegalArgumentException e) {
                // Expected exception for null elements
            }
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to add elements including nulls: " + (endTime - startTime) + " nanoseconds");
        assertEquals(2, doubleLinkedList.size(), "DoubleLinkedList should contain only non-null elements.");
        assertFalse(doubleLinkedList.contains(null), "DoubleLinkedList should not contain null elements.");
    }

    @Test
    void testRemoveNullElements() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");
        // Attempting to add nulls will throw exceptions, so no nulls in the list

        // Act
        long startTime = System.nanoTime();
        boolean removed = doubleLinkedList.remove(null); // Should return false
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to remove null element: " + (endTime - startTime) + " nanoseconds");
        assertFalse(removed, "Removing null should return false as no null elements exist.");
    }

    @Test
    void testIndexOfInSmallList() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");

        // Act
        long startTime = System.nanoTime();
        int index = doubleLinkedList.indexOf("Element1");
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to find index of 'Element1' in small list: " + (endTime - startTime) + " nanoseconds");
        assertEquals(0, index, "Index of 'Element1' should be 0.");
    }

    @Test
    void testIndexOfInLargeList() {
        // Arrange
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            doubleLinkedList.add("Element" + i);
        }

        // Act
        long startTime = System.nanoTime();
        int index = doubleLinkedList.indexOf("Element99999");
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to find index of 'Element99999' in large list: " + (endTime - startTime) + " nanoseconds");
        assertEquals(ELEMENT_COUNT - 1, index, "Index of 'Element99999' should be " + (ELEMENT_COUNT - 1) + ".");
    }

    @Test
    void testToString() {
        // Arrange
        doubleLinkedList.add("Element1");
        doubleLinkedList.add("Element2");
        doubleLinkedList.add("Element3");

        // Act
        long startTime = System.nanoTime();
        String listString = doubleLinkedList.toString();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to convert list to string: " + (endTime - startTime) + " nanoseconds");
        assertEquals("[Element1, Element2, Element3]", listString, "toString() should return the correct string representation.");
    }

    @Test
    void testIteratorEmptyList() {
        // Act
        long startTime = System.nanoTime();
        Iterator<String> iterator = doubleLinkedList.iterator();
        boolean hasNext = iterator.hasNext();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to iterate over empty list: " + (endTime - startTime) + " nanoseconds");
        assertFalse(hasNext, "Iterator should have no elements in an empty list.");
    }

    @Test
    void testIteratorWithElements() {
        // Arrange
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            doubleLinkedList.add("Element" + i);
        }

        // Act
        long startTime = System.nanoTime();
        Iterator<String> iterator = doubleLinkedList.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            String value = iterator.next();
            count++;
            // Optionally, perform operations with value
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to iterate over " + ELEMENT_COUNT + " elements: " + (endTime - startTime) + " nanoseconds");
        assertEquals(ELEMENT_COUNT, count, "Iterator should have iterated through all elements.");
    }

    @Test
    void testAddAndRemoveMixedOperations() {
        // Arrange
        Random rand = new Random();

        // Act
        long startTime = System.nanoTime();
        for (int i = 0; i < MIXED_OPERATIONS; i++) {
            doubleLinkedList.add("Element" + rand.nextInt());
            doubleLinkedList.removeFirst();
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time for " + MIXED_OPERATIONS + " mixed add and removeFirst operations: " + (endTime - startTime) + " nanoseconds");
        // Since we add and removeFirst the same number of elements, the size should remain 0
        assertEquals(0, doubleLinkedList.size(), "DoubleLinkedList should be empty after mixed operations.");
        assertTrue(doubleLinkedList.isEmpty(), "DoubleLinkedList should be empty after mixed operations.");
    }

    @Test
    void testRemoveFromEmptyList() {
        // Act & Assert
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            doubleLinkedList.removeFirst();
        });
        System.out.println("Exception when removing from empty list: " + exception.getMessage());

        exception = assertThrows(NoSuchElementException.class, () -> {
            doubleLinkedList.removeLast();
        });
        System.out.println("Exception when removing from empty list: " + exception.getMessage());
    }

    @Test
    void testSetElementInvalidIndex() {
        // Arrange
        doubleLinkedList.add("Element1");

        // Act & Assert
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleLinkedList.set(1, "Element2");
        });
        System.out.println("Exception when setting element at invalid index: " + exception.getMessage());
    }

    @Test
    void testGetElementInvalidIndex() {
        // Arrange
        doubleLinkedList.add("Element1");

        // Act & Assert
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleLinkedList.get(1);
        });
        System.out.println("Exception when getting element at invalid index: " + exception.getMessage());
    }

    @Test
    void testAddNullElement() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            doubleLinkedList.add(null);
        });
        System.out.println("Exception when adding null element: " + exception.getMessage());
    }

    @Test
    void testAddAllWithNullArray() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            doubleLinkedList.addAll(null);
        });
        System.out.println("Exception when adding all with null array: " + exception.getMessage());
    }

    @Test
    void testAddAllWithNullElements() {
        // Arrange
        String[] valuesToAdd = {"Element1", null, "Element3"};

        // Act
        long startTime = System.nanoTime();
        for (String value : valuesToAdd) {
            try {
                doubleLinkedList.add(value);
            } catch (IllegalArgumentException e) {
                // Expected exception for null elements
            }
        }
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to add elements including nulls using addAll: " + (endTime - startTime) + " nanoseconds");
        assertEquals(2, doubleLinkedList.size(), "DoubleLinkedList should contain only non-null elements.");
    }

    @Test
    void testToStringEmptyList() {
        // Act
        long startTime = System.nanoTime();
        String listString = doubleLinkedList.toString();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to convert empty list to string: " + (endTime - startTime) + " nanoseconds");
        assertEquals("[]", listString, "toString() should return '[]' for an empty list.");
    }

    @Test
    void testClearLargeList() {
        // Arrange
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            doubleLinkedList.add("Element" + i);
        }

        // Act
        long startTime = System.nanoTime();
        doubleLinkedList.clear();
        long endTime = System.nanoTime();

        // Result
        System.out.println("Time to clear a large list of " + ELEMENT_COUNT + " elements: " + (endTime - startTime) + " nanoseconds");
        assertEquals(0, doubleLinkedList.size(), "DoubleLinkedList should be empty after clearing.");
        assertTrue(doubleLinkedList.isEmpty(), "DoubleLinkedList should be empty after clearing.");
    }
}
