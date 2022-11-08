package uk.kainos.seleniumframework.site.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uk.kainos.seleniumframework.site.Site;

public class CurrysHomePage extends Site {

    @FindBy(id = "insert-id-here") private WebElement currysLogo;
}
