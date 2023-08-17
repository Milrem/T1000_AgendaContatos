package br.com.ada.model;

import br.com.ada.enums.TipoContato;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Contato {
    private String nome;
    private String sobrenome;
    private List<Telefone> telefones;
    private List<Endereco> enderecos;

    private TipoContato tipoContato;

    public Contato() {
        this.telefones = new ArrayList<>();
        this.enderecos = new ArrayList<>();
        this.tipoContato = TipoContato.PESSOAL;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }

    @Override
    public String toString() {
        return getNomeCompleto() + "[" + tipoContato + "]";
    }
    
    public boolean add(Telefone telefone) {
        return telefones.add(telefone);
    }
    
    public boolean add(Endereco endereco) {
        return enderecos.add(endereco);
    }

    public List<Telefone> getTelefones() {
        return Collections.unmodifiableList(telefones);
    }

    public List<Endereco> getEnderecos() {
        return Collections.unmodifiableList(enderecos);
    }
}
