package patterns.component;

public class Paragraph implements DocumentComponent {
    private String content;

    public Paragraph(String content) {
        this.content = content;
    }

    public void printTableOfContents(String indent) {
        // Akapit nie jest uwzględniany w spisie treści
    }
}
