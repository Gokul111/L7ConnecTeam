package com.l7.connecteam.serviceImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.l7.connecteam.daoImpl.CriteriaDaoImpl;
import com.l7.connecteam.dto.CriteriaDto;
import com.l7.connecteam.dto.TechnologyDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.service.CriteriaService;

/**
 * Implements business logic of operations related to an assessment's criteria
 * @author soumya.raj
 */
public class CriteriaServiceImpl implements CriteriaService {
	Logger logger = Logger.getLogger(CriteriaServiceImpl.class.getName());
	private CriteriaDaoImpl criteriaImplObj=new CriteriaDaoImpl();
	
	/**
	 * Logical implementations involved in criteria creation
	 * @param criteriaObj
	 * @param techDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public CriteriaDto createCriteria(CriteriaDto criteriaObj, TechnologyDto techDataObj) throws UIException, SQLException {
		CriteriaDto CriteriaData=criteriaImplObj.createCriteria(criteriaObj,techDataObj);
		return CriteriaData;
	}
	
	/**
	 * Logical implementations involved in determining if a criteria exists or not
	 * @param criteriaObj
	 * @param techDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public CriteriaDto ifCriteriaExists(CriteriaDto criteriaObj,TechnologyDto techDataObj) throws UIException, SQLException {
		criteriaObj=criteriaImplObj.ifCriteriaExists(criteriaObj,techDataObj);
		if (criteriaObj.getCriteriaID() != 0) {
			logger.info("Criteria with criteria name " + criteriaObj.getCriteriaName() + " for this technology and training group already exists");
			return criteriaObj;
		} else {
			criteriaObj = createCriteria(criteriaObj,techDataObj);
			return criteriaObj;
		}
	} 
	
}
