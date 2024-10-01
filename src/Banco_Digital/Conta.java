package Banco_Digital;

public abstract class Conta implements IConta {
    private static final int AGENCIA_PADRAO =1;
    private static final int SEQUENCIAL =1;
    protected int agencia;
    protected int num;
    protected double saldo;
    protected Cliente cliente;


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Conta(Cliente cliente) {
        this.agencia= AGENCIA_PADRAO;
        this.num = SEQUENCIAL;
        this.cliente = cliente;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    @Override
    public void sacar(double valor){
        try {
            if (valor > 0 && this.saldo < 0) {
                throw new SaldoInsuficienteException();

            }
            saldo -= valor;

        } catch (SaldoInsuficienteException e) {
            System.out.println("Saldo insuficiente para realizar o saque.");;
        }
    }

    @Override
    public void depositar( double valor){
        try {
            if (valor < 0 && this.saldo < 0) {
                throw new SaldoInsuficienteException();
            }
            saldo += valor;

        } catch (SaldoInsuficienteException e) {
            System.out.println("Valor insuficiente para depositar.");;
        }
    }

    @Override

    public void trasnferir(double valor, Conta contaDestino){

        this.sacar(valor);

        contaDestino.depositar(valor);

    }
}
