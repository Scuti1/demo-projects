package mn.gov.itc.demo.spring2.web.data.dto;


import lombok.Data;
import mn.gov.itc.demo.spring2.web.data.entity.Person;
import mn.gov.itc.demo.spring2.web.data.type.GenderType;
import org.springframework.beans.BeanUtils;

@Data
public class PersonPublicDTO {
    private String firstName;
    private String lastName;
    private GenderType gender;

    public PersonPublicDTO() {
    }

    public PersonPublicDTO(Person entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public PersonPublicDTO(String firstName, String lastName, GenderType gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }
}
