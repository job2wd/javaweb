package com.bop.petbook.core.util.string;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.bop.petbook.core.util.file.FileUtils;

/**
 * 字符串操作类
 * 
 * @author weidong_wang
 * @date 2008-10-23
 */
public final class StringUtils {

  /**
   * 消除字符串中的特殊字符：&，<，>，"，'，\n（换行）， 转而替换为：&amp;， &lt;， &gt;， &quot;， &apos;，
   * &#xD;（换行），
   * 
   * @param s
   * @return
   */
  public static String escapeSpecialCharacters(String s) {
    String s1 = s;
    int i = 0;
    if (s != null)
      i = s.length();
    int j = 0;
    boolean flag = false;
    for (; j < i; j++) {
      char c = s.charAt(j);
      if (c != '&' && c != '<' && c != '>' && c != '\'' && c != '"' && c != '\n')
        continue;
      flag = true;
      break;
    }

    if (flag) {
      StringBuffer stringbuffer = new StringBuffer();
      stringbuffer.append(s.substring(0, j));
      for (; j < i; j++) {
        char c1 = s.charAt(j);
        switch (c1) {
          case 38: // '&'
            stringbuffer.append("&amp;");
            break;

          case 60: // '<'
            stringbuffer.append("&lt;");
            break;

          case 62: // '>'
            stringbuffer.append("&gt;");
            break;

          case 34: // '"'
            stringbuffer.append("&quot;");
            break;

          case 39: // '\''
            stringbuffer.append("&apos;");
            break;

          case 10: // '\n'
            stringbuffer.append("&#xD;");
            break;

          default:
            stringbuffer.append(c1);
            break;
        }
      }

      s1 = stringbuffer.toString();
    }
    return s1;
  }

  /**
   * &lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
   * &#xD;&lt;ServicesConfig version=&quot;1.0&quot;&gt; &#xD;
   * &lt;verifyDSignServiceConfig&gt; &#xD; &lt;CSCARootCerts&gt; &#xD;
   * &lt;RootCert name=&quot;t001&quot;&gt; &#xD; &lt;name&gt;t001&lt;/name&gt;
   * &#xD; &lt;nationcode&gt;CN&lt;/nationcode&gt; &#xD;
   * &lt;dn&gt;CN=DemoCA,O=jit,C=cn&lt;/dn&gt; &#xD;
   * &lt;sn&gt;1cb6317feaa7466b&lt;/sn&gt; &#xD;
   * &lt;issuer&gt;CN=DemoCA,O=jit,C=cn&lt;/issuer&gt; &#xD;
   * &lt;srcfile&gt;20090514165818.cer&lt;/srcfile&gt; &#xD; &lt;/RootCert&gt;
   * &#xD; &lt;/CSCARootCerts&gt; &#xD; &lt;/verifyDSignServiceConfig&gt;
   * &#xD;&lt;/ServicesConfig&gt;
   * 
   * @param args
   */
  public static void main(String[] args) {
    // String str = "aaa & bbb < ccc > ddd \" eee ' fff \n";
    // String testStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
    // "<!--根元素-->\n" +
    // "<ServicesConfig version=\"1.0\">\n" +
    // "<CARootCert>\n" +
    // "<RootCert name=\"rootcert\">\n" +
    // "<name>rootcert</name>\n" +
    // "<dn>CN=DemoCA,O=JIT,C=CN</dn>\n" +
    // "<sn>11BD03009E9A971E</sn>\n" +
    // "<issuer>CN=DemoCA,O=JIT,C=CN</issuer>\n" +
    // "<srcfile>cert_11bd03009e9a971eFa2aS.cer</srcfile>\n" +
    // "</RootCert>\n" +
    // "</CARootCert>\n" +
    // "</ServicesConfig>";
    //
    // String res = StrUtil.escapeSpecialCharacters(testStr);
    // System.out.println(res);

    String filePath = "D:/MRTD/CssConfig.xml";
    try {
      byte[] b = FileUtils.readFile(filePath);
      String testStr = new String(b);
      System.out.println(testStr);
      System.out.println("=======================");
      String res = StringUtils.escapeSpecialCharacters(testStr);
      System.out.println(res);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
