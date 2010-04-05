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

import br.com.vexit.vexpersistence.dao.factory.DAOFactory;
import br.com.vexit.vexpersistence.namedquery.factory.NamedQueryFactory;
import br.com.vexit.vexpersistence.storedprocedure.factory.StoredProcedureFactory;
import java.io.Serializable;
import java.sql.Connection;

/**
 * <tt>PersistentSessionFacade</tt> é uma classe abstrata responsável por
 * fornecer uma interface simplificada para acesso a Factory Methods.
 * <p>
 * Data Access Object:
 *
 * <blockquote><pre>
 *   final String PERSISTENCE_UNIT_NAME = "persistenceUnitName";
 *   Entity foo; // it must implements java.util.Serializable interface
 *
 *   foo = PersistentFacade
 *              .getInstance( PERSISTENCE_UNIT_NAME )
 *              .getDAOFactory()
 *              .createDAO()
 *              .get( Entity.class, someValidObjectID );
 *
 *   Entity bar;
 *
 *   bar = PersistentFacade
 *              .getInstance( PERSISTENCE_UNIT_NAME )
 *              .getDAOFactory()
 *              .createDAO()
 *              .save( boo );
 * </pre></blockquote>
 *
 * Named Queries:
 *
 * <blockquote><pre>
 *   final String PERSISTENCE_UNIT_NAME = "persistenceUnitName";
 * 
 *   List foo = PersistentFacade
 *              .getInstance( PERSISTENCE_UNIT_NAME )
 *              .getNamedQueryFactory()
 *              .createNamedQuery()
 *              .findByNamedQuery( "Entity.findByProper", arg1, arg2 );
 *
 *   List bar = PersistentFacade
 *              .getInstance( PERSISTENCE_UNIT_NAME )
 *              .getNamedQueryFactory()
 *              .createNamedQuery()
 *              .findByNamedQuery( "Entity.findAll" );
 *
 *   PersistentFacade
 *       .getInstance( PERSISTENCE_UNIT_NAME )
 *       .getNamedQueryFactory()
 *       .createNamedQuery()
 *       .execByNamedQuery( "Entity.removeSet", arg);
 * </pre></blockquote>
 *
 * Stored Procedures:
 *
 * <blockquote><pre>
 *   final String PERSISTENCE_UNIT_NAME = "persistenceUnitName";
 *
 *   java.sql.Connection conn = getSomeValidConnection();
 * 
 *   ResultSet rs = PersistentFacade
 *              .getInstance( PERSISTENCE_UNIT_NAME )
 *              .getStoredProcedureFactory( conn )
 *              .createStoredProcedure()
 *              .getByStoredProcedure( "storedProcedureName", arg1, arg2 );
 *
 *   PersistentFacade
 *       .getInstance( PERSISTENCE_UNIT_NAME )
 *       .getStoredProcedureFactory( conn )
 *       .createStoredProcedure()
 *       .execStoredProcedure( "storedProcedureName", arg1, arg2 );
 * </pre></blockquote>
 *
 * @version 1.4, 25/11/08
 *
 * @see DAOFactory
 * @see NamedQueryFactory
 * @see StoredProcedureFactory
 *
 * @since 1.4
 *
 * @author Roberto Vezzoni
 */
public abstract class PersistentSessionFacade implements Serializable {

    /**
     * Retorna uma instância de uma {@link DAOFactory <tt>Factory Method</tt>} para Data Access Objects.
     *
     * @return Uma Factory Method para Data Access Objects.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract DAOFactory getDAOFactory();

    /**
     * Retorna uma instância de uma {@link NamedQueryFactory <tt>Factory Method</tt>} para Named Queries.
     *
     * @return Uma Factory Method para Named Queries.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public abstract NamedQueryFactory getNamedQueryFactory();

    /**
     * Retorna uma instância de uma {@link StoredProcedureFactory <tt>Factory Method</tt>} para Stored Procedures.
     *
     * @return Uma Factory Method para Stored Procedures.
     *
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public abstract StoredProcedureFactory getStoredProcedureFactory(Connection conn);

}