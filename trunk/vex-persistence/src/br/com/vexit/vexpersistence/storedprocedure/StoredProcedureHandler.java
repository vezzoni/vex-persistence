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

package br.com.vexit.vexpersistence.storedprocedure;

import br.com.vexit.vexpersistence.storedprocedure.impl.StoredProcedureSession;
import java.sql.Connection;
import javax.persistence.EntityManager;

/**
 * <tt>StoredProcedureHandler</tt> é uma classe concreta responsável por
 * uma instância para uma {@link StoredProcedureIntf <tt>Stored Procedure concreta</tt>}.
 *
 * @version 1.4, 25/11/08
 *
 * @see StoredProcedureIntf
 * @see StoredProcedureSession
 *
 * @since 1.4
 *
 * @author Roberto Vezzoni
 */
public class StoredProcedureHandler extends StoredProcedureSession {

    /**
     * Cria uma instância de uma Stored Procedure passando
     * uma conexão válida.
     *
     * @param conn
     *        Uma instância de java.sql.Connection válida
     *
     * @since 1.4
     *
     * @author Roberto Vezzoni
     */
    public StoredProcedureHandler(Connection conn) {
        super(conn);
    }

}
