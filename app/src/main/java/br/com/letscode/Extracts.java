package br.com.letscode;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.io.IOException;
import java.io.PrintWriter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Extracts {
    DateOperations data;

    public void makeExtracts(String path) {
        Set<String> accountsSet = data.keys();

        System.out.println(accountsSet);

        String [] accountsArray = new String[accountsSet.size()];
        accountsSet.toArray(accountsArray);
        for(int i=0; i<accountsArray.length;i++){
            generateExtract(path, accountsArray[i]);
        }
    }

    public void generateExtract(String path, String account) {
        double balance = 0.0;
        Long date;
        String separator = System.getProperty("file.separator");
        String file = path+separator+account+".txt";

        ArrayList<BankingOperation> operations = data.getKey(account);

        try {

            FileWriter arquive = new FileWriter(file);
            PrintWriter saveFile = new PrintWriter(arquive);
            saveFile.printf("Bank: "+operations.get(0).getBankAccount().getBank()+"\n");
            saveFile.printf("Agency: "+operations.get(0).getBankAccount().getAgency()+"\n");
            saveFile.printf("Account: ..... "+operations.get(0).getBankAccount().getAccount()+"\n\n");
            saveFile.printf("Date \t\t\t");
            saveFile.printf("Type \t\t");
            saveFile.printf("Value \t\t");
            saveFile.printf("Operator \n\n");
            System.out.println(operations.get(0).getBankAccount().getBank());
            for (BankingOperation item : operations) {
                date = item.getDateHourOperation().getTime();
                String dateFormat = "dd-MM-yy HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
                String dateArquive = simpleDateFormat.format(new Date(date));
                saveFile.printf(dateArquive+"\t");
                if (item.getType().equals("DEPOSITO")) {
                    balance = balance + item.getValue();
                    saveFile.printf(item.getType() + "\t");;
                    saveFile.printf("+%.2f\t\t", item.getValue());
                }
                if (item.getType().equals("SAQUE")) {
                    balance = balance - item.getValue();
                    saveFile.printf(item.getType() + "\t\t");
                    saveFile.printf("-%.2f\t\t", item.getValue());
                }
                saveFile.printf(item.getOperator()+"\t\n");
            }

            saveFile.printf("\nBalance: \t");
            saveFile.printf(Double.toString(balance));
            arquive.close();

        } catch (Exception error) {
            error.printStackTrace();
        }

    }
}
