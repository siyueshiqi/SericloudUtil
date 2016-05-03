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
 * File Name: JavaClass.java
 * Package Name: seri.core.io.model
 * 
 * Date: May 2, 2016
 * Author: Sericloud
 * 
 */

package seri.core.io.model;

import java.util.ArrayList;
import java.util.List;

import seri.core.io.builder.StringHelper;
import seri.core.lang.JavaSrcElm;

public class JavaClass {
	private String pkgName;
	private List<String> importList;
	private String simpleName;
	private String fullName;

	private String extend;
	private List<String> implementsList;
	private List<String> attrList;

	// ////////////////////////////////////

	public JavaClass(String classFileName) {
		super();

		importList = new ArrayList<String>();
		implementsList = new ArrayList<String>();
		attrList = new ArrayList<String>();


		if (!classFileName.endsWith(JavaSrcElm.JAVA_FILE_EXT)) {
			if (classFileName.endsWith(JavaSrcElm.DOT)) {
				classFileName = classFileName + JavaSrcElm.DOT;
			}
			else {
				classFileName = classFileName + JavaSrcElm.JAVA_FILE_EXT;
			}
		}
		
		int indexOfSrc = classFileName.indexOf(JavaSrcElm.SRC_PATH_NAME); // src/...
		int indexOfLastSlash = classFileName.lastIndexOf(JavaSrcElm.SLASH);// .../
		int indexOfLastDot = classFileName.lastIndexOf(JavaSrcElm.DOT);
		
		String slashPkgName = classFileName.substring(indexOfSrc + JavaSrcElm.SRC_PATH_NAME.length(),
				indexOfLastSlash);
		pkgName = StringHelper.replace(slashPkgName, JavaSrcElm.SLASH, JavaSrcElm.DOT);
		
		simpleName = classFileName.substring(indexOfLastSlash + 1, indexOfLastDot);
		
		fullName = pkgName + JavaSrcElm.DOT + simpleName;
		
	}

	public void addImportItem(String importItem) {
		importList.add(importItem);
	}

	public void addImplementsItem(String implementsItem) {
		implementsList.add(implementsItem);
	}

	public void  addAttrItem(String attrItem) {
		attrList.add(attrItem);
	}
	
	// ////////////////////////////////////

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public List<String> getImportList() {
		return importList;
	}

	public void setImportList(List<String> importList) {
		this.importList = importList;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public List<String> getImplementsList() {
		return implementsList;
	}

	public void setImplementsList(List<String> implementsList) {
		this.implementsList = implementsList;
	}

	public List<String> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<String> attrList) {
		this.attrList = attrList;
	}

}
