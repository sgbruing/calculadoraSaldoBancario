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
public class ReadCsvFile {
    private String name;
    private String location;
    private String extension;

    public DateOperations openCsvFile() {

        System.out.println("Opening file...");
        String separator = System.getProperty("file.separator");
        String locationName = this.location + separator + this.name + "." + this.extension;
        DateOperations date = new DateOperations();

        try {
            Scanner scan;
            File file;
            String line;
            BankingOperation operation;
            Date dateLine;
            BankAccount bankAccount;

            switch (this.extension.toLowerCase()) {
                case "csv":
                    file = new File(locationName);
                    scan = new Scanner(file);
                    line = scan.nextLine();
                    SimpleDateFormat formatterDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    while (scan.hasNextLine()) {
                        line = scan.nextLine();
                        String[] values = line.split(",");
                        dateLine = formatterDate.parse(values[0].replace("T", " "));

                        bankAccount = new BankAccount(values[1], values[2], values[3], values[4]);
                        //operation = new BankingOperation(values[5], values[6], Double.parseDouble(values[7]), dateLine, bankAccount);

                        System.out.println(values);

                        //date.put(operation);
                    };
                    break;
                default:
                    throw new Exception("Arquivo não suportado!");
            }
        } catch(Exception error) {
            error.printStackTrace();
        }

        return date;
    }

}