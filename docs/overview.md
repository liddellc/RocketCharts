# Overview
RocketCharts was developed out of a need to chart the altimeter data from my high-powered rocket flights for analysis. Using spreadsheets was tedious and lacked repeatability. What I needed was a solution that would easily ingest the altimeter data and make it available for display. 

# Goals
1. Cloud native - entire solution will run on a cloud provider.
1. On-Demand runtime - minimize reoccurring costs through the use of on-demand offerings over running services.
1. Learning - I will be learning as I go so I expect mistakes and refactoring to occur.

# Requirements

## Data Ingestion
1. Supported Data Sources
    1. Eggtimer WiFi Altimeters (Quantum, Proton)
    1. Eggtimer TRS Altimeter
1. Data Destination - data will be stored in a cloud native, querable data system.
1. Source files will be consumed from a cloud folder/bucket. Source files should be able to be uploaded from mobile device in the field.
1. Source files should be ingested and available for use within 10 min.

## User Interface
1. User interface will be a single page app with static assets hosted in the cloud.
1. User interface will query cloud data system to retrieve all data necessary to render all UI functionality.

# User Stories

## Supported

### Login
1. As a user, I am presented with a login page requesting my username and password. Entered correctly, I am allowed into the app.
2. As a user, if I enter incorrect information I am presented an error.

### Import Flight Data
1. As a user, there is a link that allows me to upload a file to import new data.

### User Settings
1. As a user, there is a link on the main page that allows me to modify the following user specific settings.
    1. Units - feet vs. meters

### Logout
1. As a user, there is a link on the main page that allows me to logout of my user account and returns me to the login page.

### Flight Selector
1. As a user, there is a way for me to select between the different flight data that I have already imported.

### Chart
1. As a user, a line chart of the selected flight data will display the following data:
    1. Altimeter chart - time series
    1. Accelleration chart - time series
    1. Apogee - data point on altimeter chart
    1. Nose-over - data point on altimeter chart
    1. Drogue deploy - data point on altimeter chart
    1. Main deploy - data point on altimeter chart
1. As a user, I am able to enable/disable each line on the chart.
1. As a user, I am able to select and drag to "zoom" into a subset of the chart data

### Flight Meta Data
1. As a user, the flight meta data will be displayed. This information will include:
    1. Rocket name
    1. Motor
    1. Date
    1. Location
1. As a user, I am allowed to edit/update each of the meta data fields.

### Flight Comparison
1. As a user, I am shown a list of comparable flights (same rocket, same motor) that can be charted along side the current data for comparison purposes.

## Not Supported

### Login
1. As a user, the login page contains a link to reset my password.
1. As a user, I'm allowed to login with my Facebook or Google login