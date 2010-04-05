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

package br.com.vexit.vexpersistence.namedquery.impl;

import br.com.vexit.vexpersistence.conn.LocalPersistenceConnection;
import br.com.vexit.vexpersistence.conn.PersistenceConnection;
import br.com.vexit.vexpersistence.exception.VexPersistenceException;
import br.com.vexit.vexpersistence.namedquery.intf.NamedQueryIntf;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * <tt>NamedQueryLocalSession</tt> é uma classe abstrata responsável por
 * implementar métodos genéricos para uma
 * {@link NamedQueryLocalSessionHandler <tt>NamedQueryLocalSessionHandler concreta</tt>}.
 *
 * @version 1.4.1, 05/12/08
 *
 * @see NamedQueryIntf
 * @see NamedQueryLocalSessionHandler
 * @see VexPersistenceException
 *
 * @since 1.4.0
 *
 * @author Roberto Vezzoni
 */
public abstract class NamedQueryLocalSession implements NamedQueryIntf {

    private String persistenceUnitName;
    private boolean keepSessionAlive;
    
    /**
     * Cria uma instância de uma Named Query passando
     * o nome da conexão local e a forma de tratamento da sessão.
     *
     * @param persistenceUnitName
     *        Nome de uma Persistence Unit válida para operações persistentes.
     *
     * @param keepSessionAlive
     *        true - Mantém a sessão com o banco de dados ativa.
     *        false - Fecha a sessão com banco de dados.
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    public NamedQueryLocalSession(String persistenceUnitName, boolean keepSessionAlive) {
        this.persistenceUnitName = persistenceUnitName;
        this.keepSessionAlive = keepSessionAlive;
    }

    /**
     * Retorna uma {@link LocalPersistenceConnection <tt>conexão local estabelecida</tt>}.
     *
     * @return Uma {@link LocalPersistenceConnection <tt>conexão local estabelecida</tt>}.
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    private PersistenceConnection getPersistenceConnection() {

        return PersistenceConnection.getInstance(persistenceUnitName);
    }

    /**
     * Retorna a sessão com a fonte de dados cuja a qual deve ter sido
     * levantada como recurso local.
     *
     * @return Uma EntityManager.
     *
     * @since  1.3
     *
     * @author Roberto Vezzoni
     */
    private EntityManager getEntityManager() {
        
        return getPersistenceConnection().getEntityManager();
    }

    /**
     * Inicia uma transação se a sessão foi obtida como recurso local.
     *
     * @since  1.3
     *
     * @author Roberto Vezzoni
     */
    private void startTransaction() {
        
        getPersistenceConnection().startTransaction();
    }

    /**
     * Confirma uma transação se a sessão foi obtida como recurso local.
     *
     * @since  1.3
     *
     * @author Roberto Vezzoni
     */
    private void commitTransaction() {
        
        getPersistenceConnection().commit();
    }

    /**
     * Cancela uma transação que teve problemas na operação se a sessão foi obtida como recurso local.
     *
     * @since  1.3
     *
     * @author Roberto Vezzoni
     */
    private void rollbackTransaction() {
        
        getPersistenceConnection().rollback();
    }

    /*
     * NamedQuery handler methods
     */
    /**
     * Retorna uma lista dado uma Named Query válida.
     *
     * @param  namedQuery
     *         Nome da Named Query a ser executada.
     * 
     * @param  params
     *         Lista com os valores dos parâmetros da NamedQuery.
     *
     * @return  Uma lista de entidades.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public List findByNamedQuery(String namedQuery, Object ... params) throws VexPersistenceException {
        
        return findByNamedQuery(namedQuery, -1, -1, params);
    }
    
    /**
     * Retorna uma lista dado uma Named Query válida.
     *
     * @param  namedQuery
     *         Nome da Named Query a ser executada.
     * 
     * @param  params
     *         Lista com os nomes e valores dos parâmetros da NamedQuery.
     *
     * @return  Uma lista de entidades.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public List findByNamedQuery(String namedQuery, Map<String, Object> params) throws VexPersistenceException {
        
        return findByNamedQuery(namedQuery, -1, -1, params);
    }
    
    /**
     * Retorna uma lista dado uma Named Query válida.
     *
     * @param  namedQuery
     *         Nome da Named Query a ser executada.
     * 
     * @param  firstResult
     *         De todos os elementos de resultantes, inicia uma lista de retorno a partir de um índice especificado.
     * 
     * @param  maxResults
     *         De todos os elementos de resultantes, define um conjunto de registros para a lista de retorno.
     *
     * @param  params
     *         Lista com os parâmetros da NamedQuery.
     *
     * @return Uma lista de entidades.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public List findByNamedQuery(String namedQuery, int firstResult, int maxResults, Object ... params) throws VexPersistenceException {
        
        List result = null;

        try {
            
            beforeFindByNamedQuery();
            
            try {
                Query q = getEntityManager().createNamedQuery(namedQuery);

                if ( (firstResult > -1) && (maxResults > -1) ) {                    
                    q.setFirstResult(firstResult);
                    q.setMaxResults(maxResults);
                }

                for (int i = 0; i < params.length; i++) {
                    q.setParameter(i + 1, params[i]);
                }
                
                result = q.getResultList();
                
            } catch (Exception e) {

                e.printStackTrace();

                throw new VexPersistenceException(e);
            }

            return result;
            
        } finally {
            
            if (keepSessionAlive) {
                getPersistenceConnection().closeEntityManager();
            }
            
            afterFindByNamedQuery();
        }
        
    }
    
    /**
     * Retorna uma lista dado uma Named Query válida.
     *
     * @param  namedQuery
     *         Nome da Named Query a ser executada.
     * 
     * @param  firstResult
     *         De todos os elementos de resultantes, inicia uma lista de retorno a partir de um índice especificado.
     * 
     * @param  maxResults
     *         De todos os elementos de resultantes, define um conjunto de registros para a lista de retorno.
     *
     * @param  params
     *         Lista com os nomes e valores dos parâmetros da NamedQuery.
     *
     * @return Uma lista de entidades.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public List findByNamedQuery(String namedQuery, int firstResult, int maxResults, Map<String, Object> params) throws VexPersistenceException {
        
        List result = null;

        try {
            
            beforeFindByNamedQuery();
            
            try {
                Query q = getEntityManager().createNamedQuery(namedQuery);

                if ( (firstResult > -1) && (maxResults > -1) ) {                    
                    q.setFirstResult(firstResult);
                    q.setMaxResults(maxResults);
                }

                int i = 0;
                
                while (i < params.size()) {
                    String key = (String) params.keySet().toArray()[i];

                    if (key.equals("teste")) {
                        params.remove(key);
                    } else {
                        i++;
                    }
                }
                
                for (String key: params.keySet()) {
                    q.setParameter(key, params.get(key));
                }
                
                result = q.getResultList();
                
            } catch (Exception e) {

                e.printStackTrace();

                throw new VexPersistenceException(e);
            }

            return result;
            
        } finally {
            
            if (keepSessionAlive) {
                getPersistenceConnection().closeEntityManager();
            }
            
            afterFindByNamedQuery();
        }
        
    }
    
    /**
     * Executa uma Named Query válida.
     *
     * @param  namedQuery
     *         Nome da Named Query a ser executada.
     * 
     * @param  params
     *         Lista com os parâmetros da NamedQuery.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public void execByNamedQuery(String namedQuery, Object ... params) throws VexPersistenceException {

        try {
            
            beforeExecByNamedQuery();
            
            startTransaction();
            
            try {
                Query q = getEntityManager().createNamedQuery(namedQuery);
                
                for (int i = 0; i < params.length; i++) {
                    q.setParameter(i + 1, params[i]);
                }
                
                q.executeUpdate();
                
                commitTransaction();
                
            } catch (Exception e) {

                e.printStackTrace();
                
                rollbackTransaction();

                throw new VexPersistenceException(e);
            }
            
        } finally {
            
            if (keepSessionAlive) {
                getPersistenceConnection().closeEntityManager();
            }
            
            afterExecByNamedQuery();
        }
        
    }
    
    /**
     * Executa uma Named Query válida.
     *
     * @param  namedQuery
     *         Nome da Named Query a ser executada.
     * 
     * @param  params
     *         Lista com os nomes e valores dos parâmetros da NamedQuery.
     *
     * @return Uma lista de entidades.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public void execByNamedQuery(String namedQuery, Map<String, Object> params) throws VexPersistenceException {

        try {
            
            beforeExecByNamedQuery();
            
            startTransaction();
            
            try {
                Query q = getEntityManager().createNamedQuery(namedQuery);
                
                for (String key: params.keySet()) {
                    q.setParameter(key, params.get(key));
                }
                
                q.executeUpdate();
                
                commitTransaction();
                
            } catch (Exception e) {

                e.printStackTrace();
                
                rollbackTransaction();

                throw new VexPersistenceException(e);
            }
            
        } finally {
            
            if (keepSessionAlive) {
                getPersistenceConnection().closeEntityManager();
            }
            
            afterExecByNamedQuery();
        }
        
    }
    
    /*
     * Event handler methods - call backs methods
     */
    /**
     * Callback Method executado antes de ser feita uma busca através de uma Named Query válida.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void beforeFindByNamedQuery();
    
    /**
     * Callback Method executado após ser feita uma busca através de uma Named Query válida.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void afterFindByNamedQuery();
    
    /**
     * Callback Method executado antes de ser feita a execução de uma Named Query válida.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void beforeExecByNamedQuery();
    
    /**
     * Callback Method executado após ser feita a execução de uma Named Query válida.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void afterExecByNamedQuery();
    
}