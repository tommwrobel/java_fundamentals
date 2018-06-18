# Wzorce projektowe

## Observer - obserwator

Definiuje między obiektami relację jeden do wielu w taki sposób, że kiedy wybrany obiekt zmienia swój stan, to wszystkie jego obiekty zależne zostają o tym powiadomione i automatycznie zaktualizowane.

**Przykładowa implementacja:**
```java
        // Klasa którą chcemy obserwować - rozszerza Observable

        class Monitor extends Observable {
            private int temp = 0;

            public void setTemperature(int temp) {
                this.temp = temp;
                // Ustawienie setChanged powoduje że metoda notifyObserers powiadomi wszystkich obserwatorów
                this.setChanged();
                this.notifyObservers();
            }

            public int getTemp(){
                return this.temp;
            }
        }

        // Klasa której obiekty będą reagowały na zmiany w Monitorze
        
        class Display implements Observer {

            public void displayTemp(int temp) {
                System.out.println("Current temp is: " + temp);
            }

            @Override
            public void update(Observable o, Object arg) {
                Monitor m = (Monitor)o;
                this.displayTemp(m.getTemp());
            }
        }

        Monitor monitor = new Monitor();

        Display display1 = new Display();
        Display display2 = new Display();

        
        // Dodanie obserwatorów do monitora
        monitor.addObserver(display1);
        monitor.addObserver(display2);

        monitor.setTemperature(33);

        //Output:
        // Current temp is: 33
        // Current temp is: 33
```