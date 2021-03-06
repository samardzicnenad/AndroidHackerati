# <b>The Hackerati Android course</b>

<b>01) Greeting in Tongues</b> - My first android app (advanced version of traditional 'Hello world!')

For the selected list of languages, when related button pressed, the application shows the translation of 'Hello world!' to the appropriate language (Thanks, Google Translate).

The inspiration for the application name was the Talking Heads album, so I nicked the artwork for the icon, as well.

<b>02) Three Card Monte</b> - An example of communication between activities

The Queen of Spades wins - try to guess where it is!

The app shuffles the cards and shows the card face when a card back is pressed. Card images are being used.

I've considered implementing a 'hustle mode' which doesn't allow you to win, but this is left as an improvement for one of the future versions. You're safe ;) !

UPDATE: version 2 uses SharedPreferences instead of Extras to communicate between the activities.

<b>03) Solar ListView</b> - Using a custom adapter to build a ListView of the Solar system celestial bodies

The application groups the Solar system planets together with their moons, randomizes the position of the group in the list and randomizes the background color of the group.

Every item in the ListView contains a celestial body's name and image.

When the item is pressed, the app opens the page for that celestial body in the web browser.

I have used both images and the pages from http://www.seasky.org.

<b>04) Fibonacci</b> - An example of database usage

Fibonacci application takes as an input a positive (including 0) integer which represents the highest index of the Fibonacci number in a sequence you would like to get displayed (0th element is 0; 1st element is 1, 2nd element is 1; etc).

When the "CALCULATE!" button is clicked, the app deletes all of the existing records from the DB table, calculates a Fibonacci sequence; writes it to a DB and then reads it back and displays it to the user. This needless set of steps is performed every time in order to illustrate CRUD opperations in Android.

DB platform is SQLite; DB name is 'HackeratiDB'; DB table name is 'fibonacci' and it has only one VARCHAR column named 'fib_no'.

In order for app to be able to calculate any Fibonacci number and avoid type overflow, I cast Strings to BigIntegers, perform the addition and then cast the result back to String.
