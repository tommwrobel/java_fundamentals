package designpatterns;

import java.util.ArrayList;
import java.util.List;

public class Composite {
    public static void main(String[] args) {


        HierarchyElement h1 = new HierarchyElement();

        HierarchyElement h2 = new HierarchyElement();
        HierarchyElement h3 = new HierarchyElement();

        HierarchyElement h4 = new HierarchyElement();
        HierarchyElement h5 = new HierarchyElement();

        HierarchyElement h6 = new HierarchyElement();
        HierarchyElement h7 = new HierarchyElement();

        HierarchyElement h8 = new HierarchyElement();


        h7.addElement(h8);

        h5.addElement(h6);
        h5.addElement(h7);

        h3.addElement(h4);
        h3.addElement(h5);

        h1.addElement(h2);
        h1.addElement(h3);


        System.out.println(h1.getNumberOfSubElements());
        System.out.println(h5.getNumberOfSubElements());



    }
}

class HierarchyElement {
    List<HierarchyElement> hierarchyElements = new ArrayList<>();

    public boolean isLeaf() {
        return hierarchyElements.size() == 0;
    }

    public List<HierarchyElement> getHierarchyElements() {
        return hierarchyElements;
    }

    public void addElement(HierarchyElement hierarchyElement) {
        hierarchyElements.add(hierarchyElement);
    }

    public int getNumberOfSubElements() {
        return hierarchyElements.size() + hierarchyElements.stream().mapToInt(HierarchyElement::getNumberOfSubElements).sum();
    }
}
