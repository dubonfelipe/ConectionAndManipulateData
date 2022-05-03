public class ServletUtils {

    public static File getBaseDirectory(HttpServletRequest servlet) {
        System.out.println(servlet.getServletContext().getContextPath());
        final String realPath = getResourcePath(servlet.getServletContext(), "/");

        if (realPath == null) {
            return null;
        }
        return new File(realPath);
    }

    protected static String getResourcePath(ServletContext servletContext,
                                            String path) {
        String resultPath = null;
        resultPath = servletContext.getRealPath(path);
        if (resultPath != null) {
            System.out.println("ResourcePath: "+resultPath);
            return resultPath;
        }
        return resultPath;
    }

    public static String getFilePath(String fileRelativePath, HttpServletRequest servlet) {
        String path = ServletUtils.getBaseDirectory(servlet).getAbsolutePath();
        if(path.contains("Temp")){
            File file = new File("");
            return file.getAbsolutePath()+"/src/main/"+ fileRelativePath;
        }
        return path + fileRelativePath;
    }
}
