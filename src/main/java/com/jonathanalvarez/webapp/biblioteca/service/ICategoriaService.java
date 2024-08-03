package com.jonathanalvarez.webapp.biblioteca.service;


import java.util.List;

import com.jonathanalvarez.webapp.biblioteca.model.Categoria;

public class ICategoriaService {

    public List<Categoria> listarCategorias();

    public Categoria buscarCategoriaPorId(Long id);

    public Categoria guardarCategoria(Categoria categoria);

    public void eliminarCategoria(Categoria categoria);
}
