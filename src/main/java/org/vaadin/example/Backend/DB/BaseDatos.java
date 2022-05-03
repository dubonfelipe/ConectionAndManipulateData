public class BaseDatos {
    public Connection conn = null;
    public PreparedStatement sentencia;
    public CallableStatement sentenciaFuncion;
    public ResultSet rs;

    public void conectarDB(String cadena, String usr, String password, boolean autoCommit) throws Exception{
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        conn = DriverManager.getConnection("jdbc:oracle:thin:@"+cadena,usr, password);
        conn.setAutoCommit(autoCommit);
    }

    public void desconectarBDD(){
        try{
            if(rs != null){
                rs.close();
            }
        }catch (Exception e){}

        try{
            if(sentencia !=null){
                sentencia.close();
            }

            if(sentenciaFuncion != null){
                sentenciaFuncion.close();
            }
        }catch (Exception e){}

        try{
            if(conn != null && !conn.isClosed()){
                conn.rollback();
                conn.close();
            }
        }catch (Exception e){}
    }

    public ResultSet consulta(String sql) throws SQLException{
        sentencia = conn.prepareStatement(sql);
        rs = sentencia.executeQuery();
        return rs;
    }

    public ResultSet consulta(String sql, Object... parametros) throws SQLException{
        sentencia = conn.prepareStatement(sql);
        insertParametros(false,parametros);
        rs = sentencia.executeQuery();
        return rs;
    }

    public int dml(String sql, Object... parametros) throws SQLException{
        sentencia = conn.prepareStatement(sql);
        insertParametros(false,parametros);
        return sentencia.executeUpdate();
    }

    public int dml(String sql) throws SQLException{
        sentencia = conn.prepareStatement(sql);
        return sentencia.executeUpdate();
    }

    public int dmlPK(String sql, Object... parametros) throws SQLException{
        sentencia = conn.prepareStatement(sql);
        insertParametros(false,parametros);
        sentencia.executeUpdate();
        ResultSet res = sentencia.getGeneratedKeys();
        rs.next();
        int llave = res.getInt(1);
        res.close();
        return llave;
    }

    public String llamarFuncion(String dml, Object... parametros) throws SQLException{
        String respuesta;
        sentenciaFuncion = conn.prepareCall(dml);
        sentenciaFuncion.registerOutParameter(1, OracleTypes.VARCHAR);
        insertParametros(true,parametros);
        sentenciaFuncion.executeUpdate();
        respuesta = sentenciaFuncion.getString(1);

        return respuesta;
    }
    private void insertParametros(boolean isfuncion, Object... parametros) throws SQLException{
        int contador = isfuncion ? 1 : 0;
        for(Object parametro : parametros){
            contador++;
            if(parametro instanceof String){
                if(isfuncion){
                    sentenciaFuncion.setString(contador, (String) parametro);
                }else {
                    sentencia.setString(contador, (String) parametro);
                }
            }else if(parametro instanceof Integer){
                if(isfuncion){
                    sentenciaFuncion.setInt(contador, (Integer) parametro);
                }else {
                    sentencia.setInt(contador, (Integer) parametro);
                }
            }else if(parametro instanceof Double){
                if(isfuncion){
                    sentenciaFuncion.setDouble(contador, (Double) parametro);
                }else {
                    sentencia.setDouble(contador, (Double) parametro);
                }
            }else if(parametro instanceof Long){
                if(isfuncion){
                    sentenciaFuncion.setLong(contador, (Long) parametro);
                }else {
                    sentencia.setLong(contador, (Long) parametro);
                }
            }else if(parametro instanceof java.util.Date){
                if(isfuncion){
                    sentenciaFuncion.setDate(contador, new java.sql.Date(((java.util.Date) parametro).getTime()));
                }else {
                    sentencia.setDate(contador, new java.sql.Date(((java.util.Date) parametro).getTime()));
                }
            }else if(parametro instanceof LocalDate){
                LocalDate tp = (LocalDate) parametro;
                java.util.Date date = Date.from(
                        ((LocalDate)parametro).atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant());
                insertParametros(isfuncion, date);
            }else if(parametro instanceof InputStream){
                if(isfuncion){
                    sentenciaFuncion.setBlob(contador, (InputStream) parametro);
                }else {
                    sentencia.setBlob(contador, (InputStream) parametro);
                }
            }else {
                if(isfuncion){
                    sentenciaFuncion.setObject(contador, parametro);
                }else {
                    sentencia.setObject(contador, parametro);
                }
            }
        }
    }

    public Connection getConn(){
        return conn;
    }
}
