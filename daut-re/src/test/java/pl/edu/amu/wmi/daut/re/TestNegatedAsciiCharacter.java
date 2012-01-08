package pl.edu.amu.wmi.daut.re;

import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
/**
 *
 * @author adrian
 */
public class TestNegatedAsciiCharacter extends TestCase {

    /**
     * Test klasy znaków alnum.
     */
    public void testAlnumNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^alnum:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts("aZ9"));
    }

    /**
     * Test klasy znaków alpha.
     */
    public void testAlphaNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^alpha:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts("aZ"));
    }

    /**
     * Test klasy znaków blank.
     */
    public void testBlankNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^blank:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts("\t "));
    }

    /**
     * Test klasy znaków cntrl.
     */
    public void testCntrlNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^cntrl:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts("\u0000\u0001"));
    }

    /**
     * Test klasy znaków digit.
     */
    public void testDigitNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^digit:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts("0123456789"));
    }

    /**
     * Test klasy znaków graph.
     */
    public void testGraphNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^graph:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts("az0qwe9!\"#$%&'()*"));
    }

    /**
     * Test klasy znaków lower.
     */
    public void testLowerNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^lower:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts("abcdefghijz"));
    }

    /**
     * Test klasy znaków print.
     */
    public void testPrintNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^print:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts(" az0qwe9!\"#$%&'()*"));
    }

    /**
     * Test klasy znaków punct.
     */
    public void testPunctNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^punct:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts("!-/:-@[-`{-~"));
    }

    /**
     * Test klasy znaków space.
     */
    public void testSpaceNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^space:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts("\t\n\f\r \u000B"));
    }

    /**
     * Test klasy znaków upper.
     */
    public void testUpperNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^upper:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts("ABCDEFZ"));
    }

    /**
     * Test klasy znaków word.
     */
    public void testWordNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^word:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts("Ala_ma_10_kotow"));
    }

    /**
     * Test klasy znaków xdigit.
     */
    public void testXdigitNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("[:^xdigit:]");
       character.createMap();
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts("091AFbc"));
    }
}
