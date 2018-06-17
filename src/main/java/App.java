import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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
        Thread t1 = new Thread(()->{
            throw new ArrayIndexOutOfBoundsException("hehe");
        });

        t1.start();

        t1.setUncaughtExceptionHandler((t, e) -> {

        });


        ReentrantLock myLock = new ReentrantLock();
        myLock.lock();

        try {
            // sekcja krytyczna
        }
        finally {
            myLock.unlock(); // Zapewnienie, że blokada zostanie zdjęta, nawet jeśli wystąpi wyjątek.
        }

    }


}

abstract class A<E>{
    public abstract E getName();
}

class B extends A<Integer> {
    @Override
    public Integer getName(){
        return 1;
    }
}



