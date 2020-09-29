package br.ufrj.dcc.comp2.ple.joaoPedro.lista2.trab1;

import java.util.*;
import java.lang.Math.*;
/**
 * @author      João Pedro Silva
 *
 * <h1>Classe Principal para o primeiro exercicio.
 * </h1>
 */
public class Principal {
  /**
     * Função main.
     * <p>
     *     A função espera de entrada uma string com 6 (seis) atributos que representam
     *     restrições necessárias para filtrar uma lista de veículos que se encaixam nestas restrições.
     *     No fim do programa essa lista de veículos filtrados é mostrada na tela.
     * </p>
     *
     */
  public static void main(String[] args) {
    String entrada;
    while(true){
        try{
          Scanner scanner = new Scanner(System.in);
          entrada = scanner.nextLine();
          String[] teste = entrada.split(", ");
          int passageiros = Integer.parseInt(teste[2]);
          double tempo = Double.parseDouble(teste[4]);
          double carga = Double.parseDouble(teste[3]);
          double distancia = Double.parseDouble(teste[0]);
          if(teste.length != 6){
            throw new IllegalArgumentException("different lengths: " + teste.length + "!= 6 ");
          }
          if(validarDistancia(distancia) && validarMeio(teste[1].toLowerCase()) && validarCarga(carga) && validarClima(teste[5].toLowerCase())
          && validarPassageiros(passageiros) && validarTempo(tempo))
            break;
          else{
            throw new IllegalArgumentException("different lengths: " + teste.length + "!= 6 ");
          }
      }
      catch(NumberFormatException e){
        System.out.println("Não é um número");
      }
      catch(IllegalArgumentException e){
        System.out.println("Entrada errada");
      }
    }
    String[] resultado = entrada.split(", ");
    ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
    instanciar(veiculos);
    for(Iterator<Veiculo> iterator = veiculos.iterator(); iterator.hasNext();){
      Veiculo veiculo = iterator.next();
      int passageiros = Integer.parseInt(resultado[2]);
      double tempo = Double.parseDouble(resultado[4]);
      double carga = Double.parseDouble(resultado[3]);
      double distancia = Double.parseDouble(resultado[0]);
      if(!checarVelocidade(veiculo, distancia, tempo)){
        iterator.remove();
        continue;
      }
      if(!checarCarga(veiculo, carga)){
      iterator.remove();
      continue;
    }
      if(!checarMeio(veiculo, resultado[1].toLowerCase())){
      iterator.remove();
      continue;
    }
      if(!checarClima(veiculo, resultado[5].toLowerCase())){
      iterator.remove();
      continue;
    }
      if(!checarPassageiros(veiculo, passageiros)){
      iterator.remove();
      continue;
    }
    }
    for(Iterator<Veiculo> iterator = veiculos.iterator(); iterator.hasNext();){
      Veiculo veiculo = iterator.next();
      veiculo.printVeiculo();
  }
  }
  /**
     * Função checarVelocidade.
     * <p>
     * Funçao para checar se a velocidade média é de acordo com o veiculo
     * </p>
     * @param  veiculo um objeto Veiculo.
     * @param tempo o tempo
     * @param tempo o distancia
     * @return se batem velocidade maxima com o veiculo.
     */
  public static boolean checarVelocidade(Veiculo veiculo, Double distancia, Double tempo){
      return (veiculo.velocidadeMaxima/2) * tempo >= distancia;
  }
  /**
     * Função checarCarga.
     * <p>
     * Funçao para checar se a carga bate com o veiculo
     * </p>
     * @param  veiculo um objeto Veiculo.
     * @param carga o carga
     * @return se batem o carga com o veiculo.
     */
  public static boolean checarCarga(Veiculo veiculo, Double carga){
      return veiculo.cargaMaxima >= carga;
  }
  /**
     * Função checarMeio.
     * <p>
     * Funçao para checar se o meio bate com o veiculo
     * </p>
     * @param  veiculo um objeto Veiculo.
     * @param meio o meio
     * @return se batem o meio com o veiculo.
     */
  public static boolean checarMeio(Veiculo veiculo, String meio){
    return Arrays.asList(veiculo.getMeios()).contains(meio);
  }
  /**
     * Função checarClima.
     * <p>
     * Funçao para checar se o clima bate com o veiculo
     * </p>
     * @param  veiculo um objeto Veiculo.
     * @param clima o clima
     * @return se batem o clima com o veiculo.
     */
  public static boolean checarClima(Veiculo veiculo, String clima){
    return Arrays.asList(veiculo.getClimas()).contains(clima);
  }
  /**
     * Função checarPassageiros.
     * <p>
     * Funçao para checar se os números de passageiros batem com o veiculo
     * </p>
     * @param  veiculo um objeto Veiculo.
     * @param passageiros o número de passageiros
     * @return se batem o número de passageiros com o veiculo.
     */
  public static boolean checarPassageiros(Veiculo veiculo, Integer passageiros){
    return veiculo.passageirosMaximos >= passageiros;
  }
  /**
     * Função instanciar.
     * <p>
     * Funçao para instanciar a lista de veiculos
     * </p>
     * @param  veiculos ArrayList<Veiculo>.
     */
  public static void instanciar(ArrayList<Veiculo> veiculos){
    veiculos.add(new Carro((Math.random()*((250-180)+1))+180, (Math.random()*((100-80)+1))+180, 5));
    veiculos.add(new Bicicleta((Math.random()*((22-12)+1))+12, (Math.random()*((5-2)+1))+2, 1));
    veiculos.add(new Moto((Math.random()*((200-150)+1))+180, (Math.random()*((20-5)+1))+5, 2));
    veiculos.add(new Caminhao((Math.random()*((120-80)+1))+80, (Math.random()*((2300-1200)+1))+1000, 2));
    veiculos.add(new Jipe((Math.random()*((160-100)+1))+100, (Math.random()*((700-500)+1))+500, 4));
    veiculos.add(new Canoa((Math.random()*((20-10)+1))+10, (Math.random()*((200-100)+1))+100, 3));
    veiculos.add(new Lancha((Math.random()*((150-100)+1))+100, (Math.random()*((800-600)+1))+600, 6));
    veiculos.add(new TanqueAnfibio((Math.random()*((80-40)+1))+40, (Math.random()*((1300-900)+1))+900, 2));
    return;
  }
  /**
     * Função validarCarga.
     * <p>
     * Funçao para validar a carga que o programa aceita
     * </p>
     * @param  carga uma double que seria o carga passado pelo terminal.
     * @return se a carga é valido.
     */
  public static boolean validarCarga(Double carga){
    return carga>=0;
  }
  /**
     * Função validarTempo.
     * <p>
     * Funçao para validar o tempo que o programa aceita
     * </p>
     * @param  tempo uma double que seria o tempo passado pelo terminal.
     * @return se o tempo é valido.
     */
  public static boolean validarTempo(Double tempo){
    return tempo > 0;
  }
  /**
     * Função validarDistancia.
     * <p>
     * Funçao para validar o número da distancia que o programa aceita
     * </p>
     * @param  distancia uma double que seria o distancia passado pelo terminal.
     * @return se o número da distancia é valido.
     */
  public static boolean validarDistancia(double distancia){
    return distancia > 0;
  }
  /**
     * Função validarPassageiros.
     * <p>
     * Funçao para validar o números de passageiros que o programa aceita
     * </p>
     * @param  passageiros uma double que seria o passageiros passado pelo terminal.
     * @return se o número de passageiros é valido.
     */
  public static boolean validarPassageiros(double passageiros){
    return passageiros > 0;
  }
  /**
     * Função validarClima.
     * <p>
     * Funçao para validar o clima que o programa aceita
     * </p>
     * @param  clima uma String que seria o clima passado pelo terminal.
     * @return se o clima é valido.
     */
  public static boolean validarClima(String clima){
    String[] climas = {"sol", "chuva"};
    return Arrays.asList(climas).contains(clima);
  }
  /**
     * Função validarMeio.
     * <p>
     * Funçao para validar os meios que o programa aceita
     * </p>
     * @param  meio uma String que seria o meio passado pelo terminal.
     * @return se o meio é valido.
     */
  public static boolean validarMeio(String meio){
    String[] meios = {"rodovia", "estrada de terra", "rio", "lago", "mar"};
    return Arrays.asList(meios).contains(meio);
  }
}
