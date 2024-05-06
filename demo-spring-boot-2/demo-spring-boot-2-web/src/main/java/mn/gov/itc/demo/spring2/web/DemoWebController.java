package mn.gov.itc.demo.spring2.web;


import lombok.RequiredArgsConstructor;
import mn.gov.itc.demo.spring2.web.data.dao.PersonDAO;
import mn.gov.itc.demo.spring2.web.data.dto.PersonPublicDTO;
import mn.gov.itc.demo.spring2.web.data.projection.PersonView;
import mn.gov.itc.demo.spring2.web.data.type.GenderType;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/demo/web")
@RequiredArgsConstructor
public class DemoWebController {
    private final PersonDAO personDAO;

    @GetMapping("personsMap")
    public List<PersonPublicDTO> personsMAP() {
        return personDAO.findAll().stream().map(e -> {
            PersonPublicDTO dto = new PersonPublicDTO();
            BeanUtils.copyProperties(e, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("personsDTO")
    public List<PersonPublicDTO> personsDTO() {
        return personDAO.findAllDto();
    }

    @GetMapping("persons-by-gender")
    public List<PersonView> personViews(@RequestParam("gender") GenderType gender) {
        return personDAO.findAllByGender(gender);
    }

    @GetMapping("error")
    public String error() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "this is custom error!");
    }
}
