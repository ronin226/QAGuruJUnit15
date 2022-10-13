package guru.qa;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import com.codeborne.selenide.CollectionCondition;
import guru.qa.data.Sections;

public class SimpleExampleTests {

            @CsvSource(value = {
            "Features, A test runner built for humans, Modern testing with cross-browser compatibility",
            "How it works, Testing has been broken for too long, We figured it was time to fix it",
            "Dashboard, Test and debug faster with the Cypress Dashboard, The Cypress Dashboard increases test velocity while giving total visibility into tests running in your CI pipeline ",
            "Pricing, Cypress Dashboard Pricing, Ship better code faster when you add the Cypress Dashboard to our open source"
    })


    @ParameterizedTest(name = "Проверка заголовка раздела  подзаголовка разделов {0}")
    void selenideSiteButtonsTextCSV(String section, String title, String subtitle) {
        open("https://www.cypress.io/");
        $$(".styled__NavList-sc-16oj5lj-3 li a").find(text(section)).click();
        $$(".Hero-TagLine").filter(visible)
                .shouldHave(CollectionCondition.textsInAnyOrder(title));
        $$(".Hero-ByLine").filter(visible)
                .shouldHave(CollectionCondition.textsInAnyOrder(subtitle));

    }

    @EnumSource(Sections.class)
    @ParameterizedTest
    void selenideSiteButtonsTextENUM(Sections sections) {
        open("https://www.cypress.io/");
        $$(".styled__NavList-sc-16oj5lj-3 li a").find(text(sections.name())).shouldBe(visible);
    }

    @ValueSource(strings = {"Features", "How it works","Dashboard","Pricing"})
    @ParameterizedTest
    void selenideSiteButtonsTextARRAY(String sectionname) {
        open("https://www.cypress.io/");
        $$(".styled__NavList-sc-16oj5lj-3 li a").find(text(sectionname)).shouldBe(visible);
    }
}

