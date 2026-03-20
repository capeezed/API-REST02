package com.facens.biblioteca_api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.facens.biblioteca_api.model.Livro;
import com.facens.biblioteca_api.repository.LivroRepository;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public List<Livro> listar() {
        return repository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Livro atualizar(Long id, Livro livroAtualizado) {
        Livro livro = buscarPorId(id);

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());

        return repository.save(livro);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


    public Livro emprestar(Long id) {
        Livro livro = buscarPorId(id);

        if (livro.isEmprestado()) {
            throw new RuntimeException("Livro já está emprestado");
        }

        livro.setEmprestado(true);
        livro.setDataEmprestimo(LocalDate.now());

        return repository.save(livro);
    }

    public Livro devolver(Long id) {
        Livro livro = buscarPorId(id);

        if (!livro.isEmprestado()) {
            throw new RuntimeException("Livro não está emprestado");
        }

        livro.setEmprestado(false);
        livro.setDataEmprestimo(null);

        return repository.save(livro);
    }
}