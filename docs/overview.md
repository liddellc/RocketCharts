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

### Display
1. Users will be able to select specific flights to display.
1. Display will show
    1. Meta data
    1. Altimeter chart - time series
    1. Accelleration chart - time series
    1. Apogee - data point on altimeter chart
    1. Nose-over - data point on altimeter chart
    1. Drogue deploy - data point on altimeter chart
    1. Main deploy - data point on altimeter chart
1. User will be able to enable/disable each 