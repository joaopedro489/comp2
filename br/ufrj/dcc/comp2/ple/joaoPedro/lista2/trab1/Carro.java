package br.ufrj.dcc.comp2.ple.joaoPedro.lista2.trab1;
/**
 * @author      João Pedro Silva
 *
 * <h1>Classe de Carro para o primeiro exercicio, subclasse de Veiculo e implementa
 * a interface InteriorImpermeavel. Possuindo
 * o seu construtor, e uma funçãs sobreescrita em relação a classe principal
 * Veiculo</h1>
 */
public class Carro extends Veiculo implements InteriorImpermeavel{
  /**
   * Construtor do Carro
   * <p>
   * A função recebe três doubles cada um representando os atributos
   * da classe Veiculo, velocidadeMaxima, cargaMaxima e passageirosMaximos.
   * E ela chama o construtor da classe Veiculo
   * </p>
   * @param  velocidadeMaxima um double representando a velocidade maxima.
   * @param  cargaMaxima um double representando a carga maxima.
   * @param  passageirosMaximos um double representando a quantidade maxima de passageiro.
   */
    public Carro(double velocidadeMaxima , double cargaMaxima, int passageirosMaximos){
        super(velocidadeMaxima, cargaMaxima, passageirosMaximos);
    }
    /**
     * getClimas() -> Retorna todos os climas onde o Carro pode andar
     * <p>
     * A função não recebe nada apenas trabalha com a instancia do objeto.
     * </p>
     *<p>
     *Dentro da função apenas um return que retorna os atributos que a Classe
     *implementa a partir da interface InteriorImpermeavel
     * que são os climas que o veiculo se locomove.
     *</p>
     * @return Os climas pode andar.
     */
    @Override
    protected String[] getClimas(){
      return this.climas;
    }
}
