package br.com.ada;

import br.com.ada.enums.TipoContato;
import br.com.ada.model.Contato;
import br.com.ada.ui.BasicUI;
import br.com.ada.ui.PagedListUI;

import java.util.List;

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

        BasicUI ui = new PagedListUI("Agenda de Contatos");
        ui.show();

//        System.out.println("Pagina 1");
//        List<Contato> pagina1 = agenda.listarContatos(1, 4);
//        for (int i = 0; i < pagina1.size(); i++) {
//            System.out.println(pagina1.get(i).getNomeCompleto());
//        }
//        System.out.println("Pagina 2");
//        List<Contato> pagina2 = agenda.listarContatos(2, 4);
//        for (int i = 0; i < pagina2.size(); i++) {
//            System.out.println(pagina2.get(i).getNomeCompleto());
//        }
//        System.out.println("Pagina 3");
//        List<Contato> pagina3 = agenda.listarContatos(3, 4);
//        for (int i = 0; i < pagina3.size(); i++) {
//            System.out.println(pagina3.get(i).getNomeCompleto());
//        }
    }
}