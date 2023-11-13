package com.pcontreras.gastos.nuevo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pcontreras.gastos.nuevo.models.entity.Gasto;
import com.pcontreras.gastos.nuevo.models.entity.TipoGasto;
import com.pcontreras.gastos.nuevo.service.IGastoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200" })
public class GastoRestController {
	

	@Autowired
	private IGastoService gastoService;
	
	
	@GetMapping("/gastos")
	public List<Gasto> listar() throws Exception{   //aqui quede
		return gastoService.listar();
	}
	
	@GetMapping("/gastos/page/{page}")
	public Page<Gasto> index(@PathVariable Integer page) throws Exception{
		Pageable pageable = PageRequest.of(page, 4);
		return gastoService.findAll(pageable);
	}
	
	@GetMapping("/gasto/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) throws Exception{
		
		Gasto gasto =null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			gasto = gastoService.listarPorId(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(gasto == null) {
			response.put("mensaje", "El gasto con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Gasto>(gasto,HttpStatus.OK);
		
	}
	
	@PostMapping("/gasto")
	public ResponseEntity<?> create(@Valid @RequestBody Gasto gasto,BindingResult result) throws Exception{
		// es el nuevo gasto creado
		//se inicializa
		Gasto gastoNew = null;
		Map<String, Object> response =new HashMap<>();
		
		// se valida si contiene errores el objeto 
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					     .stream()
					     .map(err -> "El campo '"+ err.getField() + "' "+err.getDefaultMessage())
					     .collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			gastoNew =gastoService.registrar(gasto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El gasto ha sido creado con éxito! ");
		response.put("gasto", gastoNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/gasto/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Gasto gasto,BindingResult result,@PathVariable Long id) throws Exception{
		
		//se obtiene el gasto  que se quiere modificar
		Gasto gastoActual = gastoService.listarPorId(id);
		
		//gasto ya actualizado
		Gasto gastoUpdated =null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			// se debe obtener los mensajes de error de cada campo 
			// y convertir estos en una lista de errores de tipo string
			
			// se debe convertir esta lista de fielderrors en String
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+ err.getField() + "' "+err.getDefaultMessage())// muy parecido  al operador map en angular (rxjs), mismo concepto!
					.collect(Collectors.toList());// ahora podemos convertir de regreso el stream  aun tipo List
			response.put("errors", errors);
			// se responde con un responseentity con listados de error
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
			
			// en lo anterior se recibe un field errors y lo convertimos a string
		}
		
		if(gastoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el gasto con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			//modificamos los datos del gasto actual con los datos del gasto que te envian
			gastoActual.setDescription(gasto.getDescription());
			gastoActual.setPrice(gasto.getPrice());
			gastoActual.setFecha(gasto.getFecha());
			gastoActual.setTipoGasto(gasto.getTipoGasto());
			
			gastoUpdated = gastoService.registrar(gastoActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el gasto en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El gasto ha sido actualizado con éxito!");
		response.put("gasto",gastoUpdated);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/gasto/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			gastoService.eliminar(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el gasto de la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El gasto fue eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/gastos/tipoGastos")
	public List<TipoGasto> listarTipos(){
		return gastoService.findAllTipoGasto();
	}
	
	@GetMapping("/gastos/valor")
	public int valorTotal() {
		return gastoService.valor();
	}
	
	@GetMapping("/gastos/cantidad")
	public int cantidad() {
		return gastoService.cantidad();
	}
	
	@GetMapping("/gastos/filtrarValor/{a}/{b}")
	public ResponseEntity<?> valorByFecha(@PathVariable int a , @PathVariable int b) {
		Integer g = 0;
		Map<String, Object> response  = new HashMap<>();
		
		if(a == 0 || b == 0 ) {
			response.put("mensaje", "Error no existe mes 0  o año 0!");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	    g = gastoService.showTotalGastoByFecha(a, b);
       
	    if(g == null) {
	    	response.put("mensaje", "No hay gastos para el mes y año consultado!");
	    	return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	     return new ResponseEntity<Integer>(g,HttpStatus.OK);
	}
}
