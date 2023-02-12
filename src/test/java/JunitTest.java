import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Keys;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class JunitTest {
    @BeforeEach
    public void setup() {
        open("https://github.com/junit-team/junit4");
        webdriver().shouldHave(url("https://github.com/junit-team/junit4"));
    }

    @Test
    @DisplayName("Переключение на ветку fixtures")
    public void shouldSwitchToBranchTest() {
        TestPages.junitPage.switchBranchButton().click();
        TestPages.junitPage.branchInput().sendKeys("fixtures");
        TestPages.junitPage.choiceBranchButton().click();
        webdriver().shouldHave(url("https://github.com/junit-team/junit4/tree/fixtures"));
    }
    @MethodSource("positiveChecks")
    @ParameterizedTest(name = "{displayName} {0}")
    @DisplayName("Позитивные проверки поиска релиза")
    public void positiveChecksSearchReleaseTest(String type, String searchText) {
        TestPages.junitPage.releasesButton().click();
        webdriver().shouldHave(url("https://github.com/junit-team/junit4/releases"));
        TestPages.junitPage.releasesInput().sendKeys(searchText + Keys.ENTER);
        TestPages.junitPage.releaseText().shouldHave(text(searchText));
    }

    static Stream<Arguments> positiveChecks() {
        return Stream.of(
                arguments(
                        "по номеру",
                        "4.11"
                ),
                arguments(
                        "по буквам в названии",
                        "Beta"
                )
        );
    }
}
