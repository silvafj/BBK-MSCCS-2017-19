package adapter;

public class XpayToPayDAdapter implements PayD {
  public XpayToPayDAdapter(Xpay xpay) {
  }

  @Override
  public String getCustCardNo() {
    return null;
  }

  @Override
  public String getCardOwnerName() {
    return null;
  }

  @Override
  public String getCardExpMonthDate() {
    return null;
  }

  @Override
  public Integer getCVVNo() {
    return null;
  }

  @Override
  public Double getTotalAmount() {
    return null;
  }

  @Override
  public void setCustCardNo(String custCardNo) {

  }

  @Override
  public void setCardOwnerName(String cardOwnerName) {

  }

  @Override
  public void setCardExpMonthDate(String cardExpMonthDate) {

  }

  @Override
  public void setCVVNo(Integer cVVNo) {

  }

  @Override
  public void setTotalAmount(Double totalAmount) {

  }
}
