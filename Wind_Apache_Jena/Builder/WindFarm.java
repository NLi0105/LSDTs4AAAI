// Java
package Builder;

import Visitor.Visitor;
import Visitor.TurbineVisitor;
import Visitor.GeospatialVisitor;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

public class WindFarm {
    private String WindFarmID;
    private String Site;
    private double AEP;
    private double TurbineSpacing;
    private double NumberOfTurbinesPerRow;
    private double NumberOfTurbines;
    private double Location;
    private double FarmPowerOutput;
    private List<Turbine> turbines;
    private Geospatial geospatial;

    // Geometry
    private Geometry geometry;

    public WindFarm() {
        this.turbines = new ArrayList<>();
    }

    public void setWindFarmID(String WindFarmID) {
        this.WindFarmID = WindFarmID;
    }

    public String getWindFarmID() {
        return WindFarmID;
    }

    public void setSite(String Site) {
        this.Site = Site;
    }

    public String getSite() {
        return Site;
    }

    public void setAEP(double AEP) {
        this.AEP = AEP;
    }

    public double getAEP() {
        return AEP;
    }

    public void setTurbineSpacing(double TurbineSpacing) {
        this.TurbineSpacing = TurbineSpacing;
    }

    public double getTurbineSpacing() {
        return TurbineSpacing;
    }

    public void setNumberOfTurbinesPerRow(double NumberOfTurbinesPerRow) {
        this.NumberOfTurbinesPerRow = NumberOfTurbinesPerRow;
    }

    public double getNumberOfTurbinesPerRow() {
        return NumberOfTurbinesPerRow;
    }

    public void setNumberOfTurbines(double NumberOfTurbines) {
        this.NumberOfTurbines = NumberOfTurbines;
    }

    public double getNumberOfTurbines() {
        return NumberOfTurbines;
    }

    public void setLocation(double Location) {
        this.Location = Location;
    }

    public double getLocation() {
        return Location;
    }

    public void setFarmPowerOutput(double FarmPowerOutput) {
        this.FarmPowerOutput = FarmPowerOutput;
    }

    public double getFarmPowerOutput() {
        return FarmPowerOutput;
    }

    public void setTurbines(List<Turbine> turbines) {
        this.turbines = turbines;
    }

    public List<Turbine> getTurbines() {
        return turbines;
    }

    public void addTurbine(Turbine turbine) {
        turbines.add(turbine);
    }

    public void removeTurbine(Turbine turbine) {
        turbines.remove(turbine);
    }

    public void setGeospatial(Geospatial geospatial) {
        this.geospatial = geospatial;
    }

    public Geospatial getGeospatial() {
        return geospatial;
    }

    public void removeGeospatial() {
        geospatial = null;
    }

    // Geometry
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if (visitor instanceof TurbineVisitor) {
            for (Turbine turbine : turbines) {
                visitor.visit(turbine);
            }
        }
    }
}