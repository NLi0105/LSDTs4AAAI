package Listener;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelChangedListener;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import java.util.List;

public class ChangeListener implements ModelChangedListener {
    @Override
    public void addedStatement(Statement s) {
        System.out.println(">> added statement " + s);
    }

    @Override
    public void addedStatements(Statement[] statements) {}

    @Override
    public void addedStatements(List<Statement> statements) {}

    @Override
    public void addedStatements(StmtIterator statements) {}

    @Override
    public void addedStatements(Model m) {}

    @Override
    public void removedStatement(Statement s) {
        System.out.println(">> removed statement " + s);
    }

    @Override
    public void removedStatements(Statement[] statements) {}

    @Override
    public void removedStatements(List<Statement> statements) {}

    @Override
    public void removedStatements(StmtIterator statements) {}

    @Override
    public void removedStatements(Model m) {}

    @Override
    public void notifyEvent(Model m, Object event) {
        System.out.println(">> notify event " + event);
    }
}