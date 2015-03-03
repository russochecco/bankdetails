package org.frusso.bankdetails.controller.web;

import com.google.common.io.CharStreams;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.frusso.bankdetails.domain.Card;
import org.frusso.bankdetails.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CardController {

    private static final Log LOGGER = LogFactory.getLog(CardController.class);
    private static final DateFormat dateFormat = new SimpleDateFormat("MMM-yyyy", Locale.US);

    @Autowired
    private CardService cardService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String listPostHandler(@RequestParam String action) {
        String view = null;
        if (action.equals("manually")) {
            view = "redirect:manually";
        } else if (action.equals("upload")) {
            view = "redirect:upload";
        }
        return view;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadPostHandler(@RequestParam String action, @RequestParam(value = "uploadControl") MultipartFile file) {
        String view = null;
        if (action.equals("back")) {
            view = "redirect:list";
        } else if (action.equals("upload")) {
            if (file != null) {
                try {
                    boolean redirect = false;
                    String content = CharStreams.toString(new InputStreamReader(file.getInputStream(), "UTF-8"));
                    String[] items = content.trim().split(",");
                    for (int i = 0; i < items.length / 3; i++) {
                        int offset = i * 3;
                        List<String> data = new ArrayList<String>();
                        for (int j = offset; j < offset + 3; j++) {
                            data.add(items[j]);
                        }
                        if (data.size() > 0) {
                            String bankName = data.get(0).trim();
                            String cardNumber = data.get(1).trim();
                            Date dateExpire = dateFormat.parse(data.get(2).trim());
                            Card card = new Card(bankName, cardNumber, dateExpire);
                            LOGGER.info("Insert card: " + card);
                            cardService.save(card);
                            if (!redirect) {
                                redirect = true;
                            }
                        }
                    }
                    if (redirect) {
                        view = "redirect:list";
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }
        return view;
    }

    @RequestMapping(value = "/manually", method = RequestMethod.GET)
    public String manually(Model uiModel) {
        LOGGER.info("Add a new card");
        uiModel.addAttribute("card", new Card());
        return "manually";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {
        return "upload";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGetHandler(Model uiModel) {
        LOGGER.info("Listing cards");
        List<Card> cards = cardService.findAll();
        uiModel.addAttribute("cards", cards);
        LOGGER.info("No. of contacts: " + cards.size());
        return "list";
    }

    @RequestMapping(value = "/manually", method = RequestMethod.POST)
    public String manuallyPostHandler(@RequestParam String action, @Valid Card card, BindingResult bindingResult) {
        String view = null;
        if (action.equals("back")) {
            view = "redirect:list";
        } else if (action.equals("save")) {
            if (!bindingResult.hasErrors()) {
                LOGGER.info("Insert card: " + card);
                cardService.save(card);
                view = "redirect:list";
            }
        }
        return view;
    }

    /**
     * Register property editors
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
