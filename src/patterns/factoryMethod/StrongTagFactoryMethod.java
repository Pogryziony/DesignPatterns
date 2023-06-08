package patterns.factoryMethod;

public class StrongTagFactoryMethod implements TagFactoryMethod {
    @Override
    public TagMethod createTag() {
        return new StrongTagMethod();
    }
}
