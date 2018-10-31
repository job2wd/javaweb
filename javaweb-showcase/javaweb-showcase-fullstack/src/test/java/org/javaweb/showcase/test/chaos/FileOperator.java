/**
 * 
 */
package org.javaweb.showcase.test.chaos;

import java.io.File;

/**
 * @author Administrator
 * 
 */
public class FileOperator {

	/**
	 * 
	 */
	public FileOperator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "C:\\Users\\Administrator\\Desktop\\help3.0\\help\\images";
		String suffix = "png";
		try {
			FileOperator.toLowerCaseFilesNameOfSameSuffix(path, suffix);
			System.out.println("to lower case the files of path: [" + path
					+ "] suffix: [." + suffix + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 将指定目录（包括其所有子目录）下规定了后缀的所有文件名称（包括后缀）全部转换为小写
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static void toLowerCaseFilesNameOfSameSuffix(String path,
			String suffix) throws Exception {
		File file = new File(path);
		File[] files = file.listFiles();
		if (files == null || files.length == 0) {
			return;
		}
		for (File f : files) {
			if (f.isFile()) {
				// file
				String name = f.getName().toLowerCase();
				if (name.endsWith(suffix.toLowerCase())) {
					f.renameTo(new File(f.getParent() + File.separator + name));
				}
			} else {
				// folder
				toLowerCaseFilesNameOfSameSuffix(f.getAbsolutePath(), suffix);
			}
		}

	}

}
