package patterns.decorator;

import java.awt.*;

public class ParagraphDecorator extends TextDecorator {
    public ParagraphDecorator(TextComponent wrappedComponent) {
        super(wrappedComponent);
    }

    public void write() {
        System.out.print("<p>");
        wrappedComponent.write();
        System.out.print("</p>");
    }
}

