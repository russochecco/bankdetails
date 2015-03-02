package org.frusso.bankdetails.service;

import java.util.List;
import org.frusso.bankdetails.domain.Card;

public interface CardService {

    List<Card> findAll();

    Card findById(Long id);

    Card findByCardNumber(String cardNumber);

    Card save(Card card);

    void delete(Card card);
}
