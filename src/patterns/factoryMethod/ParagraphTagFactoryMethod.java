package patterns.factoryMethod;

public class ParagraphTagFactoryMethod implements TagFactoryMethod {
    @Override
    public TagMethod createTag() {
        return new ParagraphTagMethod();
    }
}
