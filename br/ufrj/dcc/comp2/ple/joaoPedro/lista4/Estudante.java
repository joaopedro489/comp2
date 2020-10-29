package br.ufrj.dcc.comp2.ple.joaoPedro.lista4;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.lang.*;
/**
 * @author João Pedro Silva
 *
 *<h1>
 *     Classe dos Estudantes para o exercício da lista 4.
 *     Programa feito para simular os estudantes
 *     cadastrados em uma biblioteca escolar.
 *</h1>
 */
public class Estudante{
    private int studentId;
    private String name;
    private String surname;
    private String birthdate;
    private String gender;
    private String studentClass;
    private int point;

    /**
     * cadastroEstudante().
     * <p>
     *     Função que realiza o cadastro um estudante na biblioteca
     *     com seus dados e retorna o id deste estudante para ser utilizado
     *     na função de emprestimo().
     * </p>
     * @return id do estudante.
     */
    public static int cadastroEstudante(){
        File fileEstudante = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/students.tsv");
        InputStream estudantes;
        try{
            estudantes = new FileInputStream(fileEstudante);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return 0;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(estudantes));
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
        System.out.println("Cadastre o Estudante a seguir");
        System.out.println("Digite seu nome");
        String name = scanner.nextLine();
        System.out.println("Digite seu sobrenome");
        String surname = scanner.nextLine();
        System.out.println("Digite seu nascimento (yyyy-mm-dd)");
        String birthdate = scanner.nextLine();
        System.out.println("Digite seu genero (M ou F)");
        String gender = scanner.nextLine();
        System.out.println("Digite sua classe");
        String studentClass = scanner.nextLine();
        Estudante estudante = new Estudante((id+1), name, surname, birthdate, gender,
            studentClass, 0);
        FileOutputStream output;
        try{
            output = new FileOutputStream(fileEstudante, true);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return 0;
        }
        byte[] strToBytes = estudante.toString().getBytes();
        try{
            output.write(strToBytes);
            output.close();
        }catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            return 0;
        }
        System.out.println("Estudante Cadastrado com sucesso!");
        System.out.println(estudante.toString());
        return estudante.studentId;
    }
    /**
     * consultaEstudantesEmprestimos().
     * <p>
     *     Função que consulta no arquivo os estudantes e lista os estudantes de acordo com
     *     o parâmetro que for passado na função. Se o boolean for
     *     verdadeiro, ele retornará apenas os
     *     estudantes que já pegaram algum livro emprestado junto
     *     com seus dados e os pontos totais do aluno.
     *     Caso seja um boolean falso, ele retorna todos
     *     os estudantes com seus dados.
     * </p>
     */
    public static void consultaEstudantesEmprestimos(boolean tipo){
        File fileEmprestimo = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/borrows.tsv");
        InputStream emprestimos = null;


        try {
            emprestimos = new FileInputStream(fileEmprestimo);
        }
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo!");
            return;
        }

        String linhaTabelaEmprestimo;
        ArrayList<String> borrows = new ArrayList<String>();
        BufferedReader buffered = new BufferedReader(new InputStreamReader(emprestimos));
        try{
            buffered.readLine();
            while ((linhaTabelaEmprestimo = buffered.readLine()) != null) {
                if(!linhaTabelaEmprestimo.split("\t")[3].equals(" "))
                    borrows.add(linhaTabelaEmprestimo.split("\t")[1]);
            }
        } catch(IOException e){
            System.out.println("Não foi possível ler o arquivo");
            return;
        }


        File fileEstudante = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/students.tsv");
        InputStream estudantes;
        try{
            estudantes = new FileInputStream(fileEstudante);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return;
        }
        ArrayList<HashMap<String, String>> students;
        students = new ArrayList<HashMap<String,String>>();
        BufferedReader br = new BufferedReader(new InputStreamReader(estudantes));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Quantos alunos deseja consultar?");
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
        if(!tipo){
            String linha;
            try{
                br.readLine();
                int frequency;
                while((linha = br.readLine()) != null){
                    HashMap<String, String> map = new HashMap<String, String>();
                    String[] dados = linha.split("\t");
                    map.put("studentId", dados[0]);
                    map.put("name", dados[1]);
                    map.put("surname", dados[2]);
                    map.put("birthdate", dados[3]);
                    map.put("gender", dados[4]);
                    map.put("studentClass", dados[5]);
                    map.put("point", dados[6]);
                    frequency = Collections.frequency(borrows, dados[0]);
                    map.put("frequency", Integer.toString(frequency));
                    students.add(map);
                }
            }catch(IOException e){
                System.out.println("Erro ao ler o arquivo");
                return;
            }
            Estudante.ordenaEstudantes(students, "frequency");
            Collections.reverse(students);
            try{
                System.out.println("Pos. | Name | Surname | Birth | Gender | Class | Books");
                for(int i = 0; i < n; i++){
                    System.out.println((i + 1) + " | " + students.get(i).get("name") + " | "
                        + students.get(i).get("surname") + " | "
                        + students.get(i).get("birthdate") + " | "
                        + students.get(i).get("gender") + " | "
                        + students.get(i).get("studentClass") + " | "
                        + students.get(i).get("frequency"));
                }
            } catch(IndexOutOfBoundsException e){
                System.out.println("Limite Atingido");
                return;
            }
        }
        else{
            String linha;
            try{
                br.readLine();
                int frequency;
                while((linha = br.readLine()) != null){
                    HashMap<String, String> map = new HashMap<String, String>();
                    String[] dados = linha.split("\t");
                    map.put("studentId", dados[0]);
                    map.put("name", dados[1]);
                    map.put("surname", dados[2]);
                    map.put("birthdate", dados[3]);
                    map.put("gender", dados[4]);
                    map.put("studentClass", dados[5]);
                    map.put("point", dados[6]);
                    students.add(map);
                }
            }catch(IOException e){
                System.out.println("Erro ao ler o arquivo");
                return;
            }
            Estudante.ordenaEstudantes(students, "point");
            Collections.reverse(students);
            try{
                System.out.println("Pos. | Name | Surname | Birth | Gender | Class | Points.");
                for(int i = 0; i < n; i++){
                    System.out.println((i + 1) + " | " + students.get(i).get("name") + " | "
                        + students.get(i).get("surname") + " | "
                        + students.get(i).get("birthdate") + " | "
                        + students.get(i).get("gender") + " | "
                        + students.get(i).get("studentClass") + " | "
                        + students.get(i).get("point"));
                }
            } catch(IndexOutOfBoundsException e){
                System.out.println("Limite Atingido");
                return;
            }
        }
    }
    /**
     * ordenaEstudantes().
     * <p>
     *     Função que recebe uma lista de estudantes e
     *     o parâmetro que será usado para ordená-los
     *     em ordem crescente.
     * </p>
     * @param students lista de estudantes que será ordenada.
     * @param key chave para filtrar o ArrayList de estudantes.
     */
    private static void ordenaEstudantes(List<HashMap<String, String>> students, String key){
        Collections.sort(students, new Comparator<HashMap<String, String>>(){
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
     * toString()
     * <p>
     * Transforma um Estudante em String no formato necessário
     * para ser salvo no arquivo .tsv.
     * </p>
     * @return os dados de um estudante em formato de string.
     */
    @Override
    public String toString(){
        return this.studentId + "\t" + this.name + "\t" + this.surname + "\t"
            + this.birthdate + "\t" + this.gender + "\t" + this.studentClass
            + "\t" + this.point + "\n";
    }
    /**
     * Construtor de Estudante
     * <p>
     *     Cria uma instancia de Estudante para ser salvo em uma tabela
     *     com dados de estudantes cadastrados na biblioteca.
     * </p>
     * @param studentId id do estudante.
     * @param name nome do estudante.
     * @param surname sobrenome do estudante.
     * @param birthdate data de nascimento do estudante.
     * @param gender gênero do estudante.
     * @param studentClass turma do estudante.
     * @param point pontuação do estudante na gameficação da biblioteca.
     */
    public Estudante(int studentId, String name, String surname, String birthdate, String gender, String studentClass, int point) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.studentClass = studentClass;
        this.point = point;
    }
}
