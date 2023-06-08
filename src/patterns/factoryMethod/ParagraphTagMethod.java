package patterns.factoryMethod;

class ParagraphTagMethod implements TagMethod {
    @Override
    public String getStartTag() {
        return "<p>";
    }

    @Override
    public String getEndTag() {
        return "</p>";
    }
}
