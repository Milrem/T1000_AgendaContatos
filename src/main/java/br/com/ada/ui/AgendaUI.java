package br.com.ada.ui;

import br.com.ada.enums.TipoContato;
import br.com.ada.model.Contato;
import br.com.ada.service.AgendaService;

public class AgendaUI extends PagedListUI<Contato> {
    public AgendaUI(PagedList<Contato> pageSource) {
        super("Agenda de contatos", pageSource);
    }

    @Override
    protected void showItem(Contato item) {
        ContatoUI ui = new ContatoUI("Contato", item, new EditItemCallback<Contato>() {
            @Override
            public void remove(Contato ref) {
                AgendaService.getInstance().del(ref);
            }

            @Override
            public void add(Contato item) {
                AgendaService.getInstance().add(item);
            }

            @Override
            public boolean isNew(Contato ref) {
                if (ref.equals(item)) {
                    return false;
                } else {
                    return true;
                }
            }
        });
        ui.show();
    }

    @Override
    protected void addItem() {
        Contato newContato = new Contato();
        newContato.setTipoContato(TipoContato.PESSOAL);
        AgendaService.getInstance().add(newContato);
        showItem(newContato);
    }
}
