package demoTB;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void findVorname_returnPerson() {
        Person alex = new Person(20,"alex","abc","012546987","irgendwo","email@email.com");
        testEntityManager.persist(alex);
        testEntityManager.flush();

        Person found = personRepository.findByVorname("alex");

        assertThat(found.getVorname().equals(alex.getVorname()));
    }

    @Test
    public void findNachname_returnPerson() {
        Person alex = new Person(20,"alex","abc","012546987","irgendwo","email@email.com");
        testEntityManager.persist(alex);
        testEntityManager.flush();

        Person found = personRepository.findByNachname("abc");

        assertThat(found.getNachname().equals(alex.getNachname()));
    }
}
