package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.behavioralstates.Final;
import pt.rikmartins.uml.statemachine.pseudostates.Initial;
import pt.rikmartins.uml.statemachine.tools.BigStructure;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ricardo on 04-08-2014.
 */
public class Region implements Stateful, Finalisable, Event.EventReceptor {
    private final RegionContainer container;
    private final String name;
    private final Set<Vertex> vertexes;

    private Initial initialState;
    private Final finalState;

    private BehavioralState currentState;

    public Region(RegionContainer container) throws UMLStateMachineException {
        this(container, "");
    }

    public Region(RegionContainer container, String name) throws UMLStateMachineException {
        this(container, false, false, name);
    }

    public Region(RegionContainer container, boolean createInitialState, boolean createFinalState) throws UMLStateMachineException {
        this(container, createInitialState, createFinalState, "");
    }

    public Region(RegionContainer container, boolean createInitialState, boolean createFinalState, String name) throws UMLStateMachineException {
        this.container = container;
        this.name = name;

        container.registerRegion(this);

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

    public String getName() {
        return name;
    }

    public RegionContainer getContainer() {
        return container;
    }

    public final boolean isNameless(){
        return name.equals("");
    }

    public final Initial getInitialState() {
        return initialState;
    }

    private void setInitialState(Initial initialState) throws UMLStateMachineException {
        if (this.initialState == null) {
            this.initialState = initialState;
        } else {
            throw new UMLStateMachineException("Region already has an initial state, initial state not was not reset.");
        }
    }

    public final Final getFinalState() {
        return finalState;
    }

    private void setFinalState(Final finalState) throws UMLStateMachineException {
        if (this.finalState == null) {
            this.finalState = finalState;
        } else {
            throw new UMLStateMachineException("Region already has a final state, final state not was not reset.");
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
    public BigStructure activate() throws UMLStateMachineException {
        if (isActive())
            throw new UMLStateMachineException("Trying to activate an already active Region, nothing was changed.");
        if (initialState == null)
            return new BigStructure();

        return initialState.activate();
    }

    @Override
    public Set<BehavioralState> deactivate() throws UMLStateMachineException {
        // TODO: Determine whether this function should return something
        if (!isActive())
            throw new UMLStateMachineException("Trying to deactivate an already inactive " + this.getClass().getName() + ". nothing was changed.");

        currentState = null;
        return new HashSet<BehavioralState>();
    }

    @Override
    public Set<BehavioralState> getCurrentState() {
        Set<BehavioralState> result = new HashSet<BehavioralState>();
        if (!isActive()) return result;

        result.add(currentState);
        return result;
    }

    @Override
    public Set<BehavioralState> setCurrentState(Set<BehavioralState> behavioralStates) throws UMLStateMachineException {
        assert behavioralStates.size() == 1 : "Multiple state activation not supported yet.";
        for (BehavioralState state: behavioralStates){
            if (!state.getRegion().equals(this))
                throw new UMLStateMachineException("Trying to activate a foreign \"" + state.name + "\" BehavioralState in \"" + name + "\" Region");
            currentState = state;
        }
        Set<BehavioralState> result = new HashSet<BehavioralState>();
        result.add(currentState);
        return result;
    }

    @Override
    public BigStructure receiveEvent(Event event) {
        BigStructure bigStructure = new BigStructure();
        if (!isActive())
            return bigStructure;

        bigStructure.addAll(currentState.receiveEvent(event));
        return bigStructure;
    }

    @Override
    public boolean isFinalised() {
        return currentState != null && currentState.equals(finalState);
    }
}
