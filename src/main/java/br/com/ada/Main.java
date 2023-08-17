package br.com.ada;

import br.com.ada.enums.TipoContato;
import br.com.ada.model.Contato;
import br.com.ada.service.AgendaService;
import br.com.ada.ui.AgendaUI;
import br.com.ada.ui.BasicUI;

public class Main {
    public static void main(String[] args) {
        AgendaService agendaService = AgendaService.getInstance();
//        for (int i = 0; i < 10; i++) {
//            Contato contato = new Contato();
//            contato.setTipoContato(TipoContato.PESSOAL);
//            contato.setNome("Nome "+ i);
//            contato.setSobrenome("Teste");
//            agendaService.add(contato);
//        }
//        agendaService.exportar("contatos.dat", true);
        agendaService.importar("contatos.dat");

        BasicUI ui = new AgendaUI(new Agenda());
        ui.show();

        agendaService.exportar("contatos.dat", true);
    }
}