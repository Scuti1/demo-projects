package mn.gov.itc.demo.spring2.web;

import lombok.RequiredArgsConstructor;
import mn.gov.itc.demo.spring2.web.data.dao.PersonDAO;
import mn.gov.itc.demo.spring2.web.data.entity.Person;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GraphController {
    private final PersonDAO personDAO;

    /*@QueryMapping
    List<Person> persons() {
        return personDAO.findAll();
    }*/

}
