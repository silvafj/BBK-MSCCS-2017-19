package abstractfactorypattern;

public class TWParser implements XMLParser {
    @Override
    public String parse() {
        System.out.println("TW Parsing feedback XML...");
        return "TW Feedback XML Message";
    }
}
