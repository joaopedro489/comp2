package br.ufrj.dcc.comp2.ple.joaoPedro.lista2.trab2;

/**
 * @author      João Pedro Silva
 *
 * <h1>Classe de Heroi para o segundo exercicio.
 * Possuindo o seu construtor, e cinco funções</h1>
 */
public class Heroi {
  /**
    * <p>Atributos do atk e hp. Ambos sendo int.
    </p>
    */
  private final int atk;
  private int hp;
  /**
   * setPontosDeVida() -> determina os pontos de vida do Heroi
   * <p>
   * A função recebe o hp e trabalha com a instancia do objeto.
   * </p>
   *<p>
   *Dentro da função é determinado o hp do heroi
   *</p>
   * @param hp um interio determinando os pontos de vida.
   */
  public void setPontosDeVida(int hp){
    this.hp = hp;
  }
  /**
   * getAtaque() -> retorna o valor de ataque de um heroi
   * <p>
   * A função retorna o valor de ataque do heroi.
   * </p>
   * @return o ataque do heroi.
   */
  public int getAtaque(){
    return this.atk;
  }
  /**
   * getPontosDeVida() -> retorna o valor do hp de um Heroi
   *<p>
   *Apenas retorna o hp do heroi.
   *</p>
   * @return o hp do heroi.
   */
  public int getPontosDeVida(){
    return this.hp;
  }
  /**
   * atacar() -> Retorna o valor do dano do ataque do heroi
   * <p>
   * A função não recebe nada apenas trabalha com a instancia do objeto.
   * </p>
   *<p>
   *Dentro da função calcula-se o ataque do heroi a partir de um random entre a
   *metade do dano máximo e o dano máximo do heroi e retorna esse valor
   *</p>
   * @return o valor do ataque do heroi.
   */
  public int atacar(){
    int power = (int)(Math.random() * ((this.atk - (this.atk/2)) + 1)) + (this.atk/2);
    return power;
  }
  /**
   * Construtor do Heroi
   * <p>
   * A função recebe dois int cada um representando os atributos
   * da classe Heroi, atk e hp.
   * </p>
   * @param  atk um int representando o ataque máximo.
   * @param  hp um int representando os pontos de vida de um heroi.
   */
  public Heroi(int atk, int hp){
    this.hp = hp;
    this.atk = atk;
  }
}
