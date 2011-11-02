package pl.edu.amu.wmi.daut.base;

import java.util.List;

public class AutomataOperations {
    /*
     * Operacje na Automatach
     * 
     * @return Przeciecie , sume ... jezykow akceptowalnych przez
automaty
     */
    public AutomatonSpecification sum(AutomatonSpecification A,
AutomatonSpecification B) 
	{
		/*
		* stworzenie nowego stanu poczatkowego i koncowego
		* oraz powiazan nowego stanu poczatkowego 
		*/
		AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        {
            State q0 = automaton.addState();
        	State qk = automaton.addState();
			automaton.markAsInitial(q0);
        	automaton.markAsFinal(qk);

//tu trzeba przekopiowac caly automat A oraz automat B to automatu
//automaton
//wszystkie stany i przejscia pomiedzy nimi, trzeba tez zadbać o to zeby
// kolizji z nazwami stanów pomiędzy automatem A i B
// bo jak ostatecznie dodamy oba do siebie to moga sie dublować stany i
//pamietaj tez ze jesli dodajesz przejscie w automacie (np tak jak
//powyżej) to stan poczatkowy i konczowy powinien juz byc zdefiniowany w
//danym automacie. poniżej oczywiscie tak niejest, automat automaton
//nieposiada stanu A.getInitialState(), to jest stan z automatu A, Trzeba
//pierw dodać nowy stan to automatu automaton, ktoru bedzie reprezentował
// stan i porzystac  z niego.

// Napisze to mniej wiecej jak to powinno działać  ale idea bedzie mniej
//wicej poprawna.

Map<State, State> statesA = new HashMap<State,State>();
Map<State, State> statesB = new HashMap<State,State>();
//kopiuje stany i tworze mapowanie pomidzy nowymi stanami a starymi
for(State s:A.allStates()){
State tmp = automaton.addState();
statesA.put(s,tmp);
}
for(State s:B.allStates()){
State tmp = automaton.addState();
statesB.put(s,tmp);
}
//kopiuje przejscia pomiedzy starymi stanami z automatu A do nowych
//stantów w nowym automacie automaton
for(State s: A.allStates()){
	for(OutgoingTransition ot : allOutgoingTransitions(s)){

automaton.addTransition(statesA.get(ot),ot.getTargetState(),getTransitionLabel());
	}
}
//kopiuje przejscia pomiedzy starymi stanami z automatu B do nowych
//stantów w nowym automacie automaton
for(State s: B.allStates()){
	for(OutgoingTransition ot : allOutgoingTransitions(s)){

automaton.addTransition(statesB.get(ot),ot.getTargetState(),getTransitionLabel());
	}
}

//to by bylo na tyle, mamy nowy automat z kopiami starych stanów, oraz
//kopiami przejsc pomiedzy nimi, na koniec trzeba dodac przejscia
//poczatkowe i konczowe tak jak to robiles tylko pomiedzy zmapowanymi
//stanami.

// to powinno byc
//eplison przejscie
		automaton.addTransition(q0, statesA.get(A.getInitialState()) /*stanpoczatkowy automatu A*/, new TestTransition(''));
		automaton.addTransition(q0, statesB.get(B.getInitialState()) /*stanpoczatkowy automatu B*/, new TestTransition(''));
		automaton.addTransition(statesA.get(A.getFinalState()) /*stan koncowyautomatu A*/,qk, new TestTransition(''));
		automaton.addTransition(statesB.get(B.getFinalState()) /*stan koncowyautomatu B*/,qk, new TestTransition(''));
        }

        return automaton;
			
		
    }

}