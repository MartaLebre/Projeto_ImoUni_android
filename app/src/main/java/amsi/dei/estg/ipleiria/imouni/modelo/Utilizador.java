package amsi.dei.estg.ipleiria.imouni.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilizador {
    private int id;
    private String username, email, password, primeiroNome, ultimoNome, numeroTelemovel, genero;

    private Pattern pattern;
    private Matcher matcher;

    public Utilizador(String primeiroNome, String ultimoNome, String username, String email, String password, String numeroTelemovel, String genero) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.numeroTelemovel = numeroTelemovel;
        this.genero = genero;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getNumeroTelemovel() {
        return numeroTelemovel;
    }

    public void setNumeroTelemovel(String numeroTelemovel) {
        this.numeroTelemovel = numeroTelemovel;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
