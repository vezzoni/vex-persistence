/*
 * Copyright 2008 Roberto Vezzoni
 *
 * Este arquivo é parte da biblioteca vex-persistence.
 *
 * vex-persistence é um software livre; você pode redistribuí-la e/ou
 * modificá-la dentro dos termos da Licença Pública Geral Menor GNU como
 * publicada pela Fundação do Software Livre (FSF); na versão 2 da
 * Licença, ou (na sua opnião) qualquer versão.
 *
 * Esta biblioteca é distribuida na esperança que possa ser útil,
 * mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
 * Licença Pública Geral Menor GNU para mais detalhes.
 *
 * Você deve ter recebido uma cópia da Licença Pública Geral Menor GNU
 * junto com esta biblioteca, se não, escreva para a Fundação do Software
 * Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */

package br.com.vexit.vexpersistence.dao.impl;

import br.com.vexit.vexpersistence.dao.intf.DAOIntf;
import br.com.vexit.vexpersistence.exception.VexPersistenceException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * <tt>DAOInjectedSession</tt> é uma classe abstrata responsável por
 * implementar métodos genéricos para um {@link DAOInjectedSessionHandler <tt>DAOInjectedSessionHandler concreto</tt>}.
 *
 * @version 1.4.1, 05/12/08
 *
 * @see DAOIntf
 * @see DAOLocalSessionHandler
 *
 * @since 1.4
 *
 * @author Roberto Vezzoni
 */
public abstract class DAOInjectedSession implements DAOIntf {

    private EntityManager em;
    
    /**
     * Cria uma instância de um Data Access Object passando
     * o sessão obtida por Dependency Injection.
     *
     * @param em
     *        Uma EntityManager válida.
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    public DAOInjectedSession(EntityManager em) {
        this.em = em;
    }
    
    /**
     * Retorna a sessão com a fonte de dados cuja a qual foi obtida por Dependency Injection.
     *
     * @return Uma EntityManager.
     *
     * @since  1.3
     *
     * @author Roberto Vezzoni
     */
    private EntityManager getEntityManager() {
        
        return em;
    }

    /*
     * CRUD methods
     */
    /**
     * Retorna uma lista ordenada de todas as entidades de uma classe desejada, dado um nome de atributo como argumento.
     *
     * @param  
     * clazz
     * Classe válida.
     * @param  
     * fields
     * Lista de atributos a serem utilizados para ordenamento da lista.
     *
     * @return Uma lista de entidades.
     *
     * @since  1.2
     *
     * @author Roberto Vezzoni
     */
    public <T extends Serializable> List<T> getAll(Class<T> clazz, Field ... fields) throws VexPersistenceException {
        
        List<T> result = null;

        try {
            
            beforeGetAll(clazz);
            
            try {
                // capturando o nome da entidade.
                String entity = clazz.getName();
                
                String ql = "select e from " + entity + " as e";
                
                // definindo ordem, se houver.
                for (int i = 0; i < fields.length; i++) {
                    
                    if (i == 0) {
                        ql += " order by ";
                    }
                    
                    ql += "e." + fields[i].getName();
                    
                    if (i < (fields.length - 1)) {
                        ql += ", ";
                    }
                }
                
                
                result = getEntityManager().createQuery(ql).getResultList();

            } catch (Exception e) {

                e.printStackTrace();
                
                throw new VexPersistenceException(e);
            }

            return result;
            
        } finally {
            
            afterGetAll(clazz);
        }
        
    }
    
    /**
     * Carrega uma entidade de uma classe desejada dada uma chave primária como argumento.
     *
     * @param  clazz
     *         Classe válida.
     * 
     * @param  id
     *         Chave primária da entidade.
     *
     * @return Uma entidade.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public <T extends Serializable> T get(Class<T> clazz, Object id) throws VexPersistenceException {
        
        T result = null;

        try {
            beforeGet(clazz, id);
            
            try {
                
                result = getEntityManager().getReference(clazz, id);
                
            } catch (Exception e) {
                
                e.printStackTrace();

                throw new VexPersistenceException(e);
            }

            return result;
            
        } finally {
            
            afterGet(clazz, id);
        }
        
    }
    
    /**
     * Persiste a entidade dada como argumento.
     *
     * @param  entity
     *         Entidade válida a ser persistida.
     *
     * @return  A entidade persistida.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public <T extends Serializable> T save(T entity) throws VexPersistenceException {
        
        T result = null;

        try {
            beforeSave(entity);
            
            try {
                
                result = getEntityManager().merge(entity);
                
            } catch (Exception e) {
                
                e.printStackTrace();

                throw new VexPersistenceException(e);
            }

            return result;
            
        } finally {
            
            afterSave(entity);
        }
        
    }

    /**
     * Cancela as operações realizadas sobre a entidade passada como argumento.
     *
     * @param  entity
     *         Entidade válida.
     *
     * @see #refresh()
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    @Deprecated
    public <T extends Serializable> void cancel(T entity) {
        
        try {
            beforeCancel(entity);
            
            try {
                
                getEntityManager().refresh(entity);
                
            } catch (Exception e) {
                
                e.printStackTrace();
            }
            
        } finally {
            
            afterCancel(entity);
        }
        
    }
    
    /**
     * Cancela as operações realizadas sobre a entidade passada como argumento
     * atualizando seu estado de acordo com os valores correspondentes na fonte de dados.
     *
     * @param  entity
     *         Entidade válida.
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    public <T extends Serializable> void refresh(T entity) throws VexPersistenceException {

        try {
            beforeCancel(entity);

            try {

                getEntityManager().refresh(entity);

            } catch (Exception e) {

                e.printStackTrace();

                throw new VexPersistenceException(e);
            }

        } finally {

            afterCancel(entity);
        }

    }

    /**
     * Remove a entidade passada como argumento.
     *
     * @param  entity
     *         Entidade válida a ser persistida.
     *
     * @return  true com o sucesso e false com falha da operação.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public <T extends Serializable> boolean remove(T entity) throws VexPersistenceException {
        
        boolean result = false;

        try {
            beforeRemove(entity);
            
            try {
                
                getEntityManager().remove(entity);
                
                result = true;
            } catch (Exception e) {
                
                e.printStackTrace();

                throw new VexPersistenceException(e);
            }

            return result;
            
        } finally {
            
            afterRemove(entity);
        }
        
    }
    
    /*
     * Event handler methods - call backs methods
     */
    /**
     * Callback Method executado antes de ser feita uma busca através de uma classe válida.
     *
     * @param  clazz
     *         Classe válida.
     * 
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void beforeGetAll(Class clazz);
    
    /**
     * Callback Method executado após ser feita uma busca através de uma classe válida.
     *
     * @param  clazz
     *         Classe válida.
     * 
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void afterGetAll(Class clazz);
    
    /**
     * Callback Method executado antes de ser carregada uma entidade dados uma classe válida e sua chave primária.
     *
     * @param  clazz
     *         Classe válida.
     * 
     * @param  id
     *         Chave prmária da entidade da classe valida informada.
     * 
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void beforeGet(Class clazz, Object id);
    
    /**
     * Callback Method executado após ser carregada uma entidade dados uma classe válida e sua chave primária.
     *
     * @param  clazz
     *         Classe válida.
     * 
     * @param  id
     *         Chave prmária da entidade da classe valida informada.
     * 
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void afterGet(Class clazz, Object id);
    
    /**
     * Callback Method executado antes de ser persistida uma entidade dada como argumento.
     *
     * @param  entity
     *         Entidade válida.
     * 
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void beforeSave(Object entity);
    
    /**
     * Callback Method executado após ser persistida uma entidade dada como argumento.
     *
     * @param  entity
     *         Entidade válida.
     * 
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void afterSave(Object entity);
    
    /**
     * Callback Method executado antes de ser cancelada todas as operações sobre uma entidade dada como argumento.
     *
     * @param  entity
     *         Entidade válida.
     * 
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void beforeCancel(Object entity);
    
    /**
     * Callback Method executado após ser cancelada todas as operações sobre uma entidade dada como argumento.
     *
     * @param  entity
     *         Entidade válida.
     * 
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void afterCancel(Object entity);
    
    /**
     * Callback Method executado antes de ser removida uma entidade dada como argumento.
     *
     * @param  entity
     *         Entidade válida.
     * 
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void beforeRemove(Object entity);
    
    /**
     * Callback Method executado após ser removida uma entidade dada como argumento.
     *
     * @param  entity
     *         Entidade válida.
     * 
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void afterRemove(Object entity);
    
}