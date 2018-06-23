package advancedtechniques.streams;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Optionals {
    public static void main(String[] args) throws IOException {
        Optional<String> op1 = Optional.of("Roman");

        String name = null;
        Optional<String> op2 = Optional.ofNullable(name);

        op2.ifPresent(System.out::println);

        String result = op2.orElse("DUMP"); // Zwraca wartość lub DUMP jeśli wartość = null

       // op2.orElseThrow(()-> new IOException("a"));


        Iterator<String> it = Stream.of("aa").iterator();

        Stream.of("aa").forEach(System.out::println);
        
        
        String[] words = {"kamil","tomek","kinga","szymek","romek","franek","ania","mateusz"};


        List<String> wordsList = Arrays.asList(words);

        String[] words2 = wordsList.parallelStream()
                .collect(Collectors.toList()).toArray(new String[0]);

        System.out.println(Arrays.toString(words));
        System.out.println(Arrays.toString(words2));
        
    }
}
