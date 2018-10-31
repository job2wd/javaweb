/**
 * $Id: CardBizSummaryInfo.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail mail2wd@163.com
 */
package org.javaweb.showcase.test.chaos;

/**
 * @author David
 * @version 1.0
 * @date 2011-9-14 下午04:53:27
 */
public class CardBizSummaryInfo {

  private String branchNo;
  private String branchName;

  private Double sale_number;
  private Double sale_amount;

  private Double sale_agent_bh;
  private Double sale_agent_cb;

  private Double sale_agt_dist_bh;

  private Double sale_ext_charge_bh;

  private Double sale_mem_number;
  private Double sale_mem_amount;
  private Double sale_mem_agent_bh;
  private Double sale_mem_agent_cb;
  private Double sale_mem_ext_bh;

  private Double buy_number;
  private Double buy_amount;

  private Double buy_agent_bh;

  private Double buy_pty_bh;
  private Double buy_pty_cb;

  private Double buy_mem_number;
  private Double buy_mem_amount;
  private Double buy_mem_agent_bh;

  private String generateBlank() {
    StringBuffer s = new StringBuffer(30);
    int len = getBranchNo().length() / 3;
    for (int i = 1; i < len; i++) {
      s.append("　");
    }
    return s.toString();
  }

  public String getBranchNo() {
    return branchNo;
  }

  public void setBranchNo(String branchNo) {
    this.branchNo = branchNo;
  }

  public String getBranchName() {
    return branchName;
  }

  public String getBranchNameWithTree() {
    return this.generateBlank() + branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public Double getSale_number() {
    return sale_number;
  }

  public void setSale_number(Double saleNumber) {
    sale_number = saleNumber;
  }

  public Double getSale_amount() {
    return sale_amount;
  }

  public void setSale_amount(Double saleAmount) {
    sale_amount = saleAmount;
  }

  public Double getSale_agent_bh() {
    return sale_agent_bh;
  }

  public void setSale_agent_bh(Double saleAgentBh) {
    sale_agent_bh = saleAgentBh;
  }

  public Double getSale_agent_cb() {
    return sale_agent_cb;
  }

  public void setSale_agent_cb(Double saleAgentCb) {
    sale_agent_cb = saleAgentCb;
  }

  public Double getSale_agt_dist_bh() {
    return sale_agt_dist_bh;
  }

  public void setSale_agt_dist_bh(Double saleAgtDistBh) {
    sale_agt_dist_bh = saleAgtDistBh;
  }

  public Double getSale_ext_charge_bh() {
    return sale_ext_charge_bh;
  }

  public void setSale_ext_charge_bh(Double saleExtChargeBh) {
    sale_ext_charge_bh = saleExtChargeBh;
  }

  public Double getSale_mem_number() {
    return sale_mem_number;
  }

  public void setSale_mem_number(Double saleMemNumber) {
    sale_mem_number = saleMemNumber;
  }

  public Double getSale_mem_amount() {
    return sale_mem_amount;
  }

  public void setSale_mem_amount(Double saleMemAmount) {
    sale_mem_amount = saleMemAmount;
  }

  public Double getSale_mem_agent_bh() {
    return sale_mem_agent_bh;
  }

  public void setSale_mem_agent_bh(Double saleMemAgentBh) {
    sale_mem_agent_bh = saleMemAgentBh;
  }

  public Double getSale_mem_agent_cb() {
    return sale_mem_agent_cb;
  }

  public void setSale_mem_agent_cb(Double saleMemAgentCb) {
    sale_mem_agent_cb = saleMemAgentCb;
  }

  public Double getSale_mem_ext_bh() {
    return sale_mem_ext_bh;
  }

  public void setSale_mem_ext_bh(Double saleMemExtBh) {
    sale_mem_ext_bh = saleMemExtBh;
  }

  public Double getBuy_number() {
    return buy_number;
  }

  public void setBuy_number(Double buyNumber) {
    buy_number = buyNumber;
  }

  public Double getBuy_amount() {
    return buy_amount;
  }

  public void setBuy_amount(Double buyAmount) {
    buy_amount = buyAmount;
  }

  public Double getBuy_agent_bh() {
    return buy_agent_bh;
  }

  public void setBuy_agent_bh(Double buyAgentBh) {
    buy_agent_bh = buyAgentBh;
  }

  public Double getBuy_pty_bh() {
    return buy_pty_bh;
  }

  public void setBuy_pty_bh(Double buyPtyBh) {
    buy_pty_bh = buyPtyBh;
  }

  public Double getBuy_pty_cb() {
    return buy_pty_cb;
  }

  public void setBuy_pty_cb(Double buyPtyCb) {
    buy_pty_cb = buyPtyCb;
  }

  public Double getBuy_mem_number() {
    return buy_mem_number;
  }

  public void setBuy_mem_number(Double buyMemNumber) {
    buy_mem_number = buyMemNumber;
  }

  public Double getBuy_mem_amount() {
    return buy_mem_amount;
  }

  public void setBuy_mem_amount(Double buyMemAmount) {
    buy_mem_amount = buyMemAmount;
  }

  public Double getBuy_mem_agent_bh() {
    return buy_mem_agent_bh;
  }

  public void setBuy_mem_agent_bh(Double buyMemAgentBh) {
    buy_mem_agent_bh = buyMemAgentBh;
  }

}
