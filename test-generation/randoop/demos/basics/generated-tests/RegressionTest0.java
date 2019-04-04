import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test01");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) -1, (int) 'a');
        java.lang.Class<?> wildcardClass5 = sumOperator0.getClass();
        int int8 = sumOperator0.sum(100, (int) (byte) 10);
        int int11 = sumOperator0.sum((int) '4', 0);
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 110 + "'", int8 == 110);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 52 + "'", int11 == 52);
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test02");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) -1, (int) 'a');
        java.lang.Class<?> wildcardClass5 = sumOperator0.getClass();
        int int8 = sumOperator0.sum(100, (int) (byte) 10);
        java.lang.Class<?> wildcardClass9 = sumOperator0.getClass();
        int int12 = sumOperator0.sum(96, 110);
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 110 + "'", int8 == 110);
        org.junit.Assert.assertNotNull(wildcardClass9);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 206 + "'", int12 == 206);
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test03");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) 'a', (-1));
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test04");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) 1, (int) (byte) 10);
        int int7 = sumOperator0.sum(52, 96);
        int int10 = sumOperator0.sum(96, 52);
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 11 + "'", int4 == 11);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 148 + "'", int7 == 148);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 148 + "'", int10 == 148);
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test05");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) -1, (int) 'a');
        java.lang.Class<?> wildcardClass5 = sumOperator0.getClass();
        int int8 = sumOperator0.sum(100, (int) (byte) 10);
        int int11 = sumOperator0.sum((-1), (int) (short) 1);
        int int14 = sumOperator0.sum((int) '4', (int) (byte) -1);
        java.lang.Class<?> wildcardClass15 = sumOperator0.getClass();
        int int18 = sumOperator0.sum((int) (short) 1, (int) (byte) 100);
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 110 + "'", int8 == 110);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 51 + "'", int14 == 51);
        org.junit.Assert.assertNotNull(wildcardClass15);
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 101 + "'", int18 == 101);
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test06");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        int int3 = sumOperator0.sum(148, 96);
        java.lang.Class<?> wildcardClass4 = sumOperator0.getClass();
        int int7 = sumOperator0.sum((int) 'a', (int) (short) 100);
        java.lang.Class<?> wildcardClass8 = sumOperator0.getClass();
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 244 + "'", int3 == 244);
        org.junit.Assert.assertNotNull(wildcardClass4);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 197 + "'", int7 == 197);
        org.junit.Assert.assertNotNull(wildcardClass8);
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test07");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum(244, (int) (byte) 100);
        java.lang.Class<?> wildcardClass5 = sumOperator0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 344 + "'", int4 == 344);
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test08");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) -1, (int) 'a');
        java.lang.Class<?> wildcardClass5 = sumOperator0.getClass();
        int int8 = sumOperator0.sum((int) (byte) 0, (int) (byte) 100);
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 100 + "'", int8 == 100);
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test09");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) 1, (int) (byte) 10);
        java.lang.Class<?> wildcardClass5 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass6 = sumOperator0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 11 + "'", int4 == 11);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test10");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) -1, (int) 'a');
        java.lang.Class<?> wildcardClass5 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass6 = sumOperator0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test11");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        int int3 = sumOperator0.sum(87, (int) (byte) 0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 87 + "'", int3 == 87);
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test12");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        int int3 = sumOperator0.sum((int) 'a', 0);
        int int6 = sumOperator0.sum((int) (byte) 1, (int) (byte) 0);
        java.lang.Class<?> wildcardClass7 = sumOperator0.getClass();
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 97 + "'", int3 == 97);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertNotNull(wildcardClass7);
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test13");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass2 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass3 = sumOperator0.getClass();
        int int6 = sumOperator0.sum((int) (byte) 100, 100);
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 200 + "'", int6 == 200);
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test14");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) -1, (int) 'a');
        int int7 = sumOperator0.sum((int) (byte) 100, (int) (byte) 0);
        int int10 = sumOperator0.sum(0, (int) (byte) 1);
        java.lang.Class<?> wildcardClass11 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass12 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass13 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass14 = sumOperator0.getClass();
        int int17 = sumOperator0.sum(0, (int) (short) 0);
        int int20 = sumOperator0.sum((int) (short) 1, 101);
        java.lang.Class<?> wildcardClass21 = sumOperator0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 100 + "'", int7 == 100);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
        org.junit.Assert.assertNotNull(wildcardClass11);
        org.junit.Assert.assertNotNull(wildcardClass12);
        org.junit.Assert.assertNotNull(wildcardClass13);
        org.junit.Assert.assertNotNull(wildcardClass14);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 0 + "'", int17 == 0);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 102 + "'", int20 == 102);
        org.junit.Assert.assertNotNull(wildcardClass21);
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test15");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) -1, (int) 'a');
        int int7 = sumOperator0.sum((int) (byte) 100, (int) (byte) 0);
        int int10 = sumOperator0.sum(0, (int) (byte) 1);
        java.lang.Class<?> wildcardClass11 = sumOperator0.getClass();
        int int14 = sumOperator0.sum((int) '#', 52);
        int int17 = sumOperator0.sum((int) (short) 0, 11);
        java.lang.Class<?> wildcardClass18 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass19 = sumOperator0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 100 + "'", int7 == 100);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
        org.junit.Assert.assertNotNull(wildcardClass11);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 87 + "'", int14 == 87);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 11 + "'", int17 == 11);
        org.junit.Assert.assertNotNull(wildcardClass18);
        org.junit.Assert.assertNotNull(wildcardClass19);
    }

    @Test
    public void test16() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test16");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) -1, (int) 'a');
        java.lang.Class<?> wildcardClass5 = sumOperator0.getClass();
        int int8 = sumOperator0.sum(100, (int) (byte) 10);
        java.lang.Class<?> wildcardClass9 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass10 = sumOperator0.getClass();
        int int13 = sumOperator0.sum((int) (short) 10, 111);
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 110 + "'", int8 == 110);
        org.junit.Assert.assertNotNull(wildcardClass9);
        org.junit.Assert.assertNotNull(wildcardClass10);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 121 + "'", int13 == 121);
    }

    @Test
    public void test17() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test17");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) -1, (int) 'a');
        int int7 = sumOperator0.sum((int) (byte) 100, (int) (byte) 0);
        int int10 = sumOperator0.sum(0, (int) (byte) 1);
        java.lang.Class<?> wildcardClass11 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass12 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass13 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass14 = sumOperator0.getClass();
        int int17 = sumOperator0.sum(0, (int) (short) 0);
        java.lang.Class<?> wildcardClass18 = sumOperator0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 100 + "'", int7 == 100);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
        org.junit.Assert.assertNotNull(wildcardClass11);
        org.junit.Assert.assertNotNull(wildcardClass12);
        org.junit.Assert.assertNotNull(wildcardClass13);
        org.junit.Assert.assertNotNull(wildcardClass14);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 0 + "'", int17 == 0);
        org.junit.Assert.assertNotNull(wildcardClass18);
    }

    @Test
    public void test18() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test18");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) -1, (int) 'a');
        java.lang.Class<?> wildcardClass5 = sumOperator0.getClass();
        int int8 = sumOperator0.sum(100, (int) (byte) 10);
        int int11 = sumOperator0.sum((-1), 52);
        java.lang.Class<?> wildcardClass12 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass13 = sumOperator0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 110 + "'", int8 == 110);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 51 + "'", int11 == 51);
        org.junit.Assert.assertNotNull(wildcardClass12);
        org.junit.Assert.assertNotNull(wildcardClass13);
    }

    @Test
    public void test19() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test19");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        int int3 = sumOperator0.sum(148, 96);
        java.lang.Class<?> wildcardClass4 = sumOperator0.getClass();
        int int7 = sumOperator0.sum(148, 1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 244 + "'", int3 == 244);
        org.junit.Assert.assertNotNull(wildcardClass4);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 149 + "'", int7 == 149);
    }

    @Test
    public void test20() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test20");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) -1, (int) 'a');
        java.lang.Class<?> wildcardClass5 = sumOperator0.getClass();
        int int8 = sumOperator0.sum(100, (int) (byte) 10);
        java.lang.Class<?> wildcardClass9 = sumOperator0.getClass();
        java.lang.Class<?> wildcardClass10 = sumOperator0.getClass();
        int int13 = sumOperator0.sum(1, (int) (short) 1);
        int int16 = sumOperator0.sum(101, (int) (byte) 10);
        int int19 = sumOperator0.sum((int) (byte) -1, (int) (byte) 10);
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 110 + "'", int8 == 110);
        org.junit.Assert.assertNotNull(wildcardClass9);
        org.junit.Assert.assertNotNull(wildcardClass10);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 2 + "'", int13 == 2);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 111 + "'", int16 == 111);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 9 + "'", int19 == 9);
    }

    @Test
    public void test21() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test21");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        int int3 = sumOperator0.sum((int) 'a', 0);
        int int6 = sumOperator0.sum((int) (byte) 1, (int) (byte) 0);
        int int9 = sumOperator0.sum(97, (int) (byte) 10);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 97 + "'", int3 == 97);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 107 + "'", int9 == 107);
    }

    @Test
    public void test22() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test22");
        simpleproject.SumOperator sumOperator0 = new simpleproject.SumOperator();
        java.lang.Class<?> wildcardClass1 = sumOperator0.getClass();
        int int4 = sumOperator0.sum((int) (short) -1, (int) 'a');
        int int7 = sumOperator0.sum((int) (byte) 100, (int) (byte) 0);
        int int10 = sumOperator0.sum(0, (int) (byte) 1);
        java.lang.Class<?> wildcardClass11 = sumOperator0.getClass();
        int int14 = sumOperator0.sum(148, 1);
        int int17 = sumOperator0.sum((int) (short) 10, 197);
        int int20 = sumOperator0.sum((int) (short) 10, 149);
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 96 + "'", int4 == 96);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 100 + "'", int7 == 100);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
        org.junit.Assert.assertNotNull(wildcardClass11);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 149 + "'", int14 == 149);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 207 + "'", int17 == 207);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 159 + "'", int20 == 159);
    }
}

