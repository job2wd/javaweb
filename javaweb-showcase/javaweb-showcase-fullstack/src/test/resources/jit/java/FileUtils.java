/*
 * @(#) com.focus.main.common.dao#FileUtil.java
 * Copyright 2009 FOCUS Information Technologies Co.,Ltd. All rights reserved.
 * JIT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.FOCUS.com.cn
 * mailto:wangwdxa@163.com
 * v1.0 2009-6-1
 */
package com.bop.petbook.core.util.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;

/**
 * 文件操作类
 * 
 * @author weidong_wang
 * @date 2008-10-23
 *       <p>
 *       <li>change info:
 *       <table border="1" width="80%">
 *       <tr style="background-color: D3D1DF">
 *       <td width="20%">Author</td>
 *       <td width="20%">Date</td>
 *       <td width="60%">Change Content</td>
 *       </tr>
 *       <tr>
 *       <td>weidong_wang</td>
 *       <td>2009-04-28</td>
 *       <td>getFile();writeFile();readFile()</td>
 *       </tr>
 *       </table>
 * 
 * @version v1.0
 * @see
 * @since 1.0
 */
public final class FileUtils {

  /**
   * 文件分隔符 File.separator
   * 
   * @since 1.0
   */
  private static final String sp = File.separator;
  /**
   * 获取目录下所有文件及文件夹对象或名称
   * 
   * @since 1.0
   */
  public static final int LIST_ALL = 0;
  /**
   * 获取目录下所有文件对象或名称
   * 
   * @since 1.0
   */
  public static final int LIST_FILES = 1;
  /**
   * 获取目录下所有文件夹对象或名称
   * 
   * @since 1.0
   */
  public static final int LIST_FOLDERS = 2;

  /**
   * 读取文件缓冲大小，1024 长度byte
   * <p>
   * byte[] b = new byte[BUFFER];
   */
  private static final int BUFFER = 1024;

  /**
   * 用于记录日志信息或抛出异常时的应用程序类级别的位置
   * 
   * @see FileUtil.class.getName()
   * @since 1.0
   */
  private static final String positionClass = FileUtils.class.getName();

  /**
   * 通过路径（文件或目录）获取File对象.
   * 若参数path为null或为空，则抛出java.lang.IllegalArgumentException异常
   * 
   * @param path
   *          文件或目录路径
   * @return File对象
   * 
   * @see java.io.File#File(String)
   * @see com.focus.main.util.common.CommonUtils#checkEmpty(String)
   * @since 1.0
   */
  public static File getFile(String path) {
    if (StringUtils.isBlank(path)) {
      throw new IllegalArgumentException("The file path is null or blank");
    }
    return new File(path);
  }

  /**
   * 获取指定目录下文件夹名或文件名
   * 
   * @param directory
   *          指定获取目录
   * @param listFlag
   *          指定获取标识，用来标识获取的是所有目录名称，还是所有文件名称，或是目录和文件名称
   *          <p>
   *          可选项：
   *          <li>FileUtil.LIST_ALL
   *          <li>FileUtil.LIST_FILES
   *          <li>FileUtil.LIST_FOLDERS
   * @return 目录或文件名称集合。若传入参数listFlag为非法值，则返回全部目录和文件名称集合
   * 
   * @see #getFilesList(String, int)
   * @see java.io.File#getName()
   * @since 1.0
   */
  public static String[] getNamesList(String directory, int listFlag) {
    File[] files = getFilesList(directory, listFlag);
    if (files == null) {
      return null;
    }
    int filesLen = files.length;
    String[] fns = new String[filesLen];
    File file = null;
    for (int i = 0; i < filesLen; i++) {
      file = files[i];
      if (file != null) {
        fns[i] = file.getName();
      }
    }
    return fns;
  }

  /**
   * 获取指定目录下文件或文件夹File对象集合
   * 
   * @param directory
   *          指定获取目录
   * @param listFlag
   *          指定获取标识，用来标识获取的是所有目录对象，还是所有文件对象，或是目录和文件对象集合
   *          <p>
   *          可选项：
   *          <li>FileUtil.LIST_ALL
   *          <li>FileUtil.LIST_FILES
   *          <li>FileUtil.LIST_FOLDERS
   * @return File对象集合。若传入参数listFlag为非法值，则返回全部目录和文件File对象集合
   * 
   * @see #getFile(String)
   * @see java.io.File#isDirectory()
   * @see java.io.File#isFile()
   * @see java.io.File#listFiles()
   * @since 1.0
   */
  public static File[] getFilesList(String directory, int listFlag) {
    File file = getFile(directory);
    if (file == null || !file.isDirectory()) {
      return null;
    }
    File[] files = file.listFiles();
    if (files == null) {
      return null;
    }
    File[] reFiles = new File[files.length];
    File tf = null;
    int filesLen = files.length;
    for (int i = 0; i < filesLen; i++) {
      tf = null;
      if (listFlag == LIST_FILES) {// files
        if (files[i].isFile()) {
          tf = files[i];
        }
      } else if (listFlag == LIST_FOLDERS) {// folders
        if (files[i].isDirectory()) {
          tf = files[i];
        }
      } else {// all
        tf = files[i];
      }
      if (tf != null) {
        reFiles[i] = tf;
      }
    }
    return reFiles;
  }

  /**
   * 读取由directory和fileName指定路径下的文件
   * 
   * @param directory
   *          文件所在目录
   * @param fileName
   *          文件名称
   *          <p>
   *          若文件名称为null或为空，则以directory作为文件全路径读取文件内容
   * @return 文件内容字节数据 若文件不存在或指定路径不是一个文件，则返回null
   * @throws IOException
   *           读取文件出现异常
   * @throws FileNotFoundException
   *           读取文件出现异常
   * 
   * @see #readFile(String)
   * @since 1.0
   */
  public static byte[] readFile(String directory, String fileName) throws FileNotFoundException, IOException {
    return readFile(getFilePath(directory, fileName));
  }

  /**
   * 读取指定路径下文件
   * 
   * @param filePath
   *          文件全路径（包含文件目录和文件名的路径）
   * @return 文件内容字节数据 若文件不存在或指定路径不是一个文件，则返回null
   * @throws IOException
   *           读取文件出现异常
   * @throws FileNotFoundException
   *           读取文件出现异常
   * 
   * @see #getFile(String)
   * @see #readFile(File)
   * @since 1.0
   */
  public static byte[] readFile(String filePath) throws FileNotFoundException, IOException {
    return readFile(getFile(filePath));
  }

  /**
   * 读取由参数file指定的文件内容
   * <p>
   * 若文件不存在或指定路径不是一个文件，则返回null
   * 
   * @param file
   *          欲读取内容的文件file对象
   *          <p>
   *          若参数file为null则抛出java.lang.IllegalArgumentException异常
   * @return 文件内容的字节数据,
   *         <p>
   *         若文件不存在或指定路径不是一个文件，则返回null
   * @throws IOException
   *           ,FileNotFoundException 读取文件出现异常
   * 
   * @see java.io.File#isFile()
   * @see java.io.FileInputStream#FileInputStream(File)
   * @see java.io.FileInputStream#read(byte[])
   * @since 1.0
   */
  protected static byte[] readFile(File file) throws IOException, FileNotFoundException {
    if (file == null) {
      throw new IllegalArgumentException("The file is null");
    }
    if (!file.isFile() || !file.exists()) {
      return null;
    }
    // if(!file.canRead()){
    // return null;
    // }
    byte[] fileData = null;
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(file);
      fileData = new byte[fis.available()];
      fis.read(fileData);
    } catch (FileNotFoundException e) {
      throw e;
    } catch (IOException e) {
      throw e;
    } finally {
      free(fis, null);
    }
    return fileData;
  }

  /**
   * 
   * @param path
   * @return
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static byte[] readFile2ByteArray(String path) throws FileNotFoundException, IOException {
    return readFile2ByteArray(getFile(path));
  }

  /**
   * 
   * @param file
   * @return
   * @throws FileNotFoundException
   * @throws IOException
   * 
   * @see #BUFFER
   * @since 1.0
   */
  public static byte[] readFile2ByteArray(File file) throws FileNotFoundException, IOException {
    if (file == null) {
      throw new IllegalArgumentException("The file is null");
    }
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    byte[] b = new byte[BUFFER];
    byte[] data = null;
    int off = 0;
    int len = 0;
    FileInputStream fin = null;
    try {
      fin = new FileInputStream(file);
      while ((len = readStream2ByteArray(fin, b, off, BUFFER)) > 0) {
        bout.write(b, off, len);
        off += len;
      }
      data = bout.toByteArray();
    } catch (FileNotFoundException e) {
      throw e;
    } catch (IOException e) {
      throw e;
    } finally {
      free(fin, null);
      free(null, bout);
    }
    return data;
  }

  /**
   * 
   * @param path
   * @param b
   * @param off
   * @param len
   * @return
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static int readFile2ByteArray(String path, byte[] b, int off, int len) throws FileNotFoundException, IOException {
    return readFile2ByteArray(getFile(path), b, off, len);
  }

  /**
   * 
   * @param file
   * @param b
   * @param off
   * @param len
   * @return
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static int readFile2ByteArray(File file, byte[] b, int off, int len) throws FileNotFoundException, IOException {
    if (file == null) {
      throw new IllegalArgumentException("The file is null");
    }
    FileInputStream fin = null;
    try {
      fin = new FileInputStream(file);
      return readStream2ByteArray(fin, b, off, len);
    } catch (FileNotFoundException e) {
      throw e;
    } catch (IOException e) {
      throw e;
    } finally {
      free(fin, null);
    }
  }

  /**
   * 
   * @param in
   * @param b
   * @param off
   * @param len
   * @return
   * @throws IOException
   */
  public static int readStream2ByteArray(InputStream in, byte[] b, int off, int len) throws IOException {
    if (in == null) {
      throw new IllegalArgumentException("The input stream is null");
    }
    if (b == null) {
      throw new IllegalArgumentException("The byte array is null");
    }
    if (off < 0) {
      throw new IllegalArgumentException("off can't less than zero");
    }
    if (len <= 0) {
      throw new IllegalArgumentException("len can't less than or equal zero");
    }
    return in.read(b, off, len);
  }

  /**
   * 读取到Byte Array.
   * 
   * @param in
   * @return
   * @throws IOException
   */
  public static byte[] readStream2ByteArray(InputStream in) throws IOException {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    byte[] buf = new byte[BUFFER];
    int len = in.read(buf);
    while (len > 0) {
      bout.write(buf, 0, len);
      len = in.read(buf);
    }
    return bout.toByteArray();
  }

  /**
   * 将fileDdata数据写入directory和fileName指定的文件，并根据传入的参数确定使用何种方式写入
   * <p>
   * &nbsp;&nbsp;&nbsp;&nbsp;如：文件不存在时是否创建；写入文件时是否从文件末尾开始写起，而不是以覆盖的形式写文件
   * 只对directory指定的目录存在的文件进行更新或创建文件;若fileName为空且指定的directory的上级目录为一目录且存在,
   * 则将创建或修改以目录directory最后一名称命名的文件
   * （如：若directory为C:\\Temp\\test或C:\\Temp\\test\\,且C:\\Temp存在,则创建文件test;
   * 若当前目录下test文件存在,则修改该文件）;若fileName不为空,且directory指定的目录存在,则创建或修改文件fileName
   * <p>
   * 注意：若文件已经存在，则不再创建文件，而是在当前已经存在的文件中写入内容
   * 
   * @param fileData
   *          文件字节数据
   * @param directory
   *          欲写入的文件所在目录
   * @param fileName
   *          欲写入的文件名称
   * @param create
   *          若文件所在目录不存在时是否创建. true：创建；false：不创建
   * @param append
   *          是否以追加的形式写文件，即是否将内容写到原有内容末尾，而不是覆盖以前内容 true：追加；false：不追加
   * @return 写入文件成功标识。仅当写文件成功时返回true
   * @throws java.io.IOException
   *           ,FileNotFoundException 写文件出现异常
   * 
   * @see #writeFile(byte[], String, boolean, boolean)
   * @since 1.0
   */
  public synchronized static boolean writeFile(byte[] fileData, String directory, String fileName, boolean create, boolean append)
      throws IOException, FileNotFoundException {
    if (create) {
      if (!createDirectory(directory)) {
        return false;
      }
    }
    return writeFile(fileData, getFilePath(directory, fileName), false, append);
  }

  /**
   * 将fileDdata数据写入fullPath指定文件，并根据传入的参数确定使用何种方式写入
   * <p>
   * 如：文件不存在时是否创建；写入文件时是否从文件末尾开始写起，而不是以覆盖的形式写文件
   * 注意：若文件已经存在，则不再创建文件，而是在当前已经存在的文件中写入内容
   * 
   * @param fileData
   *          文件字节数据
   * @param filePath
   *          文件目录路径（含有文件名的全路径）
   * @param create
   *          若文件所在目录不存在，是否创建。true：创建；false：不创建
   * @param append
   *          是否将写入内容追加到文件末尾。true：追加；false：不追加
   * @return 写入文件成功标识。仅当写文件成功时返回true;创建目录或目录不存在但不要求创建时都将返回false
   * @throws java.io.IOException
   *           ,FileNotFoundException 写文件出现异常
   * 
   * @see #getFile(String)
   * @see #createDirectory(File,String)
   * @see #writeFile(File, byte[], boolean)
   * @since 1.0
   */
  public synchronized static boolean writeFile(byte[] fileData, String filePath, boolean create, boolean append) throws IOException,
      FileNotFoundException {
    File file = getFile(filePath);
    if (create) {
      if (!createDirectory(file)) {// create directory
        return false;
      }
    }
    return writeFile(file, fileData, append);
  }

  /**
   * 
   * @param path
   * @param data
   * @param append
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void writeByteArray2File(String path, byte[] data, boolean append) throws FileNotFoundException, IOException {
    writeByteArray2File(getFile(path), data, append);
  }

  /**
   * 
   * @param file
   * @param data
   * @param append
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void writeByteArray2File(File file, byte[] data, boolean append) throws FileNotFoundException, IOException {
    if (file == null) {
      throw new IllegalArgumentException("The file is null");
    }
    FileOutputStream fout = null;
    try {
      fout = new FileOutputStream(file, append);
      writeByteArray2Stream(fout, data);
      fout.flush();
    } catch (FileNotFoundException e) {
      throw e;
    } catch (IOException e) {
      throw e;
    } finally {
      free(null, fout);
    }
  }

  /**
   * 
   * @param path
   * @param data
   * @param off
   * @param len
   * @param append
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void writeByteArray2File(String path, byte[] data, int off, int len, boolean append) throws FileNotFoundException,
      IOException {
    writeByteArray2File(getFile(path), data, off, len, append);
  }

  /**
   * 
   * @param file
   * @param data
   * @param off
   * @param len
   * @param append
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void writeByteArray2File(File file, byte[] data, int off, int len, boolean append) throws FileNotFoundException,
      IOException {
    if (file == null) {
      throw new IllegalArgumentException("The file is null");
    }
    FileOutputStream fout = null;
    try {
      fout = new FileOutputStream(file, append);
      writeByteArry2Stream(fout, data, off, len);
      fout.flush();
    } catch (FileNotFoundException e) {
      throw e;
    } catch (IOException e) {
      throw e;
    } finally {
      free(null, fout);
    }
  }

  /**
   * 
   * @param out
   * @param b
   * @param off
   * @param len
   * @throws IOException
   */
  public static void writeByteArry2Stream(OutputStream out, byte[] b, int off, int len) throws IOException {
    if (out == null) {
      throw new IllegalArgumentException("The output stream is null");
    }
    if (b == null) {
      throw new IllegalArgumentException("The byte array is null");
    }
    if (off < 0) {
      throw new IllegalArgumentException("off can't less than zero");
    }
    if (len <= 0) {
      throw new IllegalArgumentException("len can't less than or equal zero");
    }
    out.write(b, off, len);
  }

  /**
   * 
   * @param out
   * @param b
   * @throws IOException
   */
  public static void writeByteArray2Stream(OutputStream out, byte[] b) throws IOException {
    if (b == null) {
      throw new IllegalArgumentException("The byte array is null");
    }
    writeByteArry2Stream(out, b, 0, b.length);
  }

  /**
   * 创建指定目录
   * <p>
   * 注意：若目录已经存在，则不再创建目录，直接返回true
   * 
   * @param file
   *          欲创建目录的文件对象（注意：为目录File对象）
   *          <p>
   *          若参数file为null则抛出java.lang.NullPointerException异常
   * @return 创建是否成功标识 当且仅当创建目录成功时返回true
   * 
   * @see java.io.File#mkdirs()
   * @since 1.0
   */
  protected synchronized static boolean createDirectory(File file) {
    if (file == null) {
      throw new IllegalArgumentException("The file is null");
    }
    if (!file.exists()) {
      return file.mkdirs();// create directory
    }
    return true;
  }

  /**
   * 创建指定目录
   * <p>
   * 注意：若目录已经存在，则不再创建目录，直接返回true
   * 
   * @param directory
   *          欲创建的目录路径
   * @return 创建是否成功标识 当且仅当创建目录成功时返回true
   * 
   * @see #getFile(String)
   * @see #createDirectory(String)
   * @since 1.0
   */
  public synchronized static boolean createDirectory(String directory) {
    return createDirectory(getFile(directory));
  }

  /**
   * 将fileDdata数据写入file指定文件，并根据传入的参数确定使用何种方式写入
   * <p>
   * 如：写入文件时是否从文件末尾开始写起，而不是以覆盖的形式写文件
   * <p>
   * 注意：file所在目录必须存在，才能成功写入文件，否则，写文件失败
   * 
   * @param file
   *          要写入内容的文件对象
   * @return 写文件成功标识。当且仅当写文件成功时返回true
   * @throws java.io.IOException
   *           ,FileNotFoundException 写文件出现异常
   * 
   * @see java.io.FileOutputStream#FileOutputStream(File, boolean)
   * @see java.io.FileOutputStream#write(byte[])
   * @since 1.0
   */
  protected synchronized static boolean writeFile(File file, byte[] fileData, boolean append) throws IOException, FileNotFoundException {
    if (file == null) {
      throw new IllegalArgumentException("The file is null");
    }
    if (fileData == null) {
      throw new IllegalArgumentException("The file data is null");
    }
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(file, append);
      fos.write(fileData);
      fos.flush();
    } catch (FileNotFoundException e) {
      throw e;
    } catch (IOException e) {
      throw e;
    } finally {
      free(null, fos);
    }
    return true;
  }

  /**
   * 写对象到指定文件（对象序列化）
   * <p>
   * 
   * 只对directory指定的目录存在的文件进行更新或创建文件;若fileName为空且指定的directory的上级目录为一目录且存在,
   * 则将创建或修改以目录directory最后一名称命名的文件
   * （如：若directory为C:\\Temp\\test或C:\\Temp\\test\\,且C:\\Temp存在,则创建文件test;
   * 若当前目录下test文件存在,则修改该文件）;若fileName不为空,且directory指定的目录存在,则创建或修改文件fileName
   * <p>
   * 注意：若文件已经存在，则不再创建文件，而是在当前已经存在的文件中写入内容。当前文件将被新写入的内容替换
   * 
   * @param obj
   *          欲序列化的对象，必须是implements了java.io.Serializable的对象
   * @param directory
   *          欲序列化的对象文件所在目录
   * @param fileName
   *          欲序列化的对象文件名称
   * @param create
   *          在文件所在目录不存在时是否创建
   * @return 写文件是否成功。当且仅当写文件成功时返回true
   *         <p>
   *         当create为true且创建目录失败时返回false
   * @throws FileNotFoundException
   *           ,IOException 对象序列化出现异常
   * 
   * @see #writeObject2File(Object, String, boolean)
   * @since 1.0
   */
  public synchronized static boolean writeObject2File(Object obj, String directory, String fileName, boolean create)
      throws FileNotFoundException, IOException {
    return writeObject2File(obj, getFilePath(directory, fileName), create);
  }

  /**
   * 写对象到指定文件（对象序列化）
   * <p>
   * 注意：若文件已经存在，则不再创建文件，而是在当前已经存在的文件中写入内容。当前文件将被新写入的内容替换
   * 
   * @param obj
   *          欲序列化的对象，必须是implements了java.io.Serializable的对象
   * @param filePath
   *          欲序列化的文件全路径（包含文件名的路径）
   * @param create
   *          在文件所在目录不存在时是否创建
   * @return 写文件是否成功。当且仅当写文件成功时返回true
   *         <p>
   *         当create为true且创建目录失败时返回false
   * @throws FileNotFoundException
   *           ,IOException 对象序列化出现异常
   * 
   * @see #getFile(String)
   * @see #createDirectory(File, boolean)
   * @see #writeObject2File(Object, File)
   * @since 1.0
   */
  public synchronized static boolean writeObject2File(Object obj, String filePath, boolean create) throws FileNotFoundException,
      IOException {
    File file = getFile(filePath);
    if (create) {
      if (!createDirectory(file)) {
        return false;
      }
    }
    return writeObject2File(obj, file);
  }

  /**
   * 写对象到指定文件（对象序列化）
   * 
   * <p>
   * <code>if (!(obj instanceof java.io.Serializable)) {// 不能被序列化
             return false;
            }
         </code>
   * <p>
   * 注意：要写入对象的文件file所在目录必须存在，才可以成功写入文件，否则写文件失败。若文件已经存在，则将被新写入的文件替换
   * 
   * @param obj
   *          欲序列化的对象，必须是implements了java.io.Serializable的对象
   * @param file
   *          欲序列化的文件file对象
   * @return 当且仅当序列化文件成功时返回true。
   *         <p>
   *         当对象obj不可序列化（即未实现java.io.Serializable）时返回false
   * @throws FileNotFoundException
   *           ,IOException
   * 
   * @see java.io.FileOutputStream#FileOutputStream(File)
   * @see java.io.ObjectOutputStream#ObjectOutputStream(java.io.OutputStream)
   * @see java.io.ObjectOutputStream#writeObject(Object)
   * @since 1.0
   */
  protected synchronized static boolean writeObject2File(Object obj, File file) throws FileNotFoundException, IOException {
    if (obj == null) {
      throw new IllegalArgumentException("The serializable object is null");
    }
    if (file == null) {
      throw new IllegalArgumentException("The file is null");
    }
    if (!(obj instanceof java.io.Serializable)) {// 不能被序列化
      return false;
    }
    // if(!file.canWrite()){
    // return false;
    // }
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    try {
      fos = new FileOutputStream(file);
      oos = new ObjectOutputStream(fos);
      oos.writeObject(obj);
      oos.flush();
    } catch (FileNotFoundException e) {
      throw e;
    } catch (IOException e) {
      throw e;
    } finally {
      free(null, fos);
      free(null, oos);
    }
    return true;
  }

  /**
   * 从文件读取对象，必须是对象序列化的文件
   * 
   * @param directory
   *          文件所在目录
   * @param fileName
   *          文件名称。
   *          <p>
   *          若该参数为null或为空，则以directory作为文件全路径（包含文件名的路径）
   *          否则以directory＋fileName为文件全路径
   * @return 读取到的对象
   *         <p>
   *         指定的文件不存在或不为有效的文件，则返回null
   * @throws ClassNotFoundException
   *           需要读取的对象引用不在系统中存在
   * @throws FileNotFoundException
   *           ,IOException
   * 
   * @see #readObjFromFile(String)
   * @since 1.0
   */
  public static Object readObjFromFile(String directory, String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
    return readObjFromFile(getFilePath(directory, fileName));
  }

  /**
   * 从文件读取对象，必须是对象序列化的文件
   * 
   * @param filePath
   *          文件全路径（包含文件名的路径）
   * @return 读取到的对象
   *         <p>
   *         指定的文件不存在或不为有效的文件，则返回null
   * @throws ClassNotFoundException
   *           需要读取的对象引用不在系统中存在
   * @throws FileNotFoundException
   *           ,IOException
   * 
   * @see #getFile(String)
   * @see #readObjFromFile(File)
   * @since 1.0
   */
  public static Object readObjFromFile(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
    return readObjFromFile(getFile(filePath));
  }

  /**
   * 从文件读取对象，必须是对象序列化的文件
   * 
   * @param file
   *          欲读取的序列化后的文件file对象
   * @return 当且仅当读取对象成功时返回true
   *         <p>
   *         当file指定的文件不存在或不为有效的文件，则返回null
   * @throws FileNotFoundException
   *           ,IOException
   * @throws ClassNotFoundException
   *           需要读取的对象引用不在系统中存在
   * 
   * @see java.io.FileInputStream#FileInputStream(File)
   * @see java.io.ObjectInputStream#ObjectInputStream(java.io.InputStream)
   * @see java.io.ObjectInputStream#readObject()
   * @since 1.0
   */
  protected static Object readObjFromFile(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
    if (file == null) {
      throw new IllegalArgumentException("The file is null");
    }
    if (!file.isFile() || !file.exists()) {
      return null;
    }
    Object obj = null;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    try {
      fis = new FileInputStream(file);
      ois = new ObjectInputStream(fis);
      obj = ois.readObject();
    } catch (FileNotFoundException e) {
      throw e;
    } catch (ClassNotFoundException e) {
      throw e;
    } catch (IOException e) {
      throw e;
    } finally {
      free(fis, null);
      free(ois, null);
    }
    return obj;
  }

  /**
   * 创建新的空文件
   * <p>
   * 注意：若要创建的文件已经存在，则抛出IOException
   * 
   * @param directory
   *          欲创建文件的上级目录
   * @param fileName
   *          文件名称
   * @param createDir
   *          若要创建文件所在的目录不存在，是否创建目录
   * @return 当且仅当创建新文件成功时返回true
   *         <p>
   *         若创建目录失败，则返回false；若要创建的文件不存在，但创建未成功，也有可能返回false
   * @throws IOException
   *           创建文件出现错误或若要创建的文件已经存在，则抛出IOException
   * 
   * @see #createTempFile(String, boolean)
   * @since 1.0
   */
  public synchronized static boolean createTempFile(String directory, String fileName, boolean createDir) throws IOException {
    return createTempFile(getFilePath(directory, fileName), createDir);
  }

  /**
   * 创建新的空文件 注意：若要创建的文件已经存在，则抛出IOException
   * 
   * @param filePath
   *          文件全路径（包含文件名）
   * @param createDir
   *          若要创建文件所在的目录不存在，是否创建目录
   * @return 当且仅当创建新文件成功时返回true
   *         <p>
   *         若创建目录失败，则返回false；若要创建的文件不存在，但创建未成功，也有可能返回false
   * @throws IOException
   *           创建文件出现错误或若要创建的文件已经存在，则抛出IOException
   * 
   * @see #getFile(String)
   * @see #createDirectory(File, boolean)
   * @see #createTempFile(File)
   * @since 1.0
   */
  public synchronized static boolean createTempFile(String filePath, boolean createDir) throws IOException {
    File file = getFile(filePath);
    if (createDir) {
      if (!createDirectory(file)) {
        return false;
      }
    }
    return createTempFile(file);
  }

  /**
   * 创建新的空文件 注意：若要创建的文件已经存在，则抛出IOException
   * 
   * @param file
   *          空文件对象
   * @return 当且仅当创建新文件成功时返回true
   *         <p>
   *         若创建目录失败，则返回false；若要创建的文件不存在，但创建未成功，也有可能返回false
   * @throws IOException
   *           创建文件出现错误或若要创建的文件已经存在，则抛出IOException
   * 
   * @see java.io.File#exists()
   * @see java.io.File#createNewFile()
   * @since 1.0
   */
  protected synchronized static boolean createTempFile(File file) throws IOException {
    if (file == null) {
      throw new IllegalArgumentException("The file is null");
    }
    if (file.exists()) {
      throw new IOException("The file has exists as " + file.getAbsolutePath());
    }
    return file.createNewFile();
  }

  /**
   * 删除单个文件，若文件不存在，则不做删除存在直接返回true 若传入的为一存在的目录，则不做删除操作，返回false
   * 
   * @param directory
   *          欲删除文件所在目录
   * @param fileName
   *          欲删除文件名称，若该参数为null或为空，则将以directory作为文件全路径进行删除
   * @return 欲删除的文件不存在或成功删除时返回true
   * 
   * @see #getFilePath(String, String)
   * @see #deleteFile(String)
   * @since 1.0
   */
  public synchronized static boolean deleteFile(String directory, String fileName) {
    return deleteFile(getFilePath(directory, fileName));
  }

  /**
   * 删除单个文件，若文件不存在，则不做删除存在直接返回true 若传入的为一存在的目录，则不做删除操作，返回false
   * 
   * @param filePath
   *          文件全路径（包含文件名称的路径）
   * @return 欲删除的文件不存在或成功删除时返回true
   * 
   * @see #getFile(String)
   * @see #deleteFile(File)
   * @since 1.0
   */
  public synchronized static boolean deleteFile(String filePath) {
    return deleteFile(getFile(filePath));
  }

  /**
   * 删除单个文件，若文件不存在，则不做删除存在直接返回true
   * <p>
   * 若传入参数为null，则抛出java.lang.IllegalArgumentException异常
   * 若传入的为一存在的目录，则不做删除操作，返回false
   * 
   * @param file
   *          欲删除的文件对象
   * @return 欲删除的文件不存在或成功删除时返回true
   * 
   * @see java.io.File#exists()
   * @see java.io.File#isFile()
   * @see java.io.File#delete()
   * @since 1.0
   */
  protected synchronized static boolean deleteFile(File file) {
    if (file == null) {
      throw new IllegalArgumentException("The file is null");
    }
    if (!file.exists()) {// 不存在，则无需做删除操作，直接返回true
      return true;
    }
    if (file.isFile()) {// 存在且是文件，则删除之
      return file.delete();
    } else {// 存在，但不是文件，或是目录
      return false;
    }
  }

  /**
   * 删除多个目录和（或）文件
   * 
   * @param files
   *          子目录列表，含有目录和（或）文件的列表
   * @param delDir
   *          是否删除传入的列表中目录
   * @param delFile
   *          是否删除传入的列表中文件
   * @return 删除标识，当且仅当成功删除时返回true
   * 
   * @see java.io.File#isDirectory()
   * @see #deleteDirectory(File)
   * @see #deleteFile(File)
   * @since 1.0
   */
  protected synchronized static boolean deleteFiles(File[] files, boolean delDir, boolean delFile) {
    if (files == null) {
      throw new IllegalArgumentException("The files is null");
    }
    boolean allSucc = true;
    int len = files.length;
    for (int i = 0; i < len; i++) {
      if (files[i].isDirectory() && delDir) {// folder
        allSucc = allSucc && deleteDirectory(files[i]);
      } else if (delFile) {// file
        allSucc = allSucc && deleteFile(files[i]);
      }
    }
    return allSucc;
  }

  /**
   * 删除指定目录
   * <p>
   * 若删除存在的单独文件，将返回false； 若删除不存在的文件或目录，将返回true； 若删除存在的目录，将返回true
   * 注意：该方法只删除目录，不删除单独文件； 若删除的目录存在，则将其和子文件及子目录一并删除
   * 
   * @param directory
   *          欲删除的目录路径
   * @return 删除成功标识
   * 
   * @see #getFile(String)
   * @see #deleteDirectory(File)
   * @since 1.0
   */
  public synchronized static boolean deleteDirectory(String directory) {
    return deleteDirectory(getFile(directory));
  }

  /**
   * 删除指定目录
   * <p>
   * 若删除存在的单独文件，将返回false； 若删除不存在的文件或目录，将返回true； 若成功删除存在的目录，将返回true
   * 注意：该方法只删除目录，不删除单独文件； 若删除的目录存在，则将其和子文件及子目录一并删除
   * 
   * @param dir
   *          欲删除的目录对象
   * @return 删除成功标识
   * 
   * @see java.io.File#exists()
   * @see java.io.File#isDirectory()
   * @see java.io.File#delete()
   * @see #deleteFiles(File[], boolean, boolean)
   * @since 1.0
   */
  protected synchronized static boolean deleteDirectory(File dir) {
    if (dir == null) {
      throw new IllegalArgumentException("The file directory is null");
    }
    boolean allSucc = true;
    if (!dir.exists()) {// 目录不存在，则无需做删除操作，直接返回true
      return allSucc;
    }
    if (dir.isDirectory()) {
      allSucc = allSucc && deleteFiles(dir.listFiles(), true, true);// 删除子目录和文件
      allSucc = allSucc && dir.delete();// 删除当前目录
    } else {// 是文件
      allSucc = false;// 不做删除操作，因为该方法业务功能为删除的是目录
    }
    return allSucc;
  }

  /**
   * 重命名文件或目录
   * 
   * @param path
   *          欲重命名文件所在目录
   * @param newName
   *          文件或目录新名称
   * @return true：重命名成功；false：目录或文件不存在或重命名失败
   * 
   * @see #getFilePath(String, String)
   * @see #renameFile(String, String)
   * @since 1.0
   */
  public synchronized static boolean renameFileOrDir(String path, String newName) {
    return renameFileOrDir(getFile(path), newName);
  }

  /**
   * 重命名文件或目录
   * 
   * @param file
   *          欲重命名目录或文件对象
   * @param newName
   *          欲重命名文件新名称
   * @return true：重命名成功；false：目录或文件不存在或重命名失败
   * 
   * @see java.io.File#exists()
   * @see java.io.File#renameTo(File)
   * @see java.io.File#getParent()
   * @see #getFile(String)
   * @see #sp
   * @since 1.0
   */
  protected synchronized static boolean renameFileOrDir(File file, String newName) {
    if (file == null) {
      throw new IllegalArgumentException("The file is null");
    }
    if (StringUtils.isBlank(newName)) {
      throw new IllegalArgumentException("The file or directory new name is null");
    }
    if (!file.exists()) {
      return false;
    }
    return file.renameTo(getFile(file.getParent() + sp + newName));
  }

  /**
   * 拷贝文件到一指定目录下
   * 
   * @param fromPath
   *          拷贝文件来源路径
   * @param toPath
   *          拷贝文件结果目录路径
   * @return 仅当拷贝成功时返回true；
   *         <p>
   *         若fromPath指定路径不为一存在的文件，则返回false
   * @throws IOException
   *           toPath指定的目录不存在或执行拷贝时读写文件出现异常
   * @throws FileNotFoundException
   *           拷贝时读写文件出现异常
   * 
   * @see #getFile(String)
   * @see #copyFile(File, File)
   * @see java.io.File#isDirectory()
   * @see java.io.File#getName()
   * @since 1.0
   */
  public static boolean copyFile(String fromPath, String toPath) throws FileNotFoundException, IOException {
    if (!getFile(toPath).isDirectory()) {
      throw new IOException("The toPath is not exists as a directory");
    }
    File fromFile = getFile(fromPath);
    return copyFile(fromFile, getFile(toPath + sp + fromFile.getName()));
  }

  /**
   * 拷贝文件到一指定目录下
   * <p>
   * 若fromPath指定路径不为一存在的文件，则返回false
   * 
   * @param fromFile
   *          文件来源
   * @param toFile
   *          文件目标
   * @return 仅当拷贝成功时返回true；
   * @throws FileNotFoundException
   *           执行拷贝时读写文件出现异常
   * @throws IOException
   *           执行拷贝时读写文件出现异常
   * 
   * @see java.io.File#isFile()
   * @see #writeFile(File, byte[], boolean)
   * @see #readFile(File)
   * @since 1.0
   */
  protected static boolean copyFile(File fromFile, File toFile) throws FileNotFoundException, IOException {
    if (fromFile == null) {
      throw new IllegalArgumentException("The fromFile is null");
    }
    if (toFile == null) {
      throw new IllegalArgumentException("The tofile is null");
    }
    if (!fromFile.isFile()) {
      return false;
    }
    return writeFile(toFile, readFile(fromFile), false);
  }

  /**
   * 拷贝某一目录到一指定目录下，包括其子目录及目录下所以文件
   * <p>
   * 若拷贝过程中某个对象拷贝出现错误，将终止拷贝或返回false
   * 
   * @param fromPath
   *          目录来源路径
   * @param toPath
   *          拷贝目录目标路径
   * @return 当且仅当拷贝操作全部成功时返回true
   * @throws FileNotFoundException
   *           拷贝目录过程出现读写文件异常
   * @throws IOException
   *           拷贝目录过程出现读写文件异常
   * 
   * @see #getFile(String)
   * @see #copyDirectory(File, File)
   * @since 1.0
   */
  public static boolean copyDirectory(String fromPath, String toPath) throws FileNotFoundException, IOException {
    return copyDirectory(getFile(fromPath), getFile(toPath));
  }

  /**
   * 拷贝某一目录到一指定目录下
   * <p>
   * 若拷贝过程中某个对象拷贝出现错误，将终止拷贝或返回false
   * 
   * @param fromFile
   *          目录来源路径对象
   * @param toFile
   *          拷贝目录目标路径对象
   * @return 当且仅当拷贝操作全部成功时返回true
   * @throws FileNotFoundException
   *           拷贝目录过程出现读写文件异常
   * @throws IOException
   *           拷贝目录过程出现读写文件异常
   * 
   * @see java.io.File#exists()
   * @see java.io.File#isFile()
   * @see java.io.File#isDirectory()
   * @see java.io.File#getAbsolutePath()
   * @see java.io.File#listFiles()
   * @see java.io.File#getName()
   * 
   * @see #getFile(String)
   * @see #copyChildFiles(File[], File)
   * @see #copyFile(File, File)
   * @since 1.0
   */
  protected static boolean copyDirectory(File fromFile, File toFile) throws FileNotFoundException, IOException {
    if (fromFile == null) {
      throw new IllegalArgumentException("The fromFile is null");
    }
    if (toFile == null) {
      throw new IllegalArgumentException("The tofile is null");
    }
    boolean res = false;
    if (!toFile.exists() || toFile.isFile()) {
      return res;
    }
    if (fromFile.isDirectory()) {// copy dir
      File childFile = getFile(toFile.getAbsolutePath() + sp + fromFile.getName());
      File[] childFiles = fromFile.listFiles();
      res = copyChildFiles(childFiles, childFile);
    } else if (fromFile.isFile()) {// copy file
      res = copyFile(fromFile, getFile(toFile.getAbsolutePath() + sp + fromFile.getName()));
    } else {
      res = false;
    }
    return res;
  }

  /**
   * 拷贝文件列表
   * 
   * @param childFiles
   *          文件或目录列表集合
   * @param toFile
   *          文件或目录拷贝目标路径对象
   * @return 当且仅当拷贝操作全部成功时返回true
   *         <p>
   *         childFiles或toFile 为null，将返回false
   * 
   * @throws FileNotFoundException
   *           拷贝目录过程出现读写文件异常
   * @throws IOException
   *           拷贝目录过程出现读写文件异常
   * 
   * @see java.io.File#getAbsolutePath()
   * @see java.io.File#getName()
   * 
   * @see #getFile(String)
   * @see #createDirectory(File)
   * @see #copyFile(File, File)
   * @see #copyChildFiles(File[], File)
   * @since 1.0
   */
  private static boolean copyChildFiles(File[] childFiles, File toFile) throws FileNotFoundException, IOException {
    File tmp = null;
    boolean res = true;
    if (childFiles == null) {
      return false;
    }
    if (toFile == null) {
      return false;
    }
    res = res && createDirectory(toFile);
    int len = childFiles.length;
    for (int i = 0; i < len; i++) {
      tmp = childFiles[i];
      res = res && createDirectory(toFile);
      if (tmp.isFile()) {
        res = res && copyFile(tmp, getFile(toFile.getAbsolutePath() + sp + tmp.getName()));
      } else {
        res = res && copyChildFiles(tmp.listFiles(), getFile(toFile.getAbsolutePath() + sp + tmp.getName()));
      }
    }
    return res;
  }

  /**
   * 剪切文件
   * 
   * @param fromPath
   * @param toPath
   * @param fileName
   * @return
   */
  public static boolean cutFile(String fromPath, String toPath, String fileName) {

    return true;
  }

  /**
   * 剪切指定目录下所有文件
   * 
   * @param fromPath
   * @param toPath
   * @return
   */
  public static boolean cutFiles(String fromPath, String toPath) {

    return true;
  }

  /**
   * 由文件目录和文件名称获取文件全路径（包含文件名的目录路径）
   * 
   * @param directory
   *          文件所在目录，不能为null，不能为空
   * @param fileName
   *          文件名称，当为null或为空时以directory作为文件全路径
   * @return 文件全路径
   * 
   * @since 1.0
   */
  private static String getFilePath(String directory, String fileName) {
    if (StringUtils.isBlank(directory)) {
      throw new IllegalArgumentException("The directory is null blank");
    }
    String filePath = "";
    if (fileName == null || fileName.equals("")) {
      filePath = directory;
    } else {
      filePath = directory + sp + fileName;
    }
    return filePath;
  }

  /**
   * 释放资源，关闭输入输出流
   * 
   * @param in
   *          输入流对象
   * @param out
   *          输出流对象
   * @throws IOException
   *           关闭流出现异常
   * @see java.io.InputStream#close()
   * @see java.io.OutputStream#close()
   * @since 1.0
   */
  private static void free(InputStream in, OutputStream out) throws IOException {
    if (in != null) {
      in.close();
    }
    if (out != null) {
      out.close();
    }
  }
}
