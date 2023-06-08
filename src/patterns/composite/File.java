package patterns.composite;

public class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void list() {
        System.out.println("File: " + name);
    }
}
