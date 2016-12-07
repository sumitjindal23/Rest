package com.ofss.config.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.config.PersistenceUnitProperties;

import com.ofss.config.service.CloudConfigService;
import com.ofss.util.logger.FCLiteAPILogger;
/**
 * 
 * This class provides Entity Manger for ORM operations
 *
 */
public class EntityManagerProvider {
    /**
     * This attribute represents the fully qualified class name.
     */
    private static final String THIS_COMPONENT_NAME = CloudConfigService.class.getName();

    /**
     * This creates a instance of logger for the component name as class name.
     */
    private static final Logger logger = FCLiteAPILogger.getUniqueInstance().getLogger(THIS_COMPONENT_NAME);

    /**
     * Thread local for Entity Manager.
     */
    private static final ThreadLocal<EntityManager> emThreadLocal;

    /**
     * Thread local for Entity.
     */
    private static final ThreadLocal<String> entityThreadLocal;

    /**
     * Cache for Entity Manager Factory
     */
    private static Map<String, Object> emfactoryCache = null;

    static {
        emThreadLocal = new ThreadLocal<EntityManager>();
        entityThreadLocal = new ThreadLocal<String>();
        emfactoryCache = new HashMap<String, Object>(100);
    }

    public static EntityManager getEntityManager() {
        if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, " Inside getEntityManager()");
        }
        EntityManagerFactory emfactory = null;
        EntityManager entitymanager = emThreadLocal.get();
        String entityJndi = entityThreadLocal.get();

        if (entitymanager == null) {
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE, "Entity Manager is null");
            }
            if (emfactoryCache.containsKey(entityJndi)) {
                if (logger.isLoggable(Level.FINE)) {
                    logger.log(Level.FINE, "Getting factory from cache");
                }
                emfactory = (EntityManagerFactory) emfactoryCache.get(entityJndi);
                entitymanager = emfactory.createEntityManager();
                emThreadLocal.set(entitymanager);
            } else {
                if (logger.isLoggable(Level.FINE)) {
                    logger.log(Level.FINE, "Initializing cache");
                }
                Map<String, String> persistenceProps = new HashMap<String, String>();
                persistenceProps.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, entityJndi);
                emfactory = Persistence.createEntityManagerFactory("fclite-persistence-unit", persistenceProps);
                entitymanager = emfactory.createEntityManager();
                emfactoryCache.put(entityJndi, emfactory);
                emThreadLocal.set(entitymanager);
            }
        }
        return entitymanager;
    }

    public static void closeEntityManager() {
        EntityManager em = emThreadLocal.get();
        if (em != null) {
            em.close();
            emThreadLocal.set(null);
            entityThreadLocal.set(null);
        }
    }

    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public static void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    public static void commit() {
        getEntityManager().getTransaction().commit();
    }

    public static void setEntityJndi(String entityJndi) {
        entityThreadLocal.set(entityJndi);
    }

    public static void closeEntityManagerFactory() {
        for (Map.Entry<String, Object> entry : emfactoryCache.entrySet()) {
            EntityManagerFactory emfactory = (EntityManagerFactory) entry.getValue();
            emfactory.close();
        }

    }
}
