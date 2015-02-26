# gcloud-play-sample
> Sample Play framework scala backend using [gcloud-node](//github.com/GoogleCloudPlatform/gcloud-node).

## Prerequisites

0. Install activator
1. Create a new cloud project on [console.developers.google.com](http://console.developers.google.com)
2. Export your project id:

    ```sh
    $ export PROJECT_ID=<project id>
    ```

## Running

#### Locally
    ```sh
    # Start the server
    $ activator run
    ```

#### [Docker](https://docker.com)

    ```sh
    # Check that Docker is running
    $ boot2docker up
    $ $(boot2docker shellinit)

    # Package the app
    $ sbt docker:stage

    # Build your Docker image
    $ docker build -t app ./target/docker

    # Start a new Docker container
    $ docker run -p 8080:8080 app

    # Test the app
    $ curl -X GET http://$(boot2docker ip):8080
    ```

#### [Managed VMs](https://developers.google.com/appengine/docs/managed-vms/)

    ```sh
    # Get gcloud
    $ curl https://sdk.cloud.google.com | bash

    # Authorize gcloud and set your default project
    $ gcloud auth login
    $ gcloud config set project $PROJECT_ID

    # Get App Engine component
    $ gcloud components update app

    # Check that Docker is running
    $ boot2docker up

    # Run the app locally
    $ gcloud preview app run target/docker
    $ curl -X GET http://localhost:8080

    # Run the app locally with debug
    $ gcloud --verbosity debug preview app run target/docker
    $ curl -X GET http://localhost:8080

    # Deploy the app to production
    $ gcloud preview app deploy target/docker
    $ curl -X GET http://$PROJECT_ID.appspot.com
    ```

## See
* build.sbt : Where the docker container configuration for GAE happens
* app.yaml : GAE managed VM config, see [Configuring a Managed VM](https://cloud.google.com/appengine/docs/managed-vms/config)
* conf/routes : GAE mandatory route for healthcheck, see [Lifecycle events](https://cloud.google.com/appengine/docs/managed-vms/custom-runtimes#lifecycle_events)