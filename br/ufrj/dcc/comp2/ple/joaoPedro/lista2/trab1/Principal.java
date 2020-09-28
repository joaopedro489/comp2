import java.util.*;
import java.lang.Math.*;

public class Principal {
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
  public static boolean checarVelocidade(Veiculo veiculo, Double distancia, Double tempo){
      return (veiculo.velocidadeMaxima/2) * tempo >= distancia;
  }
  public static boolean checarCarga(Veiculo veiculo, Double carga){
      return veiculo.cargaMaxima >= carga;
  }
  public static boolean checarMeio(Veiculo veiculo, String meio){
    return Arrays.asList(veiculo.getMeios()).contains(meio);
  }
  public static boolean checarClima(Veiculo veiculo, String clima){
    return Arrays.asList(veiculo.getClimas()).contains(clima);
  }
  public static boolean checarPassageiros(Veiculo veiculo, Integer passageiros){
    return veiculo.passageirosMaximos >= passageiros;
  }
  public static void instanciar(ArrayList<Veiculo> veiculos){
    veiculos.add(new Carro((Math.random()*((250-180)+1))+180, (Math.random()*((100-80)+1))+180, 5));
    veiculos.add(new Bicicleta((Math.random()*((22-12)+1))+12, (Math.random()*((5-2)+1))+2, 1));
    veiculos.add(new Moto((Math.random()*((200-150)+1))+180, (Math.random()*((20-5)+1))+5, 2));
    veiculos.add(new Caminhao((Math.random()*((120-80)+1))+80, (Math.random()*((2300-1200)+1))+1000, 2));
    veiculos.add(new Jipe((Math.random()*((160-100)+1))+100, (Math.random()*((700-500)+1))+500, 5));
    veiculos.add(new Canoa((Math.random()*((20-10)+1))+10, (Math.random()*((200-100)+1))+100, 3));
    veiculos.add(new Lancha((Math.random()*((150-100)+1))+100, (Math.random()*((800-600)+1))+600, 6));
    veiculos.add(new TanqueAnfibio((Math.random()*((80-40)+1))+40, (Math.random()*((1300-900)+1))+900, 2));
    return;
  }
  public static boolean validarCarga(Double carga){
    return carga>=0;
  }
  public static boolean validarTempo(Double tempo){
    return tempo > 0;
  }
  public static boolean validarDistancia(double distancia){
    return distancia > 0;
  }
  public static boolean validarPassageiros(double passageiros){
    return passageiros > 0;
  }
  public static boolean validarClima(String clima){
    String[] climas = {"sol", "chuva"};
    return Arrays.asList(climas).contains(clima);
  }
  public static boolean validarMeio(String meio){
    String[] meios = {"rodovia", "estrada de terra", "rio", "lago", "mar"};
    return Arrays.asList(meios).contains(meio);
  }
}
