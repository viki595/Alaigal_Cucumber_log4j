Feature: open Alaigal Website

  @login
  Scenario: Login With Admin Credential
    Given User On Login Page
    And Enters User Name "Admin@gmail.com" And The Password "Admin@123"
    When User Clicks Login Button
    Then User Should See The Admin Dashboard

  @memberdata
  Scenario: Retrieve All Member Details From Lounge
    When User Clicks On The MemberLounge
    Then User Should see The Members profiles
    Then Members Names Are Saved In XL

  @findnewmember
  Scenario: Retrieve New Member
    When User Loads Old Member Lounge Data
    Then Compare Old And New Data For New User
