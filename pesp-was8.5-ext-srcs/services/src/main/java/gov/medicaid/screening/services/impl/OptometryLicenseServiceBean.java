/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.medicaid.screening.services.impl;

import gov.medicaid.entities.License;
import gov.medicaid.entities.OptometryLicenseSearchCriteria;
import gov.medicaid.entities.SearchResult;
import gov.medicaid.screening.dao.OptometryLicenseDAO;
import gov.medicaid.screening.services.ErrorCode;
import gov.medicaid.screening.services.OptometryLicenseService;
import gov.medicaid.screening.services.ParsingException;
import gov.medicaid.screening.services.PharmacyLicenseService;
import gov.medicaid.screening.services.ServiceException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * This is an EJB implementation of the <code>PharmacyLicenseService</code>. It is exposed as a web service by the
 * container.
 *
 * @author sampath01, j3_guile
 * @version 1.0
 */
@Stateless
@Remote(PharmacyLicenseService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OptometryLicenseServiceBean extends BaseService implements OptometryLicenseService {

    /**
     * Optometry License data access.
     */
    @EJB
    private OptometryLicenseDAO dataAccess;

    /**
     * Searches for providers with Optometry license using the name filter.
     *
     * @param criteria the search criteria
     * @return the matched results
     * @throws ParsingException if any parsing errors are encountered
     * @throws ServiceException for any other
     */
    public SearchResult<License> searchByName(OptometryLicenseSearchCriteria criteria) throws ParsingException,
        ServiceException {
        String signature = "OptometryLicenseServiceImpl#searchByName";
        LogUtil.traceEntry(getLog(), signature, new String[]{"criteria"}, new Object[]{criteria});

        try {
            SearchResult<License> results = dataAccess.searchByName(criteria);
            return LogUtil.traceExit(getLog(), signature, results);
        } catch (ServiceException e) {
            LogUtil.traceError(getLog(), signature, e);
            throw e;
        } catch (Throwable e) {
            LogUtil.traceError(getLog(), signature, e);
            throw new ServiceException(ErrorCode.MITA99999.getDesc(), e);
        }
    }

    /**
     * Searches for providers with Optometry license using the indentifier filter.
     *
     * @param criteria the search criteria
     * @return the matched results
     * @throws ParsingException if any parsing errors are encountered
     * @throws ServiceException for any other
     */
    public SearchResult<License> searchByLicenseNumber(OptometryLicenseSearchCriteria criteria)
        throws ParsingException, ServiceException {
        String signature = "OptometryLicenseServiceImpl#searchByLicenseNumber";
        LogUtil.traceEntry(getLog(), signature, new String[]{"criteria"}, new Object[]{criteria});

        try {
            SearchResult<License> results = dataAccess.searchByLicenseNumber(criteria);
            return LogUtil.traceExit(getLog(), signature, results);
        } catch (ServiceException e) {
            LogUtil.traceError(getLog(), signature, e);
            throw e;
        } catch (Throwable e) {
            LogUtil.traceError(getLog(), signature, e);
            throw new ServiceException(ErrorCode.MITA99999.getDesc(), e);
        }
    }
}
