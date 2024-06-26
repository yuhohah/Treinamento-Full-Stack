package org.example.infra.repository.pessoa;

import org.example.Entidades.Pessoa;

import java.util.List;

public interface PessoaRepository extends Repository<Pessoa> {

    public List<Pessoa> getList();

    public Pessoa findById(int id);

    void merge(Pessoa pessoa);

    void insert(Pessoa pessoa);

    void remove(Pessoa pessoa);

}
