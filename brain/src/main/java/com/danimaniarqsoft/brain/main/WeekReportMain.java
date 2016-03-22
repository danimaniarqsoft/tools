package com.danimaniarqsoft.brain.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.http.client.utils.URIUtils;

import com.danimaniarqsoft.brain.pdes.model.WeekReport;

public class WeekReportMain {

	public static void main(String[] args) throws NumberFormatException,
			URISyntaxException, IOException {
		Properties properties = readProperties();
		String project = properties.getProperty("project");
		String port = properties.getProperty("port");
		URI uri = URIUtils.createURI("http", "localhost",
				Integer.parseInt(port), project + "//reports/week.class",
				"tl=auto&labelFilterAuto=t&pathFilterAuto=t", null);
		// String URL = readFile("test.html", Charset.defaultCharset());

		try {
			String report = new WeekReport().createReport(uri.toString());
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			WeekReport
					.saveToDisk(new StringBuilder(uri.toString()).append("\n").append(sw.toString()));
		}
	}

	private static Properties readProperties() throws IOException {
		Properties mainProperties = new Properties();
		FileInputStream file;
		String path = "./pdes.properties";
		file = new FileInputStream(path);
		mainProperties.load(file);
		file.close();
		return mainProperties;
	}
}
