package br.ufrj.dcc.comp2.ple.joaoPedro.lista2.trab2;

/**
 * @author      João Pedro Silva
 *
 * <h1>Classe de Fantasma para o segundo exercicio, subclasse de Monstro.
 * Possuindo o seu construtor, e uma funções sobreescrita em relação a classe principal
 * Monstro</h1>
 */
public class Fantasma extends Monstro {
  /**
   * atacar() -> Retorna o valor do dano do ataque do monstro
   * <p>
   * A função não recebe nada apenas trabalha com a instancia do objeto.
   * </p>
   *<p>
   *Dentro da função calcula-se o ataque do monstro a partir de um random entre a
   *metade do dano máximo e o dano máximo do monstro e retorna esse valor
   *</p>
   * @return o valor do ataque do monstro.
   */
  @Override
  public int atacar(){
    int power = (int)(Math.random() * ((getAtaque() - (getAtaque()/2)) + 1)) + (getAtaque()/2);
    return power;
  }
  /**
   * Construtor do Fantasma
   * <p>
   * A função recebe dois int e uma String cada um representando os atributos
   * da classe Monstro, atk, hp e nomeDeAtaque.
   * E ela chama o construtor da classe Monstro
   * </p>
   * @param  atk um int representando o ataque máximo.
   * @param  hp um int representando os pontos de vida de um fantasma.
   * @param  nomeDeAtaque uma String representando o nome do ataque do monstro.
   */
  public Fantasma(int atk, int hp, String nomeDeAtaque){
    super(atk, hp, nomeDeAtaque);
  }

}
