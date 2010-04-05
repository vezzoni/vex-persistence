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

package br.com.vexit.vexpersistence.dao.intf;

import br.com.vexit.vexpersistence.exception.VexPersistenceException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * <tt>DAOIntf</tt> é uma interface responsável por listar quais métodos um 
 * DAO concreto pode executar.
 *
 * @version 1.4.1, 05/12/08
 * 
 * @see DAOLocalSession
 * @see DAOInjectedSession
 * @see VexPersistenceException
 * 
 * @since 1.0
 *
 * @author Roberto Vezzoni
 */
public interface DAOIntf extends Serializable {
    
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
     * field
     * Lista de atributos a serem utilizados para ordenamento da lista.
     *
     * @return Uma lista de entidades.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    <T extends Serializable> List<T> getAll(Class<T> clazz, Field ... fields) throws VexPersistenceException;

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
    <T extends Serializable> T get(Class<T> clazz, Object id) throws VexPersistenceException;
    
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
    <T extends Serializable> T save(T entity) throws VexPersistenceException;
    
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
    <T extends Serializable> void cancel(T entity);
    
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
    <T extends Serializable> void refresh(final T entity) throws VexPersistenceException;

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
    <T extends Serializable> boolean remove(T entity) throws VexPersistenceException;
    
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
    void beforeGetAll(Class clazz);
    
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
    void afterGetAll(Class clazz);
    
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
    void beforeGet(Class clazz, Object id);
    
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
    void afterGet(Class clazz, Object id);
    
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
    void beforeSave(Object entity);
    
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
    void afterSave(Object entity);
    
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
    void beforeCancel(Object entity);
    
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
    void afterCancel(Object entity);
    
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
    void beforeRemove(Object entity);
    
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
    void afterRemove(Object entity);
    
}