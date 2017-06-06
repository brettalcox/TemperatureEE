/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author brettalcox
 */
public class AbstractBean {
    
    @PersistenceContext
    protected EntityManager entityManager;
    
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public EntityManager getEntityManager() { return entityManager; }
}
