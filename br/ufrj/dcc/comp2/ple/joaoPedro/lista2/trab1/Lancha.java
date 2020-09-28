public class Lancha extends Veiculo implements Aquatico, InteriorImpermeavel{
  public Lancha(double velocidadeMaxima , double cargaMaxima, int passageirosMaximos){
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
