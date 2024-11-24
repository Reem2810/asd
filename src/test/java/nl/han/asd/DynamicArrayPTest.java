package nl.han.asd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class DynamicArrayPTest {

    private DynamicArray<String> dynamicArray;

    @BeforeEach
    void setup(){
        dynamicArray = new DynamicArray<>();
    }

    @Test
    void testAddOneElement(){
        // Arrange
        String valueToAdd = "Margherita";

        // Act
        var startTime = System.nanoTime();
        dynamicArray.add(valueToAdd);
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to add one element: " + (endTime - startTime) + " nanoseconds");
    }

    @Test
    void testAddMultipleElements(){
        // Arrange
        String[] valuesToAdd = {"Margherita", "Mari e monte", "Marinara", "Four-seasons"};

        // Act
        var startTime = System.nanoTime();
        dynamicArray.addAll(valuesToAdd);
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to add " + valuesToAdd.length + " elements: " + (endTime - startTime) + " nanoseconds");
    }

    @Test
    void testContainsExistingElement(){
        // Arrange
        String[] valuesToAdd = {"Margherita", "Mari e monte", "Marinara"};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        boolean contains = dynamicArray.contains("Mari e monte");
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to check contains (existing element): " + (endTime - startTime) + " nanoseconds");
        System.out.println("Contains 'Mari e monte': " + contains);
    }

    @Test
    void testContainsNonExistingElement(){
        // Arrange
        String[] valuesToAdd = {"Margherita", "Mari e monte", "Marinara"};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        boolean contains = dynamicArray.contains("Reem");
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to check contains (non-existing element): " + (endTime - startTime) + " nanoseconds");
        System.out.println("Contains 'Reem': " + contains);
    }

    @Test
    void testGetElement(){
        // Arrange
        String[] valuesToAdd = {"Margherita", "Mari e monte", "Marinara", "Four-seasons"};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        String element = dynamicArray.get(2);
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to get element at index 2: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Element at index 2: " + element);
    }

    @Test
    void testSetElement(){
        // Arrange
        String[] valuesToAdd = {"Margherita", "Mari e monte", "Marinara"};
        dynamicArray.addAll(valuesToAdd);
        String newValue = "Quattro Stagioni";

        // Act
        var startTime = System.nanoTime();
        dynamicArray.set(1, newValue);
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to set element at index 1: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Element at index 1 after set: " + dynamicArray.get(1));
    }

    @Test
    void testRemoveElementByIndex(){
        // Arrange
        String[] valuesToAdd = {"Margherita", "Mari e monte", "Marinara", "Quattro Stagioni"};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        String removedElement = dynamicArray.remove(2);
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to remove element at index 2: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Removed element: " + removedElement);
        System.out.println("Array after removal: " + dynamicArray);
    }

    @Test
    void testRemoveElementByValue(){
        // Arrange
        String[] valuesToAdd = {"Margherita", "Mari e monte", "Marinara", "Quattro Stagioni"};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        boolean removed = dynamicArray.remove("Mari e monte");
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to remove element 'Mari e monte': " + (endTime - startTime) + " nanoseconds");
        System.out.println("Element 'Mari e monte' removed: " + removed);
        System.out.println("Array after removal: " + dynamicArray);
    }

    @Test
    void testIndexOfElement(){
        // Arrange
        String[] valuesToAdd = { "Mari e monte", "Marinara", "Quattro Stagioni","Margherita"};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        int index = dynamicArray.indexOf("Marinara");
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to find index of 'Marinara': " + (endTime - startTime) + " nanoseconds");
        System.out.println("Index of 'Marinara': " + index);
    }

    @Test
    void testIndexOfElementInSmallArray(){
        // Arrange
        String[] valuesToAdd = { "Marinara", "Margherita"};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        int index = dynamicArray.indexOf("Marinara");
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to find index of 'Marinara': " + (endTime - startTime) + " nanoseconds");
        System.out.println("Index of 'Marinara': " + index);
    }

    @Test
    void testSize(){
        // Arrange
        String[] valuesToAdd = {"Margherita", "Mari e monte", "Marinara"};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        int currentSize = dynamicArray.size();
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to get size: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Current size: " + currentSize);
    }

    @Test
    void testIsEmpty(){
        // Arrange
        // dynamicArray is already empty from setup

        // Act
        var startTime = System.nanoTime();
        boolean empty = dynamicArray.isEmpty();
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to check if empty: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Is empty: " + empty);
    }

    @Test
    void testClear(){
        // Arrange
        String[] valuesToAdd = {"Margherita", "Mari e monte", "Marinara"};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        dynamicArray.clear();
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to clear the array: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Array after clear: " + dynamicArray);
    }

    @Test
    void testTrimToSize(){
        // Arrange
        // Assuming DynamicArray has a method trimToSize()
        // Fill the array beyond the initial capacity to ensure resizing
        int initialCapacity = 8;
        int numberOfElements = 20; // Greater than initial capacity
        for(int i = 0; i < numberOfElements; i++) {
            dynamicArray.add("Element " + i);
        }

        // Act
        var startTime = System.nanoTime();
        dynamicArray.trimToSize();
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to trim to size: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Array after trimToSize: " + dynamicArray);
    }

    @Test
    void testIterator(){
        // Arrange
        String[] valuesToAdd = {"Margherita", "Mari e monte", "Marinara", "Quattro Stagioni"};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        Iterator<String> iterator = dynamicArray.iterator();
        while(iterator.hasNext()){
            String value = iterator.next();
            // Optionally, perform operations with value
        }
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to iterate over all elements: " + (endTime - startTime) + " nanoseconds");
    }

    @Test
    void testRemoveAllElements(){
        // Arrange
        String[] valuesToAdd = {"Margherita", "Mari e monte", "Marinara", "Quattro Stagioni"};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        while(!dynamicArray.isEmpty()){
            dynamicArray.remove(dynamicArray.size() - 1);
        }
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to remove all elements one by one: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Array after removing all elements: " + dynamicArray);
    }

    @Test
    void testMultipleAddAndRemoveOperations(){
        // Arrange
        int numberOfOperations = 1000;
        String element = "TestElement";

        // Act
        var startTime = System.nanoTime();
        for(int i = 0; i < numberOfOperations; i++) {
            dynamicArray.add(element);
        }
        for(int i = 0; i < numberOfOperations; i++) {
            dynamicArray.remove(dynamicArray.size() - 1);
        }
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time for " + numberOfOperations + " add and remove operations: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Array after operations: " + dynamicArray);
    }

    @Test
    void testAddNullElements(){
        // Arrange
        String[] valuesToAdd = {"Margherita", null, "Marinara", null};

        // Act
        var startTime = System.nanoTime();
        dynamicArray.addAll(valuesToAdd);
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to add elements including nulls: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Array after adding null elements: " + dynamicArray);
        System.out.println("Contains null: " + dynamicArray.contains(null));
    }

    @Test
    void testRemoveNullElements(){
        // Arrange
        String[] valuesToAdd = {"Margherita", null, "Marinara", null};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        boolean removedFirstNull = dynamicArray.remove(null);
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to remove first null element: " + (endTime - startTime) + " nanoseconds");
        System.out.println("Removed first null: " + removedFirstNull);
        System.out.println("Array after removing first null: " + dynamicArray);
    }
}
