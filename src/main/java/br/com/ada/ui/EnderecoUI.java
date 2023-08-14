package br.com.ada.ui;

import br.com.ada.enums.TipoEndereco;
import br.com.ada.model.Endereco;
import br.com.ada.util.ConsoleUIHelper;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EnderecoUI extends EditItemUI<Endereco> {
    public EnderecoUI(String titulo, Endereco item, EditItemCallback<Endereco> editItemCallback) {
        super(titulo, item, editItemCallback);
    }

    @Override
    public int drawItemDetails() {
        int linhas = 0;
        ConsoleUIHelper.drawWithRightPadding("Tipo: " + item.getTipoEndereco().name(), colunas, ' ');
        linhas++;
        ConsoleUIHelper.drawWithRightPadding("Pais: " + item.getPais(), colunas, ' ');
        linhas++;
        ConsoleUIHelper.drawWithRightPadding("Logradouro: " + item.getLogradouro(), colunas, ' ');
        linhas++;
        ConsoleUIHelper.drawWithRightPadding("Bairro: " + item.getBairro(), colunas, ' ');
        linhas++;
        ConsoleUIHelper.drawWithRightPadding("Cidade: " + item.getCidade(), colunas, ' ');
        linhas++;
        ConsoleUIHelper.drawWithRightPadding("Estado: " + item.getEstado(), colunas, ' ');
        if (item.getComplemento().length() > 120) {
            ConsoleUIHelper.drawWithRightPadding("Complemento: " + item.getComplemento().substring(0, 120), colunas, ' ');
            ConsoleUIHelper.drawWithRightPadding("    " + item.getComplemento().substring(121), colunas, ' ');
            linhas += 2;
        } else {
            ConsoleUIHelper.drawWithRightPadding("Complemento: " + item.getComplemento(), colunas, ' ');
            linhas++;
        }
        return linhas;
    }

    @Override
    public String[] fillFieldsNames() {
        return new String[]{"Tipo","Pais","Logradouro","Bairro","Cidade","Estado","Complemento"};
    }

    @Override
    public void fillField(Endereco item, int option) {
        switch (option) {
            case 0:
                fillTipo();
                break;
            case 1:
                fillPais();
                break;
            case 2:
                fillLogradouro();
                break;
            case 3:
                fillBairro();
                break;
            case 4:
                fillCidade();
                break;
            case 5:
                fillEstado();
                break;
            case 6:
                fillComplemento();
                break;
        }
    }

    @Override
    protected void newItem() {
        Endereco newItem = new Endereco();
        newItem.setTipoEndereco(TipoEndereco.RESIDENCIAL);
        EnderecoUI newItemUI = new EnderecoUI(titulo, newItem, editItemCallback);
        newItemUI.show();
    }

    private void fillTipo() {
        String[] tiposString = Arrays.stream(TipoEndereco.values()).map(Enum::name).collect(Collectors.toList()).toArray(new String[]{});
        int tipo = ConsoleUIHelper.askChooseOption("Informe o Tipo", tiposString);
        item.setTipoEndereco(TipoEndereco.values()[tipo]);
    }

    private void fillPais() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Pais");
        item.setPais(value);
    }

    private void fillLogradouro() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Logradouro");
        item.setLogradouro(value);
    }

    private void fillBairro() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Bairro");
        item.setBairro(value);
    }

    private void fillCidade() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Cidade");
        item.setCidade(value);
    }

    private void fillEstado() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Estado");
        item.setEstado(value);
    }

    private void fillComplemento() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Complemento");
        item.setComplemento(value);
    }
}
