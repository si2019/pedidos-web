package es.uvigo.esei.mei.pedidos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.pedidos.daos.ClienteDAO;
import es.uvigo.esei.mei.pedidos.entidades.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {
	@Autowired
	ClienteDAO dao;

	@Override
	@Transactional
	public Cliente crear(Cliente cliente) {
		return dao.save(cliente);
	}

	@Override
	@Transactional
	public Cliente modificar(Cliente cliente) {
		return dao.save(cliente);
	}

	@Override
	@Transactional
	public void eliminar(Cliente cliente) {
		dao.delete(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente buscarPorDNI(String dni) {
		return dao.getOne(dni);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> buscarTodos() {
		return dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> buscarPorNombre(String patron) {
		return dao.findByNombreContaining(patron);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> buscarPorLocalidad(String localidad) {
		return dao.findByDireccionLocalidad(localidad);
	}

}
