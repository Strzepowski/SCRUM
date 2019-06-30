package pl.sda.SCRUM.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;

import pl.sda.SCRUM.model.Contact;
import pl.sda.SCRUM.repository.ContactRepository;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.SCRUM.service.ContactService;

import java.util.Optional;


@RequestMapping
@Controller
public class ContactController {

    private final ContactRepository contactRepository;

    private  final ContactService contactService;

       @Autowired
        public ContactController(ContactRepository contactRepository, ContactService contactService) {
        this.contactRepository = contactRepository;
        this.contactService = contactService;
    }

    @PutMapping("/{id}")
    @ResponseBody
    ResponseEntity<Contact> putContact(@RequestBody Contact contact, @PathVariable("id") Integer id) {
        Optional<Contact> byId = contactRepository.findById(id);
        if (!byId.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(contactRepository.save(contact));
        } else {
            Contact contact1 = byId.get();
            contact1.setFirstName(contact.getFirstName());
            contact1.setLastName(contact.getLastName());
            contact1.setPhone(contact.getPhone());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(contactRepository.save(contact1));
        }
    }

    @GetMapping
    public ModelAndView getContacts(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("contacts", contactService.getContacts());
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    ResponseEntity<Contact> deleteContact(@PathVariable("id") Integer id) {
        if (contactService.findById(id).isPresent()) {
            contactService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



    @PostMapping("/contact/add")
    ModelAndView contactAdd(@RequestBody Contact contact){
        Contact newContact = contactService.addContact(contact);
        return new ModelAndView("index", "newContact", newContact);
    }


    

    @RequestMapping(value = "/{addContact}", method = RequestMethod.GET)
    String addContact(){
        return "addContact";
    }

    @GetMapping("/contact")
    ModelAndView concatById(@RequestParam("id") int id){


        Optional<Contact> concatById = contactService.getConcatById(id);
        System.out.println(concatById.get());

        if(concatById.isPresent()){
            return new ModelAndView("edit", "concat", concatById.get());
        }
        return getContacts();

    }

}


