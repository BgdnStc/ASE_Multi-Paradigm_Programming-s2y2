package ro.ase.acs.main;

import ro.ase.acs.classes.HandballMatch;
import ro.ase.acs.classes.Utils;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<HandballMatch> list = new ArrayList<>();
		list = Utils.readFromCSV("matches.csv");
		Utils.serialize(list, "LeFile.bin");
		list = Utils.deserialize("LeFile.bin");
		//System.out.print(list);
		Utils.writeHeader("LeFile.csv");
		Utils.writePoints("LeFile.csv", list);

		List<HandballMatch> list2 = new ArrayList<>();
		HandballMatch m4 = new HandballMatch();
		m4.setHomeTeam("RAPID");
		m4.setAwayTeam("BUCURESTI");
		m4.setGoalsHomeTeam(21);
		m4.setGoalsAwayTeam(21);
		list2.add(m4);
		HandballMatch m5 = new HandballMatch();
		m5.setHomeTeam("CRAIOVA");
		m5.setAwayTeam("CLUJ");
		m5.setGoalsHomeTeam(25);
		m5.setGoalsAwayTeam(20);
		list2.add(m5);
		HandballMatch m6 = new HandballMatch();
		m6.setHomeTeam("CLUJ");
		m6.setAwayTeam("RAPID");
		m6.setGoalsHomeTeam(21);
		m6.setGoalsAwayTeam(22);
		list2.add(m6);
		HandballMatch m7 = new HandballMatch();
		m7.setHomeTeam("CRAIOVA");
		m7.setAwayTeam("BUCURESTI");
		m7.setGoalsHomeTeam(21);
		m7.setGoalsAwayTeam(21);
		list2.add(m7);
		HandballMatch m8 = new HandballMatch();
		m8.setHomeTeam("CRAIOVA");
		m8.setAwayTeam("RAPID");
		m8.setGoalsHomeTeam(20);
		m8.setGoalsAwayTeam(20);
		list2.add(m8);
		HandballMatch m9 = new HandballMatch();
		m9.setHomeTeam("BUCURESTI");
		m9.setAwayTeam("CLUJ");
		m9.setGoalsHomeTeam(26);
		m9.setGoalsAwayTeam(21);
		list2.add(m9);
		//Utils.writePointsAndGoals("LeFile.csv", list2);
		Utils.leagueTable("LeFile.csv", list2);
		//Utils.specialLeagueTable("LeFile.csv", list2);
	}
}
