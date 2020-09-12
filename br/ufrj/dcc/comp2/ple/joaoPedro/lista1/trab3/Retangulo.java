package br.ufrj.dcc.comp2.ple.joaoPedro.lista1.trab3;

/**
 * @author      João Pedro Silva
 *
 * <h1> Classe de Retangulo para o terceiro exercicio. Programa feito para representar um
 * Retangulo.</h1>
 */
public class Retangulo{
  /**
    * <p>Atributos de a, b, c e d, que são vertices de um retangulo. E os quatros sendo
    * representados por uma instancia de Vertice</p>
    */
  public Vertice a;
  public Vertice b;
  public Vertice c;
  public Vertice d;
  /**
   * Calcula a Área de um Retangulo
   * <p>
   * A função não recebe nenhum atributo, trablhando apenas com a instancia da classe.
   * </p>
   *<p>
   *Dentro da função, primeiro precisa ser checado quais são os lados do retangulo, a base e a altura,
   * após isso é calculando o valor de cada lado do retangulo, a base e a altura, para ser possivel calcular
   *sua área, utilizando a equação padrão.
   *</p>
   * @return A area do retangulo.
   */
  public double area(){
      double sideA, sideB = 0.0;
      if(!(checkSideX(this.a, this.b)) && checkSideY(this.a, this.b) ){
        sideA = this.a.distance(this.b);
      }
      else if(!(checkSideX(this.a, this.c)) && checkSideY(this.a, this.b)){
        sideA = this.a.distance(this.c);
      }
      else{
        sideA = this.a.distance(this.d);
      }
      if(checkSideX(this.b, this.c)  && !(checkSideY(this.b, this.c))){
        sideB = this.b.distance(this.c);
      }
      else if(checkSideX(this.b, this.d) && !(checkSideY(this.b, this.d))){
        sideB = this.b.distance(this.d);
      }
      else{
        sideB = this.b.distance(this.a);
      }
      return sideA * sideB;
  }
  /**
   * Calcula a Área de um Retangulo
   * <p>
   * A função não recebe nenhum atributo, trablhando apenas com a instancia da classe.
   * </p>
   *<p>
   *Dentro da função, primeiro precisa ser checado quais são os lados do retangulo, a base e a altura,
   * após isso é calculando o valor de cada lado do retangulo, a base e a altura, para ser possivel calcular
   *sua área, utilizando a equação padrão.
   *</p>
   * @return A area do retangulo.
   */
  public double perimeter(){
    double sideA, sideB = 0.0;
    if(!(checkSideX(this.a, this.b)) && checkSideY(this.a, this.b) ){
      sideA = this.a.distance(this.b);
    }
    else if(!(checkSideX(this.a, this.c)) && checkSideY(this.a, this.b)){
      sideA = this.a.distance(this.c);
    }
    else{
      sideA = this.a.distance(this.d);
    }
    if(checkSideX(this.b, this.c)  && !(checkSideY(this.b, this.c))){
      sideB = this.b.distance(this.c);
    }
    else if(checkSideX(this.b, this.d) && !(checkSideY(this.b, this.d))){
      sideB = this.b.distance(this.d);
    }
    else{
      sideB = this.b.distance(this.a);
    }
    return (sideA*2) + (sideB*2);
  }
  /**
   * Checar se o X é o mesmo entre dois pontos
   * <p>
   * A função recebe 2 vertices para calcular se os vertices estão no mesmo ponto em x
   * e retorna um booleano afirmando se estão no mesmo x
   * </p>
   *<p>
   * A função se torna necessária para calcular os lados do retangulo, conferindo se o
   * x é o mesmo é possivel determinar que esses pontos não formam a base e sim a altura
   *</p>
   * @param  a um vertice do retangulo.
   * @param  b um vertice do retangulo.
   * @return um booleano sobre a vericidade se a e b estão no mesmo x
   */
  public boolean checkSideX(Vertice a, Vertice b){
      return a.x == b.x;
  }
  /**
   * Checar se o Y é o mesmo entre dois pontos
   * <p>
   * A função recebe 2 vertices para calcular se os vertices estão no mesmo ponto em y
   * e retorna um booleano afirmando se estão no mesmo y
   * </p>
   *<p>
   * A função se torna necessária para calcular os lados do retangulo, conferindo se o
   * x é o mesmo é possivel determinar que esses pontos não formam a altura e sim a base
   *</p>
   * @param  a um vertice do retangulo.
   * @param  b um vertice do retangulo.
   * @return um booleano sobre a vericidade se a e b estão no mesmo y
   */
  public boolean checkSideY(Vertice a, Vertice b){
    return a.y == b.y;
  }
  /**
   * Construtor do Retangulo
   * <p>
   * A função recebe quatro objetos da classe Vertices, um a, um b, um c e um d,
   * que são pontos no plano cartesiano.
   * Para construir um retangulo.
   * </p>
   * @param  a um vertice no plano cartesiano.
   * @param  b um vertice no plano cartesiano.
   * @param  c um vertice no plano cartesiano.
   * @param  d um vertice no plano cartesiano.
   */
  public Retangulo(Vertice a, Vertice b, Vertice c, Vertice d){
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
  }
}
