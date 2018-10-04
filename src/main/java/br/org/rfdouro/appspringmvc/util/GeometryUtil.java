/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

/**
 *
 * @author romulo.douro
 */
public class GeometryUtil {
 
 /**
  * 
  * @param wktPoint
  * exemplo de entrada "POINT (22.5 40.5)"
  * @return 
  */
 public static Geometry wktToGeometry(String wktPoint) {
  WKTReader fromText = new WKTReader();
  Geometry geom = null;
  try {
   geom = fromText.read(wktPoint);
  } catch (ParseException e) {
   throw new RuntimeException("Not a WKT string:" + wktPoint);
  }
  return geom;
 }
 
 public static Point coordinatesToPoint(double x, double y){
  com.vividsolutions.jts.geom.Point p = new GeometryFactory(new PrecisionModel(), 4326).createPoint(new Coordinate(x, y));
  return p;
 }
 
 public static Point coordinatesToPoint(String x, String y){
  return coordinatesToPoint(Double.parseDouble(x), Double.parseDouble(y));
 }
 
}
