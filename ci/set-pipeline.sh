#!/bin/sh
echo y | fly -t home sp -p home-income-outcome -c pipeline.yml -l ../../credentials.yml
