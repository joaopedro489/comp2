package br.ufrj.dcc.comp2.ple.joaoPedro.lista4;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.lang.*;
/**
 * @author João Pedro Silva
 *
 *<h1>
 *     Classe dos Livros para o exercício da lista 4.
 *     A classe representa os livros da biblioteca escolar.
 *</h1>
 */
public class Livro {
    private int bookId;
    private String name;
    private int pageCount;
    private int point;
    private int authorId;
    private int typeId;
    /**
     * cadastroLivro()
     * <p>
     *     A função não recebe parametros, e ela cadastra um livro na biblioteca.
     *     Se por ventura o autor deste livro não esteja cadastrado,
     *     é possível cadastrá-lo antes de cadastrar o livro.
     * </p>
     */
    public static void cadastroLivro(){
        File fileLivro = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/books.tsv");
        InputStream livros;
        try{
            livros = new FileInputStream(fileLivro);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(livros));
        Scanner scanner = new Scanner(System.in);
        String linha;
        int id = 0;
        try{
            br.readLine();
            while((linha = br.readLine()) != null){
                String[] dados = linha.split("\t");
                try{
                    id = Integer.parseInt(dados[0]);
                }
                catch(NumberFormatException e){
                    System.out.println("Não é um Inteiro");
                    return;
                }
            }
        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
            return;
        }
        System.out.println("Cadastre o Livro a seguir");
        System.out.println("Digite seu nome");
        String name = scanner.nextLine();
        System.out.println("Digite a quantidade de paginas");
        String pages = scanner.nextLine();
        int pageCount = 0;
        try{
            pageCount = Integer.parseInt(pages);
        } catch(NumberFormatException e){
            System.out.println("Não é um Inteiro");
            return;
        }
        System.out.println("Pesquise o autor do livro pelo sobrenome: ");
        String surname = scanner.nextLine();
        File fileAutor = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/authorsFull.tsv");
        InputStream autores;
        try{
            autores = new FileInputStream(fileAutor);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return;
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(autores));
        int authorId = 0;
        ArrayList<String[]> autor = new ArrayList<String[]>();
        try{
            buffer.readLine();
            String linhas;
            while((linhas = buffer.readLine()) != null){
                String[] dado = linhas.split("\t");
                if(dado[2].toLowerCase().equals(surname.toLowerCase()))
                    autor.add(dado);
            }
        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
            return;
        }
        if(autor.size() > 1){
            System.out.println("Foram encontrados os seguintes autores com o"
                + " respectivo sobrenome");
            for(int i = 0; i < autor.size(); i++){
                System.out.println(autor.get(i)[0] + "\t" + autor.get(i)[1]
                + "\t" + autor.get(i)[2]);
            }
            int number = 0;
            System.out.println("Escolha o número desejado");
            while(true){
                try{
                    number = scanner.nextInt();
                    break;
              } catch (InputMismatchException e) {
                    System.out.println("Não é inteiro");
                    scanner.next();
                }
            }
            ArrayList<String[]> found = new ArrayList<String[]>();
            for (String[] dado : autor) {
                if(Integer.parseInt(dado[0]) != number)
                    found.add(dado);
            }
            autor.removeAll(found);
        }
        if(autor.size() == 1){
            System.out.println("Autor Encontrado.");
            authorId = Integer.parseInt(autor.get(0)[0]);
        }
        if(autor.size() == 0){
            System.out.println("Não foi possível encontrar este autor"
                + " deseja cadastrar um novo auotr?");
            System.out.println("1 - Sim\n2 - Não");

            String novoAutor;
            Scanner scanner2 = new Scanner(System.in);
            while(true){
                novoAutor = scanner2.nextLine();
                if(!(novoAutor.equals("1") || novoAutor.equals("2"))){
                    System.out.println("Opção Inválida");
                    scanner2.next();
                    continue;
                }
                break;
            }

            if(novoAutor.equals("1")){
                authorId = Autor.cadastroAutor();
                if(authorId == 0){
                    System.out.println("Não foi possível realizar o cadastro");
                    return;
                }
            }
            else{
                System.out.println("Beleza! Não vai ser possível cadastrar o livro.");
                return;
            }
        }
        File fileGenero = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/types.tsv");
        InputStream generos;
        try{
            generos = new FileInputStream(fileGenero);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return;
        }
        BufferedReader buffered = new BufferedReader(new InputStreamReader(generos));
        int typeId = 0;
        int genero = 0;
        ArrayList<String[]> type = new ArrayList<String[]>();
        try{
            buffered.readLine();
            String linhas;
            System.out.println("Escolha o genero a partir de seu número: ");
            while((linhas = buffered.readLine()) != null){
                String[] dado = linhas.split("\t");
                type.add(dado);
                System.out.println(linhas);
            }
        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
            return;
        }
        System.out.println("Escolha o número desejado");
        while(true){
            try{
                genero = scanner.nextInt();
                break;
          } catch (InputMismatchException e) {
                System.out.println("Não é inteiro");
                scanner.next();
            }
        }
        ArrayList<String[]> foundGender = new ArrayList<String[]>();
        for (String[] linhas : type ) {
            if(genero != Integer.parseInt(linhas[0]))
                foundGender.add(linhas);
        }
        type.removeAll(foundGender);
        if(type.isEmpty()){
            System.out.println("Não foi possível encontrar o genero, logo "
                + "não foi possível cadastrar o livro");
        }
        typeId = Integer.parseInt(type.get(0)[0]);
        Livro livro = new Livro((id + 1), name, pageCount, 20 + (int)(Math.random() * ((100 - 20) + 1)), authorId, typeId);
        FileOutputStream output;
        try{
            output = new FileOutputStream(fileLivro, true);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return;
        }
        byte[] strToBytes = livro.toString().getBytes();
        try{
            output.write(strToBytes);
            output.close();
        }catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            return;
        }
        System.out.println("Livro Cadastrado com sucesso!");
        System.out.println(livro.toString());
        return;

    }

    /**
     * consultaLivroEmprestimo().
     * <p>
     *     A função recebe um parametro que é a quantidade de livros para listar
     *     os mais populares, caso receba 0 ou -1 será utilizado nas funções
     *     de consultaAutorEmprestimo() e consultaGeneroEmprestimo(), respectivamente.
     *     A função sempre retorna um ArrayList com os livros ordenados.
     *     E a função printa os mais populares.
     * </p>
     * @param n inteiro n para a quantidade de livros para listar
     * @return lista de livros com a quantidade de vezes que foram retirados.
     */
    public static ArrayList<HashMap<String, String>> consultaLivroEmprestimo(int n){

        File fileEmprestimo = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/borrows.tsv");
        InputStream emprestimos = null;

        try {
            emprestimos = new FileInputStream(fileEmprestimo);
        }
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo!");
            return null;
        }

        String linhaTabelaEmprestimo;
        ArrayList<String> borrows = new ArrayList<String>();
        BufferedReader buffered = new BufferedReader(new InputStreamReader(emprestimos));
        try{
            buffered.readLine();
            while ((linhaTabelaEmprestimo = buffered.readLine()) != null) {
                if(!linhaTabelaEmprestimo.split("\t")[3].equals(" "))
                    borrows.add(linhaTabelaEmprestimo.split("\t")[2]);
            }
        } catch(IOException e){
            System.out.println("Não foi possível ler o arquivo");
            return null;
        }

        File fileLivro = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/books.tsv");
        InputStream livros;
        try{
            livros = new FileInputStream(fileLivro);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return null;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(livros));
        ArrayList<HashMap<String,String>> books = new ArrayList<HashMap<String, String>>();
        String linha;
        try{
            int frequency;
            br.readLine();
            while((linha = br.readLine()) != null){
                String[] dados = linha.split("\t");
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("bookId", dados[0]);
                map.put("name", dados[1]);
                map.put("pageCount", dados[2]);
                map.put("point", dados[3]);
                map.put("authorId", dados[4]);
                map.put("typeId", dados[5]);
                frequency = Collections.frequency(borrows, dados[0]);
                map.put("frequency", Integer.toString(frequency));
                books.add(map);
            }
        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
            return null;
        }
        Livro.ordenaLivrosInteiros(books, "frequency");
        try{
            Collections.reverse(books);
            for(int i = 0; i < n; i++){
                if( i == 0) System.out.println("Pos. | Name | pageCount | point | Quant.");
                System.out.println((i + 1) + " | " + books.get(i).get("name") + " | "
                    + books.get(i).get("pageCount") + " | "
                    + books.get(i).get("point") + " | "
                    + books.get(i).get("frequency"));
            }
        } catch(IndexOutOfBoundsException e){
            System.out.println("Limite Atingido");
            return null;
        }

        if(n == 0){
            Livro.ordenaLivrosInteiros(books, "authorId");

            return books;
        }
        if(n == -1 ){
            Livro.ordenaLivrosInteiros(books, "typeId");

            return books;
        }
        return null;
    }
    /**
     * ordenaLivrosInteiros().
     * <p>
     *     Função que recebe uma lista de livros e
     *     o parâmetro que será usado para ordená-los. Apenas
     *     funciona com atributos inteiros.
     * </p>
     * @param books lista de livros que será ordenada.
     * @param key parâmetro usado para ordenar os livros.
     */
    private static void ordenaLivrosInteiros(List<HashMap<String, String>> books, String key){
        Collections.sort(books, new Comparator<HashMap<String, String>>(){
        public int compare(HashMap<String, String> one, HashMap<String, String> two) {
            Integer frequencyOne;
            frequencyOne = Integer.parseInt(one.get(key));
            Integer frequencyTwo;
            frequencyTwo = Integer.parseInt(two.get(key));
            return frequencyOne.compareTo(frequencyTwo);
        }
        });
    }

    /**
     * toString();
     * <p>
     * Transforma um Livro em String no formato necessário
     * para ser salvo no arquivo .tsv.
     * </p>
     * @return os dados de um livro em formato de string.
     */
    @Override
    public String toString(){
        return this.bookId + "\t" + this.name + "\t" + this.pageCount + "\t" + this.point + "\t"
            + this.authorId + "\t" + this.typeId + "\n";
    }
    /**
     * Construtor de um Livro
     * <p>
     * Cria uma instancia de Livro para ser salvo nos arquivos da biblioteca.
     * </p>
     * @param bookId id do livro.
     * @param name nome do livro.
     * @param pageCount número de páginas do livro.
     * @param point pontuação do livro na gameficação da biblioteca.
     * @param authorId id do autor do livro.
     * @param typeId id do gênero do livro.
     */
    public Livro(int bookId, String name, int pageCount, int point, int authorId, int typeId) {
        this.bookId = bookId;
        this.name = name;
        this.pageCount = pageCount;
        this.point = point;
        this.authorId = authorId;
        this.typeId = typeId;
    }

}
