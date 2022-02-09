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

public class DateOperations {
    Map<String, ArrayList<BankingOperation>> map = new HashMap<>();

    public Set<String> keys() { return map.keySet(); }

    public ArrayList<BankingOperation> getKey(String key) {
        return map.get(key);
    }

    public ArrayList<BankingOperation> addOrdered(ArrayList<BankingOperation> accountOperations, BankingOperation newOperation) {
        boolean checkEquals;

        ArrayList<BankingOperation> orderedList = new ArrayList<>();
        Date newDate = newOperation.getDateHourOperation();
        long newDateTime = newDate.getTime();
        boolean control = true;
        int i=0;

        while (i < accountOperations.size()) {
            Date itemData = accountOperations.get(i).getDateHourOperation();
            long itemNewDate = itemData.getTime();
            if (control) {
                if (newDateTime == itemNewDate) {
                    checkEquals = accountOperations.get(i).getOperator().equals(newOperation.getOperator()) && accountOperations.get(i).getType().equals(newOperation.getType()) && accountOperations.get(i).getValue() == newOperation.getValue();
                    if (checkEquals) {
                        i = i+1;
                    } else {
                        orderedList.add(newOperation);
                        control = false;
                    }
                }
                if (newDateTime > itemNewDate) {
                    orderedList.add(accountOperations.get(i));
                    i = i+1;
                }
                if (newDateTime < itemNewDate) {
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
        String idAccount = operation.getBankAccount().getId();
        ArrayList<BankingOperation> accountOperations = map.get(idAccount);
        if (accountOperations == null) {
            operationsList.add(operation);
            map.put(idAccount, operationsList);
        } else {
            operationsList = accountOperations;
            ArrayList<BankingOperation> orderedList = addOrdered(operationsList, operation);
            map.put(idAccount, orderedList);
        }
    }
}