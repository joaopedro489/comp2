package br.ufrj.dcc.comp2.ple.joaoPedro.lista2.trab1;

import java.io.*;
import java.lang.*;
import java.util.*;
/**
 * @author      João Pedro Silva
 *
 * <h1>Classe de TanqueAnfibio para o primeiro exercicio, subclasse de Veiculo e implementa
 * as interfaces OffRoad e InteriorImpermeavel. Possuindo
 * o seu construtor, e duas funções sobreescritas em relação a classe principal
 * Veiculo</h1>
 */
public class TanqueAnfibio extends Veiculo implements Aquatico, InteriorImpermeavel{
  /**
   * Construtor do TanqueAnfibio
   * <p>
   * A função recebe três doubles cada um representando os atributos
   * da classe Veiculo, velocidadeMaxima, cargaMaxima e passageirosMaximos.
   * E ela chama o construtor da classe Veiculo
   * </p>
   * @param  velocidadeMaxima um double representando a velocidade maxima.
   * @param  cargaMaxima um double representando a carga maxima.
   * @param  passageirosMaximos um double representando a quantidade maxima de passageiro.
   */
  public TanqueAnfibio(double velocidadeMaxima , double cargaMaxima, int passageirosMaximos){
    super(velocidadeMaxima, cargaMaxima, passageirosMaximos);
  }
  /**
   * getMeios() -> Retorna todos os meios onde o Caminhão se locomove
   * <p>
   * A função não recebe nada apenas trabalha com a instancia do objeto.
   * </p>
   *<p>
   *Dentro da função apenas um return que retorna os atributos que a Classe
   *implementa a partir da interface OffRoad que são os meios que o veiculo
   *se locomove.
   *</p>
   * @return Os meios que o veiculo se locomove.
   */
  @Override
  protected String[] getMeios(){
    String[] meios = Aquatico.meios;
    return meios;
  }
  /**
   * getMeios() -> Retorna todos os climas onde o Caminhao pode andar
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
