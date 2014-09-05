package pt.rikmartins.uml.statemachine;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class PseudoState extends Vertex {
    public PseudoState(Region region) throws UMLStateMachineException {
        super(region);
    }

    @Override
    public final boolean isActive() {
        return false;
    }

    @Override
    public BehavioralStateSet activate() throws UMLStateMachineException {
        BehavioralStateSet result = new BehavioralStateSet();

        for(BehavioralTransition transition : getOutTransitions()){
            result.addAll(transition.transition().activate());
        }
        return result;
    }

    @Override
    protected boolean canRegisterOutTransition(BehavioralTransition transition) {
        if (!super.canRegisterOutTransition(transition))
            return false;
        if (transition.getTriggers().size() > 0)
            return false;
        return true;
    }
}
