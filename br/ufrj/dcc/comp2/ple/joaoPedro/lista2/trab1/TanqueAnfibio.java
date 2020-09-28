import java.io.*;
import java.lang.*;
import java.util.*;
public class TanqueAnfibio extends Veiculo implements Aquatico, InteriorImpermeavel{
  public TanqueAnfibio(double velocidadeMaxima , double cargaMaxima, int passageirosMaximos){
    super(velocidadeMaxima, cargaMaxima, passageirosMaximos);
  }

  @Override
  protected String[] getMeios(){
    String[] meios = Aquatico.meios;
    return meios;
  }
  @Override
  protected String[] getClimas(){
    return this.climas;
  }
}
