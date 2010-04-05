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

package br.com.vexit.vexpersistence.storedprocedure.intf;

import br.com.vexit.vexpersistence.exception.VexPersistenceException;
import br.com.vexit.vexpersistence.storedprocedure.StoredProcedureParam;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 * <tt>StoredProcedureIntf</tt> é uma interface responsável por listar quais métodos uma
 * Stored Procedure concreta pode executar.
 *
 * @version 1.4.1, 05/12/08
 *
 * @see StoredProcedureLocalSession
 * @see VexPersistenceException
 *
 * @since 1.0
 *
 * @author Roberto Vezzoni
 */
public interface StoredProcedureIntf extends Serializable {

    /*
     * StoredProcedure handler methods
     */     
    /**
     * Retorna um ResultSet para o nome da Stored Procedure informada junto com os seus argumentos.
     *
     * @param  storedProcedureName
     *         Nome da Stored Procedure a ser executada.
     * 
     * @param  inParams
     *         Lista com os valores dos parâmetros de tipo IN da Stored Procedure.
     *
     * @return  Um ResultSet.
     *
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    ResultSet getByStoredProcedure(String storedProcedureName, Object ... inParams) throws VexPersistenceException;

    /**
     * Retorna um ResultSet para o nome da Stored Procedure informada junto com os seus argumentos.
     *
     * @param  storedProcedureName
     *         Nome da Stored Procedure a ser executada.
     * 
     * @param  outParams
     *         Lista com os valores dos parâmetros de tipo OUT da Stored Procedure.
     * 
     * @param  inParams
     *         Lista com os valores dos parâmetros de tipo IN da Stored Procedure.
     *
     * @return  Um ResultSet.
     *
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    <T extends StoredProcedureParam> ResultSet getByStoredProcedure(String storedProcedureName, List<T> outParams, Object ... inParams) throws VexPersistenceException;

    /**
     * Executa uma Stored Procedure informada junto com os seus argumentos.
     *
     * @param  storedProcedureName
     *         Nome da Stored Procedure a ser executada.
     * 
     * @param  inParams
     *         Lista com os valores dos parâmetros de tipo IN da Stored Procedure.
     *
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    void execStoredProcedure(String storedProcedureName, Object ... inParams) throws VexPersistenceException;

    /**
     * Executa uma Stored Procedure informada junto com os seus argumentos.
     *
     * @param  storedProcedureName
     *         Nome da Stored Procedure a ser executada.
     * 
     * @param  outParams
     *         Lista com os valores dos parâmetros de tipo OUT da Stored Procedure.
     * 
     * @param  inParams
     *         Lista com os valores dos parâmetros de tipo IN da Stored Procedure.
     *
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    <T extends StoredProcedureParam> void execStoredProcedure(String storedProcedureName, List<T> outParams, Object ... inParams) throws VexPersistenceException;

}