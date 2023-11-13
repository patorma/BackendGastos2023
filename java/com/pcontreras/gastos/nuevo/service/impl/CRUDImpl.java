package com.pcontreras.gastos.nuevo.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.pcontreras.gastos.nuevo.models.dao.IGenericDao;
import com.pcontreras.gastos.nuevo.service.ICRUD;

public abstract class CRUDImpl<T,ID> implements ICRUD<T, ID> {

	
	//necesito que una implmentacion le diga como comportarse
	protected abstract IGenericDao<T, ID> getDao();

	@Override
	@Transactional
	public T registrar(T t) throws Exception {
		
		return getDao().save(t);
	}

	@Override
	@Transactional
	public T modificar(T t) throws Exception {
		
		return getDao().save(t);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> listar() throws Exception {
		
		return getDao().findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public T listarPorId(ID id) throws Exception {
	
		return getDao().findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void eliminar(ID id) throws Exception {
		getDao().deleteById(id);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<T> findAll(Pageable pageable) throws Exception {
		
		return getDao().findAll(pageable);
	}


}
