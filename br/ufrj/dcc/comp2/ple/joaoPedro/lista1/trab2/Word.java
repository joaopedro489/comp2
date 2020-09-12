package br.ufrj.dcc.comp2.ple.joaoPedro.lista1.trab2;
import java.util.*;
/**
 * @author      João Pedro Silva
 *
 *  <h1>Classe de Word para o segundo exercicio. Programa feito para receber uma
 *  lista de palavras, quando recebe uma string vazia para de receber valores
 *  e depois printa a lista de palavras em ordem alfabética</h1>
 */
public class Word{
  /**
   * Função main
   * <p>
   * A função não espera de entrada uma lista de palavras é só parará de ler
   * as palavras quando receber uma string vazia
   * E depois será organizado em ordem alfabetica a lista e no final será
   *printado as palavras que foram recebidas em ordem alfabetica.
   *</p>
   */
  public static void main(String[] args) {
    ArrayList<String> words = new ArrayList<String>();
    Scanner scanner = new Scanner(System.in);
    while(true){
      System.out.println("Digite a palavra");
      System.out.println("Para parar de digitar, aperte enter");
      String linha = scanner.nextLine();
      if(linha.equals("")){
        break;
      }
        words.add(linha);
    }
    System.out.println("========================");
    Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
    for ( String word : words ) {
        System.out.println(word);
    }
  }
}
