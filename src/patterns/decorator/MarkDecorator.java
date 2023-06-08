package patterns.decorator;

import java.awt.*;

public class MarkDecorator extends TextDecorator {
    public MarkDecorator(TextComponent wrappedComponent) {
        super(wrappedComponent);
    }

    public void write() {
        System.out.print("<mark>");
        wrappedComponent.write();
        System.out.print("</mark>");
    }
}

