/**
 * Copyright (C) 2015-2016 OurBeehive(http://ourbeehive.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Project Name: SericloudUtil
 * File Name: FileUtil.java
 * Package Name: seri.core.io.file
 * 
 * Date: May 2, 2016
 * Author: Sericloud
 * 
 */

package seri.core.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <code>FileUtil</code> is a class that define the basic file operation
 * @author Sericloud
 * @date May 2, 2016
 *
 */
public class FileUtil {
	
	/**
	 * create file with making directory automatically
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean createFile(File file) throws IOException {
		if (!file.exists()) {
			makeDir(file.getParentFile());
		}
		return file.createNewFile();
	}

	/**
	 * make directory
	 * @param dir
	 */
	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}
	

	/**
	 * read text file
	 * @param file
	 * @return file content
	 * @throws IOException
	 */
	public static String readTextFile(File file) throws IOException {
		return readTextFile(file.getAbsolutePath());
	}

	/**
	 * @param fileName
	 *            String The absolute path of a file
	 * @return String
	 * @throws IOException
	 */
	public static String readTextFile(String fileName) throws IOException {

		BufferedReader in = null;
		StringBuffer all = new StringBuffer();
		String line = null;
		String lineSep = System.getProperty("line.separator");

		try {
			in = new BufferedReader(new FileReader(fileName));
			line = in.readLine();

			while (line != null) {
				all.append(line + lineSep);
				line = in.readLine();
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		
		return all.toString();
	}

	/**
	 * Read file with the specified encoding.
	 * 
	 * @param file
	 *            File
	 * @param enc
	 *            String
	 * @return String
	 * @throws IOException
	 */
	public static String readTextFile(File file, String enc) throws IOException {
		return readTextFile(file.getAbsolutePath(), enc);
	}

	/**
	 * Read file with the specified encoding.
	 * 
	 * @param fileName
	 *            java.lang.String
	 * @param enc
	 *            java.lang.String
	 * @return java.lang.String
	 * @throws IOException
	 */
	public static String readTextFile(String fileName, String enc) throws IOException {

		InputStreamReader in = null;
		StringBuffer all = new StringBuffer();
		int bufferLength = 512;
		int readLength = 0;
		char[] ch = new char[bufferLength];

		try {
			in = new InputStreamReader(new FileInputStream(fileName), enc);

			readLength = in.read(ch, 0, bufferLength);
			while (readLength != -1) {
				all.append(ch, 0, readLength);
				readLength = in.read(ch, 0, bufferLength);
			}
		} finally {
			// try {
			if (in != null) {
				in.close();
			}
			// } catch (Exception ex) {
			// throw new ReadFileException(ex);
			// }
		}

		return all.toString();
	}
	
	/**
	 * @param file
	 *            java.io.File
	 * @param content
	 *            java.lang.String
	 * @param append
	 *            boolean
	 * @throws IOException
	 */
	public static void writeTextFile(File file, String content, boolean append) throws IOException {

		writeTextFile(file.getAbsolutePath(), content, append);
	}

	/**
	 * @param fileName
	 *            java.lang.String The absolute path of the file ready to be
	 *            written.
	 * @param content
	 *            java.lang.String
	 * @throws IOException
	 */
	public static void writeTextFile(String fileName, String content, boolean append) throws IOException {

		BufferedWriter out = null;

		try {
			
			File file = new File(fileName);
			File parentPath = new File(file.getParent());
			
			if (parentPath.exists() == false) {
				parentPath.mkdirs();
			} else if (parentPath.isFile() == true) {
				parentPath.mkdirs();
			}

			// Create a empty new file if the file does not exist.
			file.createNewFile();

			out = new BufferedWriter(new FileWriter(file, append));
			if (append) {
				out.newLine();
			}
			out.write(content);
			
		} finally {
			if (out != null) {
				out.close();
			}
		}
		
	}
}
