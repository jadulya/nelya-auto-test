import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {
    @Test
    public void shouldAuthTest(){
        open("https://github.com/");
        $("[href='/login']").click();
        $("#login_field").sendKeys("jadulya");
        $("#password").sendKeys("none");
        $("[value='Sign in']").click();
        $("[aria-label='Create new…']").shouldBe(visible);
        $("[aria-label='View profile and more']").click();
        $(byText("Your profile")).click();
        $(".js-profile-editable-edit-button").shouldBe(visible);
    }
}
