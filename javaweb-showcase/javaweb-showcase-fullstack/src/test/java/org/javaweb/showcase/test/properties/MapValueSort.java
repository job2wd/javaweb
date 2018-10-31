/**
 * $Id: MapValueSort.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.properties;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class MapValueSort {

  /**
   * 
   */
  public MapValueSort() {
    // TODO Auto-generated constructor stub
  }

  /** inner class to do soring of the map **/
  public static class ValueComparer implements Comparator {
    private Map _data = null;

    public ValueComparer(Map data) {
      super();
      _data = data;
    }

    @Override
    public int compare(Object o1, Object o2) {
      String e1 = (String) _data.get(o1);
      String e2 = (String) _data.get(o2);
      return e1.compareTo(e2);
    }
  }

  public static void main(String[] args) {

    Map unsortedData = new HashMap();
    unsortedData.put("2", "DEF");
    unsortedData.put("1", "ABC");
    unsortedData.put("4", "ZXY");
    unsortedData.put("3", "BCD");

    printMap(unsortedData);
    
    SortedMap sortedData = new TreeMap(new MapValueSort.ValueComparer(unsortedData));

//    printMap(unsortedData);

    sortedData.putAll(unsortedData);
    System.out.println();
    printMap(sortedData);
  }

  public static void printMap(Map data) {
    for (Iterator iter = data.keySet().iterator(); iter.hasNext();) {
      String key = (String) iter.next();
      System.out.println("Value/key:" + data.get(key) + "/" + key);
    }
  }

}
