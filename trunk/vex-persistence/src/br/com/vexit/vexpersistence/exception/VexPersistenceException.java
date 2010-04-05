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

package br.com.vexit.vexpersistence.exception;

/**
 * <code>VexPersistenceException</code> estende <code>RuntimeException</code>
 * tornando-a <code>unchecked</code>.
 * <p>
 * Um método não é obrigado a tratar ou lançar classes <code>RuntimeException</code>.
 *
 * @version 1.0, 15/12/2008
 *
 * @since 1.4.1
 *
 * @author Roberto Vezzoni
 */
public class VexPersistenceException extends RuntimeException {

    /**
     * Constrói uma nova vex persistence exception com <code>null</code> como
     * detail message. A causa não é inicializada, e pode subsequentemente
     * ser inicializada em uma chamada a {@link #initCause}.
     *
     * @since  1.4.1
     *
     * @author Roberto Vezzoni
     */
    public VexPersistenceException() {
        super();
    }

    /**
     * Constrói uma nova vex persistence exception com uma detail message
     * especificada. A causa não é inicializada, e pode subsequentemente
     * ser inicializada em uma chamada a {@link #initCause}.
     *
     * @param message
     *        A detail message. Esta detail message pode ser recuperada através
     *        de uma chamada ao método {@link #getMessage()}.
     *
     * @since  1.4.1
     *
     * @author Roberto Vezzoni
     */
    public VexPersistenceException(String message) {
        super(message);
    }

    /**
     * Constrói uma nova vex persistence exception com uma detail message
     * e uma causa especificadas.
     *
     * @param message
     *        A detail message. Esta detail message pode ser recuperada através
     *        de uma chamada ao método {@link #getMessage()}.
     * @param cause
     *        A causa (cuja é salva para ser recuperada através de uma chamada
     *        ao método {@link #getCause()} method). (Um valor <tt>null</tt> é
     *        permitido, e siginifica que a causa é inexistente ou desconhecida).
     *
     * @since  1.4.1
     *
     * @author Roberto Vezzoni
     */
    public VexPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constrói uma nova vex persistence exception com uma causa especificada. A
     * detail message também será especificada implicitamente através de
     * <tt>(cause==null ? null : cause.toString())</tt> (cuja a causa tipicamente
     * contém a classe e a detail message da <tt>cause</tt>).
     *
     * @param cause
     *        A causa (cuja é salva para ser recuperada através de uma chamada
     *        ao método {@link #getCause()} method). (Um valor <tt>null</tt> é
     *        permitido, e siginifica que a causa é inexistente ou desconhecida).
     *
     * @since  1.4.1
     *
     * @author Roberto Vezzoni
     */
    public VexPersistenceException(Throwable cause) {
        super(cause);
    }

}
