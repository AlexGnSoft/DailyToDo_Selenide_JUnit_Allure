package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import helpfiles.PropertiesFile;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Selenide.*;

public class BaseTestConfiguration extends AllureSetup{

    @Step("Open browser and application")
    @BeforeEach
    public void openApplication(){
        PropertiesFile propertiesFile = new PropertiesFile();
        propertiesFile.getBrowser();
        Configuration.browserSize = propertiesFile.getBrowserSize();
        Configuration.timeout = 5000;
        Configuration.headless = false;

        open(propertiesFile.getApplicationUrl());
    }

    @Step("Tear down")
    @AfterEach
    public void tearDown(){
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}
