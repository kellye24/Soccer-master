package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.CharBuffer;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    // dummied up variable; you will need to change this
    private Hashtable<String, SoccerPlayer> database = new Hashtable<>();
    //private int num = 0;
    private int next = 0;

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        //Log.i("addPlayer", database.get(firstName + " ## " + lastName).toString());
        if (database.get(firstName + " ## " + lastName) == null) {
            // the player does not exist, let's add them
            SoccerPlayer newPlayer = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
            database.put(firstName + " ## " + lastName, newPlayer);
            return true;
        }
        return false;
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        if (database.get(firstName + " ## " + lastName) != null) {
            // the player exists, let's get them
            // no way... it worked? ok then lol
            database.remove(firstName + " ## " + lastName);
            return true;
        }
        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        if (database.get(firstName + " ## " + lastName) != null) {
            // the player exists, let's get them
            // no way... it worked? ok then lol
            return database.get(firstName + " ## " + lastName);
        }
        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        if (database.get(firstName + " ## " + lastName) != null) {
            // the player exists, let's add to their goals
            database.get(firstName + " ## " + lastName).bumpGoals();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if (database.get(firstName + " ## " + lastName) != null) {
            // the player exists, let's add to their goals
            database.get(firstName + " ## " + lastName).bumpYellowCards();
            return true;
        }

        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if (database.get(firstName + " ## " + lastName) != null) {
            // the player exists, let's add to their goals
            database.get(firstName + " ## " + lastName).bumpRedCards();
            return true;
        }

        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        int num = 0;

        if (teamName == null) {
            return database.size();
        } else {

            for (String key : database.keySet()) {
                //Log.i("key", "Key set is " + key + " || " + (database.get(key)).getTeamName() + " is... " + teamName);
                if ((database.get(key)).getTeamName().equals(teamName)) {
                    //Log.i("key", "Key set is " + database.get(key).getTeamName() + "");
                    num = num + 1;
                }
            }
        }
        return num;
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerIndex(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerIndex(int idx, String teamName) {
        int size = 0;
        int all = 0;
        int num = 0;


        for (String key : database.keySet()) {
            if ((database.get(key)).getTeamName().equals(teamName) && teamName != null) {
                //Log.i("pIndex", "COUNT idx: " + idx + " teamName" + teamName + " getTeamName: " + (database.get(key)).getTeamName());
                size++;
            } else {

            }
            all++;
        }

        //Log.i("pIndex", "INITIAL idx: " + idx + " teamName" + teamName + " size: " + size + " all: " + all);
        for (String key : database.keySet()) {

            if (teamName != null) {
                if ((database.get(key)).getTeamName().equals(teamName)) {
                    Log.i("pIndex", "CHECK: idx: " + idx + " num:" + num + " teamName" + teamName + " size: " + size);
                    //Log.i("pIndex", "MAX idx: " + idx + " num:" + num + " teamName" + teamName + " size: " + size);
                    if (next != num && idx < size) {
                        next = num;
                            return database.get(key);
                        } else if (idx >= size) {
                            return null;
                        }
                    }
                }

            if (teamName == null) {
                if (num == idx) {
                    Log.i("pIndex", "ALL idx: " + idx + " num:" + num + " teamName" + teamName + " size: " + all);
                    return database.get(key);
                } else if (idx >= all) {
                    Log.i("pIndex", "ALL OVER idx: " + idx + " num:" + num + " teamName" + teamName + " size: " + all);
                    return null;
                }
            }
            num++;
        }
        return null;

/*
        // Check for all matching values for teamName and establish size
        for (String key : database.keySet()) {
            if ((database.get(key)).getTeamName().equals(teamName) && teamName != null) {
                //Log.i("pIndex", "idx: " + idx + " teamName" + teamName);
                size++;
            }
            all++;
        }

        Log.i("pIndex", "idx: " + idx + " teamName" + teamName + " size: " + size);
        for (String key : database.keySet()) {

            if (teamName != null) {
                if ((database.get(key)).getTeamName().equals(teamName)) {
                    Log.i("pIndex", "CHECK: idx: " + idx + " num:" + num + " teamName" + teamName);
                    if (num == idx) {
                        Log.i("pIndex", "idx: " + idx + " num:" + num + " teamName" + teamName);
                        num++;
                        return database.get(key);
                    } else if (idx > size) {
                        num--;
                        return null;
                    }
                }
            } else if (num == idx) {
                    num++;
                    return database.get(key);
                } else if (idx > all) {
                    num--;
                    return null;

                }
                num++;
            }
            return null;*/
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) throws FileNotFoundException {

        // read all player data from file
        // if name of existing player conflicts with what's
        // read from the file,
        /*
        if (database.get(firstName + " ## " + lastName) == null) {
            // the player does not exist, let's add them
            SoccerPlayer newPlayer = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
            database.put(firstName + " ## " + lastName, newPlayer);
            return true;
        }*/

        Scanner sc = new Scanner(file);

        int[] values = {};

        // first name // last name // team name // uniform // goals // yellow

        /*
        if (database.get(firstName + " ## " + lastName) == null) {
            // the player does not exist, let's add them
            SoccerPlayer newPlayer = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
            database.put(firstName + " ## " + lastName, newPlayer);
            return true;
        }
        */



        while (sc.hasNextLine()) {
            String first = sc.nextLine();
            String last = sc.nextLine();
            String team = sc.nextLine();
            String uniform = sc.nextLine();
            String goals = sc.nextLine();
            String yellow = sc.nextLine();
            String red = sc.nextLine();
            //System.out.println(data);
            //Log.i("writes", "First: " + first + " Last: " + last); // " Team: " + team + " Uniform: " + uniform + " Goals: " + goals + "");

            // database.get(firstName + " ## " + lastName)

            for (int i = 0; i < database.size(); i++) {
                if (database.get(first + " ## " + last) != null) {
                    // checks if the scanned names exist
                    // if they do... then we read in the next
                    // replace the player
                    removePlayer(first, last);
                }
                SoccerPlayer player = new SoccerPlayer(first, last, Integer.parseInt(uniform), team);
                database.put(first + " ## " + last, player);

                for (int a = 0; a < Integer.parseInt(goals); a++) {
                    database.get(first + " ## " + last).bumpGoals();
                }

                //database.put(first + " ## " + last);
                // String firstName, String lastName,
                // int uniformNumber, String teamName
            }
            //Log.i("write", "Data: " + data);
        }
        sc.close();
        return file.exists();
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        for (String key : database.keySet()) {
            //writer.write(String.valueOf(database.get(key).getFirstName()));
            //writer.write((String.valueOf(database.get(key).getFirstName())));
            //writer.write((String.valueOf(database.get(key).getLastName())));
            //writer.write((String.valueOf(database.get(key).getTeamName())));
            //writer.write((String.valueOf(database.get(key).getUniform())));
            //writer.write((String.valueOf(database.get(key).getGoals())));
            //writer.write((String.valueOf(database.get(key).getYellowCards())));
            //writer.write((String.valueOf(database.get(key).getRedCards())));

            //writer.println(logString(database.get(key).getFirstName()));
            writer.println(logString(String.valueOf(database.get(key).getFirstName())));
            writer.println(logString(String.valueOf(database.get(key).getLastName())));
            writer.println(logString(String.valueOf(database.get(key).getTeamName())));
            writer.println(logString(String.valueOf(database.get(key).getUniform())));
            writer.println(logString(String.valueOf(database.get(key).getGoals())));
            writer.println(logString(String.valueOf(database.get(key).getYellowCards())));
            writer.println(logString(String.valueOf(database.get(key).getRedCards())));
        }
        writer.close();

        return false;
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        //
        HashSet<String> set = new HashSet<String>();
        for (String s : database.keySet()) {
            set.add(database.get(s).getTeamName());
        }
        // originally was... return new HashSet<String>;
        return set;
    }

    /**
     * Helper method to empty the database and the list of teams in the spinner;
     * this is faster than restarting the app
     */
    public boolean clear() {
        if(database != null) {
            database.clear();
            return true;
        }
        return false;
    }
}
