package compositepattern;

import java.util.ArrayList;
import java.util.List;

public class HtmlParentElement extends HtmlTag {
  private List<HtmlTag> children;

  public HtmlParentElement(String s) {
    super(s);
    this.children = new ArrayList<>();
  }

  @Override
  public void addChildTag(HtmlTag htmlTag) {
    this.children.add(htmlTag);
  }

  @Override
  public void removeChildTag(HtmlTag htmlTag) {
    this.children.remove(htmlTag);
  }

  @Override
  public List<HtmlTag> getChildren() {
    return this.children;
  }

  @Override
  public void generateHtml() {
    System.out.println(this.getStartTag());
    for (HtmlTag child: this.getChildren()) {
      child.generateHtml();
    }
    System.out.println(this.getEndTag());

  }
}
