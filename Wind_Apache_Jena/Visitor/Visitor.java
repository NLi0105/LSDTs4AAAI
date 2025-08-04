package Visitor;


import Builder.Geospatial;
import Builder.Turbine;
import Builder.WindFarm;
import Builder.WindResource;

public interface Visitor {
    void visit(Turbine turbine);
    void visit(WindFarm windFarm);
    void visit(Geospatial geospatial);
    void visit(WindResource windResource);
}
