package com.eazybytes.eazyschool.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.eazybytes.eazyschool.model.contact;
import com.eazybytes.eazyschool.service.contactService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class contactController {
    private static final Logger log = Logger.getLogger(contactController.class.getName());
    private contactService contactService;
    @Autowired
    public contactController(contactService contactService) {
        this.contactService = contactService;
    }
    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact.html";
    }

    @RequestMapping(value = "/contact",method = RequestMethod.POST)
    public ModelAndView saveMessage(contact contact) {

      boolean bt=  contactService.saveContact(contact);
     return new ModelAndView("contact.html", "contact", contact);
    }
}
