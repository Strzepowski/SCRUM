package pl.sda.SCRUM.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@Component

public class Contact {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;

    private String lastName;

    private String phone;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}
