package patterns.simpleFactory;

public class TagFactory {
    public static Tag createTag(String tagName) {
        switch (tagName.toLowerCase()) {
            case "strong":
                return new StrongTag();
            case "p":
                return new ParagraphTag();
            case "em":
                return new EmphasisTag();
            case "mark":
                return new MarkTag();
            default:
                throw new IllegalArgumentException("Invalid tag: " + tagName);
        }
    }
}