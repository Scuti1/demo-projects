package mn.gov.itc.demo.spring2.web.data.projection;

import mn.gov.itc.demo.spring2.web.data.type.GenderType;
import org.springframework.beans.factory.annotation.Value;

public interface PersonView {

    @Value("#{target.lastName.substring(0,1).toUpperCase() + '.' + target.firstName}")
    String getFullName();

    GenderType getGender();
}
