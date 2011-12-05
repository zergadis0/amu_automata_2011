package pl.edu.amu.wmi.daut.re;

import java.util.List;
import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;

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
    public class AddedOperator {
        private String id;
        private List<String> separators = new ArrayList<String>(); // ? kla kazdego operatora osobna lista
        private int priority;
        private RegexpOperatorFactory operatorFactory;
    }

    private List<AddedOperator> definedOperators= new ArrayList<AddedOperator>();// wszystkie dodane operatory
    private AddedOperator actualOperator;

     /**
     * Dodaje fabrykę operatorFactory danego operatora.
     */
    void addOperator(String id, RegexpOperatorFactory operatorFactory, List<String> separators,
            int priority)
    {
        actualOperator.id = id;
        actualOperator.priority = priority;
        actualOperator.operatorFactory = operatorFactory;
        actualOperator.separators.addAll(separators);

        definedOperators.add(actualOperator);
    }

    /**
     * Dodaje fabrykę operatorFactory danego operatora bez podanego priorytetu.
     */
    void addOperator(String id, RegexpOperatorFactory operatorFactory, List<String> separators)
    {
        actualOperator.id = id;
        actualOperator.priority = 0; // ?????
        actualOperator.operatorFactory = operatorFactory;
        actualOperator.separators.addAll(separators);

        definedOperators.add(actualOperator);
    }


    /**
     * Zwraca listę separatorów dla operatora o identyfikatorze id.
     */
    List<String> getSeparators(String id)
    {
        List<String> returnedSeparators = new ArrayList<String>();

        for(AddedOperator operator: definedOperators) {
            if (operator.id.equals(id))
                returnedSeparators.addAll(operator.separators);
        }

       return returnedSeparators; // jesli id nie istnieje zwraca pusta liste

      // if (returnedSeparators.isEmpty())
      //  return null;
    }


    /**
    * Zwraca fabrykę operatora o identyfikatorze id.
    */
    RegexpOperatorFactory getFactory(String id) {

        RegexpOperatorFactory returned = null;

        for(AddedOperator operator: definedOperators) {
            if (operator.id.equals(id))
                  returned = operator.operatorFactory;
        }
        // ??
       return returned; // jesli id nie istnieje zwraca null

    }


    /**
     * Zwraca priorytet operatora id.
     */
    int getPriority(String id) {
        int returned = -1;

        for(AddedOperator operator: definedOperators) {
            if (operator.id.equals(id))
                 returned = operator.priority;
        }
      //  if (returned != -1)
        return returned;
      //  else return 0; // ?? jeśli id nie istnieje to co zwracać ??
    }


    /**
    * Zwraca listę identyfikatorów operatorów, których pierwszy separator to najdłuższy prefiks
    * napisu s. Jeśli żaden niepusty prefiks napisu s nie jest pierwszym separatorem, wówczas
    * powinna zwrócić listę wszystkich operatorów, których pierwszy separator jest napisem pustym.
    */
    List<String> getOperatorsForStringPrefix(String s) {
        List<String> returnedId = new ArrayList<String>();
        String firstSeparator;
       // int lenghtOfPrefix = 0, maxLenghtOfPrefix = 0;

        for(AddedOperator operator: definedOperators) {
            firstSeparator = operator.separators.get(0);

                if (s.startsWith(firstSeparator))// jeśli separator to cos (lub "") ??
                {
                    if (!firstSeparator.equals("")) { // ?? jeśli pierwszy separator = "" to dodawac do zwracanych ??
                        returnedId.add(operator.id);

                        /*for (char symbol : s.toCharArray()) { // ?? o ktora wersje chodzi ??
                                        // ?? "których pierwszy separator to najdłuższy prefiks" ??

                            if (symbol == firstSeparator.toCharArray()[0])
                                lenghtOfPrefix++;

                            if(lenghtOfPrefix > maxLenghtOfPrefix) {
                                maxLenghtOfPrefix = lenghtOfPrefix;
                                returnedId.clear();
                                returnedId.add(operator.id);
                            }

                            if(lenghtOfPrefix == maxLenghtOfPrefix)
                                returnedId.add(operator.id);
                        }*/
                    }
                }
        }
        if (returnedId.isEmpty()) {
            for(AddedOperator operator: definedOperators) {
                firstSeparator = operator.separators.get(0);

                if (firstSeparator.equals(""))
                    returnedId.add(operator.id);
            }
        }

        return returnedId;
    }
}
