package nl.han.asd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DynamicArrayPTest {

    private DynamicArray<String> dynamicArray;
    @BeforeEach
    void setup(){
        dynamicArray = new DynamicArray<>();
    }

    @Test
    void testAddOneStrings(){
        // Arrange
        String vaueToAdd = "Test";

        // Act
        var startTime = System.nanoTime();
        dynamicArray.add(vaueToAdd);
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to add one Element: " + (endTime - startTime) + " nanoseconds");
    }

    @Test
    void testAddThreeStrings(){
        // Arrange
        String[] valuesToAdd = {"Reem", "Beem", "Shmeem"};

        // Act
        var startTime = System.nanoTime();
        dynamicArray.addAll(valuesToAdd);
        var endTime = System.nanoTime();

        // Result
        System.out.println("Time to add three elements: " + (endTime - startTime) + " nanoseconds");
    }

    @Test
    void testContainsWithThreeStrings(){
        // Arrange
        String[] valuesToAdd = {"Reem", "Shmeem","Beem"};
        dynamicArray.addAll(valuesToAdd);

        // Act
        var startTime = System.nanoTime();
        var contains = dynamicArray.contains("Beem");
        var endTime = System.nanoTime();


        // Result
        System.out.println("Time to check contains: " + (endTime - startTime) + " nanoseconds");
    }



}
