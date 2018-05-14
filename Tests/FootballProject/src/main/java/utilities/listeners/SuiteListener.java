package utilities.listeners;

import org.testng.*;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class SuiteListener implements ISuiteListener, ITestListener, IInvokedMethodListener2 {

    private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
    private static String tableReportsPath;
    private static String reportDir;

    public static String getReportDir() {
        return reportDir;
    }

    public static String getTableReportsPath() {
        return tableReportsPath;
    }

    @Override
    public void onFinish(ISuite suite) {

    }

    @Override
    public void onStart(ISuite suite) {
        System.setProperty(ESCAPE_PROPERTY, "false"); // set this for ReportNG report
        File folder = new File(suite.getOutputDirectory());
        File resultDir = new File(folder.getParent());
        File screenshotFolder = new File(folder.getParent() + File.separator + "html" + File.separator + "screenshots");
        File tableReportsFolder = new File(
                folder.getParent() + File.separator + "html" + File.separator + "tableReports");
        if (!screenshotFolder.exists()) {
            screenshotFolder.mkdirs();
        }
        if (!tableReportsFolder.exists()) {
            tableReportsFolder.mkdirs();
        }
        tableReportsPath = tableReportsFolder.getPath();
        reportDir = resultDir.getPath();
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Do Nothing
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Do Nothing
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Do Nothing
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Do Nothing
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Do Nothing
    }

    @Override
    public void onStart(ITestContext context) {
        // Do Nothing
    }


    @Override
    public void onFinish(ITestContext context) {
        // Do Nothing
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // Do Nothing
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // Do Nothing
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        // Do Nothing
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        // Reporter.setCurrentTestResult(testResult);
        int invocationCount = testResult.getMethod().getCurrentInvocationCount();
        if (method.isTestMethod() && null == testResult.getThrowable()) {
            SoftAssert sAssert =
                    (SoftAssert) context.getAttribute(testResult.getInstance().toString() + invocationCount + ":sAssert");
            if (null == sAssert) {
                return;
            }
            try {
                sAssert.assertAll();
            } catch (AssertionError ae) {
                testResult.setStatus(ITestResult.FAILURE);
                testResult.setThrowable(ae);
            }
        }
    }
}
