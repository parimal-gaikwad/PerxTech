package testCases;

import static commons.Configuration.url;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.TestBase;
import pages.BulkActionsPage;
import pages.LoginPage;
import pages.OverviewPage;

public class BulkActionsTest extends TestBase {

	LoginPage lp;
	OverviewPage op;
	BulkActionsPage bap;

	@BeforeMethod(alwaysRun = true)
	public void openPage() {
		driver.get(url.asString());
		lp = new LoginPage(driver);
		op = new OverviewPage(driver);
		bap = new BulkActionsPage(driver);
	}

	//Form upload should only accept from the accepted file list (.txt, .xlsx, .csv).
	@Test
	public void verifyUserAbleToUploadExcelTypeFile() {
		SoftAssert sa = new SoftAssert();		
		String excelPath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\Assets\\TestDocuments\\sampleExcel.xlsx";

		sa.assertTrue(lp.doAdminLogin(),"admin login failed");
		sa.assertTrue(op.goToBulkActionsSection(),"admin not navigated to bulk actions page");
		sa.assertTrue(bap.uploadBulkActionsFile(excelPath, "File uploaded" ,"Upload Users"),"Excel type file not accepted");
		sa.assertAll();
	}
	
	
	//Form upload should only accept from the accepted file list (.txt, .xlsx, .csv).
	@Test
	public void verifyUserAbleToUploadTxtTypeFileTest() {

		SoftAssert sa = new SoftAssert();

		String txtPath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\Assets\\TestDocuments\\sampleText.txt";

		sa.assertTrue(lp.doAdminLogin(),"admin login failed");
		sa.assertTrue(op.goToBulkActionsSection(),"admin not navigated to bulk actions page");
		sa.assertTrue(bap.uploadBulkActionsFile(txtPath, "File uploaded","Upload Users"),"Text type file not accepted");
		sa.assertAll();
	}
	
	
	//Form upload should only accept from the accepted file list (.txt, .xlsx, .csv).
	@Test
	public void verifyUserAbleToUploadCsvTypeFileTest() {

		SoftAssert sa = new SoftAssert();

		String csvPath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\Assets\\TestDocuments\\demo.csv";

		sa.assertTrue(lp.doAdminLogin(),"admin login failed");
		sa.assertTrue(op.goToBulkActionsSection(),"admin not navigated to bulk actions page");
		sa.assertTrue(bap.uploadBulkActionsFile(csvPath, "File uploaded","Upload Users"),"csv type file not accepted");
		sa.assertAll();
	}

	//Form upload should only accept from the accepted file list (.txt, .xlsx, .csv).
	@Test
	public void verifyUserNotAbleToUploadPDFTypeFileTest() {
		SoftAssert sa = new SoftAssert();
		String pdfPath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\Assets\\TestDocuments\\sample.pdf";
		sa.assertTrue(lp.doAdminLogin(),"admin login failed");
		sa.assertTrue(op.goToBulkActionsSection(),"admin not navigated to bulk actions page");
		sa.assertTrue(bap.uploadBulkActionsFile(pdfPath, "Error uploading file.","Upload Users"),"pdf type file is being accepted");
		sa.assertAll();
	}
	
	
	//After a successful upload, the file list should reflect the newly uploaded file
	@Test
	public void verifyNewlyUploadedFileReflectsInListTest() {
		SoftAssert sa = new SoftAssert();
		String path = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\Assets\\TestDocuments\\demo.csv";
		
		sa.assertTrue(lp.doAdminLogin(),"admin login failed");
		sa.assertTrue(op.goToBulkActionsSection(),"admin not navigated to bulk actions page");
		sa.assertTrue(bap.uploadBulkActionsFile(path, "File uploaded","Upload Users"),"File not uploaded");
		sa.assertTrue(bap.verifyNewlyUploadedFileReflectsInList("demo.csv"),"File not present in list");
		sa.assertAll();
	}
	
	
	
	//Each file should be tied to one action.
	@Test
	public void verifyEachFileShouldBeTiedToOneActionTest() {
		SoftAssert sa = new SoftAssert();

		String csvPath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\Assets\\TestDocuments\\demo.csv";
		String fileName = csvPath.substring(csvPath.lastIndexOf("\\") + 1);
		sa.assertTrue(lp.doAdminLogin(),"admin login failed");
		sa.assertTrue(op.goToBulkActionsSection(),"admin not navigated to bulk actions page");
		sa.assertTrue(bap.uploadBulkActionsFile(csvPath, "File uploaded","Upload Users"),"csv type file not accepted");
		sa.assertTrue(op.goToBulkActionsSection(),"admin not navigated to bulk actions page");
		sa.assertTrue(bap.verifyEachFileShouldBeTiedToOneAction(fileName, "Upload users"),"Action type not attched to uploaded file");
		
		sa.assertAll();		
	}
	
	
	
	

}
