package com.ys.isGood;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class test {

    enum CreditCard {

        SHINHAN("신한", Arrays.asList("Mr.Life 카드", "Deep Dream 카드", "Deep Oil 카드")),
        KB("국민", Arrays.asList("톡톡D 카드", "티타늄 카드", "다담 카드")),
        NH("농협", Arrays.asList("올바른 FLEX 카드", "테이크 5 카드", "NH 올원 파이카드"));

        private final String enterprise;
        private final List<String> cards;

        CreditCard(String name, List<String> cards) {
            this.enterprise = name;
            this.cards = cards;
        }

        static CreditCard getCard(String cardName) {
            return Arrays.stream(CreditCard.values())
                    .filter(creditCard -> creditCard.name().equals(cardName))
                    .findFirst()
                    .orElse(null);
        }
    }

    @Test
    void CreditCardService(){

        System.out.println(CreditCard.getCard("SHINHAN"));

    }



}
