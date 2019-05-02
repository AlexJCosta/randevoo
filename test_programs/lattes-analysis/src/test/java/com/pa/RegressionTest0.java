//package com.pa;
//
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runners.MethodSorters;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class RegressionTest0 {
//
//    public static boolean debug = false;
//
//    @Test
//    public void test01() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test01");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        curriculoResult0.setOnGoingOrientations((int) (short) 0);
//    }
//
//    @Test
//    public void test02() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test02");
//        com.pa.analyzer.GroupAnalyzer groupAnalyzer0 = com.pa.analyzer.GroupAnalyzer.getInstance();
//        com.pa.entity.Group group1 = null;
//        java.util.Map<com.pa.util.EnumPublicationLocalType, com.pa.entity.QualisData> enumPublicationLocalTypeMap2 = null;
//        com.pa.analyzer.GroupResult groupResult3 = groupAnalyzer0.groupResult(group1, enumPublicationLocalTypeMap2);
//        org.junit.Assert.assertNotNull(groupAnalyzer0);
//        org.junit.Assert.assertNull(groupResult3);
//    }
//
//    @Test
//    public void test03() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test03");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        int int1 = curriculoResult0.getOnGoingOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult2 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.Orientation> orientationList3 = curriculoResult2.getOrientations();
//        curriculoResult0.setOrientations(orientationList3);
//        java.util.List<com.pa.entity.TechnicalProduction> technicalProductionList5 = curriculoResult0.getTechinicalProductions();
//        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
//        org.junit.Assert.assertNotNull(orientationList3);
//        org.junit.Assert.assertNotNull(technicalProductionList5);
//    }
//
//    @Test
//    public void test04() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test04");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        com.pa.analyzer.GroupResult groupResult1 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = groupResult1.getConferencesByQualis();
//        groupResult0.setPeriodicsByQualis(enumQualisClassificationMap2);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap2);
//    }
//
//    @Test
//    public void test05() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test05");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        int int1 = curriculoResult0.getOnGoingOrientations();
//        int int2 = curriculoResult0.getConcludedOrientations();
//        curriculoResult0.setConcludedOrientations((int) 'a');
//        int int5 = curriculoResult0.getOnGoingOrientations();
//        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
//        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
//        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
//    }
//
//    @Test
//    public void test06() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test06");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        int int1 = curriculoResult0.getOnGoingOrientations();
//        java.util.List<com.pa.entity.Orientation> orientationList2 = curriculoResult0.getOrientations();
//        int int3 = curriculoResult0.getConcludedOrientations();
//        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
//        org.junit.Assert.assertNotNull(orientationList2);
//        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
//    }
//
//    @Test
//    public void test07() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test07");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.TechnicalProduction> technicalProductionList1 = curriculoResult0.getTechinicalProductions();
//        com.pa.analyzer.CurriculoResult curriculoResult2 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.TechnicalProduction> technicalProductionList3 = curriculoResult2.getTechinicalProductions();
//        curriculoResult0.setTechinicalProductions(technicalProductionList3);
//        org.junit.Assert.assertNotNull(technicalProductionList1);
//        org.junit.Assert.assertNotNull(technicalProductionList3);
//    }
//
//    @Test
//    public void test08() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test08");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        int int1 = curriculoResult0.getOnGoingOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult2 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.Orientation> orientationList3 = curriculoResult2.getOrientations();
//        curriculoResult0.setOrientations(orientationList3);
//        int int5 = curriculoResult0.getConcludedOrientations();
//        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
//        org.junit.Assert.assertNotNull(orientationList3);
//        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
//    }
//
//    @Test
//    public void test09() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test09");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        int int1 = curriculoResult0.getOnGoingOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult2 = new com.pa.analyzer.CurriculoResult();
//        int int3 = curriculoResult2.getOnGoingOrientations();
//        java.util.List<com.pa.entity.Orientation> orientationList4 = curriculoResult2.getOrientations();
//        com.pa.analyzer.GroupResult groupResult5 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap6 = groupResult5.getConferencesByQualis();
//        curriculoResult2.setPeriodicsByQualis(enumQualisClassificationMap6);
//        curriculoResult0.setPeriodicsByQualis(enumQualisClassificationMap6);
//        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
//        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
//        org.junit.Assert.assertNotNull(orientationList4);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap6);
//    }
//
//    @Test
//    public void test10() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test10");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.Orientation> orientationList1 = curriculoResult0.getOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult2 = new com.pa.analyzer.CurriculoResult();
//        int int3 = curriculoResult2.getOnGoingOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult4 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.Orientation> orientationList5 = curriculoResult4.getOrientations();
//        curriculoResult2.setOrientations(orientationList5);
//        curriculoResult0.setOrientations(orientationList5);
//        org.junit.Assert.assertNotNull(orientationList1);
//        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
//        org.junit.Assert.assertNotNull(orientationList5);
//    }
//
//    @Test
//    public void test11() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test11");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.Orientation> orientationList1 = curriculoResult0.getOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult2 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.Orientation> orientationList3 = curriculoResult2.getOrientations();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap4 = curriculoResult2.getConferencesByQualis();
//        curriculoResult0.setPeriodicsByQualis(enumQualisClassificationMap4);
//        org.junit.Assert.assertNotNull(orientationList1);
//        org.junit.Assert.assertNotNull(orientationList3);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap4);
//    }
//
//    @Test
//    public void test12() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test12");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getConferencesByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = null;
//        groupResult0.setConferencesByQualis(enumQualisClassificationMap2);
//        java.util.List<com.pa.entity.TechnicalProduction> technicalProductionList4 = groupResult0.getTechinicalProductions();
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertNotNull(technicalProductionList4);
//    }
//
//    @Test
//    public void test13() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test13");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getConferencesByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = groupResult0.getConferencesByQualis();
//        com.pa.analyzer.CurriculoResult curriculoResult3 = new com.pa.analyzer.CurriculoResult();
//        com.pa.analyzer.CurriculoResult curriculoResult4 = new com.pa.analyzer.CurriculoResult();
//        int int5 = curriculoResult4.getOnGoingOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult6 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.Orientation> orientationList7 = curriculoResult6.getOrientations();
//        curriculoResult4.setOrientations(orientationList7);
//        curriculoResult3.setOrientations(orientationList7);
//        groupResult0.setOrientations(orientationList7);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap2);
//        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
//        org.junit.Assert.assertNotNull(orientationList7);
//    }
//
//    @Test
//    public void test14() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test14");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        com.pa.analyzer.CurriculoResult curriculoResult1 = new com.pa.analyzer.CurriculoResult();
//        int int2 = curriculoResult1.getOnGoingOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult3 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.Orientation> orientationList4 = curriculoResult3.getOrientations();
//        curriculoResult1.setOrientations(orientationList4);
//        curriculoResult0.setOrientations(orientationList4);
//        java.util.List<com.pa.entity.TechnicalProduction> technicalProductionList7 = curriculoResult0.getTechinicalProductions();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap8 = curriculoResult0.getConferencesByQualis();
//        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
//        org.junit.Assert.assertNotNull(orientationList4);
//        org.junit.Assert.assertNotNull(technicalProductionList7);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap8);
//    }
//
//    @Test
//    public void test15() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test15");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        com.pa.analyzer.CurriculoResult curriculoResult1 = new com.pa.analyzer.CurriculoResult();
//        int int2 = curriculoResult1.getOnGoingOrientations();
//        java.util.List<com.pa.entity.Orientation> orientationList3 = curriculoResult1.getOrientations();
//        groupResult0.setOrientations(orientationList3);
//        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
//        org.junit.Assert.assertNotNull(orientationList3);
//    }
//
//    @Test
//    public void test16() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test16");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getConferencesByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = null;
//        groupResult0.setConferencesByQualis(enumQualisClassificationMap2);
//        int int4 = groupResult0.getConcludedOrientations();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap5 = groupResult0.getConferencesByQualis();
//        int int6 = groupResult0.getOnGoingOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult7 = new com.pa.analyzer.CurriculoResult();
//        int int8 = curriculoResult7.getOnGoingOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult9 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.Orientation> orientationList10 = curriculoResult9.getOrientations();
//        curriculoResult7.setOrientations(orientationList10);
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap12 = curriculoResult7.getConferencesByQualis();
//        com.pa.analyzer.CurriculoResult curriculoResult13 = new com.pa.analyzer.CurriculoResult();
//        int int14 = curriculoResult13.getOnGoingOrientations();
//        java.util.List<com.pa.entity.Orientation> orientationList15 = curriculoResult13.getOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult16 = new com.pa.analyzer.CurriculoResult();
//        int int17 = curriculoResult16.getOnGoingOrientations();
//        java.util.List<com.pa.entity.Orientation> orientationList18 = curriculoResult16.getOrientations();
//        curriculoResult13.setOrientations(orientationList18);
//        curriculoResult7.setOrientations(orientationList18);
//        groupResult0.setOrientations(orientationList18);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
//        org.junit.Assert.assertNull(enumQualisClassificationMap5);
//        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
//        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
//        org.junit.Assert.assertNotNull(orientationList10);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap12);
//        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
//        org.junit.Assert.assertNotNull(orientationList15);
//        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 0 + "'", int17 == 0);
//        org.junit.Assert.assertNotNull(orientationList18);
//    }
//
//    @Test
//    public void test17() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test17");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getConferencesByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = null;
//        groupResult0.setConferencesByQualis(enumQualisClassificationMap2);
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap4 = groupResult0.getConferencesByQualis();
//        groupResult0.setConcludedOrientations(0);
//        java.util.List<com.pa.entity.TechnicalProduction> technicalProductionList7 = groupResult0.getTechinicalProductions();
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertNull(enumQualisClassificationMap4);
//        org.junit.Assert.assertNotNull(technicalProductionList7);
//    }
//
//    @Test
//    public void test18() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test18");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getConferencesByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = null;
//        groupResult0.setConferencesByQualis(enumQualisClassificationMap2);
//        int int4 = groupResult0.getConcludedOrientations();
//        int int5 = groupResult0.getOnGoingOrientations();
//        groupResult0.setOnGoingOrientations((int) '#');
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
//        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
//    }
//
//    @Test
//    public void test19() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test19");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getConferencesByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = null;
//        groupResult0.setConferencesByQualis(enumQualisClassificationMap2);
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap4 = groupResult0.getPeriodicsByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap5 = groupResult0.getConferencesByQualis();
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap4);
//        org.junit.Assert.assertNull(enumQualisClassificationMap5);
//    }
//
//    @Test
//    public void test20() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test20");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        int int1 = curriculoResult0.getOnGoingOrientations();
//        java.util.List<com.pa.entity.Orientation> orientationList2 = curriculoResult0.getOrientations();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap3 = curriculoResult0.getConferencesByQualis();
//        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
//        org.junit.Assert.assertNotNull(orientationList2);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap3);
//    }
//
//    @Test
//    public void test21() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test21");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getConferencesByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = null;
//        groupResult0.setConferencesByQualis(enumQualisClassificationMap2);
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap4 = groupResult0.getConferencesByQualis();
//        groupResult0.setConcludedOrientations(10);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertNull(enumQualisClassificationMap4);
//    }
//
//    @Test
//    public void test22() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test22");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getConferencesByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = groupResult0.getConferencesByQualis();
//        com.pa.analyzer.GroupResult groupResult3 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap4 = groupResult3.getConferencesByQualis();
//        groupResult0.setConferencesByQualis(enumQualisClassificationMap4);
//        groupResult0.setOnGoingOrientations((int) (short) 1);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap2);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap4);
//    }
//
//    @Test
//    public void test23() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test23");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getConferencesByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = null;
//        groupResult0.setConferencesByQualis(enumQualisClassificationMap2);
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap4 = groupResult0.getConferencesByQualis();
//        groupResult0.setConcludedOrientations(0);
//        int int7 = groupResult0.getConcludedOrientations();
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertNull(enumQualisClassificationMap4);
//        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
//    }
//
//    @Test
//    public void test24() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test24");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getConferencesByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = null;
//        groupResult0.setConferencesByQualis(enumQualisClassificationMap2);
//        com.pa.analyzer.CurriculoResult curriculoResult4 = new com.pa.analyzer.CurriculoResult();
//        com.pa.analyzer.CurriculoResult curriculoResult5 = new com.pa.analyzer.CurriculoResult();
//        int int6 = curriculoResult5.getOnGoingOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult7 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.Orientation> orientationList8 = curriculoResult7.getOrientations();
//        curriculoResult5.setOrientations(orientationList8);
//        curriculoResult4.setOrientations(orientationList8);
//        groupResult0.setOrientations(orientationList8);
//        java.util.List<com.pa.entity.Orientation> orientationList12 = groupResult0.getOrientations();
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
//        org.junit.Assert.assertNotNull(orientationList8);
//        org.junit.Assert.assertNotNull(orientationList12);
//    }
//
//    @Test
//    public void test25() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test25");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        com.pa.analyzer.CurriculoResult curriculoResult1 = new com.pa.analyzer.CurriculoResult();
//        int int2 = curriculoResult1.getOnGoingOrientations();
//        com.pa.analyzer.CurriculoResult curriculoResult3 = new com.pa.analyzer.CurriculoResult();
//        java.util.List<com.pa.entity.Orientation> orientationList4 = curriculoResult3.getOrientations();
//        curriculoResult1.setOrientations(orientationList4);
//        curriculoResult0.setOrientations(orientationList4);
//        com.pa.entity.Orientation[] orientationArray7 = new com.pa.entity.Orientation[] {};
//        java.util.ArrayList<com.pa.entity.Orientation> orientationList8 = new java.util.ArrayList<com.pa.entity.Orientation>();
//        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<com.pa.entity.Orientation>) orientationList8, orientationArray7);
//        curriculoResult0.setOrientations((java.util.List<com.pa.entity.Orientation>) orientationList8);
//        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
//        org.junit.Assert.assertNotNull(orientationList4);
//        org.junit.Assert.assertNotNull(orientationArray7);
//        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
//    }
//
//    @Test
//    public void test26() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test26");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getPeriodicsByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = groupResult0.getPeriodicsByQualis();
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap2);
//    }
//
//    @Test
//    public void test27() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test27");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getConferencesByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = groupResult0.getConferencesByQualis();
//        int int3 = groupResult0.getOnGoingOrientations();
//        java.util.List<com.pa.entity.TechnicalProduction> technicalProductionList4 = groupResult0.getTechinicalProductions();
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap2);
//        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
//        org.junit.Assert.assertNotNull(technicalProductionList4);
//    }
//
//    @Test
//    public void test28() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test28");
//        com.pa.analyzer.GroupResult groupResult0 = new com.pa.analyzer.GroupResult();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap1 = groupResult0.getConferencesByQualis();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap2 = null;
//        groupResult0.setConferencesByQualis(enumQualisClassificationMap2);
//        int int4 = groupResult0.getConcludedOrientations();
//        java.util.Map<com.pa.util.EnumQualisClassification, java.util.List<com.pa.entity.Publication>> enumQualisClassificationMap5 = groupResult0.getConferencesByQualis();
//        java.util.List<com.pa.entity.Orientation> orientationList6 = groupResult0.getOrientations();
//        org.junit.Assert.assertNotNull(enumQualisClassificationMap1);
//        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
//        org.junit.Assert.assertNull(enumQualisClassificationMap5);
//        org.junit.Assert.assertNotNull(orientationList6);
//    }
//
//    @Test
//    public void test29() throws Throwable {
//        if (debug)
//            System.out.format("%n%s%n", "RegressionTest0.test29");
//        com.pa.analyzer.CurriculoResult curriculoResult0 = new com.pa.analyzer.CurriculoResult();
//        int int1 = curriculoResult0.getOnGoingOrientations();
//        int int2 = curriculoResult0.getConcludedOrientations();
//        curriculoResult0.setConcludedOrientations((int) 'a');
//        java.util.List<com.pa.entity.TechnicalProduction> technicalProductionList5 = curriculoResult0.getTechinicalProductions();
//        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
//        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
//        org.junit.Assert.assertNotNull(technicalProductionList5);
//    }
//}
//
