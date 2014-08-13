package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.events.Event;
import pt.rikmartins.uml.statemachine.exceptions.StateMachineException;
import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions.BehavioralState;
import pt.rikmartins.uml.statemachine.vertexes.Vertex;
import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.Final;
import pt.rikmartins.uml.statemachine.vertexes.pseudostates.Initial;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ricardo on 04-08-2014.
 */
public class Region implements Deactivatable, Finalisable, Event.EventReceptor, Stateful {
    private final RegionContainer container;
    private final String name;
    private final Set<Vertex> vertexes;

    private Initial initialState;
    private Final finalState;

    private BehavioralState currentState;

    public Region(RegionContainer container) {
        this(container, "");
    }

    public Region(RegionContainer container, String name) {
        this(container, name, true);
    }

    public Region(RegionContainer container, String name, boolean createInitialState) {
        this(container, name, createInitialState, false);
    }

    public Region(RegionContainer container, String name, boolean createInitialState, boolean createFinalState) {
        this.container = container;
        this.name = name;

        container.registerRegions(this);

        this.vertexes = new HashSet<Vertex>();

        if (createInitialState) {
            try {
                setInitialState(new Initial(this));
            } catch (Vertex.MalformedVertexException e) {
                e.printStackTrace(); // TODO: Properly handle this exception or forward it
            } catch (StateMachineException e) {
                e.printStackTrace(); // TODO: Properly handle this exception or forward it
            }
        } else {
            initialState = null;
        }
        if (createFinalState) {
            try {
                setFinalState(new Final(this));
            } catch (Vertex.MalformedVertexException e) {
                e.printStackTrace(); // TODO: Properly handle this exception or forward it
            } catch (StateMachineException e) {
                e.printStackTrace(); // TODO: Properly handle this exception or forward it
            }
        } else {
            finalState = null;
        }
        currentState = null;
    }

    public Initial getInitialState() {
        return initialState;
    }

    private void setInitialState(Initial initialState) throws StateMachineException {
        if (this.initialState == null) {
            this.initialState = initialState;
        } else {
            throw new StateMachineException("Region already has an initial state");
        }
    }

    public Final getFinalState() {
        return finalState;
    }

    private void setFinalState(Final finalState) throws StateMachineException {
        if (this.finalState == null) {
            this.finalState = finalState;
        } else {
            throw new StateMachineException("Region already has a final state");
        }
    }

    public String getName() {
        return name;
    }

    public RegionContainer getContainer() {
        return container;
    }

    @Override
    public Coiso getCurrentState() {
        return currentState;
    }

    public void setCurrentState(BehavioralState state) throws StateMachineException {
        if ((state == null) || (state.getRegion() == this)) {
            this.currentState = state;
        } else {
            throw new StateMachineException("BehavioralState does not belong to this Region");
        }
    }

    public Set<Vertex> getVertexes() {
        return vertexes;
    }

    public Region registerVertex(Vertex vertex) throws StateMachineException {
        Set<Vertex> vertexes = new HashSet<Vertex>();
        vertexes.add(vertex);
        return registerVertex(vertexes);
    }

    public Region registerVertex(Collection<Vertex> newVertexes) throws StateMachineException {
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
    public Set<BehavioralState> receiveEvent(Event event) {
        if (!isActive()) return null;

        Set<BehavioralState> vertexSet = currentState.receiveEvent(event); // TODO: This should return something
        if (vertexSet.size() <= 0)
            return null;
        currentState = null;
        for (Vertex vertex: vertexSet){
            vertex.activate();
        }

        return null;
    }

    @Override
    public boolean isFinalised() {
        return currentState.equals(finalState);
    }

    @Override
    public Set<BehavioralState> activate() {
        if (isActive() || initialState == null)
            return null;

        Set<BehavioralState> result = initialState.activate();

        for (BehavioralState behavioralState : result)
            behavioralState.activate();

        return result;
    }

    @Override
    public boolean isActive() {
        return currentState == null;
    }

    @Override
    public Set<BehavioralState> deactivate() {
        Set<BehavioralState> result = new HashSet<BehavioralState>();
        result.add(currentState);
        currentState = null;
        return result;
    }
}
//class Region(object):
//    def _can_register_vertexes(self, vertexes):
//        pass
//
//    def propagate_event(self, event=None):
//        states_set = self.current_state.propagate_event(event)
//        if len(states_set) <= 0:
//        return
//        self.current_state = None
//        for state in states_set:
//        state.activate()
//
//    def activate(self, state=None):  # TODO: Deal with forced activations of states, or completely discard it
//        if self.is_active() or self.initial_state is None:
//        return
//        self.current_state = self.initial_state
//        self.propagate_event()
//
//    def deactivate(self):
//        self.current_state = None
//
//    def __str__(self):
//        return self.name
