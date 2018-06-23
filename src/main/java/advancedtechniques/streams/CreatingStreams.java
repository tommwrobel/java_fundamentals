package advancedtechniques.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CreatingStreams {
    public static void main(String[] args) throws IOException {

        // Wypisuje do konsoli 3 pierwsze linie z pliku .gitignore
        Files.lines(Paths.get(".gitignore"))
                .limit(3)
                .forEach(System.out::println);


        // Przekształca strumień napisów na strumien długości tych napisów
        Stream.of("a", "bb", "ccc")
                .map(String::length)
                .forEach(System.out::println);

        // Flat map splaszcza strumienie
        Stream.of("a", "bb", "ccc")
                .flatMap(CreatingStreams::letters)
                .forEach(System.out::println);

        // Swraca strumień zawierający tylko unikalne wartości
        Stream<String> uniqueWords
                = Stream.of("radośnie", "radośnie", "radośnie", "delikatnie").distinct();

        // Sortuje elementy i zwraca nowy posortowany strumień
        Stream<String> sorted
                = Stream.of("radośnie", "radośnie", "radośnie", "delikatnie")
                .sorted(Comparator.comparing(String::length).reversed());
    }

    public static Stream<String> letters(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++)
            result.add(s.substring(i, i + 1));
        return result.stream();
    }
}
