package de.likeherotozero.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;

    // Benutzer und Passwörter
    private static final Map<String, String> USER_CREDENTIALS = new HashMap<>();

    static {
        USER_CREDENTIALS.put("Admin", "4mail99$");
        USER_CREDENTIALS.put("Wissenschaftler1", "test");
        USER_CREDENTIALS.put("Wissenschaftler2", "test");
    }

    public String login() {
        String validPassword = USER_CREDENTIALS.get(username);
        if (validPassword != null && validPassword.equals(password)) {
            return "scientist/dataEntry.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login fehlgeschlagen", "Ungültige Anmeldeinformationen"));
            return null;
        }
    }

    // Getter und Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
