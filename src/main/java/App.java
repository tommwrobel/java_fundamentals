import sun.text.normalizer.UTF16;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.function.Function;


/**
 * @version 1.0
 * @author Kamil Wróbel
 */
public class App {
    interface Dupa {
        String A = "asdf";
    }

    public static void invoke(Function<String, Integer> rn){
        rn.apply("a");
    }

    public static void main(String[] args) throws Exception {



        Files.createFile(Paths.get("sample.txt"));

        BigInteger bi = BigInteger.ONE;




    }




}


