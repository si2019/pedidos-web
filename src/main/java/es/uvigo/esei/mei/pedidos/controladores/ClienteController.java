package es.uvigo.esei.mei.pedidos.controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.uvigo.esei.mei.pedidos.entidades.Cliente;
import es.uvigo.esei.mei.pedidos.entidades.Direccion;
import es.uvigo.esei.mei.pedidos.servicios.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	ClienteService clienteService;

	@GetMapping
	public String prepararListarClientes(Model modelo) {
		List<Cliente> clientes = clienteService.buscarTodos();
		modelo.addAttribute("clientes", clientes);
		modelo.addAttribute("nombreCliente", "");
		modelo.addAttribute("nombreLocalidad", "");
		return "cliente/listado_clientes";
	}

	@PostMapping
	public String actualizarListarClientes(@RequestParam(required = false) String nombreCliente, @RequestParam(required = false) String nombreLocalidad, Model modelo) {
		List<Cliente> clientes;
		if ((nombreCliente != null) && !nombreCliente.isEmpty()) {
			clientes = clienteService.buscarPorNombre(nombreCliente);
		} else if ((nombreLocalidad != null) && !nombreLocalidad.isEmpty()) {
			clientes = clienteService.buscarPorLocalidad(nombreLocalidad);
		} else {
			clientes = clienteService.buscarTodos();
		}
		modelo.addAttribute("clientes", clientes);
		return "cliente/listado_clientes";
	}

	@GetMapping("{dni}/eliminar")
	public String borrarCliente(@PathVariable("dni") String dni, Model modelo) {
		Cliente cliente = clienteService.buscarPorDNI(dni);
		if (cliente != null) {
			clienteService.eliminar(cliente);
			return "redirect:/clientes";
		} else {
			modelo.addAttribute("mensajeError", "Cliente no encontrado");
			return "error";
		}
	}

	@GetMapping("nuevo")
	public String prepararNuevoCliente(Model modelo) {
		Cliente cliente = new Cliente();
		cliente.setDireccion(new Direccion());
		modelo.addAttribute("cliente", cliente);
		modelo.addAttribute("esNuevo", true);
		return "cliente/editar_cliente";
	}
	
	@PostMapping("nuevo")
	public String crearCliente(@ModelAttribute Cliente cliente) {
		clienteService.crear(cliente);
		return "redirect:/clientes";
	}

	
	@GetMapping("{dni}")
	public String prepararEditarCliente(@PathVariable("dni") String dni, Model modelo) {
		Cliente cliente = clienteService.buscarPorDNI(dni);
		if (cliente != null) {
			modelo.addAttribute("cliente", cliente);
			modelo.addAttribute("esNuevo", false);
			return "cliente/editar_cliente";
		} else {
			modelo.addAttribute("mensajeError", "Cliente no encontrado");
			return "error";
		}
	}

	@PostMapping("{dni}")
	public String actualizarCliente(@Valid Cliente cliente, BindingResult resultado) {
		clienteService.modificar(cliente);
		return "redirect:/clientes";
	}

}
