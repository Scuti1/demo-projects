package mn.gov.itc.demo.spring2.web.data.entity;

import lombok.Data;
import mn.gov.itc.demo.spring2.web.data.type.GenderType;

import javax.persistence.*;

@Entity
@Table(name = "PERSONS")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "REGNO")
    private String regNo;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private GenderType gender;
}
