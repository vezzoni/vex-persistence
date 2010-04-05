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

import br.com.vexit.vexpersistence.namedquery.impl.NamedQueryInjectedSession;
import javax.persistence.EntityManager;

/**
 * <tt>NamedQueryInjectedSessionHandler</tt> é uma classe abstrata responsável por
 * implementar métodos genéricos para uma
 * {@link NamedQueryInjectedSessionHandler <tt>NamedQueryInjectedSessionHandler concreta</tt>}.
 *
 * @version 1.4, 25/11/08
 *
 * @see NamedQueryIntf
 * @see NamedQueryInjectedSession
 *
 * @since 1.4
 *
 * @author Roberto Vezzoni
 */
public class NamedQueryInjectedSessionHandler extends NamedQueryInjectedSession {
    
    /**
     * Cria uma instância de uma Named Query passando
     * o sessão obtida por Dependency Injection.
     *
     * @param em
     *        Uma EntityManager válida.
     *
     * @since  1.4
     *
     * @author Roberto Vezzoni
     */
    public NamedQueryInjectedSessionHandler(EntityManager em) {
        super(em);
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