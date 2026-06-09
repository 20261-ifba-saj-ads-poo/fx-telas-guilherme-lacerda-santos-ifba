package br.edu.ifba.saj.ads.poo;

import br.edu.ifba.saj.ads.poo.data.Restaurante;
import br.edu.ifba.saj.ads.poo.model.Cliente;
import br.edu.ifba.saj.ads.poo.model.ItemCardapio;
import br.edu.ifba.saj.ads.poo.model.Pedido;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RestauranteController {

    @FXML private TextField txtCliente;
    @FXML private TextField txtNomeItem, txtDescricao, txtPreco;
    @FXML private ComboBox<ItemCardapio> cbCardapio;
    @FXML private ListView<ItemCardapio> listaItensPedido;
    @FXML private Label lblTotal;

    @FXML private TableView<Pedido> tabelaPedidos;
    @FXML private TableColumn<Pedido, String> colCliente;
    @FXML private TableColumn<Pedido, String> colQuantidade;
    @FXML private TableColumn<Pedido, String> colItens;
    @FXML private TableColumn<Pedido, String> colTotal;

    private Restaurante restaurante;
    private Pedido pedidoAtual;
    private ObservableList<ItemCardapio> itensDoCardapio;
    private ObservableList<ItemCardapio> itensNoPedido;
    private ObservableList<Pedido> listaPedidosRegistrados;

    @FXML
    public void initialize() {
        restaurante = new Restaurante("Restaurante");
        itensDoCardapio = FXCollections.observableArrayList();
        itensNoPedido = FXCollections.observableArrayList();
        listaPedidosRegistrados = FXCollections.observableArrayList();

        cbCardapio.setItems(itensDoCardapio);
        listaItensPedido.setItems(itensNoPedido);
        tabelaPedidos.setItems(listaPedidosRegistrados);

        colCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getNome()));
        
        colQuantidade.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getItens().size())));

        colItens.setCellValueFactory(cellData -> {
            String textoItens = "";
            for (ItemCardapio item : cellData.getValue().getItens()) {
                if (item.getDescricao() == null || item.getDescricao().trim().isEmpty()) {
                    textoItens += item.getNome() + ", ";
                } else {
                    textoItens += item.getNome() + " (" + item.getDescricao() + "), ";
                }
            }
            // Remove a última vírgula e espaço
            if (textoItens.length() > 0) {
                textoItens = textoItens.substring(0, textoItens.length() - 2);
            }
            return new SimpleStringProperty(textoItens);
        });

        colTotal.setCellValueFactory(cellData -> {
            double total = cellData.getValue().calcularTotal();
            return new SimpleStringProperty(String.format("R$ %.2f", total));
        });
    }

    @FXML
    private void cadastrarNoCardapio() {
        String nome = txtNomeItem.getText();
        String desc = txtDescricao.getText();
        double preco = Double.parseDouble(txtPreco.getText());

        ItemCardapio novoItem = new ItemCardapio(nome, desc, preco);
        restaurante.adicionarItemCardapio(novoItem);
        itensDoCardapio.add(novoItem);

        txtNomeItem.clear();
        txtDescricao.clear();
        txtPreco.clear();
    }

    @FXML
    private void criarPedido() {
        Cliente cliente = new Cliente(txtCliente.getText());
        pedidoAtual = new Pedido(cliente);
        itensNoPedido.clear();
        lblTotal.setText("Total: R$ 0.0");
    }

    @FXML
    private void adicionarAoPedido() {
        ItemCardapio selecionado = cbCardapio.getSelectionModel().getSelectedItem();
        if (pedidoAtual != null && selecionado != null) {
            pedidoAtual.adicionarItem(selecionado);
            itensNoPedido.add(selecionado);
            lblTotal.setText("Total: R$ " + pedidoAtual.calcularTotal());
        }
    }

    @FXML
    private void finalizarPedido() {
        if (pedidoAtual != null && !pedidoAtual.getItens().isEmpty()) {
            restaurante.registrarPedido(pedidoAtual);
            listaPedidosRegistrados.add(pedidoAtual);
            
            pedidoAtual = null;
            itensNoPedido.clear();
            txtCliente.clear();
            lblTotal.setText("Total: R$ 0.0");
        }
    }
}
