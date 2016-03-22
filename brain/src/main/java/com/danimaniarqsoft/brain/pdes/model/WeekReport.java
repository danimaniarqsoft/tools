package com.danimaniarqsoft.brain.pdes.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WeekReport {
	public String createReport(final String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		WeekTable table = new WeekTable(doc);

		String vg = table.getProperty(1, 4) + "% " + "Diferencia = "
				+ (table.getProperty(1, 4) - table.getProperty(1, 3))
				+ " Falta :" + ((1 - table.getProperty(1, 5)) * 100 + "%");
		String horasTarea = table.getProperty(1, 1) + "";
		String tareaCerradas = ((table.getProperty(3, 2) * 100) - 100) + "%";
		double hsTareasTerm = (table.getProperty(1, 1) - table
				.getProperty(3, 1));
		String semHrTarNoTerm = (hsTareasTerm / table.getProperty(2, 1))
				+ " Semanas";
		double vhxH = (table.getProperty(1, 4) / table.getProperty(3, 1));
		String vgNoRe = (vhxH * hsTareasTerm) + " Puntos";
		String recup = (table.getProperty(1, 3) - (table.getProperty(1, 4))
				/ table.getProperty(2, 4))
				+ " Semanas";
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-40s : %-20s ", "VG", vg)).append("\n");
		sb.append(String.format("%-40s : %-20s ", "Horas Tarea", horasTarea))
				.append("\n");
		sb.append(
				String.format("%-40s : %-20s ",
						"Tareas Cerradas Sobre/SubEstimadas", tareaCerradas))
				.append("\n");
		sb.append(
				String.format("%-40s : %-20s ", "Hs en tareas no terminadas",
						hsTareasTerm)).append("\n");
		sb.append(
				String.format("%-40s : %-20s ",
						"Semanas de Hs en tareas no terminadas", semHrTarNoTerm))
				.append("\n");
		sb.append(String.format("%-40s : %-20s ", "VG/H", vhxH)).append("\n");
		sb.append(String.format("%-40s : %-20s ", "VG no realizado", vgNoRe))
				.append("\n");
		sb.append(String.format("%-40s : %-20s ", "Recuperación", recup))
				.append("\n");
		saveToDisk(sb);
		return null;
	}
	
	public static void saveToDisk(StringBuilder sb) {
		FileOutputStream fop = null;
		File file;
		String content = sb.toString();

		try {

			file = new File("./data.txt");
			fop = new FileOutputStream(file);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
