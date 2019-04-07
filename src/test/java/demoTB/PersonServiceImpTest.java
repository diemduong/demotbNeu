package demoTB;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceImpTest {

    @TestConfiguration
    static class PersonServiceImplTestContextConfiguration {

        @Bean
        public PersonService personService() {
            return new PersonServiceImp();
        }
    }

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        Person alex = new Person(20,"alex","abc","012546987","irgendwo","email@email.com");

        Mockito.when(personRepository.findByVorname(alex.getVorname())).thenReturn(alex);
        Mockito.when(personRepository.findByNachname(alex.getNachname())).thenReturn(alex);
    }

    @Test
    public void findVornameBeFound() {
        String vorname = "alex";
        Person found = personService.findByVorname(vorname);

        assertThat(found.getVorname().equals(vorname));
    }

    @Test
    public void findNachnameBeFound() {
        String nachname = "abc";
        Person found = personService.findByNachname(nachname);

        assertThat(found.getNachname().equals(nachname));
    }
}
