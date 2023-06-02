package ro.ase.acs.classes;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Utils implements Serializable {

	public static void matchDayReport(List<HandballMatch> matches, String filename) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filename);
			OutputStreamWriter streamWriter = new OutputStreamWriter(fileOutputStream);
			BufferedWriter writer = new BufferedWriter(streamWriter);
			HandballMatch[] matchArray = matches.toArray(new HandballMatch[0]);
			for(int i = 0; i < matches.size(); i++) {
				writer.write("\t");
				writer.write(matchArray[i].getHomeTeam());
				writer.write(" ");
				int homeGoals = matchArray[i].getGoalsHomeTeam();
				writer.write(Integer.toString(homeGoals));
				writer.write(" - ");
				int awayGoals = matchArray[i].getGoalsAwayTeam();
				writer.write(Integer.toString(awayGoals));
				writer.write(" ");
				writer.write(matchArray[i].getAwayTeam());
				writer.write(System.lineSeparator());
			}
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static List<HandballMatch> readFromCSV(String filename) {
		List<HandballMatch> matchList = new ArrayList<>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String header = reader.readLine();
			String line = reader.readLine();
			while(line != null) {
				String[] h = line.split(", ");
				HandballMatch handballMatch = new HandballMatch();
				handballMatch.setHomeTeam(h[0]);
				handballMatch.setAwayTeam(h[1]);
				handballMatch.setGoalsHomeTeam((Integer.parseInt(h[2])));
				handballMatch.setGoalsAwayTeam((Integer.parseInt(h[3])));
				matchList.add(handballMatch);
				line = reader.readLine();
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return matchList;
	}

	public static int secretInfo(String filename) {
		int championships = 0;
		try {
			FileInputStream binaryInputStream = new FileInputStream(filename);
			DataInputStream dataInputStream = new DataInputStream(binaryInputStream);
			for(int i = 0; i < 12; i++) {
				Byte x = dataInputStream.readByte();
			}
			championships = dataInputStream.readInt();
			dataInputStream.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return championships;
	}

	public static void serialize(List<HandballMatch> matches, String filename) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filename);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			for(HandballMatch i: matches) {
				objectOutputStream.writeObject(i);
			}
			objectOutputStream.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static List<HandballMatch> deserialize(String filename) {
		List<HandballMatch> list = new ArrayList<>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			HandballMatch handballMatch = (HandballMatch) objectInputStream.readObject();
			while(handballMatch != null) {
				list.add(handballMatch);
				handballMatch = (HandballMatch) objectInputStream.readObject();
			}
			objectInputStream.close();
		} catch(IOException | ClassNotFoundException e) {
			return list;
		}
		return list;
	}

	public static void writeHeader(String filename) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filename);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
			outputStreamWriter.write("NO, TEAM, PTS, GF, GA, GD");
			outputStreamWriter.write(System.lineSeparator());
			outputStreamWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void writePoints(String filename, List<HandballMatch> matches) {
        try {
			FileOutputStream fileOutputStream = new FileOutputStream(filename);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
			Map<String, Integer> teamPoints = new HashMap<>();
			for (HandballMatch i : matches) {
				if (teamPoints.containsKey(i.getHomeTeam())) {
					if (i.getGoalsHomeTeam() > i.getGoalsAwayTeam()) {
						teamPoints.put(i.getHomeTeam(), teamPoints.get(i.getHomeTeam()) + 3);
					} else if (i.getGoalsHomeTeam() == i.getGoalsAwayTeam()) {
                        teamPoints.put(i.getHomeTeam(), teamPoints.get(i.getHomeTeam()) + 1);
					}
				}
                else {
                    if (i.getGoalsHomeTeam() > i.getGoalsAwayTeam()) {
                        teamPoints.put(i.getHomeTeam(), 3);
                    } else if (i.getGoalsHomeTeam() == i.getGoalsAwayTeam()) {
                        teamPoints.put(i.getHomeTeam(), 1);
                    }
                    else {
                        teamPoints.put(i.getHomeTeam(), 0);
                    }
				}
				if (teamPoints.containsKey(i.getAwayTeam())) {
                    if (i.getGoalsAwayTeam() > i.getGoalsHomeTeam()) {
                        teamPoints.put(i.getAwayTeam(), teamPoints.get(i.getAwayTeam()) + 3);
                    } else if (i.getGoalsHomeTeam() == i.getGoalsAwayTeam()) {
                        teamPoints.put(i.getAwayTeam(), teamPoints.get(i.getAwayTeam()) + 1);
                    }
				} else {
                    if (i.getGoalsAwayTeam() > i.getGoalsHomeTeam()) {
                        teamPoints.put(i.getAwayTeam(), 3);
                    } else if (i.getGoalsHomeTeam() == i.getGoalsAwayTeam()) {
                        teamPoints.put(i.getAwayTeam(), 1);
                    }
                    else {
                        teamPoints.put(i.getAwayTeam(), 0);
                    }
				}
			}
            for (String i : teamPoints.keySet()) {
                outputStreamWriter.write(i + ", ");
                outputStreamWriter.write(teamPoints.get(i) + ", ");
            }
			outputStreamWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writePointsAndGoals(String filename, List<HandballMatch> matches) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            Map<String, ArrayList<Integer>> teamPoints = new HashMap<>();
            for (HandballMatch i : matches) {
                if (teamPoints.containsKey(i.getHomeTeam())) {
                    if (i.getGoalsHomeTeam() > i.getGoalsAwayTeam()) {
                        teamPoints.get(i.getHomeTeam()).set(0, teamPoints.get(i.getHomeTeam()).get(0) + 3);
                        teamPoints.get(i.getHomeTeam()).set(1, teamPoints.get(i.getHomeTeam()).get(1) + i.getGoalsHomeTeam());
                        teamPoints.get(i.getHomeTeam()).set(2, teamPoints.get(i.getHomeTeam()).get(2) + i.getGoalsAwayTeam());
                    } else if (i.getGoalsHomeTeam() == i.getGoalsAwayTeam()) {
                        teamPoints.get(i.getHomeTeam()).set(0, teamPoints.get(i.getHomeTeam()).get(0) + 1);
                        teamPoints.get(i.getHomeTeam()).set(1, teamPoints.get(i.getHomeTeam()).get(1) + i.getGoalsHomeTeam());
                        teamPoints.get(i.getHomeTeam()).set(2, teamPoints.get(i.getHomeTeam()).get(2) + i.getGoalsAwayTeam());
                    } else {
						teamPoints.get(i.getHomeTeam()).set(1, teamPoints.get(i.getHomeTeam()).get(1) + i.getGoalsHomeTeam());
						teamPoints.get(i.getHomeTeam()).set(2, teamPoints.get(i.getHomeTeam()).get(2) + i.getGoalsAwayTeam());
					}
                }
                else {
                    teamPoints.put(i.getHomeTeam(), new ArrayList<>());
                    if (i.getGoalsHomeTeam() > i.getGoalsAwayTeam()) {
                        teamPoints.get(i.getHomeTeam()).add(3);
                        teamPoints.get(i.getHomeTeam()).add(i.getGoalsHomeTeam());
                        teamPoints.get(i.getHomeTeam()).add(i.getGoalsAwayTeam());
                    } else if (i.getGoalsHomeTeam() == i.getGoalsAwayTeam()) {
                        teamPoints.get(i.getHomeTeam()).add(1);
                        teamPoints.get(i.getHomeTeam()).add(i.getGoalsHomeTeam());
                        teamPoints.get(i.getHomeTeam()).add(i.getGoalsAwayTeam());
                    }
                    else {
                        teamPoints.get(i.getHomeTeam()).add(0);
                        teamPoints.get(i.getHomeTeam()).add(i.getGoalsHomeTeam());
                        teamPoints.get(i.getHomeTeam()).add(i.getGoalsAwayTeam());
                    }
                }
                if (teamPoints.containsKey(i.getAwayTeam())) {
                    if (i.getGoalsAwayTeam() > i.getGoalsHomeTeam()) {
                        teamPoints.get(i.getAwayTeam()).set(0, teamPoints.get(i.getAwayTeam()).get(0) + 3);
                        teamPoints.get(i.getAwayTeam()).set(1, teamPoints.get(i.getAwayTeam()).get(1) + i.getGoalsAwayTeam());
                        teamPoints.get(i.getAwayTeam()).set(2, teamPoints.get(i.getAwayTeam()).get(2) + i.getGoalsHomeTeam());
                    } else if (i.getGoalsHomeTeam() == i.getGoalsAwayTeam()) {
                        teamPoints.get(i.getAwayTeam()).set(0, teamPoints.get(i.getAwayTeam()).get(0) + 1);
                        teamPoints.get(i.getAwayTeam()).set(1, teamPoints.get(i.getAwayTeam()).get(1) + i.getGoalsAwayTeam());
                        teamPoints.get(i.getAwayTeam()).set(2, teamPoints.get(i.getAwayTeam()).get(2) + i.getGoalsHomeTeam());
                    } else {
						teamPoints.get(i.getAwayTeam()).set(1, teamPoints.get(i.getAwayTeam()).get(1) + i.getGoalsAwayTeam());
						teamPoints.get(i.getAwayTeam()).set(2, teamPoints.get(i.getAwayTeam()).get(2) + i.getGoalsHomeTeam());
					}
                }
                else {
					teamPoints.put(i.getAwayTeam(), new ArrayList<>());
                    if (i.getGoalsAwayTeam() > i.getGoalsHomeTeam()) {
                        teamPoints.get(i.getAwayTeam()).add(3);
                        teamPoints.get(i.getAwayTeam()).add(i.getGoalsAwayTeam());
                        teamPoints.get(i.getAwayTeam()).add(i.getGoalsHomeTeam());
                    } else if (i.getGoalsHomeTeam() == i.getGoalsAwayTeam()) {
                        teamPoints.get(i.getAwayTeam()).add(1);
                        teamPoints.get(i.getAwayTeam()).add(i.getGoalsAwayTeam());
                        teamPoints.get(i.getAwayTeam()).add(i.getGoalsHomeTeam());
                    }
                    else {
                        teamPoints.get(i.getAwayTeam()).add(0);
                        teamPoints.get(i.getAwayTeam()).add(i.getGoalsAwayTeam());
                        teamPoints.get(i.getAwayTeam()).add(i.getGoalsHomeTeam());
                    }
                }
            }
            for (String i : teamPoints.keySet()) {
                outputStreamWriter.write(i + ", ");
                int h = teamPoints.get(i).get(1) - teamPoints.get(i).get(2);
                outputStreamWriter.write(teamPoints.get(i).get(0) + ", " + teamPoints.get(i).get(1) + ", " + teamPoints.get(i).get(2) + ", " + h + ", ");
				outputStreamWriter.flush();
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void leagueTable(String filename, List<HandballMatch> matches) {
		writePointsAndGoals(filename, matches);
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String line = reader.readLine();
			String[] h = line.split(", ");
			Map<String, ArrayList<Integer>> teamPoints= new HashMap<>();
			for (int i = 0; i < h.length; i = i + 5) {
				ArrayList<Integer> z = new ArrayList<>();
				z.add(Integer.parseInt(h[i + 1]));
				z.add(Integer.parseInt(h[i + 2]));
				z.add(Integer.parseInt(h[i + 3]));
				z.add(Integer.parseInt(h[i + 4]));
				teamPoints.put(h[i], z);
			}
			FileOutputStream fileOutputStream = new FileOutputStream(filename);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
			while (teamPoints.size() != 0) {
				String maxTeam = null;
				int maxPoints = -1;
				for (String i : teamPoints.keySet()) {
					if (teamPoints.get(i).get(0) > maxPoints) {
						maxPoints = teamPoints.get(i).get(0);
						maxTeam = i;
					}
				}
				outputStreamWriter.write(maxTeam + ", " + maxPoints + ", " + teamPoints.get(maxTeam).get(1) + ", " + teamPoints.get(maxTeam).get(2) + ", " + teamPoints.get(maxTeam).get(3) + ", " + System.lineSeparator());
				teamPoints.remove(maxTeam);
			}
			reader.close();
			outputStreamWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void specialLeagueTable(String filename, List<HandballMatch> matches) {
		writePointsAndGoals(filename, matches);
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String line = reader.readLine();
			String[] h = line.split(", ");
			Map<String, ArrayList<Integer>> teamPoints= new HashMap<>();
			for (int i = 0; i < h.length; i = i + 5) {
				ArrayList<Integer> z = new ArrayList<>();
				z.add(Integer.parseInt(h[i + 1]));
				z.add(Integer.parseInt(h[i + 2]));
				z.add(Integer.parseInt(h[i + 3]));
				z.add(Integer.parseInt(h[i + 4]));
				teamPoints.put(h[i], z);
			}
			FileOutputStream fileOutputStream = new FileOutputStream(filename);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
			while (teamPoints.size() != 0) {
				String maxTeam = null;
				int maxPoints = -1;
				for (String i : teamPoints.keySet()) {
					if (teamPoints.get(i).get(0) > maxPoints) {
						maxPoints = teamPoints.get(i).get(0);
						maxTeam = i;
					} else if (teamPoints.get(i).get(0) == maxPoints) {
						if (teamPoints.get(i).get(3) > teamPoints.get(maxTeam).get(3)) {
							maxPoints = teamPoints.get(i).get(0);
							maxTeam = i;
						} else if (teamPoints.get(i).get(3) == teamPoints.get(maxTeam).get(3)){
							if (teamPoints.get(i).get(1) > teamPoints.get(maxTeam).get(1)) {
								maxTeam = i;
							} else if (teamPoints.get(i).get(1) == teamPoints.get(maxTeam).get(1)) {
								if (i.compareTo(maxTeam) > 0) {
									maxTeam = i;
								}
							}
						}
					}
				}
				outputStreamWriter.write(maxTeam + ", " + maxPoints + ", " + teamPoints.get(maxTeam).get(1) + ", " + teamPoints.get(maxTeam).get(2) + ", " + teamPoints.get(maxTeam).get(3) + ", " + System.lineSeparator());
				teamPoints.remove(maxTeam);
			}
			reader.close();
			outputStreamWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
