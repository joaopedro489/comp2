package br.ufrj.dcc.comp2.ple.joaoPedro.lista2.trab1;
/**
 * @author      João Pedro Silva
 *
 * <h1>Classe abstrata de Veiculo para o primeiro exercicio. Possuindo
 * o seu construtor, e três funções.
 * Veiculo</h1>
 */
public abstract class Veiculo {
  /**
    * <p>Atributos do velocidadeMaxima,cargaMaxima e passageirosMaximos.
    *Os dois primeiros sendo double e o ultimo sendo int.
    *</p>
    */
    public double velocidadeMaxima;
    public double cargaMaxima;
    public int passageirosMaximos;
    /**
     * printVeiculo() ->Funçao para printar todos atriburos da classe e subclasses de Veiculo
     * <p>
     * A função não recebe nada apenas trabalha com a instancia do objeto.
     * </p>
     *<p>
     *Dentro da função apenas um println para setar a maneria de como o objeto
     *será apresentado no display
     *</p>
     */
    protected void printVeiculo(){
      System.out.println( this.getClass().getSimpleName() + ": velocidade máxima = " + String.format("%.2f", this.velocidadeMaxima) + "km/h, carga máxima= " +
      String.format("%.2f", this.cargaMaxima) +
      "kg, maximo passageiros = " + (this.passageirosMaximos - 1 ));
    }
    /**
     * getMeios() ->Retorna todos os meios onde o Veiculo se locomove
     * <p>
     * A função não recebe nada apenas trabalha com a instancia do objeto.
     * </p>
     *<p>
     *Dentro da função apenas um return que retorna por padrão o meio rodovia
     * no qual alguns veiculos podem se locomover.
     *</p>
     * @return Os meios que o veiculo se locomove.
     */
    protected String[] getMeios(){
      String[] meios = {"rodovia"};
      return meios;
    }
    /**
     * getClimas() -> Retorna todos os climas onde o Veiculo pode andar
     * <p>
     * A função não recebe nada apenas trabalha com a instancia do objeto.
     * </p>
     *<p>
     *Dentro da função apenas um return que retorna por padrão o clima sol
     * no qual qualquer veiculo pode andar.
     *</p>
     * @return Os climas pode andar.
     */
    protected String[] getClimas(){
      String[] climas = {"sol"};
      return climas;
    }
    /**
     * Construtor do Veiculo
     * <p>
     * A função recebe três doubles cada um representando os atributos
     * da classe, velocidadeMaxima, cargaMaxima e passageirosMaximos.
     * </p>
     * @param  velocidadeMaxima um double representando a velocidade maxima.
     * @param  cargaMaxima um double representando a carga maxima.
     * @param  passageirosMaximos um double representando a quantidade maxima de passageiro.
     */
    public Veiculo(double velocidadeMaxima , double cargaMaxima, int passageirosMaximos){
      this.velocidadeMaxima = velocidadeMaxima;
      this.cargaMaxima = cargaMaxima;
      this.passageirosMaximos = passageirosMaximos;
    }
}
