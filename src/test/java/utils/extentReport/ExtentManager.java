package utils.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
        reporter.config().setReportName("Turknet Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Turknet", "Mobile Test");
        extentReports.setSystemInfo("Author", "Bakican Boydas");
        return extentReports;
    }
}
