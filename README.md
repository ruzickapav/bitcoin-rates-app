# Bitcoin Rates Server
Server shows graph with history of Bitcoin/USD rate

## Run Docker
Increase docker memory to 6 GiB in docker preferences because `npm run build` 
needs more than default 2 GiB

`docker-compose up`

Or you can run from prebuild images using

`docker-compose -f docker-compose.latest.yml up`

Then historical graph can be seen on [Bitcoin Graph](http://localhost:80)
You must refresh the site if you want to see updated data

