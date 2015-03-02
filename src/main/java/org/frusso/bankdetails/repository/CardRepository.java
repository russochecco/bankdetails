package org.frusso.bankdetails.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.frusso.bankdetails.domain.Card;

public interface CardRepository extends CrudRepository<Card, Long> {

    public List<Card> findByCardNumber(String cardNumber);
}