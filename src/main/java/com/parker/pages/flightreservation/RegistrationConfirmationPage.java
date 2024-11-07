package com.parker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.parker.pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage{

    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firstNameElement;

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightsSearchButton;
    
    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightsSearchButton));
        return this.goToFlightsSearchButton.isDisplayed();
    }

    public String getFirstNameElement() {
        return this.firstNameElement.getText();
    }
    
    public void goToFlightsSearch() {
        this.goToFlightsSearchButton.click();
    }
}
