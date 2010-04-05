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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * <tt>LocalPersistenceConnection</tt> é uma classe concreta responsável por
 * manter uma conexão e fornecer caminhos para operações persistentes quando a
 * conexão se der como recurso local, ou seja, quando a conexão não foi obtida
 * através de Dependency Injection.
 *
 * @version 1.4.3, 28/10/09
 * 
 * @see PersistentFacade
 * 
 * @since 1.4
 *
 * @author Roberto Vezzoni
 */
public class LocalPersistenceConnection extends PersistenceConnection {
    
    private String persistenceUnitName;
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    /**
     * Creates a new instance of PersistenceConnection
     */
    public LocalPersistenceConnection(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }
    
    /**
     * Inicia uma conexão com a fonte de dados.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    private void init() {
        
        try {
            
            // inicialização da conexão com a fonte de dados.
            emf = Persistence.createEntityManagerFactory( persistenceUnitName );

        } catch (Throwable t) {

            t.printStackTrace();

            emf = null;
        }
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
    public EntityManager getEntityManager() {
        
        if (emf == null) {
            init();
        }

        if ( (emf != null) && ( (em == null) || (!em.isOpen()) ) ) {
            em = emf.createEntityManager();
            emf.close();
        }

        return em;
    }
    
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
    public void closeEntityManager() {
        if (em.isOpen()) {
            em.close();
        }
    }
    
    /**
     * Inicia uma transação com o banco de dados corrente.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public void startTransaction() {
        getEntityManager().getTransaction().begin();
    }
    
    /**
     * Confirma uma transação já iniciada com o banco de dados corrente.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public void commit() {
        if (getEntityManager().getTransaction().isActive()) {
            getEntityManager().getTransaction().commit();
        }
    }
    
    /**
     * Cancela uma transação já iniciada com o banco de dados corrente.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public void rollback() {
        // cancela uma transação iniciada com o banco de dados corrente.
        if (getEntityManager().getTransaction().isActive()) {
            getEntityManager().getTransaction().rollback();
        }
    }
    
    /**
     * Desliga a conexão banco de dados corrente.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public void shutdown() {
        // fecha a fábrica de conexão.
        if (emf != null) {
            emf.close();
        }
    }
    
}