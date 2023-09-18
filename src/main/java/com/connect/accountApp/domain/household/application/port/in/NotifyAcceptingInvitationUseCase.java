package com.connect.accountApp.domain.household.application.port.in;

public interface NotifyAcceptingInvitationUseCase {

  void notifyAcceptingInvitation(String householdInviteCode, String acceptedMemberEmail);

}
