package designpatterns;

public class Factory {
    public static void main(String[] args) {

        Animal dog = AnimalFactory.createAnimal(AnimalType.DOG);
        Animal sparrow = AnimalFactory.createAnimal(AnimalType.SPARROW);

        dog.move();
        sparrow.move();

    }
}

class AnimalFactory {

    public static Animal createAnimal(AnimalType animalType) {
        switch (animalType) {
            case DOG:
                return new Dog();
            case SPARROW:
                return new Sparrow();
            default:
                throw new IllegalArgumentException(String.format("Illegal argument %s", animalType));
        }
    }

}

enum AnimalType {
    DOG, SPARROW
}

interface Animal {
    void move();

    int getNumberOfLegs();
}

class Dog implements Animal {
    @Override
    public void move() {
        System.out.println("Dog is moving");
    }

    @Override
    public int getNumberOfLegs() {
        return 4;
    }
}

class Sparrow implements Animal {

    @Override
    public void move() {
        System.out.println("Duck is flying");
    }

    @Override
    public int getNumberOfLegs() {
        return 2;
    }
}


