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

package br.com.vexit.vexpersistence.namedquery.intf;

import br.com.vexit.vexpersistence.exception.VexPersistenceException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <tt>NamedQueryIntf</tt> é uma interface responsável por listar quais métodos uma
 * Named Query concreta pode executar.
 *
 * @version 1.4.1, 05/12/08
 *
 * @see NamedQueryLocalSession
 * @see NamedQueryInjectedSession
 * @see VexPersistenceException
 *
 * @since 1.0
 *
 * @author Roberto Vezzoni
 */
public interface NamedQueryIntf extends Serializable {
    
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
    List findByNamedQuery(String namedQuery, Object ... params) throws VexPersistenceException;
        
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
    List findByNamedQuery(String namedQuery, Map<String, Object> params) throws VexPersistenceException;
    
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
     *         Lista com os valores dos parâmetros da NamedQuery.
     *
     * @return Uma lista de entidades.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    List findByNamedQuery(String namedQuery, int firstResult, int maxResults, Object ... params) throws VexPersistenceException;
        
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
    List findByNamedQuery(String namedQuery, int firstResult, int maxResults, Map<String, Object> params) throws VexPersistenceException;
    
    /**
     * Executa uma Named Query válida.
     *
     * @param  namedQuery
     *         Nome da Named Query a ser executada.
     * 
     * @param  params
     *         Lista com os valores dos parâmetros da NamedQuery.
     *
     * @return Uma lista de entidades.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    void execByNamedQuery(String namedQuery, Object ... params) throws VexPersistenceException;
    
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
    void execByNamedQuery(String namedQuery, Map<String, Object> params) throws VexPersistenceException;
    
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
    void beforeFindByNamedQuery();
    
    /**
     * Callback Method executado após ser feita uma busca através de uma Named Query válida.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    void afterFindByNamedQuery();
    
    /**
     * Callback Method executado antes de ser feita a execução de uma Named Query válida.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    void beforeExecByNamedQuery();
    
    /**
     * Callback Method executado após ser feita a execução de uma Named Query válida.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    void afterExecByNamedQuery();
    
}