package com.danimaniarqsoft.brain.pdes.service;

import java.io.IOException;
import java.net.URI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.danimaniarqsoft.brain.pdes.model.WeekTable;

public class WeekReportService {
  public static StringBuilder createReport(final URI uri) throws IOException {
    Document doc = Jsoup.connect(uri.toString()).get();
    WeekTable table = new WeekTable(doc);
    String vg = table.getProperty(1, 4) + "% " + "Diferencia = "
        + (table.getProperty(1, 4) - table.getProperty(1, 3)) + " Falta :"
        + ((1 - table.getProperty(1, 5)) * 100 + "%");
    String horasTarea = table.getProperty(1, 1) + "";
    String tareaCerradas = ((table.getProperty(3, 2) * 100) - 100) + "%";
    double hsTareasTerm = (table.getProperty(1, 1) - table.getProperty(3, 1));
    String semHrTarNoTerm = (hsTareasTerm / table.getProperty(2, 1)) + " Semanas";
    double vhxH = (table.getProperty(1, 4) / table.getProperty(3, 1));
    String vgNoRe = (vhxH * hsTareasTerm) + " Puntos";
    String recup = (table.getProperty(1, 3) - (table.getProperty(1, 4)) / table.getProperty(2, 4))
        + " Semanas";
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("%-40s : %-20s ", "VG", vg)).append("\n");
    sb.append(String.format("%-40s : %-20s ", "Horas Tarea", horasTarea)).append("\n");
    sb.append(String.format("%-40s : %-20s ", "Tareas Cerradas Sobre/SubEstimadas", tareaCerradas))
        .append("\n");
    sb.append(String.format("%-40s : %-20s ", "Hs en tareas no terminadas", hsTareasTerm))
        .append("\n");
    sb.append(
        String.format("%-40s : %-20s ", "Semanas de Hs en tareas no terminadas", semHrTarNoTerm))
        .append("\n");
    sb.append(String.format("%-40s : %-20s ", "VG/H", vhxH)).append("\n");
    sb.append(String.format("%-40s : %-20s ", "VG no realizado", vgNoRe)).append("\n");
    sb.append(String.format("%-40s : %-20s ", "Recuperacion", recup)).append("\n");
    return sb;
  }
}
