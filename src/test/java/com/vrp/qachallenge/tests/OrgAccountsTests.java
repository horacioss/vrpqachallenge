package com.vrp.qachallenge.tests;

import com.vrp.qachallenge.models.OrgAccountModel;
import com.vrp.qachallenge.pages.AccountsPage;
import com.vrp.qachallenge.pages.LoginPage;
import com.vrp.qachallenge.pages.NavBar;
import com.vrp.qachallenge.utils.ConfigReader;
import com.vrp.qachallenge.utils.OrgAccountDataProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class OrgAccountsTests extends BaseTest {

    LoginPage loginPage;
    NavBar navBar;
    AccountsPage accountsPage;

    @BeforeMethod
    public void openOrgAccountsPage() {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        navBar = new NavBar(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(navBar.accountsTab));
        navBar.accountsTab.click();
        accountsPage = new AccountsPage(driver);
    }



    @Test(
            priority = 1,
            testName = "Create Org Account",
            dataProvider = "getTestData",
            dataProviderClass = OrgAccountDataProvider.class
    )
    public void testCreateOrgAccount(OrgAccountModel accountData) {

        accountsPage.createNewAccountWithName(accountData.getAccountName());
        assertTrue(accountsPage.alertMessage.isDisplayed());
        assertEquals(
                accountsPage.alertMessage.getText(),
                String.format("Account \"%s\" was created.", accountData.getAccountName())
        );
        navBar.accountsTab.click();
        assertTrue(accountsPage.isAccountPresent(accountData.getAccountName()));
    }


    @Test(
            testName = "Edit Org Account",
            dependsOnMethods = "testCreateOrgAccount",
            dataProvider = "getTestData",
            dataProviderClass = OrgAccountDataProvider.class
    )
    public void testEditOrgAccount(OrgAccountModel accountData) {
        accountsPage.editAccountWithName(accountData);
        assertTrue(accountsPage.alertMessage.isDisplayed());
        assertEquals(
                accountsPage.alertMessage.getText(),
                String.format("Account \"%s\" was saved.", accountData.getNewAccountName())
        );
        navBar.accountsTab.click();
        assertTrue(accountsPage.isAccountPresent(accountData.getNewAccountName()));
    }



    @Test(
            testName = "Delete Org Account",
            dependsOnMethods = "testEditOrgAccount",
            dataProvider = "getTestData",
            dataProviderClass = OrgAccountDataProvider.class
    )
    public void testDeleteOrgAccount(OrgAccountModel accountData) {
        accountsPage.deleteAccountWithName(accountData.getNewAccountName());
        assertTrue(accountsPage.alertMessage.isDisplayed());
        assertEquals(
                accountsPage.alertMessage.getText(),
                String.format("Account \"%s\" was deleted. Undo", accountData.getNewAccountName())
        );
    }
}
