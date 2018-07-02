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

```java
// Tworzenie
Optional<String> op1 = Optional.of(val); // Używać kiedy val nie może być null
Optional<String> op2 = Optional.ofNullable(val); // Używać kiedy val może przyjąć wartość null
Optional<String> op3 = Optional.empty();

// Pobieranie wartości

String val1 = op2.get() // Nie zalecane - rzuca wyjątek jesli nie ma wartości
String val2 = op2.orElse("bbb") // Zwraca wartość lub 'bbb' jeśli wartości brak
String val3 = op3.orElseGet(e->"bbb") // To samo co orElse ale funkcja w orElseGet wykonwywana tylko kiedy nie ma wartości. Używać kiedy pobranie alternatywnej wartości jest kosztowne

op2.ifPresent(value -> print(value)) // Wykonuje funkcję dla value jeśli ta istnieje i != null
op2.filter(value -> value.length() >2) // Filtruje wartośc jeśli istnieje i zwraca ją lub pusty optional
op2.map(value -> value.toUpperCase()) // Mapuje wartośc optionala jeśli ta istnieje

op2.isPresent() // zwraca boolean mowiący czy wartośc istnieje i != null
op2.orElseThrow(IOException::new) // wyrzuca wyjątek IOException jeśli wartośc nie istnieje

// jeśli wywołanie s.f() zwróci niepustą wartość, na jej rzecz zostanie wywołana metoda g. W przeciwnym razie zostanie zwrócony pusty obiekt Optional<U>.
Optional<U> result = s.f.flatMap(T::g);

// Jeśli którakolwiek z metod — inverse lub squareRoot — zwróci wartość Optiona.empty, to także ostateczny wynik będzie pusty.
Optional<Double> result = Optional.of(-4.0).flatMap(MyMath::inverse).flatMap(MyMath::squareRoot);

```
## 1.8. Gromadzenie wyników

// Zwrócenie iteratora
Iterator<String> it = Stream.of("aa").iterator();

// Przejrzenie wszystkich wartości strumienia
Stream.of("aa").forEach(System.out::println);
Stream.of("aa").forEachOrdered(System.out::println); // Utrata korzyści z przetwarzania równoległego

**java.util.stream.Stream 8**
* ` void forEach(Consumer<? super T> action)`
wywołuje akcję dla każdego elementu strumienia. Jest to operacja kończąca.
* ` Object[] toArray()`
* ` <A> A[] toArrary(IntFunction<A[]> generator)`
zwraca tablicę obiektów bądź tablicę typu A, o ile w wywołaniu zostanie przekazana
referencja do konstruktora A[]::new.
* ` <R,A> R collect(Collector<? super T,A,R> collector)`
zbiera elementy dostępne w tym strumieniu, używając do tego określonego
kolektora. Klasa Collectors udostępnia metody fabryczne pozwalające na
stosowanie kolektorów wielu różnych typów.

**java.util.stream.Collectors 8**
* ` static <T> Collector<T,?,List<T>> toList()`
* ` static <T> Collector<T,?,Set<T>> toSet()`
zwraca kolektory gromadzące elementy na liście lub w zbiorze.
* ` static <T,C extends Collection<T>> Collector<T,?,C>`
toCollection(Supplier<C> collectionFactory)
zwraca kolektor gromadzący elementy w kolekcji dowolnego typu. W wywołaniu
należy przekazać referencję do konstruktora, taką jak TreeSet::new.
* ` static Collector<CharSequence,?,String> joining()`
* ` static Collector<CharSequence,?,String> joining(CharSequence delimiter)`
* ` static Collector<CharSequence,?,String> joining(CharSequence delimiter,CharSequence prefix, CharSequence suffix)`
zwraca kolektor wykonujący konkatenację łańcuchów znaków. Pomiędzy elementami
może być umieszczany separator, istnieje także możliwość określenia prefiksu
i końcówki dodawanych do łańcucha wynikowego. W przypadku pominięcia tych
dodatkowych elementów będą one puste.
* ` static <T> Collector<T,?,IntSummaryStatistics> summarizingInt(ToIntFunction<? super T> mapper)`
* ` static<T> Collector<T,?,LongSummaryStatistics>`
summarizingLong(ToLongFunction<? superT> mapper)
* ` static <T> Collector<T,?,DoubleSummaryStatistics>`
summarizingDouble(ToDoubleFunction<? super T> mapper)
zwraca kolektor generujący obiekt (Int|Long|Double)SummaryStatistic, z którego
można pobrać sumę elementów strumienia, ich liczbę, wartość maksymalną
i minimalną, uzyskane po zastosowaniu do każdego elementu strumienia funkcji
mapper.

**IntSummaryStatistics 8**
**LongSummaryStatistics 8**
**DoubleSummaryStatistics 8**
* ` long getCount()`
zwraca liczbę przetworzonych elementów.
* ` (int|long|double) getSum()`
* ` double getAverage()`
zwraca sumę lub średnią przetworzonych elementów bądź 0, jeśli nie zostały
przetworzone żadne elementy.
* ` (int|long|double) getMax()`
* ` (int|long|double) getMin()`
zwraca wartość maksymalną lub minimalną przetworzonych elementów. W razie
braku elementów zwracana jest wartość (Integer|Long|Double).(MAX|MIN)_VALUE.

## 1.9. Gromadzenie wyników w mapach

```java
List<User> users = new ArrayList<User>() {{
    add(new User("kamil"));
    add(new User("ania"));
    add(new User("tomek"));
    add(new User("kamil"));
}};

// Mapa user.id -> user (HashMap)
Map<Integer, User> usersMap = users.stream().collect(Collectors.toMap(
         User::getId, // Klucz
         e -> e, // Wartość
         (oldK, newK) -> newK, // Rozwiązanie konfliktu kluczy [opcjonalny].
         TreeMap::new)); // Typ wynikowej mapy [opcjonalny] default: HashMap / ConcurrentHashMap

// Jeśli podczas operacji pojawią się 2 takie same klucze to sotanie wyrzucony IllegalStateException,
// chyba, że rozwiążemy konflikt przy użyciu 3 parametru

// Istnieją też collectory toConcurrentMap
```

## 1.10. Grupowanie i podział

```java
Map<String, List<User>> usersMap2 = users.stream()
         .collect(Collectors.groupingBy(User::getName));

// Grupuje userów po ich imionach. Pod kluczem ania będzie lista zawierająca jednego usera o iminiu ania
// natomiast pod kluczem kamil będzie lista z 2 userami o imieniu kamil

```

## 1.11. Kolektory przetwarzające

// Wartościami mogą też być inne elementy niż lista - tutaj ilość elementów
 Map<String, Long> usersMap2 = users.stream()
         .collect(Collectors.groupingBy(User::getName, Collectors.counting()));

// Inne możliwe wartości to:
//  - toSet() - wartośść będzie setem
//  - summing - wartością będzie suma wartości elementów listy np summintInt(User::getId)
//  - maxBy(Comparator.comparing(City::getPopulation))) i minBy - wartością będzie wartość max / min

## 1.12. Operacje redukcji

Redukcja strumienia do jednej wartości opakowanej w Optional

```java
// Redukuje listę userów do jednej wartości zawierającej sumę długości imion wszystkich userów
// W operacji redukcji nie powinna mieć znaczenia kolejność redukowania

 Optional<Integer> names = users.stream()
         .map(u -> u.getName().length())
         .reduce(Integer::sum); //  .reduce((x, y) -> x + y);

 names.ifPresent(System.out::println);
 
 // Alternatywnie to samo można osiągnąć używająć IntStream:
 
 int names2 = users.stream()
          .mapToInt(u -> u.getName().length())
          .sum();
```

## 1.13. Strumienie danych typów prostych

Umoliwiają tworzenie strumieniu typów prostych bez konieczności opakowywania ich co znacząco zwiększa wydajność przetwarzania tych strumieni

```java

IntStream stream = IntStream.of(1, 1, 2, 3, 5);
stream = Arrays.stream(values, from, to); // wartości są zapisane w tablicy int[]

IntStream zeroToNinetyNine = IntStream.range(0, 100); // górna granica nie jest dołączana do zakresu
IntStream zeroToHundred = IntStream.rangeClosed(0, 100); // górna granica jest dołączana

// Przekształcenie strumienia obiektów na strumień typów prostych:
IntStream lengths = words.mapToInt(String::length);

// I w drugą stronę: Strumień typów prosty na strumień obiektów:
Stream<Integer> integers = IntStream.range(0, 100).boxed();
```

## 1.14. Strumienie równoległe

//TODO

Operacje w strumieniach równoległych wykonywane są jednocześnie w kilku watkach - należ uważać aby nie doprowadzić do wyścigu gdzie w strumieniu modyfikujemy jakąś wartość z poza strumienia

`Stream<String> parallelWords = words.parallelStream();`

`Stream<String> parallelWords = Stream.of(wordArray).parallel();`

# 2. Wejście wyjście

##  2.1. Strumienie wejścia-wyjścia.
##  2.2. Strumienie tekstowe.
##  2.3. Odczyt i zapis danych binarnych.
##  2.4. Strumienie obiektów i serializacja.
##  2.5. Zarządzanie plikami.
##  2.6. Pliki mapowane w pamięci.
##  2.7. Wyrażenia regularne.