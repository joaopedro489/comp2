package br.ufrj.dcc.comp2.ple.joaoPedro.lista4;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.lang.*;
/**
 * @author João Pedro Silva
 *
 *<h1>
 *     Classe dos Gêneros para o exercício da lista 4.
 *     A classe representa os gêneros de livros de uma biblioteca escolar.
 *</h1>
 */
public class Genero {
    private int typeId;
    private String name;
    /**
     * consultaGeneroEmprestimo().
     * <p>
     *     Função não recebe parametros. Dentro da função é recebido
     *     a entrada do usuario sobre a quantidade de gêneros
     *     mais populares que será printada na tela.
     * </p>
     */
    public static void consultaGeneroEmprestimo(){
        ArrayList<HashMap<String, String>> livros = Livro.consultaLivroEmprestimo(-1);
        File fileType = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/types.tsv");
        InputStream generos;
        try{
            generos = new FileInputStream(fileType);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return;
        }
        ArrayList<HashMap<String, String>> types;
        types = new ArrayList<HashMap<String,String>>();
        BufferedReader br = new BufferedReader(new InputStreamReader(generos));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Quantos generos deseja consultar?");
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
                map.put("typeId", dados[0]);
                map.put("name", dados[1]);
                map.put("frequency", "0");
                types.add(map);
            }
        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
            return;
        }
        int j = 0;
        for(int i = 0; i < types.size(); i++){
            while(j < livros.size() - 1 && types.get(i).get("typeId").equals(livros.get(j).get("typeId"))){
                int frequencyAuthor = Integer.parseInt(types.get(i).get("frequency"));
                int frequencyBook =Integer.parseInt(livros.get(j).get("frequency"));
                frequencyAuthor += frequencyBook;
                types.get(i).put("frequency", Integer.toString(frequencyAuthor));
                j++;
            }
        }
        Genero.ordenaGenero(types);
        Collections.reverse(types);
        try{
            System.out.println("Pos. | Name | Quant.");
            for (int i = 0;i< n ;i++ ) {
                System.out.println((i + 1) + " | "  + types.get(i).get("name") +
                 " | " + types.get(i).get("frequency"));
            }
        } catch(IndexOutOfBoundsException e){
            System.out.println("Limite Atingido");
            return;
        }
    }
    /**
     * ordenaGenero().
     * <p>
     *     A função ordena os gêneros mais populares de acordo com a quantidade
     *     de emprestimos de livros com o especifico tema.
     * </p>
     * @param types lista de gêneros para ser ordenada.
     */
    private static void ordenaGenero(List<HashMap<String, String>> types){
        Collections.sort(types, new Comparator<HashMap<String, String>>(){
        public int compare(HashMap<String, String> one, HashMap<String, String> two) {
            Integer frequencyOne;
            frequencyOne = Integer.parseInt(one.get("frequency"));
            Integer frequencyTwo;
            frequencyTwo = Integer.parseInt(two.get("frequency"));
            return frequencyOne.compareTo(frequencyTwo);
        }
        });
    }

}
