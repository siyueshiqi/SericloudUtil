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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seri.core.io.test.CtrlEvalMap;
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

	public static void main(String[] args) {
		String excellence = "■优秀 □合格 □基本合格 □不合格";
		String pass = "□优秀 ■合格 □基本合格 □不合格";
		String basic = "□优秀 □合格 ■基本合格 □不合格";
		String fail = "□优秀 □合格 □基本合格 ■不合格";
		String noScore = "□优秀 □合格 □基本合格 □不合格";
		
		Map<String, Object> ftlMap = new HashMap<String, Object>();
		ftlMap.put("proName","proName");
		ftlMap.put("proAddr","proAddr");
		ftlMap.put("proBegDate","proBegDate");
		ftlMap.put("proEndDate","proEndDate");
		ftlMap.put("manager","manager");
		ftlMap.put("managerTel","managerTel");
		ftlMap.put("contractNum","contractNum");
		ftlMap.put("contractCost","contractCost");
		ftlMap.put("constructionUnit","constructionUnit");
		ftlMap.put("constructionAddr","constructionAddr");
		ftlMap.put("consContact","consContact");
		ftlMap.put("consTel","consTel");
		ftlMap.put("executionCompany","executionCompany");
		ftlMap.put("exeAddr","exeAddr");
		ftlMap.put("exeContact","exeContact");
		ftlMap.put("exeTel","exeTel");
		ftlMap.put("arrivingScore",excellence);
		ftlMap.put("arrivingDesc","arrivingDesc");
		ftlMap.put("scheduleScore",fail);
		ftlMap.put("scheduleDesc","scheduleDesc");
		ftlMap.put("qualityScore",basic);
		ftlMap.put("qualityDesc","qualityDesc");
		ftlMap.put("securityScore",pass);
		ftlMap.put("securityDesc","securityDesc");
		ftlMap.put("salaryScore",fail);
		ftlMap.put("salaryDesc","salaryDesc");
		ftlMap.put("performanceScore",noScore);
		ftlMap.put("performanceDesc","performanceDesc");
		
		CtrlEvalMap ftlObj = new CtrlEvalMap();
		ftlObj.setProName("proName");
		ftlObj.setProAddr("proAddr");
		ftlObj.setProBegDate("proBegDate");
		ftlObj.setProEndDate("proEndDate");
		ftlObj.setDirector("director");
		ftlObj.setDirectorTel("directorTel");
		ftlObj.setContractNum("contractNum");
		ftlObj.setContractCost("contractCost");
		ftlObj.setConstructionUnit("constructionUnit");
		ftlObj.setConstructionAddr("constructionAddr");
		ftlObj.setConsContact("consContact");
		ftlObj.setConsTel("consTel");
		ftlObj.setCtrlCompany("ctrlCompany");
		ftlObj.setCtrlAddr("ctrlAddr");
		ftlObj.setCtrlContact("ctrlContact");
		ftlObj.setCtrlTel("ctrlTel");
		ftlObj.setArrivingScore(fail);
		ftlObj.setArrivingDesc("arrivingDesc");
		ftlObj.setQualityScore(excellence);
		ftlObj.setQualityDesc("qualityDesc");
		ftlObj.setInvestmentScore(pass);
		ftlObj.setInvestmentDesc("investmentDesc");
		ftlObj.setScheduleScore(noScore);
		ftlObj.setScheduleDesc("scheduleDesc");
		ftlObj.setSecurityScore(basic);
		ftlObj.setSecurityDesc("securityDesc");
		ftlObj.setContractScore(excellence);
		ftlObj.setContractDesc("contractDesc");
		ftlObj.setInformationScore(excellence);
		ftlObj.setInformationDesc("informationDesc");
		ftlObj.setOrganizingScore(basic);
		ftlObj.setOrganizingDesc("organizingDesc");
		ftlObj.setPerformanceScore(basic);
		ftlObj.setPerformanceDesc("performanceDesc");

		try {
			String basePkgPath = "/seri/core/io/test";
			
//			Map<String, String> templateMap = new HashMap<String, String>();
//			templateMap.put("a", "aFormat.ftl");
//			templateMap.put("b", "b.ftl");
//			WordGenerator.configureWithTemplateMap(basePkgPath, templateMap);
			
			List<String> fileNameList = new ArrayList<String>();
			fileNameList.add("a.ftl");
			fileNameList.add("b.ftl");
			WordGenerator.configureWithFileNameList(basePkgPath, fileNameList);
			
			String exePath = "src/seri/study/file/word/exe.doc";
			String ctrlPath = "src/seri/study/file/word/ctrl.doc";
			WordGenerator.createDocWithDataMapAndTemplateName(ftlMap, "a.ftl", exePath);
			WordGenerator.createDocWithDataMapAndTemplateName(ftlObj.getMap(), "b.ftl", ctrlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
