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

package br.com.vexit.vexpersistence.storedprocedure.impl;

import br.com.vexit.vexpersistence.exception.VexPersistenceException;
import br.com.vexit.vexpersistence.storedprocedure.StoredProcedureParam;
import br.com.vexit.vexpersistence.storedprocedure.intf.StoredProcedureIntf;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 * <tt>StoredProcedureSession</tt> é uma classe abstrata responsável por
 * implementar métodos genéricos para uma
 * {@link StoredProcedureSessionHandler <tt>StoredProcedureSessionHandler concreta</tt>}.
 *
 * @version 1.4.1, 05/12/08
 *
 * @see StoredProcedureIntf
 * @see StoredProcedureSessionHandler
 *
 * @since 1.4
 *
 * @author Roberto Vezzoni
 */
public class StoredProcedureSession implements StoredProcedureIntf {

    private Connection conn;

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
    public StoredProcedureSession(Connection conn) {
        this.conn = conn;
    }
    
    /**
     * Retorna uma Connection de acordo com a sessão ativa com o banco de dados.
     *
     * @return Uma Connection.
     *
     * @since 1.3
     *
     * @author Roberto Vezzoni
     */
    private Connection getConnection() {
        return conn;
    }
    
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
    public ResultSet getByStoredProcedure(String storedProcedureName, Object ... inParams) throws VexPersistenceException {
        
        return getByStoredProcedure(storedProcedureName, null, inParams);
    }
    
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
    public <T extends StoredProcedureParam> ResultSet getByStoredProcedure(String storedProcedureName, List<T> outParams, Object ... inParams) throws VexPersistenceException {

        ResultSet result = null;

        try {

            String call = "{call " + storedProcedureName;
            
            call += "(";
            
            for (int i = 0; i < inParams.length; i++) {
                call += "?";

                if (i < (inParams.length - 1))
                  call += ", ";
            }
            
            call += ")}";
            
            CallableStatement cs = getConnection()
                    .prepareCall(
                        call, 
                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
            
            // registro dos parâmetros OUT
            if (outParams != null && !outParams.isEmpty()) {
                for (StoredProcedureParam param: outParams) {
                    cs.registerOutParameter(param.getName(), param.getSqlType(), param.getScale());
                }
            }
            
            // passando os parâmetros de entrada da stored procedure
            for (int i = 0; i < inParams.length; i++) {
                cs.setObject(i + 1, inParams[i]);
            }
                
            result = cs.executeQuery();

            return result;
            
        } catch (SQLException e) {

            e.printStackTrace();

            throw new PersistenceException(e);
        }
        
    }

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
    public void execStoredProcedure(String storedProcedureName, Object ... inParams) throws VexPersistenceException {
        
        execStoredProcedure(storedProcedureName, null, inParams);
    }
    
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
    public <T extends StoredProcedureParam> void execStoredProcedure(String storedProcedureName, List<T> outParams, Object ... inParams) throws VexPersistenceException {

        try {

            String call = "{call " + storedProcedureName;
            
            call += "(";
            
            for (int i = 0; i < inParams.length; i++) {
                call += "?";

                if (i < (inParams.length - 1))
                  call += ", ";
            }
            
            call += ")}";
            
            CallableStatement cs = getConnection()
                    .prepareCall(
                        call, 
                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
            
            // registro dos parâmetros OUT
            if (outParams != null && !outParams.isEmpty()) {
                for (StoredProcedureParam param: outParams) {
                    cs.registerOutParameter(param.getName(), param.getSqlType(), param.getScale());
                }
            }
            
            // passando os parâmetros de entrada da stored procedure
            for (int i = 0; i < inParams.length; i++) {
                cs.setObject(i + 1, inParams[i]);
            }
                
            cs.execute();

        } catch (SQLException e) {

            e.printStackTrace();

            throw new VexPersistenceException(e);
        }
        
    }
    
}