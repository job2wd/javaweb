/**
 * $Id: CrlTest.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.cert.crl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Wang WD
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class CrlTest {

  /**
   * 
   */
  public CrlTest() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @param args
   */
  public static void main(String[] args) throws FileNotFoundException, CertificateException, CRLException {

    FileInputStream fis = new FileInputStream("D:\\Jobs\\Certificates\\Developers Certs\\crl1.crl");
    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    X509CRL aCrl = (X509CRL) cf.generateCRL(fis);

    int i = 0;
    Set tSet = aCrl.getRevokedCertificates();
    Iterator tIterator = tSet.iterator();

    // System.out.println("tIterator   "+tIterator);

    while (tIterator.hasNext()) {
      X509CRLEntry tEntry = (X509CRLEntry) tIterator.next();
      String sn = tEntry.getSerialNumber().toString(16).toUpperCase();
      String issName = aCrl.getIssuerDN().toString();
      String time = new SimpleDateFormat("yyyyMMddHHmmss").format(tEntry.getRevocationDate());
      i++;
      System.out.println(sn);
      System.out.println(issName);
      System.out.println(time);
      System.out.println("***************************");
    }
  }
}