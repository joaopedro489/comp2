package br.ufrj.dcc.comp2.ple.joaoPedro.lista2.trab1;

/**
 * @author      João Pedro Silva
 *
 * <h1>Classe de Bicicleta para o primeiro exercicio, subclasse de Veiculo. Possuindo apenas
 o seu construtor</h1>
 */
public class Bicicleta extends Veiculo {
  /**
   * Construtor do Bicicleta
   * <p>
   * A função recebe três doubles cada um representando os atributos
   * da classe Veiculo, velocidadeMaxima, cargaMaxima e passageirosMaximos.
   * E ela chama o construtor da classe Veiculo
   * </p>
   * @param  velocidadeMaxima um double representando a velocidade maxima.
   * @param  cargaMaxima um double representando a carga maxima.
   * @param  passageirosMaximos um double representando a quantidade maxima de passageiro.
   */
  public Bicicleta(double velocidadeMaxima , double cargaMaxima, int passageirosMaximos){
    super(velocidadeMaxima, cargaMaxima, passageirosMaximos);
  }

}
