import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.Function;

public class App {
    interface Dupa {
        String A = "asdf";
    }

    public static void invoke(Function<String, Integer> rn){
        rn.apply("a");
    }

    public static void main(String[] args) throws Exception {

        String[] arr = {"a"};

        Arrays.sort(arr, String::compareTo);
        Arrays.sort(arr, (a,b) -> a.compareTo(b));


        class A {
            public void print() {
                System.out.println("A");
            }
        }

        class B extends A {
            public void print() {
                System.out.println("B");
            }
        }

        A[] arr = {new A(), new B()};

        arr[0].print(); // prints A
        arr[1].print(); // prints B

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
