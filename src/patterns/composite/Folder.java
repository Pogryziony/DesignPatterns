package patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components;
    private Folder parent;

    public Folder(String name) {
        this.name = name;
        components = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    public void list() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent component : components) {
            component.list();
        }
    }

    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    public FileSystemComponent getComponent(String name) {
        for (FileSystemComponent component : components) {
            if (component.getName().equals(name)) {
                return component;
            }
        }
        return null;
    }
}

