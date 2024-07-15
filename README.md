# GoNature - Park Management Application
This project was developed by our mid-term group based on the requirements of <br>the Engineering Methods For Developing Software Systems (שיטות הנדסיות לפיתוח מערכות תוכנה).<br>

## About GoNature
GoNature is a two-layered architecture design project (client-server) with a MySQL Database, <br>
serving the purpose of managing parks, offering a user-friendly interface for visitors to book reservations ahead of time while helping the park's management monitor the parks' capacities.

## Server Key Features
- Displaying the state of the server (online/offline)
- Import user data from an external source
- Monitor client connectivities dynamically

<img src="https://gyazo.com/8b5e207cefc10a9b4b0237493504c183.gif" alt="Server Listening to incoming connects" width="600" height="300">

<img src="https://gyazo.com/5e44f44c6f0d64e5cdc27d00ee989ec0.gif" alt="Server Listening to incoming disconnects" width="600" height="300">



## Client Key Features
- Offers two validation options, one for users and one for new visitors
- Observable to changes<br>
- Automatic message simulation (Reminders for upcoming reservations, System messages and more)

##### Visitor Landing Interface and Billing interface

<img src="https://gyazo.com/3c56641671dc86b4973782b348fbd92a.png" alt="Visitor Landing" width="400" height="300">


<img src="https://gyazo.com/8db9f879c7914523161408ec3ba43685.png" alt="Visitor Landing" width="400" height="300">

#### Options in case of an unavailable reservation
- the table suggests alternative booking times based on the current reservation, offering available times around the originally picked date<br>
<br>
<img src="https://gyazo.com/dd4fae18b643b299db1e63b153d05996.png" alt="Unavailable reservation" width="400" height="300">

#### Automatic message
- Show casing an automatic message that is sent to a user that has a reservation in his waiting list, and the park can handle his reservation now.
<br><br>
<img src="https://gyazo.com/72433c0598f8d805abd1a957bfbd97ad.gif" alt="Instant message" width="400" height="300">
<img src="https://gyazo.com/1d71289acbe5ca2b831d30d2ffed1596.png" alt="outcome" width="400" height="300">