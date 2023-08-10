package br.com.ada.ui;

import br.com.ada.model.Contato;

import java.util.List;

public interface PagedList {
    List<Contato> listarContatos(int pagina, int tamanhoPagina);
}
