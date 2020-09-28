//package br.ufrj.dcc.comp2.ple.joaoPedro.lista2.trab1;

public class Carro extends Veiculo implements InteriorImpermeavel{
//(Math.random()*((200-300)+1))+200;
    //(Math.random()*((180-220)+1))+180;
    public Carro(double velocidadeMaxima , double cargaMaxima, int passageirosMaximos){
        super(velocidadeMaxima, cargaMaxima, passageirosMaximos);
    }
    @Override
    protected String[] getClimas(){
      return this.climas;
    }
}
