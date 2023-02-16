import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static Path path1 = Paths.get("/downs");
    private static Path path2 = Paths.get("/downloads/copy");

    public static void main(String[] args) {
        SizeMeter sizeMeter = new SizeMeter();
        sizeMeter.sizeIt(String.valueOf(path1));
        sizeMeter.sizeIt(String.valueOf(path2));
        try {
            copyFolder(path1, path2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sizeMeter.sizeIt(String.valueOf(path2));
    }

    public static void copyFolder(Path path1, Path path2) throws IOException {
        File file1 = new File(String.valueOf(path1));
        File file2 = new File(String.valueOf(path2));
        copyFolder(file1, file2);
    }


    public static void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String files[] = src.list();
            for (String file :
                    files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                copyFolder(srcFile, destFile);
            }
        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];

            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        }
    }
}
