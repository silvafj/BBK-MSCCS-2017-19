package abstractfactorypattern;

public final class ParserFactoryProducer {

  private ParserFactoryProducer() {
    throw new AssertionError();
  }

  public static AbstractParserFactory getFactory(String factoryType) {
    // TODO
    return null;
  }

}