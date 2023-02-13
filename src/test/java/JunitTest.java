import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
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
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Owner("jadulya")
@Feature("Junit")
public class JunitTest {
    @BeforeEach
    public void setup() {
        open("https://github.com/junit-team/junit4");
        webdriver().shouldHave(url("https://github.com/junit-team/junit4"));
    }

    @Test
    @DisplayName("Переключение на ветку fixtures")
    public void shouldSwitchToBranchTest() {
        step("Клик по полу поиска нужной ветки", () -> {
            TestPages.junitPage.switchBranchButton().click();
        });
        step("Ввод названия искомой ветки и ее выбор", () -> {
            TestPages.junitPage.branchInput().sendKeys("fixtures");
            TestPages.junitPage.choiceBranchButton().click();
        });
        step("Проверка, что мы находимся на странице искомой ветки", () -> {
            webdriver().shouldHave(url("https://github.com/junit-team/junit4/tree/fixtures"));
        });
    }

    @MethodSource("positiveChecks")
    @ParameterizedTest(name = "{displayName} {0}")
    @DisplayName("Позитивные проверки поиска релиза")
    public void positiveChecksSearchReleaseTest(String type, String searchText) {
        step("Переход на страницу релизов", () -> {
            TestPages.junitPage.releasesButton().click();
        });
        step("Проверка, что находимся на странице релизов", () -> {
            webdriver().shouldHave(url("https://github.com/junit-team/junit4/releases"));
        });
        step("Ввод названия искомой версии релиза и поиск", () -> {
            TestPages.junitPage.releasesInput().sendKeys(searchText + Keys.ENTER);
        });
        step("Проверка, что искомый текст находится в результатах поиска", () -> {
            TestPages.junitPage.releaseText().shouldHave(text(searchText));
        });
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
