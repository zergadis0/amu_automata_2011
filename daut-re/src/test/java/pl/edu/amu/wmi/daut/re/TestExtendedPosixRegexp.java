///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package pl.edu.amu.wmi.daut.re;
//
//import junit.framework.TestCase;
//
///**
// *
// * @author Łukasz
// */
//public class TestExtendedPosixRegexp extends TestCase {
//    /**
//     * Test dla wyrażenia bedącego ciągiem zwykłych znaków.
//     */
//    public final void testForNormalString() {
//        ExtendedPosixRegexp reg = new ExtendedPosixRegexp("kanba");
//        assertTrue(reg.accepts("kanba"));
//        assertFalse(reg.accepts("kamba"));
//        assertFalse(reg.accepts("kanbc"));
//        assertFalse(reg.accepts("kaba"));
//        assertTrue(reg.accepts("kanbaaaaa"));
//        assertTrue(reg.accepts("kanbakanba"));
//        assertFalse(reg.accepts(""));
//    }
//
//    /**
//     * test dla wyrażenia z operatorem wyboru.
//     */
//    public final void testForRegexpWithChoiceOperator() {
//        ExtendedPosixRegexp reg = new ExtendedPosixRegexp("(k|g)ra");
//        assertTrue(reg.accepts("kra"));
//        assertTrue(reg.accepts("gra"));
//        assertFalse(reg.accepts("dra"));
//        assertFalse(reg.accepts("ra"));
//        assertTrue(reg.accepts("kgra"));
//        assertTrue(reg.accepts("grah"));
//        assertFalse(reg.accepts(""));
//    }
//
//    /**
//     * Test dla wyrażenia z operatorem znaku zapytania.
//     */
//    public final void testForRegexpWithQustionOperator() {
//        ExtendedPosixRegexp reg = new ExtendedPosixRegexp("[kg]?ra");
//        assertTrue(reg.accepts("kra"));
//        assertTrue(reg.accepts("gra"));
//        assertFalse(reg.accepts("dra"));
//        assertTrue(reg.accepts("ra"));
//        assertTrue(reg.accepts("kgra"));
//        assertTrue(reg.accepts("grah"));
//        assertFalse(reg.accepts(""));
//    }
//
//    /**
//     * Test dla wyrażenia z trzema lub więcej znakami a lub b na początku.
//     */
//    public final void testForRegexpThreeOrMoreSigns() {
//        ExtendedPosixRegexp reg = new ExtendedPosixRegexp("[ab]{3,}ra");
//        assertTrue(reg.accepts("bbara"));
//        assertTrue(reg.accepts("babara"));
//        assertFalse(reg.accepts("ra"));
//        assertFalse(reg.accepts("bara"));
//        assertFalse(reg.accepts("kdagra"));
//        assertTrue(reg.accepts("babrah"));
//        assertFalse(reg.accepts(""));
//    }
//
//    /**
//     * Test dla wyrażenia z początkiem i końcem.
//     */
//    public final void testForRegexpWithBeginAndEnd() {
//        ExtendedPosixRegexp reg = new ExtendedPosixRegexp("^(ara)$");
//        assertFalse(reg.accepts("bbara"));
//        assertTrue(reg.accepts("ara"));
//        assertFalse(reg.accepts("ra"));
//        assertFalse(reg.accepts("araa"));
//        assertFalse(reg.accepts(""));
//    }
//
//    /**
//     * Test dla wyrażenia z operatorem gwiazdki oraz początkiem i końcem.
//     */
//    public final void testForRegexpWithStarWithBeginAndEnd() {
//        ExtendedPosixRegexp reg = new ExtendedPosixRegexp("^([a-z]*b)$");
//        assertTrue(reg.accepts("bbarzb"));
//        assertFalse(reg.accepts("ara"));
//        assertTrue(reg.accepts("b"));
//        assertFalse(reg.accepts("baa"));
//        assertfalse(reg.accepts("ardsba"));
//        assertFalse(reg.accepts(""));
//    }
//
//    /**
//     * Test dla wyrażenia z operatorem plusa oraz początkiem i końcem.
//     */
//    public final void testForRegexpWithPlusWithBeginAndEnd() {
//        ExtendedPosixRegexp reg = new ExtendedPosixRegexp("^([a-z]+b)$");
//        assertTrue(reg.accepts("bbarzb"));
//        assertFalse(reg.accepts("ara"));
//        assertFalse(reg.accepts("b"));
//        assertfalse(reg.accepts("ardsba"));
//        assertFalse(reg.accepts(""));
//    }
//
//    /**
//     * Test dla wyrażenia z operatorem gwiazdki oraz operatorem ^ oraz początkiem i końcem.
//     */
//    public final void testForRegexpWithoutLettersOtherThanLast() {
//        ExtendedPosixRegexp reg = new ExtendedPosixRegexp("^([^a-z]*b)$");
//        assertTrue(reg.accepts("34432b"));
//        assertFalse(reg.accepts("ara"));
//        assertTrue(reg.accepts("b"));
//        assertTFalse(reg.accepts("dew23b"));
//        assertFalse(reg.accepts("baa"));
//        assertfalse(reg.accepts("ardsba"));
//        assertFalse(reg.accepts(""));
//    }
//
//    /**
//     * Test dla wyrażenia ze znakami specjalnymi.
//     */
//    public final void testForRegexpWithSpecialSigns() {
//        ExtendedPosixRegexp reg = new ExtendedPosixRegexp("\\\\\\sb");
//        assertTrue(reg.accepts("\\ b"));
//        assertFalse(reg.accepts("a b"));
//        assertFalse(reg.accepts("\\sb"));
//        assertfalse(reg.accepts("asda\\ bfsd"));
//        assertFalse(reg.accepts(""));
//    }
//
//    /**
//     * Test dla złożonego wyrażenia 1.
//     */
//    public final void testForLongRegexp1() {
//        ExtendedPosixRegexp reg = new ExtendedPosixRegexp("a+b|a..c");
//        assertTrue(reg.accepts("aaaaab"));
//        assertTrue(reg.accepts("abgc"));
//        assertFalse(reg.accepts("abgca"));
//        assertfalse(reg.accepts("sdb"));
//        assertfalse(reg.accepts("aaaadb"));
//        assertFalse(reg.accepts(""));
//    }
//
//    /**
//     * Test dla złożonego wyrażenia 2.
//     */
//    public final void testForLongRegexp2() {
//        ExtendedPosixRegexp reg = new ExtendedPosixRegexp("^(([a-z]+b|z..c)+[0-9]{5})$");
//        assertTrue(reg.accepts("afdsbzasc98012"));
//        assertFalse(reg.accepts("abgczasc98012"));
//        assertFalse(reg.accepts("afdsbzasc980"));
//        assertTrue(reg.accepts("afdsbafdsbafdsb98012"));
//        assertFalse(reg.accepts("afdsbbb98012"));
//        assertFalse(reg.accepts("bafdsb98012"));
//        assertTrue(reg.accepts("zsdc98012"));
//        assertFalse(reg.accepts(""));
//    }
//}