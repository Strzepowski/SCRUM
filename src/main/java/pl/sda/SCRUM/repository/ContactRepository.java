package pl.sda.ScrumAPP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.ScrumAPP.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
