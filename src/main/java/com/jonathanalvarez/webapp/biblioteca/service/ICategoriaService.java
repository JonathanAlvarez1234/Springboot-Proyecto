package com.jonathanalvarez.webapp.biblioteca.service;


import java.util.List;

import com.jonathanalvarez.webapp.biblioteca.model.Categoria;

public interface ICategoriaService {

    public List<Categoria> listarCategorias();

    public Categoria buscarCategoriaPorId(long id);

    public Boolean guardarCategoria(Categoria categoria);

    public void eliminarCategoria(Categoria categoria);

    public Boolean verificarCategoriaDuplicada(Categoria categorianNueva); 
}
