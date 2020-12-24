# TRANSFORMER ALL SPARK BATTLE DEMO APPLICATION

This project has been develop as a technical mobile test for Aequilibrium.

## REQUIREMENTS

Create an application that makes use of their AllSpark API to manage a list of transformers
and perform different use cases with that data.

The Highlighted Operations were the following

• Create and Edit new unit and send them to their API
• Fetch unit already registered
• Simulate a battle between them based on their specs present in the response objects

The prerequisites were the following

• Application must target Android 4.4, API level 19 and above
• Application should be responsive over multiple screen sizes
• Application should be built in Kotlin
• UI libraries not allowed

## ARQUITECTURE

For the whole project I applied Clean Architecture by separating concerns into their own modules. 

• APP -> Presentation
• Domain 
• Data

## TECNOLOGIES USED IN THE PROJECT

• Livedata
• Dependency Injection with Hilt
• Material Library
• MockK
• Coroutines
• Navigation Library
• Retrofit + Okhttp3

## PERSONAL COMMENTARY

I'm not that familiar with Transformer as a whole but the project was really fun. Instead of just
trying to make the CRUD as simple as possible I tried to make it look as a game so I appreciate the
effort of the people in charge of this material. It was a good experience.

The part where I had to create a new Transformer I decided to hide the stats values behind predifined
unit types, this would make it easier to register since going through each of the values can be open 
to more customization but as a user I think those are more steps. The doc didn't mentioned to register
all the fields so I figured I could get over that with this strategy.

Using another mock Json to get a list of predefined units was also helpful. It made it easier to
test the battle functionality.

Thanks to https://www.camphortree.net/tf/specs/ that served as a reference for that data

## NOTES

• The Api doc says that the TransformerId is an Int but in reality is a String.
• This can be improved with more test and some UI refactor since it's pretty simple as it is
• Increase the minimum sdk can help to use some libraries such as the security one in order to use
encrypted shared preferences.