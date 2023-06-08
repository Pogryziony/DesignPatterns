package patterns.factoryMethod;

class StrongTagMethod implements TagMethod {
    @Override
    public String getStartTag() {
        return "<strong>";
    }

    @Override
    public String getEndTag() {
        return "</strong>";
    }
}
