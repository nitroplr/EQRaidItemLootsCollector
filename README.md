# EQRaidItemLootsCollector
Outputs all of the raid's loots that occur after a new raid command.

1. This is a java project, so setup a java ide.
2. Clone this repo and open up the project code in src/app/raidbuilder/main
3. Modify the eqLogFilePath, eqChannel, and newRaidCommand variables accordingly.
4. Add items to BlockList.txt to ignore junk
5. Send a message in everquest to a custom eqChannel containing the newRaidCommand to start a new raid
6. Run the program and items will be output to RaidLoots.txt with all items first, then all items with who looted them.  This will only output items that have been looted since step 5.
