Distributed Pipeline
ReportingService (Akshay Dharamkar)

REST Endpoint Documentation

Example Report JSON:
{
        "id": 1,
        "timestamp": 1508830456000,
        "userid": "akshaydv",
        "message": "generic message",
        "severity": "fatal"
 }

GET(Get All Reports)
172.23.238.216:8080/v1.0/reportingservice/report
	{
        id;
	timestamp;
	userid;
	message;
	severity
	}

GET(GetReportById)
172.23.238.216:8080/v1.0/reportingservice/report/{id:0-9}
	{
        id;
	timestamp;
	userid;
	message;
	severity
	}

GET(GetReportByUserId)
172.23.238.216:8080/v1.0/reportingservice/report/{userid:a-zA-Z}
	{
        id;
	timestamp;
	userid;
	message;
	severity
	}

GET(GetReportByYear)
172.23.238.216:8080/v1.0/reportingservice/report/year/{year:0-9}
	{
        id;
	timestamp;
	userid;
	message;
	severity
	}

POST(SaveReport)
172.23.238.216:8080/v1.0/reportingservice/report
	{
        id;
	timestamp;
	userid;
	message;
	severity
	}


ReportingService Kafka Documentation

Publishing Data Into Kafka
	Report(report-topic) : {
        id;
	timestamp;
	userid;
	message;
	severity
	}


Consuming Data from Kafka
{
       Report(report-topic){
	id;
        timestamp;
	userid;
	message;
	severity
	}
}


PersistenceManagerService(Akshay Vaibhav)

REST Endpoint Documentation
```
GET
<ip_address>:8080/v1.0/workflow/get
	{
	id;
	sequence;
	userid;
	}
GET BY ID
<ip_address>:8080/v1.0/workflow/get/id
	{
	id;
	sequence;
	userid;
	}

POST
<ip_address>:8080/v1.0/workflow/save
	{
	id;
	sequence;
	userid;
	}
UPDATE
<ip_address>:8080/v1.0/workflow/update
DELETE
<ip_address>:8080/v1.0/workflow/delete/id
{

}

PersistenceService Kafka Documentation

Publishing Data Into Kafka
{
id;
sequence;
userid;
}



<h3>Task1 Service (Akshay Dharamkar)</h3>

REST Endpoint Documentation

GET(GetTask1Response)
172.23.238.216:8080/v1.0/task1service/task
{
String;
}


TaskOneService Kafka Documentation


Publishing Data Into Kafka
{
     String(task-response),
     Report(report-topic){
	id;
        timestamp;
	userid;
	message;
	severity
	}
}

Consuming Data from Kafka
{
        String(task1-topic)
}

Task2 Service (Akshay Dharamkar)

REST Endpoint Documentation

GET(GetTask2Response)
172.23.238.216:8080/v1.0/task2service/task
{
String;
}


Task2Service Kafka Documentation


{
     String(task-response),
     Report(report-topic){
	id;
        timestamp;
	userid;
	message;
	severity
	}
}

Consuming Data from Kafka
{
        String(task2-topic)
}


Engine Service (Vishal Kumar)

Rest Endpoint Service

GET(GetWorkflow)
172.23.238.202:8080/v1.0/engineserviceservice/workflow
{
String;
}

EngineService Kafka Documentation


Publishing Data Into Kafka
{
Dummy String;
}

Consuming Data from Kafka

Persistence Object

{
 id;
userid;
sequence;
}

Task
{
Dummy String;
}

