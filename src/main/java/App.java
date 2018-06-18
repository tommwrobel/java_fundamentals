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

        BlockingQueue<String> elements = new ArrayBlockingQueue<>(5);

        new Thread(() -> {
            while (true) {
                System.out.println("Adding element");

                try {
                    elements.put(String.valueOf(System.currentTimeMillis()));
                    System.out.println("Elements: " + elements.size());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {

                try {
                    System.out.println("Taking element:" + elements.take());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

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



