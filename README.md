# Monnify Payment Solution

This is a payment solution built with Java SpringBoot.
The application is integrated with Monnify (by Monniepoint) API for transactions.

The API documentation is intended for developers who  want to interact with the application's backend services.

## API Documentation

#### There are two entities User and Wallet

# USER API

## Sign Up
 - This function creates new users in the platform.
-    POST (https://{hostname}/v1/sign-up)


### Sample Request Header
    'Content-Type: application/json'

### Sample Request Body
    {
    "email": "test@test.com",
    "password": "P@ssw0rd",
    "bvn": "11111111111"
    }

### Sample Response Body
    {
    "message": "Hi John! Sign Up Successful, Please proceed to sign in"
    }

## Login User
 - This function logs users in the platform.
-    POST (https://{hostname}/v1/sign-in)

### Sample Request Header
    'Content-Type: application/json'

### Sample Request Body
    {
        "email": "test@test.com",
        "password": "myP@ssw0rd"
    }

### Sample Response Body
    {
    "token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsibW9ubmlmeS12YWx1ZS1hZGRlZC1zZXJ2aWNlIiwibW9ubmlmeS1wYXltZW50LWVuZ2luZSIsIm1vbm5pZnktZGlzYnVyc2VtZW50LXNlcnZpY2UiLCJtb25uaWZ5LW9mZmxpbmUtcGF5bWVudC1zZXJ2aWNlIl0sInNjb3BlIjpbInByb2ZpbGUiXSwiZXhwIjoxNzI3OTA1MDgzLCJhdXRob3JpdGllcyI6WyJNUEVfTUFOQUdFX0xJTUlUX1BST0ZJTEUiLCJNUEVfVVBEQVRFX1JFU0VSVkVEX0FDQ09VTlQiLCJNUEVfSU5JVElBTElaRV9QQVlNRU5UIiwiTVBFX1JFU0VSVkVfQUNDT1VOVCIsIk1QRV9DQU5fUkVUUklFVkVfVFJBTlNBQ1RJT04iLCJNUEVfUkVUUklFVkVfUkVTRVJWRURfQUNDT1VOVCIsIk1QRV9ERUxFVEVfUkVTRVJWRURfQUNDT1VOVCIsIk1QRV9SRVRSSUVWRV9SRVNFUlZFRF9BQ0NPVU5UX1RSQU5TQUNUSU9OUyJdLCJqdGkiOiJkNzZkMmMyMi04NzAzLTQ4NDctYWMxOS0xNjhhN2FiYWU4NDgiLCJjbGllbnRfaWQiOiJNS19URVNUX0hCNzY2NFJFUVkifQ.N5PgkrRFFquR6qvND7KsvtAIxo80eG1R6KoTj1f2sWDTvNX8rn1qxZ6ztYtMKAeLPNfTOTalHhLUBCNpP_i3P6ESmPSu2fELVRZb0IWT_6rtn9bomnXGDHZiM5EUt2q1C_p61dEdTebdx6ozkH_YpQ23cJYVur0hqervoW62uHQH4fpsCT5BnOQQtyuKnX5nOy_a59U6vqBsgDF31GPdenWt2jx7wvhq8LV2yg0H_cnVB_l4Vv4BWHLEq1K-8PYnyOKmvdcWPA5ZciklfyuyQzqAVp0tew_vNQOzyS73y3t5cKAJalzAFewqP78fCxrMZBieFy737BRsO6J2bRJiEg",
    "expiresIn": 3599
    }


# Wallet API


## Create Reserved Account
 - This function allows the system to a customer create reserved account for collection.
- POST (https://{hostname}/v1/create-reserved-account)

### Sample Request Header
    'Content-Type: application/json'

### Sample Request Body
    {    
        "accountName": "John Doe",
        "currencyCode": "NGN",
        "contractCode": "7059707855",
        "bvn":"11111111111",
        "customerEmail": "test@test.com",
        "customerName": "ysbbs hsbsb nxnznq",
        "nin":"34848484058",
        "getAllAvailableBanks": false,
        "preferredBanks": [232]
    }

### Sample Response Body
          {
              "requestSuccessful": true,
              "responseMessage": "success",
              "responseCode": "0",
              "responseBody": {
                  "contractCode": "8389328412",
                  "accountReference": "abc1234",
                  "accountName": "Tes",
                  "currencyCode": "NGN",
                  "customerEmail": "test@tester.com",
                  "customerName": "John Doe",
                  "accounts": [
                      {
                          "bankCode": "232",
                          "bankName": "Sterling bank",
                          "accountNumber": "8952095784",
                          "accountName": "Tes"
                      },
                      {
                          "bankCode": "035",
                          "bankName": "Wema bank",
                          "accountNumber": "7206930470",
                          "accountName": "Tes"
                      },
                      {
                          "bankCode": "50515",
                          "bankName": "Moniepoint Microfinance Bank",
                          "accountNumber": "6254727989",
                          "accountName": "Tes"
                      },
                      {
                          "bankCode": "058",
                          "bankName": "GTBank",
                          "accountNumber": "0784829227",
                          "accountName": "Tes"
                      }
                  ],
                  "collectionChannel": "RESERVED_ACCOUNT",
                  "reservationReference": "NWA7DMJ0W2UDK1KN5SLF",
                  "reservedAccountType": "GENERAL",
                  "status": "ACTIVE",
                  "createdOn": "2023-04-14 12:04:39.034",
                  "incomeSplitConfig": [],
                  "bvn": "21212121212",
                  "restrictPaymentSource": false
              }
    }


## Get Reserved Account
 - This function gets a reserved account.
-   GET (https://{hostname}/v1/get-reserved-account)

### Query Paramter
 `accountReference=MONN-OdS015`

### Sample Request Header
    'Content-Type: application/json'

### Sample Response Body
      {
          "requestSuccessful": true,
          "responseMessage": "success",
          "responseCode": "0",
          "responseBody": {
              "contractCode": "7059707855",
              "accountReference": "abc1niui--23",
              "accountName": "Test01-Tes",
              "currencyCode": "NGN",
              "customerEmail": "test@tester.com",
              "customerName": "mao  Zhang",
              "accounts": [
                  {
                      "bankCode": "035",
                      "bankName": "Wema bank",
                      "accountNumber": "5000588053",
                      "accountName": "Tes"
                  },
                  {
                      "bankCode": "232",
                      "bankName": "Sterling bank",
                      "accountNumber": "6001363175",
                      "accountName": "Tes"
                  }
              ],
              "collectionChannel": "RESERVED_ACCOUNT",
              "reservationReference": "8MD1E79P4LD94N6E2VNJ",
              "reservedAccountType": "GENERAL",
              "status": "ACTIVE",
              "createdOn": "2022-07-31 10:08:41.0",
              "contract": {
                  "name": "Default Contract",
                  "code": "7059707855",
                  "description": null
              },
              "transactionCount": 0,
              "incomeSplitConfig": [
                  {
                      "subAccountCode": "MFY_SUB_762212281785",
                      "feePercentage": 10.5,
                      "feeBearer": true,
                      "splitPercentage": 20,
                      "reservedAccountConfigCode": "HRCNXDSAYX"
                  }
              ],
              "bvn": "21212121212",
              "restrictPaymentSource": true
          }
    }
