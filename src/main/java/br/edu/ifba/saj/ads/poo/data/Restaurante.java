package br.edu.ifba.saj.ads.poo.data;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.saj.ads.poo.model.ItemCardapio;
import br.edu.ifba.saj.ads.poo.model.Pedido;

public class Restaurante {

    private String nome;
    private List<ItemCardapio> cardapio;
    private List<Pedido> pedidos;

    public Restaurante(String nome) {
        this.nome = nome;
        this.cardapio = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public void adicionarItemCardapio(ItemCardapio item) {
        cardapio.add(item);
    }

    public void registrarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<ItemCardapio> getCardapio() {
        return cardapio;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public String getNome() {
        return nome;
    }
}
