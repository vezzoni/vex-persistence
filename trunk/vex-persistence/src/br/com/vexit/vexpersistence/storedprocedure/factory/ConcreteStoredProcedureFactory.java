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

package br.com.vexit.vexpersistence.storedprocedure.factory;

import br.com.vexit.vexpersistence.storedprocedure.StoredProcedureHandler;
import br.com.vexit.vexpersistence.storedprocedure.intf.StoredProcedureIntf;
import java.sql.Connection;

/**
 * <tt>StoredProcedureFactory</tt> é uma Factory Method responsável por 
 * disponibilizar uma instância de uma {@link StoredProcedureHandler <tt>classe genérica</tt>} capaz de executar Stored Procedures.
 *
 * @version  1.2, 01/07/09
 * @see      #getInstance
 * @since 1.1
 *
 * @author Roberto Vezzoni
 */
public class ConcreteStoredProcedureFactory extends StoredProcedureFactory {

    private static ThreadLocal<ConcreteStoredProcedureFactory> factory = new ThreadLocal<ConcreteStoredProcedureFactory>() {

        @Override
        public ConcreteStoredProcedureFactory initialValue() {
            return new ConcreteStoredProcedureFactory();
        }
    };

    private static Connection aConn;

    private StoredProcedureIntf storedProcedure;

    private ConcreteStoredProcedureFactory() {

    }

    /**
     * Retorna uma instância (Singleton) de uma {@link ConcreteStoredProcedureFactory <tt>Factory Method</tt>}
     * para Stored Procedures a partir de uma conexão obtida por Dependency Injection.
     *
     * @return Uma {@link ConcreteStoredProcedureFactory <tt>Factory Method</tt>} para Stored Procedures.
     *
     * @param conn
     *        Instância de uma java.sql.Connection válida para operações persistentes.
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    public static ConcreteStoredProcedureFactory getInstance(Connection conn) {
        ConcreteStoredProcedureFactory.aConn = conn;

        return ConcreteStoredProcedureFactory.factory.get();
    }

    /**
     * Cria uma instância de uma Named Query passando
     * a forma de tratamento da sessão com o banco de dados.
     *
     * @param keepSessionAlive
     *        true - Mantém a sessão com o banco de dados ativa.
     *        false - Fecha a sessão com banco de dados.
     *
     * @see    #createStoredProcedure(javax.persistence.EntityManager)
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    @Override
    public StoredProcedureIntf createStoredProcedure() {
        storedProcedure = new StoredProcedureHandler( ConcreteStoredProcedureFactory.aConn );
        return storedProcedure;
    }

}