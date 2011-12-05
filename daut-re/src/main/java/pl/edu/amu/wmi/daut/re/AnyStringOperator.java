package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.State;
import pl.edu.amu.wmi.daut.base.AnyTransitionLabel;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;

/**
 * Reprezentuje dowolny napis, dowolnej długości (także pusty).
 * Odpowiada to wyrażeniu regularnego '.*'.
 */
public class AnyStringOperator extends NullaryRegexpOperator {
    @Override
    public final AutomatonSpecification createFixedAutomaton() {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        State qs = spec.addState();
        State qf = spec.addState();

        spec.markAsInitial(qs);
        spec.markAsFinal(qf);

        spec.addTransition(qs, qf, new AnyTransitionLabel());

        // return new KleeneStarOperator().createAutomatonFromOneAutomaton(spec);
        return spec;
    }
}
