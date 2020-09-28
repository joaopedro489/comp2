public class Caminhao extends Veiculo implements OffRoad, InteriorImpermeavel {
  public Caminhao(double velocidadeMaxima , double cargaMaxima, int passageirosMaximos){
    super(velocidadeMaxima, cargaMaxima, passageirosMaximos);
  }
  @Override
  protected String[] getMeios(){
    return this.meios;
  }
  @Override
  protected String[] getClimas(){
    return this.climas;
  }
}
