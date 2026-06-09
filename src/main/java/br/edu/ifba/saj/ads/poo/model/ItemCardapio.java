package br.edu.ifba.saj.ads.poo.model;

public class ItemCardapio {

    private static int quantidadeItens = 0;

    private int id;
    private String nome;
    private String descricao;
    private double preco;

    public ItemCardapio(String nome, String descricao, double preco) {
        this.id = ++quantidadeItens;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return this.nome + " - R$ " + this.preco;
    }
}
