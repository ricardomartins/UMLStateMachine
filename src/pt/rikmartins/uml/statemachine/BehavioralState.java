package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.behavioralstates.Final;
import pt.rikmartins.uml.statemachine.behavioralstates.SpecialBehavioralState;
import pt.rikmartins.uml.statemachine.tools.BigStructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class BehavioralState extends Vertex implements Event.EventReceptor, Finalisable {
    private static final Map<String, Class<? extends SpecialBehavioralState>> reservedBehaviorStates;
    static {
        reservedBehaviorStates = new HashMap<String, Class<? extends SpecialBehavioralState>>();
        reservedBehaviorStates.put("Final", Final.class);
    }

    protected final String name;

    public BehavioralState(Region region, String name) throws UMLStateMachineException {
        super(region);
        if (BehavioralState.reservedBehaviorStates.containsKey(name) && BehavioralState.reservedBehaviorStates.get(name) != this.getClass()){
            throw new UMLStateMachineException("Reserved behave name, can not use it.");
        }
        this.name = name;
    }

    @Override
    public BigStructure receiveEvent(Event event) {
        BigStructure result = new BigStructure();
        for (BehavioralTransition transition: getOutTransitions()){
            Vertex vertex = null;
            try {
                vertex = transition.initiateTransition(event);
            } catch (UMLStateMachineException e) {
                e.printStackTrace(); // TODO: Probably something
            }
            if (vertex != null) {
                try {
                    result.addToTransitionRENAME(transition);
                    result.addAll(vertex.activate());
                } catch (UMLStateMachineException e) {
                    e.printStackTrace(); // TODO: Probably something
                }
            }
        }
        return result;
    }

    @Override
    public boolean isActive() {
        Set<BehavioralState> behavioralStateSet = getRegion().getCurrentState();
        assert behavioralStateSet == null || behavioralStateSet.size() == 1: "Region returning more than one BehavioralState"; // TODO: Possibly to remove
        for (BehavioralState state: behavioralStateSet)
            return getRegion().getCurrentState().equals(this);
        return false;
    }

    @Override
    public BigStructure activate() throws UMLStateMachineException {
        BigStructure result = new BigStructure();
        result.addToTheSetRENAME(this);
//        this.getRegion().setCurrentState(result); // Commented because it should be handled elsewhere
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
