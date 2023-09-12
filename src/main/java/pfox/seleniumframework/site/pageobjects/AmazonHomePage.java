package pfox.seleniumframework.site.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import pfox.seleniumframework.site.Site;

public class AmazonHomePage extends Site {

    @FindBy(id = "nav-logo") private WebElement amazonLogo;
    @FindBy(id = "sp-cc-accept") private WebElement acceptCookiesButton;
    @FindBy(id = "twotabsearchtextbox") private WebElement searchBar;
    @FindBy(css = "#nav-search-submit-text > input") private WebElement searchButton;
    @FindBy(tagName = "h2") private List<WebElement> searchResults;

    public void amazonWebsiteIsDisplayed() {
        waitForElementToAppear(amazonLogo);
    }

    public void acceptCookies() {
        waitForElementToAppear(acceptCookiesButton);
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
