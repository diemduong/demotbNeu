package demoTB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/tbDemo")
@CrossOrigin("*")
public class DemotbController {
    public static final Logger logger = LoggerFactory.getLogger(DemotbApplication.class);

    @Autowired
    private PersonService personService;

    @PostMapping(path = "/addi",consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Person> addNewPerson(@Valid @RequestBody Person person) {
        if (personService.isPersonExist(person)) {
            return new ResponseEntity(new CustomErrorType("Die Person kann nicht hinzugefügt werden. " +
                    person.getVorname() + " existiert schon."),HttpStatus.CONFLICT);
        }
        if (!person.getTelefon().matches("[0-9]+")) {
            return new ResponseEntity(new CustomErrorType("Die Telefonummer erhält nur number."),HttpStatus.CONFLICT);
        }
        if (!person.getEmail().contains("@")) {
            return new ResponseEntity(new CustomErrorType("Die Emailadresse muss @ erhalten."),HttpStatus.CONFLICT);
        }
        person.setId(personService.findAllPersons().get((int)personService.countPersons()-1).getId() +1);
        personService.savePerson(new Person(person.getId(),person.getVorname(),person.getNachname(),person.getTelefon(),
                person.getAdresse(),person.getEmail()));
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deletePerson (@PathVariable long id) {
        Person person = personService.findById(id);
        if (person==null) {
            return new ResponseEntity(new CustomErrorType("Person mit ID " + id + " existiert nicht."),
                    HttpStatus.NOT_FOUND);
        }
        personService.deletePersonById(id);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path="/deleteAll")
    public String deleteall() {
        personService.deleteAllPerson();
        return "Die Liste der Personen wird erfolgreich gelöscht.";
    }

    @GetMapping(path = "/findID/{id}")
    public ResponseEntity<?> findbyId(@PathVariable long id) {
        Person person = personService.findById(id);
        if (person==null) {
            return new ResponseEntity(new CustomErrorType("Person mit ID " + id
                    + " existiert nicht."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @GetMapping(path="/list")
    public @ResponseBody Iterable<Person> getAllPersons() {
        // This returns a JSON or XML with the users
        return personService.findAllPersons();
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable long id,@RequestBody Person person) {
        Person toFind = personService.findOne(id);
        if (toFind==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        toFind.setVorname(person.getVorname());
        toFind.setNachname(person.getNachname());
        toFind.setTelefon(person.getTelefon());
        toFind.setAdresse(person.getAdresse());
        toFind.setEmail(person.getEmail());
        Person complete = personService.savePerson(toFind);
        return ResponseEntity.accepted().build();
    }
//    @RequestMapping(value = "/person/", method = RequestMethod.GET)
//    public ResponseEntity<List<Person>> listPerson() {
//        List<Person> listP = personService.findAllPersons();
//        if (listP.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<List<Person>>(listP, HttpStatus.OK);
//    }


}

