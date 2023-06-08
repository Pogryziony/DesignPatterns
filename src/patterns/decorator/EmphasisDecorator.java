package patterns.decorator;

import java.awt.*;

public class EmphasisDecorator extends TextDecorator {
    public EmphasisDecorator(TextComponent wrappedComponent) {
        super(wrappedComponent);
    }

    public void write() {
        System.out.print("<em>");
        wrappedComponent.write();
        System.out.print("</em>");
    }
}
