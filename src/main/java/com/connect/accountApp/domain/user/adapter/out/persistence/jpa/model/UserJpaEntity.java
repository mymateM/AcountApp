package com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model;

import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import com.connect.accountApp.domain.user.domain.model.Bank;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class UserJpaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  private String userName;
  private String userImgUrl;
  private String userAccount;

  @Enumerated(EnumType.STRING)
  private Bank userAccountBank;
  private boolean userAccept;
  private int userRatio;

  @ManyToOne
  @JoinColumn(name = "household_id")
  private HouseHoldJpaEntity houseHoldJpaEntity;


  @Builder
  public UserJpaEntity(Long userId, String userName, String userImgUrl, String userAccount,
      Bank userAccountBank, boolean userAccept, int userRatio,
      HouseHoldJpaEntity houseHoldJpaEntity) {
    this.userId = userId;
    this.userName = userName;
    this.userImgUrl = userImgUrl;
    this.userAccount = userAccount;
    this.userAccountBank = userAccountBank;
    this.userAccept = userAccept;
    this.userRatio = userRatio;
    this.houseHoldJpaEntity = houseHoldJpaEntity;
  }
}
