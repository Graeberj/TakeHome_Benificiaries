First Step was to create the empty activity and remove all the unnecessary code from the activity.
This included any of the Compose code that was generated by the template.

Next I wanted to create a structure that I wanted to follow for the app. 
This involved the following:
- creating a model data class for the data 
- a custom view for the list item that I would be using in the list
- a custom view for the detail view that would be used to display the details of the item
- a custom view for the full list view that would be used to display the full list of items
- a viewmodel class that would be used to store the data and handle the logic for the app
- a repository class that would be used to fetch the data from the file and parse it.


my goal creating this was to separate the concerns using the clean architecture principles and using MVVM as the design pattern.
I wanted to make sure that the view was only responsible for displaying the data and not handling any logic.
this would separate the app into data, domain, and presentation layers, with the viewmodel acting as the bridge between the domain and presentation layers.
I created a mapper class to map the models from the data layer to the domain layer and vice versa. 
this would allow me to use the parcelized data in the presentation layer 


things I would have done differently:
 - spent more time on the UI and making it look better
 - added more(any) tests to the app
 - taken the time to check where scndMailLine was being parsed and updated it so it wasn't 
      changing null into a string
 - formatted the phone number to be more readable
 - created a button to go back to the list view from the detail view
 - worked a little more independently on each layer and commit more

Further steps to improve the app:
 - usually data would be fetched from a network call, so I would have added a functionality to handle this
 - if the data was being fetched from a network call, I would have added a caching to store the data
 - added a loading state to the app to show the user that the data is being fetched
 - created a login screen, possibly have the user enter their email and password to access the list
 - added a search bar to the list view to allow the user to search for a specific item