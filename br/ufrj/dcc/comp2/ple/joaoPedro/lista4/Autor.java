package br.ufrj.dcc.comp2.ple.joaoPedro.lista4;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.lang.*;
/**
* @author João Pedro Silva
 *
 *<h1>
 *     Classe dos Autores para o exercício da lista 4.
 *     Programa representa os autores de livros
 *     presentes na biblioteca escolar.
 *</h1>
 */
public class Autor {
    private int authorId;
    private String name;
    private String surname;
    /**
     * cadastroAutor().
     * <p>
     *     Função realiza o cadastro de um autor na biblioteca com seus dados.
     *     a função recebe as entradas do usuário sobre o novo autor e adiciona
     *     no arquivo de autores.
     * </p>
     * @return id do autor para ser utilizado na hora de cadastrar um livro.
     */
    public static int cadastroAutor(){
        File fileAutor = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/authorsFull.tsv");
        InputStream autores;
        try{
            autores = new FileInputStream(fileAutor);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return 0;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(autores));
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
                    return 0;
                }
            }
        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
            return 0;
        }
        System.out.println("Cadastre o autor a seguir");
        System.out.println("Digite seu nome");
        String name = scanner.nextLine();
        System.out.println("Digite seu sobrenome");
        String surname = scanner.nextLine();
        Autor autor = new Autor((id + 1), name, surname);
        FileOutputStream output;
        try{
            output = new FileOutputStream(fileAutor, true);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return 0;
        }
        byte[] strToBytes = autor.toString().getBytes();
        try{
            output.write(strToBytes);
            output.close();
        }catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            return 0;
        }
        System.out.println("Autor Cadastrado com sucesso!");
        System.out.println(autor.toString());
        return (id + 1);
    }
    /**
     * consultaAutorEmprestimo()
     * <p>
     *     Função que lista a quantidade de autores populares determinada
     *     pelo usuário. Dentro da função ele recebe a informação dessa quantidade
     *     e após isso ele ordena pela quantidade de livros emprestados por
     *     cada autor.
     * </p>
     */
    public static void consultaAutorEmprestimo(){
        ArrayList<HashMap<String, String>> livros = Livro.consultaLivroEmprestimo(0);
        File fileAutor = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/authorsFull.tsv");
        InputStream autores;
        try{
            autores = new FileInputStream(fileAutor);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return;
        }
        ArrayList<HashMap<String, String>> authors;
        authors = new ArrayList<HashMap<String,String>>();
        BufferedReader br = new BufferedReader(new InputStreamReader(autores));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Quantos autores deseja consultar?");
        int n = 0;
        while(true){
            try{
                n = scanner.nextInt();
                break;
            }
            catch(InputMismatchException e){
                System.out.println("Não é um Inteiro");
                scanner.next();
            }
        }

        String linha;
        try{
            br.readLine();
            int frequency;
            while((linha = br.readLine()) != null){
                HashMap<String, String> map = new HashMap<String, String>();
                String[] dados = linha.split("\t");
                map.put("authorId", dados[0]);
                map.put("name", dados[1]);
                map.put("surname", dados[2]);
                map.put("frequency", "0");
                authors.add(map);
            }
        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
            return;
        }
        int j = 0;
        for(int i = 0; i < authors.size(); i++){
            while(j < livros.size() - 1 && authors.get(i).get("authorId").equals(livros.get(j).get("authorId"))){
                int frequencyAuthor = Integer.parseInt(authors.get(i).get("frequency"));
                int frequencyBook =Integer.parseInt(livros.get(j).get("frequency"));
                frequencyAuthor += frequencyBook;
                authors.get(i).put("frequency", Integer.toString(frequencyAuthor));
                j++;
            }
        }
        Autor.ordenaAutor(authors);
        Collections.reverse(authors);
        try{
            System.out.println("Pos. | Name | Surname | Quant.");
            for (int i = 0;i< n ;i++ ) {
                System.out.println((i + 1) + " | "  + authors.get(i).get("name") +
                    " | " + authors.get(i).get("surname") + " | "
                    + authors.get(i).get("frequency"));
            }
        } catch(IndexOutOfBoundsException e){
            System.out.println("Limite Atingido");
            return;
        }
    }
    /**
     * ordenaAutor().
     * <p>
     *  A função recebe uma lista de autores, que será ordenada de acordo
     * com as quantidades de livros emprestados por autor.
     * </p>
     * @param authors lista de autores que será ordenada.
     */
    private static void ordenaAutor(List<HashMap<String, String>> authors){
        Collections.sort(authors, new Comparator<HashMap<String, String>>(){
        public int compare(HashMap<String, String> one, HashMap<String, String> two) {
            Integer frequencyOne;
            frequencyOne = Integer.parseInt(one.get("frequency"));
            Integer frequencyTwo;
            frequencyTwo = Integer.parseInt(two.get("frequency"));
            return frequencyOne.compareTo(frequencyTwo);
        }
        });
    }
    /**
     * toString()
     * <p>
     * Transforma uma instancia da classe Autor em String
     * no formato necessário para ser salvo no arquivo .tsv.
     * </p>
     * @return os dados de um autor em formato de string.
     */
    @Override
    public String toString(){
        return this.authorId + "\t" + this.name + "\t" + this.surname + "\n";
    }
    /**
     * Construtor de um autor
     * <p>
     *     Cria uma instancia da Classe Autor a partir dos
     *     parametros passados no construtor.
     * </p>
     * @param authorId id do autor.
     * @param name nome do autor.
     * @param surname sobrenome do autor.
     */
    public Autor(int authorId, String name, String surname) {
            this.authorId = authorId;
            this.name = name;
            this.surname = surname;
        }
}
