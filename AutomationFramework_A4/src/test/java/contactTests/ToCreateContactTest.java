package contactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactTest extends genericUtility.BaseClass {

	@Test(groups = "smoke")
	public void createOrganization() throws EncryptedDocumentException, IOException {

		// To click on contacts link
		objectRepository.HomePage hp = new objectRepository.HomePage(driver);
		hp.getContactsLink().click();

		// To click on create contact lookup image
		objectRepository.ContactsPage cp = new objectRepository.ContactsPage(driver);
		cp.getCreateContactIcon().click();

		// To create contact with details
		genericUtility.ExcelFileUtility eutil = new genericUtility.ExcelFileUtility();
		String contactName = eutil.toReadDataFromExcel("Contacts", 1, 2);
		objectRepository.CreateContactPage ccp = new objectRepository.CreateContactPage(driver);
		ccp.getLastnameTextFieldEdt().sendKeys(contactName);
		ccp.getSaveButtonInCreateContactPage().click();
		Assert.fail();

		// To verify
		objectRepository.ContactsInformationPage cip = new objectRepository.ContactsInformationPage(driver);
		String name = cip.getContactsVerification().getText();
		Assert.assertTrue(name.contains(contactName), "Failed to create contact");
		Reporter.log("Successfully created contact", true);
	}
}
