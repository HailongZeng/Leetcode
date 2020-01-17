package Amazon_VO;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author HailongZeng
 * @date 1/4/20 11:06 下午
 */
public class findFile {

    public static List<String> findByName(File directory, int level){
        List<String> res = new ArrayList<>();
        if (directory == null) return res;
        if (directory.isDirectory()){
            List<File> list = Arrays.asList(directory.listFiles());
            for (File f: list){
                String name = f.getName();
                if (f.isDirectory()) {
                    if (level == 0) res.add(name);
                    if (name.endsWith(".xml")) {
                        List<String> sub = findByName(f, level + 1);
                        res.addAll(new ArrayList<>(sub));
                    }
                } else{
                    if (level == 0) res.add(name);
                }
            }
        }
        return res;
    }

    public static List<String> findBySize(File directory, int size, int level){
        List<String> res = new ArrayList<>();
        if (directory == null) return res;
        if (directory.isDirectory()){
            List<File> list = Arrays.asList(directory.listFiles());
            for (File f: list){
                String name = f.getName();
                if (f.isDirectory()) {
                    if (level == 0) res.add(name);
                    if (name.length() > size) {
                        List<String> sub = findByName(f, level + 1);
                        res.addAll(new ArrayList<>(sub));
                    }
                } else{
                    if (level == 0) res.add(name);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String mainPath = st.nextLine();
            File directory = new File(mainPath);
//            System.out.println(Arrays.asList(directory.listFiles()));
            if (directory.exists()){
                System.out.println(findByName(directory, 0));
            }
        }
    }
}
