package com.parker.tests.flightreservation;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.parker.pages.flightreservation.FlightConfirmationPage;
import com.parker.pages.flightreservation.FlightsSearchPage;
import com.parker.pages.flightreservation.FlightsSelectionPage;
import com.parker.pages.flightreservation.RegistrationConfirmationPage;
import com.parker.pages.flightreservation.RegistrationPage;
import com.parker.tests.AbstractTest;
import com.parker.tests.flightreservation.model.FlightReservationTestData;
import com.parker.util.Config;
import com.parker.util.Constants;
import com.parker.util.JsonUtil;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters({"testDataPath"})
    public void setParameters(String testDataPath) {
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getFirstNameElement(), testData.firstName());
        registrationConfirmationPage.goToFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest() {
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt());
        
        flightsSearchPage.decideRoundTrip(testData.isRoundTrip());
        flightsSearchPage.selectPassengers(testData.numOfPassengers());
        flightsSearchPage.selectDepartingFrom(testData.departingLocation());
        flightsSearchPage.selectArrivingIn(testData.arrivingLocation());
        flightsSearchPage.chooseServiceClass(testData.serviceClass());
        flightsSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightsSelectionTest() {
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());

        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightsSelectionTest")
    public void flightReservationConfirmationTest() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
    }
}
