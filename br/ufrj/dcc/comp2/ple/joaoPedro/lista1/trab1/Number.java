package br.ufrj.dcc.comp2.ple.joaoPedro.lista1.trab1;
import java.util.*;
/**
 * @author      João Pedro Silva
 *
 * <h1>Classe de Number para o primeiro exercicio. Programa feito para receber uma
 * certa quantidade de números e depois printa a média</h1>
 */
public class Number {
  /**
   * Função main
   * <p>
   * A função espera de entrada um número determinando a quatidade de números que serão
   * lidos pelo scanner, e também será calculada a média desses números, em uma outra função
   * e num print final será mostrado a média dos números
   *</p>
   */
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int quantity = 0;
      boolean pass = false;
      while(true){
        pass = false;
          try{
            System.out.println("Digite a quantidade de números que você deseja: ");
            quantity = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Não é inteiro");
            scanner.next();
            pass = true;
        }
        if(!pass){
          break;
        }
      }
      int x = quantity;
      double sumOfTotal = 0.0;
      sumOfTotal = sumAllNumbers(scanner, x, pass);
      System.out.println("A média é de: " + (sumOfTotal/x));
    }
    /**
     * Soma todos os números obtidos pelo scanner
     * <p>
     * A função recebe o scanner para que ele leia todos os números passados,
     *recebe também um inteiro para saber quantos números serão lidos, e um booleano
     *necessário para validar se os dados passados para sair do loop de validação
     * </p>
     *<p>
     *Dentro da função temos um loop for, para ser possível de obter todos os números, e um loop while,
     *para validar os dados recebidos do scanner usando um try e catch,
     *e apenas sai desse loop quando o fluxo
     *não passa pelo catch, isto é quando nao apresenta erros. Então se o scanner.nextDouble()
     *não apresentar erros a variável sumOfTotal já recebe e soma os números para que
     *no retorno da função seja possível retornar a soma de todos os números
     *</p>
     * @param  scanner Um objeto da classe Scanner.
     * @param  x total de números que serão recebidos.
     * @param  pass booleano, anteriormente já utilizado, para checar try/catch.
     * @return A soma de todos os números obtidos pelo scanner.
     */
    public static double sumAllNumbers(Scanner scanner, int x, boolean pass){
      double sumOfTotal = 0.0;
      for(int i = 0; i < x; i++){
        while(true){
          pass = false;
          try{
            System.out.println("Digite o " + (i + 1) + " numero: ");
            sumOfTotal += scanner.nextDouble();
          } catch(InputMismatchException e){
            System.out.println("Não é um número");
            scanner.next();
            pass = true;
          }
          if(!pass){
            break;
          }
        }
      }
      return sumOfTotal;
    }
}
