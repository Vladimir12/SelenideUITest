package com.gmail.smiyan;

import org.junit.Test;

import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.type;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import com.codeborne.selenide.Configuration;

public class PromBasketTest {

    @Test
    public void tests() {
        userCanSelectRegion();
        userCanSelectedProduct();
        addItemToCart();
    }

    public void userCanSelectRegion() {

        Configuration.reportsFolder = "reports/PromTest";
        open("http://prom.ua/");
        $("#search_text").setValue("Кепки,Бейсболки");
        $(byText("Все регионы")).shouldBe(visible).click();
        $(By.name("city_search")).setValue("Киев");
        $(byText("Киевская область")).shouldBe(visible).click();
        screenshot("prom_test");
        $("#search_submit").shouldHave(type("submit")).click();//переход на следующую страницу
    }

    public void userCanSelectedProduct() {

        $$(".js-btn-text").shouldHave(size(4));
        $(".js-btn-text").shouldHave(text("Киевская обл."));
        $("#search_text").setValue("Кепка Jordan Air");
        $("#search_submit").shouldHave(type("submit")).click();//переход на следующую страницу

        $(".b-search-bar-total-results__header.h-break-word").
                shouldHave(text("\"Кепка Jordan Air\" в категории бейсболки и кепки  в "),
                        text("Киевской области"));
        $$(".b-text-hider.b-text-hider_type_two-lines.medium-text.h-mb-10.h-mt-15").shouldHave(size(10));
        $(By.xpath("//a[@title='Зеленая с черным кепка Jordan Air']")).click();//переход на страницу товара
    }

    public void addItemToCart() {
        getWebDriver().getWindowHandles();
        $(By.xpath("//span[text()='Зеленая с черным кепка Jordan Air']")).shouldHave(visible);
    }
}

