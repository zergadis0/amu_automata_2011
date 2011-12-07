package pl.edu.amu.wmi.daut.re;

import java.util.List;
import java.util.ArrayList;

    /**
    * Dodaje fabrykę operatorFactory danego operatora. Napis id będzie identyfikatorem (nazwą)
    * operatora. Lista separators będzie listą napisów oddzielających argumenty i parametry,
    * lista ta musi mieć długość równą sumie liczby argumentów i parametrów powiększoną o jeden.
    * Pierwszy i ostatni element listy separators może być napisem pustym. Np. dla operatora
    * `KleeneStarOperator` i tradycyjnego zapisu wyrażeń regularnych separators będzie listą
    * dwuelementową, pierwszym elementem będzie napis pusty, drugim - napis "*". Z kolei dla
    * operatora RangeNumberOfOccurrencesOperator pierwszym elementem będzie napis pusty, drugim -
    * napis "{", trzecim - napis ",", czwartym - napis "}" (operator ma jeden argument i dwa
    * parametry).
    *
    * Identyfikator operatora jest dowolnym napisem, zwykle będziemy jakoś wykorzystywać któryś z
    * separatorów (ale nie jest to obowiązkowe), np. dobrym identyfikatorem KleeneStarOperator
    * będzie gwiazdka.
    *
    * Priority to priorytet operatora.
    */
public class RegexpOperatorManager {

    /**
    * Reprezentuje dane operatora.
    */
    public class operatorFactory {
        private String id;
        private List<String> separators = new ArrayList<String>();
        private int priority;
        private RegexpOperatorFactory operatorFactory;
    }

    private List<operatorFactory> definedOperators= new ArrayList<operatorFactory>();
    private operatorFactory actualOperator;

     /**
     * Dodaje fabrykę operatorFactory danego operatora.
     */
    void addOperator(String id, RegexpOperatorFactory operatorFactory, List<String> separators,
            int priority)
    {
        this.actualOperator.id = id;
        this.actualOperator.priority = priority;
        this.actualOperator.operatorFactory = operatorFactory;
        this.actualOperator.separators.addAll(separators);

        definedOperators.add(actualOperator);
    }

    /**
     * Dodaje fabrykę operatorFactory danego operatora bez podanego priorytetu.
     */
    void addOperator(String id, RegexpOperatorFactory operatorFactory, List<String> separators)
    {
        this.actualOperator.id = id;
        this.actualOperator.priority = 0; // ?????
        this.actualOperator.operatorFactory = operatorFactory;
        this.actualOperator.separators.addAll(separators);

        definedOperators.add(actualOperator);
    }


    /**
     * Zwraca listę separatorów dla operatora o identyfikatorze id.
     */
    List<String> getSeparators(String id)
    {
        List<String> returnedSeparators = new ArrayList<String>();

        for(operatorFactory operator: definedOperators) {
            if (operator.id.equals(id))
                returnedSeparators.addAll(operator.separators);
        }

       if (returnedSeparators.isEmpty())
            return null;
       else return returnedSeparators;
    }


    /**
    * Zwraca fabrykę operatora o identyfikatorze id.
    */
    RegexpOperatorFactory getFactory(String id) {

        RegexpOperatorFactory returned = null;

        for(operatorFactory operator: definedOperators) {
            if (operator.id.equals(id))
                  returned = operator.operatorFactory;
        }

       return returned;
    }


    /**
     * Zwraca priorytet operatora id.
     */
    int getPriority(String id) {
        int returned = -1;

        for(operatorFactory operator: definedOperators) {
            if (operator.id.equals(id))
                 returned = operator.priority;
        }
        return returned;
    }


    /**
    * Zwraca listę identyfikatorów operatorów, których pierwszy separator to najdłuższy prefiks
    * napisu s. Jeśli żaden niepusty prefiks napisu s nie jest pierwszym separatorem, wówczas
    * powinna zwrócić listę wszystkich operatorów, których pierwszy separator jest napisem pustym.
    */
    List<String> getOperatorsForStringPrefix(String s) {
        List<String> returnedId = new ArrayList<String>();
        String firstSeparator;

        for(operatorFactory operator: definedOperators) {
            firstSeparator = operator.separators.get(0);

            if (s.startsWith(firstSeparator))
            {
                if (!firstSeparator.equals("")) {
                    returnedId.add(operator.id);
                }
            }
        }
        if (returnedId.isEmpty()) {
            for(operatorFactory operator: definedOperators) {
                firstSeparator = operator.separators.get(0);

                if (firstSeparator.equals(""))
                    returnedId.add(operator.id);
            }
        }

        return returnedId;
    }
}
