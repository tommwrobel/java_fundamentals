package designpatterns;

public class Singleton {

    public static void main(String[] args) {
        SingletonExample1 instance = SingletonExample1.getInstance();
    }
}

class SingletonExample1 {
    private static volatile SingletonExample1 instance;

    private SingletonExample1() {
    }

    public static SingletonExample1 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample1.class) {
                if (instance == null) {
                    instance = new SingletonExample1();
                }
            }
        }
        return instance;
    }
}
