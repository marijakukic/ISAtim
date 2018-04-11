package ftn.dto;

import ftn.model.Segment;

public class MestoDTO {

    private Long id;

    private String naziv;

    private Segment segment;

    private Long salaId;

    private Integer x;

    private Integer y;

    public MestoDTO() {
    }

    public MestoDTO(Long id, String naziv, Segment segment, Long salaId, Integer x, Integer y) {
        this.id = id;
        this.naziv = naziv;
        this.segment = segment;
        this.salaId = salaId;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
