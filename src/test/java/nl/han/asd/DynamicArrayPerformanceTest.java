package nl.han.asd;

    public class DynamicArrayPerformanceTest {

        public static void main(String[] args) {
            DynamicArrayPerformanceTest tester = new DynamicArrayPerformanceTest();

            System.out.println("Performance Test: Add Operation");
            tester.testAddPerformance();

            System.out.println("\nPerformance Test: Remove Operation");
            tester.testRemovePerformance();

            System.out.println("\nPerformance Test: Get Operation");
            tester.testGetPerformance();

            System.out.println("\nPerformance Test: Contains Operation");
            tester.testContainsPerformance();
        }

        public void testAddPerformance() {
            DynamicArray<Integer> dynamicArray = new DynamicArray<>(10);

        }

        public void testRemovePerformance() {
            DynamicArray<Integer> dynamicArray = new DynamicArray<>(100_000);
            for (int i = 0; i < 100_000; i++) {
                dynamicArray.add(i);
            }

            // Remove from the start
            long startTime = System.nanoTime();
            dynamicArray.remove(0);
            long endTime = System.nanoTime();
            System.out.println("Time to remove from start: " + (endTime - startTime) + " nanoseconds");

            // Remove from the middle
            startTime = System.nanoTime();
            dynamicArray.remove(dynamicArray.size() / 2);
            endTime = System.nanoTime();
            System.out.println("Time to remove from middle: " + (endTime - startTime) + " nanoseconds");

            // Remove from the end
            startTime = System.nanoTime();
            dynamicArray.remove(dynamicArray.size() - 1);
            endTime = System.nanoTime();
            System.out.println("Time to remove from end: " + (endTime - startTime) + " nanoseconds");
        }

        public void testGetPerformance() {
            DynamicArray<Integer> dynamicArray = new DynamicArray<>(100_000);
            for (int i = 0; i < 100_000; i++) {
                dynamicArray.add(i);
            }

            // Get from the start
            long startTime = System.nanoTime();
            dynamicArray.get(0);
            long endTime = System.nanoTime();
            System.out.println("Time to get from start: " + (endTime - startTime) + " nanoseconds");

            // Get from the middle
            startTime = System.nanoTime();
            dynamicArray.get(dynamicArray.size() / 2);
            endTime = System.nanoTime();
            System.out.println("Time to get from middle: " + (endTime - startTime) + " nanoseconds");

            // Get from the end
            startTime = System.nanoTime();
            dynamicArray.get(dynamicArray.size() - 1);
            endTime = System.nanoTime();
            System.out.println("Time to get from end: " + (endTime - startTime) + " nanoseconds");
        }

        public void testContainsPerformance() {
            DynamicArray<Integer> dynamicArray = new DynamicArray<>(100_000);

            }
    }


