package br.com.ada;

import br.com.ada.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class Agenda implements br.com.ada.ui.PagedList {
    private List<Contato> contatos;

    public Agenda() {
        contatos = new ArrayList<>();
    }

    @Override
    public List<Contato> listarContatos(int pagina, int tamanhoPagina) {
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
        contatos.add(contato);
    }

    void removerContato(Contato contato) {
        contatos.remove(contato);
    }

    void duplicarContato(Contato contato) {

    }

    List<Contato> pesquisarContato(String nome) {
        List<Contato> encontrados = new ArrayList<>();
        for (Contato contato : contatos) {
            if (contato.getNomeCompleto().toLowerCase().contains(nome.toLowerCase())) {
                encontrados.add(contato);
            }
        }
        return encontrados;
    }
}
