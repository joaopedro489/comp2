package br.ufrj.dcc.comp2.ple.joaoPedro.lista1.trab3;
import java.lang.Math.*;
/**
 * @author      João Pedro Silva
 *
 *<h1>Classe de Circulo para o terceiro exercicio. Programa feito para representar um
 *circulo</h1>
 */
public class Circulo {
  /**
    * <p>Atributos de raio de um circulo e do vertice do seu centro. Raio sendo um double
    e o centro do círculo sendo um Vertice, classe criada para o exercício</p>
    */
  public double raio ;
  public Vertice center;
  /**
   * Calcula a Área de um Círculo
   * <p>
   * A função não recebe nenhum atributo, trablhando apenas com a instancia da classe.
   * </p>
   *<p>
   *Dentro da função temos apenas o return, calculando o valor da area do circulo pela
   *sua fórmula padrão, utilizando a biblioteca Math de Java para usar o valor de PI
   *e a função pow()
   *</p>
   * @return A area do circulo.
   */
  public double area(){
      return Math.PI * Math.pow(this.raio, 2);
  }
  /**
   * Calcula o Perimetro de um Circulo
   * <p>
   * A função não recebe nenhum atributo, trablhando apenas com a instancia da classe.
   * </p>
   *<p>
   *Dentro da função temos apenas o return, calculando o valor do perimetro do circulo pela
   *sua fórmula padrão, utilizando a biblioteca Math de Java para usar o valor de PI
   *</p>
   * @return A perimetro de um Circulo.
   */
  public double perimeter(){
    return 2 * Math.PI * this.raio;
  }
  /**
   * Construtor do Circulo
   * <p>
   * A função recebe um double e um objeto Vertice, que são o raio e o centro do circulo, respectivamente,
   *para que seja construído uma instancia da classe Circulo.
   * </p>
   * @param  raio raio do circulo criado.
   * @param  center um objeto Vertice, criado anteriormente, que seria o centro do circulo.
   */
  public Circulo(double raio, Vertice center){
    this.raio = raio;
    this.center = center;
  }
}
