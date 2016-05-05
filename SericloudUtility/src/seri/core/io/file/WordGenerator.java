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
 * File Name: WordGenerator.java
 * Package Name: seri.core.io.file
 * 
 * Date: May 2, 2016
 * Author: Sericloud
 * 
 */

package seri.core.io.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordGenerator {
	private static Configuration configuration = null;
	private static Map<String, Template> allTemplates = null;
	private static boolean isConfigured = false;

	static {
		configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setDefaultEncoding("utf-8");
		allTemplates = new HashMap<>();
	}

	private WordGenerator() {
		throw new AssertionError();
	}
	
	/**
	 * 
	 * @param basePkgPath
	 * @param fileNameList
	 * 			List<String> : template file name
	 * @throws IOException
	 */
	public static void configureWithFileNameList(String basePkgPath, List<String> fileNameList) throws IOException {
		if (basePkgPath == null || fileNameList == null) {
			return;
		}
		configuration.setClassForTemplateLoading(WordGenerator.class, basePkgPath);
		for (String fileName : fileNameList) {
			allTemplates.put(fileName, configuration.getTemplate(fileName));
		}
		isConfigured = true;
	}
	
	/**
	 * 
	 * @param basePkgPath
	 * @param templatesMap
	 * 			Map<key,value>, key: template name, value: template file name
	 */
	public static void configureWithTemplateMap(String basePkgPath, Map<String, String> templatesMap) throws IOException {
		configuration.setClassForTemplateLoading(WordGenerator.class, basePkgPath);
		
		for (Map.Entry<String, String> entry : templatesMap.entrySet()) {
			allTemplates.put(entry.getKey(), configuration.getTemplate(entry.getValue()));
		}
		isConfigured = true;
	}

	public static File createDocWithDataMapAndTemplateName(Map<?, ?> dataMap, String templateName, String docPath) throws IOException {
		if (isConfigured == false) {
			throw new IOException("Pleast call 'WordGenerator.configureXXX' method first, before you create Doc.");
		}

		File f = new File(docPath);
		if (!f.exists()) {
			FileUtil.createFile(f);
		}
		Template t = allTemplates.get(templateName);
		try {
			// Cann't use FileWriter, because here need to set CharSet.
			// Otherwise, the generated Word will cann't be opened.
			Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			t.process(dataMap, w);
			w.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return f;
	}
}
