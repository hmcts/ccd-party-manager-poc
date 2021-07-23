# CCD-PARTY-MANAGER-POC

## Purpose

To prove the use of a party manager API as a viable solution for complex case types

## Setup

Run the application in your IDE, it will spin up on port 4550.

## DB

This project uses a h2 in memory database. It is accessible locally with the following parameters
- url: `jdbc:h2:mem:testdb`
- username: `sa`
- password:

The console is available at `http://localhost:4550/h2-console`

## Endpoints

GET: for getting interactions for a given caseId (`/api/12345`)

Example Response:
```json
{
    "id": "1",
    "ccdReferenceId": "12345",
    "interactionBy": 1,
    "interactionType": "Create case",
    "interactionDate": "2021-06-18T10:58:42.836701",
    "description": "\"a description\"",
    "parentId": 1
}
```

POST: for saving interactions for a given caseId (`/api/12345`)

Example Payload:
```json
{
    "ccdReferenceId": "12345",
    "interactionBy": 1,
    "interactionType": "Create case",
    "description": "a description",
    "parentId": 1
}
```

## Postman

Postman scripts are located in `tools/postman-collections`. There is a get and post template for interactions.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

