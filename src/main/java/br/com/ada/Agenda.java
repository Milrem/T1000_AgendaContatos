package br.com.ada;

import br.com.ada.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos;

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
