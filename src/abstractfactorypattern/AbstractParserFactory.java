package abstractfactorypattern;

public interface AbstractParserFactory {
    public XMLParser getParserInstance(String parserType);
}