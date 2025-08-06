package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTituloContainingIgnoreCase(String bookTitle);

    List<Livro> findByIdiomasContainingIgnoreCase(String languageCode);


    Set<Livro> findByAutor(Autor a);
}
