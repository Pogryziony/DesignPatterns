package patterns.factoryMethod;

class EmphasisTagMethod implements TagMethod {
    @Override
    public String getStartTag() {
        return "<em>";
    }

    @Override
    public String getEndTag() {
        return "</em>";
    }
}
