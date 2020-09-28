//package br.ufrj.dcc.comp2.ple.joaoPedro.lista2.trab1;

public abstract class Veiculo {
    protected double velocidadeMaxima;
    protected double cargaMaxima;
    protected int passageirosMaximos;

    protected void printVeiculo(){
      System.out.println( this.getClass().getSimpleName() + ": velocidade máxima = " + String.format("%.2f", this.velocidadeMaxima) + "km/h, carga máxima= " +
      String.format("%.2f", this.cargaMaxima) +
      "kg, maximo passageiros = " + (this.passageirosMaximos - 1 ));
    }
    protected String[] getMeios(){
      String[] meios = {"rodovia"};
      return meios;
    }
    protected String[] getClimas(){
      String[] climas = {"sol"};
      return climas;
    }
    public Veiculo(double velocidadeMaxima , double cargaMaxima, int passageirosMaximos){
      this.velocidadeMaxima = velocidadeMaxima;
      this.cargaMaxima = cargaMaxima;
      this.passageirosMaximos = passageirosMaximos;
    }
}
