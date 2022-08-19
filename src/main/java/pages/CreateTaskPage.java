package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import utils.RandomDataGeneration;
import java.util.ArrayList;
import static com.codeborne.selenide.Selenide.$;

public class CreateTaskPage {
    private static final Logger log = LogManager.getLogger(CreateTaskPage.class.getName());

    public static final SelenideElement textarea = $(By.xpath("//div[@class='center narrow']/form//textarea[@name='tasks']"));
    public static final SelenideElement saveTasksBtn = $(By.xpath("//p/input[@type='submit']"));
    public static final SelenideElement enteredTasks = $(By.xpath("//form/textarea[@name='tasks']"));
    public static final SelenideElement titleField = $(By.xpath("//form/input[@name='title']"));

    /**
     * Method is used to enter Daily Tasks
     * @tasksQty - quantity of tasks to be added to a list
     */
    @Step("Enter Daily Tasks")
    public static void enterDailyTasks(int tasksQty){
        int rndStrMaxLength = 10;
        String allTasks = "";

        for (int i = 0; i < tasksQty; i++) {
            allTasks = allTasks + RandomDataGeneration.randomString(rndStrMaxLength)+"\n";
            textarea.setValue(allTasks);
        }
        log.log(Level.INFO, "enterDailyTasks method");
    }

    /**
     * Method is used to get an array of entered tasks on Create Tasks Page
     */
    @Step("Get entered tasks list")
    public static ArrayList<String> getEnteredTasksList(){
        String initStr =  enteredTasks.getText();
        String[] strSplit = initStr.split(" ");
        ArrayList<String> strList = new ArrayList<>();

        for (int i = 0; i < strSplit.length; i++) {
            strList.add(strSplit[i]);
        }
        log.log(Level.INFO, "getEnteredTasksList method");
        return strList;
    }
}
