package org.avaje.one2many.dao;

import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.avaje.one2many.domain.AuthorizationSchedule;
import org.avaje.one2many.domain.RoleAssignment;
import org.avaje.one2many.domain.User;

public class AuthorizationScheduleMappingTest extends AbstractEBeanDaoTestCase {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationScheduleMappingTest.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldFindAuthorizationSchedule() {
        logger.debug("start shouldFindAuthorizationSchedule()");
        AuthorizationSchedule sched = null;
        try {
            sched = ebeanServer.find(AuthorizationSchedule.class).where().eq("id", 1201).findUnique();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Assert.assertNotNull(sched);
        logger.debug("pre get assigned roles");
        int nroles=-1;
        try {
            nroles = sched.getAssignedRoles().size();
        } catch (Exception e) {
            logger.warn("count roles",e);
        }
        logger.debug("nroles={}",nroles);
        logger.debug("passed shouldFindAuthorizationSchedule()  " + sched);
    }

    @Test
    public void shouldFindAuthorizationScheduleViaMappedJoin() {
        logger.debug("start shouldFindAuthorizationScheduleViaMappedJoin()");
        User testUser = ebeanServer.find(User.class,1201);
        AuthorizationSchedule authorizationSchedule = testUser.getAuthorizationSchedule();
        logger.debug("authorizationSchedule=" + authorizationSchedule);
        Assert.assertNotNull(authorizationSchedule);
        Set<RoleAssignment> roles=null;
        try {
            roles = authorizationSchedule.getAssignedRoles();
        } catch (Exception e) {
            logger.warn("count roles via mapped join",e);
            Assert.fail();
        }
        Assert.assertNotNull(roles);
        logger.debug("roles={}", roles.size());
        logger.debug("status=" + authorizationSchedule.getStatus());
        logger.debug("passed shouldFindAuthorizationSchedule()  " + authorizationSchedule.getId());
    }
    
    @Test
    public void shouldFindListOfRoles() {
        logger.debug("start listOfRoles()");
        List<RoleAssignment> roles=null;
        try {
            roles = ebeanServer.find(RoleAssignment.class).where().eq("parent_id",1201).findList();
        } catch (Exception e) {
            logger.warn("find roles",e);
        }
        logger.debug("found {}",roles.size());
    }
}
