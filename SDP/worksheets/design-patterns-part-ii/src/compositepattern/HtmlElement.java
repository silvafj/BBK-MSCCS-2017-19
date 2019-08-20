package compositepattern;

public class HtmlElement extends HtmlTag {
  private String tagBody;

  public HtmlElement(String s) {
    super(s);
  }

  @Override
  public void setTagBody(String tagBody) {
    this.tagBody = tagBody;
  }

  public String getTagBody() {
    return tagBody;
  }

  @Override
  public void generateHtml() {
    System.out.println(this.getStartTag() + this.getTagBody() + this.getEndTag());
  }
}
