import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;


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
        Integer[] arr = {1, 2, 3};

        List<Integer> list = Arrays.asList(arr);

        arr[0] = 44;

        System.out.println(list);


    }


}


