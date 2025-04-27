package de.likeherotozero.controller;

import de.likeherotozero.model.CountryEmission;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class DataEntryBean implements Serializable {

    private int year;
    private double population;
    private double co2Total;
    private double co2PerCapita;
    private String selectedCountry;

    private List<String> countries = Arrays.asList(
    		"Afghanistan", "Africa", "Albania", "Algeria", "Andorra", "Angola", "Anguilla", "Antigua and Barbuda",
            "Argentina", "Armenia", "Aruba", "Asia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain",
            "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
            "Bonaire Sint Eustatius and Saba", "Bosnia and Herzegovina", "Botswana", "Brazil", "British Virgin Islands",
            "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
            "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Cook Islands",
            "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Curacao", "Cyprus", "Czechia", "Democratic Republic of Congo",
            "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador",
            "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Europe", "European Union", "Faroe Islands",
            "Fiji", "Finland", "France", "French Polynesia", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece",
            "Greenland", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong",
            "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan",
            "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon",
            "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao", "Madagascar", "Malawi",
            "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico",
            "Micronesia (country)", "Moldova", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Myanmar",
            "Namibia", "Nauru", "Nepal", "Netherlands", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria",
            "Niue", "North America", "North Korea", "North Macedonia", "Norway", "Oceania", "Oman", "Pakistan", "Palau",
            "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar",
            "Romania", "Russia", "Rwanda", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon",
            "Saint Vincent and the Grenadines", "Samoa", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia",
            "Seychelles", "Sierra Leone", "Singapore", "Sint Maarten (Dutch part)", "Slovakia", "Slovenia", "Solomon Islands",
            "Somalia", "South Africa", "South America", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname",
            "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago",
            "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
            "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Wallis and Futuna",
            "World", "Yemen", "Zambia", "Zimbabwe"
    );

    // Persistenz
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emissionsPU");

    public String save() {
        CountryEmission emission = new CountryEmission();
        emission.setYear(year);
        emission.setPopulation((long) population);  
        emission.setEmissionKt(co2Total);           
        emission.setCo2PerCapita(co2PerCapita);
        emission.setCountry(selectedCountry);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(emission);
            tx.commit();
            return "addEmission?faces-redirect=true";
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return "error.xhtml";
        } finally {
            em.close();
        }
    }

    // Getter & Setter

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getPopulation() { return population; }
    public void setPopulation(double population) { this.population = population; }

    public double getCo2Total() { return co2Total; }
    public void setCo2Total(double co2Total) { this.co2Total = co2Total; }

    public double getCo2PerCapita() { return co2PerCapita; }
    public void setCo2PerCapita(double co2PerCapita) { this.co2PerCapita = co2PerCapita; }

    public String getSelectedCountry() { return selectedCountry; }
    public void setSelectedCountry(String selectedCountry) { this.selectedCountry = selectedCountry; }

    public List<String> getCountries() { return countries; }
}
