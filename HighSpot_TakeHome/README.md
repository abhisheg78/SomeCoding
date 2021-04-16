# Highspot PlayList Ingest Take home exercise

## Overview
`Highspot_TakeHome` is a project which can ingest a a file `mixtape.json` with a changes file `changes.json`.
 The changes file contains a list of changes to be applied on `mixtape.json`. 
 project writes result to an output file which has the same structure with `mixtape.json`.
 
 All the input paths are taken as parameters. 
 Commandline help is shown with "-h" OR "--help" options.
 
## Features
The project support following changes that can be applied to `mixtape.json`
* Add a new play list, the play list must contain at least on song or else an error will be thrown.
* Remove a play list. If play list do not exist then an error is thrown.
* Add an existing song to an existing play list. Again existance validation is done.

## Prerequisite
* Java >= 11
* maven ( Only if you want to build - Optional )
* macOS

## Getting the Source
* git clone https://github.com/abhisheg78/SomeCoding

or

* visit https://github.com/abhisheg78/SomeCoding and download the Zip file and unzip.

## Building
* go to the project folder and run the following command:
```
mvn clean && mvn package
```

This is not required.  You should be able to run the program from command line without building it yourself.

## Usage
### Get Help
* go to the project folder ( "HighSpot_TakeHome" ) , run the following command:
```
1) java -Dfile.encoding=UTF-8 -classpath ./target/classes:./target/jackson-databind-2.9.10.3.jar:./target/jackson-annotations-2.9.10.jar:./target/jackson-core-2.9.10.jar:./target/junit-4.13.jar:./target/hamcrest-core-1.3.jar:./target/picocli-4.2.0.jar com.highspot.mixtape.takehome.Main -h  [ This will show help ]

2) java -Dfile.encoding=UTF-8 -classpath ./target/classes:./target/jackson-databind-2.9.10.3.jar:./target/jackson-annotations-2.9.10.jar:./target/jackson-core-2.9.10.jar:./target/junit-4.13.jar:./target/hamcrest-core-1.3.jar:./target/picocli-4.2.0.jar com.highspot.mixtape.takehome.Main -i ./src/main/resources/mixtape-data.json -c ./src/main/resources/changes.json -o output.json

This will actually run the program.  All paths are relative here so if you run this from "HighSpot_TakeHome" folder ir should run fine.  Output.json will be produced inside current directory.

```


### Input files
* Input files are under HighSpot_TakeHome/src/main/resources


## Data structure
### Data Structure For MixTape.json
There is an example of MixTape.json, it's location is HighSpot_TakeHome/src/main/resources/mixtape-data.json.

It mainly contains three section:
1. Users
2. Songs
3. PlayList.

* Data example for User 
```
{
  "id" : "1",
  "name" : "Albin Jaye"
}
```
* Data example for Song
```
{
 "id" : "33",
 "artist": "Drake",
 "title": "Nice For What"
}
```
* Data example for PlayList
```
{
 "id" : "2",
 "user_id" : "3",
 "song_ids" : ["6", "8", "11"]
}
```
 
### Data Structure For Change File.
The changes file is an array of changes. Example : - HighSpot_TakeHome/src/main/resources/changes.json.
Here is a brief example of change file
```
[
    {
        "action" : "add_playlist",
        "playlist" : {
            "id" : "1",
            "user_id" : "1",
            "song_ids" : ["6", "7", "13" ]
        }
    },
    {
        "action" : "remove_playlist",
        "playlist_id" : "1"
    },
    {
        "action" : "add_song",
        "song_id" : "1",
        "playlist_id": "3"
    }
]
```
* Add a new play list example
```
{
    "action" : "add_playlist",
    "playlist" : {
        "id" : "2",
        "user_id" : "3",
        "song_ids" : ["6", "2", "13"]
    }
}
```
* Remove a play list example
```
{
    "action" : "remove_playlist",
    "playlist_id" : "1"
}
```


## Large file processing ( Scale up ) 

I think it will be more efficient to move the file based operations to APIs hosted in servers. If MixTape data grow it will be inefficient to load it in memory and do the changes. Also it will be inefficient to write back the output after every change. We should have the data in data storage like SQL or NO-SQL data bases. We will have APIs for each change operation type. They will be REST APIs. Each unit of change will result into a REST API call which will ultimately translate into write operations on the Data base. Data base tables will represent the current state of the Data and each changes will be independent data base operations invoked through the APIs. The APIs can then perform validations, authentication, rate limiting , produce metrics apart from executing the change itself. 

The data seems to be strongly relational so we can go for RDBMS . We can have three tables User, Songs, PLaylist.  We have Ids in each table so we will build index on that so operations are performant. 

If the number of operations grow ( More and more changes are applied ) we can scale by adding more servers which are added behind load balancer ( multiple load balancers with a Leader - follwer set up so in case of leader failure follower can take over ).

If the Data grows we will introduce data sharding techniques to partition the data . 

Let's briefly talk about some of the key aspects of the system

1) Availability -  We should attain availability by having multiple servers hosting the APIs and also having Data base replicas ( Leader - Follower set up ).
2) Performance -  By Building Indexes.  We should use Cache as well so when we look up data for validations those operations are fast. Here operations are write operations but I assume we will have Read as well ( at least when we want to know the state of the data like we were dumping in output file ) and read operations can be improved substantially throuch caching.
3) Durability - Since we are having Data base with replicas data should not be lost.
4) Consistency - RDBMS have strong consistency ( ACID property).
5) Fault tolerance - This system will be fault tolerant as we have redundancy in every layer. If we have multiple data centers we should be able to switch traffic from one to another in case of major disaster.

Finally if Data goes too big we should have Data sharding.  Sharding techniques should be separated in another service ( abstracted out from API servers ).  I think Id based partitioning of the tables should be good in this case. 
 




