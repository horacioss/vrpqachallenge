package com.vrp.qachallenge.pages;

import com.vrp.qachallenge.models.OrgAccountModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.AssertJUnit.assertTrue;

public class AccountsPage extends BasePage {

    // List Accounts Elements
    @FindBy(xpath = "//li[@data-target-selection-name='sfdc:StandardButton.Account.New']//a[@title='New']")
    public WebElement newAccountButton;

    // New & Edit Account Form Elements
    @FindBy(xpath = "//flexipage-field[contains(@data-field-id, 'RecordNameField')]//input[@name='Name']")
    public WebElement accountNameInput;
    @FindBy(xpath = "//flexipage-field[contains(@data-field-id, 'RecordPhoneField')]//input[@name='Phone']")
    public WebElement accountPhoneInput;
    @FindBy(xpath = "(//flexipage-field[contains(@data-field-id, 'RecordTypeField')])[2]//button[@role='combobox']")
    public WebElement accountTypeButtonCombobox;
    @FindBy(xpath = "(//flexipage-field[contains(@data-field-id, 'RecordIndustryField')])[2]//button[@role='combobox']")
    public WebElement accountIndustryButtonCombobox;
    public WebElement accountTypeOrIndustryOptionWithName(String typeOrIndustry, String name) {
        return driver.findElement(
                By.xpath(String.format("//div[@aria-label='%s']//lightning-base-combobox-item[@data-value='%s']", typeOrIndustry,name))
        );
    }
    @FindBy(xpath = "//div[@data-target-selection-name='sfdc:RecordField.Account.Website']//input[@name='Website']")
    public WebElement accountWebsiteInput;
    @FindBy(xpath = "//flexipage-field[contains(@data-field-id, 'RecordDescriptionField')]//textarea")
    public WebElement accountDescriptionInput;
    @FindBy(xpath = "//h3[span[text()='Additional Information']]/following-sibling::laf-progressive-container//flexipage-field[contains(@data-field-id, 'RecordPhoneField')]//input")
    public WebElement accountAdditionalInfoPhoneInput;
    @FindBy(xpath = "//flexipage-field[@data-field-id='RecordNumberOfEmployeesField']//input[@name='NumberOfEmployees']")
    public WebElement accountNumberOfEmployeesInput;


    @FindBy(xpath = "//records-form-footer//button[@name='CancelEdit']")
    public WebElement cancelEditButton;
    @FindBy(xpath = "//records-form-footer//button[@name='SaveAndNew']")
    public WebElement saveAndNewButton;
    @FindBy(xpath = "//records-form-footer//button[@name='SaveEdit']")
    public WebElement saveEditButton;


    // Account Details Elements
    @FindBy(xpath = "(//flexipage-record-home-scrollable-column[contains(@id, 'leftColumn')]//li[@data-target-selection-name='sfdc:StandardButton.Account.Edit']//button[@name='Edit'])")
    public WebElement editAccountButton;
    @FindBy(xpath = "(//flexipage-record-home-scrollable-column[contains(@id, 'leftColumn')]//li[@data-target-selection-name='sfdc:StandardButton.Account.Delete']//button[@name='Delete'])")
    public WebElement deleteAccountButton;


    // Confirm Delete Account Modal
    @FindBy(css = "div[class~='slds-modal'] button[class~='forceActionButton'][title='Delete']")
    public WebElement confirmDeleteButton;
    @FindBy(css = "div[class~='slds-modal'] button[class~='forceActionButton'][title='Cancel']")
    public WebElement cancelDeleteButton;


    public WebElement labelAccountWithName(String accountName) {
        return driver.findElement(
                By.cssSelector(String.format("table > tbody > tr > th > span > a[data-refid='recordId'][title='%s']", accountName))
        );
    }


    public AccountsPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public void createNewAccountWithName(String accountName) {
        clickOnElement(newAccountButton);
        sendKeysToElement(accountNameInput, accountName);
        clickOnElement(saveEditButton);
        validateAlertMessage(String.format("Account \"%s\" was created.", accountName));
    }
    public void editAccountWithName(OrgAccountModel accountData) {
        clickOnElement(labelAccountWithName(accountData.getAccountName()));
        clickOnElement(editAccountButton);
        sendKeysToElement(accountNameInput, accountData.getNewAccountName());
        sendKeysToElement(accountPhoneInput, accountData.getPhone());
        clickOnElement(accountTypeButtonCombobox);
        clickOnElement(accountTypeOrIndustryOptionWithName("Type", accountData.getAccountType()));
        clickOnElement(accountIndustryButtonCombobox);
        clickOnElement(accountTypeOrIndustryOptionWithName("Industry", accountData.getAccountIndustry()));
        sendKeysToElement(accountWebsiteInput, accountData.getWebsite());
        sendKeysToElement(accountDescriptionInput, accountData.getDescription());
        sendKeysToElement(accountNumberOfEmployeesInput, accountData.getEmployeesCount());
        clickOnElement(saveEditButton);
        validateAlertMessage(String.format("Account \"%s\" was saved.", accountData.getNewAccountName()));
    }
    public void deleteAccountWithName(String accountName) {
        labelAccountWithName(accountName).click();
        clickOnElement(deleteAccountButton);
        clickOnElement(confirmDeleteButton);
        validateAlertMessage(String.format("Account \"%s\" was deleted. Undo XD ", accountName));
    }
    public void isAccountPresent(String accountName) {
        assertTrue( "The account label was not in the list.", labelAccountWithName(accountName).isDisplayed());
    }
}
