package visitor;

public class HtmlElement extends HtmlTag {
    private String tagBody;

    public HtmlElement(String tagName) {
        super(tagName);
    }

    @Override
    public void setTagBody(String tagBody) {
        this.tagBody = tagBody;
    }

    @Override
    public void generateHtml() {
        System.out.println(this.getStartTag() + this.tagBody + this.getEndTag());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
