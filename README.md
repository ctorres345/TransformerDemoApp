### TRANSFORMER ANDROID APPLICATION
This project has been develop as a technical mobile test for Aequilibrium

## Requirements

Create an application that makes use of their AllSpark API to manage a list of transformers
and perform different use cases with that data.

The Highlighted Operations were the following

* Create and Edit new unit and send them to their API
* Fetch unit already registered
* Simulate a battle between them based on their specs present in the response objects

The prerequisites were the following

* Application must target Android 4.4, API level 19 and above
* Application should be responsive over multiple screen sizes
* Application should be built in Kotlin
* UI libraries not allowed
* No data persistance allowed

## Arquitecture

For the whole project I applied Clean Architecture by separating concerns into their own modules. 

* APP -> Presentation
* Domain
* Data

## Libraries and Technologies I used?

* Livedata
* Dependency Injection with Hilt
* Material Library
* MockK
* Coroutines
* Navigation Library
* Retrofit + Okhttp3

## Personal Commentary

I'm not that familiar with Transformer as a whole but the project was really fun. Instead of just
trying to make the CRUD as simple as possible I tried to make it look as a game so I appreciate the
effort of the people in charge of this material. It was a good experience.

The part where I had to create a new Transformer I decided to hide the stats values behind predifined
unit types, this would make it easier to register since going through each of the values can be open 
to more customization but as a user I think those are more steps. The doc didn't mentioned to register
all the fields so I figured I could get over that with this strategy.

*UPDATE (28/12/20) -> Regarding this previous comment I figured out the custom stat could be used for testing
inside the app so I decided to add it anyways.*

Using another mock Json to get a list of predefined units was also helpful. It made it easier to
test the battle functionality.

Thanks to https://www.camphortree.net/tf/specs/ that served as a reference for that data

## How can this project be improved?
* Create Local implementation for the use cases. Since for this demo I can't use data persistance there's a lot of use cases that can take advantage of that to manage your units.
* Implement secure shared preferences
* Add more unit/UI test
* Use Kotlin DSL (I did but I had some config problem with some plugins so I didn't spent much time on it)
* Improve Gradle dependency scripts (Kotlin dsl can make this easy)
* Add lint checks
* The fight screen will always generate the same result since the teams does not change we can improve this by shuffling the teams. This wasn't on the requirements so I didn't want to go beyond the scope for now.
* Use a String resource manager to access safely string.xml resources on places without android context
* Dark Theme can be supported easily but it would need some tweaks in colors and resources in order to make sure it looks good

## Notes

* The Api doc says that the TransformerId is an Int but in reality is a String
* For the battle section I wasn't sure if it always going to be Decepticons vs Autobots so I kinda assumed that for some validations. Certainly the logic for that use case could be modified to support any unit vs any unit.

## Changelog

Version 1.0.0

* Inventory screen implement to check your registered units
* Summon screen to recruit new units and create custom ones
* Battle screen to simulate a Battle between your units
* Added TeamBattleResultUseCaseImplTest and ValidateVersusTeamsUseCaseImplTest

Version 1.0.1

* Create ModelExtTest to add some testing for the extensions from that class
* Create EditUnitViewModelTest and CustomUnitViewModelTest
* Quality of life changes inside the CustomUnitDialog/EditUnitDialog
* New Types added in order to easily test the rules in the battle mode.
* Added custom type for custom stat creation for new units
* Small UI refactor to avoid hardcoded strings