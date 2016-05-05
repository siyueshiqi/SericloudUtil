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
 * File Name: FtlUtilDemo.java
 * Package Name: seri.core.io.test
 * 
 * Date: May 5, 2016
 * Author: Sericloud
 * 
 */

package seri.core.io.test;

import java.io.IOException;

import seri.core.io.file.FtlUtil;

/**
 * read a.ftl to generate ExeEvalObj.java
 * read b.ftl to generate CtrlEvalObj.java
 * 
 * @author Sericloud
 * @date May 5, 2016
 *
 */
public class FtlUtilDemo {
	public static void main(String[] args) {
		String exeFilePathStr = "src/seri/core/io/test/a.ftl";
		String exeClassPathStr = "src/seri/core/io/test/ExeEvalObj.java";
		
		String ctrlFilePathStr = "src/seri/core/io/test/b.ftl";
		String ctrlClassPathStr = "src/seri/core/io/test/CtrlEvalObj.java";
		try {
			FtlUtil.generateModelClass(exeFilePathStr, exeClassPathStr);
			FtlUtil.generateModelClass(ctrlFilePathStr, ctrlClassPathStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
