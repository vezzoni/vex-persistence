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

package br.com.vexit.vexpersistence.dao;

import br.com.vexit.vexpersistence.dao.impl.DAOLocalSession;
import br.com.vexit.vexpersistence.dao.intf.DAOIntf;

/**
 * <tt>DAOLocalSessionHandler</tt> é uma classe concreta responsável por
 * uma instância para um {@link DAOIntf <tt>DAO concreto</tt>}.
 *
 * @version 1.4, 25/11/08
 *
 * @see DAOIntf
 * @see DAOLocalSession
 *
 * @since 1.4
 *
 * @author Roberto Vezzoni
 */
public class DAOLocalSessionHandler extends DAOLocalSession {
    
    /**
     * Cria uma instância de um Data Access Object passando
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
    public DAOLocalSessionHandler(String persistenceUnitName, boolean keepSessionAlive) {
        super(persistenceUnitName, keepSessionAlive);
    }

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
    public void beforeGetAll(Class clazz) {
    }

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
    public void afterGetAll(Class clazz) {
    }

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
    public void beforeGet(Class clazz, Object id) {
    }

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
    public void afterGet(Class clazz, Object id) {
    }

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
    public void beforeSave(Object entity) {
    }

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
    public void afterSave(Object entity) {
    }

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
    public void beforeCancel(Object entity) {
    }

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
    public void afterCancel(Object entity) {
    }

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
    public void beforeRemove(Object entity) {
    }

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
    public void afterRemove(Object entity) {
    }

}