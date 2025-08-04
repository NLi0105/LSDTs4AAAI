package Listener;

import org.apache.jena.rdf.listeners.StatementListener;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class HurricaneListener_backup extends StatementListener {

    private static final String NS = "http://windfarm/";
    private static final String NS_EVENT = NS + "Event/";
    private static final String NS_WEATHER_EVENT = NS_EVENT + "WeatherEvent/";
    private static final String NS_HURRICANE = NS_WEATHER_EVENT + "Hurricane#";
    private static final String NS_TURBINE = NS + "Turbine#";
    private static final String NS_WIND_FARM = NS + "WindFarm#";

    private final Model model;
    private final Property hasStatus;
    private final Property hasTurbineStatus;
    private final Property hasTurbineType;
    private final Property hasMaxWindSpeed;
    private final Property hasCutOutWindSpeed;
    private final Property hasPitchAngle;
    private final Property hasWindFarmStatus;
    private final Property hasArrivalTime;
    private final Property hasCurrentTime;
    private final Resource hurricaneClass;

    // Track resources we've already processed
    private final Set<String> processedStatuses = new HashSet<>();

    public HurricaneListener_backup(Model model) {
        this.model = model;
        this.hasStatus = model.getProperty(NS_EVENT + "hasStatus");
        this.hasTurbineStatus = model.getProperty(NS_TURBINE + "hasTurbineStatus");
        this.hasTurbineType = model.getProperty(NS_TURBINE + "hasTurbineType");
        this.hasMaxWindSpeed = model.getProperty(NS_HURRICANE + "hasMaxWindSpeed");
        this.hasCutOutWindSpeed = model.getProperty(NS_TURBINE + "hasCutOutWindSpeed");
        this.hasPitchAngle = model.getProperty(NS_TURBINE + "hasPitchAngle");
        this.hasWindFarmStatus = model.getProperty(NS_WIND_FARM + "hasWindFarmStatus");
        this.hasArrivalTime = model.getProperty(NS_HURRICANE + "hasArrivalTime");
        this.hasCurrentTime = model.getProperty(NS + "Time#hasCurrentTime");
        this.hurricaneClass = model.getResource(NS_HURRICANE);
    }

    @Override
    public void addedStatement(Statement statement) {
        Resource subject = statement.getSubject();
        Property predicate = statement.getPredicate();

        // Only process hurricanes when the maximum wind speed is added
        if (isHurricaneResource(subject) && predicate.equals(hasMaxWindSpeed)) {
            // Process each hurricane only once based on its URI
            String hurricaneURI = subject.getURI();
            if (!processedStatuses.contains(hurricaneURI)) {
                processedStatuses.add(hurricaneURI);
                System.out.println("======== HURRICANE ALERT ========");
                System.out.println("Detected hurricane with wind speed data: " + hurricaneURI);

                // Check if hurricane is active before taking action
                if (isHurricaneActive(subject)) {
                    System.out.println("Hurricane is active - evaluating impact on turbines and wind farm");
                    evaluateWindSpeedAndAdjustTurbines(subject);
                } else {
                    System.out.println("Hurricane is not active - no action required");
                    System.out.println("===============================================");
                }
            }
        }
    }

    private boolean isHurricaneActive(Resource hurricane) {
        Statement statusStmt = hurricane.getProperty(hasStatus);
        if (statusStmt != null && statusStmt.getObject().isLiteral()) {
            String status = statusStmt.getObject().asLiteral().getString();
            return !status.equals("Not Active");
        }
        // If status is not specified, default to treating it as active
        return true;
    }

    private boolean isHurricaneResource(Resource resource) {
        return resource.hasProperty(RDF.type, hurricaneClass);
    }

    private boolean isArrivalTimeWithin24Hours(Resource hurricane) {
        Statement arrivalTimeStmt = hurricane.getProperty(hasArrivalTime);
        StmtIterator currentTimeStmts = model.listStatements(null, hasCurrentTime, (RDFNode) null);

        if (arrivalTimeStmt != null && arrivalTimeStmt.getObject().isLiteral() && currentTimeStmts.hasNext()) {
            try {
                String arrivalTimeStr = arrivalTimeStmt.getObject().asLiteral().getString();
                String currentTimeStr = currentTimeStmts.nextStatement().getObject().asLiteral().getString();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date arrivalTime = sdf.parse(arrivalTimeStr);
                Date currentTime = sdf.parse(currentTimeStr);

                long diffInMillies = arrivalTime.getTime() - currentTime.getTime();
                long diffInHours = diffInMillies / (1000 * 60 * 60);

                System.out.println("Arrival Time: " + arrivalTime);
                System.out.println("Current Time: " + currentTime);
                System.out.println("Difference in Hours: " + diffInHours);

                return diffInHours <= 24;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void evaluateWindSpeedAndAdjustTurbines(Resource hurricane) {
        // Get hurricane's maximum wind speed
        double hurricaneMaxWindSpeed = 0.0;
        Statement maxWindSpeedStmt = hurricane.getProperty(hasMaxWindSpeed);

        if (maxWindSpeedStmt == null || !maxWindSpeedStmt.getObject().isLiteral()) {
            System.out.println("Hurricane has no valid maximum wind speed information.");
            return;
        }

        hurricaneMaxWindSpeed = maxWindSpeedStmt.getObject().asLiteral().getDouble();
        System.out.println("Hurricane Max Wind Speed: " + hurricaneMaxWindSpeed);

        System.out.println("Evaluating each offshore turbine against cut-out wind speed...");

        // Find all turbines
        ResIterator turbineIter = model.listSubjectsWithProperty(hasTurbineType);
        int shutdownCount = 0;
        int safeCount = 0;
        int featheredCount = 0;

        while (turbineIter.hasNext()) {
            Resource turbine = turbineIter.nextResource();
            Statement cutOutWindSpeedStmt = turbine.getProperty(hasCutOutWindSpeed);

            if (cutOutWindSpeedStmt != null && cutOutWindSpeedStmt.getObject().isLiteral()) {
                double cutOutWindSpeed = cutOutWindSpeedStmt.getObject().asLiteral().getDouble();

                if (hurricaneMaxWindSpeed > cutOutWindSpeed) {
                    // If hurricane wind speed exceeds the cut-out wind speed, shutdown the turbine
                    updateTurbineStatus(turbine, "Shut down");

                    // Set pitch angle to 90 degrees (feathered)
                    updateTurbinePitchAngle(turbine, 90.0);

                    shutdownCount++;
                    featheredCount++;
                } else {
                    // If the hurricane wind speed is below the cut-out wind speed, turbine is safe
                    updateTurbineStatus(turbine, "Operational");

                    safeCount++;
                }
            }
        }

        // Shut down the wind farm if the arrival time is within 24 hours
        if (isArrivalTimeWithin24Hours(hurricane)) {
            updateWindFarmStatus("Shut down");
            System.out.println("Shut down wind farm.");
        } else {
            System.out.println("Wind farm not shut down - arrival time is not within 24 hours.");
        }

        System.out.println("Hurricane response complete: " + shutdownCount + " turbines shut down, " +
                safeCount + " turbines safe");
        System.out.println("Blade feathering complete: " + featheredCount + " turbines with feathered blades");
        System.out.println("===============================================");
    }

    private void updateTurbineStatus(Resource turbine, String newStatus) {
        // Remove any existing status
        turbine.removeAll(hasTurbineStatus);
        // Add the new status
        turbine.addProperty(hasTurbineStatus, newStatus);
    }

    private void updateTurbinePitchAngle(Resource turbine, double pitchAngle) {
        // Remove any existing pitch angle
        turbine.removeAll(hasPitchAngle);
        // Add the new pitch angle
        turbine.addProperty(hasPitchAngle, model.createTypedLiteral(pitchAngle));
    }

    private void updateWindFarmStatus(String newStatus) {
        // Find the wind farm resource
        ResIterator windFarmIter = model.listSubjectsWithProperty(hasWindFarmStatus);
        while (windFarmIter.hasNext()) {
            Resource windFarm = windFarmIter.nextResource();
            // Remove any existing status
            windFarm.removeAll(hasWindFarmStatus);
            // Add the new status
            windFarm.addProperty(hasWindFarmStatus, newStatus);
        }
    }
}