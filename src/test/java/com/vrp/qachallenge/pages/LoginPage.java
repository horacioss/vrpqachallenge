package com.vrp.qachallenge.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(id = "username")
    public WebElement inputUsername;
    @FindBy(id = "password")
    public WebElement inputPassword;
    @FindBy(id = "Login")
    public WebElement inputLogin;
    @FindBy(id = "rememberUn")
    public WebElement checkboxRememberMe;
    @FindBy(id = "forgot_password_link")
    public WebElement linkForgotPassword;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        inputLogin.click();
    }

}
