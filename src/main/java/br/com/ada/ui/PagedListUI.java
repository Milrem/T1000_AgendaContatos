package br.com.ada.ui;

import br.com.ada.model.Contato;
import br.com.ada.util.ConsoleUIHelper;

import java.util.List;

public class PagedListUI extends BasicUI {

    protected final int PAGE_SIZE;
    protected int curPage;

    protected PagedList pageSource;

    public PagedListUI(String titulo, PagedList pageSource) {
        this(DEFAULT_COLUMNS, DEFAULT_ROWS, titulo, pageSource);
    }

    public PagedListUI(int colunas, int linhas, String titulo, PagedList pageSource) {
        super(colunas, linhas, titulo);
        PAGE_SIZE = linhas - 4;
        curPage = 1;
        this.pageSource = pageSource;
    }

    @Override
    public int drawContent() {
        List<Contato> dataList = pageSource.listarContatos(curPage, PAGE_SIZE);
        dataList.forEach(System.out::println);
//        for (int i = 0; i < dataList.size(); i++) {
//            System.out.println(dataList.get(i));
//        }
        return dataList.size();
    }

    @Override
    public int menuLines() {
        return 6;
    }

    @Override
    public boolean drawMenu() {
        int option = ConsoleUIHelper.askChooseOption(
                "Escolhar uma opção",
                "Página Anterior",
                "Página Seguinte",
                "Novo item",
                "Ver detalhes",
                "Sair");
        switch (option) {
            case 0: {
                previousPage();
                break;
            }
            case 1: {
                nextPage();
                break;
            }
            case 2: {
                addItem();
                break;
            }
            case 3: {
                seeItem();
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }

    private void seeItem() {
    }

    private void addItem() {

    }

    private void nextPage() {

    }

    private void previousPage() {

    }
}
