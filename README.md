# Docuten Hash Verification dApp for Alastria

This is a decentralized application to verify on the Alastria Blockchain the status of documents and invoices which have been securely certified by Docuten (app.docuten.com).

The idea of this application is to provide an open source solution to allow our customers future verifications of already certified documents and invoices, without the need of relying on Docuten SaaS.

Basically, this dApp talks directly to the Alastria Telsius blockchain network, where our Docuten Smart Contracts ensure the proof-of-existence and proof-of-life of your documents and invoices are permanently stored and certified.

You only need the hash of the invoice/document you want to verify and introduce to ask directly to the blockchain!

# How does it work?

This application has two components:

* **server**: this folder contains a `spring-boot` project (built with `gradle`) which is able to directly interact to one of the Docuten Smart Contracts.

This project connects by default to one of our regular nodes (http://blockchain.docuten.com:22000) but you can modify the file `application.yaml` to point to the Alastria regular node you prefer (**remember that one of the advantages of the blockchain is that the information is not stored in one single server, but rather distributed in the whole network**).

This is the configuration you should change in case you want to connect to your own node:

```
endpoint:
  alastria:
    telsius:  'http://5.57.225.79:22000' //your node IP here
```


* **dapp**: A simple UI which allows an easy visual interaction with `server` to verify your documents/invoices hashes. By default it is pointing to a deployed version of `server` which is in Heroku. However, you can deploy the `server` wherever you want (by default in `localhost:8080`) and modify the endpoint in `dapp/src/app/tab/tab2.page.ts` (line 19):

```
  public endpoint = 'http://docuten-dapp.herokuapp.com'
```


# How to use it?

To easily use the UI you only have to run it locally or install the app in your phone.

However, remember that by default the UI connects to our deployed instance in Heroku. In other words, if you just run the UI you will be relying on an external server querying the blockchain on your behalf.

For example, when you see a summary of a hash in the UI, behind the curtains, the UI will be sending a request to an URL like:

http://docuten-dapp.herokuapp.com/documentDetails?documentHash=c4ecb5508773aef2d4a1c70339c375758741d8dd64c7a2d02e026c63b1c268ac

To avoid that dependence and have full control over your dApp, you need to build and run both modules on your own.

## Running the server

In order to run the server you can directly execute:

```
cd server
gradle clean bootRun
```

You can also build the `jar` and execute it afterwards with:

```
cd server
gradle clean build
java -jar build/libs/docuten-alastria-hash-verification-dApp-0.1.0.jar
```

Both options will end up executing a running instance of our `spring-boot` project, that is, `server`.

## Running the UI

First of all, you need to install Ionic and Cordova:

```
npm install -g ionic cordova
```

Once you have done that, you can execute the UI directly, or build a specific app for your mobile device (iOS or Android supported).

## Run webapp locally

```
cd dapp
ionic serve
```
## Use the dApp form your phone?

We are not gonna build an app for each operative system, but in case you want to do it, feel free!
The UI is built with Ionic, so you can easily transform it in a mobile app following the Ionic documentation.

### Build mobile app for IOS

```
cd dapp
npm install -g ios-deploy ios-sim
ionic cordova build ios --prod --verbose  --buildFlag="-UseModernBuildSystem=0"
open platforms/ios/MyApp.xcodeproj
```

### Android? Other operative systems?

We have not tested that, so in case you are interested is up to you :)
Check the Ionic documentation here: https://ionicframework.com/docs/building/android


# Do you want to talk directly to our Smart Contract?

If you want to create your own dApp, here you have all the info you need.
Basically, the network, the address and the ABI of our proxy smart contract:

* **Contract Network:** `Alastria Telsius` (Quorum)
* **Contract Address:** `0x8fe3a625641ce728058e83ee0a9f86676e34293c`
* **Contract ABI:**

```
[{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"authorizedUsers","outputs":[{"name":"","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"isOpenToEveryUser","outputs":[{"name":"","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"bytes32"}],"name":"idByDocumentHash","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"uint256"},{"name":"","type":"uint256"}],"name":"auditRegistryByDocumentId","outputs":[{"name":"description","type":"string"},{"name":"timestamp","type":"string"},{"name":"blockTimestamp","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"uint256"}],"name":"hashByDocumentId","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"uint256"}],"name":"ownerByDocumentId","outputs":[{"name":"","type":"address"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"delegateCallAddress","outputs":[{"name":"","type":"address"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"isLocked","outputs":[{"name":"","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"},{"name":"","type":"uint256"}],"name":"documentsByOwnerAddress","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"lastId","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"uint256"}],"name":"ipfsHashByDocumentId","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"contractOwner","outputs":[{"name":"","type":"address"}],"payable":false,"stateMutability":"view","type":"function"},{"inputs":[{"name":"existingContractAddress","type":"address"}],"payable":false,"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":false,"name":"call","type":"string"},{"indexed":false,"name":"result","type":"bool"}],"name":"DelegateCallEvent","type":"event"},{"constant":false,"inputs":[{"name":"existingContractAddress","type":"address"}],"name":"updateDelegateCallAddress","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[],"name":"lock","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[],"name":"unlock","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"user","type":"address"}],"name":"addAuthorizedUser","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"user","type":"address"}],"name":"removeCredentials","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[],"name":"openToEveryUser","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[],"name":"closeToAuthorizedUsers","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"_documentHash","type":"string"},{"name":"_ipfsHash","type":"string"},{"name":"_timestamp","type":"string"}],"name":"certifyDocumentCreationWithIPFSHash","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"_id","type":"uint256"},{"name":"_description","type":"string"},{"name":"_timestamp","type":"string"}],"name":"appendAuditRegistry","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"owner","type":"address"}],"name":"getDocumentsByOwner","outputs":[{"name":"","type":"uint256[]"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"_documentHash","type":"string"}],"name":"getDocumentDetailsByHash","outputs":[{"name":"","type":"uint256"},{"name":"","type":"string"},{"name":"","type":"string"},{"name":"","type":"address"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"_documentHash","type":"string"},{"name":"_index","type":"uint256"}],"name":"getAuditRegistryByDocumentHash","outputs":[{"name":"","type":"string"},{"name":"","type":"string"},{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"_documentHash","type":"string"}],"name":"countAuditRegistriesByDocumentHash","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"_id","type":"uint256"}],"name":"getDocumentDetailsById","outputs":[{"name":"","type":"uint256"},{"name":"","type":"string"},{"name":"","type":"string"},{"name":"","type":"address"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"_id","type":"uint256"},{"name":"_index","type":"uint256"}],"name":"getAuditRegistryByDocumentId","outputs":[{"name":"","type":"string"},{"name":"","type":"string"},{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"_documentHash","type":"string"}],"name":"getId","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"_stringInput","type":"string"}],"name":"stringToBytes32","outputs":[{"name":"result","type":"bytes32"}],"payable":false,"stateMutability":"pure","type":"function"}]
```

