package com.formacion.block7crudvalidationparte1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPerson;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) throws Exception {
        if (usuario == null) {
            throw new Exception("Usuario no puede ser nulo");
        }
        if (usuario.length() > 10) {
            throw new Exception("Longitud de usuario no puede ser superior a 10 caracteres");
        }
        if (usuario.length() < 6) {
            throw new Exception("Longitud de usuario no puede ser inferior a 6 caracteres");
        }
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if (password == null) {
            throw new Exception("Password no puede ser nulo");
        }
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name == null) {
            throw new Exception("Nombre no puede ser nulo");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) throws Exception {
        if (companyEmail == null) {
            throw new Exception("Email de la empresa no puede ser nulo");
        }
        this.companyEmail = companyEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) throws Exception {
        if (personalEmail == null) {
            throw new Exception("Email personal no puede ser nulo");
        }
        this.personalEmail = personalEmail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws Exception {
        if (city == null) {
            throw new Exception("Ciudad no puede ser nula");
        }
        this.city = city;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) throws Exception {
        if (createdDate == null) {
            throw new Exception("Fecha de creación no puede ser nula");
        }
        this.createdDate = createdDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public Person orElse(Object o) {
        return null;
    }

    public void validate() {
    }
}
