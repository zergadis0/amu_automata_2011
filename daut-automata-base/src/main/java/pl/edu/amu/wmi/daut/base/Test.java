/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.daut.base;

/**
 *
 * @author Irminka
 */
abstract class Test extends AutomatonSpecification {
    
    @Override
      public AutomatonSpecification clone() {
        AutomatonSpecification mini = new NaiveAutomatonSpecification();
        State q0 = mini.addState();
        mini.insert(q0, this);
        return mini;
    }
}
