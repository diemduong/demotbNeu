package demoTB;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
@Table(name = "APP_PERSON")
public class Person {

    @Id
    @Column(name="ID", nullable=false)
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id=0;

    @NotEmpty
    @Column(name="VORNAME", nullable=false)
    private String vorname;

    @NotEmpty
    @Column(name="NACHNAME", nullable=false)
    private String nachname;

    @Column(name="TELEFONUMMER", nullable=false)
    private String telefon;

    @Column(name="ADRESSE", nullable=false)
    private String adresse;

    @Column(name="EMAIL", nullable=false)
    private String email;

    public Person () {
        super();
    }
    public Person(long id, String vorname, String nachname, String telefon, String adresse, String email) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.telefon = telefon;
        this.adresse = adresse;
        this.email = email;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}

