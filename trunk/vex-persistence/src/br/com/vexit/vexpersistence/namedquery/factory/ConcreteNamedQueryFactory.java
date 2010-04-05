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

package br.com.vexit.vexpersistence.namedquery.factory;

import br.com.vexit.vexpersistence.namedquery.NamedQueryInjectedSessionHandler;
import br.com.vexit.vexpersistence.namedquery.NamedQueryLocalSessionHandler;
import br.com.vexit.vexpersistence.namedquery.intf.NamedQueryIntf;
import javax.persistence.EntityManager;

/**
 * <tt>ConcreteNamedQueryFactory</tt> é uma classe concreta que representa uma Factory Method
 * responsável por disponibilizar uma instância de uma {@link NamedQueryIntf <tt>Named Query genérica</tt>}.
 *
 * @version 1.4.2, 01/07/09
 *
 * @see NamedQueryFactory
 * @see NamedQueryIntf
 *
 * @since 1.4
 *
 * @author Roberto Vezzoni
 */
public class ConcreteNamedQueryFactory extends NamedQueryFactory {

    private static ThreadLocal<ConcreteNamedQueryFactory> factory = new ThreadLocal<ConcreteNamedQueryFactory>() {

        @Override
        public ConcreteNamedQueryFactory initialValue() {
            return new ConcreteNamedQueryFactory();
        }
    };

    private NamedQueryIntf namedQuery;
    
    private static String aPersistenceUnitName;
    private static EntityManager aEM;

    private ConcreteNamedQueryFactory() {

    }

    /**
     * Retorna uma instância (Singleton) de uma {@link ConcreteNamedQueryFactory <tt>Factory Method</tt>}
     * para Named Queries a partir de uma conexão local.
     *
     * @return Uma {@link ConcreteNamedQueryFactory <tt>Factory Method</tt>} para Named Queries.
     *
     * @param persistenceUnitName
     *        Nome de uma Persistence Unit válida para operações persistentes.
     *
     * @see #getInstance(javax.persistence.EntityManager)
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    public static ConcreteNamedQueryFactory getInstance(String persistenceUnitName) {
        ConcreteNamedQueryFactory.aPersistenceUnitName = ( (persistenceUnitName != null) ? persistenceUnitName : "" );
        ConcreteNamedQueryFactory.aEM = null;

        return getInstance();
    }

    /**
     * Retorna uma instância (Singleton) de uma {@link ConcreteNamedQueryFactory <tt>Factory Method</tt>}
     * para Named Queries a partir de uma conexão obtida por Dependency Injection.
     *
     * @return Uma {@link ConcreteNamedQueryFactory <tt>Factory Method</tt>} para Named Queries.
     *
     * @param em
     *        Instância de uma EntityManager válida para operações persistentes.
     *
     * @see #getInstance(java.lang.String)
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    public static ConcreteNamedQueryFactory getInstance(EntityManager em) {
        ConcreteNamedQueryFactory.aEM = em;

        return getInstance();
    }

    private static ConcreteNamedQueryFactory getInstance() {

        return ConcreteNamedQueryFactory.factory.get();
    }

    /**
     * Retorna uma instância de uma {@link NamedQueryIntf <tt>Named Query</tt>} para operações persistentes.
     *
     * @return Uma {@link NamedQueryIntf <tt>Named Query</tt>} para operações persistentes.
     *
     * @see #createNamedQuery(boolean)
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    @Override
    public NamedQueryIntf createNamedQuery() {
        return createNamedQuery(false);
    }

    /**
     * Retorna uma instância de uma {@link NamedQueryIntf <tt>Named Query</tt>} para operações persistentes.
     *
     * @return Uma {@link NamedQueryIntf <tt>Named Query</tt>} para operações persistentes.
     *
     * @param keepSessionAlive
     *        true - Mantém a sessão com o banco de dados ativa.
     *        false - Fecha a sessão com banco de dados.
     *
     * @see #createNamedQuery()
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    @Override
    public NamedQueryIntf createNamedQuery(boolean keepSessionAlive) {

        try {

            if (ConcreteNamedQueryFactory.aEM == null) {
                namedQuery = new NamedQueryLocalSessionHandler(ConcreteNamedQueryFactory.aPersistenceUnitName, keepSessionAlive);
            } else {
                namedQuery = new NamedQueryInjectedSessionHandler(ConcreteNamedQueryFactory.aEM);
            }

        } finally {
            return namedQuery;
        }

    }

}