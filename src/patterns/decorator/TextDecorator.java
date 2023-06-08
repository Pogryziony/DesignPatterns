package patterns.decorator;

import java.awt.*;

public abstract class TextDecorator implements TextComponent {
    public TextComponent wrappedComponent;

    public TextDecorator(TextComponent wrappedComponent) {
        this.wrappedComponent = wrappedComponent;
    }

    public abstract void write();
}
