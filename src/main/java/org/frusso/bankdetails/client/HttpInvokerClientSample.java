package org.frusso.bankdetails.client;

import java.util.GregorianCalendar;
import java.util.List;
import org.frusso.bankdetails.domain.Card;
import org.frusso.bankdetails.service.CardService;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HttpInvokerClientSample {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:http-invoker-context.xml");
        ctx.refresh();
        CardService cardService = ctx.getBean("remoteCardService", CardService.class);

        System.out.println("Retrieve all cards:");
        List<Card> cards = cardService.findAll();
        listCards(cards);

        System.out.println("Retrieve card by number:");
        Card card = cardService.findByCardNumber("378673348965345");
        System.out.println(card);

        System.out.println("Update card:");
        card.setDateExpire(new GregorianCalendar(2016, 6, 0).getTime());
        cardService.save(card);
        card = cardService.findByCardNumber("378673348965345");
        System.out.println(card);

        if (cardService.findByCardNumber("378673348961222") == null) {
            System.out.println("Create new card:");
            Card newCard = new Card("American Express", "378673348961222", new GregorianCalendar(2018, 8, 0).getTime());
            cardService.save(newCard);
            System.out.println(newCard);
        }

        System.out.println("All cards:");
        cards = cardService.findAll();
        listCards(cards);
    }

    private static void listCards(List<Card> cards) {
        for (Card card : cards) {
            System.out.println(card);
        }
    }
}
