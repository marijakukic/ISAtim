package ftn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private String spisakGlumaca;

    private String zanr;

    private String imeReditelja;

    private String trajanje;

    private String poster;

    private Double prosecnaOcena;

    private String kratakOpis;


}
