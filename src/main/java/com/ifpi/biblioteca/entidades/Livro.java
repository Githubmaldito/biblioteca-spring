package com.ifpi.biblioteca.entidades;
import java.util.Objects;

public class Livro {
    String isbn;
    String titulo;
    String autor;
    boolean emprestimo;

    
    public Livro( String titulo, String autor, boolean emprestimo, String isbn) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.emprestimo = emprestimo;
    }

    public String getIsbn() {
        return this.isbn;
    }public void setIsbn(String isbn) {
        this.isbn = isbn;}

    public String getTitulo() {
        return this.titulo;
    }public void setTitulo(String titulo) {
        this.titulo = titulo;}

    public String getAutor() {
        return this.autor;
    }public void setAutor(String autor) {
        this.autor = autor;}

    public boolean isEmprestimo() {
        return this.emprestimo;
    }public boolean getEmprestimo() {
        return this.emprestimo;
    }public void setEmprestimo(boolean emprestimo) {
        this.emprestimo = emprestimo;}

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Livro)) {
            return false;
        }
        Livro livro = (Livro) o;
        return isbn == livro.isbn && Objects.equals(titulo, livro.titulo) && Objects.equals(autor, livro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, titulo, autor);
    }

    @Override
    public String toString() {
        return "{" +
            " isbn='" + getIsbn() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", autor='" + getAutor() + "'" +
            "}";
    }
    
}

