package com.pcontreras.gastos.nuevo.service;

import java.util.List;

import com.pcontreras.gastos.nuevo.models.entity.Gasto;
import com.pcontreras.gastos.nuevo.models.entity.TipoGasto;

public interface IGastoService extends ICRUD<Gasto,Long>{
	

	
	public List<TipoGasto> findAllTipoGasto();
	
	public int valor();
	
	public int cantidad();
	
	public Integer showTotalGastoByFecha(int mes,int year);
	
	
	

}
