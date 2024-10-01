package Banco_Digital;

public interface IConta {
    public void sacar(double valor);
    public void depositar( double valor);
    public void trasnferir(double valor, Conta contaDestino);
}
