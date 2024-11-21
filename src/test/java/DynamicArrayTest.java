import nl.han.asd.DynamicArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicArrayTest {

    @Test
    void testAddAndGet() {
        DynamicArray<Integer> array = new DynamicArray<>(5);
        array.add(10);
        array.add(20);
        assertEquals(10, array.get(0)); // Check first element
        assertEquals(20, array.get(1)); // Check second element
    }

    @Test
    void testRemoveByIndex() {
        DynamicArray<Integer> array = new DynamicArray<>(5);
        array.add(10);
        array.add(20);
        assertEquals(10, array.remove(0)); // Remove first element
        assertEquals(20, array.get(0));    // Check remaining element
    }

    @Test
    void testContainsAndIndexOf() {
        DynamicArray<String> array = new DynamicArray<>(5);
        array.add("hello");
        array.add("world");
        assertTrue(array.contains("hello")); // Check containment
        assertEquals(1, array.indexOf("world")); // Check index
    }

    @Test
    void testIsEmpty() {
        DynamicArray<Integer> array = new DynamicArray<>(5);
        assertTrue(array.isEmpty()); // Should be empty initially
        array.add(1);
        assertFalse(array.isEmpty()); // Should not be empty after adding an element
    }

    @Test
    void testClear() {
        DynamicArray<Integer> array = new DynamicArray<>(5);
        array.add(1);
        array.add(2);
        array.clear(); // Clear the array
        assertTrue(array.isEmpty()); // Should be empty after clearing
    }

    @Test
    void testTrimToSize() {
        DynamicArray<Integer> array = new DynamicArray<>(10);
        array.add(1);
        array.add(2);
        array.trimToSize(); // Trim capacity to size
        assertEquals(2, array.size()); // Ensure size is correct
    }

    @Test
    void testIterator() {
        DynamicArray<Integer> array = new DynamicArray<>(5);
        array.add(1);
        array.add(2);
        array.add(3);

        int sum = 0;
        for (int value : array) {
            sum += value; // Use iterator to sum elements
        }
        assertEquals(6, sum); // Check sum
    }
}
