Gamesys coding exercise - Payment tracker
-----------------------------------------

--------------------------------------------------------------
Run application instructions (considering maven is installed):
--------------------------------------------------------------
-install application at first with command:
	mvn clean install

-run application from command line with command:
	mvn exec:java                         ... start app without any parameters provided
	mvn exec:java -Dexec.args="test.txt"  ... start app with input file passed in a parameter

-after app is started user is allowed to type input into the command line

-expected input format:
	[CURRENCY] [AMOUNT]
		where [CURRENCY] = 3 letter currency symbol
		 and  [AMOUNT] = Number format
		 and space character is between [CURRENCY] and [AMOUNT]

-sample input:
	USD 200

-application exits when user types "quit"

-----------
Assumptions:
-----------
If the user enters invalid input error message is displayed. Program continues with another input request.
Reading input from file is skipped when the file doesn't exists.
Currency symbol may be any lowercase or uppercase 3 letter code.
Bonus feature is not implemented.