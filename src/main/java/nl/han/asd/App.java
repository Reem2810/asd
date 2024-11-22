package nl.han.asd;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

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

        DynamicArray<Float> dynamicFloatArray = new DynamicArray<>(1);
        DynamicArray<Integer> dynamicIntegerArray = new DynamicArray<>(1);
       DynamicArray<Float> dynamicObjectArray = new DynamicArray<>(1);

        dynamicIntegerArray.addAll(datasetSorting.lijst_null_1);
        dynamicFloatArray.addAll(datasetSorting.lijst_float_8001);
      // dynamicObjectArray.addAll(datasetSorting.lijst_onsorteerbaar_3);

        System.out.println(" Size of dynamic array " + dynamicFloatArray.size());

        IStack<Integer> stack = new DynamicStack<>();

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
    }

    private URL createPathForResource(final String resource) {


        var url = getClass().getClassLoader().getResource(resource);

        if (url == null) {
            throw new NullPointerException(resource);
        }
        return url;
    }


}
