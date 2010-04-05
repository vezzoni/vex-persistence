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

package br.com.vexit.vexpersistence;

import br.com.vexit.vexpersistence.conn.LocalPersistenceConnection;
import br.com.vexit.vexpersistence.conn.PersistenceConnection;
import br.com.vexit.vexpersistence.conn.factory.PersistenceConnectionFactory;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 * <tt>PersistentFacade</tt> é uma classe abstrata que atua como fachada que
 * é responsável por fornecer acesso padrão nesta API.
 *
 * @version 1.4.1, 05/12/08
 *
 * @see PersistentSessionFacade
 * @see PersistenceConnection
 * 
 * @since 1.0
 *
 * @author Roberto Vezzoni
 */
public abstract class PersistentFacade implements Serializable {

    /**
     * Retorna uma instância de uma {@link PersistentSessionFacade <tt>Façade</tt>} para operações persistentes.
     * <p>
     * Ao utilizar mais de uma conexão (Persistence Units diferentes) implica em declarar explicitamente
     * no arquivo <tt>persistence.xml</tt> as classes (Entity Beans) que devem ser carregadas.
     *
     * @return Uma {@link PersistentSessionFacade <tt>Façade</tt>} para operações persistentes.
     *
     * @param persistenceUnitName
     *        Nome de uma Persistence Unit válida para operações persistentes.
     *
     * @since  1.4.0
     *
     * @author Roberto Vezzoni
     */
    public static PersistentSessionFacade getInstance(String persistenceUnitName) {

        return LocalSessionFacade.getInstance( persistenceUnitName );
    }

    /**
     * Retorna uma instância de uma {@link PersistentFacade <tt>Façade</tt>} para operações persistentes.
     *
     * @return Uma {@link PersistentSessionFacade <tt>Façade</tt>} para operações persistentes.
     *
     * @param em
     *        Instância de uma EntityManager válida para operações persistentes.
     *
     * @since  1.4.0
     *
     * @author Roberto Vezzoni
     */
    public static PersistentSessionFacade getInstance(EntityManager em) {

        return InjectedSessionFacade.getInstance( em );
    }

    /**
     * Retorna uma instância de uma {@link LocalPersistenceConnection <tt>conexão local</tt>} para operações persistentes.
     * <p>
     * Com essa instância de uma {@link LocalPersistenceConnection <tt>conexão local</tt>} é possível alcançar
     * os métodos de trabalho com operações persistentes.
     *
     * @return Uma {@link PersistenceConnection <tt>PersistenceConnection</tt>}.
     *
     * @param persistenceUnitName
     *        Nome de uma Persistence Unit válida para operações persistentes.
     *
     * @since  1.4.0
     *
     * @author Roberto Vezzoni
     */
    public static PersistenceConnection getPersistenceConnection(String persistenceUnitName) {

        return PersistenceConnectionFactory.createPersistenceConnection(persistenceUnitName);
    }

}