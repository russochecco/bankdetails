package org.frusso.bankdetails.service.jpa;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import org.frusso.bankdetails.domain.Card;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.frusso.bankdetails.repository.CardRepository;
import org.frusso.bankdetails.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service("cardService")
@Repository
@Transactional
public class CardServiceImpl implements CardService {

    private static final Log LOGGER = LogFactory.getLog(CardServiceImpl.class);

    @Autowired
    private CardRepository cardRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Card> findAll() {
        List<Card> cards = Lists.newArrayList(cardRepository.findAll());
        Collections.sort(cards);
        return cards;
    }

    @Override
    @Transactional(readOnly = true)
    public Card findByCardNumber(String cardNumber) {
        List<Card> card = cardRepository.findByCardNumber(cardNumber);
        if (card != null && card.size() > 0) {
            return card.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Card save(Card card) {
        LOGGER.info("Card saved with id: " + card.getId());
        return cardRepository.save(card);
    }

    @Override
    public void delete(Card card) {
        LOGGER.info("Card " + card.getId() + " deleted successfully");
        cardRepository.delete(card);
    }

    @Override
    @Transactional(readOnly = true)
    public Card findById(Long id) {
        return cardRepository.findOne(id);
    }
}
