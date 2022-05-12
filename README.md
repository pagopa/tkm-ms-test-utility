# Test Utility
This microservice provides APIs for E2E testing of TKM.

## Configuration
The following ENVIRONMENT variables are needed to deploy and run the application.


### Develop enviroment configuration


## How to start SIT azure pipeline

1. Move into:
> develop

1. Run:<br>
   `$version=??` for poweshell or `version=??` for gitbash<br>
   `mvn --batch-mode release:clean release:prepare`<br>
   `git checkout -b tmp/${version} test-utility-${version}`<br>
   `git push --set-upstream origin tmp/${version}`<br>

2. Merge **tmp/${version}** into **release/sit**

## How to start UAT azure pipeline

1. Merge **release/sit** into **release/uat**

## How to start PROD azure pipeline

1. Merge **release/uat** into **master**