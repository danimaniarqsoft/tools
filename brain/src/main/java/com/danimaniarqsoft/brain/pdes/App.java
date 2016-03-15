package com.danimaniarqsoft.brain.pdes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class App {
  public static void main(String[] args) {
    List<Credentials> members = getNames();
    System.out.println("-------------------------------------------");
    for (Credentials credentials : members) {
      String name = credentials.getName();
      String email = credentials.getEmail();
      String username = email.substring(0, email.indexOf("@"));
      System.out.println("Username: " + username);
      System.out.println("Password: " + getPassword(username));
      System.out.println("FullName: " + credentials.getName());
      System.out.println("Email: " + credentials.getEmail());
      System.out.println("-------------------------------------------");
    }
  }

  private static List<Credentials> getNames() {
    try {

      String sourceFile = App.class.getResource("/data.csv").getFile();
      CSVReader reader = new CSVReader(new FileReader(sourceFile));
      List<Credentials> credentials = new ArrayList<Credentials>();
      String[] nextLine;
      while ((nextLine = reader.readNext()) != null) {
        credentials.add(new Credentials(nextLine[0], nextLine[1]));
      }
      reader.close();
      return credentials;
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Something is wrong", e);
    } catch (IOException e) {
      throw new RuntimeException("Something is wrong", e);
    }
  }

  private static String getPassword(String username) {
    int index = username.indexOf(".");
    if (index == -1) {
      return username + "2016";
    } else {
      return username.substring(0, index) + "2016";
    }
  }
}
