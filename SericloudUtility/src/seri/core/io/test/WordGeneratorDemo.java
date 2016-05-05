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
 * Project Name: SericloudUtility
 * File Name: WordGeneratorDemo.java
 * Package Name: seri.core.io.test
 * 
 * Date: May 5, 2016
 * Author: Sericloud
 * 
 */

package seri.core.io.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seri.core.io.file.WordGenerator;

public class WordGeneratorDemo {
	
	private static String excellence = "■优秀 □合格 □基本合格 □不合格";
	private static String pass = "□优秀 ■合格 □基本合格 □不合格";
	private static String basic = "□优秀 □合格 ■基本合格 □不合格";
	private static String fail = "□优秀 □合格 □基本合格 ■不合格";
	private static String noScore = "□优秀 □合格 □基本合格 □不合格";
	
	private static String basePkgPath = "/seri/core/io/test";
	
	Map<String, Object> getMapWithPutMethod() {
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
		
		return ftlMap;
	}
	
	Map<String, String> getMapWithSetMethod() {
		CtrlEvalObj ftlObj = new CtrlEvalObj();
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
		return ftlObj.getMap();
	}

	private void configWordGeneratorWithMap() throws IOException {
		Map<String, String> templateMap = new HashMap<String, String>();
		templateMap.put("a", "aFormat.ftl");
		templateMap.put("b", "b.ftl");
		WordGenerator.configureWithTemplateMap(basePkgPath, templateMap);
	}
	
	private void configWordGeneratorWithList() throws IOException {
		List<String> fileNameList = new ArrayList<String>();
		fileNameList.add("a.ftl");
		fileNameList.add("b.ftl");
		WordGenerator.configureWithFileNameList(basePkgPath, fileNameList);
	}
	
	public static void main(String[] args) {

		WordGeneratorDemo demo = new WordGeneratorDemo();
		String exePath = "src/seri/core/io/test/exe.doc";
		String ctrlPath = "src/seri/core/io/test/ctrl.doc";
		
		try {
			demo.configWordGeneratorWithList();
			WordGenerator.createDocWithDataMapAndTemplateName(demo.getMapWithPutMethod(), "a.ftl", exePath);
			System.out.println("generate word successfully: a.ftl-> " + exePath);
			demo.configWordGeneratorWithMap();
			WordGenerator.createDocWithDataMapAndTemplateName(demo.getMapWithSetMethod(), "b", ctrlPath);
			System.out.println("generate word successfully: b.ftl-> " + ctrlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
