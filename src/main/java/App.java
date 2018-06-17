import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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
        Integer[] arr = {1, 2, 3};

        List<Integer> list = Arrays.asList(arr);

        arr[0] = 44;

        System.out.println(list);

        Properties prop = new Properties();
        //properties_example.txt
        //property1=hoho
        prop.load(new FileInputStream(new File("properties_example.txt")));
        System.out.println(prop.getProperty("property1"));

        URL resource = App.class.getResource("Tablice.class");

        System.out.println(resource.getContent());

        System.out.println(System.getProperty("user.home"));

        Preferences node = Preferences.userNodeForPackage(App.class);

//        node.putInt("Name",44);
//        node.put("Zupa","dupa");

        System.out.println(Arrays.toString(node.keys()));

        System.out.println(node.getInt("Name",55));

        node.exportNode(new FileOutputStream("backup.xml"));
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



