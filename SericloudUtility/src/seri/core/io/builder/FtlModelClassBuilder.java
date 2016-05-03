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
 * File Name: FtlModelClassBuilder.java
 * Package Name: seri.core.io.builder
 * 
 * Date: May 2, 2016
 * Author: Sericloud
 * 
 */

package seri.core.io.builder;

import java.util.List;

import seri.core.io.model.JavaClass;
import seri.core.lang.GlobalConst;
import seri.core.lang.JavaSrcElm;

public class FtlModelClassBuilder {

	private static final String FTL_MAP_ATTR = "ftlMap";
	private static final String DECLARE_FTL_MAP = "private Map<String, String> " + FTL_MAP_ATTR + " = new HashMap<String, String>();";
	
	private static FtlModelClassBuilder ftlModelClassBuilder;

	private FtlModelClassBuilder() {
	}

	public synchronized static FtlModelClassBuilder getInstance() {
		if (ftlModelClassBuilder == null) {
			ftlModelClassBuilder = new FtlModelClassBuilder();
		}
		return ftlModelClassBuilder;
	}

	public String exportClass(JavaClass javaClass) {
		// Make additional update on JavaClass
		buildClass(javaClass);

		// Content of current class entity
		StringBuffer srcCode = new StringBuffer();

		// Build the package and import statement
		srcCode.append(BuilderHelper.exportPackage(javaClass.getPkgName()));
		srcCode.append(GlobalConst.LINE_SEPARATOR);

		srcCode.append(BuilderHelper.exportImport(javaClass.getImportList(), 1));
		srcCode.append(GlobalConst.LINE_SEPARATOR);

		// Export type and name.
		srcCode.append(BuilderHelper.exportName(javaClass, true));

		// Export extends and implements.
		srcCode.append(BuilderHelper.exportSuperCls(javaClass));

		// Export opening brace.
		srcCode.append(BuilderHelper.exportOpeningBrace());
		srcCode.append(GlobalConst.LINE_SEPARATOR);

		// Export attrs.
		srcCode.append(exportAllAttrs(javaClass.getAttrList()));

		// Export getter/setter method
		srcCode.append(exportSetterMethods(javaClass.getAttrList()));

		// Export refreshMap Method
		srcCode.append(exportGetFtlMapMethod());

		// Close the class
		srcCode.append(GlobalConst.LINE_SEPARATOR);
		srcCode.append(JavaSrcElm.RIGHT_BRACKET);

		return srcCode.toString();
	}

	private Object exportGetFtlMapMethod() {
		String indents = BuilderHelper.exportIndents();
		StringBuffer srcCode = new StringBuffer();
		srcCode.append(indents);
	    srcCode.append("public Map<String, String> getMap() {");
	    srcCode.append(GlobalConst.LINE_SEPARATOR);
	    srcCode.append(indents);
	    srcCode.append(indents);
	    srcCode.append("return ftlMap;");
	    srcCode.append(GlobalConst.LINE_SEPARATOR);
	    srcCode.append(indents);
	    srcCode.append("}");
	    srcCode.append(GlobalConst.LINE_SEPARATOR);
		return srcCode.toString();
	}

	private void buildClass(JavaClass javaClass) {
		javaClass.getImportList().add(JavaSrcElm.IO_SERIALIZABLE_FULL);
		javaClass.getImportList().add(JavaSrcElm.UTIL_MAP_FULL);
		javaClass.getImportList().add(JavaSrcElm.UTIL_HASHMAP_FULL);

		javaClass.getImplementsList().add(JavaSrcElm.IO_SERIALIZABLE_SIMPLE);
	}

	private String exportSetterMethods(List<String> attrList) {
		StringBuffer srcCode = new StringBuffer();
		int attrListSize = attrList.size();
		if (attrListSize == 0) {
			return srcCode.toString();
		}
		String indents = BuilderHelper.exportIndents();

		String returnType = JavaSrcElm.VOID;
		String parameterType = JavaSrcElm.STRING;
		for (int i = 0; i < attrListSize; i++) {
			String attrName = attrList.get(i);
			String setterMethodName = JavaSrcElm.SET
					+ StringHelper.toUpperCase(attrList.get(i), 0);

			// Process the method modifiers, return value and name
			srcCode.append(GlobalConst.LINE_SEPARATOR);
			srcCode.append(indents);
			srcCode.append(JavaSrcElm.PUBLIC);
			srcCode.append(JavaSrcElm.WHITE_SPACE);
			srcCode.append(returnType);
			srcCode.append(JavaSrcElm.WHITE_SPACE);
			srcCode.append(setterMethodName);

			// Process parameters
			srcCode.append(JavaSrcElm.LEFT_PARENTHESIS);
			srcCode.append(parameterType);
			srcCode.append(JavaSrcElm.WHITE_SPACE);
			srcCode.append(attrName);
			srcCode.append(JavaSrcElm.RIGHT_PARENTHESIS);

			// Process method body.
			srcCode.append(JavaSrcElm.WHITE_SPACE);
			srcCode.append(JavaSrcElm.LEFT_BRACKET);
			srcCode.append(GlobalConst.LINE_SEPARATOR);
			
			/*
			// attribute
			srcCode.append(indents);
			srcCode.append(indents);
			srcCode.append(JavaSrcElm.THIS);
			srcCode.append(JavaSrcElm.DOT);
			srcCode.append(attrName);
			srcCode.append(JavaSrcElm.WHITE_SPACE);
			srcCode.append(JavaSrcElm.EQUAL);
			srcCode.append(JavaSrcElm.WHITE_SPACE);
			srcCode.append(attrName);
			srcCode.append(JavaSrcElm.SEMICOLON);
			srcCode.append(GlobalConst.LINE_SEPARATOR);
			*/
			
			// ftlMap.put("attributeName", this.attribute);
			srcCode.append(indents);
			srcCode.append(indents);
			srcCode.append(JavaSrcElm.THIS);
			srcCode.append(JavaSrcElm.DOT);
			srcCode.append(FTL_MAP_ATTR);
			srcCode.append(JavaSrcElm.DOT);
			srcCode.append(JavaSrcElm.PUT);
			srcCode.append(JavaSrcElm.LEFT_PARENTHESIS);
			srcCode.append(JavaSrcElm.DOUBLE_QUOTATION);
			srcCode.append(attrName);
			srcCode.append(JavaSrcElm.DOUBLE_QUOTATION);
			srcCode.append(JavaSrcElm.COMMA);
			srcCode.append(attrName);
			srcCode.append(JavaSrcElm.RIGHT_PARENTHESIS);
			srcCode.append(JavaSrcElm.SEMICOLON);
			srcCode.append(GlobalConst.LINE_SEPARATOR);
			
			srcCode.append(indents);
			srcCode.append(JavaSrcElm.RIGHT_BRACKET);

			srcCode.append(GlobalConst.LINE_SEPARATOR);
			

		}
		srcCode.append(GlobalConst.LINE_SEPARATOR);
		return srcCode.toString();
	}

	private String exportAllAttrs(List<String> attrList) {

		StringBuffer srcCode = new StringBuffer();
		srcCode.append(BuilderHelper.exportVersionUid());
		
		// Export ftlMap attribute
		srcCode.append(BuilderHelper.exportIndents());
		srcCode.append(DECLARE_FTL_MAP);
		srcCode.append(GlobalConst.LINE_SEPARATOR);
		/*
		srcCode.append(GlobalConst.LINE_SEPARATOR);
		int attrListSize = attrList.size();
		if (attrListSize == 0) {
			return srcCode.toString();
		}

		for (int i = 0; i < attrListSize; i++) {
			String attrName = attrList.get(i);
			srcCode.append(BuilderHelper.exportIndents());
			srcCode.append(JavaSrcElm.PRIVATE);
			srcCode.append(JavaSrcElm.WHITE_SPACE);
			srcCode.append(JavaSrcElm.STRING);
			srcCode.append(JavaSrcElm.WHITE_SPACE);
			srcCode.append(attrName);
			srcCode.append(JavaSrcElm.EQUAL);
			srcCode.append(JavaSrcElm.EMPTY_VALUE);
			srcCode.append(JavaSrcElm.SEMICOLON);
			srcCode.append(GlobalConst.LINE_SEPARATOR);
		}*/
		return srcCode.toString();
	}
}
