package br.com.ada.ui;

import br.com.ada.util.ConsoleUIHelper;

public abstract class EditItemUI<T> extends BasicUI {
    protected final T item;
    protected final EditItemCallback<T> editItemCallback;

    public EditItemUI(String titulo, T item, EditItemCallback<T> editItemCallback) {
        super(titulo);
        this.item = item;
        this.editItemCallback = editItemCallback;
    }

    @Override
    public int drawContent() {
        return drawItemDetails();
    }

    @Override
    public int menuLines() {
        return fillFieldsNames().length + (editItemCallback.isNew(item) ? 1 : 3);
    }

    public void exitAndSave() {
        if (editItemCallback.isNew(item)) {
            editItemCallback.add(item);
        }
    }

    public abstract int drawItemDetails();

    public abstract String[] fillFieldsNames();

    public abstract void fillField(T item, int option);

    @Override
    public boolean drawMenu() {
        String[] options;
        if (editItemCallback.isNew(item)) {
            options = new String[fillFieldsNames().length + 1];
            options[0] = "Sair";
        } else {
            options = new String[fillFieldsNames().length + 3];
            options[0] = "Novo";
            options[fillFieldsNames().length + 1] = "Apagar";
            options[fillFieldsNames().length + 2] = "Sair";
        }
        for (int i = 0; i < fillFieldsNames().length; i++) {
            options[i+1] = fillFieldsNames()[i];
        }
        int option = ConsoleUIHelper.askChooseOption("Escolha uma opção", options);
        boolean keepShowing = true;
        if (option == 0) {
            if (editItemCallback.isNew(item)) {
                exitAndSave();
                keepShowing = false;
            } else {
                newItem();
            }
        } else if (option == fillFieldsNames().length + 1) {
            boolean confirm = ConsoleUIHelper.askConfirm("Confirma a exclusão do endereço " + item.toString() + "?", "Sim", "Não");
            if (confirm) {
                keepShowing = false;
                editItemCallback.remove(item);
                ConsoleUIHelper.showMessageAndWait("Item apagado!", 5);
            }
        } else if (option == fillFieldsNames().length + 2) {
            exitAndSave();
            keepShowing = false;
        } else {
            fillField(item, option-1);
        }
        return keepShowing;
    }

    protected abstract void newItem();
}
