package br.com.letscode;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.io.File;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
@Setter
public class readCsvFile {
    private String name;
    private String location;
    private String extension;

    public operations openCsvFile() {

        System.out.println("Opening file...");
        String separator = System.getProperty("file.separator");
        String locationName = this.location + separator + this.name + "." + this.extension;

        try {
            Scanner scan;
            File file;
            String line;
            BankingOperation operation;
            Date dateline;
            BankAccount account;

            switch (this.extension.toLowerCase()) {
                case "csv":
                    file = new File(pathName);
                    scan = new Scanner(file);
                    line = scan.nextLine();
                    SimpleDateFormat formatterDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    while (scan.hasNextLine()) {
                        line = scan.nextLine();
                        String[] values = line.split(",");
                        dateLine = formatterDate.parse(values[0].replace("T", " "));

                        System.out.println(values);

                        //implementar conta bancaria
                        //implementar operação
                    };
                    break;
                default:
                    throw new Exception("Arquivo não suportado!");
            }
        } catch(Exception error) {
            error.printStackTrace();
        }


    }

}