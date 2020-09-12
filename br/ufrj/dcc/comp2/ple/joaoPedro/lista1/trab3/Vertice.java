package br.ufrj.dcc.comp2.ple.joaoPedro.lista1.trab3;

import java.lang.Math.*;
/**
 * @author      João Pedro Silva
 *
 * <h1>Classe de Vertice para o terceiro exercicio. Programa feito para representar um
 * ponto no plano cartesiano.</h1>
 */

public class Vertice {
  /**
    * <p>Atributos do x e y, no plano cartesiano. Amobos sendo doubles</p>
    */
    public double x;
    public double y;
    /**
     * Distancia entre dois pontos
     * <p>
     * A função recebe apenas um atributo, sendo ele um obejto da classe vertice
     *, e também a função trablha com a instancia da classe.
     * </p>
     *<p>
     *Dentro da função temos apenas o return, calculando o valor da distancia
     *entre dois pontos, utilizando o Teorema de Pitagoras, usando o hypot,
     *função da biblioteca hypot que calcula a hipotenusa em um triangulo retangulo.
     *</p>
     * @param  v2 um ponto no plano cartesiano.
     * @return Distancia entre dois pontos.
     */
    public double distance(Vertice v2){
      return Math.hypot(Math.abs(this.x - v2.x), Math.abs(this.y - v2.y));
    }
    /**
     * Construtor do Vertice
     * <p>
     * A função recebe dois doubles, um x e um y, que são pontos no plano cartesiano.
     * Para construir um ponto no plano.
     * </p>
     * @param  x um ponto na reta abscissa do plano cartesiano.
     * @param  y um ponto na reta ordenada do plano cartesiano.
     */
    public Vertice(double x, double y){
      this.x = x;
      this.y = y;
    }
    /**
     * Construtor do Vertice
     * <p>
     * Construtor vazio para os testes na classe Principal
     * </p>
     */
     public Vertice(){}
}
