package demoTB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("personService")
@Transactional
public class PersonServiceImp implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public long countPersons() {
        return personRepository.count();
    }

    @Override
    public Person findById(long id) {
        return personRepository.findOne(id);
    }

    @Override
    public Person findByVorname(String vorname) {
        return personRepository.findByVorname(vorname);
    }

    @Override
    public Person findByNachname(String nachname) {
        return personRepository.findByNachname(nachname);
    }

    @Override
    public Person findByTelefon(String telefon) {
        return personRepository.findByTelefon(telefon);
    }

    @Override
    public Person findByAdresse(String adresse) {
        return personRepository.findByAdresse(adresse);
    }

    @Override
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePersonById(long id) {
        personRepository.delete(id);
//        for (long i = id+1; i <= countPersons()+1; i++) {
//            personRepository.findOne(i).setId(i-1);
//        }
    }

    @Override
    public void deleteAllPerson() {
        personRepository.deleteAll();
    }

    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person findOne (long id) {return personRepository.findOne(id);}
    @Override
    public boolean isPersonExist(Person person) {
        return (findByNachname(person.getNachname()) !=null) && (findByVorname(person.getVorname()) != null) &&
                (findByEmail(person.getEmail()) != null) && (findByAdresse(person.getAdresse()) != null);
    }
}
