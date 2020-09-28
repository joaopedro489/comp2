public class Zumbi extends Monstro{
  @Override
  public int atacar(){
    int power = (int)(Math.random() * ((getAtaque() - (getAtaque()/2)) + 1)) + (getAtaque()/2);
    return power;
  }
  public Zumbi(int atk, int hp, String nomeDeAtaque){
    super(atk, hp, nomeDeAtaque);
  }

}
