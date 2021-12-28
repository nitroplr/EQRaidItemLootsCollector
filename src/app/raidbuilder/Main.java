package app.raidbuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //CHANGE this filepath to your own character's filepath
        String eqLogFilePath = "C:\\Users\\Public\\Daybreak Game Company\\Installed Games\\EverQuest\\Logs\\eqlog_Blastoff_mischief.txt";
        //CHANGE this string to the name of your guild's channel for creating new raids
        //this is case sensitive to how the channel appears in eq; TestChannel is not the same as testchannel
        String eqChannel = "testchannel";
        //CHANGE this string to the command you will send to eqChannel to create a new raid
        String newRaidCommand = "testcommand";
        //this is the path to your project's local copy of BlockList.txt
        //add items to be blocked by the collector to this text file with a new line for each item
        //this is case insensitive
        String blockedItemFilepath = new File("").getAbsolutePath();
        blockedItemFilepath = blockedItemFilepath.concat("\\BlockList.txt");
        //creating a list of items to not collect
        File blockedItems = new File(blockedItemFilepath);
        ArrayList<String> blockedItemsList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(blockedItems);
            while (scanner.hasNext()) {
                blockedItemsList.add(scanner.nextLine().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Reading in the log file for gathering items
        File logFile = new File(eqLogFilePath);
        ArrayList<String> logString = new ArrayList<>();
        ArrayList<String> itemLoots = new ArrayList<>();
        ArrayList<String> itemLootsWithLooter = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(logFile);
            while (scanner.hasNext()) {
                logString.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //get the start of the most recent raid
        int indexOfLastNewRaidCommand = 0;
        for (int i = 0; i < logString.size(); i++) {
            //a new raid command has been found
            if (logString.get(i).contains(eqChannel) && logString.get(i).contains(newRaidCommand)) {
                indexOfLastNewRaidCommand = i;
            }
        }

        //get raid loots since the start of the raid
        for (int i = indexOfLastNewRaidCommand; i < logString.size(); i++) {
            //this is a loot string
            if (logString.get(i).contains("--") && logString.get(i).contains("has looted")) {
                int indexOfDashes = logString.get(i).indexOf("--");
                int indexOfHasLooted = logString.get(i).indexOf("has looted");
                int indexOfFrom = logString.get(i).lastIndexOf("from");
                String item = logString.get(i).substring(indexOfHasLooted + 13, indexOfFrom - 1).toLowerCase().trim();
                String looter = logString.get(i).substring(indexOfDashes + 2, indexOfHasLooted - 1);
                if (!blockedItemsList.contains(item)) {
                    itemLoots.add(item);
                    itemLootsWithLooter.add(looter + " - " + item);
                }
            }
        }

        //output the loots to an output file
        String outfilePath = new File("").getAbsolutePath() + "\\RaidLoots.txt";
        try {
            FileWriter outfile = new FileWriter(outfilePath);

            StringBuilder outstring = new StringBuilder();
            for (String itemLoot : itemLoots) {
                outstring.append(itemLoot).append(System.lineSeparator());
            }
            for (String itemLoot : itemLootsWithLooter) {
                outstring.append(itemLoot).append(System.lineSeparator());
            }
            outfile.write(outstring.toString());
            outfile.flush();
            outfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
