package mn.gov.itc.demo.spring2.web.data.dao;

import mn.gov.itc.demo.spring2.web.data.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface PersonGraphqlDAO extends JpaRepository<Person, Long> {
}
