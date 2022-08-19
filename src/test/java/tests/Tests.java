package tests;

import com.codeborne.selenide.Condition;
import config.AllureSetup;
import config.BaseTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CreateTaskPage;
import pages.HomePage;
import utils.RandomDataGeneration;

@ExtendWith({AllureSetup.class})
public class Tests extends BaseTestConfiguration {

    @DisplayName("Create Daily Tasks")
    @Test
    public void testCreateTasks(){
        //Test data
        int tasksQuantity = 5;

        Assertions.assertTrue(HomePage.homePage.shouldBe(Condition.visible).isDisplayed(), "Home page wasn't loaded");
        HomePage.createYourDailyTodoListBtn.shouldBe(Condition.visible).click();
        CreateTaskPage.enterDailyTasks(tasksQuantity);
        CreateTaskPage.saveTasksBtn.click();
        //check that entered and displayed tasks are equal
        HomePage.isEnteredAndDisplayedTasksEqual();
        //check empty check-boxes state
        HomePage.isElementsAreDisplayed(HomePage.checkBoxesEmptyStateList);
        //check that red square is in red color when checkboxes are not selected
        Assertions.assertTrue(HomePage.isSquareMatrixElementInRedColor().isDisplayed());
    }

    @DisplayName("Track your tasks")
    @Test
    public void testTrackDailyTasks(){
        //Test data
        int tasksQuantity = 5;

        Assertions.assertTrue(HomePage.homePage.shouldBe(Condition.visible).isDisplayed(), "Home page wasn't loaded");
        HomePage.createYourDailyTodoListBtn.shouldBe(Condition.visible).click();
        CreateTaskPage.enterDailyTasks(tasksQuantity);
        CreateTaskPage.saveTasksBtn.click();
        HomePage.clickOnEveryCheckBox(HomePage.checkBoxesEmptyStateList);
        HomePage.isElementsAreDisplayed(HomePage.checkBoxesSelectedStateList);
        //unselect checkboxes
        HomePage.clickOnEveryCheckBox(HomePage.checkBoxesSelectedStateList);
        HomePage.isElementsAreDisplayed(HomePage.checkBoxesEmptyStateList);
    }

    @DisplayName("Update daily tasks")
    @Test
    public void testUpdateDailyTasks(){
        //Test data
        int initialTasksQty = 5;
        int updatedTaskQty = 3;
        int titleLength = 20;

        Assertions.assertTrue(HomePage.homePage.shouldBe(Condition.visible).isDisplayed(), "Home page wasn't loaded");
        HomePage.createYourDailyTodoListBtn.shouldBe(Condition.visible).click();
        CreateTaskPage.enterDailyTasks(initialTasksQty);
        CreateTaskPage.saveTasksBtn.click();
        HomePage.editBtn.click();
        CreateTaskPage.titleField.setValue(RandomDataGeneration.randomString(titleLength));
        CreateTaskPage.enterDailyTasks(updatedTaskQty);
        CreateTaskPage.saveTasksBtn.click();

        //check that updated and displayed tasks are equal
        HomePage.isElementsAreDisplayed(HomePage.checkBoxesSelectedStateList);
        //check that updated and displayed title is equal
        String titleOnHomePage = HomePage.titleField.getText();
        HomePage.editBtn.click();
        String titleOfCreateTasksPage = CreateTaskPage.titleField.getText();
        Assertions.assertTrue(titleOnHomePage.contains(titleOfCreateTasksPage), "Home page title and updated title are not equal");
    }

    @DisplayName("Delete all")
    @Test
    public void testDeleteAll(){
        //Test data
        int tasksQuantity = 5;
        String expectedTitle = "Tasks for today";

        Assertions.assertTrue(HomePage.homePage.shouldBe(Condition.visible).isDisplayed(), "Home page wasn't loaded");
        HomePage.createYourDailyTodoListBtn.shouldBe(Condition.visible).click();
        CreateTaskPage.enterDailyTasks(tasksQuantity);
        CreateTaskPage.saveTasksBtn.click();

        HomePage.editBtn.click();
        CreateTaskPage.titleField.setValue("");
        CreateTaskPage.textarea.setValue("");
        CreateTaskPage.saveTasksBtn.click();
        Assertions.assertEquals(expectedTitle, HomePage.titleField.getText().replace(" Edit", ""));
    }
}
