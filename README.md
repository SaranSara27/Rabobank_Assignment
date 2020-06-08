
# Rabobank Customer Statement Processor
Rabobank receives monthly deliveries of customer statement records. This information is delivered in JSON Format. These records need to be validated.

## Input
The format of the file is a simplified format of the MT940 format. The format is as follows:

Field  |Description
----|----
Transaction reference  | A numeric value
Account number   | An IBAN 
Account | IBAN 
Start Balance | The starting balance in Euros 
Mutation | Either and addition (+) or a deducation (-) 
Description | Free text 
End Balance | The end balance in Euros 

## Expected output
There are two validations:
* all transaction references should be unique
* The end balance needs to be validated ( Start Balance +/- Mutation = End Balance )

# Steps to run the application:
1.	Clone the project Rabobank (Spring REST project).
```bash
git clone https://github.com/SaranSara27/Rabobank_Assignment
```
2.	Run maven command to install dependency.
3.	Compile and run the project using tomcat 9.
4.	This Web service application have one active service to process json file. please find service url below,
http://localhost:8080/Robobank/rabobank/processStatement
5.	Upload input json file in the service using postman client.
6.	The input file will be validated based on two conduction mentioned in the problem statment.(validation condition mentioned in expected output section)
      *	Duplicate Transaction key check, 
      *	End balance calculation check. (endbalance = startbalance – mutation)
7.  Finally invalid records will be getting as webservice response.


