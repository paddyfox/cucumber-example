package pfox.seleniumframework.site.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pfox.seleniumframework.site.Site;

public class CurrysHomePage extends Site {

    @FindBy(id = "insert-id-here") private WebElement currysLogo;
}
