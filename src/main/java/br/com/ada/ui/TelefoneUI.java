package br.com.ada.ui;

import br.com.ada.enums.TipoTelefone;
import br.com.ada.model.Telefone;
import br.com.ada.util.ConsoleUIHelper;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TelefoneUI extends EditItemUI<Telefone> {
    public TelefoneUI(String titulo, Telefone item, EditItemCallback<Telefone> editItemCallback) {
        super(titulo, item, editItemCallback);
    }

    @Override
    public int drawItemDetails() {
        ConsoleUIHelper.drawWithRightPadding("Tipo: " + item.getTipoTelefone().name(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("DDI: " + item.getDdi(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("DDD: " + item.getDdd(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Numero: " + item.getNumero(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Ramal: " + item.getRamal(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Contato: " + item.getContato(), colunas, ' ');
        return 6;
    }

    @Override
    public String[] fillFieldsNames() {
        return new String[]{"Tipo","DDI","DDD","Numero","Ramal","Contato"};
    }

    @Override
    public void fillField(Telefone item, int option) {
        switch (option) {
            case 0:
                fillTipo();
                break;
            case 1:
                fillDdi();
                break;
            case 2:
                fillDdd();
                break;
            case 3:
                fillNumero();
                break;
            case 4:
                fillRamal();
                break;
            case 5:
                fillContato();
                break;
        }
    }

    @Override
    protected void newItem() {
        Telefone newPhone = new Telefone();
        newPhone.setTipoTelefone(TipoTelefone.CELULAR);
        TelefoneUI newItemUI = new TelefoneUI(titulo, newPhone, editItemCallback);
        newItemUI.show();
    }

    private void fillTipo() {
        String[] tiposString = Arrays.stream(TipoTelefone.values()).map(Enum::name).collect(Collectors.toList()).toArray(new String[]{});
        int tipo = ConsoleUIHelper.askChooseOption("Informe o Tipo", tiposString);
        item.setTipoTelefone(TipoTelefone.values()[tipo]);
    }

    private void fillDdi() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o DDI");
        item.setDdi(value);
    }

    private void fillDdd() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o DDD");
        item.setDdd(value);
    }

    private void fillNumero() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Numero");
        item.setNumero(value);
    }

    private void fillRamal() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Ramal");
        item.setRamal(value);
    }

    private void fillContato() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Contato");
        item.setContato(value);
    }
}
