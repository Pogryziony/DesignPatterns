package patterns.decorator;

import java.awt.*;

public class PlainText implements TextComponent {
    private String text;

    public PlainText(String text) {
        this.text = text;
    }

    public void write() {
        System.out.print(text);
    }
}
