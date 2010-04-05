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

import java.io.Serializable;


/**
 * <tt>StoredProcedureParam</tt> é uma classe responsável por 
 * manter o estado de um parâmetro de tipo OUT de uma Stored Procedure.
 *
 * @version  1.4, 24/07/08
 * 
 * @see StoredProcedureIntf
 * @see StoredProcedureSession
 * @see StoredProcedureSessionHandler
 * 
 * @since 1.1
 *
 * @author Roberto Vezzoni
 */
public class StoredProcedureParam implements Serializable {
    
    private String name;
    private int sqlType;
    private Object value;
    private int scale;
    
    /**
     * Cria uma instância de StoredProcedureParam sem estado.
     *
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public StoredProcedureParam() {
        this("", null);
    }

    /**
     * Cria uma instância de StoredProcedureParam com nome e valor do parâmetro.
     *
     * @param name
     *        Nome do parâmetro da StoredProcedure.
     * 
     * @param value
     *        Valor do parâmetro da StoredProcedure.
     * 
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public StoredProcedureParam(String name, Object value) {
        this(name, 0, value);
    }

    /**
     * Cria uma instância de StoredProcedureParam com nome, valor e o tipo 
     * (tipo do banco de dados) do parâmetro.
     *
     * @param name
     *        Nome do parâmetro da StoredProcedure.
     * 
     * @param sqlType
     *        Tipo do parâmetro da StoredProcedure. 
     *        <br/>
     *        Dentres os tipos podemos ter:
     *        <code>java.sql.Types.INTEGER</code> e <code>java.sql.Types.VARCHAR</code>.
     * 
     * @param value
     *        Valor do parâmetro da StoredProcedure.
     * 
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public StoredProcedureParam(String name, int sqlType, Object value) {
        this(name, sqlType, value, 2);
    }

    /**
     * Cria uma instância de StoredProcedureParam com nome, valor, tipo 
     * (tipo do banco de dados) e a escala (no caso de ser de tipo ponto flutuante) do parâmetro.
     *
     * @param name
     *        Nome do parâmetro da StoredProcedure.
     * 
     * @param sqlType
     *        Tipo do parâmetro da StoredProcedure. 
     *        <br/>
     *        Dentres os tipos podemos ter:
     *        <code>java.sql.Types.INTEGER</code> e <code>java.sql.Types.VARCHAR</code>.
     * 
     * @param value
     *        Valor do parâmetro da StoredProcedure.
     * 
     * @param scale
     *        Escala se o parâmetro da StoredProcedure for do tipo ponto flutuante.
     *        <br/>
     *        Valor padrão é 2.
     * 
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public StoredProcedureParam(String name, int sqlType, Object value, int scale) {
        setName(name);
        setSqlType(sqlType);
        setValue(value);
        setScale(scale);
    }

    /**
     * Pega o nome do parâmetro Stored Procedure.
     * 
     * @return Nome do parâmetro da Stored Procedure.
     *
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do parâmetro da Stored Procedure.
     * 
     * @param name
     *        Nome do parâmetro da Stored Procedure.
     * 
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Pega o tipo (tipo do banco de dados) do parâmetro da Stored Procedure.
     * 
     * @return Tipo do parâmetro da Stored Procedure.
     *
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public int getSqlType() {
        return sqlType;
    }

    /**
     * Define o tipo (tipo do banco de dados) do parâmetro da Stored Procedure.
     * 
     * @param sqlType
     *        Tipo do parâmetro da Stored Procedure.
     * 
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    /**
     * Pega o valor do parâmetro Stored Procedure.
     * 
     * @return Valor do parâmetro da Stored Procedure.
     *
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public Object getValue() {
        return value;
    }

    /**
     * Define o valor do parâmetro da Stored Procedure.
     * 
     * @param value
     *        Valor do parâmetro da Stored Procedure.
     * 
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Pega a escala do parâmetro da Stored Procedure quando este for de tipo ponto flutuante.
     * 
     * @return Escala para ponto flutuantes.
     *
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public int getScale() {
        return scale;
    }

    /**
     * Define a escala para parâmetro da Stored Procedure de tipo ponto flutuante.
     * 
     * @param scale
     *        Escala para parâmetro da Stored Procedure de tipo ponto flutuante.
     * 
     * @since  1.1
     *
     * @author Roberto Vezzoni
     */
    public void setScale(int scale) {
        this.scale = scale;
    }

}