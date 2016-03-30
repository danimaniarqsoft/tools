package com.danimaniarqsoft.brain.main;

import java.net.URI;
import java.util.Arrays;

import org.apache.http.client.utils.URIUtils;

import com.danimaniarqsoft.brain.dao.OverallMetricsDAO;
import com.danimaniarqsoft.brain.pdes.model.SizeTable;

public class MainTest {
  public static void main(String[] args) throws Exception {
    // System.out.println(DateUtils.convertDateToString(new Date()));
    // Date date = DateUtils.convertStringToDate("20/3/2016");
    // System.out.println(DateUtils.convertDateToString(DateUtils.moveDays(date, -8)));
    // Document doc = Jsoup
    // .connect(
    // "http://localhost:2468/dads_strategy2016//reports/week.class?tl=auto&labelFilterAuto=t&pathFilterAuto=t")
    // .get();
    // // // GeneralTable gt = new GeneralTable(doc);
    // Element element = doc.select("body table tbody tr td.left").get(1);
    // String parse = element.text();
    // System.out.println(parse);
    // File file = new File(Constants.REPORT_FOLDER + DateUtils.getDateFolderForma(new Date()));
    // File outPutFile = new File(file, "hola.html");
    // System.out.println(outPutFile.toString());
    // //
    // System.out.println("Fecha Reporte : " + gt.getDateReport());
    // System.out.println("PeriodoReportado : " + gt.getReportedPeriod());
    // System.out.println("Fecha Fin Planeada : " + gt.getDateEndPlanned());
    // System.out.println("Estatus : " + gt.getStatus());
    // System.out.println("Fecha Fin Pronosticada : " + gt.getDateForecast());

    // String date = "To Date (through 3/23/16)";
    // String sDate = DateUtils.extractDate(date);
    // System.out.println(DateUtils.convertPdesDate(sDate));
    // Serialization
    // Serialization
    URI uri = URIUtils.createURI("http", "localhost", Integer.parseInt("2468"),
        "dads_strategy2016" + "//cms/TSP/indiv_plan_summary", "frame=content&section=100", null);
    OverallMetricsDAO omDAO = new OverallMetricsDAO(uri);
    SizeTable table = omDAO.findSizeTable("body div form table tbody");
    String[][] t = table.getData();
  }
}
