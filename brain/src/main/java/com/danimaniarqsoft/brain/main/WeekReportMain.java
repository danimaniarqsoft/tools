package com.danimaniarqsoft.brain.main;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danimaniarqsoft.brain.pdes.service.PersonalReportService;
import com.danimaniarqsoft.brain.pdes.service.context.ReportContext;
import com.danimaniarqsoft.brain.util.Constants;
import com.danimaniarqsoft.brain.util.ContextUtil;
import com.danimaniarqsoft.brain.util.UrlPd;

/*
 * WeekReportMain class execute the main app.
 * 
 * @author Daniel Cortes Pichardo
 */
public class WeekReportMain {
	private static final Logger LOGGER = LoggerFactory.getLogger(WeekReportMain.class);

	public WeekReportMain() {

	}

	public static void main(String[] args) {
		try {
			ReportContext context = new ReportContext();
			UrlPd urlPd = ContextUtil.getUrlContext();
			context.setUrlPd(urlPd);
			PersonalReportService.getInstance().createReport(context);
			LOGGER.info("");
		} catch (Exception e) {
			ContextUtil.saveExceptionToDisk(e, Constants.FILE_ERROR_TXT, new File("./"));
		}
		LOGGER.info(
				"Thanks for using danimaniarqsoft solutions, visit my web page at www.danimanicp.com for further news");
	}
}
