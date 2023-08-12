package br.com.ada;

import br.com.ada.enums.TipoContato;
import br.com.ada.enums.TipoTelefone;
import br.com.ada.model.Contato;
import br.com.ada.model.Telefone;
import br.com.ada.ui.BasicUI;
import br.com.ada.ui.EditItemCallback;
import br.com.ada.ui.TelefoneUI;

public class ExemploUI {
    public static void main(String[] args) {
        Contato contato = new Contato();
        contato.setTipoContato(TipoContato.PESSOAL);
        contato.setNome("Teste");
        contato.setSobrenome("Contato");
        Telefone t1 = new Telefone();
        t1.setTipoTelefone(TipoTelefone.CELULAR);
        BasicUI teste = new TelefoneUI(contato.getNomeCompleto(), t1, new EditItemCallback<Telefone>() {
            @Override
            public void remove(Telefone item) {
//                contato.removeTelefone(item);
                System.out.println(item.getContato());
            }

            @Override
            public void add(Telefone item) {
//                contato.addTelefone(item);
                System.out.println(item.getContato());
            }

            @Override
            public boolean isNew(Telefone item) {
                if (item.equals(t1)) {
                    return false;
                } else {
                    return true;
                }
            }
        });
        teste.show();
    }
}