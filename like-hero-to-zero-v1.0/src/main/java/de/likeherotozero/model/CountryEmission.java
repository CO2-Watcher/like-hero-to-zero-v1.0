package de.likeherotozero.model;

import javax.persistence.*;

@Entity
@Table(name = "country_emission")
public class CountryEmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private int year;
    private long population;

    @Column(name = "co2_total")
    private double emissionKt;

    @Column(name = "co2_per_capita")
    private double co2PerCapita;

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getEmissionKt() {
        return emissionKt;
    }

    public void setEmissionKt(double emissionKt) {
        this.emissionKt = emissionKt;
    }

    public double getCo2PerCapita() {
        return co2PerCapita;
    }

    public void setCo2PerCapita(double co2PerCapita) {
        this.co2PerCapita = co2PerCapita;
    }
}
