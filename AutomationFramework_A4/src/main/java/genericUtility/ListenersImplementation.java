package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener {

	ExtentReports report;
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Started---");
		test = report.createTest(methodname);
	}

	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Success---");
		test.log(Status.PASS, methodname + "----passed");
	}

	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		String screenshotName = methodname + " " + jutil.getSystemDateInFormat();
		try {
			String path = wutil.toTakeScreenshot(BaseClass.sDriver, screenshotName);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(Status.FAIL, methodname + "---failed");

	}

	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Skipped---");
		test.log(Status.SKIP, methodname + "---Skipped");

	}

	public void onStart(ITestContext context) {
		System.out.println("---Suite Execution Started---");
		ExtentSparkReporter htmlreport = new ExtentSparkReporter(
				".\\ExtentReports\\Report" + new JavaUtility().getSystemDateInFormat() + ".html");
		htmlreport.config().setDocumentTitle("Vtiger Execution Report");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName("VTIGER EXECUTION REPORT");

		report = new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("BaseUrl", "http://localhost:8888/");
		report.setSystemInfo("Username", "admin");
		report.setSystemInfo("Password", "password");
		report.setSystemInfo("ReporterName", "Kiran Ks");
	}

	public void onFinish(ITestContext context) {
		System.out.println("---Suite Execution Started---");
		report.flush();
	}

}
