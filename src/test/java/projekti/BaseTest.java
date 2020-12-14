package projekti;

import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.configuration.FluentConfiguration;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import projekti.user.User;
import projekti.user.UserService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FluentConfiguration(webDriver = "chrome", capabilities = "{\"chromeOptions\": {\"args\": [\"headless\",\"disable-gpu\"]}}")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public abstract class BaseTest extends FluentTest {

    @Value("http://localhost:${local.server.port}")
    protected String baseUrl;

    @Autowired
    protected UserService userService;

    public BaseTest() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
    }

    public WebDriverWait driverWait() {
        return new WebDriverWait(getDriver(), 10);
    }

    public User authenticate() {
        User test = new User("test", "Test", "test", "password", null);
        userService.store(test);
        goTo(baseUrl + "/login");
        find("#handle").fill().with("test");
        find("#password").fill().with("password");
        find("form").first().submit();
        driverWait().until(ExpectedConditions.urlToBe(baseUrl + "/profiles/test"));
        return test;
    }
}
