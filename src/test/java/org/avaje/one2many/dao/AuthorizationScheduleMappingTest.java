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

    @Test
    public void shouldFindListOfRoles() {
        logger.debug("start listOfRoles()");
        List<RoleAssignment> roles = null;
        try {
            roles = ebeanServer.find(RoleAssignment.class).where().eq("parent.id", 1201).findList();
        } catch (Exception e) {
            logger.warn("find roles", e);
        }
        logger.debug("found {}", roles.size());
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
        validateSchedule(sched, "direct ");
        logger.debug("passed shouldFindAuthorizationSchedule()  " + sched);
    }

    @Test
    public void shouldFindAuthorizationScheduleViaMappedJoin() {
        logger.debug("start shouldFindAuthorizationScheduleViaMappedJoin()");
        User testUser = ebeanServer.find(User.class, 1201);
        AuthorizationSchedule authorizationSchedule = testUser.getAuthorizationSchedule();
        logger.debug("authorizationSchedule=" + authorizationSchedule);
        validateSchedule(authorizationSchedule, "byMapping");
        logger.debug("status=" + authorizationSchedule.getStatus());
        logger.debug("passed shouldFindAuthorizationScheduleViaMappedJoin()  " + authorizationSchedule.getId());
    }

    private void validateSchedule(AuthorizationSchedule sched, String label) {
        Assert.assertNotNull(label, sched);
        logger.debug(label + ": pre get assigned roles");
        int nroles = -1;
        try {
            final Set<RoleAssignment> assignedRoles = sched.getAssignedRoles();
            if(assignedRoles!=null)
            nroles = assignedRoles.size();
        } catch (Exception e) {
            logger.warn(label + ": count roles", e);
        }
        logger.debug(label + ": nroles={}", nroles);
    }
}
