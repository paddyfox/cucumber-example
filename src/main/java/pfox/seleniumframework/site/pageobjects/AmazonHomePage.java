package pfox.seleniumframework.site.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pfox.seleniumframework.site.Site;

import java.util.List;
import java.util.stream.Collectors;

public class AmazonHomePage extends Site {

    @FindBy(id = "nav-logo") private WebElement amazonLogo;
    @FindBy(id = "sp-cc-accept") private WebElement acceptCookiesButton;
    @FindBy(id = "twotabsearchtextbox") private WebElement searchBar;
    @FindBy(css = "#nav-search-submit-text > input") private WebElement searchButton;
    @FindBy(tagName = "h2") private List<WebElement> searchResults;

    public void amazonWebsiteIsDisplayed() {
        waitForElementToAppear(amazonLogo, 20);
    }

    public void acceptCookies() {
        waitForElementToAppear(acceptCookiesButton, 20);
        acceptCookiesButton.click();
    }

    public void searchForItem(String itemName) {
        searchBar.sendKeys(itemName);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public List<String> getAllSearchResults() {
        return searchResults
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
