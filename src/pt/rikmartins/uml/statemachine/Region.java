package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.behavioralstates.Final;
import pt.rikmartins.uml.statemachine.pseudostates.Initial;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ricardo on 04-08-2014.
 */
public class Region implements Stateful, Finalisable, Event.Receptor {
    protected final RegionContainer container;
    protected final String name;
    private final Set<Vertex> vertexes;

    private Initial initialState;
    private Final finalState;

    private BehavioralState currentState;

    public Region(RegionContainer container) throws UMLStateMachineException {
        this(container, "");
    }

    public Region(RegionContainer container, String name) throws UMLStateMachineException {
        this(container, name, true);
    }

    public Region(RegionContainer container, String name, boolean createInitialState) throws UMLStateMachineException {
        this(container, name, createInitialState, false);
    }

    public Region(RegionContainer container, String name, boolean createInitialState, boolean createFinalState) throws UMLStateMachineException {
        this.container = container;
        this.name = name;

        container.registerRegions(this);

        this.vertexes = new HashSet<Vertex>();

        if (createInitialState) {
            new Initial(this);
        } else {
            initialState = null;
        }
        if (createFinalState) {
            new Final(this);
        } else {
            finalState = null;
        }
        currentState = null;
    }

    public final Initial getInitialState() {
        return initialState;
    }

    private void setInitialState(Initial initialState) throws UMLStateMachineException {
        if (this.initialState == null) {
            this.initialState = initialState;
        } else {
            throw new UMLStateMachineException("Region already has an initial state");
        }
    }

    public final Final getFinalState() {
        return finalState;
    }

    private void setFinalState(Final finalState) throws UMLStateMachineException {
        if (this.finalState == null) {
            this.finalState = finalState;
        } else {
            throw new UMLStateMachineException("Region already has a final state");
        }
    }

    public final Set<Vertex> getVertexes() {
        Set<Vertex> result = new HashSet<Vertex>(vertexes);
        return result;
    }

    Region registerVertex(Vertex vertex) throws UMLStateMachineException {
        Set<Vertex> vertexes = new HashSet<Vertex>();
        vertexes.add(vertex);
        return registerVertexes(vertexes);
    }

    Region registerVertexes(Collection<Vertex> newVertexes) throws UMLStateMachineException {
        for (Vertex vertex : newVertexes) {
            if (vertex instanceof Initial) {
                setInitialState((Initial) vertex);
            }
            if (vertex instanceof Final) {
                setFinalState((Final) vertex);
            }
        }
        this.vertexes.addAll(newVertexes);
        return this;
    }

    @Override
    public boolean isActive() {
        return currentState != null;
    }

    @Override
    public BehavioralStateSet activate() throws UMLStateMachineException {
        if (isActive())
            throw new UMLStateMachineException("Trying to activate an already active Region");
        if (initialState == null)
            throw new UMLStateMachineException("Trying to activate a Region that has no Initial state");

        return initialState.activate();
    }

    @Override
    public BehavioralStateSet deactivate() throws UMLStateMachineException {
        if (!isActive())
            throw new UMLStateMachineException("Trying to deactivate an already inactive Region");

        BehavioralStateSet result = new BehavioralStateSet();
        result.add(currentState);
        currentState = null;
        return result;
    }

    @Override
    public BehavioralStateSet getCurrentState() {
        if (!isActive()) return null;

        BehavioralStateSet result = new BehavioralStateSet();
        result.add(currentState);
        return result;
    }

    @Override
    public BehavioralStateSet setCurrentState(BehavioralStateSet behavioralStates) throws UMLStateMachineException {
        assert behavioralStates.size() == 1 : "Multiple state activation not supported yet.";
        for (BehavioralState state: behavioralStates){
            if (!state.getRegion().equals(this))
                throw new UMLStateMachineException("Trying to activate a foreign \"" + state.name + "\" BehavioralState in \"" + name + "\" Region");
            currentState = state;
        }
        BehavioralStateSet result = new BehavioralStateSet();
        result.add(currentState);
        return result;
    }

    @Override
    public BehavioralStateSet receiveEvent(Event event) {
        if (!isActive()) return null;

        BehavioralStateSet vertexSet = currentState.receiveEvent(event);
        if (vertexSet.size() <= 0)
            return null;

        return vertexSet;
    }

    @Override
    public boolean isFinalised() {
        return currentState.equals(finalState);
    }
}
