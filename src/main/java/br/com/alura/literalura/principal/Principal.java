package br.com.alura.literalura.principal;

import br.com.alura.literalura.dto.ApiResponseDTO;
import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumirApi;
import br.com.alura.literalura.service.ConverteData;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Principal {
    // CRIANDO OS DADOS DA CLASS
    private final Scanner leitura = new Scanner(System.in);
    private final ConsumirApi consumirApi = new ConsumirApi();
    private final String endereco = "https://gutendex.com/books";
    private final ConverteData converter = new ConverteData();
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;
    private int opcao = -1;

    // CRIANDO OS DADOS DA CLASS
    public Principal(){}

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        //PERSONALIZANDO EXIBIÇÃO AO USUARIO
        System.out.println("\n" +
                "    / /                                              // | |\n" +
                "   / /        ( )   __  ___     ___        __       //__| |    //                 __        ___\n" +
                "  / /        / /     / /      //___) )   //  ) )   / ___  |   //     //   / /   //  ) )   //   ) )\n" +
                " / /        / /     / /      //         //        //    | |  //     //   / /   //        //   / /\n" +
                "/ /____/ / / /     / /      ((____     //        //     | | //     ((___( (   //        ((___( (\n");


        //CRIANDO O LOOP PARA A EXIBIÇÃO DO MENU PARA QUE O MESMO SE REPITA ATÉ A OPÇÃO 9 SEJA DIGITADA
        while (opcao != 9) {
            var menu = """                                        
                    Como podemos ajudar?
                    Digite a opção desejada: 
                    1 - Buscar livro pelo Título
                    2 - Listar todos os livros registrados
                    3 - Listar todos os autores registrados
                    4 - Listar todos os autores vivos em um determinado ano
                    5 - Listar todos os livros em um determinado idioma
                                                   
                    9 - Sair
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    addLivroData();
                    break;
                case 2:
                    listLivrosRegistrados();
                    break;
                case 3:
                    listAutoresRegistrados();
                    break;
                case 4:
                    listAutoresVivosEmUmAno();
                    break;
                case 5:
                    listLivrosPorUmIdioma();
                    break;
                case 9:
                    System.out.println("Obrigado pela preferência, até logo..." +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣦⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⢿⣿⠟⠋⠉⠀⠀⠀⠀⠉⠑⠢⣄⡀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⢠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣦⡀\n" +
                            "⠀⣀⠀⠀⢀⡏⠀⢀⣴⣶⣶⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⠇\n" +
                            "⣾⣿⣿⣦⣼⡀⠀⢺⣿⣿⡿⠃⠀⠀⠀⠀⣠⣤⣄⠀⠀⠈⡿⠋⠀\n" +
                            "⢿⣿⣿⣿⣿⣇⠀⠤⠌⠁⠀⡀⢲⡶⠄⢸⣏⣿⣿⠀⠀⠀⡇⠀⠀\n" +
                            "⠈⢿⣿⣿⣿⣿⣷⣄⡀⠀⠀⠈⠉⠓⠂⠀⠙⠛⠛⠠⠀⡸⠁⠀⠀\n" +
                            "⠀⠀⠻⣿⣿⣿⣿⣿⣿⣷⣦⣄⣀⠀⠀⠀⠀⠑⠀⣠⠞⠁⠀⠀⠀\n" +
                            "⠀⠀⠀⢸⡏⠉⠛⠛⠛⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠸⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⢿⣿⣿⣿⣿⡄⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⢷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣿⣿⣿⣿⡀⠀⠀⠀\n" +
                            "⠀⠀⠀⢸⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⡇⠀⠀⠀\n" +
                            "⠀⠀⠀⢸⣿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⡟⠻⠿⠟⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⣿⣿⣿⣿⣶⠶⠤⠤⢤⣶⣾⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠹⣿⣿⣿⠏⠀⠀⠀⠈⢿⣿⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠈⠉⠉⠀⠀⠀⠀⠀⠀⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    break;
                default:
                    System.out.println("Opção inválida...\n" +
                            "Por favor, digite uma opção correta!");
            }
        }
    }

    //CRIANDO METODOS
    //Metodo para povoar o livro desejado
    private void addLivroData() {
        Livro livro = searchLivroByTituloWeb();

        if (livro == null){
            System.out.println("O livro informado não foi localizado.");
        }else{
            Autor autor = livro.getAutor();

            if (isLivroRegistrado(livro.getTitulo())) {
                System.out.println("O livro " + livro + " já está cadastrado em sistema.");
            }else{
                Autor autorOnDatabase = getAutorOnDatabase(autor.getNome());

                if ( autorOnDatabase == null){
                    autorRepository.save(autor);
                }else{
                    livro.setAutor(autorOnDatabase);
                }

                livroRepository.save(livro);
                System.out.println(livro);
            }
        }
    }
    //Metodo para pesquisar o livro na API pelo titulo desejado pelo usuario
    private Livro searchLivroByTituloWeb(){
        System.out.println("Por gentileza, informe o título do livro para busca:");
        String tituloLivro = leitura.nextLine();

        var apiUrl = endereco + "/?search=" + tituloLivro.replace(" ", "%20");
        var json = consumirApi.getData(apiUrl);

        ApiResponseDTO apiResponse = converter.getData(json, ApiResponseDTO.class);
        Optional<LivroDTO> livroBuscado = apiResponse.livroData().stream().findFirst();

        if (livroBuscado.isPresent()){
            LivroDTO livroData = livroBuscado.get();
            return new Livro(livroData);
        }

        return null;
    }

    private boolean isLivroRegistrado(String tituloLivro){
        Optional<Livro> livro = livroRepository.findByTituloContainingIgnoreCase(tituloLivro);
        return livro.isPresent();
    }

    private Autor getAutorOnDatabase(String autorNome){
        Optional<Autor> autor = autorRepository.findByNomeContainingIgnoreCase(autorNome);
        return autor.orElse(null);
    }

    private void listLivrosRegistrados(){
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()){
            System.out.println("Até o momento, nenhum livro foi registrado.");
        }else{
            livros.forEach(System.out::println);
        }
    }

    private void listAutoresRegistrados(){
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()){
            System.out.println("Até o momento, nenhum autor foi registrado.");
        }else{
            autores.forEach(a -> {
                Set<Livro> autorLivros = livroRepository.findByAutor(a);
                a.setLivros(autorLivros);
                System.out.println(a);
            });
        }
    }

    //Metodo para listar os autores vivos escolhidos em um ano escolhido pelo usuario
    private void listAutoresVivosEmUmAno(){
        int year = 0;

        while(year <= 0){
            System.out.println("Por gentileza, informe o ano desejado: ");
            year = leitura.nextInt();
            leitura.nextLine();

            if (year <= 0 ) {
                System.out.println("Ano inválido. " +
                        "Tente novamente!");
            }
        }

        List<Autor> autores = autorRepository.findAutoresVivosNoAno(year);

        if (autores.isEmpty()){
            System.out.println("Nenhum autor encontrado.");
        }else{
            System.out.println("Os autores vivos no ano de " + year + " são: ");
            autores.forEach(System.out::println);
            System.out.println("*********************** TOTAL DE AUTORES VIVOS ***********************");
            System.out.println("Total de autores vivos: " + autores.size());
            System.out.println("******************************************************");
        }
    }

    //Metodo para listar todos os livros cadastrados por um idioma escolhido pleo usuario
    private void listLivrosPorUmIdioma(){
        System.out.println("""
            Digite o idioma pretendido:
            
            Inglês (en)
            Português (pt)
            Espanhol (es)
            """);
        System.out.println("Digite o código do idioma desejado: ");
        String codigoDoIdioma = leitura.nextLine();

        List<Livro> livros = livroRepository.findByIdiomasContainingIgnoreCase(codigoDoIdioma);

        if (livros.isEmpty()){
            System.out.println("Nenhum livro encontrado no idioma especificado.");
        }else{
            livros.forEach(System.out::println);
            System.out.println("Total de livros encontrados: " + livros.size());
        }

    }

    //    private void processarRespostaJSON(String jsonResponse) {
//        JSONObject jsonObject = new JSONObject(jsonResponse);
//        JSONArray livros = jsonObject.getJSONArray("results");
//
//        if (livros.length() > 0) {
//            JSONObject livro = livros.getJSONObject(0);
//            String titulo = livro.getString("title");
//            String autor = livro.getJSONArray("authors").getJSONObject(0).getString("name");
//
//            System.out.println("Título: " + titulo);
//            System.out.println("Autor: " + autor);
//        } else {
//            System.out.println("Nenhum livro encontrado com o título fornecido.");
//        }
//    }

}
