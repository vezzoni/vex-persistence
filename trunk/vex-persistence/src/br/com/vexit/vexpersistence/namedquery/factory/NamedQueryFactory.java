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

import br.com.vexit.vexpersistence.namedquery.intf.NamedQueryIntf;
import java.io.Serializable;

/**
 * <tt>NamedQueryFactory</tt> é uma classe abstrata que representa uma Factory Method
 * responsável por disponibilizar uma instância de uma {@link NamedQueryIntf <tt>Named Query genérica</tt>}.
 *
 * @version 1.4, 25/11/08
 *
 * @see ConcreteNamedQueryFactory
 * @see NamedQueryIntf
 *
 * @since 1.0
 *
 * @author Roberto Vezzoni
 */
public abstract class NamedQueryFactory implements Serializable {

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
    public abstract NamedQueryIntf createNamedQuery();

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
    public abstract NamedQueryIntf createNamedQuery(boolean keepSessionAlive);

}
