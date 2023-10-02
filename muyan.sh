#!/bin/bash

# Check if docker is installed
if ! command -v docker &> /dev/null
then
    echo "Docker could not be found. Please install Docker and try again."
    exit
fi

# Check if docker-compose or docker compose is installed
if ! command -v docker-compose &> /dev/null && ! docker compose version &> /dev/null
then
    echo "Neither Docker-compose v1 nor v2 could be found. Please install docker compose and try again."
    exit
fi

# Clone the repository
git clone https://github.com/muyantech/platform.git

# Change directory to the cloned repository
cd platform

# Check which version of docker-compose is installed and run the appropriate commands
if command -v docker-compose &> /dev/null
then
    # Build the Docker image
    docker-compose build

    # Run the Docker container in detached mode
    docker-compose up -d
elif docker compose version &> /dev/null
then
    # Build the Docker image
    docker compose build

    # Run the Docker container in detached mode
    docker compose up -d
fi
