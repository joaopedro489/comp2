package br.ufrj.dcc.comp2.ple.joaoPedro.lista2.trab2;

/**
 * @author      João Pedro Silva
 *
 * <h1>Classe abstrata de Monstro para o segundo exercicio.
 * Possuindo o seu construtor, e cinco funções</h1>
 */
public abstract class Monstro {
  /**
    * <p>Atributos do atk e hp. Ambos sendo double. E uma String nomeDeAtaque.
    </p>
    */
  private final int atk;
  private int hp;
  private String nomeDeAtaque;
  /**
   * atacar() -> Funçao abstrata a ser implementada nas subclasses
   */
  public abstract int atacar();
  /**
   * getAtaque() -> retorna o valor de ataque de um Monstro
   * <p>
   * A função retorna o valor de ataque do monstro.
   * </p>
   * @return o ataque do monstro.
   */
  public int getAtaque(){
    return this.atk;
  }
  /**
   * getPontosDeVida() -> retorna o valor do hp de um Monstro
   *<p>
   *Apenas retorna o hp do monstro.
   *</p>
   * @return o hp do monstro.
   */
  public int getPontosDeVida(){
    return this.hp;
  }
  /**
   * setPontosDeVida() -> determina os pontos de vida do Monstro
   * <p>
   * A função recebe o hp e trabalha com a instancia do objeto.
   * </p>
   *<p>
   *Dentro da função é determinado o hp do monstro
   *</p>
   * @param hp um interio determinando os pontos de vida.
   */
  public void setPontosDeVida(int hp){
    this.hp = hp;
  }
  /**
   * getNomeDeAtaque() -> retorna o nome do ataque de um Monstro
   *<p>
   *Apenas retorna o nome do ataque do monstro.
   *</p>
   * @return o nome do ataque do monstro.
   */
  public String getNomeDeAtaque(){
    return this.nomeDeAtaque;
  }
  /**
   * Construtor do Monstro
   * <p>
   * A função recebe dois int e uma String cada um representando os atributos
   * da classe Monstro, atk, hp e nomeDeAtaque.
   * </p>
   * @param  atk um int representando o ataque máximo.
   * @param  hp um int representando os pontos de vida de um monstro.
   * @param  nomeDeAtaque o nome do ataque do monstro.
   */
  public Monstro(int atk, int hp, String nomeDeAtaque){
    this.atk = atk;
    this.hp = hp;
    this.nomeDeAtaque = nomeDeAtaque;
  }


}
