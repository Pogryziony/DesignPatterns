package patterns.simpleFactory;

class ParagraphTag implements Tag {
    @Override
    public String getStartTag() {
        return "<p>";
    }

    @Override
    public String getEndTag() {
        return "</p>";
    }
}
