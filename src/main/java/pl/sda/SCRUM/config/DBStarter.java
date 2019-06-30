package pl.sda.SCRUM.config;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.sda.SCRUM.model.Contact;
import pl.sda.SCRUM.repository.ContactRepository;

@Component
public class DBStarter implements ApplicationRunner {


    private ContactRepository contactRepository;

    @Autowired
    protected DBStarter(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Fairy fairy = Fairy.create();


        for(int i = 0; i < 100; i++){
            Person person = fairy.person();
            Contact contact = new Contact();
            contact.setFirstName(person.getFirstName());
            contact.setLastName(person.getLastName());
            contact.setPhone(person.getTelephoneNumber());
            contactRepository.save(contact);
            System.out.println(contact.toString());
        }

    }
}
