package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.controller.contactController;
import com.eazybytes.eazyschool.model.contact;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class contactService {
    private static final Logger log = Logger.getLogger(contactController.class.getName());
    public  boolean saveContact(contact contact) {
      log.info(contact.toString());
      return true;
    }
}
