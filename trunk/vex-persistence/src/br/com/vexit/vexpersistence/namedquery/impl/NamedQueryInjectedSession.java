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

import br.com.vexit.vexpersistence.exception.VexPersistenceException;
import br.com.vexit.vexpersistence.namedquery.intf.NamedQueryIntf;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * <tt>NamedQueryInjectedSession</tt> é uma classe abstrata responsável por
 * implementar métodos genéricos para uma
 * {@link NamedQueryInjectedSessionHandler <tt>NamedQueryInjectedSessionHandler concreta</tt>}.
 *
 * @version 1.4.1, 05/12/08
 *
 * @see NamedQueryIntf
 * @see NamedQueryInjectedSessionHandler
 * @see VexPersistenceException
 * 
 * @since 1.4
 *
 * @author Roberto Vezzoni
 */
public abstract class NamedQueryInjectedSession implements NamedQueryIntf {

    private EntityManager em;
    
    /**
     * Cria uma instância de uma Named Query passando
     * o sessão obtida por Dependency Injection.
     *
     * @param em
     *        Uma EntityManager válida.
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    public NamedQueryInjectedSession(EntityManager em) {
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
            
            try {
                Query q = getEntityManager().createNamedQuery(namedQuery);
                
                for (int i = 0; i < params.length; i++) {
                    q.setParameter(i + 1, params[i]);
                }
                
                q.executeUpdate();
                
            } catch (Exception e) {

                e.printStackTrace();

                throw new VexPersistenceException(e);
            }
            
        } finally {
            
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
            
            try {
                Query q = getEntityManager().createNamedQuery(namedQuery);
                
                for (String key: params.keySet()){
                    q.setParameter(key, params.get(key));
        	}
                
                q.executeUpdate();
                
            } catch (Exception e) {

                e.printStackTrace();

                throw new VexPersistenceException(e);
            }
            
        } finally {
            
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