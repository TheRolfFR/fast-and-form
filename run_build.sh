#!/bin/sh
# Requires act to run Github Acitons locally: https://github.com/nektos/act
# Act uses Docker so install Docker before
act --job build workflow_dispatch --eventpath dispatch.json