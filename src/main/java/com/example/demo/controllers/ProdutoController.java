package com.example.demo.controllers;

import com.example.demo.dtos.ProdutoDTO;
import com.example.demo.entities.Produto;
import com.example.demo.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    // Cria um novo produto
    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setDescricao(produtoDTO.descricao());
        produto.setCategoria(produtoDTO.categoria());
        produto.setQuantidade(produtoDTO.quantidade());
        produto.setPreco(produtoDTO.preco());
        produto.setObservacoes(produtoDTO.observacoes());

        Produto savedProduto = repository.save(produto);

        ProdutoDTO savedProdutoDTO = new ProdutoDTO(
                savedProduto.getDescricao(),
                savedProduto.getCategoria(),
                savedProduto.getQuantidade(),
                savedProduto.getPreco(),
                savedProduto.getObservacoes()
        );

        return ResponseEntity.status(201).body(savedProdutoDTO);  // Retorna o status de criacao concluida
    }

    // Cria vários produtos
    @PostMapping("/batch")
    public ResponseEntity<List<ProdutoDTO>> createBatch(@Valid @RequestBody List<ProdutoDTO> produtosDTO) {
        List<Produto> produtos = produtosDTO.stream()
                .map(dto -> {
                    Produto produto = new Produto();
                    produto.setDescricao(dto.descricao());
                    produto.setCategoria(dto.categoria());
                    produto.setQuantidade(dto.quantidade());
                    produto.setPreco(dto.preco());
                    produto.setObservacoes(dto.observacoes());
                    return produto;
                })
                .toList();

        List<Produto> savedProdutos = repository.saveAll(produtos);

        List<ProdutoDTO> savedProdutosDTO = savedProdutos.stream()
                .map(produto -> new ProdutoDTO(
                        produto.getDescricao(),
                        produto.getCategoria(),
                        produto.getQuantidade(),
                        produto.getPreco(),
                        produto.getObservacoes()
                ))
                .toList();

        return ResponseEntity.status(201).body(savedProdutosDTO);  // Retorna o status de criacao concluida
    }

    // Listar todos os produtos
    @GetMapping
    public List<ProdutoDTO> listAll() {
        List<Produto> produtos = repository.findAll();
        return produtos.stream()
                .map(produto -> new ProdutoDTO(
                        produto.getDescricao(),
                        produto.getCategoria(),
                        produto.getQuantidade(),
                        produto.getPreco(),
                        produto.getObservacoes()
                ))
                .toList();
    }

    // Obter produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(produto -> ResponseEntity.ok(new ProdutoDTO(
                        produto.getDescricao(),
                        produto.getCategoria(),
                        produto.getQuantidade(),
                        produto.getPreco(),
                        produto.getObservacoes()
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar produto pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setDescricao(produtoDTO.descricao());
        produto.setCategoria(produtoDTO.categoria());
        produto.setQuantidade(produtoDTO.quantidade());
        produto.setPreco(produtoDTO.preco());
        produto.setObservacoes(produtoDTO.observacoes());

        Produto updatedProduto = repository.save(produto);

        ProdutoDTO updatedProdutoDTO = new ProdutoDTO(
                updatedProduto.getDescricao(),
                updatedProduto.getCategoria(),
                updatedProduto.getQuantidade(),
                updatedProduto.getPreco(),
                updatedProduto.getObservacoes()
        );

        return ResponseEntity.ok(updatedProdutoDTO);  // Retorna o produto atualizado
    }

    // Deletar produto pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();  // Retorna 404 se o produto não existir
        }

        repository.deleteById(id);  // Deleta o produto
        return ResponseEntity.noContent().build();
    }
}
