import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Owner("jadulya")
@Feature("Авторизация")
public class AuthTest {
    @BeforeEach
    public void setup() {
        open("https://github.com/");
        TestPages.mainPage.mainSingInButton().click();
    }

    @Test
    @DisplayName("Успешная авторизация и переход в профиль")
    public void shouldAuthTest() {
        step("Ввести логин и пароль, нажать на кнопку авторизации", () -> {
            TestPages.mainPage.loginInput().sendKeys("jadulya");
            TestPages.mainPage.passwordInput().sendKeys("h0sP6C5:");
            TestPages.mainPage.signInButton().click();
        });
        step("Проверить, что присутствует кнопка 'Create new...'", () -> {
            TestPages.mainPage.createNewButton().shouldBe(visible);
        });
        step("Перейти в профиль", () -> {
            TestPages.mainPage.yourProfileButton().click();
            $(byText("Your profile")).click();
        });
        step("Проверить, что присутствует кнопка 'Edit profile'", () -> {
            TestPages.mainPage.editProfileButton().shouldBe(visible);
        });
    }

    @MethodSource("incorrectCredentials")
    @ParameterizedTest(name = "{displayName} {0}")
    @DisplayName("Неуспешная авторизация:")
    public void shouldNotAuthorizeTest(String type, String login, String password) {
        step("Ввести логин и пароль, нажать на кнопку авторизации", () -> {
            TestPages.mainPage.loginInput().sendKeys(login);
            TestPages.mainPage.passwordInput().sendKeys(password);
            TestPages.mainPage.signInButton().click();
        });

        step("Проверить, что присутствует сообщение об ошибке", () -> {
            TestPages.mainPage.errorAuthMessage().shouldBe(visible);
        });
    }

    static Stream<Arguments> incorrectCredentials() {
        return Stream.of(
                arguments(
                        "зарегистрированный логин и пароль от чужого аккаунта",
                        "jadulya",
                        "123456789Qq"

                ),
                arguments(
                        "незарегистрированный логин и пароль от чужого аккаунта",
                        "jadulya123",
                        "123456789Qq"
                )
        );
    }
}
