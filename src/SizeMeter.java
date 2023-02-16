import java.io.File;


public class SizeMeter {

    private static int count1;

    public void sizeIt(String path) {
        File f = new File(path);
        long d = doFile(f);
        doCount();
        System.out.println("Size of " + f.getName() + " is "
                + (double) d / (1024 * 1024)
                + " MB");
        System.out.println("Size of " + f.getName() + " is "
                + (double) d / (1024 * 1024 * 1024)
                + " GB");
    }

    private void doCount() {
        System.out.println(count1 + " files counted");
        count1 = 0;
    }

    public long doFile(File folder) {
        long d = 0;
        try {
            File[] files = folder.listFiles();
            int count = files.length;
            count1 += count;
            for (int i = 0; i < count; i++) {
                if (files[i].exists() && files[i].isFile()) {
                    d += files[i].length();
                } else {
                    d += doFile(files[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
}
