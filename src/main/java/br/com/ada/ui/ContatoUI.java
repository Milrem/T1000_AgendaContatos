package br.com.ada.ui;

import br.com.ada.enums.TipoContato;
import br.com.ada.model.Contato;
import br.com.ada.util.ConsoleUIHelper;

public class ContatoUI extends EditItemUI<Contato> {
    public ContatoUI(String titulo, Contato item, EditItemCallback<Contato> editItemCallback) {
        super(titulo, item, editItemCallback);
    }

    @Override
    public int drawItemDetails() {
        ConsoleUIHelper.drawWithRightPadding("Tipo: " + item.getTipoContato().name(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Nome: " + item.getNome(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Sobrenome: " + item.getSobrenome(), colunas, ' ');
        return 3;
    }

    @Override
    public String[] fillFieldsNames() {
        return new String[]{"Tipo","Nome","Sobrenome"};
    }

    @Override
    public void fillField(Contato item, int option) {
        switch (option) {
            case 0:
                break;
            case 1:
                String nome = ConsoleUIHelper.askSimpleInput("Informe o nome");
                item.setNome(nome);
                break;
            case 2:
                String sobrenome = ConsoleUIHelper.askSimpleInput("Informe o sobrenome");
                item.setSobrenome(sobrenome);
                break;
        }
    }

    @Override
    protected void newItem() {
        Contato newContato = new Contato();
        newContato.setTipoContato(TipoContato.PESSOAL);
        ContatoUI ui = new ContatoUI(titulo + " :: Novo", newContato, editItemCallback);
        ui.show();
    }
}
