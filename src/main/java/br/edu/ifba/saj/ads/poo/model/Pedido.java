package br.edu.ifba.saj.ads.poo.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private static int quantidadePedidos = 0;

    private int id;
    private Cliente cliente;
    private List<ItemCardapio> itens;

    public Pedido(Cliente cliente) {
        this.id = ++quantidadePedidos;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;

        for (ItemCardapio item : itens) {
            total += item.getPreco();
        }

        return total;
    }

    public void listarItens() {
        for (ItemCardapio item : itens) {
            System.out.println(item.getNome());
        }
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemCardapio> getItens() {
        return itens;
    }
}
