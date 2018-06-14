public class BlokInicjalizacyjny {
    public static void main(String... args) {
        new Dog();
        //new Animal();
    }
}

class Animal {
    {
        System.out.println("Animal - not static block 1");
    }

    static {
        System.out.println("Animal - static block");
    }

    {
        System.out.println("Animal - not static block 2");
    }

    public Animal() {
        System.out.println("Animal - constructor");
    }
}

class Dog extends Animal {
    static {
        System.out.println("Dog - static block");
    }

    {
        System.out.println("Dog - not static block");
    }

    public Dog() {
        
        System.out.println("Dog - constructor");
    }
}
