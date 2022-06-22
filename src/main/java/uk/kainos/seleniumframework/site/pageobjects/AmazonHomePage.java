package uk.kainos.seleniumframework.site.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uk.kainos.seleniumframework.site.Site;

import java.util.List;
import java.util.stream.Collectors;

public class AmazonHomePage extends Site {

    @FindBy(id = "nav-logo-sprites") private WebElement pageHeaderLogo;
    @FindBy(id = "sp-cc-accept") private WebElement acceptCookies;
    @FindBy(id = "twotabsearchtextbox") private WebElement searchBar;
    @FindBy(css = "#nav-search-submit-text > input") private WebElement searchButton;
    @FindBy(tagName = "h2") private List<WebElement> searchResults;
    @FindBy(xpath = "//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/h2/a") private WebElement snoopDogCDResult;
    @FindBy(xpath = "//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/h2/a") private WebElement echoDotResult;

    public void amazonWebsiteIsDisplayed() {
        pageHeaderLogo.isDisplayed();
    }

    public void searchForItem(String itemName) {
        searchBar.sendKeys(itemName);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void acceptCookies() { acceptCookies.click(); }

    public void selectFirstResultForSnoopDogg() {
        snoopDogCDResult.click();
    }

    public void selectFirstResultForEchoDot() {
        echoDotResult.click();
    }

    public List<String> getAllSearchResults() {
        return searchResults
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
