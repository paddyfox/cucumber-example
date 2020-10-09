package uk.kainos.seleniumframework.site.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uk.kainos.seleniumframework.site.Site;

public class StartPage extends Site {

    private static final String PAGE_TITLE = "Home";

    @FindBy(id = "header") private WebElement pageHeader;

    public void verifyPageHeader() {
        verifyPageTitle(pageHeader.getText(), PAGE_TITLE);
    }
}
