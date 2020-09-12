package br.ufrj.dcc.comp2.ple.joaoPedro.lista1.trab3;

import java.lang.Math.*;
/**
 * @author      João Pedro Silva
 *
 * <h1> Classe de Triangulo para o terceiro exercicio. Programa feito para representar um
 * Triangulo.</h1>
 */
public class Triangulo{
  /**
    * <p>Atributos de a, b e c, que são vertices de um triangulo. E os três sendo
    * representados por uma instancia de Vertice</p>
    */
    public Vertice a;
    public Vertice b;
    public Vertice c;
    /**
     * Calcula a Área de um Triangulo
     * <p>
     * A função não recebe nenhum atributo, trablhando apenas com a instancia da classe.
     * </p>
     *<p>
     *Dentro da função calculando o valor de cada lado do triangulo para ser possivel calcular
     *sua área, a funçao utiliza o Teorema de Herão, utilizando a biblioteca Math de Java para calcular a raiz
     *</p>
     * @return A area do triangulo.
     */
    public double area(){
        double sideA, sideB, sideC = 0.0;
        sideA = this.a.distance(this.b);
        sideB = this.b.distance(this.c);
        sideC = this.c.distance(this.a);
        return Math.sqrt((this.perimeter() * (this.perimeter() - sideA) * (this.perimeter() - sideB)  * (this.perimeter() - sideC)));
    }
    /**
     * Calcula o Perimetro de um Triangulo
     * <p>
     * A função não recebe nenhum atributo, trablhando apenas com a instancia da classe.
     * </p>
     *<p>
     *Dentro da função calculando o valor de cada lado do triangulo para ser possivel calcular
     *seu perimetro, utilziando a formula padrão.
     *</p>
     * @return A perimetro de um Triangulo.
     */
    public double perimeter(){
      double sideA, sideB, sideC = 0.0;
      sideA = this.a.distance(this.b);
      sideB = this.b.distance(this.c);
      sideC = this.c.distance(this.a);
      return (sideA +sideB +sideC)/2;
    }
    /**
     * Construtor do Triangulo
     * <p>
     * A função recebe três objetos da classe Vertices, um a, um b e um c, que são pontos no plano cartesiano.
     * Para construir um triangulo.
     * </p>
     * @param  a um vertice no plano cartesiano.
     * @param  b um vertice no plano cartesiano.
     * @param  c um vertice no plano cartesiano.
     */
    public Triangulo(Vertice a, Vertice b, Vertice c){
      this.a = a;
      this.b = b;
      this.c = c;
    }
}
