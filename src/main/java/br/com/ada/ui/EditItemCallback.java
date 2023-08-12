package br.com.ada.ui;

public interface EditItemCallback<T> {
    void remove(T item);
    void add(T item);
    boolean isNew(T item);
}
