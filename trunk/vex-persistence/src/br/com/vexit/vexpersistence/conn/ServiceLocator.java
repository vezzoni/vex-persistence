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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <tt>ServiceLocator</tt> é uma classe concreta responsável por
 * disponibilizar instâncias de {@link LocalPersistenceConnection <tt>LocalPersistenceConnection</tt>}.
 *
 * @version 1.4, 25/11/08
 *
 * @see LocalPersistenceConnection
 *
 * @since 1.4
 *
 * @author Roberto Vezzoni
 */
public class ServiceLocator implements Serializable {

    private Map<String, LocalPersistenceConnection> connections = new HashMap<String, LocalPersistenceConnection>();

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
    public LocalPersistenceConnection getPersistenceConnection(String persistenceUnitName) {

        LocalPersistenceConnection conn = lookup(persistenceUnitName);
        
        if (conn == null) {
            conn = addConnection(persistenceUnitName);
        }

        return conn;
    }

    private LocalPersistenceConnection lookup(String persistenceUnitName) {

        LocalPersistenceConnection conn = null;
        
        if (connections.containsKey(persistenceUnitName)) {
            conn = connections.get(persistenceUnitName);
        }
        
        return conn;
    }

    private LocalPersistenceConnection addConnection(String persistenceUnitName) {
        
        LocalPersistenceConnection conn = new LocalPersistenceConnection(persistenceUnitName);

        connections.put(persistenceUnitName, conn);

        return conn;
    }
    
}
