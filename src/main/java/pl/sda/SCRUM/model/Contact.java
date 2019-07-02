package pl.sda.SCRUM.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(of = "id")

public class Contact {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;

    private String lastName;

    private String phone;

}
