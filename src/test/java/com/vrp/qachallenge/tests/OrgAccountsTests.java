package com.vrp.qachallenge.tests;

import com.vrp.qachallenge.models.OrgAccountModel;
import com.vrp.qachallenge.pages.AccountsPage;
import com.vrp.qachallenge.pages.LoginPage;
import com.vrp.qachallenge.utils.ConfigReader;
import com.vrp.qachallenge.utils.OrgAccountDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrgAccountsTests extends BaseTest {

    LoginPage loginPage;
    AccountsPage accountsPage;

    @BeforeMethod
    public void openOrgAccountsPage() {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        loginPage.openAccountsPage();
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
        accountsPage.openAccountsPage();
        accountsPage.isAccountPresent(accountData.getAccountName());
    }


    @Test(
            testName = "Edit Org Account",
            dependsOnMethods = "testCreateOrgAccount",
            dataProvider = "getTestData",
            dataProviderClass = OrgAccountDataProvider.class
    )
    public void testEditOrgAccount(OrgAccountModel accountData) {
        accountsPage.editAccountWithName(accountData);
        accountsPage.openAccountsPage();
        accountsPage.isAccountPresent(accountData.getNewAccountName());
    }



    @Test(
            testName = "Delete Org Account",
            dependsOnMethods = "testEditOrgAccount",
            dataProvider = "getTestData",
            dataProviderClass = OrgAccountDataProvider.class
    )
    public void testDeleteOrgAccount(OrgAccountModel accountData) {
        accountsPage.deleteAccountWithName(accountData.getNewAccountName());
    }
}
