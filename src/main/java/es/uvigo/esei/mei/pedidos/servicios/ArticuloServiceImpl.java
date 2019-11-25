package es.uvigo.esei.mei.pedidos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.pedidos.daos.ArticuloDAO;
import es.uvigo.esei.mei.pedidos.daos.FamiliaDAO;
import es.uvigo.esei.mei.pedidos.entidades.Articulo;
import es.uvigo.esei.mei.pedidos.entidades.Familia;

@Service
public class ArticuloServiceImpl implements ArticuloService {
	@Autowired
	ArticuloDAO articuloDAO;

	@Autowired
	FamiliaDAO familiaDAO;

	@Override
	@Transactional
	public Articulo crear(Articulo articulo) {
		return articuloDAO.save(articulo);
	}

	@Override
	@Transactional
	public Articulo modificar(Articulo articulo) {
		return articuloDAO.save(articulo);
	}

	@Override
	@Transactional
	public void eliminar(Articulo articulo) {
		articuloDAO.delete(articulo);
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo buscarPorId(Long id) {
		return articuloDAO.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> buscarTodos() {
		return articuloDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> buscarPorDescripcion(String patron) {
		return articuloDAO.findByDescripcionContaining(patron);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> buscarPorFamilia(Long idFamilia) {
		return articuloDAO.findByFamiliaId(idFamilia);
	}

	@Override
	@Transactional
	public Familia crearFamilia(Familia familia) {
		return familiaDAO.save(familia);
	}

	@Override
	@Transactional
	public Familia modificarFamilia(Familia familia) {
		return familiaDAO.save(familia);
	}

	@Override
	@Transactional
	public void eliminarFamilia(Familia familia) {
		familiaDAO.delete(familia);
	}

	@Override
	@Transactional(readOnly = true)
	public Familia buscarFamiliaPorId(Long id) {
		return familiaDAO.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Familia> buscarFamilias() {
		return familiaDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Familia> buscarFamiliasPorDescripcion(String patron) {
		return familiaDAO.findByDescripcionContaining(patron);
	}

}
