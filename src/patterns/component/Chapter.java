package patterns.component;

import java.util.ArrayList;
import java.util.List;

public class Chapter implements DocumentComponent {
    private String title;
    private List<DocumentComponent> components;

    public Chapter(String title) {
        this.title = title;
        components = new ArrayList<>();
    }

    public void addComponent(DocumentComponent component) {
        components.add(component);
    }

    public void removeComponent(DocumentComponent component) {
        components.remove(component);
    }

    public void printTableOfContents(String indent) {
        System.out.println(indent + title);
        for (DocumentComponent component : components) {
            component.printTableOfContents(indent + "  ");
        }
    }
}

