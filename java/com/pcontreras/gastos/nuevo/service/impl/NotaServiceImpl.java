package com.pcontreras.gastos.nuevo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcontreras.gastos.nuevo.models.dao.IGenericDao;
import com.pcontreras.gastos.nuevo.models.dao.INotaDao;
import com.pcontreras.gastos.nuevo.models.entity.Nota;
import com.pcontreras.gastos.nuevo.service.INotaService;

@Service
public class NotaServiceImpl extends CRUDImpl<Nota, Long> implements INotaService{
	
	@Autowired
	private INotaDao notaDao;

	@Override
	protected IGenericDao<Nota, Long> getDao() {
		
		return notaDao;
	}

}
