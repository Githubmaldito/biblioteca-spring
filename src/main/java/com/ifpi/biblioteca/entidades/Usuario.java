package com.ifpi.biblioteca.entidades;
import java.util.Objects;

public class Usuario {
    String nome;
    String email;
    String matricula;

    public Usuario(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
    }

    public String getNome() {
        return this.nome;
    }public void setNome(String nome) {
        this.nome = nome;}

    public String getEmail() {
        return this.email;
    }public void setEmail(String email) {
        this.email = email;}

    public String getMatricula() {
        return this.matricula;
    }public void setMatricula(String matricula) {
        this.matricula = matricula;}


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && matricula == usuario.matricula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, matricula);
    }

    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", email='" + getEmail() + "'" +
            ", matricula='" + getMatricula() + "'" +
            "}";
    }
}