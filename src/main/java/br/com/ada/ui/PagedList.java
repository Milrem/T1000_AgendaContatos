package br.com.ada.ui;

import br.com.ada.model.Contato;

import java.util.List;

public interface PagedList<T> {
    List<T> listar(int pagina, int tamanhoPagina);
}
