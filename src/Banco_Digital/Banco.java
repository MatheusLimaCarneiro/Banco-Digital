package Banco_Digital;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nome;
    private static List<Conta> contaList;

    public Banco(String nome) {
        this.nome = nome;
        this.contaList = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static List<Conta> getContaList() {
        return contaList;
    }

    public static void setContaList(List<Conta> contaList) {
        Banco.contaList = contaList;
    }
}
