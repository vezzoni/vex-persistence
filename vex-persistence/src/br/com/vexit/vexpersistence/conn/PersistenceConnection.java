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

package br.com.vexit.vexpersistence.conn;

import br.com.vexit.vexpersistence.PersistentFacade;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 * <tt>PersistenceConnection</tt> é uma classe abstrata responsável por
 * manter uma conexão e fornecer caminhos para operações persistentes quando a
 * conexão se der como recurso local, ou seja, quando a conexão não foi obtida
 * através de Injeção de Dependência.
 *
 * @version  1.4, 25/11/08
 * 
 * @see PersistentFacade
 * @see LocalPersistenceConnection
 * @see ServiceLocator
 * 
 * @since 1.0
 *
 * @author Roberto Vezzoni
 */
public abstract class PersistenceConnection implements Serializable {

    private static ServiceLocator locator;

    private static ServiceLocator getLocator() {

        if (locator == null) {
            locator = new ServiceLocator();
        }

        return locator;
    }

    /**
     * Retorna uma instância de uma {@link LocalPersistenceConnection <tt>conexão local</tt>} para operações persistentes.
     * <p>
     * Com essa instância de uma {@link LocalPersistenceConnection <tt>conexão local</tt>} é possível alcançar
     * os métodos de trabalho com operações persistentes.

     *
     * @return Uma {@link LocalPersistenceConnection <tt>conexão local</tt>} para operações persistentes.
     *
     * @param persistenceUnitName
     *        Nome de uma Persistence Unit válida para operações persistentes.
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    public static PersistenceConnection getInstance(String persistenceUnitName) {

        return getLocator().getPersistenceConnection(persistenceUnitName);
    }

    /**
     * Retorna uma instância de uma {@link EntityManager <tt>EntityManager</tt>}
     * responsável por disponibilizar operações sobre banco de dados.
     *
     * @return EntityManager.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract EntityManager getEntityManager();

    /**
     * Fecha a instância ativa de uma {@link EntityManager <tt>EntityManager</tt>}
     * responsável por disponibilizar operações sobre banco de dados.
     *
     * @return EntityManager.
     *
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public abstract void closeEntityManager();

    /**
     * Inicia uma transação com o banco de dados corrente.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void startTransaction();

    /**
     * Confirma uma transação já iniciada com o banco de dados corrente.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void commit();

    /**
     * Cancela uma transação já iniciada com o banco de dados corrente.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void rollback();

    /**
     * Desliga a conexão banco de dados corrente.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract void shutdown();
   
}