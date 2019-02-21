package abstractfactorypattern;

public class NYParser implements XMLParser {

    @Override
    public String parse() {
        System.out.println("NY Parsing order XML...");
        return "NY Order XML Message";
    }
}
