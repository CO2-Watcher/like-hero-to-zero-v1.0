package de.likeherotozero.controller;

import de.likeherotozero.model.CountryEmission;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PublicViewBean implements Serializable {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emissionsPU");

    private String selectedCountry;

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public List<String> getCountries() {
        EntityManager em = emf.createEntityManager();
        List<String> countries = em.createQuery("SELECT DISTINCT e.country FROM CountryEmission e", String.class).getResultList();
        em.close();
        return countries;
    }

    public List<CountryEmission> getLatestEmissionsByCountry() {
        if (selectedCountry == null || selectedCountry.isEmpty()) return List.of();
        EntityManager em = emf.createEntityManager();
        List<CountryEmission> result = em.createQuery(
                        "SELECT e FROM CountryEmission e WHERE e.country = :country ORDER BY e.year DESC",
                        CountryEmission.class)
                .setParameter("country", selectedCountry)
                .getResultList();
        em.close();
        return result;
    }
}
