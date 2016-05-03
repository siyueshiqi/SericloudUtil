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
 * Project Name: MyBatisPioneer
 * File Name: BuilderHelper.java
 * Package Name: seri.core.io.builder
 * 
 * Date: May 2, 2016
 * Author: Sericloud
 * 
 */


package seri.core.io.builder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seri.core.io.model.JavaClass;
import seri.core.lang.GlobalConst;
import seri.core.lang.JavaSrcElm;

public class BuilderHelper {

	public static String exportPackage(String pkg) {

		StringBuffer srcCode = new StringBuffer();

		srcCode.append(JavaSrcElm.PACKAGE);
		srcCode.append(JavaSrcElm.WHITE_SPACE);
		srcCode.append(pkg);
		srcCode.append(JavaSrcElm.SEMICOLON);
		srcCode.append(GlobalConst.LINE_SEPARATOR);

		return srcCode.toString();

	}

	public static String exportImport(List<String> importList, int skipStep) {

		// add by fr 2015-06-26
		// record the imported class
		Set<String> importedMap = new HashSet<String>();

		StringBuffer javaImport = new StringBuffer();

		// srcCode.append(JavaSrcElm.IMPORT);
		// srcCode.append(JavaSrcElm.WHITE_SPACE);
		// srcCode.append(JavaSrcElm.IO_SERIALIZABLE_FULL);
		// srcCode.append(JavaSrcElm.SEMICOLON);
		// srcCode.append(GlobalConst.LINE_SEPARATOR);

		if (skipStep <= 0) {
			javaImport.append(JavaSrcElm.IMPORT);
			javaImport.append(JavaSrcElm.WHITE_SPACE);
			javaImport.append(JavaSrcElm.UTIL_LIST_FULL);
			javaImport.append(JavaSrcElm.SEMICOLON);
			javaImport.append(GlobalConst.LINE_SEPARATOR);

			importedMap.add(JavaSrcElm.UTIL_LIST_FULL);
		}

		int importListSize = importList.size();
		if (importListSize == 0) {
			return javaImport.toString();
		}

		StringBuffer slpImport = new StringBuffer();
		String importLine = null;
		int lessThan = -1;
		for (int i = 0; i < importListSize; i++) {
			importLine = importList.get(i).toString();
			if (importLine.startsWith(JavaSrcElm.JAVA_PKG_PREFIX) == true) {
				// Get rid of those imported type with generic type declaration.
				lessThan = importLine.indexOf(JavaSrcElm.LESS_THAN);
				if (lessThan != -1) {
					importLine = importLine.substring(0, lessThan);
				}

				if (!importedMap.contains(importLine)) {
					javaImport.append(JavaSrcElm.IMPORT);
					javaImport.append(JavaSrcElm.WHITE_SPACE);
					javaImport.append(importLine);
					javaImport.append(JavaSrcElm.SEMICOLON);
					javaImport.append(GlobalConst.LINE_SEPARATOR);
					importedMap.add(importLine);
				}

			} else {
				if (!importedMap.contains(importLine)) {
					slpImport.append(JavaSrcElm.IMPORT);
					slpImport.append(JavaSrcElm.WHITE_SPACE);
					slpImport.append(importLine);
					slpImport.append(JavaSrcElm.SEMICOLON);
					slpImport.append(GlobalConst.LINE_SEPARATOR);
					importedMap.add(importLine);
				}
			}
		}

		if (javaImport.length() > 0 && slpImport.length() > 0) {
			javaImport.append(GlobalConst.LINE_SEPARATOR);
		}

		return javaImport.toString() + slpImport.toString();

	}

	public static String exportName(JavaClass javaClass, boolean isClass) {

		StringBuffer srcCode = new StringBuffer();

		// Build type and name.
		srcCode.append(JavaSrcElm.PUBLIC);
		srcCode.append(JavaSrcElm.WHITE_SPACE);
		if (isClass == true) {
			srcCode.append(JavaSrcElm.CLASS);
		} else {
			srcCode.append(JavaSrcElm.INTERFACE);
		}
		srcCode.append(JavaSrcElm.WHITE_SPACE);
		srcCode.append(javaClass.getSimpleName());

		return srcCode.toString();

	}

	public static String exportSuperCls(JavaClass javaClass) {

		List<String> implementsList = javaClass.getImplementsList();

		StringBuffer srcCode = new StringBuffer();
		if (javaClass.getExtend() != null && !javaClass.getExtend().isEmpty()) {
			srcCode.append(JavaSrcElm.WHITE_SPACE);
			srcCode.append(JavaSrcElm.EXTENDS);
			srcCode.append(JavaSrcElm.WHITE_SPACE);
			srcCode.append(javaClass.getExtend());
		}
		int implementsListSize = implementsList.size();
		if (implementsListSize != 0) {
			for (int i = 0; i < implementsListSize; i++) {
				if (i == 0) {
					srcCode.append(JavaSrcElm.WHITE_SPACE);
					srcCode.append(JavaSrcElm.IMPLEMENTS);
				} else {
					srcCode.append(JavaSrcElm.COMMA);
				}
				srcCode.append(JavaSrcElm.WHITE_SPACE);
				srcCode.append(implementsList.get(i));
			}
		}

		return srcCode.toString();

	}

	public static String exportOpeningBrace() {

		StringBuffer srcCode = new StringBuffer();

		srcCode.append(JavaSrcElm.WHITE_SPACE);
		srcCode.append(JavaSrcElm.LEFT_BRACKET);
		srcCode.append(GlobalConst.LINE_SEPARATOR);

		return srcCode.toString();

	}

	public static String exportImplicitConstructor(JavaClass javaClass) {

		StringBuffer srcCode = new StringBuffer();
		String indents = exportIndents();

		srcCode.append(GlobalConst.LINE_SEPARATOR);
		srcCode.append(indents);
		srcCode.append(JavaSrcElm.PUBLIC);
		srcCode.append(JavaSrcElm.WHITE_SPACE);
		srcCode.append(javaClass.getSimpleName());
		srcCode.append(JavaSrcElm.LEFT_PARENTHESIS);
		srcCode.append(JavaSrcElm.RIGHT_PARENTHESIS);
		srcCode.append(JavaSrcElm.WHITE_SPACE);
		srcCode.append(exportOpeningBracket());
		srcCode.append(exportClosingBracket());

		return srcCode.toString();

	}

	public static String exportOpeningBracket() {

		StringBuffer srcCode = new StringBuffer();

		srcCode.append(JavaSrcElm.LEFT_BRACKET);
		srcCode.append(GlobalConst.LINE_SEPARATOR);

		return srcCode.toString();

	}

	public static String exportClosingBracket() {

		String indents = exportIndents();

		StringBuffer srcCode = new StringBuffer();
		srcCode.append(indents);
		srcCode.append(JavaSrcElm.RIGHT_BRACKET);
		srcCode.append(GlobalConst.LINE_SEPARATOR);

		return srcCode.toString();

	}

	public static String exportIndents() {

		StringBuffer srcCode = new StringBuffer();
		int count = JavaSrcElm.NUM_OF_SPACE_PER_TAB;

		for (int i = 0; i < count; i++) {
			srcCode.append(JavaSrcElm.WHITE_SPACE);
		}

		return srcCode.toString();

	}

	public static String exportVersionUid() {

		StringBuffer srcCode = new StringBuffer();
		String indents = exportIndents();

		srcCode.append(indents);
		srcCode.append(JavaSrcElm.PRIVATE_STATIC_FINAL);
		srcCode.append(JavaSrcElm.WHITE_SPACE);

		srcCode.append(JavaSrcElm.LONG);
		srcCode.append(JavaSrcElm.WHITE_SPACE);
		srcCode.append(JavaSrcElm.SERIAL_VERSION_UID);
		srcCode.append(JavaSrcElm.EQUAL);
		srcCode.append("1");
		srcCode.append(JavaSrcElm.LONG_SUFFIX);
		srcCode.append(JavaSrcElm.SEMICOLON);
		srcCode.append(GlobalConst.LINE_SEPARATOR);

		return srcCode.toString();

	}
}
