import java.io.EOFException;
import java.io.IOException;
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

        try{
            // cos tu może wyrzucić wyjątek
        }catch(IOException e){
        }

        // Przechwytywanie kilku wyjątków

        try{
            // cos tu może wyrzucić wyjątek
        }catch(IOException e){

        }catch (EOFException e){

        }

        // Przechwytywanie kilku wyjąktów w jednych catch
        try{
            // Rzucza 2 różne wyjątki
        }catch(IOException | EOFException ex){
            //ex nie można zmienić referencji = ex jest finalne w tym przypadku
        }

        // Klauzura finally
        try{

        }catch(Exception ex){

        }finally {
            // wykona się bez względu czy w try wystąpi wyjątek czy nie
        }

        // Try z zasobami
        try(Resource res = new Resource()){
            // Resource musi implementować AutoCloseable
            // Błędy wyrzucone przez AutoCloseable.close są tłumione
        }catch(Exception ex){
        }



    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }


    class Outer {

        public void method1(){

            Comparable cmp = new Comparable() {
                @Override
                public int compareTo(Object o) {
                    return 0;
                }
            };
        }
    }
}


