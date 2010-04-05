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

package br.com.vexit.vexpersistence.namedquery;

import br.com.vexit.vexpersistence.namedquery.impl.NamedQueryLocalSession;
import br.com.vexit.vexpersistence.namedquery.intf.NamedQueryIntf;

/**
 * <tt>NamedQueryLocalSessionHandler</tt> é uma classe concreta responsável por
 * uma instância para uma {@link DAOIntf <tt>Named Query concreta</tt>}.
 *
 * @version 1.4, 25/11/08
 *
 * @see NamedQueryIntf
 * @see NamedQueryLocalSession
 *
 * @since 1.4
 *
 * @author Roberto Vezzoni
 */
public class NamedQueryLocalSessionHandler extends NamedQueryLocalSession {
    
    /**
     * Cria uma instância de uma Named Query passando
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
    public NamedQueryLocalSessionHandler(String persistenceUnitName, boolean keepSessionAlive) {
        super(persistenceUnitName, keepSessionAlive);
    }

    /**
     * Callback Method executado antes de ser feita uma busca através de uma Named Query válida.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public void beforeFindByNamedQuery() {
    }

    /**
     * Callback Method executado após ser feita uma busca através de uma Named Query válida.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public void afterFindByNamedQuery() {
    }

    /**
     * Callback Method executado antes de ser feita a execução de uma Named Query válida.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public void beforeExecByNamedQuery() {
    }

    /**
     * Callback Method executado após ser feita a execução de uma Named Query válida.
     *
     * @since  1.0
     *
     * @author Roberto Vezzoni
     */
    public void afterExecByNamedQuery() {
    }
    
}