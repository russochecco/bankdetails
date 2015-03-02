package org.frusso.bankdetails.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "card")
public class Card implements Serializable, Comparable<Card> {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-yyyy");

    private Long id;
    private int version;
    private String bankName;

    @NotNull
    @Size(min = 15, max = 16, message = "*")
    private String cardNumber;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @NotNull(message = "*")
    @Future
    private Date dateExpire;

    public Card() {
    }

    public Card(String bankName, String cardNumber, Date dateExpire) {
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.dateExpire = dateExpire;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    @Column(name = "BANK_NAME")
    public String getBankName() {
        return bankName;
    }

    @Column(name = "CARD_NUMBER")
    public String getCardNumber() {
        return cardNumber;
    }

    @Column(name = "DATE_EXPIRE")
    @Temporal(TemporalType.DATE)
    public Date getDateExpire() {
        return dateExpire;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", bankName, cardNumber, simpleDateFormat.format(dateExpire));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setDateExpire(Date dateExpire) {
        this.dateExpire = dateExpire;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Card)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Card target = (Card) obj;
        return this.cardNumber.equals(target.cardNumber) && this.bankName.equals(target.bankName);
    }

    @Override
    public int hashCode() {
        return this.cardNumber.hashCode() + this.bankName.hashCode() * 17;
    }

    @Override
    public int compareTo(Card target) {
        int result = target.dateExpire.compareTo(this.dateExpire);
        if (result == 0 && !target.equals(this)) {
            result = -1;
        }
        return result;
    }

    public static String getCardNumberMask(String cardNumber) {
        String result = cardNumber;
        StringBuilder builder = new StringBuilder();
        if (cardNumber != null) {
            String value = cardNumber.trim();
            builder.append(value.substring(0, 4));
            builder.append("-xxxx-xxxx-");
            String bound = value.substring(12, cardNumber.length());
            bound = bound.replaceAll("\\d", "x");
            builder.append(bound);
            result = builder.toString();
        }
        return result;
    }
}
