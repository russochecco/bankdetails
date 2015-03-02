package org.frusso.bankdetails.customtag;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.frusso.bankdetails.domain.Card;

public class CardNumberMaskTag extends SimpleTagSupport {

    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    StringWriter sw = new StringWriter();

    public void doTag()
            throws JspException, IOException {
        if (value != null) {
            JspWriter out = getJspContext().getOut();
            out.println(Card.getCardNumberMask(value));
        } else {
            getJspBody().invoke(sw);
            getJspContext().getOut().println(sw.toString());
        }
    }
}
