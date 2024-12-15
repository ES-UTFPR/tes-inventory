package com.example.demo.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoDTO(
        @NotNull(message = "Descrição não pode ser nula")
        @Size(min = 3, max = 100, message = "Descrição deve ter entre 3 e 100 caracteres")
        String descricao,

        @NotNull(message = "Categoria não pode ser nula")
        @Size(min = 3, max = 50, message = "Categoria deve ter entre 3 e 50 caracteres")
        String categoria,

        @NotNull(message = "Quantidade não pode ser nula")
        int quantidade,

        @NotNull(message = "Preço não pode ser nulo")
        double preco,

        @Size(max = 500, message = "Observações devem ter no máximo 500 caracteres")
        String observacoes
) {}
