package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    private static final Logger log = LogManager.getLogger(HomePage.class.getName());

    public static final SelenideElement homePage = $(By.xpath("//body/h1/a"));
    public static final SelenideElement createYourDailyTodoListBtn = $(By.xpath("//form[@action='/create']/input[@type='submit']"));
    public static final ElementsCollection tasksList = $$(By.xpath("//tr/td[@class='text']"));
    public static final SelenideElement editBtn = $(By.xpath("//td/a[@href]"));
    public static final ElementsCollection checkBoxesEmptyStateList = $$(By.xpath("//input[@class='check']"));
    public static final ElementsCollection checkBoxesSelectedStateList = $$(By.xpath("//b[@style='display: inline;']"));
    public static final SelenideElement squareMatrixElement  = $(By.xpath("//div[@class='ltdiv']/b[@class='red blockFalse']"));
    public static final SelenideElement titleField  = $(By.xpath("//tr/td[@id='headline']"));
    public static final ElementsCollection buttonsToMatrix  = $$(By.xpath("//td[@class='trend more']/a[@href]"));

    /**
     * Method is used to get updated tasks on home page
     */
    @Step("Get updated tasks list")
    public static ArrayList<String> getUpdateTasksList(){
        ArrayList<String> strList = new ArrayList<>();
        for (int i = 0; i < tasksList.size(); i++) {
            strList.add(tasksList.get(i).getText());
        }
        log.log(Level.INFO, "getUpdateTasksList method");

        return strList;
    }


    /**
     * Method verifies whether Entered tasks on Create tasks page are equal to tasks
     * displayed on Home page
     */
    @Step("Verify entered and displayed tasks")
    public static Boolean isEnteredAndDisplayedTasksEqual(){
        boolean isEqual = false;
        editBtn.click();
        ArrayList<String> enteredTasksList = CreateTaskPage.getEnteredTasksList();
        CreateTaskPage.saveTasksBtn.click();
        ArrayList<String> updateTasksList = HomePage.getUpdateTasksList();

        if(enteredTasksList.equals(updateTasksList))
        isEqual = true;

        log.log(Level.INFO, "isEnteredAndDisplayedTasksEqual method");
        return isEqual;
    }

    /**
     * Method verifies whether list of elements is visible
     * @elementsCollection - list of elements
     */
    @Step("Verify that elements are displayed")
    public static List<Boolean> isElementsAreDisplayed(ElementsCollection elementsCollection) {
        List<Boolean> booleanList = null;
        for (SelenideElement element : elementsCollection) {
            booleanList = Collections.singletonList(element.isDisplayed());
        }
        log.log(Level.INFO, "isElementsAreDisplayed method");
        return booleanList;
    }

    /**
     * Method is used to verify that red square is displayed on square matrix
     */
    @Step("Verify that square element is in red color")
    public static SelenideElement isSquareMatrixElementInRedColor(){
        buttonsToMatrix.get(0).click();
        log.log(Level.INFO, "isSquareMatrixElementInRedColor method");

        return squareMatrixElement.shouldBe(Condition.visible);
    }

    /**
     * Method is used to click on every checkbox
     * @elementsCollection - list of checkboxes
     */
    @Step("Click on every checkbox")
    public static void clickOnEveryCheckBox(ElementsCollection elementsCollection){
        for (int i = 0; i < elementsCollection.size(); i++) {
            elementsCollection.get(i).click();
            sleep(500);
        }
        log.log(Level.INFO, "clickOnEveryCheckBox method");
    }
}
