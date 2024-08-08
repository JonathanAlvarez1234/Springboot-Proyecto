package com.jonathanalvarez.webapp.biblioteca.service;

import java.util.List;

import com.jonathanalvarez.webapp.biblioteca.model.Cliente;

public interface IClienteService {

    public List<Cliente> listarClientes();

    public Cliente guardarCliente(Cliente cliente);

    public Cliente buscarClientePorId(Long dpi);

    public void eliminarCliente(Cliente cliente);
}
