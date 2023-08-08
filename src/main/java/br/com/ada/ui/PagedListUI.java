package br.com.ada.ui;

import br.com.ada.util.ConsoleUIHelper;

public class PagedListUI extends BasicUI {
    public PagedListUI(String titulo) {
        super(titulo);
    }

    public PagedListUI(int colunas, int linhas, String titulo) {
        super(colunas, linhas, titulo);
    }

    @Override
    public int drawContent() {
        this.colunas = 10;
        return 0;
    }

    @Override
    public int menuLines() {
        return 0;
    }

    @Override
    public boolean drawMenu() {
        return ConsoleUIHelper.askConfirm("Sair do sistema?", "Não", "Sim");
    }
}
