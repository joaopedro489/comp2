package br.ufrj.dcc.comp2.ple.joaoPedro.lista4;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.lang.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;

/**
 * @author João Pedro Silva
 *
 *<h1>
 *     Classe dos Empréstimos para o exercício da lista 4.
 *     A classe representa os empréstimos de livros
 *     em uma biblioteca escolar.
 *</h1>
 */

public class Emprestimo {
    private int borrowId;
    private int studentId;
    private int bookId;
    private String takenDate;
    private String brought;
    /**
     * emprestimo().
     * <p>
     *     A função realiza um emprestimo de um livro.
     *     Se o estudante não esteja cadastrado
     *     no sistema, é possível cadastrá-lo.
     *     Se o estudante possuir dois livros, sem ter devolvido um deles, não
     *     poderá pegar um terceiro.
     *     Se dois exemplares de um livro desejado já estiver emprestados,
     *     o estudante entra em uma fila para pegar
     *     o livro assim que for devolvido.
     *     Além disso a função calcula os pontos dos alunos.
     *     A função também atualiza a tabela de empréstimos e de estudantes.
     * </p>
     */
    public static void emprestimo(){
        Scanner scanner = new Scanner(System.in);
        File fileEstudante = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/students.tsv");
        InputStream estudantes = null;

        try {
            estudantes = new FileInputStream(fileEstudante);
        }
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo!");
            return;
        }

        String linhaTabelaEstudante;
        ArrayList<String []> students = new ArrayList<String[]>();
        int estudanteId = 0;
        BufferedReader buffered = new BufferedReader(new InputStreamReader(estudantes));
        System.out.println("Insira o nome do estudante");
        String name = scanner.nextLine();
        System.out.println("Insira o sobrenome do estudante");
        String surname = scanner.nextLine();
        try {
            buffered.readLine();
            while ((linhaTabelaEstudante = buffered.readLine()) != null) {
                String[] dado =  linhaTabelaEstudante.split("\t");
                if (dado[1].equals(name) || dado[2].equals(surname)) {
                    students.add(dado);
                }
            }
        } catch(IOException err){
            System.out.println("Erro ao ler o arquivo");
            return;
        }
        if (students.size() > 0) {
            System.out.println("Escolha um estudante pelo seu número a seguir:");
            for (String[] student : students) {
                System.out.println(student[0] + "\t" + student[1] +  "\t"
                    + student[2] + "\t" + student[3] + "\t"+ student[4] + "\t"
                    + student[5] + "\t" + student[6]);
            }

            int number = 0;
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
            for (String[] dado : students) {
                if(Integer.parseInt(dado[0]) != number)
                    found.add(dado);
            }
            students.removeAll(found);
            if (students.size() == 0) {
                System.out.println("Este id não é válido!");
                System.out.println("Deseja cadastrar um novo estudante?");
                System.out.println("1 - Sim\n2 - Não");
                String opcao;
                opcao = scanner.nextLine();

                switch(opcao) {
                    case "1" -> estudanteId = Estudante.cadastroEstudante();
                    case "2" -> {
                        System.out.println("Não foi possivel Realizar o Emprestimo");
                        return;
                    }
                    default -> {
                        System.out.println("Não é uma opção válida.");
                        return;
                    }
                }
            }
            else{
                estudanteId = Integer.parseInt(students.get(0)[0]);
            }
        } else {
            System.out.println("Não foi encontrado o estudante!");
            System.out.println("Deseja cadastrar um novo estudante?");
            System.out.println("1 - Sim\n2 - Não");
            String opcao;
            opcao = scanner.nextLine();

            switch(opcao) {
                case "1" -> estudanteId = Estudante.cadastroEstudante();
                case "2" -> {
                    System.out.println("Não foi possivel Realizar o Emprestimo");
                    return;
                }
                default -> {
                    System.out.println("Não é uma opção válida.");
                    return;
                }
            }
        }

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
        String ultimaLinhaTabela = "0";
        ArrayList<Integer> livrosEmprestimoId = new ArrayList<Integer>();
        ArrayList<String[]> borrows = new ArrayList<String[]>();
        buffered = new BufferedReader(new InputStreamReader(emprestimos));
        try{
            buffered.readLine();
            while ((linhaTabelaEmprestimo = buffered.readLine()) != null) {
                String[] dado = linhaTabelaEmprestimo.split("\t");
                ultimaLinhaTabela = linhaTabelaEmprestimo;
                if(dado[4].equals(" ")){
                    livrosEmprestimoId.add(Integer.parseInt(dado[2]));
                    if (Integer.parseInt(dado[1]) == estudanteId ) {
                            borrows.add(dado);
                    }
                }
            }
        } catch(IOException e){
            System.out.println("Não foi possível ler o arquivo");
            return;
        }
        if(borrows.size() == 2) {
            System.out.println("Você não pode pegar mais que dois livros!");
            return;
        }

        File fileLivro = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/books.tsv");
        InputStream livros = null;
        ArrayList<String []> books = new ArrayList<String []>();
        int livroId = 0;
        String linhaTabelaLivro;

        try {
            livros = new FileInputStream(fileLivro);
        }
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo!");
            return;
        }

        buffered = new BufferedReader(new InputStreamReader(livros));
        try{
            buffered.readLine();
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Insira o nome do livro que deseja pegar");
            String busca = scanner2.nextLine();

            while ((linhaTabelaLivro = buffered.readLine()) != null) {
                String[]dado = linhaTabelaLivro.split("\t");
                if(dado[1].toLowerCase().equals(busca.toLowerCase())) {
                    books.add(dado);
                }
            }
        } catch (IOException e){
            System.out.println("Não foi ler o arquivo.");
            return;
        }
        if(books.size() > 0) {
            System.out.println("Escolha um livro pelo seu id");
            for (int i = 0; i< books.size() ; i++ ) {
                System.out.println(books.get(i)[0] + "\t" + books.get(i)[1] + "\t"
                    + books.get(i)[2] + "\t" + books.get(i)[3]);
            }
            int number = 0;
            int point = 0;
            while(true){
                try{
                    number = scanner.nextInt();
                    break;
              } catch (InputMismatchException e) {
                    System.out.println("Não é inteiro");
                    scanner.next();
                }
            }
            for (String[] livro : books) {
                if (number == Integer.parseInt(livro[0])) {
                    livroId = number;
                    point = Integer.parseInt(livro[3]);
                    break;
                }
            }
            System.out.println(livroId);
            if(livroId == 0) {
                System.out.println("Este número não é válido!");
                return;
            }
            else {
                for (int i = 0; i<borrows.size(); i++) {
                    if (livroId == Integer.parseInt(borrows.get(i)[2])) {
                        System.out.println("Você está atualmente com este livro");
                        return;
                    }
                }
                int emprestimoId = Integer.parseInt(ultimaLinhaTabela.split("\t")[0]) + 1;
                int quantidadeDeEmprestimos = Collections.frequency(livrosEmprestimoId, livroId);
                Emprestimo emprestimo;
                boolean pass = false;
                ArrayList<String> tabelaEstudante = new ArrayList<String>();
                if(quantidadeDeEmprestimos == 2) {
                    System.out.println("Estamos sem estoque do livro desejado."
                    + "\nVocê entrou na fila!");
                    emprestimo = new Emprestimo(emprestimoId, estudanteId, livroId, " ", " ");

                }
                else {
                    pass= true;
                    LocalDateTime agora = LocalDateTime.now();
                    DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

                    File fileEstudantes = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/students.tsv");
                    InputStream estudante = null;

                    try {
                        estudante = new FileInputStream(fileEstudantes);
                    }
                    catch (IOException e) {
                        System.out.println("Erro ao ler o arquivo!");
                        return;
                    }

                    String agoraFormatado = agora.format(formatadorDeData);
                    String linhaTabelaEstudante2;
                    emprestimo = new Emprestimo(emprestimoId, estudanteId, livroId, agoraFormatado, " ");
                    buffered = new BufferedReader(new InputStreamReader(estudante));
                    try {
                        tabelaEstudante.add(buffered.readLine() + "\n");
                        while ((linhaTabelaEstudante2 = buffered.readLine()) != null) {
                            String[] dado = linhaTabelaEstudante2.split("\t");
                            if(Integer.parseInt(dado[0]) == estudanteId){
                                int previous = Integer.parseInt(dado[6]);
                                linhaTabelaEstudante2 = dado[0] + "\t" + dado[1]
                                    + "\t" + dado[2] + "\t" + dado[3] +  "\t"
                                    + dado[4] + "\t" + dado[5] + "\t" + (previous + point);
                            }
                            tabelaEstudante.add(linhaTabelaEstudante2 + "\n");
                        }
                    } catch(IOException err){
                        System.out.println("Erro ao ler o arquivo");
                        return;
                    }
                    System.out.println(emprestimo.toString());
                }


                FileOutputStream output;
                FileOutputStream studentOutput = null;

                try {
                    output = new FileOutputStream(fileEmprestimo, true);
                    if(pass)
                        studentOutput = new FileOutputStream(fileEstudante);
                }
                catch(FileNotFoundException e) {
                    System.out.println("Erro ao abrir o arquivo!");
                    return;
                }

                byte[] strToBytes = emprestimo.toString().getBytes();

                try {
                    output.write(strToBytes);
                    output.close();
                    if(pass){
                        for(int i = 0; i<tabelaEstudante.size(); i++){
                            studentOutput.write(tabelaEstudante.get(i).toString().getBytes());
                        }
                        studentOutput.close();
                    }
                }
                catch (IOException e) {
                    System.out.println("Erro ao escrever no arquivo!");
                    return;
                }
                System.out.println("Empréstimo realizado com sucesso!");

            }
        }
        else {
            System.out.println("Não foi encontrado nenhum livro na busca!");
            return;
        }
    }
    /**
     * devolver()
     * <p>
     *     A função realiza a devolução de um livro por um estudante.
     *     O estudante precisa estar cadastrado na plataforma
     *     e precisa possuir pelo menos um livro.
     * </p>
     */
    public static void devolver(){
        Scanner scanner = new Scanner(System.in);
        File fileEstudante = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/students.tsv");
        InputStream estudantes = null;
        List<String> input = new ArrayList<String>();


        try {
            estudantes = new FileInputStream(fileEstudante);
        }
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo!");
            System.exit(1);
        }

        String linhaTabelaEstudante;
        ArrayList<String []> students = new ArrayList<String[]>();
        int estudanteId = 0;
        BufferedReader buffered = new BufferedReader(new InputStreamReader(estudantes));

        System.out.println("Insira o nome do estudante");
        String name = scanner.nextLine();
        System.out.println("Insira o sobrenome do estudante");
        String surname = scanner.nextLine();
        try {
            buffered.readLine();
            while ((linhaTabelaEstudante = buffered.readLine()) != null) {
                String[] dado =  linhaTabelaEstudante.split("\t");
                if (dado[1].equals(name) || dado[2].equals(surname)) {
                    students.add(dado);
                }
            }
        } catch(IOException err){
            System.out.println("Erro ao ler o arquivo");
            return;
        }
        if (students.size() > 0) {
            System.out.println("Escolha um estudante pelo seu número a seguir:");
            for (String[] student : students) {
                System.out.println(student[0] + "\t" + student[1] +  "\t"
                    + student[2] + "\t" + student[3] + "\t"+ student[4] + "\t"
                    + student[5] + "\t" + student[6]);
            }
            int number = 0;
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
            for (String[] dado : students) {
                if(Integer.parseInt(dado[0]) != number)
                    found.add(dado);
            }
            students.removeAll(found);
            if (students.size() == 0) {
                System.out.println("Não foi encontrado");
                return;
                }
            else{
                estudanteId = Integer.parseInt(students.get(0)[0]);
                }
            }
            else {
            System.out.println("Não foi encontrado");
            return;
        }
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
        String ultimaLinhaTabela = "0";
        ArrayList<Integer> livrosEmprestimoId = new ArrayList<Integer>();
        ArrayList<String[]> borrows = new ArrayList<String[]>();
        ArrayList<Integer> listaDeLivros = new ArrayList<Integer>();
        buffered = new BufferedReader(new InputStreamReader(emprestimos));
        try{
            input.add(buffered.readLine() + "\n");
            while ((linhaTabelaEmprestimo = buffered.readLine()) != null) {
                String[] dado = linhaTabelaEmprestimo.split("\t");
                input.add(linhaTabelaEmprestimo + "\n");
                livrosEmprestimoId.add(Integer.parseInt(dado[2]));
                ultimaLinhaTabela = linhaTabelaEmprestimo;
                if (Integer.parseInt(dado[1]) == estudanteId && dado[4].equals(" ")
                    && !(dado[3].equals(" "))) {
                        listaDeLivros.add(Integer.parseInt(dado[2]));
                        borrows.add(dado);
                }
            }
        } catch(IOException e){
            System.out.println("Não foi possível ler o arquivo");
            return;
        }

        File fileLivro = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/books.tsv");
        InputStream livros = null;
        ArrayList<String []> books = new ArrayList<String []>();
        String linhaTabelaLivro;

        try {
            livros = new FileInputStream(fileLivro);
        }
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo!");
            return;
        }

        buffered = new BufferedReader(new InputStreamReader(livros));
        try{
            buffered.readLine();
            while ((linhaTabelaLivro = buffered.readLine()) != null) {
                String[] dado = linhaTabelaLivro.split("\t");
                if(listaDeLivros.contains(Integer.parseInt(dado[0]))) {
                    books.add(dado);
                }
            }
        } catch (IOException e){
            System.out.println("Não foi possível ");
            return;
        }
        if(books.size() == 0){
            System.out.println("Não existe livros para devolver");
        }
        System.out.println("Qual livro é desejado devolver?");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i)[0] + "\t" + books.get(i)[1] + "\t"
                + books.get(i)[2] + "\t" + books.get(i)[3]);
        }
        int number = 0;
        while(true){
            try{
                number = scanner.nextInt();
                break;
          } catch (InputMismatchException e) {
                System.out.println("Não é inteiro");
                scanner.next();
            }
        }
        if(!listaDeLivros.contains(number)){
            System.out.println("Erro ao devolver livro");
            return;
        }

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        String agoraFormatado = agora.format(formatadorDeData);
        for(int i = 0; i<input.size(); i++){
            if(i == 0)
                continue;
            String[] dado = input.get(i).split("\t");
            if(Integer.parseInt(dado[2]) == number
                && Integer.parseInt(dado[1]) == estudanteId){
                    dado[4] = agoraFormatado;
                    String s = dado[0] + "\t" + dado[1] + "\t" + dado[2]
                        + "\t" + dado[3] + "\t" + dado[4] + "\n";
                    input.set(i, s);
                }
        }
        FileOutputStream output;

        try {
            output = new FileOutputStream(fileEmprestimo);
        }
        catch(FileNotFoundException e) {
            System.out.println("Erro ao abrir o arquivo!");
            return;
        }


        try {
            output.write(input.get(0).toString().getBytes());
            boolean pass = false;
            for(int i = 1; i<input.size(); i++){
                if(Integer.parseInt(input.get(i).split("\t")[2]) == number &&
                    input.get(i).split("\t")[3].equals(" ") && !pass){
                        String s = input.get(i).split("\t")[0] + "\t"
                            + input.get(i).split("\t")[1] + "\t"
                            + input.get(i).split("\t")[2]
                            + "\t" + agoraFormatado + "\t \n";
                        input.set(i, s);
                        pass = true;
                    }
                output.write(input.get(i).toString().getBytes());
            }
            output.close();
        }
        catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo!");
            return;
        }
        System.out.println("Devolução realizado com sucesso!");

    }
    /**
     * ultimosEmprestimos().
     * <p>
     *     A função não recebe paramtros, dentro da função é esperado a entrada
     *     do usuario sobre a quantidade de empréstimos mais recentes
     *     que deseja printar na tela, existe a ordenação os últimos empréstimos e
     *     após isso será listado para o usuário.
     * </p>
     */
    public static void ultimosEmprestimos(){
        System.out.println("Quantos ultimos emprestimos gostaria de ver?");
        Scanner scanner = new Scanner(System.in);
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
                students.add(map);
            }
        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
            return;
        }

        File fileLivro = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/books.tsv");
        InputStream livros;
        try{
            livros = new FileInputStream(fileLivro);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return;
        }
        br = new BufferedReader(new InputStreamReader(livros));
        ArrayList<HashMap<String,String>> books = new ArrayList<HashMap<String, String>>();
        String linhaTabelaLivro;
        try{
            int frequency;
            br.readLine();
            while((linhaTabelaLivro = br.readLine()) != null){
                String[] dados = linhaTabelaLivro.split("\t");
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("bookId", dados[0]);
                map.put("name", dados[1]);
                books.add(map);
            }
        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
            return;
        }
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
        ArrayList<HashMap<String, String>> borrows = new ArrayList<HashMap<String, String>>();
        BufferedReader buffered = new BufferedReader(new InputStreamReader(emprestimos));
        try{
            buffered.readLine();
            while ((linhaTabelaEmprestimo = buffered.readLine()) != null) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("borrowId", linhaTabelaEmprestimo.split("\t")[0]);
                if(linhaTabelaEmprestimo.split("\t")[3].equals(" "))
                    continue;
                for(int i = 0; i < students.size(); i++){
                    if(students.get(i).get("studentId").equals(linhaTabelaEmprestimo.split("\t")[1])){
                        map.put("student", (students.get(i).get("name") + " "
                            + students.get(i).get("surname")));
                        break;
                        }
                    }
                for(int i = 0; i < books.size(); i++){
                    if(books.get(i).get("bookId").equals(linhaTabelaEmprestimo.split("\t")[2])){
                        map.put("book", books.get(i).get("name"));
                        break;
                    }
                }
                map.put("takenDate", linhaTabelaEmprestimo.split("\t")[3]);
                map.put("brought", linhaTabelaEmprestimo.split("\t")[4]);

                borrows.add(map);
            }
        } catch(IOException e){
            System.out.println("Não foi possível ler o arquivo");
            return;
        }
        Emprestimo.ordenaDataEmprestimo(borrows);
        Collections.reverse(borrows);
        try{
            System.out.println("ID | Estudante | Livro | Data do Emprestimo  | Data de Devolução");
            for (int i = 0; i< n ;i ++ ) {
                System.out.println(borrows.get(i).get("borrowId") + " | "
                    + borrows.get(i).get("student") + " | "
                    + borrows.get(i).get("book") + " | "
                    + borrows.get(i).get("takenDate") + " | "
                    + borrows.get(i).get("brought"));
            }
        }catch (IndexOutOfBoundsException e) {
            System.out.println("Limite Atingido");
            return;
        }
    }
    /**
     * consultaDiasEmprestimos().
     * <p>
     *     A função não recebe parametros, dentro da função é recebido
     *     a quantidade mínima de dias que possui um empréstimo (fechado ou em aberto)
     *     e é listado na tela os empréstimos que entram nesse caso.
     * </p>
     */

    public static void consultaDiasEmprestimos(){
        System.out.println("Quantos dias de emprestimos gostaria de ver?");
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("Gostaria de ver abertos ou fechados?");
        System.out.println("1 - Abertos\n2 - Fechados");
        Scanner scanner2 = new Scanner(System.in);
        String tipo;
        while(true){
            tipo = scanner2.nextLine();
            if(!(tipo.equals("1") || tipo.equals("2"))){
                System.out.println("Opção Inválida");
                scanner2.next();
                continue;
            }
            break;
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
                students.add(map);
            }
        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
            return;
        }

        File fileLivro = new File("br/ufrj/dcc/comp2/ple/joaoPedro/lista4/library/books.tsv");
        InputStream livros;
        try{
            livros = new FileInputStream(fileLivro);
        }catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo");
            return;
        }
        br = new BufferedReader(new InputStreamReader(livros));
        ArrayList<HashMap<String,String>> books = new ArrayList<HashMap<String, String>>();
        String linhaTabelaLivro;
        try{
            int frequency;
            br.readLine();
            while((linhaTabelaLivro = br.readLine()) != null){
                String[] dados = linhaTabelaLivro.split("\t");
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("bookId", dados[0]);
                map.put("name", dados[1]);
                books.add(map);
            }
        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
            return;
        }
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
        ArrayList<HashMap<String, String>> borrows = new ArrayList<HashMap<String, String>>();
        BufferedReader buffered = new BufferedReader(new InputStreamReader(emprestimos));
        try{

            buffered.readLine();
            while ((linhaTabelaEmprestimo = buffered.readLine()) != null) {
                String[] dados = linhaTabelaEmprestimo.split("\t");
                DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

                HashMap<String, String> map = new HashMap<String, String>();
                if(dados[3].equals(" "))
                    continue;
                if(tipo.equals("1")){
                    if(dados[4].equals(" ")){
                        LocalDateTime takenDate = LocalDateTime.parse(dados[3], formatadorDeData);
                        LocalDateTime agora = LocalDateTime.now();
                        String agoraFormatado = agora.format(formatadorDeData);
                        agora = LocalDateTime.parse(agoraFormatado, formatadorDeData).minusDays(n);
                        if(!(agora.isBefore(takenDate)))
                            continue;
                    }
                    else{
                        continue;
                    }
                }
                if(tipo.equals("2")){
                    if(!(dados[4].equals(" "))){
                        LocalDateTime takenDate = LocalDateTime.parse(dados[3], formatadorDeData);
                        LocalDateTime broughtDate = LocalDateTime.parse(dados[4], formatadorDeData).minusDays(n);
                        if(!(broughtDate.isBefore(takenDate)))
                            continue;
                    }
                    else{
                        continue;
                    }
                }
                map.put("borrowId", dados[0]);
                for(int i = 0; i < students.size(); i++){
                    if(students.get(i).get("studentId").equals(dados[1])){
                        map.put("student", (students.get(i).get("name") + " "
                            + students.get(i).get("surname")));
                        break;
                    }
                }
                for(int i = 0; i < books.size(); i++){
                    if(books.get(i).get("bookId").equals(dados[2])){
                        map.put("book", books.get(i).get("name"));
                        break;
                    }
                }
                map.put("takenDate", dados[3]);
                map.put("brought", dados[4]);

                borrows.add(map);
            }
        } catch(IOException e){
            System.out.println("Não foi possível ler o arquivo");
            return;
        }
        Emprestimo.ordenaDataEmprestimo(borrows);
        Collections.reverse(borrows);

       try{
            System.out.println("ID | Estudante | Livro | Data do Emprestimo | Data de Devolução");
            for(int i =0; i < borrows.size(); i++){
                System.out.println(borrows.get(i).get("borrowId") + " | "
                    + borrows.get(i).get("student") + " | "
                    + borrows.get(i).get("book") + " | "
                    + borrows.get(i).get("takenDate") + " | "
                    + borrows.get(i).get("brought"));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Limite Maximo");
            return;
        }
    }
    /**
     * ordenaDataEmprestimo()
     * <p>
     *     A função ordena os empréstimos pela data
     *     em que foram emprestados do mais recente ao
     *     mais antigo.
     * </p>
     * @param borrows lista de empréstimos para ser ordenada.
     */
    private static void ordenaDataEmprestimo(List<HashMap<String, String>> borrows){
        Collections.sort(borrows, new Comparator<HashMap<String, String>>(){
        public int compare(HashMap<String, String> one, HashMap<String, String> two) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            LocalDateTime dateOne;
            dateOne = LocalDateTime.parse(one.get("takenDate"), formatter);
            LocalDateTime dateTwo;
            dateTwo = LocalDateTime.parse(two.get("takenDate"), formatter);
            return dateOne.compareTo(dateTwo);
        }
        });
    }
    /**
     * toString()
     * <p>
     * Transforma um Emprestimo em String no formato necessário
     * para ser salvo no arquivo .tsv.
     * </p>
     * @return os dados de um empréstimo em formato de string.
     */
    @Override
    public String toString(){
        return this.borrowId + "\t" + this.studentId + "\t" + this.bookId + "\t"
            + this.takenDate + "\t" + this.brought + "\n";
    }
    /**
     * Construtor de Emprestimo
     * <p>
     *     Cria uma instancia de Emprestimo para ser salvo em uma tabela
     *     com dados de empréstimos da biblioteca.
     * </p>
     * @param borrowId id do empréstimo.
     * @param studentId id do estudante.
     * @param bookId id do livro.
     * @param takenDate data em que o livro foi retirado da biblioteca.
     * @param brought data em que o livro foi devolvido à biblioteca
     */
    public Emprestimo(int borrowId, int studentId, int bookId, String takenDate, String brought) {
        this.borrowId = borrowId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.takenDate = takenDate;
        this.brought = brought;
    }
}
