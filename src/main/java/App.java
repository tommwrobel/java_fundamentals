import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.prefs.Preferences;


/**
 * @author Kamil Wróbel
 * @version 1.0
 */
public class App {
    interface Dupa {
        String A = "asdf";
    }

    /**
     * Nie wiaomo co to robi
     *
     * @param rn
     */
    public static void invoke(Function<String, Integer> rn) {
        rn.apply("a");
    }

    public static void main(String[] args) throws Exception {

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

    }


}

abstract class A<E> {
    public abstract E getName();
}

class B extends A<Integer> {
    @Override
    public Integer getName() {
        return 1;
    }
}



