package patterns.factoryMethod;

class MarkTagMethod implements TagMethod {
    @Override
    public String getStartTag() {
        return "<mark>";
    }

    @Override
    public String getEndTag() {
        return "</mark>";
    }
}
