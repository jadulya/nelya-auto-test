package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public SelenideElement mainSingInButton() {
        return $("[href='/login']").as("кнопка 'Авторизоваться'");
    }

    public SelenideElement loginInput() {
        return $("#login_field").as("ввод логина");
    }

    public SelenideElement passwordInput(){
        return $("#password").as("ввод пароля");
    }

    public SelenideElement signInButton(){
       return $("[value='Sign in']").as ("кнопка подтверждения авторизации");
    }

    public SelenideElement createNewButton(){
        return $("[aria-label='Create new…']").as("кнопка выпадающего списка 'Create new...'");
    }

    public SelenideElement yourProfileButton(){
        return $("[aria-label='View profile and more']").as("кнопка выпадающего списка 'Your profile'");
    }

    public SelenideElement editProfileButton(){
        return $(".js-profile-editable-edit-button").as("кнопка 'Edit profile'");
    }

    public SelenideElement errorAuthMessage(){
        return $(".flash.flash-full.flash-error").as("сообщение об ошибке авторизации");
    }
}
