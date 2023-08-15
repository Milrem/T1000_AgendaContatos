package br.com.ada;

import br.com.ada.enums.TipoContato;
import br.com.ada.model.Contato;
import br.com.ada.ui.AgendaUI;
import br.com.ada.ui.BasicUI;
import br.com.ada.ui.PagedListUI;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        for (int i = 0; i < 10; i++) {
            Contato contato = new Contato();
            contato.setTipoContato(TipoContato.PESSOAL);
            contato.setNome("Nome "+ i);
            contato.setSobrenome("Teste");
            agenda.adicionarContato(contato);
        }

        BasicUI ui = new AgendaUI(agenda);
        ui.show();
    }
}