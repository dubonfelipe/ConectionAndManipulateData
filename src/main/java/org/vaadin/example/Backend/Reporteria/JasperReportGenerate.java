
public class JasperReportGenerate {
    private HashMap fillParametros = new HashMap();
    private IConexion conexion;
    private ByteArrayOutputStream outputStream;
    private String filename;
    private HttpServletRequest httpServletRequest;
    public JasperPrint pdfView;

    public JasperReportGenerate(String filename, HttpServletRequest httpServletRequest, IConexion conexion){
        this.filename = filename;
        this.httpServletRequest = httpServletRequest;
        outputStream = new ByteArrayOutputStream();
        this.conexion = conexion;
    }

    public void execute() throws Exception{
        File reporte = null;
        File template = null;
        reporte = new File(ServletUtils.getFilePath("",this.httpServletRequest));

        if(reporte.exists()){
            template = new File(reporte.getAbsolutePath());
            for (Object key : this.fillParametros.keySet()) {
                Object value = this.fillParametros.get(key);
                System.out.println(value);
            }
            fillParametros.put(JRParameter.REPORT_LOCALE, new Locale("es","ES"));
            conexion.conectar();
            JasperPrint jasperPrint = JasperFillManager.fillReport(template.getAbsolutePath(), fillParametros, conexion.getConecction());
            //JasperPrint jasperPrint = JasperFillManager.fillReport(template.getAbsolutePath(), fillParametros, new JREmptyDataSource());
            pdfView = jasperPrint;

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
            conexion.desconectar();
        }
    }

    public void addParameter(String key, Object value){
        fillParametros.put(key,value);
    }

    public String getFilename(){
        return this.filename;
    }

    public byte[] getBytes(){
        return this.outputStream.toByteArray();
    }
}
