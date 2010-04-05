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

package br.com.vexit.vexpersistence.conn.factory;

import br.com.vexit.vexpersistence.conn.PersistenceConnection;
import java.io.Serializable;

/**
 * <tt>PersistenceConnectionFactory</tt> é uma Factory Method
 * responsável por disponibilizar uma instância de uma
 * {@link PersistenceConnection <tt>PersistenceConnection</tt>}.
 *
 * @version 1.4.1, 05/15/08
 *
 * @see PersistenceConnection
 *
 * @since 1.4.1
 *
 * @author Roberto Vezzoni
 */
public class PersistenceConnectionFactory implements Serializable {

    private static PersistenceConnection persistenceConnection;
    
    /**
     * Retorna uma instância de uma {@link PersistenceConnection <tt>PersistenceConnection</tt>}
     * para operações persistentes.
     *
     * @return Uma {@link PersistenceConnection <tt>PersistenceConnection</tt>}.
     *
     * @param persistenceUnitName
     *        Nome de uma Persistence Unit válida para operações persistentes.
     *
     * @since  1.4.1
     *
     * @author Roberto Vezzoni
     */
    public static PersistenceConnection createPersistenceConnection(String persistenceUnitName) {

        if (PersistenceConnectionFactory.persistenceConnection == null) {
            PersistenceConnectionFactory.persistenceConnection = PersistenceConnection.getInstance(persistenceUnitName);
        }

        return persistenceConnection;
    }
    
}
