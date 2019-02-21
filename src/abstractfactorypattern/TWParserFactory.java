package abstractfactorypattern;

public class TWParserFactory implements AbstractParserFactory {
    @Override
    public XMLParser getParserInstance(String parserType) {
        return new TWParser();
    }
}
