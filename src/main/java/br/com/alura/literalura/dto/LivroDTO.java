package br.com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//CLASS RECORD DE PERSONALIZAÇÃO
@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(@JsonAlias({"title"}) String titulo,
                       @JsonAlias({"authors"}) List<AutorDTO> autors,
                       @JsonAlias("languages") List<String> idiomas,
                       @JsonAlias("download_count") Integer numberoDeDownloads) {}