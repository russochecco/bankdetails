package org.frusso.bankdetails.domain;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "cardType")
@XmlRootElement(name = "cards", namespace = "org.frusso.bankdetails.domain")
public class Cards implements Serializable {

    private List<Card> cards;

    public Cards() {
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    @XmlElement(name = "card")
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
