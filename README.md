# BSFDV Challenge

### Requirements
Create an application Backend with below details:

Tech stack to be used:

    Java/Kotlin (Kotlin is preferred)
    Spring Boot

Application Requirements:

 - [X] Service should expose a Rest API to accept `money transfers` to other accounts. Money transfers should persist a new balance of accounts
 - [X] Service should expose a Rest API for getting the `account` details. You can disregard currencies at this time

Points to consider:

- [X] Keep the design simple and to the point. Should be scalable for adding new features
- [X] The datastore should run in-memory for the tests
- [X] Proper unit testing and decent coverage is a must
- [X] Upload the code to a repository
- [X] Disregard Currency or Rate Conversion
- [X]  Improvise where details are not provided

Plus Points:

- [ ] Kotlin used => Sowwyyyy
- [X] Proper handling of concurrent transactions for the accounts
- [X] Documentation of API
- [X] Dockerized app

### Terminologies
1. **Account**: Specific user, who has basic information. (name, email, username, password and county) and (balance).
2. **Role**: Granted Authority for our accounts: User, Moderator and Admin accounts. (Focus will be on user roles).
3. **Transaction**: a transfer of money happens in the system, It can be either (PAY) Involving 2 parties, (WITHDRAW/DEPOSIT) Which involves only one party, sender side, with a specific amount of money.
   1. Pay Transaction: Example (Hassan sends 1000$ to Marco).
   2. Withdraw Transaction: Example (Hassan withdraws 1000$ from his account).
   3. Deposit Transaction: Example (Hassan deposits 1000$ to his account).

### Authentication
Authentication used to set valid cookie, which lasts for 1 day. (With testing with postman, It's auto set, you can check it in cookies section.)
An Account should be able to deposit and withdraw only to/from his account through the identified cookie.

### API Documentation
You can follow `/api/bsfdv/swagger-ui.html` to find all information about APIs.

### Postman collection
You can follow the OpenAPI specification [here](./transaction-openapi.yaml).

### Happy scenario for testing the application
1. Signup => **POST** `/api/bsfdv/accounts` with payload in Body.
`{
   "name": "Hassan",
   "country": "Egypt",
   "email": "hassan.elseoudy1@gmail.com",
   "password": "123456",
   "username": "semsem"
   }`

2. Login => **POST** `/api/bsfdv/accounts/login` with payload in Body.
   `{
   "username": "semsem",
   "password": "123456"
   }`

3. Make a deposit transaction => **POST** `/api/bsfdv/transactions` with payload in Body.
`{
   "transactionType": "DEPOSIT",
   "amount": 20000
   }`

4. Now try to get account details (For development purposes, there's no restrictions, anyone can see balance information).
=> **GET** `/api/bsfdv/accounts/1` and the balance should be 20000 now.

