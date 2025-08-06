package br.com.alura.literalura.model;

import br.com.alura.literalura.dto.LivroDTO;
import jakarta.persistence.*;

@Entity(name = "Book")
@Table(name = "books") //utilizando as anotações para criar a tebela no banco de dados e renomeando para livros
public class Livro {
    // CRIANDO OS DADOS DA CLASS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idiomas;
    private Integer numberoDeDownloads;
    @ManyToOne
    private Autor autor;

    // CRIANDO O CONSTRUTOR DA CLASS
    public Livro(){}

    public Livro(LivroDTO data) {
        this.titulo = data.titulo();
        this.autor = new Autor(data.autors().getFirst());
        this.idiomas = data.idiomas().getFirst();
        this.numberoDeDownloads = data.numberoDeDownloads();
    }

    public Livro(String titulo, Autor autor, String idiomas, Integer numberoDeDownloads){
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.numberoDeDownloads = numberoDeDownloads;
    }

    // CRIANDO OS GETTERS E SETTERS DA CLASS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumberoDeDownloads() {
        return numberoDeDownloads;
    }

    public void setNumberoDeDownloads(Integer numberoDeDownloads) {
        this.numberoDeDownloads = numberoDeDownloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "\n********************* LIVRO *********************" + '\n' +
                "Título:  " + titulo + '\n' +
                "Autor: " + autor.getNome() + '\n' +
                "Idioma: " + idiomas + '\n' +
                "Número de downloads: " + numberoDeDownloads +'\n' +
                "**************************************************" + '\n';
    }
}
