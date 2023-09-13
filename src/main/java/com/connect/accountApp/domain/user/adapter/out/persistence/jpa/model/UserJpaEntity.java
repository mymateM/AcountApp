package com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model;

import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import com.connect.accountApp.domain.user.domain.model.Bank;
import com.connect.accountApp.domain.user.domain.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

  @Column(unique = true)
  private String userEmail;
  private String userPassword;
  private String userNickname;
  private String userImgUrl;
  private String userAccount;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Enumerated(EnumType.STRING)
  private Bank userAccountBank;
  private boolean userAccept;
  private int userRatio;

  private String deviceToken;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "household_id")
  private HouseHoldJpaEntity houseHoldJpaEntity;


  @Builder
  public UserJpaEntity(Long userId, String userEmail, String userPassword,
      String userNickname, String userImgUrl, Role role,
      String userAccount, Bank userAccountBank, boolean userAccept, int userRatio,
      HouseHoldJpaEntity houseHoldJpaEntity, String deviceToken) {
    this.userId = userId;
    this.userEmail = userEmail;
    this.userPassword = userPassword;
    this.userNickname = userNickname;
    this.userImgUrl = userImgUrl;
    this.role = role;
    this.userAccount = userAccount;
    this.userAccountBank = userAccountBank;
    this.userAccept = userAccept;
    this.userRatio = userRatio;
    this.houseHoldJpaEntity = houseHoldJpaEntity;
    this.deviceToken = deviceToken;
  }
}
