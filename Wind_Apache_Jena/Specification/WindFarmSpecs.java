// Java
package Specification;

import org.locationtech.jts.geom.Geometry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import Geometry.GeometryAdapter;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "WindFarmSpecs")
public class WindFarmSpecs {
    private String WindFarmID;
    private String Site;
    private double AEP;
    private double TurbineSpacing;
    private double NumberOfTurbinesPerRow;
    private double NumberOfTurbines;
    private String Location;
    private double FarmPowerOutput;
    private List<TurbineSpecs> turbines;
    private Geometry geometry;
    private String WindFarmStatus;

    public WindFarmSpecs() {
        this.turbines = new ArrayList<>();
    }

    @XmlElement
    public String getWindFarmID() {
        return WindFarmID;
    }

    public void setWindFarmID(String windFarmID) {
        WindFarmID = windFarmID;
    }

    @XmlElement
    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    @XmlElement
    public double getAEP() {
        return AEP;
    }

    public void setAEP(double AEP) {
        this.AEP = AEP;
    }

    @XmlElement
    public double getTurbineSpacing() {
        return TurbineSpacing;
    }

    public void setTurbineSpacing(double turbineSpacing) {
        TurbineSpacing = turbineSpacing;
    }

    @XmlElement
    public double getNumberOfTurbinesPerRow() {
        return NumberOfTurbinesPerRow;
    }

    public void setNumberOfTurbinesPerRow(double numberOfTurbinesPerRow) {
        NumberOfTurbinesPerRow = numberOfTurbinesPerRow;
    }

    @XmlElement
    public double getNumberOfTurbines() {
        return NumberOfTurbines;
    }

    public void setNumberOfTurbines(double numberOfTurbines) {
        NumberOfTurbines = numberOfTurbines;
    }

    @XmlElement
    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    @XmlElement
    public double getFarmPowerOutput() {
        return FarmPowerOutput;
    }

    public void setFarmPowerOutput(double farmPowerOutput) {
        FarmPowerOutput = farmPowerOutput;
    }

    @XmlElement
    public List<TurbineSpecs> getTurbines() {
        return turbines;
    }

    public void addTurbine(TurbineSpecs turbine) {
        for (int i = 0; i < turbines.size(); i++) {
            if (turbines.get(i).getTurbineID().equals(turbine.getTurbineID())) {
                turbines.set(i, turbine);
                return;
            }
        }
        turbines.add(turbine);
    }

    @XmlElement
    @XmlJavaTypeAdapter(GeometryAdapter.class)
    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @XmlElement
    public String getWindFarmStatus() {
        return WindFarmStatus;
    }

    public void setWindFarmStatus(String windFarmStatus) {
        WindFarmStatus = windFarmStatus;
    }
}