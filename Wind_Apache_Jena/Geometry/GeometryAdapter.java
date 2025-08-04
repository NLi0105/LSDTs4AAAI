package Geometry;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class GeometryAdapter extends XmlAdapter<String, Geometry> {
    private WKTReader reader = new WKTReader();
    private WKTWriter writer = new WKTWriter();

    @Override
    public Geometry unmarshal(String v) throws Exception {
        return reader.read(v);
    }

    @Override
    public String marshal(Geometry v) throws Exception {
        return writer.write(v);
    }
}