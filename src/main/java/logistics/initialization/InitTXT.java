package logistics.initialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InitTXT {

    private static volatile InitTXT instance;

    private List<String[]> list;
    private String fileName;

    private InitTXT(String fileName) {
        this.list = new ArrayList<>();
        this.fileName = fileName;
        initData();
    }

    public static InitTXT init(String fileName) {
        InitTXT result = instance;
        if (result != null) {
            return result;
        }
        synchronized (InitTXT.class) {
            if (instance == null) {
                instance = new InitTXT(fileName);
            }
            return instance;
        }

    }

    private void initData(){
        try {
        File file = new File(fileName);

        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);

        String line = reader.readLine();
        while (line != null) {
            list.add(line.split(";"));
            line = reader.readLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
        list.remove(0);
    }

    public List<String[]> getList(){
        return list;
    }
}
