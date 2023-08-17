package br.com.ada.service;

import br.com.ada.enums.TipoContato;
import br.com.ada.enums.TipoEndereco;
import br.com.ada.enums.TipoTelefone;
import br.com.ada.model.Contato;
import br.com.ada.model.Endereco;
import br.com.ada.model.Telefone;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AgendaService {
    private static AgendaService instance;

    public synchronized static AgendaService getInstance() {
        if (instance == null) {
            instance = new AgendaService();
        }
        return instance;
    }

    private List<Contato> contatoList;

    private AgendaService() {
        contatoList = new LinkedList<>();
    }

    public List<Contato> getContatoList() {
        return Collections.unmodifiableList(contatoList);
    }

    public void add(Contato contato) {
        contatoList.add(contato);
    }

    public void del(Contato contato) {
        contatoList.remove(contato);
    }

    public void importar(String path) {
        Path importPath = Paths.get(path).toAbsolutePath();
        try {
            Files.lines(importPath, StandardCharsets.UTF_8).forEach(line -> {
                String[] tokens = line.split(Character.toString(247));
                if (tokens[0].equals("C")) {
                    contatoList.add(textToContato(tokens));
                } else if (tokens[0].equals("T")) {
                    if (!contatoList.isEmpty()) {
                        Contato contato = contatoList.get(contatoList.size()-1);
                        Telefone telefone = textToTelefone(tokens);
                        contato.add(telefone);
                    }
                } else if (tokens[0].equals("E")) {
                    if (!contatoList.isEmpty()) {
                        Contato contato = contatoList.get(contatoList.size()-1);
                        Endereco endereco = textToEndereco(tokens);
                        contato.add(endereco);
                    }
                }
            });
        } catch (IOException ignored) {
            System.err.println(ignored.getMessage());
        }
    }

    public boolean exportar(String path, boolean sobrescrever) {
        Path exportPath = Paths.get(path).toAbsolutePath();
        try {
            if (Files.exists(exportPath)) {
                if (!sobrescrever) {
                    return false;
                } else {
                    Path copyPath = exportPath.getParent().resolve(exportPath.toFile().getName().concat(".bkp"));
                    Files.copy(exportPath, copyPath, StandardCopyOption.REPLACE_EXISTING);
                    Files.deleteIfExists(exportPath);
                }
            }
            Files.createFile(exportPath);
            for (Contato contato : contatoList) {
                Files.writeString(exportPath, contatoToText(contato), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                for (Telefone telefone : contato.getTelefones()) {
                    Files.writeString(exportPath, telefoneToText(telefone), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                }
                for (Endereco endereco : contato.getEnderecos()) {
                    Files.writeString(exportPath, enderecoToText(endereco), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                }
            }
            return true;
        } catch (IOException ignored) {
            System.err.println(ignored.getMessage());
        }
        return false;
    }

    private String contatoToText(Contato contato) {
        return String.join(Character.toString(247),
                "C",
                contato.getNome(),
                contato.getSobrenome(),
                contato.getTipoContato().name(),
                "\n"
        );
    }

    private Contato textToContato(String[] tokens) {
        if (tokens.length < 4) {
            return null;
        }
        TipoContato tipo;
        try {
            tipo = TipoContato.valueOf(tokens[3]);
        } catch (IllegalArgumentException e) {
            tipo = TipoContato.PESSOAL;
        }
        Contato contato = new Contato();
        contato.setTipoContato(tipo);
        contato.setNome(tokens[1]);
        contato.setSobrenome(tokens[2]);
        return contato;
    }

    private String telefoneToText(Telefone telefone) {
        return String.join(Character.toString(247),
                "T",
                telefone.getTipoTelefone().name(),
                telefone.getDdi(),
                telefone.getDdd(),
                telefone.getNumero(),
                telefone.getRamal(),
                telefone.getContato(),
                "\n"
        );
    }

    private Telefone textToTelefone(String[] tokens) {
        if (tokens.length < 7) {
            return null;
        }
        TipoTelefone tipo;
        try {
            tipo = TipoTelefone.valueOf(tokens[1]);
        } catch (IllegalArgumentException e) {
            tipo = TipoTelefone.CELULAR;
        }
        Telefone telefone = new Telefone();
        telefone.setTipoTelefone(tipo);
        telefone.setDdi(tokens[2]);
        telefone.setDdd(tokens[3]);
        telefone.setNumero(tokens[4]);
        telefone.setRamal(tokens[5]);
        telefone.setContato(tokens[6]);
        return telefone;
    }

    private String enderecoToText(Endereco endereco) {
        return String.join(Character.toString(247),
                "E",
                endereco.getTipoEndereco().name(),
                endereco.getPais(),
                endereco.getCep(),
                endereco.getLogradouro(),
                "0",//endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                "\n"
        );
    }

    private Endereco textToEndereco(String[] tokens) {
        if (tokens.length < 10) {
            return null;
        }
        TipoEndereco tipo;
        try {
            tipo = TipoEndereco.valueOf(tokens[1]);
        } catch (IllegalArgumentException e) {
            tipo = TipoEndereco.RESIDENCIAL;
        }
        Endereco endereco = new Endereco();
        endereco.setTipoEndereco(tipo);
        endereco.setPais(tokens[2]);
        endereco.setCep(tokens[3]);
        endereco.setLogradouro(tokens[4]);
//        endereco.setNumero(tokens[5]);
        endereco.setComplemento(tokens[6]);
        endereco.setBairro(tokens[7]);
        endereco.setCidade(tokens[8]);
        endereco.setEstado(tokens[9]);
        return endereco;
    }
}
