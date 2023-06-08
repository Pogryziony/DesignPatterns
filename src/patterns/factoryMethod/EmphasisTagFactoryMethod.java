package patterns.factoryMethod;

public class EmphasisTagFactoryMethod implements TagFactoryMethod {
    @Override
    public TagMethod createTag() {
        return new EmphasisTagMethod();
    }
}
