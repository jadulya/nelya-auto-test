import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AuthTest {
    @BeforeEach
    public void setup(){
        open("https://github.com/");
        TestPages.mainPage.mainSingInButton().click();
    }
    @Test
    @DisplayName("Успешная авторизация и переход в профиль")
    public void shouldAuthTest(){
        TestPages.mainPage.loginInput().sendKeys("jadulya");
        TestPages.mainPage.passwordInput().sendKeys("none");
        TestPages.mainPage.signInButton().click();
        TestPages.mainPage.createNewButton().shouldBe(visible);
        TestPages.mainPage.yourProfileButton().click();
        $(byText("Your profile")).click();
        TestPages.mainPage.editProfileButton().shouldBe(visible);
    }

    @MethodSource("incorrectCredentials")
    @ParameterizedTest(name = "{displayName} {0}")
    @DisplayName("Неуспешная авторизация:")
    public void shouldNotAuthorizeTest(String type, String login, String password){
        TestPages.mainPage.loginInput().sendKeys(login);
        TestPages.mainPage.passwordInput().sendKeys(password);
        TestPages.mainPage.signInButton().click();
        TestPages.mainPage.errorAuthMessage().shouldBe(visible);
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
