package com.bop.commons.model.dao.hibernate;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bop.commons.model.dao.BaseDaoTestCase;
import com.bop.commons.model.dao.LookupDao;

/**
 * This class tests the current LookupDao implementation class
 * @author mraible
 */
public class LookupDaoTest extends BaseDaoTestCase {
    @Autowired
    LookupDao lookupDao;

    @Test
    public void testGetRoles() {
        List roles = lookupDao.getRoles();
        log.debug(roles);
        assertTrue(roles.size() > 0);
    }
}
