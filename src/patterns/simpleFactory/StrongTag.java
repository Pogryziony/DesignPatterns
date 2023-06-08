package patterns.simpleFactory;

class StrongTag implements Tag {
    @Override
    public String getStartTag() {
        return "<strong>";
    }

    @Override
    public String getEndTag() {
        return "</strong>";
    }
}
