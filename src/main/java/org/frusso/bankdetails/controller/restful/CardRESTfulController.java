package org.frusso.bankdetails.controller.restful;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.frusso.bankdetails.domain.Card;
import org.frusso.bankdetails.domain.Cards;
import org.frusso.bankdetails.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/card")
public class CardRESTfulController {

    private static final Log LOGGER = LogFactory.getLog(CardRESTfulController.class);

    @Autowired
    private CardService cardService;

    @RequestMapping(value = "/listdata", method = RequestMethod.GET)
    @ResponseBody
    public Cards listdata() {
        LOGGER.info("Lists all cards");
        return new Cards(cardService.findAll());
    }

    @RequestMapping(value = "/{cardnumber}", method = RequestMethod.GET)
    @ResponseBody
    public Card findByNumber(@PathVariable String cardnumber) {
        LOGGER.info("Find card number " + cardnumber);
        return cardService.findByCardNumber(cardnumber);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Card create(@RequestBody Card card) {
        LOGGER.info("Create card " + card.toString());
        cardService.save(card);
        return card;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Card card, @PathVariable Long id) {
        LOGGER.info("Update card id " + id);
        cardService.save(card);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        LOGGER.info("Delete card id " + id);
        cardService.delete(cardService.findById(id));
    }
}
