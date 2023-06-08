package patterns.decorator;

import java.awt.*;

public class StrongDecorator extends TextDecorator {
    public StrongDecorator(TextComponent wrappedComponent) {
        super(wrappedComponent);
    }

    public void write() {
        System.out.print("<strong>");
        wrappedComponent.write();
        System.out.print("</strong>");
    }
}

