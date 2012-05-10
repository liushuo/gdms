package org.tute.sure.hibernatedao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tute.sure.model.Department;

/**
 	* A data access object (DAO) providing persistence and search support for Department entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see org.tute.sure.model.Department
  * @author MyEclipse Persistence Tools 
 */

public class DepartmentDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(DepartmentDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String TELEPHONE = "telephone";



    
    public void save(Department transientInstance) {
        log.debug("saving Department instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Department persistentInstance) {
        log.debug("deleting Department instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Department findById( java.lang.Integer id) {
        log.debug("getting Department instance with id: " + id);
        try {
            Department instance = (Department) getSession()
                    .get("org.tute.sure.model.Department", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Department> findByExample(Department instance) {
        log.debug("finding Department instance by example");
        try {
            List<Department> results = (List<Department>) getSession()
                    .createCriteria("org.tute.sure.model.Department")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Department instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Department as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Department> findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List<Department> findByTelephone(Object telephone
	) {
		return findByProperty(TELEPHONE, telephone
		);
	}
	

	public List findAll() {
		log.debug("finding all Department instances");
		try {
			String queryString = "from Department";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Department merge(Department detachedInstance) {
        log.debug("merging Department instance");
        try {
            Department result = (Department) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Department instance) {
        log.debug("attaching dirty Department instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Department instance) {
        log.debug("attaching clean Department instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}