/**
 * 
 */
package com.bop.petbook.core.util.num;

/**
 * 
 * @author wangwd
 * @date Nov 14, 2008
 */
public class NumberUtils {

  /**
   * 转换数字为大写形式
   * @param num
   * @return
   */
  public static String toCaption(double num) {
    String straNum[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
    String straBit[] = { "仟", "佰", "拾", "万", "仟", "佰", "拾", "亿", "仟", "佰",
        "拾", "万", "仟", "佰", "拾", "元", "角", "分" };
    String straTmp[] = new String[40];
    String straObjNum[] = new String[40];
    double dTmpQuotient = 0.0D;
    int naObjNum[] = new int[20];
    int nCounterI = 0;
    int nCounterJ = 0;
    int nCounterM = 0;
    int nObjLen = 0;
    double dDeno = 1000000000000000D;
    StringBuffer strbTmp = new StringBuffer("");
    String strNoCaption;
    if (num == 0.0D) {
      strNoCaption = "零元整";
    } else {
      num *= 100D;
      for (; dDeno >= 1.0D; dDeno /= 10D) {
        dTmpQuotient = num / dDeno;
        if (dTmpQuotient >= 1.0D)
          break;
      }

      for (nCounterI = 0; dDeno >= 1.0D; nCounterI++) {
        dTmpQuotient = num / dDeno;
        naObjNum[nCounterI] = (int) dTmpQuotient;
        num %= dDeno;
        dDeno /= 10D;
      }

      nObjLen = nCounterI;
      nCounterJ = nObjLen * 2 - 1;
      nCounterM = 17;
      for (nCounterI = nObjLen - 1; nCounterI >= 0; nCounterI--) {
        straTmp[nCounterJ] = straBit[nCounterM];
        nCounterJ--;
        straTmp[nCounterJ] = straNum[naObjNum[nCounterI]];
        nCounterJ--;
        nCounterM--;
      }

      nObjLen *= 2;
      nCounterJ = 0;
      nCounterM = 0;
      for (nCounterI = 0; nCounterI < nObjLen; nCounterI++) {
        if (straTmp[nCounterI].compareTo("零") == 0) {
          if (straTmp[nCounterI + 1] != null) {
            if (straTmp[nCounterI + 1].compareTo("万") != 0
                && straTmp[nCounterI + 1].compareTo("亿") != 0
                && straTmp[nCounterI + 1].compareTo("元") != 0) {
              straObjNum[nCounterJ] = straTmp[nCounterI];
              nCounterI++;
              nCounterJ++;
            } else {
              nCounterI++;
              straObjNum[nCounterJ] = straTmp[nCounterI];
              nCounterJ++;
            }
          }
        } else {
          straObjNum[nCounterJ] = straTmp[nCounterI];
          nCounterJ++;
        }
      }

      nCounterJ = 0;
      for (; straObjNum[nCounterJ] != null; nCounterJ++) {
        if (straObjNum[nCounterJ].compareTo("零") == 0) {
          if (straObjNum[nCounterJ + 1] != null
              && (straObjNum[nCounterJ + 1].compareTo("万") == 0
                  || straObjNum[nCounterJ + 1].compareTo("亿") == 0
                  || straObjNum[nCounterJ + 1].compareTo("元") == 0 || straObjNum[nCounterJ + 1]
                  .compareTo("零") == 0))
            straObjNum[nCounterJ] = "_";
        }
      }

      if (straObjNum[nCounterJ - 1].compareTo("零") == 0)
        straObjNum[nCounterJ - 1] = "_";
      nCounterJ = 0;
      nCounterI = 0;
      for (; straObjNum[nCounterJ] != null; nCounterJ++)
        if (straObjNum[nCounterJ].compareTo("_") != 0) {
          straTmp[nCounterI] = straObjNum[nCounterJ];
          nCounterI++;
        }

      nObjLen = nCounterI;
      for (nCounterI = 0; nCounterI < nObjLen; nCounterI++) {
        strbTmp.append(straTmp[nCounterI]);
        if (straTmp[nCounterI].compareTo("亿") == 0
            && straTmp[nCounterI + 1].compareTo("万") == 0)
          straTmp[nCounterI + 1] = "";
      }

      strNoCaption = strbTmp.append("整").toString();
    }
    return strNoCaption;
  }
}
