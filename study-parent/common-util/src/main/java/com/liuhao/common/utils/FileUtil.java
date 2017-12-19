package com.liuhao.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.alibaba.fastjson.util.IOUtils;

public class FileUtil {

	public static String read(String path) {
		InputStream input = null;
		InputStreamReader inputReader = null;
		BufferedReader reader = null;
		StringBuilder txt = new StringBuilder();

		try {
			input = FileUtil.class.getClassLoader().getResourceAsStream(path);
			inputReader = new InputStreamReader(input);
			reader = new BufferedReader(inputReader);


			String line = "";

			while ((line = reader.readLine()) != null) {
				txt.append(line).append("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(input);
			IOUtils.close(inputReader);
			IOUtils.close(reader);
		}
		return txt.toString();
	}
}
