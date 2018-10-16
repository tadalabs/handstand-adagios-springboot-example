#!/usr/bin/env bash
aws dynamodb create-table --cli-input-json file:///etc/Widget.json --endpoint-url http://sample-dynamo:8000
