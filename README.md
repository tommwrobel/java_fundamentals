# Java podstawy

## 2. Środowisko programistyczne Javy

### Kompilacja i uruchamianie programów


```
javac Welcome.java
java Welcome
```

## 3. Podstawowe elementy języka Java

### 3.3 Typy danych

```

int     4 bajty     -2mld - 2mld
short   2 bajty     -32768 - 32767
long    8 bajtów    
byte    1 bajt      -128 - 127
float   4 bajty     około ±3,40282347E+38F (6 – 7 znaczących cyfr dziesiętnych)
double  8 bajtów    około ±1,79769313486231570E+308 (15 znaczących cyfr dziesiętnych)
```

```
char - można zapisywać jako kody unicode w zakresie od \u0000 do \uFFFF
```

#### 3.5.2 Konwersja typów numerycznych

Strzałki przerywane - konwersja mogąca powodować utratę danych

![alt konwersja](images/konwersja.png)


#### 3.5.9 Typ wyliczeniowy Enum

* Jest klasą posiadającą określoną liczbę instancji
* Dziedziczy po Enum
* Można używać jako argument `switch`

```java
enum Size {
    SMALL("SM"), MEDIUM("M"), LARGE("L")
    
    private String abbrv;
    
    private Size(String abbr){
        this.abbrv = abbrv;
    }
    
    public String getAbbrv(){
        return this.abbrv;
    }
}
```

### Lańcuchy

* Są niemodyfikowalne (immutable)
* Dwa te same łańcuchy mogą być w rzeczywistości jednym w pamięci


Przydatne metody

```java
int compareTo(String other) //Zwraca wartość ujemną, jeśli łańcuch znajduje się przed innym (other) łańcuchemw kolejności słownikowej

String join("delimiter", CharSequence... elements) //Zwraca nowy łańcuch będący połączeniem wszystkich elementów za pomocą
                                                     określonego znaku.

```