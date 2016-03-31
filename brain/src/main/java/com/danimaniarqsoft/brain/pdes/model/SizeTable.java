package com.danimaniarqsoft.brain.pdes.model;

import org.jsoup.nodes.Element;

public class SizeTable {
  Element data;

  public SizeTable(Element data) {
    this.data = data;
  }

  public Element getData() {
    return this.data;
  }
}
