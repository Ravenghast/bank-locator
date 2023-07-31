# My Bank Locator
How to build and run:

## 1. Build
Run the gradle build either through interactive IDE
(OR) in the command line by running the command ***gradle*** from the root folder of the project

## 2. Run the app
1. Go to the folder: *<project_folder>*\delivery
2. Run the batch file: **RunLocatorService.bat**
   <p>The app is up & running now, ready to receive REST requests in the default port 8080</p>

## 4. Test the app using Swagger
1. In a browser, open the link [here](http://localhost:8080/swagger-ui.html)
2. From the home page, click on the option: **locator-controller**
3. After that, click the **GET** button for the REST API *bank-locations*
4. Next click, the option **Try it out**
5. Enter the parameters for inputs: **postCode** (valid UK Postcode) and **serviceType** (valid values: BRANCH, ATM, BRANCH_AND_ATM) and finally click **Execute**
6. Try various test conditions for both positive & negative cases.
