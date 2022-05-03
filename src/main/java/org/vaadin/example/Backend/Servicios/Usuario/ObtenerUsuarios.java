
@Service
public class ObtenerUsuarios {
    @Autowired
    private Conexion db;

    public ObtenerUsuarios(){}

    public LinkedList<LovDTO> obtenerUsuarios(){
        LinkedList<LovDTO> resultado = new LinkedList<>();
        try {
            db.conectar();
            String sql = "select NOMBRE_USUARIO, CORREO_USUARIO from usuarios";
            ResultSet rs = db.consulta(sql);
            while(rs.next()){
                resultado.add(new LovDTO(rs.getString(1),rs.getString(2)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.desconectar();
        }
        return resultado;
    }
}
