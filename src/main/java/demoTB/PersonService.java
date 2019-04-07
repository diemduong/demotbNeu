package demoTB;
import java.util.List;

public interface PersonService {

    Person findById(long id);
    Person findByVorname(String vorname);
    Person findByNachname(String nachname);
    Person findByTelefon(String telefon);
    Person findByAdresse(String adresse);
    Person findByEmail(String email);
    Person findOne(long id);
    Person savePerson(Person person);
    long countPersons();
    void deletePersonById(long id);
    void deleteAllPerson();
    List<Person> findAllPersons();
    boolean isPersonExist(Person person);
}
