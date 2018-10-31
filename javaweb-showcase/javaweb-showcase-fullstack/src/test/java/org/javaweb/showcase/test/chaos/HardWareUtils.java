package org.javaweb.showcase.test.chaos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

public class HardWareUtils {

  public static void main(String[] args) {
    try {
      //NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

      printNetworkInterface();

      printInetAddress();

      printInterfaceAddress();

      printMainboardSN();

      getDeviceAllMacAddr();

      System.out.println("CPU  SN:" + HardWareUtils.getCPUSerial());
      System.out.println("主板  SN:" + HardWareUtils.getMotherboardSN());
      System.out.println("C盘   SN:" + HardWareUtils.getHardDiskSN("c"));
      System.out.println("MAC  SN:" + HardWareUtils.getMac());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static native void initJniService();

  private static native String getAllMacAddr();//获取设备 MAC 地址

  public static void printNetworkInterface() throws Exception {
    System.out.println("\n===============NetworkInterface===============\n");

    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();

    while (nis.hasMoreElements()) {
      NetworkInterface ni = nis.nextElement();

      byte[] mac = ni.getHardwareAddress();

      if (mac == null || mac.length <= 0) {
        continue;
      }

      System.out.println(ni.getDisplayName() + ":" + ni.isLoopback() + ":" + ni.isPointToPoint() + ":" + ni.isUp() + ":" + ni.isVirtual());
      System.out.println(getMAC(mac) + "\n");
    }
  }

  public static void printInetAddress() throws Exception {
    System.out.println("\n===============InetAddress===============\n");

    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();

    while (nis.hasMoreElements()) {
      NetworkInterface ni = nis.nextElement();

      Enumeration<InetAddress> ias = ni.getInetAddresses();
      while (ias.hasMoreElements()) {
        InetAddress ia = ias.nextElement();

        System.out.println(ia.getHostName() + "\n" + getMAC(ia.getAddress()) + "\n");
      }
    }
  }

  public static void printInterfaceAddress() throws Exception {
    System.out.println("\n===============InterfaceAddress===============\n");

    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();

    while (nis.hasMoreElements()) {
      NetworkInterface ni = nis.nextElement();

      List<InterfaceAddress> ifas = ni.getInterfaceAddresses();

      for (InterfaceAddress ifa : ifas) {
        InetAddress address = ifa.getAddress();
        System.out.println(address.getHostName() + "\n" + getMAC(address.getAddress()) + "\n");
      }
    }
  }

  public static void printMainboardSN() throws Exception {
    System.out.println(UUID.fromString(UUID.randomUUID().toString()).toString());
  }

  public static String getDeviceAllMacAddr() {
    try {
      System.load("/prism_dc/jni_service.so");
      //initJniService();
    } catch (Throwable e) {
      e.printStackTrace();
      System.out.println("load jni_service.so and initJniService error");
      return "";
    }
    try {
      return getAllMacAddr();
    } catch (Throwable e) {
      e.printStackTrace();
      System.out.println("getAllMacAddr error");
      return "";
    }
  }

  public static String getMAC(byte[] mac) {
    //下面代码是把mac地址拼装成String
    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < mac.length; i++) {
      if (i != 0) {
        sb.append("-");
      }
      //mac[i] & 0xFF 是为了把byte转化为正整数
      String s = Integer.toHexString(mac[i] & 0xFF);
      sb.append(s.length() == 1 ? 0 + s : s);
    }

    return sb.toString().toUpperCase();
  }

  /**
   * 获取主板序列号
   * 
   * @return
   */
  public static String getMotherboardSN() {
    String result = "";
    try {
      File file = File.createTempFile("realhowto", ".vbs");
      file.deleteOnExit();
      FileWriter fw = new java.io.FileWriter(file);

      String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n" + "Set colItems = objWMIService.ExecQuery _ \n"
          + "   (\"Select * from Win32_BaseBoard\") \n" + "For Each objItem in colItems \n" + "    Wscript.Echo objItem.SerialNumber \n"
          + "    exit for  ' do the first cpu only! \n" + "Next \n";

      fw.write(vbs);
      fw.close();
      Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
      BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line;
      while ((line = input.readLine()) != null) {
        result += line;
      }
      input.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result.trim();
  }

  /**
   * 获取硬盘序列号
   * 
   * @param drive
   *          盘符
   * @return
   */
  public static String getHardDiskSN(String drive) {
    String result = "";
    try {
      File file = File.createTempFile("realhowto", ".vbs");
      file.deleteOnExit();
      FileWriter fw = new java.io.FileWriter(file);

      String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n" + "Set colDrives = objFSO.Drives\n"
          + "Set objDrive = colDrives.item(\"" + drive + "\")\n" + "Wscript.Echo objDrive.SerialNumber"; // see note
      fw.write(vbs);
      fw.close();
      Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
      BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line;
      while ((line = input.readLine()) != null) {
        result += line;
      }
      input.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result.trim();
  }

  /**
   * 获取CPU序列号
   * 
   * @return
   */
  public static String getCPUSerial() {
    String result = "";
    try {
      File file = File.createTempFile("tmp", ".vbs");
      file.deleteOnExit();
      FileWriter fw = new java.io.FileWriter(file);
      String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n" + "Set colItems = objWMIService.ExecQuery _ \n"
          + "   (\"Select * from Win32_Processor\") \n" + "For Each objItem in colItems \n" + "    Wscript.Echo objItem.ProcessorId \n"
          + "    exit for  ' do the first cpu only! \n" + "Next \n";

      // + "    exit for  \r\n" + "Next";
      fw.write(vbs);
      fw.close();
      Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
      BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line;
      while ((line = input.readLine()) != null) {
        result += line;
      }
      input.close();
      file.delete();
    } catch (Exception e) {
      e.fillInStackTrace();
    }
    if (result.trim().length() < 1 || result == null) {
      result = "无CPU_ID被读取";
    }
    return result.trim();
  }

  /**
   * 获取MAC地址
   */
  public static String getMac() {
    String result = "";
    try {

      Process process = Runtime.getRuntime().exec("ipconfig /all");

      InputStreamReader ir = new InputStreamReader(process.getInputStream());

      LineNumberReader input = new LineNumberReader(ir);

      String line;

      while ((line = input.readLine()) != null) {
        if (line.indexOf("Physical Address") > 0) {

          String MACAddr = line.substring(line.indexOf("-") - 2);

          result = MACAddr;

        }
      }

    } catch (java.io.IOException e) {

      System.err.println("IOException " + e.getMessage());

    }
    return result;
  }

}
