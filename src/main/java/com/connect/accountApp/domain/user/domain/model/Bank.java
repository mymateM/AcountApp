package com.connect.accountApp.domain.user.domain.model;

import static com.connect.accountApp.domain.user.domain.model.Bank.DigitType.DIGIT_11;
import static com.connect.accountApp.domain.user.domain.model.Bank.DigitType.DIGIT_12;
import static com.connect.accountApp.domain.user.domain.model.Bank.DigitType.DIGIT_13;
import static com.connect.accountApp.domain.user.domain.model.Bank.DigitType.DIGIT_14;

import java.util.List;

public enum Bank {
  WOORI("우리은행", "woori.png", List.of(DIGIT_13)),
  HANA("하나은행", "hana.png", List.of(DIGIT_14)),
  KB("국민은행", "kb.png", List.of(DIGIT_12, DIGIT_14)),
  SINHAN("신한은행", "sinhan.png", List.of(DIGIT_11, DIGIT_12)),
  SC("제일은행", "sc.png", List.of(DIGIT_11)),
  CITI("씨티은행", "citi.png", List.of(DIGIT_12)),
  NH("농협은행", "nh.png", List.of(DIGIT_13));


  private final String bankName;
  private final String bankImage;
  private final List<DigitType> digitType;

  Bank(String bankName, String bankImage, List<DigitType> digitType) {
    this.bankName = bankName;
    this.bankImage = bankImage;
    this.digitType = digitType;
  }

  boolean checkBankPattern(String bankAccount) { // 해당 계좌번호의 자릿수가 맞는지 체크하는 함수
    return digitType.stream().anyMatch(digit -> digit.checkBankPattern(bankAccount));

  }

  enum DigitType {
    DIGIT_11 {
      @Override
      boolean checkBankPattern(String bankAccount) {
        return bankAccount.length() == 11;
      }
    },
    DIGIT_12 {
      @Override
      boolean checkBankPattern(String bankAccount) {
        return bankAccount.length() == 12;
      }
    },
    DIGIT_13 {
      @Override
      boolean checkBankPattern(String bankAccount) {
        return bankAccount.length() == 13;
      }
    },
    DIGIT_14 {
      @Override
      boolean checkBankPattern(String bankAccount) {
        return bankAccount.length() == 14;
      }
    };

    abstract boolean checkBankPattern(String bankAccount);

  }




}
