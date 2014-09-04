package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.behavioralstates.Final;
import pt.rikmartins.uml.statemachine.behavioralstates.SpecialBehavioralState;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class BehavioralState extends Vertex implements Event.Receptor, Vertex.MultipleTransitionSource, Vertex.MultipleTransitionTarget {
    private static final Map<String, Class<? extends SpecialBehavioralState>> reservedBehaviorStates;
    static {
        reservedBehaviorStates = new HashMap<String, Class<? extends SpecialBehavioralState>>();
        reservedBehaviorStates.put("Final", Final.class);
    }

    protected final String name;

    public BehavioralState(Region region, String name) throws UMLStateMachineException {
        super(region);
        if (BehavioralState.reservedBehaviorStates.containsKey(name) && BehavioralState.reservedBehaviorStates.get(name) != this.getClass()){
            throw new UMLStateMachineException("Reserved behavior name, can not use it.");
        }
        this.name = name;
    }

    @Override
    public BehavioralStateSet receiveEvent(Event event) {
        BehavioralStateSet result = new BehavioralStateSet();
        for (BehavioralTransition transition: getOutTransitions()){
            Vertex vertex = transition.transition(event);
            if (vertex != null) {
                try {
                    result.addAll(vertex.activate());
                } catch (UMLStateMachineException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public boolean isActive() {
        BehavioralStateSet algo = region.getCurrentState();
        assert algo == null || algo.size() == 1: "Region returning more than one BehavioralState";
        for (BehavioralState state: algo)
            return region.getCurrentState().equals(this);
        return false;
    }

    @Override
    public BehavioralStateSet activate() throws UMLStateMachineException {
        BehavioralStateSet states = new BehavioralStateSet();
        states.add(this);
        this.region.setCurrentState(states);
        return states;
    }

    @Override
    public String toString() {
        return name;
    }
}
