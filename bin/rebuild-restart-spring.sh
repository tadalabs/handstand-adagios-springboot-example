#!/usr/bin/env bash

docker stop sample-rest

./bin/rebuild-war.sh

docker start sample-rest