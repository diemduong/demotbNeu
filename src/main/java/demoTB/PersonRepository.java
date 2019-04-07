package demoTB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByVorname(String vorname);
    Person findByNachname(String nachname);
    Person findByTelefon(String telefon);
    Person findByAdresse(String adresse);
    Person findByEmail(String email);
    Person save(Person person);
}
