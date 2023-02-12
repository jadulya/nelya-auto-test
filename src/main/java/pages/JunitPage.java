package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class JunitPage {
    public SelenideElement switchBranchButton(){
        return $("[title='Switch branches or tags']").as("кнопка выбора ветки");
    }

    public SelenideElement branchInput(){
        return $("#context-commitish-filter-field").as("поле ввода названия ветки");
    }

    public SelenideElement choiceBranchButton(){
        return $(".SelectMenu-item").as("кнопка выбора ветки из списка");
    }

    public SelenideElement releasesButton(){
        return $("[href='/junit-team/junit4/releases']").as("кнопка перехода на страницу релизов");
    }

    public SelenideElement releasesInput(){
        return $("#release-filter").as("поле ввода названия релиза");
    }

    public SelenideElement releaseText(){
        return $(".hx_keyword-hl").as("искомый текст в названии релиза");
    }
}
