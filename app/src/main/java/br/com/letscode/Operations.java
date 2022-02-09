package br.com.letscode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lombok.ToString;
import lombok.NoArgsConstructor;

@ToString
@NoArgsConstructor

public class Operations {
    Map<String, ArrayList<BankingOperation>> map = new HashMap<>();

    public Set<String> keys() { return map.keySet(); }

    public ArrayList<BankingOperation> getKey(String key) {
        return map.get(key);
    }

    public ArrayList<BankingOperation> addOrdered(ArrayList<BankingOperation> accountOperations, bankingOperation newOperation) {
        boolean checkEquals;

        ArrayList<BankingOperation> orderedList = new ArrayList<>();
        Date newDate = newOperation.getDataHoraOperacao();
        long newDate = newDate.getTime();
        boolean control = true;
        int i=0;

        System.out.println(newOperation);

        while (i < accountOperations.size()) {
            Date itemData = accountOperations.get(i).getDataHoraOperacao();
            long itemNewDate = itemData.getTime();
            if (control) {
                if (newDate == itemNewDate) {
                    checkEquals = accountOperations.get(i).getOperator().equals(newOperation.getOperator()) && accountOperations.get(i).getType().equals(newOperation.getTipo()) && accountOperations.get(i).getValue() == newOperation.getValue();
                    if (checkEquals) {
                        i = i+1;
                    } else {
                        orderedList.add(newOperation);
                        control = false;
                    }
                }
                if (newDate > itemNewDate) {
                    orderedList.add(accountOperations.get(i));
                    i = i+1;
                }
                if (newDate < itemNewDate) {
                    orderedList.add(newOperation);
                    control = false;
                }
            } else {
                orderedList.add(accountOperations.get(i));
                i = i+1;
            }
        }
        if (control) {
            orderedList.add(newOperation);
        }
        return orderedList;
    }

    public void put(BankingOperation operation){
        ArrayList<BankingOperation> operationsList = new ArrayList<>();
        String idAccount = operation.getAccount().getId();
        ArrayList<BankingOperation> accountOperations = map.get(idAccount);
        if (accountOperations == null) {
            operationsList.add(operation);
            map.put(idAccount, operationsList);
        } else {
            operationsList = accountOperations;
            ArrayList<BankingOperation> orderedList = adicionarOrdenado(operationsList, operacao);
            map.put(idAccount, orderedList);
        }
    }
}