package pl.sda.SCRUM.controllers;


import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;

import pl.sda.SCRUM.model.Contact;
import pl.sda.SCRUM.repository.ContactRepository;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.SCRUM.service.ContactService;

import java.util.Map;
import java.util.Optional;


//@RequestMapping
@Controller
@ComponentScan("pl.sda.SCRUM")
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

    @DeleteMapping("/deleteContact")
    public ModelAndView deleteContact2(@RequestParam(value = "id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deleteContact");
//           modelAndView.addObject("contactDelete", contactService.findById(id));
        contactService.deleteById(id);
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView getContactDelete(@RequestParam(value = "id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("delete");
        modelAndView.addObject("contact", contactService.findById(id));
        return modelAndView;
    }


    /*@PostMapping("/add")
    ModelAndView contactAdd(@ModelAttribute Contact contact){
        Contact newContact = contactService.addContact(contact);
        return new ModelAndView("index", "index", newContact);
    }*/

    @PostMapping("/addContact")
    public String greetingSubmit(@ModelAttribute Contact contact) {
        return "result";
    }

    //to napewno dziala
    /*@PostMapping("/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    ModelAndView contactAdd(@ModelAttribute Contact contact){
        Contact newContact = contactService.addContact(contact);
        return new ModelAndView("index", "contact", newContact);
    }*/

    //to napewno dziala i cos wswietla ale nie dodaje do bazy
    /*@PostMapping("/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String formSubmit(@ModelAttribute Contact contact) {
        return "result";
    }*/
    
    //to napewno dziala
//    @RequestMapping(value = "/{addContact}", method = RequestMethod.GET)
//    String addContact(){
//        return "addContact";
//    }

    @GetMapping("/addContact")
    String formularz(){
        return "addContact";
    }

    /*@PostMapping("/addContact")
    public String addSubmit(@ModelAttribute Contact contact) {
        return "result";
    }

    @GetMapping("/addContact")
    public String addContactForm(Model model) {
        model.addAttribute(new Contact());
        return "addContact";
    }*/

    @GetMapping("/contact")
    ModelAndView concatById(@RequestParam("id") int id){
        Optional<Contact> concatById = contactService.getConcatById(id);
        if(concatById.isPresent()){
            return new ModelAndView("edit", "contact", concatById.get());
        }
        return getContacts();
    }

}


