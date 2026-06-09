package br.edu.ifba.saj.ads.poo.model;

import java.util.List;
import java.util.ArrayList;

public class Cardapio {

    private List<ItemCardapio> itens;

    public Cardapio() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public List<ItemCardapio> getItens() {
        return itens;
    }
}
