package patterns.simpleFactory;

class MarkTag implements Tag {
    @Override
    public String getStartTag() {
        return "<mark>";
    }

    @Override
    public String getEndTag() {
        return "</mark>";
    }
}
