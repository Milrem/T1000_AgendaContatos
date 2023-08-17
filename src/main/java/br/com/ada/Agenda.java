package br.com.ada;

import br.com.ada.model.Contato;
import br.com.ada.service.AgendaService;
import br.com.ada.ui.PagedList;

import java.util.ArrayList;
import java.util.List;

public class Agenda implements PagedList<Contato> {
    private AgendaService agendaService;

    public Agenda() {
        agendaService = AgendaService.getInstance();
    }

    @Override
    public List<Contato> listar(int pagina, int tamanhoPagina) {
        List<Contato> contatos = agendaService.getContatoList();
        List<Contato> listagem = new ArrayList<>();
        int primeiroRegistro = tamanhoPagina * (pagina-1);
        if (primeiroRegistro > contatos.size() -1) {
            return listagem;
        }
        int ultimoRegistro = primeiroRegistro + tamanhoPagina;
        if (contatos.size() < ultimoRegistro) {
            ultimoRegistro = contatos.size();
        }
        for (int i = primeiroRegistro; i < ultimoRegistro; i++) {
            Contato contato = contatos.get(i);
            listagem.add(contato);
        }
        return listagem;
    }

    void adicionarContato(Contato contato) {
        agendaService.add(contato);
    }

    void removerContato(Contato contato) {
        agendaService.del(contato);
    }
}
