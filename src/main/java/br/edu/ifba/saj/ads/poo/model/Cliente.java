package br.edu.ifba.saj.ads.poo.model;

public class Cliente {

    private static int quantidadeClientes = 0;

    private int id;
    private String nome;

    public Cliente(String nome) {
        this.id = ++quantidadeClientes;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
