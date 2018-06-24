package advancedtechniques.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamCollectors {
    public static void main(String[] args) {
        List<User> users = new ArrayList<User>() {{
            add(new User("kamil"));
            add(new User("ania"));
            add(new User("tomek"));
            add(new User("kamil"));
        }};

        Map<Integer, User> usersMap = users.stream().collect(Collectors.toMap(
                User::getId, // Klucz
                e -> e, // Wartość
                (oldK, newK) -> newK, // Rozwiązanie konfliktu kluczy [opcjonalny].
                TreeMap::new)); // Typ wynikowej mapy [opcjonalny]

        System.out.println(usersMap);


        Map<String, Long> usersMap2 = users.stream()
                .collect(Collectors.groupingBy(User::getName, Collectors.counting()));

        System.out.println(usersMap2);


        Optional<Integer> names = users.stream()
                .map(u -> u.getName().length())
                .reduce(Integer::sum); //  .reduce((x, y) -> x + y);

        int names2 = users.stream()
                .mapToInt(u -> u.getName().length())
                .sum();

        names.ifPresent(System.out::println);
        System.out.println(names2);


    }
}

class User {
    private static int nextId = 0;
    private int id;
    private String name;

    public User(String name) {
        this.id = nextId++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
