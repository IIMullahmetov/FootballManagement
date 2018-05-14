package utilities.verifications;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utilities.logger.Report;

import java.util.*;

public class Verification {
    public static void VerifyValue(String sMessage, String sExpected, String sActual) {
        if (!sExpected.trim().equalsIgnoreCase(sActual.trim())) {
            String sMSG = "Verification Failed ==>" + sMessage + " -> Expected = " + sExpected
                + " Actual Result = " + sActual;
            Report.logError(sMSG);

        }
        Assert.assertTrue(sExpected.trim().equalsIgnoreCase(sActual.trim()), sMessage);
        sMessage = "Verification Passed ==>" + sMessage + " -> Expected = " + sExpected + " Actual Result = " + sActual;
        Report.logPass(sMessage);
    }

    public static void VerifyValuePresent(String sMessage, String sExpected, String sActual) {
        if (!sActual.trim().contains(sExpected.trim())) {
            String sMSG = "Verification Failed ==>" + sMessage + " -> Expected = " + sExpected
                + " Actual Result = " + sActual;
            Report.logError(sMSG);
        }
        Assert.assertTrue(sActual.trim().contains(sExpected.trim()), sMessage);
        sMessage = "Verification Passed ==>" + sMessage + " -> Expected = " + sExpected + " Actual Result = " + sActual;
        Report.logPass(sMessage);
    }

    public static void VerifyValue(String sMessage, boolean bExpCondition, boolean bActCondition) {
        if (!(bExpCondition == bActCondition)) {
            String sMSG = "Verification Failed ==>" + sMessage + " -> Expected = " + bExpCondition
                + " Actual Result = " + bActCondition;
            Report.logError(sMSG);
        }
        Assert.assertTrue(bExpCondition == bActCondition, sMessage);
        sMessage = "Verification Passed ==>" + sMessage + " -> Expected = " + bExpCondition
            + " Actual Result = " + bActCondition;
        Report.logPass(sMessage);
    }

    public static void VerifyValue(String sMessage, boolean bCondition) {
        if (!bCondition) {
            Report.logError("Verification Failed ==>" + sMessage + " -> Expected = " + false);
        }
        Assert.assertTrue(bCondition, sMessage);
        Report.logPass("Verification Passed ==>" + sMessage + " -> Expected = " + true);
    }

    public static void VerifySort(List<String> lsOriginal, SoftAssert sAssert) {
        List<String> seqSorted = new ArrayList<>();
        seqSorted.addAll(lsOriginal);
        Collections.sort(seqSorted);
        Iterator<String> sOriginalText = lsOriginal.iterator();
        Iterator<String> sSortedText = seqSorted.iterator();
        boolean bSorted = true;
        while (sOriginalText.hasNext()) {
            if (!sOriginalText.next().equals(sSortedText.next())) {
                bSorted = false;
                break;
            }
        }
        Verification.softVerifyValue("Data is in Sorted Order", bSorted, sAssert);
    }

    public static void VerifyValue(String sMessage, int ExpValue, int ActValue) {
        if (ExpValue != ActValue) {
            Report.logError("Verification Failed ==>" + sMessage + " -> Expected = " + ExpValue
                + " Actual Result = " + ActValue);
        }
        Assert.assertTrue(ExpValue == ActValue, sMessage);
        Report.logPass("Verification Passed ==>" + sMessage + " -> Expected = " + ExpValue
            + " Actual Result = " + ActValue);
    }

    public static boolean verifyValue(String sMessage, boolean bExpCondition, boolean bActCondition) {
        Boolean bFail = false;
        if (!(bExpCondition == bActCondition)) {
            bFail = true;
            Report.logError("Verification Failed ==>" + sMessage + " -> Expected = " + bExpCondition
                + " Actual Result = " + bActCondition);
        } else {
            Report.logPass("Verification Passed ==>" + sMessage + " -> Expected = " + bExpCondition
                + " Actual Result = " + bActCondition);
        }
        return bFail;
    }

    public static boolean verifySortDesc(List<Integer> lsOriginal) {
        List<Integer> seqSorted = new ArrayList<>();
        seqSorted.addAll(lsOriginal);

        Comparator<Integer> comparator = Collections.reverseOrder();
        Collections.sort(seqSorted, comparator);

        Iterator<Integer> iOriginalValue = lsOriginal.iterator();
        Iterator<Integer> iSortedValue = seqSorted.iterator();
        boolean bSorted = true;
        while (iOriginalValue.hasNext()) {
            if (!Objects.equals(iOriginalValue.next(), iSortedValue.next())) {
                bSorted = false;
                break;
            }
        }

        if (!bSorted) {
            Report.logError("Verification Failed ==> Data is not in descending sorted order");
            Assert.fail();
        } else {
            Report.logPass("Verification Passed ==> Data is in descending sorted order");
        }
        return false;
    }

    // soft assert cases
    public static void softVerifyValue(String sMessage, String sExpected, String sActual, SoftAssert sAssert) {
        boolean bEqual = sExpected.trim().equalsIgnoreCase(sActual.trim());
        if (!bEqual) {
            Report.logError("Verification Failed ==>" + sMessage + " -> Expected = '" + sExpected
                + "' Actual Result = '" + sActual + "'.");

        } else {
            Report.logPass("Verification Passed ==>" + sMessage + " -> Expected = " + sExpected
                + " Actual Result = " + sActual);
        }
        sAssert.assertEquals(bEqual, true, sMessage);
    }

    public static void softVerifyValuePresent(String sMessage, String sExpected, String sActual,
                                              SoftAssert sAssert) {
        boolean bContains = sActual.trim().contains(sExpected.trim());
        if (!bContains) {
            Report.logError("Verification Failed ==>" + sMessage + " -> Expected = " + sExpected
                + " Actual Result = " + sActual);
        } else {
            Report.logPass("Verification Passed ==>" + sMessage + " -> Expected = " + sExpected
                + " Actual Result = " + sActual);
        }
        sAssert.assertEquals(bContains, true, sMessage);
    }

    public static void softVerifyValuePresentIgnoreCase(String sMessage, String sExpected,
                                                        String sActual, SoftAssert sAssert) {
        boolean bContains = StringUtils.containsIgnoreCase(sActual.trim(), sExpected.trim());
        if (!bContains) {
            Report.logError("Verification Failed ==>" + sMessage + " -> Expected = " + sExpected
                + " Actual Result = " + sActual);
        } else {
            Report.logPass("Verification Passed ==>" + sMessage + " -> Expected = " + sExpected
                + " Actual Result = " + sActual);
        }
        sAssert.assertEquals(bContains, true, sMessage);
    }

    public static void softVerifyValue(String sMessage, boolean bExpCondition,
                                       boolean bActCondition, SoftAssert sAssert) {
        if (!(bExpCondition == bActCondition)) {
            Report.logError("Verification Failed ==>" + sMessage + " -> Expected = " + bExpCondition
                + " Actual Result = " + bActCondition);
        } else {
            Report.logPass("Verification Passed ==>" + sMessage + " -> Expected = " + bExpCondition
                + " Actual Result = " + bActCondition);
        }
        sAssert.assertEquals(bActCondition, bExpCondition, sMessage);
    }

    public static void softVerifyValue(String sMessage, boolean bCondition, SoftAssert sAssert) {
        if (!bCondition) {
            Report.logError("Verification Failed ==>" + sMessage + " -> Expected = " + false);
        } else {
            Report.logPass("Verification Passed ==>" + sMessage + " -> Expected = " + true);
        }
        sAssert.assertEquals(bCondition, true, sMessage);
    }

    public static void softVerifyValue(String sMessage, int ExpValue, int ActValue, SoftAssert sAssert) {
        if (ExpValue != ActValue) {
            Report.logError("Verification Failed ==>" + sMessage + " -> Expected = " + ExpValue
                + " Actual Result = " + ActValue);
        } else {
            Report.logPass("Verification Passed ==>" + sMessage + " -> Expected = " + ExpValue
                + " Actual Result = " + ActValue);
        }
        sAssert.assertEquals(ExpValue == ActValue, true, sMessage);
    }

    public static void softVerifyValue(String sMessage, Collection<?> lstExpCondition,
                                       Collection<?> lstActCondition, SoftAssert sAssert) {
        try {
            Assert.assertEquals(lstActCondition, lstExpCondition);
            Report.logPass("Verification Passed ==>" + sMessage + " -> Expected = " + lstExpCondition
                + " Actual Result = " + lstActCondition);
        } catch (AssertionError e) {
            Report.logError("Verification Failed ==>" + sMessage + " -> " + e);
        }
        sAssert.assertEquals(lstActCondition, lstExpCondition, sMessage);
    }

    public static void softVerifyValue(String sMessage, Map<?, ?> lstExpCondition,
                                       Map<?, ?> lstActCondition, SoftAssert sAssert) {
        try {
            Assert.assertEquals(lstActCondition, lstExpCondition);
            Report.logPass("Verification Passed ==>" + sMessage + " -> Expected = " + lstExpCondition
                + " Actual Result = " + lstActCondition);
        } catch (AssertionError e) {
            Report.logError("Verification Failed ==>" + sMessage + " -> " + e);
        }
        sAssert.assertEquals(lstActCondition, lstExpCondition);
    }

    public static boolean verifyAndLogValue(String sMessage, String ExpValue, String sActValue) {
        if (ExpValue.equalsIgnoreCase(sActValue)) {
            sMessage = "Verification Passed ==>" + sMessage + " -> Expected = " + ExpValue
                + " Actual Result = " + sActValue;
            Report.logPass(sMessage);
        } else {
            Report.logError("Verification Failed ==>" + sMessage + " -> Expected = " + ExpValue
                + " Actual Result = " + sActValue);
        }
        return false;
    }
}
