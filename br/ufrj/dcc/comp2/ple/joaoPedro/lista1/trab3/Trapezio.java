package br.ufrj.dcc.comp2.ple.joaoPedro.lista1.trab3;
import java.lang.Math.*;
/**
 * @author      João Pedro Silva
 *
 * <h1> Classe de Trapezio para o terceiro exercicio. Programa feito para representar um
 * Trapezio.</h1>
 */
public class Trapezio {
  /**
    * <p>Como houve dificuldades de representar um Trapezio a solução encontrada foi de
    * utilizar os atributos para representar os vertices das bases do trapezio, com isso
    *os atributos são baseMaiorVertice1, baseMaiorVertice2, baseMenorVertice1 e baseMenorVertice2, onde
    * o 1 é o vertice da esquerda e o 2 é o vertice da direita da base. E os quatros sendo
    * representados por uma instancia de Vertice</p>
    */
  public Vertice baseMaiorVertice1;
  public Vertice baseMaiorVertice2;
  public Vertice baseMenorVertice1;
  public Vertice baseMenorVertice2;
  /**
   * Calcula a Área de um Trapezio
   * <p>
   * A função não recebe nenhum atributo, trablhando apenas com a instancia da classe.
   * </p>
   *<p>
   *Dentro da função calculando o valor de cada base do trapezio pela distancia entre os vertices das bases,
   *e após isso calcula-se a altura utilizando os primeiros vetices das bases, sendo que se for um Trapezio
   *reto só precisaria da distancia dos y's entre os pontos. Por fim calcula-se a área pela fórmula padrão.
   *</p>
   * @return A area do trapezio.
   */
  public double area(){
    double baseMaior, baseMenor, altura = 0.0;
    baseMaior = this.baseMaiorVertice1.distance(this.baseMaiorVertice2);
    baseMenor = this.baseMenorVertice1.distance(this.baseMenorVertice2);
    if(!(this.baseMaiorVertice1.x == this.baseMenorVertice1.x)){
      altura = Math.sqrt(Math.pow((this.baseMaiorVertice1.distance(this.baseMenorVertice1)), 2) - Math.pow(((baseMaior - baseMenor)/2), 2));
    }
    else{
      altura = Math.abs(this.baseMaiorVertice1.y - this.baseMenorVertice1.y);
    }
    return ((baseMaior - baseMenor) * altura)/2;
  }
  /**
   * Calcula o Perimetro de um Trapezio
   * <p>
   * A função não recebe nenhum atributo, trablhando apenas com a instancia da classe.
   * </p>
   *<p>
   *Dentro da função calculando o valor de cada base do trapezio para ser possivel calcular
   *seu perimetro, e no return dele também se calcula os lados do poligono.
   *</p>
   * @return A perimetro de um Triangulo.
   */
  public double perimeter(){
    double baseMaior, baseMenor = 0.0;
    baseMaior = this.baseMaiorVertice1.distance(this.baseMaiorVertice2);
    baseMenor = this.baseMenorVertice1.distance(this.baseMenorVertice2);
    return (this.baseMaiorVertice1.distance(this.baseMenorVertice1)) + (this.baseMaiorVertice2.distance(this.baseMenorVertice2)) + baseMaior + baseMenor;
  }
  /**
   * Construtor do Trapezio
   * <p>
   * A função recebe quatro objetos da classe Vertices, um baseMaiorVertice1, um baseMaiorVertice2, um baseMenorVertice1, e um baseMenorVertice2,
   * que são pontos no plano cartesiano.
   * Para construir um trapezio.
   * </p>
   * @param  baseMaiorVertice1 um vertice no plano cartesiano.
   * @param  baseMaiorVertice2 um vertice no plano cartesiano.
   * @param  baseMenorVertice1 um vertice no plano cartesiano.
   * @param  baseMenorVertice2 um vertice no plano cartesiano.
   */
  public Trapezio(Vertice baseMaiorVertice1, Vertice baseMaiorVertice2, Vertice baseMenorVertice1, Vertice baseMenorVertice2){
    this.baseMaiorVertice1 = baseMaiorVertice1;
    this.baseMaiorVertice2 = baseMaiorVertice2;
    this.baseMenorVertice1 = baseMenorVertice1;
    this.baseMenorVertice2 = baseMenorVertice2;
  }
}
