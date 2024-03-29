package pl.sda.SCRUM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.SCRUM.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
