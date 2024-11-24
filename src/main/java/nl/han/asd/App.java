package nl.han.asd;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

/**
 * Hello world!
 *
 */public class App {

    public static final String DATASET_SORTEREN_JSON = "dataset_sorteren.json";

    public static void main(String[] args) throws URISyntaxException, IOException {

        var app = new App();
        var url = app.createPathForResource(DATASET_SORTEREN_JSON);
        var content = Files.readString(Path.of(url.toURI()), Charset.defaultCharset());

        var gson = new Gson();
        DataSetSorting datasetSorting = gson.fromJson(content, DataSetSorting.class);

        System.out.println("*******************************");
        System.out.println("testing DynamicArray against Marco's data ");
        System.out.println("*******************************");

        DynamicArray<Float> dynamicFloatArray = new DynamicArray<>(1);
        DynamicArray<Integer> dynamicIntegerArray = new DynamicArray<>(1);
        DynamicArray<Float> dynamicMixedArray = new DynamicArray<>(1);

        dynamicIntegerArray.addAll(datasetSorting.lijst_null_1);
        dynamicFloatArray.addAll(datasetSorting.lijst_float_8001);
      // dynamicMixedArray.addAll(datasetSorting.lijst_onsorteerbaar_3);

        System.out.println(" Test contains " + dynamicIntegerArray.contains(4));
        dynamicIntegerArray.add(4); // Perform the add operation
        System.out.println("Test contains after adding the element."+ dynamicIntegerArray.contains(4));
        System.out.println(" Size of dynamic array " + dynamicIntegerArray.size());
        System.out.println(dynamicIntegerArray.toString());


        System.out.println("*******************************");
        System.out.println("testing Stack against Marco's data ");
        System.out.println("*******************************");
        IStack<Integer> stack = new StackOfDynamicArray<>();

        stack.push(8);
        stack.push(9);
        stack.push(10);
        System.out.println("Top element (peek): " + stack.peek());
        stack.pop();
        System.out.println("Top element (peek): " + stack.peek());
        System.out.println("Size of stack: " + stack.size());
        System.out.println("Top element (pop): " + stack.pop());
        System.out.println("Size of stack: " + stack.size());


//    System.out.println("Dataset (lijst_float_8001):");
       //
//        int elementsPerLine = 10;
//        for (int i = 0; i < dynamicFloatArray.size(); i++) {
//            System.out.print(dynamicFloatArray.get(i) + " ");
//            if ((i + 1) % elementsPerLine == 0) {
//                System.out.println();
//            }
//        }
        System.out.println("*******************************");
        System.out.println("testing DoubleLinkedList Marco's data ");
        System.out.println("*******************************");

        Integer[] lijst_oplopend_10000 = new Integer[5000];
        for (int i = 0; i < 5000; i++) {
            lijst_oplopend_10000[i] = i + 1;
        }

        DoubleLinkedList<Integer> doubleList = new DoubleLinkedList<>();

        doubleList.addAll(lijst_oplopend_10000);

        System.out.println("List size after adding elements: " + doubleList.size());

        Iterator<Integer> iterator = doubleList.iterator();
        System.out.print("First 10 elements: ");
        for (int i = 0; i < 10 && iterator.hasNext(); i++) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("...");

        System.out.println("Last element in the list: " + doubleList.get(doubleList.size() - 1));

    }

    private URL createPathForResource(final String resource) {


        var url = getClass().getClassLoader().getResource(resource);

        if (url == null) {
            throw new NullPointerException(resource);
        }
        return url;
    }


}
