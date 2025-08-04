package Builder;

import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

public class WindFarmBuilder {
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

    public WindFarmBuilder() {
        this.turbines = new ArrayList<>();
    }

    public WindFarmBuilder setWindFarmID(String WindFarmID) {
        this.WindFarmID = WindFarmID;
        return this;
    }

    public WindFarmBuilder setSite(String Site) {
        this.Site = Site;
        return this;
    }

    public WindFarmBuilder setAEP(double AEP) {
        this.AEP = AEP;
        return this;
    }

    public WindFarmBuilder setTurbineSpacing(double TurbineSpacing) {
        this.TurbineSpacing = TurbineSpacing;
        return this;
    }

    public WindFarmBuilder setNumberOfTurbinesPerRow(double NumberOfTurbinesPerRow) {
        this.NumberOfTurbinesPerRow = NumberOfTurbinesPerRow;
        return this;
    }

    public WindFarmBuilder setNumberOfTurbines(double NumberOfTurbines) {
        this.NumberOfTurbines = NumberOfTurbines;
        return this;
    }

    public WindFarmBuilder setLocation(double Location) {
        this.Location = Location;
        return this;
    }

    public WindFarmBuilder setFarmPowerOutput(double FarmPowerOutput) {
        this.FarmPowerOutput = FarmPowerOutput;
        return this;
    }

    public WindFarmBuilder setTurbines(List<Turbine> turbines) {
        this.turbines = turbines;
        return this;
    }

    public WindFarmBuilder setGeospatial(Geospatial geospatial) {
        this.geospatial = geospatial;
        return this;
    }

    // Geometry
    public WindFarmBuilder setGeometry(Geometry geometry) {
        this.geometry = geometry;
        return this;
    }


    public WindFarm build() {
        WindFarm windFarm = new WindFarm();
        windFarm.setWindFarmID(WindFarmID);
        windFarm.setSite(Site);
        windFarm.setAEP(AEP);
        windFarm.setTurbineSpacing(TurbineSpacing);
        windFarm.setNumberOfTurbinesPerRow(NumberOfTurbinesPerRow);
        windFarm.setNumberOfTurbines(NumberOfTurbines);
        windFarm.setLocation(Location);
        windFarm.setFarmPowerOutput(FarmPowerOutput);
        windFarm.setTurbines(turbines);
        windFarm.setGeospatial(geospatial);

        // Geometry
        windFarm.setGeometry(geometry);

        return windFarm;
    }

}
