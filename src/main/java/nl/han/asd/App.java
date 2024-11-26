package nl.han.asd;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class App {

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

        IDynamicArray<Integer> dynamicIntegerArray = new DynamicArray<>(1);
        IDynamicArray<Float> dynamicFloatArray = new DynamicArray<>(1);
        IDynamicArray<Float> dynamicMixedArray = new DynamicArray<>(1);

        dynamicIntegerArray.addAll(datasetSorting.lijst_null_1);
        dynamicFloatArray.addAll(datasetSorting.lijst_float_8001);
      // dynamicMixedArray.addAll(datasetSorting.lijst_onsorteerbaar_3); //will give"type" error

        System.out.println(" Test contains for not existed element: " + dynamicIntegerArray.contains(4));
        dynamicIntegerArray.add(4); // Perform the add operation
        System.out.println("Test contains after adding the element: "+ dynamicIntegerArray.contains(4));
        System.out.println(" Size of dynamic array " + dynamicIntegerArray.size());
        System.out.println(dynamicIntegerArray.toString());
        System.out.println();
        System.out.println("size of dynamic array before clear is: " + dynamicFloatArray.size());
        dynamicFloatArray.clear();
        System.out.println("size of dynamic array after clear is: " + dynamicFloatArray.size());



        System.out.println("*******************************");
        System.out.println("testing Stack against Marco's data ");
        System.out.println("*******************************");
        IStack<Integer> stackOfInteger = new StackOfDynamicArray<>();
        IStack<Float> stackOfFloat = new StackOfDynamicArray<>();
        IStack<Float> stackOfMixed = new StackOfDynamicArray<>();

        stackOfInteger.pushAll(datasetSorting.lijst_willekeurig_10000);
        stackOfInteger.push(8);
        stackOfInteger.push(9);
        stackOfInteger.push(10);
        System.out.println("Top element (peek): " + stackOfInteger.peek());
        stackOfInteger.pop();
        System.out.println("Top element (peek): " + stackOfInteger.peek());
        System.out.println("Size of stack: " + stackOfInteger.size());
        System.out.println("Top element (pop): " + stackOfInteger.pop());
        System.out.println("Size of stack: " + stackOfInteger.size());




        System.out.println("*******************************");
        System.out.println("testing DoubleLinkedList Marco's data ");
        System.out.println("*******************************");

        DoubleLinkedList<Integer> doubleLinkedListOfInteger = new DoubleLinkedList<>();
        DoubleLinkedList<Float> doubleLinkedListOfFloat = new DoubleLinkedList<>();
        doubleLinkedListOfInteger.addAll(datasetSorting.lijst_willekeurig_10000);
        doubleLinkedListOfFloat.addAll(datasetSorting.lijst_float_8001);
       // doubleLinkedListOfFloat.addAll(datasetSorting.lijst_willekeurig_10000); error due to type mis-match

        System.out.println("List size after adding elements: " + doubleLinkedListOfInteger.size());
        Iterator<Integer> iterator = doubleLinkedListOfInteger.iterator();
        System.out.print("First 10 elements: ");
        for (int i = 0; i < 10 && iterator.hasNext(); i++) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("...");

        System.out.println("Last element in the list: " + doubleLinkedListOfInteger.get(doubleLinkedListOfInteger.size() - 1));

    }

    private URL createPathForResource(final String resource) {


        var url = getClass().getClassLoader().getResource(resource);

        if (url == null) {
            throw new NullPointerException(resource);
        }
        return url;
    }


}
