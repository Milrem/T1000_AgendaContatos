package br.com.ada.ui;

import br.com.ada.enums.TipoTelefone;
import br.com.ada.model.Telefone;
import br.com.ada.util.ConsoleUIHelper;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TelefoneUI extends BasicUI {
    private final Telefone telefone;
    private final EditItemCallback<Telefone> editItemCallback;

    public TelefoneUI(String titulo, Telefone telefone, EditItemCallback<Telefone> editItemCallback) {
        super(titulo);
        this.telefone = telefone;
        this.editItemCallback = editItemCallback;
    }

    @Override
    public int drawContent() {
        ConsoleUIHelper.drawWithRightPadding("Tipo: " + telefone.getTipoTelefone().name(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("DDI: " + telefone.getDdi(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("DDD: " + telefone.getDdd(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Numero: " + telefone.getNumero(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Ramal: " + telefone.getRamal(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Contato: " + telefone.getContato(), colunas, ' ');
        return 6;
    }

    @Override
    public int menuLines() {
        return editItemCallback.isNew(telefone) ? 8 : 10;
    }

    public void exitAndSave() {
        if (editItemCallback.isNew(telefone)) {
            editItemCallback.add(telefone);
        }
    }

    @Override
    public boolean drawMenu() {
        String[] options;
        if (editItemCallback.isNew(telefone)) {
            options = new String[]{"Sair","Alterar Tipo","Alterar DDI","Alterar DDD","Alterar Numero","Alterar Ramal","Alterar Contato"};
        } else {
            options = new String[]{"Novo","Alterar Tipo","Alterar DDI","Alterar DDD","Alterar Numero","Alterar Ramal","Alterar Contato","Apagar","Sair"};
        }
        int option = ConsoleUIHelper.askChooseOption("Escolha uma opção", options);
        boolean keepShowing = true;
        switch (option) {
            case 0:
                if (editItemCallback.isNew(telefone)) {
                    exitAndSave();
                    keepShowing = false;
                } else {
                    newFone();
                }
                break;
            case 1:
                fillTipo();
                break;
            case 2:
                fillDdi();
                break;
            case 3:
                fillDdd();
                break;
            case 4:
                fillNumero();
                break;
            case 5:
                fillRamal();
                break;
            case 6:
                fillContato();
                break;
            case 7:
                boolean confirm = ConsoleUIHelper.askConfirm("Confirma a exclusão do telefone " + telefone.toString() + "?", "Sim", "Não");
                if (confirm) {
                    keepShowing = false;
                    editItemCallback.remove(telefone);
                    ConsoleUIHelper.showMessageAndWait("Telefone apagado!", 5);
                }
            default:
                exitAndSave();
                keepShowing = false;
        }
        return keepShowing;
    }

    private void newFone() {
        Telefone newPhone = new Telefone();
        newPhone.setTipoTelefone(TipoTelefone.CELULAR);
        TelefoneUI newItemUI = new TelefoneUI(titulo, newPhone, editItemCallback);
        newItemUI.show();
    }

    private void fillTipo() {
        String[] tiposString = Arrays.stream(TipoTelefone.values()).map(Enum::name).collect(Collectors.toList()).toArray(new String[]{});
        int tipo = ConsoleUIHelper.askChooseOption("Informe o Tipo", tiposString);
        telefone.setTipoTelefone(TipoTelefone.values()[tipo]);
    }

    private void fillDdi() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o DDI");
        telefone.setDdi(value);
    }

    private void fillDdd() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o DDD");
        telefone.setDdd(value);
    }

    private void fillNumero() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Numero");
        telefone.setNumero(value);
    }

    private void fillRamal() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Ramal");
        telefone.setRamal(value);
    }

    private void fillContato() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Contato");
        telefone.setContato(value);
    }
}
