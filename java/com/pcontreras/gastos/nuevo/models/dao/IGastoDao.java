package com.pcontreras.gastos.nuevo.models.dao;

import java.util.List;


import org.springframework.data.jpa.repository.Query;


import com.pcontreras.gastos.nuevo.models.entity.Gasto;
import com.pcontreras.gastos.nuevo.models.entity.TipoGasto;

public interface IGastoDao extends IGenericDao<Gasto, Long> {
	
	@Query(value ="SELECT SUM(price)  FROM Gastos ", nativeQuery = true)
	public int valor();
	
	@Query(value="SELECT COUNT(description) FROM Gastos",nativeQuery = true)
	public int cantidad();
	
	@Query(value="SELECT SUM(g.price) FROM Gastos g WHERE MONTH(g.fecha_gasto) = ?1 AND YEAR(g.fecha_gasto) = ?2",nativeQuery = true)
	public Integer showTotalGastoByFecha(int mes,int year);

	@Query("from TipoGasto")
	public List<TipoGasto> findAllTipos();
}
