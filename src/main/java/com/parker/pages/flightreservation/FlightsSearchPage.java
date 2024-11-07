package com.parker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.parker.pages.AbstractPage;

public class FlightsSearchPage extends AbstractPage{

    @FindBy(id = "oneway")
    private WebElement oneWayRadioButton;

    @FindBy(id = "twoway")
    private WebElement twoWayRadioButton;

    @FindBy(id = "passengers")
    private WebElement passengersSelect;

    @FindBy(id = "depart-from")
    private WebElement departFromSelect;

    @FindBy(id = "arrive-in")
    private WebElement arriveInSelect;

    @FindBy(id = "service-class1")
    private WebElement economyClassRadioButton;

    @FindBy(id = "service-class2")
    private WebElement firstClassRadioButton;

    @FindBy(id = "service-class3")
    private WebElement businessClassRadioButton;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;

    public FlightsSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.searchFlightsButton));
        return this.searchFlightsButton.isDisplayed();
    }

    public void decideRoundTrip(boolean isRoundTrip) {
        (isRoundTrip ? twoWayRadioButton: oneWayRadioButton).click();
    }

    public void selectPassengers(String numOfPassengers) {
        Select passengers = new Select(this.passengersSelect);
        passengers.selectByValue(numOfPassengers);
    }

    public void selectDepartingFrom(String departingLocation) {
        Select locations = new Select(this.departFromSelect);
        locations.selectByValue(departingLocation);
    }

    public void selectArrivingIn(String arrivingLocation) {
        Select locations = new Select(this.arriveInSelect);
        locations.selectByValue(arrivingLocation);
    }

    public void chooseServiceClass(String serviceClass) {
        if (serviceClass == "Economy" ) {
            economyClassRadioButton.click();
        } else if (serviceClass == "First") {
            firstClassRadioButton.click();
        } else if (serviceClass == "Business") {
            businessClassRadioButton.click();
        }
    }

    public void searchFlights() {
        this.searchFlightsButton.click();
    }
    

}
