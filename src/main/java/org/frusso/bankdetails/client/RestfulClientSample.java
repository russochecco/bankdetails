package org.frusso.bankdetails.client;

import java.util.GregorianCalendar;
import org.frusso.bankdetails.domain.Card;
import org.frusso.bankdetails.domain.Cards;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class RestfulClientSample {

    private static final String URL_GET_ALL_CARDS
            = "http://localhost:8080/bankdetails/restful/card/listdata";
    private static final String URL_GET_CARD_BY_NUMBER
            = "http://localhost:8080/bankdetails/restful/card/{cardnumber}";
    private static final String URL_CREATE_CARD
            = "http://localhost:8080/bankdetails/restful/card/";
    private static final String URL_UPDATE_CARD
            = "http://localhost:8080/bankdetails/restful/card/{id}";
    private static final String URL_DELETE_CARD
            = "http://localhost:8080/bankdetails/restful/card/{id}";

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:restful-context.xml");
        ctx.refresh();

        RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);

        System.out.println("Retrieve all cards:");
        Cards cards = restTemplate.getForObject(URL_GET_ALL_CARDS, Cards.class);
        listCards(cards);

        System.out.println("Retrieve card by number:");
        Card card = restTemplate.getForObject(URL_GET_CARD_BY_NUMBER, Card.class, "378673348965345");
        System.out.println(card);

        System.out.println("Update card:");
        card.setDateExpire(new GregorianCalendar(2017, 6, 0).getTime());
        restTemplate.put(URL_UPDATE_CARD, card, card.getId());
        card = restTemplate.getForObject(URL_GET_CARD_BY_NUMBER, Card.class, "378673348965345");
        System.out.println(card);

        card = restTemplate.getForObject(URL_GET_CARD_BY_NUMBER, Card.class, "378673348961255");
        if (card == null) {
            System.out.println("Create new card:");
            Card newCard = new Card("American Express", "378673348961255", new GregorianCalendar(2019, 8, 0).getTime());
            restTemplate.postForObject(URL_CREATE_CARD, newCard, Card.class);
            System.out.println(newCard);
        }

        card = restTemplate.getForObject(URL_GET_CARD_BY_NUMBER, Card.class, "378673348965345");
        if (card != null) {
            System.out.println("Delete card:");
            restTemplate.delete(URL_DELETE_CARD, card.getId());
        }

        System.out.println("All cards:");
        cards = restTemplate.getForObject(URL_GET_ALL_CARDS, Cards.class);
        listCards(cards);
    }

    private static void listCards(Cards cards) {
        for (Card card : cards.getCards()) {
            System.out.println(card);
        }
    }
}
