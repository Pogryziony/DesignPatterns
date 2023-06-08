package patterns.factoryMethod;

public class MarkTagFactoryMethod implements TagFactoryMethod {
    @Override
    public TagMethod createTag() {
        return new MarkTagMethod();
    }
}