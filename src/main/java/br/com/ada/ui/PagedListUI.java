package br.com.ada.ui;

import br.com.ada.util.ConsoleUIHelper;

import java.util.List;

public abstract class PagedListUI<T> extends BasicUI {

    protected final int PAGE_SIZE;
    protected final PagedList<T> pageSource;
    protected int curPage;
    private List<T> dataList;

    public PagedListUI(String titulo, PagedList<T> pageSource) {
        this(DEFAULT_COLUMNS, DEFAULT_ROWS, titulo, pageSource);
    }

    public PagedListUI(int colunas, int linhas, String titulo, PagedList<T> pageSource) {
        super(colunas, linhas, titulo);
        PAGE_SIZE = linhas - 4;
        curPage = 1;
        this.pageSource = pageSource;
    }

    @Override
    public int drawContent() {
        dataList = pageSource.listar(curPage, PAGE_SIZE);
        if (dataList.isEmpty() && curPage > 0) {
            previousPage();
            dataList = pageSource.listar(curPage, PAGE_SIZE);
        }
        for (int i = 0; i < dataList.size(); i++) {
            String text = dataList.get(i).toString();
            ConsoleUIHelper.drawWithRightPadding(i + " -> " + text, colunas, ' ');
        }
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

    protected void seeItem() {
        int op = ConsoleUIHelper.askNumber("Informe o item a exibir").intValue();
        if (op >= 0 && op < dataList.size()) {
            showItem(dataList.get(op));
        } else {
            ConsoleUIHelper.showMessageAndWait("Item inválido, por favor informe um item válido!", 10);
            ConsoleUIHelper.clearScreen();
        }
    }

    protected abstract void showItem(T item);

    protected abstract void addItem();

    private void nextPage() {
        curPage++;
    }

    private void previousPage() {
        if (curPage > 0) {
            curPage--;
        }
    }
}
