package ies.pedro.utils;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import javafx.geometry.Rectangle2D;

public class Rectangle2DAdapterXML extends XmlAdapter<String, Rectangle2D> {

    // Convierte el XML (String) a un objeto Point2D
    @Override
    public Rectangle2D unmarshal(String v) throws Exception {
        if (v == null || v.isEmpty()) {
            return null;
        }
        String[] parts = v.split(",");
        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        double w = Double.parseDouble(parts[2]);
        double h = Double.parseDouble(parts[3]);
        return new Rectangle2D(x, y,w,h);
    }

    // Convierte un Point2D a String para ser serializado a XML
    @Override
    public String marshal(Rectangle2D v) throws Exception {
        if (v == null) {
            return null;
        }
        return v.getMinX() + "," + v.getMinY()+","+v.getWidth()+","+v.getHeight(); // Formato "x,y"
    }
}