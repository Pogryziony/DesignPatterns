package patterns.simpleFactory;

class EmphasisTag implements Tag {
    @Override
    public String getStartTag() {
        return "<em>";
    }

    @Override
    public String getEndTag() {
        return "</em>";
    }
}
