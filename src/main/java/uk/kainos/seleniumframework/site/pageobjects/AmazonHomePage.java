package uk.kainos.seleniumframework.site.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uk.kainos.seleniumframework.site.Site;

import java.util.List;
import java.util.stream.Collectors;

public class AmazonHomePage extends Site {

    @FindBy(id = "nav-logo-sprites") private WebElement pageHeaderLogo;
    @FindBy(id = "twotabsearchtextbox") private WebElement searchBar;
    @FindBy(css = "#nav-search-submit-text > input") private WebElement searchButton;
    @FindBy(tagName = "h2") private List<WebElement> searchResults;

    public void amazonWebsiteIsDisplayed() {
        pageHeaderLogo.isDisplayed();
    }

    public void searchForItem(String itemName) {
        searchBar.sendKeys(itemName);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void selectFirstSearchResult() {
        //TODO: Make this work
        searchResults.get(0).click();
    }

    public List<String> getAllSearchResults() {
        return searchResults
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
