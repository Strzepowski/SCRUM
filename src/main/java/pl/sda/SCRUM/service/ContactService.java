package pl.sda.SCRUM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.SCRUM.model.Contact;
import pl.sda.SCRUM.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact addContact(Contact contact){
        return contactRepository.save(contact);
    }

    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    public Optional<Contact> findById(int id) {
        return contactRepository.findById(id);
    }

    public  void deleteById(int id) {
        contactRepository.deleteById(id);
    }



}



