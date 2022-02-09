package br.com.letscode;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class BankAccount {
    private String id;
    private String bank;
    private String agency;
    private String account;
}