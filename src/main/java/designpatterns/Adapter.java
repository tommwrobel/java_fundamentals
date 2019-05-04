package designpatterns;

import java.util.Enumeration;
import java.util.Iterator;

public class Adapter {


}


class EnumerationToIteratorAdapter<E> implements Iterator<E> {

    private final Enumeration<E> enumeration;

    EnumerationToIteratorAdapter(Enumeration<E> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public E next() {
        return enumeration.nextElement();
    }
}
