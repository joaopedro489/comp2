public class Canoa extends Veiculo implements Aquatico{
  public Canoa(double velocidadeMaxima , double cargaMaxima, int passageirosMaximos){
    super(velocidadeMaxima, cargaMaxima, passageirosMaximos);
  }
  @Override
  protected String[] getMeios(){
    return this.meios;
  }
}
