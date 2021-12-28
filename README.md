# EQRaidItemLootsCollector
Outputs all of the raid's loots that occur after a new raid command.

1. This is a java project, so setup a java ide.
2. Clone this repo and open up the project code in src/app/raidbuilder/main
3. Modify the eqLogFilePath, eqChannel, and newRaidCommand variables accordingly.
4. Add items to BlockList.txt to ignore junk (must be all lowercase)
5. Send a message in everquest to a custom eqChannel containing the newRaidCommand to start a new raid
6. Alternately, you can link items to the channel separated by |, eq does this by default if you right click the mob's name in adv loot and link it.  These items will still be filtered by the block list.
7. Run the program and items will be output to RaidLoots.txt with all linked items first, then all items and finally all items with who looted them.  This will only output items that have been linked or looted since step 5.