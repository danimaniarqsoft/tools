package com.danimaniarqsoft.brain.pdes.model;

/**
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class TableWrapper {
  private double[][] doubleValues;
  private String[][] stringValues;

  public TableWrapper(double[][] doubleValues, String[][] stringValues) {
    this.doubleValues = doubleValues;
    this.stringValues = stringValues;
  }

  public String[][] getStringValues() {
    return stringValues;
  }

  public double[][] getDoubleValues() {
    return doubleValues;
  }
}
