package br.com.letscode;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class bankingOperation {
    private Date dateHourOperation;
    private BankAccount bankAccount
    private String operator;
    private String type;
    private double value;
}