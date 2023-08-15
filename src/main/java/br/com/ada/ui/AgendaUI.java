package br.com.ada.ui;

import br.com.ada.model.Contato;

public class AgendaUI extends PagedListUI<Contato> {
    public AgendaUI(PagedList<Contato> pageSource) {
        super("Agenda de contatos", pageSource);
    }

    @Override
    protected void showItem(Contato item) {
        ContatoUI ui = new ContatoUI("", item, new EditItemCallback<Contato>() {
            @Override
            public void remove(Contato item) {

            }

            @Override
            public void add(Contato item) {

            }

            @Override
            public boolean isNew(Contato item) {
                return false;
            }
        });
        ui.show();
    }

    @Override
    protected void addItem() {

    }
}
