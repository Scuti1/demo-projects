package mn.gov.itc.demo.spring2.web.data.dao;

import mn.gov.itc.demo.spring2.web.data.dto.PersonPublicDTO;
import mn.gov.itc.demo.spring2.web.data.entity.Person;
import mn.gov.itc.demo.spring2.web.data.projection.PersonView;
import mn.gov.itc.demo.spring2.web.data.type.GenderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDAO extends JpaRepository<Person, Long> {

    @Query("select new mn.gov.itc.demo.spring2.web.data.dto.PersonPublicDTO(o) from Person o")
    List<PersonPublicDTO> findAllDto();

    @Query("select new mn.gov.itc.demo.spring2.web.data.dto.PersonPublicDTO(o.firstName,o.lastName,o.gender) from Person o")
    List<PersonPublicDTO> findAllConstructor();

    List<PersonView> findAllByGender(@Param("gender") GenderType gender);
}
