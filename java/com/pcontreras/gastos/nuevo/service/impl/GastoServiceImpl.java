package com.pcontreras.gastos.nuevo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcontreras.gastos.nuevo.models.dao.IGastoDao;
import com.pcontreras.gastos.nuevo.models.dao.IGenericDao;
import com.pcontreras.gastos.nuevo.models.entity.Gasto;
import com.pcontreras.gastos.nuevo.models.entity.TipoGasto;
import com.pcontreras.gastos.nuevo.service.IGastoService;

@Service
public class GastoServiceImpl extends CRUDImpl<Gasto, Long> implements IGastoService {
	
	@Autowired
	private IGastoDao gastoDao;

	@Override
	@Transactional(readOnly = true)
	public List<TipoGasto> findAllTipoGasto() {
		
		return gastoDao.findAllTipos();
	}

	@Override
	protected IGenericDao<Gasto, Long> getDao() {
		
		return gastoDao;
	}

	@Override
	@Transactional(readOnly = true)
	public int valor() {
		
		return gastoDao.valor();
	}

	@Override
	@Transactional(readOnly = true)
	public int cantidad() {
		
		return gastoDao.cantidad();
	}

	@Override
	@Transactional(readOnly = true)
	public Integer showTotalGastoByFecha(int mes, int year) {
	
		return gastoDao.showTotalGastoByFecha(mes, year);
	} 
	
	

	/*
	@Override
	@Transactional(readOnly = true)
	public List<Gasto> findAll() {
		
		return gastoDao.findAll();
	}

	@Override
	public Gasto findById(Long id) {
	
		return gastoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Gasto save(Gasto gasto) {
		
		return gastoDao.save(gasto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		gastoDao.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoGasto> findAllTipoGasto() {
		
		return ga*/

}
