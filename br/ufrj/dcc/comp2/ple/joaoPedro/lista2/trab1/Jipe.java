public class Jipe extends Veiculo implements OffRoad, InteriorImpermeavel{
  public Jipe(double velocidadeMaxima , double cargaMaxima, int passageirosMaximos){
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
