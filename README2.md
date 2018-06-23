# 1. Biblioteka strumieni Java SE 8

## 1.1. Od iteracji do operacji na strumieniach

* Strumienie nie przechowuja żadnych elementów - te znajdują się tylko w źródle strumienia
* Strumienie nie modyfikują źródła (np lista words poniżej)
* Strumienie są leniwe - wykonywane są dopiero kiedy potrzebne są wyniki

Operacje na strumieniach mają 3 składowe
* Utworzenie strumienia
* Operacje pośrednie - zwracają strumień
* Operacje kończące

```java
//Zliczanie długich słow w kolekcji

List<String> words = ...

long count = words.stream()
.filter(w -> w.length() > 12)
.count();

long count = words.parallelStream()
.filter(w -> w.length() > 12)
.count();
```
## 1.2. Tworzenie strumieni

```java
// Wywłanie metody stream kolekcji:

List<String> words...
words.stream()...

// Dla tablic wywołanie Stream.of(someArray)
Stream.of("a","b"),"c");

// Dla tablic można użyć też Arrays.stream(arr, idxFrom, idxTo)
String[] arr..
Arrays.stream(arr, 0, arr.length())...

// Tworzenie pustego strumienia
Stream.empty();

// Strumień zawierający linie w pliku
Stream<String> lines = Files.lines(Path.from

// Tworzenie nieskończonych strumieni:

   // Nieskończony strumień zawierający słowa 'echo'
   Stream<String> echos = Stream.generate(() -> "echo"); // Stream.generate(Supplier s);

   // Nieskończony strumień liczb losowych
   Stream<Double> randoms = Stream.generate(Math::random);

   // Nieskończony strumień zawierający kolejne liczby
   Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
```
## 1.3. Metody filter, map oraz flatMap

```java
// filter - bierze arument Predicate<T> p i zwraca strumień zawierający tylko elemnty dla których p.text(T t) zwróci true

Stream<String> longWords = wordList.stream().filter(w -> w.length() > 12);

// map - służy do przekrzstałcania elementów strumienia

Stream.of("a","bb","ccc")
                .map(String::length)
                .forEach(System.out::println);

// flatMap - spłaszcza strumień. Np Jeśli chcemy srobić strumień zawierający litery wszystkich słów w kolekcji words:
// Def: zwraca strumień utworzony poprzez połączenie wyników zwróconych przez funkcję mapper po przekazaniu do niej elementów tego strumienia. (Trzeba pamiętać, że każdy z tych wyników jest strumieniem).

Stream.of("a", "bb", "ccc")
           .flatMap(CreatingStreams::letters) //letters zwraca Strumień znaków z przekazanego slowa
           .forEach(System.out::println);
```

**java.util.stream.Stream 8**
* `Stream<T> filter(Predicate<? super T>` predicate)
zwraca strumień zawierający elementy tego strumienia spełniające podany warunek.
* `<R> Stream<R> map(Function(? super T, ? extends R> mapper)`
zwraca strumień zawierający wynik, który obejmuje wyniki zwracane przez funkcję
mapper po przekazaniu do niej elementów tego strumienia.
* `<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>>`
mapper)
zwraca strumień utworzony poprzez połączenie wyników zwróconych przez funkcję
mapper po przekazaniu do niej elementów tego strumienia (Trzeba pamiętać,że każdy z tych wyników jest strumieniem).

## 1.4. Pobieranie podstrumieni i łączenie strumieni


```java

// strumień.limit(n) - zwraca nowy strumien zawierający n poczkątkowych elementów źródłowego strumienia
// strumień.skip(n) - zwraca nowy strumień w którycm pominięte jest n początkowych elementów źródłowego strumienia

// Lacznie strumieni za pomoca metdy concat (letters zwraca strumien)

Stream<String> combined = Stream.concat(letters("Witaj"), letters("świecie"));

```

**java.util.stream.Stream 8**
* `Stream<T> limit(long maxSize)`
    zwraca strumień, który będzie zawierał co najwyżej maxSize elementów tego
    strumienia.
* `Stream<T> skip(long n)`
    zwraca strumień, w którym zostało pominiętych n pierwszych elementów tego
    strumienia.
* `static <T> Stream<T>` concat(Stream<? extends T> a, Stream<? extends T>b)
    zwraca strumień, którego

## 1.5. Inne przekształcenia strumieni

```java
// distinct - używa equals do sprawdzania czy elementy się powtarzają i zwraca strumien bez powtórzeń
Stream<String> uniqueWords = Stream.of("radośnie", "radośnie", "radośnie", "delikatnie").distinct();

// sorted - zwraca strumień posortowanych elementów
Stream<String> sorted= Stream.of("radośnie", "radośnie", "radośnie", "delikatnie")
                            .sorted(Comparator.comparing(String::length).reversed());
// peek - zwraca strumień źródłowy ale wykonuje określoną funkcję na każdym elemencie - np do debugowania przydatne
sorted.peek(e -> System.out.println(e));
```

**java.util.stream.Stream 8**
* ` Stream<T> distinct()`
zwraca strumień zawierający jedynie unikalne elementy tego strumienia.
* ` Stream<T> sorted()`
* ` Stream<T> sorted(Comparator<? super T> comparator)`
zwraca strumień zawierający posortowane elementy tego strumienia. Pierwsza
wersja metody wymaga tego, by elementy strumienia były instancjami klasy
implementującej interfejs Comparable.
* ` Stream<T> peek(Consumer<? super T> action)`
zwraca strumień zawierający te same elementy co ten strumień, przy czym

## 1.6. Proste operacje redukcji
    * Operacje końcowe - redukują strumień do postaci jednej wartości
    
**java.util.stream.Stream 8**
* ` Optional<T> max(Comparator<? super T> comparator)`
* ` Optional<T> min(Comparator<? super T> comparator)`
zwraca maksymalny lub minimalny element strumienia, używając przy tym
uporządkowania zdefiniowanego przez podany komparator albo pusty obiekt
Optional (jeśli strumień jest pusty). Są to operacje końcowe.
* ` Optional<T> findFirst()`
* ` Optional<T> findAny()`
zwraca pierwszy lub dowolny element strumienia bądź pusty obiekt Optionl
(jeśli strumień jest pusty). Są to operacje końcowe.
* ` boolean anyMatch(Predicate<? super T> predicate)`
* ` boolean allMatch(Predicate<? super T> predicate)`
* ` boolean noneMatch(Predicate<? super T> predicate)`
zwraca true, jeśli dowolny element strumienia lub wszystkie jego elementy spełniają
podany predykat albo jeśli nie spełnia go żaden z tych elementów. Są to operacje
końcowe.  
  
## 1.7. Typ Optional 
## 1.7.1. Sposoby posługiwania się wartościami Optional 
## 1.7.2. Jak nie należy używać wartości opcjonalnych 
## 1.7.3. Tworzenie obiektów typu Optional 
## 1.7.4. Łączenie funkcji zwracających wartości opcjonalne przy użyciu flatMap 
## 1.8. Gromadzenie wyników 
## 1.9. Gromadzenie wyników w mapach 
## 1.10. Grupowanie i podział 
## 1.11. Kolektory przetwarzające 
## 1.12. Operacje redukcji 
## 1.13. Strumienie danych typów prostych 
## 1.14. Strumienie równoległe 