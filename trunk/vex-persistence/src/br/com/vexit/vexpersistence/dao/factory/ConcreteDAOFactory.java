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

package br.com.vexit.vexpersistence.dao.factory;

import br.com.vexit.vexpersistence.dao.DAOInjectedSessionHandler;
import br.com.vexit.vexpersistence.dao.DAOLocalSessionHandler;
import br.com.vexit.vexpersistence.dao.intf.DAOIntf;
import javax.persistence.EntityManager;

/**
 * <tt>ConcreteDAOFactory</tt> é uma classe concreta que representa uma Factory Method
 * responsável por disponibilizar uma instância de um {@link DAOIntf <tt>DAO genérico</tt>}.
 *
 * @version 1.4.2, 01/07/09
 *
 * @see DAOFactory
 * @see DAOIntf
 *
 * @since 1.0
 *
 * @author Roberto Vezzoni
 */
public class ConcreteDAOFactory extends DAOFactory {

    private static ThreadLocal<ConcreteDAOFactory> factory = new ThreadLocal<ConcreteDAOFactory>() {
        
        @Override
        public ConcreteDAOFactory initialValue() {
            return new ConcreteDAOFactory();
        }
    };

    private DAOIntf dao;
    
    private static String aPersistenceUnitName;
    private static EntityManager aEM;

    private ConcreteDAOFactory() {

    }

    /**
     * Retorna uma instância (Singleton) de uma {@link ConcreteDAOFactory <tt>Factory Method</tt>}
     * para Data Access Objects a partir de uma conexão local.
     *
     * @return Uma {@link ConcreteDAOFactory <tt>Factory Method</tt>} para Data Access Objects.
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
    public static ConcreteDAOFactory getInstance(String persistenceUnitName) {
        
        ConcreteDAOFactory.aPersistenceUnitName = ( (persistenceUnitName != null) ? persistenceUnitName : "" );
        ConcreteDAOFactory.aEM = null;

        return getInstance();
    }

    /**
     * Retorna uma instância (Singleton) de uma {@link ConcreteDAOFactory <tt>Factory Method</tt>}
     * para Data Access Objects a partir de uma conexão obtida por Dependency Injection.
     *
     * @return Uma {@link ConcreteDAOFactory <tt>Factory Method</tt>} para Data Access Objects.
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
    public static ConcreteDAOFactory getInstance(EntityManager em) {
        ConcreteDAOFactory.aEM = em;

        return getInstance();
    }

    private static ConcreteDAOFactory getInstance() {

        return ConcreteDAOFactory.factory.get();
    }

    /**
     * Retorna uma instância de uma {@link DAOIntf <tt>DAO</tt>} para operações persistentes.
     *
     * @return Um DAO para operações persistentes.
     *
     * @see #createDAO(boolean)
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    @Override
    public DAOIntf createDAO() {
        return createDAO(false);
    }

    /**
     * Retorna uma instância de uma {@link DAOIntf <tt>DAO</tt>} para operações persistentes.
     *
     * @return Um DAO para operações persistentes.
     *
     * @param keepSessionAlive
     *        true - Mantém a sessão com o banco de dados ativa.
     *        false - Fecha a sessão com banco de dados.
     *
     * @see #createDAO()
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    @Override
    public DAOIntf createDAO(boolean keepSessionAlive) {

        try {

            if (ConcreteDAOFactory.aEM == null) {
                dao = new DAOLocalSessionHandler(ConcreteDAOFactory.aPersistenceUnitName, keepSessionAlive);
            } else {
                dao = new DAOInjectedSessionHandler(ConcreteDAOFactory.aEM);
            }

        } finally {
            return dao;
        }

    }

}