package com.teamtreehouse.tinypencil;

import com.teamtreehouse.tinypencil.model.Helpers;
import com.teamtreehouse.tinypencil.model.Party;
import com.teamtreehouse.tinypencil.ui.Dashboard;

public class App {

  public static void main(String[] args) {
    Party party = new Party("Andrew", "Craig", "Dave", "Alena");
    Helpers.randomizeParty(party);

    Dashboard dashboard = new Dashboard();
    dashboard.displayWinner(party);
    dashboard.displaySummary(party);
    dashboard.displayPartyDetail(party);


  }

}
