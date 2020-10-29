package br.ufrj.dcc.comp2.ple.joaoPedro.lista4;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.lang.*;
/**
 * @author João Pedro Silva
 *
 *<h1>
 *     Classe Principal para o exercício da lista 4.
 *</h1>
 * <p>
 *     O programa foi feito para representar uma biblioteca escolar.
 *     Em nosso sistema é possível cadastrar estudantes, livros e autores,
 *     além de emprestimos e devoluções dde livros.
 *     Existe a possibilidade de executar diversas consultas sobre
 *     os estudantes, livros, autores e gemeros sobre os empréstimos feitos
 *     além de consultar os emprestimos.
 * </p>
 */
public class Principal {
    /**
     * <p>
     *     A função foi feita para representar um menu da biblioteca para
     *     ser possível realizar suas operações.
     * </p>
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seja Bem Vindo a Biblioteca.");
        while(true){
            System.out.println("Qual operação você deseja realizar?");
            System.out.println("1 - Cadastro novo Estudante.");
            System.out.println("2 - Cadastro novo Livro.");
            System.out.println("3 - Realizar uma Consulta.");
            System.out.println("4 - Empréstimo de Livro");
            System.out.println("5 - Devolução de Livro");
            System.out.println("6 - Sair do Programa.");
            String linha = scanner.nextLine();
            if(linha.equals("6"))
                break;
            if(linha.equals("1")){
                Estudante.cadastroEstudante();
                continue;
            }
            if(linha.equals("2")){
                Livro.cadastroLivro();
                continue;
            }
            if(linha.equals("3")){
                System.out.println("Qual consulta deseja ser realizada?");
                System.out.println("1 - Consulta aos N últimos empréstimos de livros");
                System.out.println("2 - Consulta aos empréstimos (fechados ou em aberto) com mais de N dias.");
                System.out.println("3 - Consulta aos N estudantes que pegaram mais livros emprestados.");
                System.out.println("4 - Consulta aos N livros mais emprestados.");
                System.out.println("5 - Consulta aos N autores mais populares.");
                System.out.println("6 - Consulta aos estilos literários mais populares.");
                System.out.println("7 - Consulta aos estudantes com mais pontos");
                String resposta = scanner.nextLine();
                switch(resposta){
                    case "1" -> {
                        Emprestimo.ultimosEmprestimos();
                        continue;
                    }
                    case "2" -> {
                        Emprestimo.consultaDiasEmprestimos();
                        continue;
                    }
                    case "3" -> {
                        Estudante.consultaEstudantesEmprestimos(false);
                        continue;
                    }
                    case "4" -> {
                        Scanner scanner2 = new Scanner(System.in);
                        System.out.println("Quantos livros deseja consultar?");
                        int n = 0;
                        while(true){
                            try{
                                n = scanner2.nextInt();
                                break;
                            }
                            catch(InputMismatchException e){
                                System.out.println("Não é um Inteiro");
                                continue;
                            }
                        }
                        Livro.consultaLivroEmprestimo(n);
                        continue;
                    }
                    case "5" -> {
                        Autor.consultaAutorEmprestimo();
                        continue;
                    }
                    case "6" -> {
                        Genero.consultaGeneroEmprestimo();
                        continue;
                    }
                    case "7" ->{
                        Estudante.consultaEstudantesEmprestimos(true);
                        continue;
                    }
                    default -> {
                        System.out.println("Esta não é uma opção válida!");
                        continue;
                    }
                }
            }
            if(linha.equals("4")){
                Emprestimo.emprestimo();
                continue;
            }
            if(linha.equals("5")){
                Emprestimo.devolver();
                continue;
            }
            else{
                System.out.println("Opção Inválida");
            }
        }
    }
}
