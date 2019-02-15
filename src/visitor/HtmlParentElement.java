package visitor;

import java.util.ArrayList;
import java.util.List;

public class HtmlParentElement extends HtmlTag {

  public HtmlParentElement(String tagName) {
  }

  @Override
  public String getTagName() {
    return null;
  }

  @Override
  public void setStartTag(String tag) {
  }

  @Override
  public void setEndTag(String tag) {
  }

  @Override
  public String getStartTag() {
    return null;
  }

  @Override
  public String getEndTag() {
    return null;
  }

  @Override
  public void addChildTag(HtmlTag htmlTag) {
  }

  @Override
  public void removeChildTag(HtmlTag htmlTag) {
  }

  @Override
  public List<HtmlTag> getChildren() {
    return null;
  }

  @Override
  public void generateHtml() {

  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}